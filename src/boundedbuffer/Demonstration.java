package boundedbuffer;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demonstration {

    public static void main(String[] args) {
        // SOME REQUIRED LOGIC //
        ExecutorService threadPool = getThreadPool();
        List<Runnable> requiredTasks = Arrays.asList(

        );
        requiredTasks.forEach(threadPool::execute);
    }

    static ExecutorService getThreadPool(){
        int poolBuffer = 0;
        int coreCount = Runtime.getRuntime().availableProcessors();
        ExecutorService threadPool = Executors.newFixedThreadPool(coreCount + poolBuffer);
        return threadPool;
    }

}
