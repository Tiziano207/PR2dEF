import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        SocialNetwork microBlog = new SocialNetwork();

        microBlog.addUser("");
        microBlog.addUser("Francesco");
        microBlog.addUser("Matteo");
        microBlog.addUser("Marco");
        microBlog.addUser("Antonio");
        microBlog.addUser("Paolo");
        microBlog.addUser("Pasquale");

        // iscrizione al social Network?
        microBlog.addPost("Franco", "Voglio pubblicare ma non sono iscritto");
        microBlog.addPost("Marco", "Daje ci");
        microBlog.addPost("Paolo", "Ce sto zi");
        microBlog.addPost("Antonio", "Ce senti Cerqua??");
        microBlog.addPost("Antonio", "Ah boh veramente");

        microBlog.addLike("Antonio", 1);
        microBlog.addLike("Antonio", 1);
        microBlog.addLike("Marco", 2);
        microBlog.addLike("Pasquale", 3);
        microBlog.addLike("Marco", 3);
        microBlog.addLike("Matteo", 3);
        microBlog.addLike("Gennaro", 3);

        ArrayList<Post> listaPost = new ArrayList<Post>();
        listaPost.add(microBlog.getPost().get(1));
        listaPost.add(microBlog.getPost().get(2));
        listaPost.add(microBlog.getPost().get(3));

        System.out.println(microBlog.getMentionedUsers());

        System.out.println("\nguessFollowers" + microBlog.guessFollowers(listaPost));

        System.out.println("influencers" + microBlog.influencers(microBlog.guessFollowers(listaPost)));

        System.out.println("mensioned users " + microBlog.getMentionedUsers());
        System.out.println("mensioned users " + microBlog.getMentionedUsers(listaPost));

        for (Post ps : microBlog.writtenBy("Antonio")) {
            System.out.println("\nwrittenbyList " + ps.getText());
        }

        for (Post ps : microBlog.writtenBy(listaPost, "Antonio")) {
            System.out.println("\nwrittenbyList " + ps.getText());
        }

        ArrayList<String> wordList = new ArrayList<String>();
        wordList.add("boh");

        for (Post ps : microBlog.containing(wordList)) {
            System.out.println("\nContaining " + ps.getText());
        }
        // _________________________________________________________________________________________________________________\\
        SocialNetworkReportOffensivePost microblogfiltered = new SocialNetworkReportOffensivePost();
        microblogfiltered.addBlackList("Bamba");
        microblogfiltered.addBlackList("Droka");

        System.out.println("\nBlakList:");
        for (String word : microblogfiltered.getBlackList()) {
            System.out.println("\n" + word);
        }
        Post postF0 = new Post(0, "Lapo Elkan", "mi voglio fare di Bianca");
        microblogfiltered.addPost(postF0);

        Post postF1 = new Post(1, "Berlusconi", "mi va proprio di drogarmi");
        microblogfiltered.addPost(postF1);

        microblogfiltered.addReport(postF0, "Marco");
        microblogfiltered.addReport(postF0, "Jhonny");
        microblogfiltered.controlReportMap();

        // Post postF2 = new Post(2, "Lapo Elkan", "mi voglio fare di Bamba");
        // microblogfiltered.addPost(postF2);
    }
}
