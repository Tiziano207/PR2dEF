import java.util.*;

public interface ISocialNetworkReportOffensivePost extends ISocialNetwork {
    public void addPost(Post ps) throws ExplicitLanguageException;

    void addReport(Post ps, String reporter) throws DoubleSegnalationException;

    void controlReportMap();

    List<String> getBlackList();

    void addBlackList(String badWord);

    void removeWordBlacklist(String badWord);

}
