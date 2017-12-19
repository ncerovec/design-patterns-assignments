/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_4;

import ncerovec_zadaca_4.helper.AppHelper;
import ncerovec_zadaca_4.context.SpecialtiesContext;
import ncerovec_zadaca_4.context.DiversContext;
import ncerovec_zadaca_4.context.EquipmentContext;
import ncerovec_zadaca_4.context.DivesContext;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ncerovec_zadaca_4.algorithm.AlgorithmMaxDepth;
import ncerovec_zadaca_4.algorithm.AlgorithmMaxLevel;
import ncerovec_zadaca_4.algorithm.AlgorithmRandom;
import ncerovec_zadaca_4.model.Club;
import ncerovec_zadaca_4.model.agency.AgencyBSAC;
import ncerovec_zadaca_4.model.agency.AgencyCMAS;
import ncerovec_zadaca_4.model.agency.AgencyNAUI;
import ncerovec_zadaca_4.model.agency.AgencySSI;
import ncerovec_zadaca_4.model.dive.DiveManager;
import ncerovec_zadaca_4.model.diver.DiverFactory;
import ncerovec_zadaca_4.model.diver.DiverFactoryAgency;
import ncerovec_zadaca_4.model.diver.DiverFactorySpecialties;
import ncerovec_zadaca_4.model.institution.AssociationHRS;
import ncerovec_zadaca_4.mvc.dive.DiveView;
import ncerovec_zadaca_4.mvc.divers.DiversView;
import ncerovec_zadaca_4.mvc.equipment.EquipmentView;
import ncerovec_zadaca_4.strategy.divers.QualifiedDiverSetStrategy;

/**
 *
 * @author nino
 */
public class Diving
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {        
        if(args.length >= 6)
        {
            int numRows = 35;
            int numCols = 160;
            int numRowsBuffer = 200;
            
            int randSeed = Integer.parseInt(args[0]);
            
            String diversFile = args[1];
            String divesFile = args[2];
            String specialtiesFile = args[3];
            String equipmentFile = args[4];
            String outputFile = "izlaz.txt";
            
            String[] algTypes = {AlgorithmMaxDepth.algName,AlgorithmMaxLevel.algName,AlgorithmRandom.algName};
            
            //int maxDepth = 35;
            //float tempWater = 15;
            //boolean nightDive = false;
            //int numCameras = 1;
            
            int maxLoanDays = Integer.parseInt(args[5]);
            
            if(randSeed < 100) System.out.println("Neispravan broj za sjeme slučajnog broja (min 100)!");
            else if(!AppHelper.checkStringsUnique(algTypes)) System.out.println("Ne mogu se uspoređivati isti algoritmi!");
            else if (numRows < 24 || numRows > 40) System.out.println("Neispravan broj redaka za sučelje (min 24/max 40)!");
            else if (numCols < 80 || numCols > 160) System.out.println("Neispravan broj stupaca za sučelje (min 80/max 160)!");
            else if (numRowsBuffer < numRows || numRowsBuffer > 400) System.out.println("Neispravna veličina spremnika za sučelje (min <broj redaka>/max 400)!");
            //else if (maxDepth < 5 || maxDepth > 40) System.out.println("Dubina urona nije valjana (min 5/max 40)!");
            //else if (tempWater < 0 || tempWater > 35) System.out.println("Temperatura vode nije valjana (min 0/max 35)!");
            //else if (numCameras < 0) System.out.println("Broj kamera nije valjan (min 0)!");
            else if (maxLoanDays < 1 || maxLoanDays > 14) System.out.println("Neispravan maksimalan broj dana posudbe (min 1/max 14)!");
            else
            {   
                DiversContext.diversView = new DiversView(numRows, numCols, numRowsBuffer);
                EquipmentContext.equipmentView = new EquipmentView(numRows, numCols, numRowsBuffer);
                DivesContext.diveView = new DiveView(numRows, numCols, numRowsBuffer);
                
                //registerObservers();

                //GroupLevelsPrintVisitor glp = new GroupLevelsPrintVisitor();                    
                //glp.visit(GroupStructure.getDiverGroups());

                //read equipment
                if(equipmentFile != null)
                {
                    EquipmentContext.maxLoanDays = maxLoanDays;
                    new EquipmentContext().readEquipment(equipmentFile);
                }

                //read divers & specialties
                DiversContext diversContext = null;
                DiverFactory diverFactory = null;
                if(specialtiesFile != null)
                {
                    List<String> specialtiesAssignmentsList = new SpecialtiesContext().readSpecialtiesList(specialtiesFile);
                    diverFactory = new DiverFactorySpecialties(specialtiesAssignmentsList);
                    diversContext = new DiversContext(diverFactory);
                }
                else
                {
                    diverFactory = new DiverFactoryAgency();
                    diversContext = new DiversContext(diverFactory);
                }
                diversContext.readDivers(diversFile);

                //read dives
                if(Club.getInstance().getDivers().size() > 1)
                {
                    DiveManager diveSelector = new DiveManager();
                    DivesContext divesContext = new DivesContext(diveSelector);

                    divesContext.readAlgorithms(algTypes);
                    
                    //diveSelector.setStrategy(new RandomDiverSetStrategy(randSeed));
                    diveSelector.setStrategy(new QualifiedDiverSetStrategy(randSeed));
                    //List<Dive> dives = readDives(divesFile);
                    divesContext.readDives(divesFile); //dives set in function
                    
                    //writeOutput(outputFile);    //output all data/results/statistics (console+file)
                }
                else
                {
                    System.out.println("Nedovoljan broj ronioca u klubu!");
                }
            }
        }
        else
        {
            System.out.println("Neispravan broj argumenata!");
            System.out.println("Primjer pokretanja: ncerovec_zadaca_4 515 DZ_4_ronioci.txt DZ_4_uroni.txt DZ_4_specijalnosti.txt DZ_4_oprema.txt 7");
        }
    }
    
    /**
     * Register agencies as observers for member dives (DiveNotification)
     * @return 
     */
    private static void registerObservers()
    {
        Club.getInstance().addObserver(AgencyBSAC.getInstance());
        Club.getInstance().addObserver(AgencyCMAS.getInstance());
        Club.getInstance().addObserver(AgencyNAUI.getInstance());
        Club.getInstance().addObserver(AgencySSI.getInstance());
        Club.getInstance().addObserver(AssociationHRS.getInstance());
    }

    /**
     * Write output of program to file and console
     * @param outputFile 
     */
    private static void writeOutput(String outputFile)
    {
        String textDivers = Club.getInstance().printDiversTable();
        System.out.println(textDivers);

        String textDives = Club.getInstance().printDivesTable();
        System.out.println(textDives);
                    
        String textDiversDives = Club.getInstance().printDetailDiversTable();
        System.out.println(textDiversDives);

        String textDivesDivers = Club.getInstance().printDetailDivesTable();
        System.out.println(textDivesDivers);

        String textDivingStats = Club.getInstance().printStatsTable();
        System.out.println(textDivingStats);
                
        writeToFile(outputFile, textDivers+textDives+textDiversDives+textDivesDivers+textDivingStats);
    }
    
    private static void writeToFile(String outputFile, String text)
    {
        List<String> lines = Arrays.asList(text.split("\n"));
        Path file = Paths.get(outputFile);
        
        try { Files.write(file, lines, Charset.forName("UTF-8")); }
        catch (IOException ex) { Logger.getLogger(Diving.class.getName()).log(Level.SEVERE, null, ex); }
    }
    
    public static void quitProgram() { System.exit(0); }
}
