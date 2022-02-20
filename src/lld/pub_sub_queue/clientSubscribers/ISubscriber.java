package lld.pub_sub_queue.clientSubscribers;

import lld.pub_sub_queue.models.Message;

public interface ISubscriber {

    String getId();
    void consume(Message message) throws InterruptedException;
}
