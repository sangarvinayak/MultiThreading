package lld.pub_sub_queue.models;

public class Message {
    private final String msg;

    public Message(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
