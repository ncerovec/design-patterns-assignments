/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_1;

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
import ncerovec_zadaca_1.algorithm.AlgorithmMaxDepth;
import ncerovec_zadaca_1.algorithm.AlgorithmMaxLevel;
import ncerovec_zadaca_1.algorithm.AlgorithmRandom;
import ncerovec_zadaca_1.model.Club;
import ncerovec_zadaca_1.model.dive.Dive;
import ncerovec_zadaca_1.model.dive.DiveBuilder;
import ncerovec_zadaca_1.model.dive.DiveBuilderMaxDepth;
import ncerovec_zadaca_1.model.dive.DiveBuilderMaxLevel;
import ncerovec_zadaca_1.model.dive.DiveBuilderRandom;
import ncerovec_zadaca_1.model.dive.DiveManager;
import ncerovec_zadaca_1.model.diver.Diver;
import ncerovec_zadaca_1.model.diver.DiverFactory;
import ncerovec_zadaca_1.model.diver.DiverFactoryAgency;

/**
 *
 * @author nino
 */
public class Diving
{
    private static final DiverFactory diverFactory = new DiverFactoryAgency();
    
    private static final DiveManager diveSelector = new DiveManager();
    private static DiveBuilder diveBuilder = null;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {        
        if(args.length >= 5)
        {
            int randSeed = Integer.parseInt(args[0]);
            String diversFile = args[1];
            String divesFile = args[2];
            String algType = args[3];
            String outputFile = args[4];
            
            if(randSeed >= 100)
            {
                if(algType.equals(AlgorithmMaxDepth.algName) || algType.equals(AlgorithmMaxLevel.algName) || algType.equals(AlgorithmRandom.algName))
                {
                    //List<Diver> divers = readDivers(diversFile);
                    readDivers(diversFile); //divers set in function
                    
                    if(Club.getInstance().getDivers().size() > 1)
                    {
                        readAlgorithm(algType, randSeed);

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
                    System.out.println("Zadani algoritam ne postoji!");
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
            System.out.println("Primjer pokretanja: ncerovec_zadaca_1 717 DZ_1_ronioci.txt DZ_1_uroni.txt AlgoritamMaksUron izlaz.txt");
        }
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
     * Read algorithm and initialize DiveBuilder
     * @param algorithm 
     */
    private static void readAlgorithm(String algorithm, int randSeed)
    {
        switch(algorithm)
        {
            case AlgorithmMaxDepth.algName:
                diveBuilder = new DiveBuilderMaxDepth(randSeed);
            break;

            case AlgorithmMaxLevel.algName:
                diveBuilder = new DiveBuilderMaxLevel(randSeed);
            break;

            case AlgorithmRandom.algName:
                diveBuilder = new DiveBuilderRandom(randSeed);
            break;                
        }
        diveSelector.setDiveBuilder(diveBuilder);
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
        String textDivers = Club.getInstance().printDivers();
        System.out.println(textDivers);

        String textDives = Club.getInstance().printDives();
        System.out.println(textDives);
                    
        String textDiversDives = Club.getInstance().printAllDiverDives();
        System.out.println(textDiversDives);
        
        writeToFile(outputFile, textDivers+textDives+textDiversDives);
    }
    
    private static void writeToFile(String outputFile, String text)
    {
        List<String> lines = Arrays.asList(text.split("\n"));
        Path file = Paths.get(outputFile);
        
        try { Files.write(file, lines, Charset.forName("UTF-8")); }
        catch (IOException ex) { Logger.getLogger(Diving.class.getName()).log(Level.SEVERE, null, ex); }
    }
}
