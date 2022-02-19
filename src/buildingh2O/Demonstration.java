package buildingh2O;

import buildingh2O.tasks.generateAtomTask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demonstration {

    private static int POOL_SIZE = 50;
    private static int TEST_LIMIT = 100;
    private static int FIRST_ATOM_COUNT = 1;
    private static int SECOND_ATOM_COUNT = 2;
    private static String FIRST_ATOM_SYMBOL = "C";
    private static String SECOND_ATOM_SYMBOL = "O";

    public static void main(String[] args) {
        MoleculeGenerator generator = new MoleculeGenerator(FIRST_ATOM_COUNT, SECOND_ATOM_COUNT,
                                                            FIRST_ATOM_SYMBOL, SECOND_ATOM_SYMBOL);
        ExecutorService threadPool = getThreadPool(POOL_SIZE);
        List<Runnable> assignedTasks = getAssignedTasks(TEST_LIMIT, generator);
        assignedTasks.forEach(threadPool::execute);
    }

    private static List<Runnable> getAssignedTasks(int testLimit,
                                                   MoleculeGenerator generator) {
        List<Runnable> tasks = new ArrayList<Runnable>();
        List<String> atoms = Arrays.asList(FIRST_ATOM_SYMBOL, SECOND_ATOM_SYMBOL);
        for(int i = 0; i < testLimit; i++) {
            String randomAtom = atoms.get((int) ((new Random().nextInt(2))%2));
            tasks.add(new generateAtomTask(generator, randomAtom));
        }
        return tasks;
    }

    private static ExecutorService getThreadPool(int poolSize){
        return Executors.newFixedThreadPool(poolSize);
    }



}
