package tockenbucket;

import tockenbucket.tasks.TokenAddTask;
import tockenbucket.tasks.TokenConsumeTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * CAN BE UTILIZED IN RATE LIMITER implementation.
 */
public class Demonstration {

    public static void main(String[] args) {
        TokenBucket tokenBucket = new TokenBucket(5, 10);
        ExecutorService threadPool = getThreadPool();
        threadPool.execute(new TokenAddTask(tokenBucket));
        getConsumeTokenTasks(3, tokenBucket).forEach(threadPool::execute);
    }

    private static List<TokenConsumeTask> getConsumeTokenTasks(int numOfTasks,
                                                       TokenBucket tokenBucket) {
        List<TokenConsumeTask> consumeTasks = new ArrayList<TokenConsumeTask>();
        for(int i = 0; i < numOfTasks; i++)
            consumeTasks.add(new TokenConsumeTask(tokenBucket));
        return consumeTasks;
    }

    private static ExecutorService getThreadPool(){
        int poolBuffer = 10;
        int coreCount = Runtime.getRuntime().availableProcessors();
        ExecutorService threadPool = Executors.newFixedThreadPool(coreCount + poolBuffer);
        return threadPool;
    }

}
