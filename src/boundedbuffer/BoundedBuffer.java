package boundedbuffer;


/**
 * Buffer of a given size.
 */
public class BoundedBuffer {

    private int bufferSize;
    private int bufferFilled;

    public BoundedBuffer(int bufferSize) {
        this.bufferSize = bufferSize;
        this.bufferFilled = 0;
    }

    public synchronized void produceItem() throws InterruptedException {
        while (bufferFilled == bufferSize)
            this.wait();
        bufferFilled++;
        System.out.println("Produced item to buffer: " + bufferFilled);
        this.notify();
    }

    public synchronized void consumeItem() throws InterruptedException {
        while(bufferFilled == 0)
            this.wait();
        System.out.println("Consumed item from buffer: " + bufferFilled);
        bufferFilled--;
        this.notify();
    }
}
