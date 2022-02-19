package boundedbuffer.tasks;

import boundedbuffer.BoundedBuffer;

public class ConsumeTask implements Runnable {

    private BoundedBuffer boundedbuffer;

    public ConsumeTask(BoundedBuffer boundedbuffer) {
        this.boundedbuffer = boundedbuffer;
    }

    /**
     * Consume an item from the buffer while you can.
     */
    @Override
    public void run() {
        try {
            while(true)
                boundedbuffer.consumeItem();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
