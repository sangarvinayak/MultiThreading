package lld.pub_sub_queue.models;

import lld.pub_sub_queue.tasks.SubscriberWorkerTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Topic {
    private final String topicName;
    private final String topicId;
    private final List<Message> messages; // TODO: Change getter this to send only immutable list outside.
    private final List<TopicSubscriber> subscribers; // TODO: Change getter this to send only immutable list outside.
    private final Map<String, Thread> subscriberWorkerThreads;

    public Topic(final String topicName, final String topicId) {
        this.topicName = topicName;
        this.topicId = topicId;
        this.messages = new ArrayList<>();
        this.subscribers = new ArrayList<>();
        this.subscriberWorkerThreads = new HashMap<>();
    }

    public List<Message> getMessages() {
        return messages;
    }

    public String getTopicName() {
        return topicName;
    }

    public List<TopicSubscriber> getSubscribers() {
        return subscribers;
    }

    public String getTopicId() {
        return topicId;
    }

    public synchronized void publishMessage(final Message message) {
        messages.add(message);
        System.out.println(message.getMsg() + " published to topic: " + this.topicName);
        for (TopicSubscriber topicSubscriber:this.subscribers)
            topicSubscriber.alarmMessageIsPublished();
    }

    public synchronized void subscribe(final TopicSubscriber subscriber) {
        subscribers.add(subscriber);
        final SubscriberWorkerTask subscriberWorkerTask = new SubscriberWorkerTask(this, subscriber);
        Thread subscriberThread = new Thread(subscriberWorkerTask);
        subscriberWorkerThreads.put(subscriber.getSubscriber().getId(), subscriberThread);
        subscriberThread.start();
    }



}
