
import java.util.*;
import java.util.Map.Entry;

public class SocialNetwork implements ISocialNetwork {
    /**
     * OVERVIEW: Tipo di dato modificabile che rappresenta una retesociale
     * determinata come un HashMap e un HashMap che invece contiene i Post
     * appartenenti alla retescoiale in particolare questi Post sono identificati
     * univocamente da un IDPost
     * 
     * TYPICAL ELEMENT: <retesociale, postMap>
     * 
     * con: retesociale = NomeUtente=[UtenteSeguito1,..., UtenteSeguitoN]
     * 
     * con: postMap = IDPost = [Post]
     */

    private HashMap<String, ArrayList<String>> retesociale;
    private HashMap<Integer, Post> postMap;
    int idPostCount = 1;

    /**
     * @EFFECTS crea un nuovo SocialNetwork inizializzando le HashMap retesociale e
     *          postMap
     */
    public SocialNetwork() {
        retesociale = new HashMap<String, ArrayList<String>>();
        postMap = new HashMap<Integer, Post>();
    }

    /**
     * @throws PostListNullException
     * @REQUIRES ps != null
     * @THROWS PostListNullException (Unchecked) se ps == null
     * @EFFECTS Restituisce un HashaMap che ha come chiave i nomi degli autori dei
     *          post senza ripetizioni e come valori i nomi delle persone che
     *          seguono i post dell'autore del post
     */
    public HashMap<String, ArrayList<String>> guessFollowers(List<Post> ps) {

        if (ps == null)
            throw new NullPointerException();

        HashMap<String, ArrayList<String>> socialDerivateFromPost = new HashMap<String, ArrayList<String>>();
        for (Post post : ps) {
            if (socialDerivateFromPost.containsKey(post.getAuthor())) {
                ArrayList<String> stringTemp = socialDerivateFromPost.get(post.getAuthor());
                for (String s : post.getFollowers()) {
                    if (!stringTemp.contains(s))
                        stringTemp.add(s);
                }
                socialDerivateFromPost.get(post.getAuthor()).addAll(stringTemp);
            } else {
                // inizio lista vuota quindi nessun duplicato
                socialDerivateFromPost.put(post.getAuthor(), new ArrayList<String>());
                socialDerivateFromPost.get(post.getAuthor()).addAll(post.getFollowers());
            }
        }

        return socialDerivateFromPost;
    }

    /**
     * @REQUIRES followers != null
     * @EFFECTS Restituisce una Lista di Stringhe contenente le persone della rete
     *          sociale in ordine di Influenza, ovvero ordinati in modo tale che le
     *          persone che hanno più followers sono in testa e quelle meno
     *          influenti sono in coda alla Lista
     * @THROWS NullPointerException (Unchecked) if followers == null
     * 
     */

    public List<String> influencers(HashMap<String, ArrayList<String>> followers) {

        HashMap<String, Integer> influencersMap = new HashMap<String, Integer>();
        // creo hashmap con influencer e numero di followers
        for (String influencer : followers.keySet()) {
            influencersMap.put(influencer, followers.get(influencer).size());
        }

        Set<Entry<String, Integer>> entrySet = influencersMap.entrySet();
        List<Entry<String, Integer>> sortedInfluencersMap = new ArrayList<>(entrySet);
        // faccio il sort delle key per valore, quindi mettendo le persone con il
        // maggior numero di follower alla fine delle chiavi
        sortedInfluencersMap.sort(Map.Entry.comparingByValue());

        List<String> sortedInfluencersList = new ArrayList<String>();

        // creo una lista di appoggio per andare a mettere le chiavi in ordine crescente
        // di folllowers quindi scorro le chiavi al contrario e aggiungo i valori alla
        // mia lista per avere in ordine decrescente gli utenti per numero di followers
        for (Integer i = sortedInfluencersMap.size() - 1; i >= 0; i--) {
            sortedInfluencersList.add(sortedInfluencersMap.get(i).getKey());
        }

        return sortedInfluencersList;
    }

    /**
     * @EFFECTS Restitutisce gli utenti attualmente presenenti nella retesociale,
     *          restituendo il Set di chiavi della HashMap retesociale
     */
    public Set<String> getMentionedUsers() {
        return retesociale.keySet();
    }

    /**
     * @EFFECTS Restitutisce gli utenti attualmente presenenti nella retesociale,
     *          restituendo il Set di Stringhe contenente tutti gli aautori dei post
     *          senza ripetizioni
     * @THROWS NullPointerException (Unchecked) if ps == null
     */
    public Set<String> getMentionedUsers(List<Post> ps) {
        Set<String> mentionedUsersFromPost = new HashSet<String>();

        for (Post post : ps) {
            if (!mentionedUsersFromPost.contains(post.getAuthor()))
                mentionedUsersFromPost.add(post.getAuthor());
        }
        return mentionedUsersFromPost;
    }

    /**
     * @EFFECTS Restitutisce una lista di Post contenente tutti i Post scritti da
     *          quella persona senza ripetizioni all'interno della Lista
     */
    public List<Post> writtenBy(String username) {
        List<Post> postFromAuthor = new ArrayList<Post>();
        for (Post post : postMap.values()) {
            if (post.getAuthor().equals(username))
                postFromAuthor.add(post);
        }

        return postFromAuthor;
    }

    /**
     * @REQUIRES ps != null
     * @EFFECTS Restitutisce una lista di Post contenente tutti i Post scritti da
     *          quella persona senza ripetizioni all'interno della Lista. La lista
     *          di Post che viene restituita viene derivata da una lista di Post
     *          data in input al metodo
     * @THROWS NullPointerException (Unchecked) if ps == null
     */

    public List<Post> writtenBy(List<Post> ps, String username) {
        List<Post> postFromAuthor = new ArrayList<Post>();
        for (Post post : ps) {
            if (post.getAuthor().equals(username))
                postFromAuthor.add(post);
        }
        return postFromAuthor;
    }

    public List<Post> containing(List<String> words) {
        List<Post> postWwords = new ArrayList<Post>();
        for (Post post : postMap.values()) {
            for (String word : words)
                if (post.getText().contains(word) && !postWwords.contains(post))
                    postWwords.add(post);
        }
        return postWwords;
    }

    // una persona può far parte del Social anche senza aver postato
    public void addUser(String name) {
        try {
            if (name.equals("") || name.equals(null))
                throw new EmptyUsernameException("Username can't be null");
            // solo in questo caso viene aggiunto
            if (!retesociale.containsKey(name))
                retesociale.put(name, new ArrayList<String>());
            else
                throw new UserCannotBeEqualException("User can't have same Nickname");
        } catch (EmptyUsernameException e) {
            System.out.println(e);
        } catch (UserCannotBeEqualException e) {
            System.out.println(e);
        }
        return;

    }

    public void addPost(String author, String text) {
        try {
            if (author.equals("") || author.equals(null) || text.equals("") || text.equals(null))
                throw new AuthorORTextCannotBeNullException("Author and Text cannot be null");
            if (!retesociale.keySet().contains(author))
                throw new MustBeRegisterToPublishException("You must register on Social to post");
            Post psNew = new Post(idPostCount, author, text);
            postMap.put(idPostCount, psNew);
            idPostCount++;
        } catch (TextTooLongException e) {
            System.out.println(e);
        } catch (AuthorORTextCannotBeNullException e) {
            System.out.println(e);
        } catch (MustBeRegisterToPublishException e) {
            System.out.println(e);
        }
        return;
    }

    public HashMap<Integer, Post> getPost() {
        return postMap;
    }

    public void addLike(String follower, int IDPost) {
        try {

            if (!retesociale.keySet().contains(follower))
                throw new MustBeRegisterToAddLikeException("You must be registered to add like");
            postMap.get(IDPost).addLike(follower);
        } catch (UserCannotSelfLikeException e) {
            System.out.println(e);
        } catch (UserCannotPutLikeTwiceException e) {
            System.out.println(e);
        } catch (MustBeRegisterToAddLikeException e) {
            System.out.println(e);
        }
        return;
    }
}