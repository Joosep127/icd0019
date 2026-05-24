package reflection.serializer;

public class Main {

    public static void main(String[] args) {
        Post post = new Post("Post 1", "Post text", 4);

        System.out.println(post);

        String postAsString = new Serializer().serialize(post);

        System.out.println(postAsString);

        Post restored = new Serializer()
                .deserialize(postAsString, Post.class);

        System.out.println(restored);
    }
}
