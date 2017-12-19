/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_4.algorithm;

import java.util.ArrayList;
import java.util.List;
import ncerovec_zadaca_4.model.dive.combinations.DiveCombination;
import ncerovec_zadaca_4.model.diver.Diver;

/**
 *
 * @author nino
 */
public class AlgorithmMaxDepth implements Algorithm
{
    public static final String algName = "AlgoritamMaksUron";

    @Override
    public String getAlgName() { return algName; }
    
    private List<DiveCombination> diveCmbs = null;
    
    private List<Diver> listAnyDivers = new ArrayList<>();
    private List<Diver> list10mDivers = new ArrayList<>();
    private List<Diver> list30mDivers = new ArrayList<>();
    private List<Diver> list40mDivers = new ArrayList<>();
    
    @Override
    public List<DiveCombination> makeDiveCombinations(List<Diver> diveDivers, int depthLimit)
    {
        this.diveCmbs = new ArrayList<>();
        
        this.listAnyDivers = new ArrayList<>();
        this.list10mDivers = new ArrayList<>();
        this.list30mDivers = new ArrayList<>();
        this.list40mDivers = new ArrayList<>();
        
        this.sortDivers(diveDivers, depthLimit);
        
        //not using makePairs before makePairsOrTroikas (first make Troika for every group, then make Pairs with rest of divers in group)
        this.diveCmbs = AlgorithmHelper.makeTroikaOrPairs(this.diveCmbs, this.list10mDivers, depthLimit, this.listAnyDivers);
        this.diveCmbs = AlgorithmHelper.makeTroikaOrPairs(this.diveCmbs, this.list30mDivers, depthLimit, this.listAnyDivers);
        this.diveCmbs = AlgorithmHelper.makeTroikaOrPairs(this.diveCmbs, this.list40mDivers, depthLimit, this.listAnyDivers);
        
        this.diveCmbs = AlgorithmHelper.makeTroikaOrPairs(this.diveCmbs, this.listAnyDivers, depthLimit, this.listAnyDivers);

        if(!this.listAnyDivers.isEmpty() || !this.list10mDivers.isEmpty() || !this.list30mDivers.isEmpty() || !this.list40mDivers.isEmpty())
        {
            System.out.println("Greška u algoritmu, nisu svi ronioci uključeni!");
        }
        
        return this.diveCmbs;
    }
    
    private void sortDivers(List<Diver> diveDivers, int depthLimit)
    {    
        //sort every diver in group with other divers with same depth limit
        for(Diver diver : diveDivers)
        {
            //to sort only by DiverMaxDepth level - remove Dive-depthLimit from sorting algorithm
            if(depthLimit > 0 && depthLimit <= 10 && diver.getLevel().getMaxDepth() == 10)
                this.list10mDivers.add(diver);
            else if(depthLimit > 10 && depthLimit <= 30 && diver.getLevel().getMaxDepth() == 30)
                this.list30mDivers.add(diver);
            else if(depthLimit > 30 && diver.getLevel().getMaxDepth() == 40)
                this.list40mDivers.add(diver);
            else
                this.listAnyDivers.add(diver);
        }
        
        int size10m = this.list10mDivers.size();
        int size30m = this.list30mDivers.size();
        int size40m = this.list40mDivers.size();
        
        //make every group have zero or more than one diver
        if(size10m == 1)
        {
            Diver odd10mDiver = this.list10mDivers.get(0);
            this.listAnyDivers.add(odd10mDiver);
            this.list10mDivers.remove(odd10mDiver);
        }        
        
        if(size30m == 1)
        {
            Diver odd30mDiver = this.list30mDivers.get(0);
            this.listAnyDivers.add(odd30mDiver);
            this.list30mDivers.remove(odd30mDiver);
        }
        
        if(size40m == 1)
        {
            Diver odd40mDiver = this.list40mDivers.get(0);
            this.listAnyDivers.add(odd40mDiver);
            this.list40mDivers.remove(odd40mDiver);
        }
        
        //make group anyGroup to have more than one diver
        int sizeAny = this.listAnyDivers.size();
        size10m = this.list10mDivers.size();
        size30m = this.list30mDivers.size();
        size40m = this.list40mDivers.size();
        if(sizeAny == 1)
        {
            //all groups have zero or more than one diver but anyGroup has only one diver
            
            if(size10m == 1 || (size10m > 2 && size10m%2 == 0)) //needless to check if size=1 (taken care of before)
            {
                Diver diver = this.list10mDivers.get(size10m-1);
                
                this.listAnyDivers.add(diver);
                this.list10mDivers.remove(diver);
            }
            else if(size30m == 1 || (size30m > 2 && size30m%2 == 0))
            {
                Diver diver = this.list30mDivers.get(size30m-1);
                
                this.listAnyDivers.add(diver);
                this.list30mDivers.remove(diver);
            }
            else if(size40m == 1 || (size40m > 2 && size40m%2 == 0))
            {
                Diver diver = this.list40mDivers.get(size40m-1);
                
                this.listAnyDivers.add(diver);
                this.list40mDivers.remove(diver);
            }
            else
            {
                //all diver groups have odd number of divers or have 2 divers
                //take two divers from group with two divers and add them to anyGroup
                if(size10m == 2)
                {
                    Diver firstDiver = this.list10mDivers.get(size10m-1);
                    Diver secondDiver = this.list10mDivers.get(size10m-2);

                    this.listAnyDivers.add(firstDiver);
                    this.list10mDivers.remove(firstDiver);
                    
                    this.listAnyDivers.add(secondDiver);
                    this.list10mDivers.remove(secondDiver);
                }
                else if(size30m == 2)
                {
                    Diver firstDiver = this.list30mDivers.get(size30m-1);
                    Diver secondDiver = this.list30mDivers.get(size30m-2);

                    this.listAnyDivers.add(firstDiver);
                    this.list30mDivers.remove(firstDiver);
                    
                    this.listAnyDivers.add(secondDiver);
                    this.list30mDivers.remove(secondDiver);
                }
                else if(size40m == 2)
                {
                    Diver firstDiver = this.list40mDivers.get(size40m-1);
                    Diver secondDiver = this.list40mDivers.get(size40m-2);

                    this.listAnyDivers.add(firstDiver);
                    this.list40mDivers.remove(firstDiver);
                    
                    this.listAnyDivers.add(secondDiver);
                    this.list40mDivers.remove(secondDiver);
                }
                else
                {
                    //all diver groups have odd number of divers and more than 2 divers
                    //take one diver from group with most divers and add it to anyGroup - restricting taking from empty group
                    if(size10m >= size30m && size10m >= size40m)
                    {
                        Diver diver = this.list10mDivers.get(size10m-1);

                        this.listAnyDivers.add(diver);
                        this.list10mDivers.remove(diver);
                    }
                    else if(size30m >= size10m && size30m >= size40m)
                    {
                        Diver diver = this.list30mDivers.get(size30m-1);

                        this.listAnyDivers.add(diver);
                        this.list30mDivers.remove(diver);
                    }
                    else //(size40m >= size10m && size40m >= size30m)
                    {
                        Diver diver = this.list40mDivers.get(size40m-1);

                        this.listAnyDivers.add(diver);
                        this.list40mDivers.remove(diver);
                    }
                }
            }
        }
    }
    
    /*
    private void makePairs(List<Diver> groupDivers, int depthLimit)
    {
        int itr = groupDivers.size()/2;
        for(int i=0; i < itr; i++)
        {
            DivePairs pair = new DivePairs();
            
            Diver diver1 = groupDivers.get(i*2);
            Diver diver2 = groupDivers.get(i*2+1);
            
            pair.setDiver1(diver1);
            //list10mDivers.remove(diver1);
            pair.setDiver2(diver2);
            //list10mDivers.remove(diver2);
            
            //clear list when last iteration
            if(i+1 == itr)
            {
                //if any - add last (odd) Diver to listAnyDivers group
                if(groupDivers.size()%2 > 0)
                    this.listAnyDivers.add(groupDivers.get(i*2+2));
                
                groupDivers.clear(); //prevents check if all divers are included in combinations (clears this.listAnyDivers)
            }
            
            pair.calculateCombDepths(depthLimit);
            this.diveCmbs.add(pair);    //add created pair to combination list
        }
    }

    private void makePairsOrTroikas(List<Diver> groupDivers, int depthLimit)
    {
        int sizeGroup = groupDivers.size();
        if(sizeGroup%2 > 0)
        {
            DiveTroikas troikas = new DiveTroikas();
            
            Diver diver1 = groupDivers.get(sizeGroup-1);
            Diver diver2 = groupDivers.get(sizeGroup-2);
            Diver diver3 = groupDivers.get(sizeGroup-3);
            
            troikas.setDiver1(diver1);
            groupDivers.remove(diver1);
            troikas.setDiver2(diver2);
            groupDivers.remove(diver2);
            troikas.setDiver3(diver3);
            groupDivers.remove(diver3);
            
            troikas.calculateCombDepths(depthLimit);
            this.diveCmbs.add(troikas);    //add created troika to combination list
        }
        
        if(sizeGroup%2 == 0)
        {
            makePairs(groupDivers, depthLimit);
        }
    }
    */
}
