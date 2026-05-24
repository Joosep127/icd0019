package reflection.serializer;

public class Post {

    private String title;

    private String text;

    private int replyCount;

    public Post() {}

    public Post(String title, String text, int replyCount) {
        this.title = title;
        this.text = text;
        this.replyCount = replyCount;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "Post{" +
                "title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", replyCount='" + replyCount + '\'' +
                '}';
    }
}
