package com.SocialNetwork.Interfaces;

import java.sql.Timestamp;
import java.util.*;;

public interface IPost {
    /*
     * OVERVIEW: un Post è un oggetto immutable contenente un id univoco ∈ N, un
     * autore, un testo di massimo 140 caratteri, un timestamp che rappresenta data
     * e ora della pubblicazione e una lista di Stringhe che rappresenta le persone
     * che hanno messo "like" al post
     * 
     * TYPICAL ELEMENT: <id,author,text,timestamp> dove author,text sono scritti
     * dall utente e passati come parametro al costruttore, id e timestamp sono
     * generati automaticamente
     */

    /*
     * EFFECTS: restituisce l'id univoco del post
     */
    int getIDPost();

    /*
     * EFFECTS: restituisce l'autore del post
     */
    String getAuthor();

    /*
     * EFFECTS: restituisce il testo del post
     */
    String getText();

    /*
     * EFFECTS: restituisce il timestamp del post
     */
    Timestamp getTimeStamp();

    /*
     * EFFECTS: restituisce la lista delle persone cha hanno messo like al post
     */
    ArrayList<String> getFollowers();

    /*
     * EFFECTS: aggiunge la stringa dell'utente che ha messo like nella lista
     */
    void addLike(String like) throws UserCannotSelfLikeException, UserCannotPutLikeTwiceException;

}
