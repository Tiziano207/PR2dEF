import java.util.*;

public class SocialNetworkReportOffensivePost extends SocialNetwork implements ISocialNetworkReportOffensivePost {

    private List<String> blackList;
    private HashMap<Integer, ArrayList<String>> offensivePostSegnalation;

    public SocialNetworkReportOffensivePost() {
        blackList = new ArrayList<String>();
        offensivePostSegnalation = new HashMap<Integer, ArrayList<String>>();
    }

    // aggiunge post alla retesociale solo se il post non contiene brutte parole
    public void addPost(Post ps) throws ExplicitLanguageException {
        try {
            for (String badWord : blackList) {
                if (ps.getText().contains(badWord)) {
                    throw new ExplicitLanguageException("You can't use strong Language");
                }
            }
            super.addPost(ps.getAuthor(), ps.getText());
        } catch (ExplicitLanguageException e) {
            System.out.println(e);
        }
        return;
    }

    public void addReport(Post ps, String reporter) throws DoubleSegnalationException {

        if (!offensivePostSegnalation.containsKey(ps.getIDPost())) {
            offensivePostSegnalation.put(ps.getIDPost(), new ArrayList<String>());
            offensivePostSegnalation.get(ps.getIDPost()).add(reporter);
            System.out.println("Il Post è stato segnalato");
        } else {
            if (offensivePostSegnalation.get(ps.getIDPost()).contains(reporter)) {
                System.out.println("Non puoi segnalare due volte lo stesso post");
            } else {
                offensivePostSegnalation.get(ps.getIDPost()).add(reporter);
            }
        }
        return;
    }

    public void controlReportMap() {
        System.out.println(offensivePostSegnalation);
        for (Integer i : offensivePostSegnalation.keySet()) {
            System.out.println(offensivePostSegnalation.keySet());
            if (offensivePostSegnalation.get(i).size() >= 2) {
                offensivePostSegnalation.remove(i);
                super.getPost().remove(i);
            }
        }
    }

    public List<String> getBlackList() {
        return blackList;
    }

    public void addBlackList(String badWord) {
        if (!blackList.contains(badWord)) {
            blackList.add(badWord);
        }
        return;
    }

    public void removeWordBlacklist(String badWord) {
        blackList.remove(badWord);
    }

}