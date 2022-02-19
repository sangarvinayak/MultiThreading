package uberride;

import uberride.tasks.bookRideTask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demonstration {

    private static int POOL_SIZE = 50;
    private static int TEST_LIMIT = 100;
    private static String FIRST_PARTY_SYMBOL = "R";
    private static String SECOND_PARTY_SYMBOL = "D";

    public static void main(String[] args) {
        RideGenerator uber = new RideGenerator(FIRST_PARTY_SYMBOL, SECOND_PARTY_SYMBOL);
        ExecutorService threadPool = getThreadPool(POOL_SIZE);
        List<Runnable> assignedTasks = getAssignedTasks(TEST_LIMIT, uber);
        assignedTasks.forEach(threadPool::execute);
    }

    private static List<Runnable> getAssignedTasks(int testLimit,
                                                   RideGenerator generator) {
        List<Runnable> tasks = new ArrayList<Runnable>();
        int countFirst = 0, countSecond = 0;
        List<String> passengers = Arrays.asList(FIRST_PARTY_SYMBOL, SECOND_PARTY_SYMBOL);
        for(int i = 0; i < testLimit; i++) {
            String randomPassenger = passengers.get((int) ((new Random().nextInt(2))%2));
            if(randomPassenger.equals(FIRST_PARTY_SYMBOL)) {countFirst++;randomPassenger += "_" + countFirst;}
            if(randomPassenger.equals(SECOND_PARTY_SYMBOL)) {countSecond++;randomPassenger += "_" + countSecond;}
            tasks.add(new bookRideTask(generator, randomPassenger));
        }
        return tasks;
    }

    private static ExecutorService getThreadPool(int poolSize){
        return Executors.newFixedThreadPool(poolSize);
    }



}
