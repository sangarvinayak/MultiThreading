package buildingh2O.tasks;

import buildingh2O.MoleculeGenerator;

public class generateAtomTask implements Runnable {

    private MoleculeGenerator generator;
    private String atom;

    public generateAtomTask(MoleculeGenerator generator, String atom) {
        this.generator = generator;
        this.atom = atom;
    }


    @Override
    public void run() {
        try {
            generator.generateAtom(atom);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
