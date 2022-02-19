package boundedbuffer;

import boundedbuffer.tasks.ConsumeTask;
import boundedbuffer.tasks.ProduceTask;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demonstration {

    public static void main(String[] args) {
        BoundedBuffer boundedbuffer = new BoundedBuffer(5);
        ExecutorService threadPool = getThreadPool();
        List<Runnable> requiredTasks = Arrays.asList(
            new ProduceTask(boundedbuffer, 12),
            new ConsumeTask(boundedbuffer)
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
