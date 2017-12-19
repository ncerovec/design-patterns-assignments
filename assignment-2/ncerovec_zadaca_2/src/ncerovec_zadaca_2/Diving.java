/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ncerovec_zadaca_2.algorithm.AlgorithmMaxDepth;
import ncerovec_zadaca_2.algorithm.AlgorithmMaxLevel;
import ncerovec_zadaca_2.algorithm.AlgorithmRandom;
import ncerovec_zadaca_2.model.Club;
import ncerovec_zadaca_2.model.agency.AgencyBSAC;
import ncerovec_zadaca_2.model.agency.AgencyCMAS;
import ncerovec_zadaca_2.model.agency.AgencyNAUI;
import ncerovec_zadaca_2.model.agency.AgencySSI;
import ncerovec_zadaca_2.model.agency.AssociationHRS;
import ncerovec_zadaca_2.model.dive.Dive;
import ncerovec_zadaca_2.model.dive.DiveBuilder;
import ncerovec_zadaca_2.model.dive.DiveBuilderMaxDepth;
import ncerovec_zadaca_2.model.dive.DiveBuilderMaxLevel;
import ncerovec_zadaca_2.model.dive.DiveBuilderRandom;
import ncerovec_zadaca_2.model.dive.DiveManager;
import ncerovec_zadaca_2.model.diver.Diver;
import ncerovec_zadaca_2.model.diver.DiverFactory;
import ncerovec_zadaca_2.model.diver.DiverFactoryAgency;
import ncerovec_zadaca_2.visitor.GroupLevelsPrintVisitor;
import ncerovec_zadaca_2.visitor.GroupStructure;

/**
 *
 * @author nino
 */
public class Diving
{
    private static final DiverFactory diverFactory = new DiverFactoryAgency();
    
    private static final DiveManager diveSelector = new DiveManager();
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {        
        if(args.length >= 7)
        {
            int randSeed = Integer.parseInt(args[0]);
            String diversFile = args[1];
            String divesFile = args[2];
            String[] algTypes = {args[3],args[4],args[5]};
            String outputFile = args[6];
            
            if(randSeed >= 100)
            {
                if(AppHelper.checkStringsUnique(algTypes))
                {
                    registerObservers();
                    
                    GroupLevelsPrintVisitor glp = new GroupLevelsPrintVisitor();                    
                    glp.visit(GroupStructure.getDiverGroups());
                    
                    //List<Diver> divers = readDivers(diversFile);
                    readDivers(diversFile); //divers set in function
                    
                    if(Club.getInstance().getDivers().size() > 1)
                    {
                        readAlgorithms(algTypes, randSeed);

                        //List<Dive> dives = readDives(divesFile);
                        readDives(divesFile); //dives set in function

                        writeOutput(outputFile);
                    }
                    else
                    {
                        System.out.println("Nedovoljan broj ronioca u klubu!");
                    }
                }
                else
                {
                    System.out.println("Ne mogu se uspoređivati isti algoritmi!");
                }
            }
            else
            {
                System.out.println("Neispravan broj za sjeme slučajnog broja (min 100)!");
            }
        }
        else
        {
            System.out.println("Neispravan broj argumenata!");
            System.out.println("Primjer pokretanja: ncerovec_zadaca_2 717 DZ_2_ronioci.txt DZ_2_uroni.txt AlgoritamMaksUron AlgoritamMaksRazina AlgoritamSlucajno izlaz.txt");
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
     * Read divers file and create divers
     * @param diversFile
     * @return 
     */
    private static List<Diver> readDivers(String diversFile)
    {
        System.out.println("\n -- Čitanje ronioca --");
        
        List<Diver> divers = new ArrayList<>();
        Club.getInstance().setDivers(divers);
        
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
    
    /**
     * Read algorithms and pass them to DiveManager
     * @param algorithm 
     */
    private static void readAlgorithms(String[] alternativeAlgorithms, int randSeed)
    {
        diveSelector.setDiveBuilder(new DiveBuilder(randSeed));
        
        for(String algorithm : alternativeAlgorithms)
        {
            switch(algorithm)
            {
                case AlgorithmMaxDepth.algName:
                    diveSelector.addDiveBuilder(new DiveBuilderMaxDepth(randSeed));
                break;

                case AlgorithmMaxLevel.algName:
                    diveSelector.addDiveBuilder(new DiveBuilderMaxLevel(randSeed));
                break;

                case AlgorithmRandom.algName:
                    diveSelector.addDiveBuilder(new DiveBuilderRandom(randSeed));
                break;                
            }
        }
    }
    
    /**
     * Read dives file and create dives
     * @param divesFile
     * @return 
     */
    private static List<Dive> readDives(String divesFile)
    {
        System.out.println("\n -- Čitanje urona --");
        
        List<Dive> dives = new ArrayList<>();
        Club.getInstance().setDives(dives);
        
        try
        {
            BufferedReader inDives = new BufferedReader(new FileReader(divesFile));                

            String dive = null;
            while((dive = inDives.readLine()) != null)
            {
                System.out.println("Pročitan redak: " + dive);
                String[] diveProps = dive.split(";");
                
                if(diveProps.length >= 4)
                {
                    String diveDate = diveProps[0];
                    String diveTime = diveProps[1];
                    int diveMaxDepth = Integer.parseInt(diveProps[2]);
                    int diveNumDiver = Integer.parseInt(diveProps[3]);
                    
                    if(diveNumDiver > 1)
                    {
                        diveSelector.createDive(diveDate, diveTime, diveMaxDepth, diveNumDiver);
                        Dive newDive = diveSelector.getDive();
                        if(newDive != null) dives.add(newDive);

                        System.out.println(" -> uron kreiran");
                        Club.getInstance().setState(newDive);   //send notifications about new dive
                    }
                    else
                    {
                        System.out.println(" -> nedovoljan broj ronioca za uron!");
                    }
                }
                else
                {
                    System.out.println(" -> neispravan redak");
                }
            }
            inDives.close();

        }
        catch (IOException ex) { Logger.getLogger(Diving.class.getName()).log(Level.SEVERE, null, ex); }
        
        return dives;
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
}
