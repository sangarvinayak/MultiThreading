package boundedbuffer.tasks;

import boundedbuffer.BoundedBuffer;

public class ProduceTask implements Runnable {

    private BoundedBuffer boundedbuffer;
    private int maxItems;


    public ProduceTask(BoundedBuffer boundedbuffer, int maxItems) {
        this.boundedbuffer = boundedbuffer;
        this.maxItems = maxItems;
    }

    /**
     * Produce n items to the buffer.
     */
    @Override
    public void run() {
        try {
            for(int i = 0; i < maxItems; i++)
                boundedbuffer.produceItem();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
