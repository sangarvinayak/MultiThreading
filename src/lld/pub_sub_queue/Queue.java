package lld.pub_sub_queue;

import lld.pub_sub_queue.models.Message;
import lld.pub_sub_queue.models.Topic;
import lld.pub_sub_queue.models.TopicSubscriber;
import lld.pub_sub_queue.clientSubscribers.ISubscriber;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Queue {
    private final Map<String, Topic> topics;

    public Queue() {
        this.topics = new HashMap<>();
    }

    public Topic createTopic(final String topicName) {
        final Topic topic = new Topic(topicName, UUID.randomUUID().toString());
        topics.put(topic.getTopicId(), topic);
        System.out.println("Created topic: " + topic.getTopicName());
        return topic;
    }

    public void subscribe(final ISubscriber subscriber, final Topic topic) {
        topic.subscribe(new TopicSubscriber(subscriber, topic));
    }

    public void publish(final Topic topic, final Message message) {
        topic.publishMessage(message);
    }

    public void resetOffset(final Topic topic, final ISubscriber subscriber, final Integer newOffset) {
        for (TopicSubscriber topicSubscriber : topic.getSubscribers()) {
            if (topicSubscriber.getSubscriber().equals(subscriber)) {
                topicSubscriber.updateReadOffset(newOffset);
                break;
            }
        }
    }
}

