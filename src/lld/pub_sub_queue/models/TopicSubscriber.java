package lld.pub_sub_queue.models;

import lld.pub_sub_queue.clientSubscribers.ISubscriber;

import java.util.concurrent.atomic.AtomicInteger;

public class TopicSubscriber {
    private final Topic topic;
    private AtomicInteger readOffset;
    private final ISubscriber subscriber;

    public TopicSubscriber(final ISubscriber subscriber, final Topic topic) {
        this.topic = topic;
        this.subscriber = subscriber;
        this.readOffset = new AtomicInteger(0);
    }

    public ISubscriber getSubscriber() {
        return subscriber;
    }

    /**
     * Inter thread communication:
     *      synchronisation b/w consumeMessage & alarmMessageIsPublished
     *      synchronisation b/w consumeMessage & updateReadOffset
     *
     * Data Synchronisation: readOffset
     *      synchronisation b/w consumeMessage & updateReadOffset
     */


    public synchronized void consumeMessage() throws InterruptedException {
        while(this.readOffset.get() >= topic.getMessages().size())
            this.wait();
        this.subscriber.consume(topic.getMessages().get(this.readOffset.get()));
        this.readOffset.set(this.readOffset.get()+1);
    }

    public synchronized void alarmMessageIsPublished() {
        this.notifyAll();
    }

    public synchronized void updateReadOffset(Integer newOffset) {
        this.readOffset = new AtomicInteger(newOffset);
        this.notifyAll();
    }
}

