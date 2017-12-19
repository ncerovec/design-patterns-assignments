/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_4.context;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ncerovec_zadaca_4.Diving;
import ncerovec_zadaca_4.model.Club;
import ncerovec_zadaca_4.model.diver.Diver;
import ncerovec_zadaca_4.model.diver.DiverFactory;
import ncerovec_zadaca_4.mvc.divers.DiversController;
import ncerovec_zadaca_4.mvc.divers.DiversModel;
import ncerovec_zadaca_4.mvc.divers.DiversView;

/**
 *
 * @author nino
 */
public class DiversContext
{
    public static DiversView diversView = null;
    public static DiversController diversController = null;
    
    private DiverFactory diverFactory = null;
    //private static final DiverFactory diverFactory = new DiverFactoryAgency();

    public DiversContext(DiverFactory diverFactory)
    {
        this.diverFactory = diverFactory;
    }
    
    /**
     * Read divers file and create divers
     * @param diversFile
     * @return 
     */
    public List<Diver> readDivers(String diversFile)
    {
        System.out.println("\n -- Čitanje ronioca --");
        
        List<Diver> divers = Club.getInstance().getDivers();
        
        try
        {           
            BufferedReader inDivers = new BufferedReader(new FileReader(diversFile));                

            String diver = null;
            while((diver = inDivers.readLine()) != null)
            {
                System.out.println("Pročitan redak: " + diver);
                String[] diverProps = diver.split(";");
                
                if(diverProps.length >= 4)
                {
                    String diverName = diverProps[0];
                    String diverAgency = diverProps[1];
                    String diverLvl = diverProps[2];
                    int diverBirthYear = Integer.parseInt(diverProps[3]);

                    Diver newDiver = diverFactory.createDiver(diverName, diverAgency, diverLvl, diverBirthYear);
                    if(newDiver != null) divers.add(newDiver);
                    
                    System.out.println(" -> ronioc kreiran");
                }
                else
                {
                    System.out.println(" -> neispravan redak");
                }
            }
            
            inDivers.close();
        }
        catch (IOException ex) { Logger.getLogger(Diving.class.getName()).log(Level.SEVERE, null, ex); }
        
        return divers;
    }
    
    public static void showDiversView(List<Diver> diversList)
    {
        DiversModel diversModel = new DiversModel(diversList);
        DiversContext.diversController = new DiversController(diversModel, DiversContext.diversView);
        diversModel.setController(DiversContext.diversController);
        DiversContext.diversView.setController(DiversContext.diversController);
        DiversContext.diversController.updateView();
        DiversContext.diversController.enableInput();
    }    
}
