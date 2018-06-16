package chapter11;

public class Message {

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Message(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Message { " +
                "content='" + content + '\'' +
                " }";
    }
}
