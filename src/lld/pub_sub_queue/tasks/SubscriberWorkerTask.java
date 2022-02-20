package lld.pub_sub_queue.tasks;

import lld.pub_sub_queue.models.Topic;
import lld.pub_sub_queue.models.TopicSubscriber;

public class SubscriberWorkerTask implements Runnable {

    private final Topic topic;
    private final TopicSubscriber topicSubscriber;

    public SubscriberWorkerTask(final Topic topic, final TopicSubscriber topicSubscriber) {
        this.topic = topic;
        this.topicSubscriber = topicSubscriber;
    }

    @Override
    public void run() {
        while(true){
            try {
                topicSubscriber.consumeMessage();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
