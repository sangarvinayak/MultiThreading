package printingthings.foobar;

import printingthings.foobar.tasks.CustomPrintTask;
import printingthings.foobar.tasks.CustomPrintTaskBuzz;
import printingthings.foobar.tasks.CustomPrintTaskFizz;
import printingthings.foobar.tasks.CustomPrintTaskFizzBuzz;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demonstration {

    private static int POOL_SIZE = 4;
    private static int TEST_START = 1;
    private static int TEST_END = 60;

    public static void main(String[] args) {
        NumberPrinter numberPrinter = new NumberPrinter(TEST_START);
        List<Runnable> printTasks = getPrintTasks(numberPrinter, TEST_START, TEST_END);
        ExecutorService threadPool = getThreadPool(POOL_SIZE);
        printTasks.forEach(threadPool::execute);
    }

    private static List<Runnable> getPrintTasks(NumberPrinter numberPrinter,
                                                          int start, int end) {
        return Arrays.asList(
            new CustomPrintTask(numberPrinter,start, end),
            new CustomPrintTaskFizz(numberPrinter,start, end),
            new CustomPrintTaskBuzz(numberPrinter,start, end),
            new CustomPrintTaskFizzBuzz(numberPrinter,start,end)
        );
    }

    private static ExecutorService getThreadPool(int poolSize){
        return Executors.newFixedThreadPool(poolSize);
    }

}
