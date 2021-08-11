import java.util.*;

public interface ISocialNetwork {
    /*
     * OVERVIEW: un Social Network è un oggetto immutable contenente una HashMap
     * contenente una Stringa che farà riferimento all'utente e un Array di stringhe
     * che faranno riferimento a quali persone l'utente segue. Contiene anche un
     * HashMap che come chiave ha un intero (ricavato dall'IDPost) e come valore
     * associato alla chiave avrà un Post.
     * 
     * 
     * TYPICAL ELEMENT: <HashMap<String, ArrayList<String>>,HashMap<Integer,Post>>
     */

    /*
     * EFFECTS: restituisce un HashMap strutturata come String Username e Array di
     * Stringhe contenenti coloro che seguono quella persona
     */
    HashMap<String, ArrayList<String>> guessFollowers(List<Post> ps);

    /*
     * EFFECTS: restituisce una Lista con le persone più influenti della
     * retesociale, le persone che hanno più "like" ai Post
     */
    List<String> influencers(HashMap<String, ArrayList<String>> followers);

    /*
     * EFFECTS: restituisce un Set di Stringhe contenente tutte le persone che hanno
     * postato almeno una volta nella rete sociale
     */
    Set<String> getMentionedUsers();

    /*
     * EFFECTS: restituisce un Set di Stringhe contenente tutte le persone che hanno
     * postato almeno una volta trovati grazie al parametro List<Post> ps passato in
     * input
     */
    Set<String> getMentionedUsers(List<Post> ps);

    /*
     * EFFECTS: restituisce una lista di Oggetti Post scritti dalla persona
     * identificata con l'Username all'interno della rete sociale, il nome della
     * persona di cui si vogliono i post viene passato in input come Stringa
     */
    List<Post> writtenBy(String username);

    /*
     * EFFECTS: restituisce una lista di Oggetti Post scritti dalla persona
     * identificata con l'Username all'interno della lista di Post passata come
     * input al metodo, il nome della persona di cui si vogliono i post viene
     * passato in input come Stringa
     */
    List<Post> writtenBy(List<Post> ps, String username);

    /*
     * EFFECTS: restituisce una lista di tipo di dato Post i quali contengono
     * all'interno della String Post.text almeno una parola all'intenro della lista
     * passata in input
     */
    List<Post> containing(List<String> words);

    /*
     * EFFECTS: aggiunge un Username nella rete sociale esso non può essere uguale
     * ad altri Username della rete sociale e non può essere nullo
     */
    void addUser(String name) throws UserCannotBeEqualException, EmptyUsernameException;

    /*
     * EFFECTS:aggiunge un post alla rete sociale
     */
    void addPost(String author, String text)
            throws ExplicitLanguageException, TextTooLongException, AuthorORTextCannotBeNullException;

    /*
     * EFFECTS: restituisce l'HasMap della rete sociale definita da Integer IDPost
     * come chiave e Post come valore mappato dalla chiave
     */
    HashMap<Integer, Post> getPost();
}
