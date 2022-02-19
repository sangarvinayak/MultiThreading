package printingthings.alternatethreenos;

import printingthings.alternatethreenos.tasks.PrintTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demonstration {

    private static int POOL_SIZE = 30;
    private static int TEST_START = 1;
    private static int TEST_END = 60;

    public static void main(String[] args) {
        NumberPrinter numberPrinter = new NumberPrinter(TEST_START);
        List<PrintTask> printTasks = getPrintTasks(numberPrinter,TEST_START, TEST_END, POOL_SIZE);
        ExecutorService threadPool = getThreadPool(POOL_SIZE);
        printTasks.forEach(threadPool::execute);
    }

    private static List<PrintTask> getPrintTasks(NumberPrinter numberPrinter, int start,
                                                 int end, int poolSize) {
        List<PrintTask> printTasks = new ArrayList<PrintTask>();
        for(int i  = start; i <= poolSize; i++){
            printTasks.add(new PrintTask(numberPrinter, i, end, poolSize));
        }
        return printTasks;
    }

    private static ExecutorService getThreadPool(int poolSize){
        return Executors.newFixedThreadPool(poolSize);
    }

}
