package buildingh2O;

import java.util.ArrayList;
import java.util.List;

public class MoleculeGenerator {

    private int requiredFirstAtomCount;
    private int requiredSecondAtomCount;
    private int totalFirstAtomCount;
    private int totalSecondCount;
    private String firstAtomSymbol;
    private String secondAtomSymbol;
    private List<String> seqOfAtoms;

    public MoleculeGenerator(int requiredFirstAtomCount, int requiredSecondAtomCount,
                             String firstAtomSymbol, String secondAtomSymbol) {
        this.requiredFirstAtomCount = requiredFirstAtomCount;
        this.requiredSecondAtomCount = requiredSecondAtomCount;
        this.firstAtomSymbol = firstAtomSymbol;
        this.secondAtomSymbol = secondAtomSymbol;
        this.totalFirstAtomCount = 0;
        this.totalSecondCount = 0;
        this.seqOfAtoms = new ArrayList<String>();
    }

    public synchronized void generateAtom(String atom) throws InterruptedException {
        if(atom.equals(firstAtomSymbol)){
            while(totalFirstAtomCount == requiredFirstAtomCount)
                this.wait();
            totalFirstAtomCount++;
            seqOfAtoms.add(atom);
        } else if(atom.equals(secondAtomSymbol)){
            while(totalSecondCount == requiredSecondAtomCount)
                this.wait();
            totalSecondCount++;
            seqOfAtoms.add(atom);
        } else {;}
        if(totalFirstAtomCount == requiredFirstAtomCount && totalSecondCount == requiredSecondAtomCount){
            seqOfAtoms.forEach(System.out::print);
            System.out.println("");
            this.totalFirstAtomCount = 0;
            this.totalSecondCount = 0;
            this.seqOfAtoms = new ArrayList<String>();
        }
        this.notifyAll();
    }
}
