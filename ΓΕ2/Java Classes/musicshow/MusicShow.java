/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicshow;

import java.util.ArrayList;
import java.util.Date;
import musicshow.judging.JudgingPanel;
import java.util.List;
import musicshow.contestants.Contestant;
import musicshow.judging.Judge;

/**
 * Αναπαριστά την Εκπομπή
 * @author nicolas
 */
public class MusicShow {
    // Το όριο επεισοδίων για μια εκπομπή
    public static final int EPISODES_LIMIT = 10;
    // Ο κωδικός της εκπομπής
    private int code;
    // Ο τίτλος της εκπομπής
    private String title;
    // Η λίστα επεισοδίων που περιλαμβάνει η εκπομπή. Υλοποιεί τη συσχέτιση "περιλαμβάνει" με την κλάση Επεισόδιο 
    private List<Episode> episodes;
    // Η κριτική επιτροπή που συμμετέχει στην εκπομπή. Υλοποιεί τη συσχέτιση "συμμετέχει" με την κλάση Κριτική Επιτροπή
    private JudgingPanel judgingPanel;

    /**
     * O προκαθορισμένος δημιουργός ο οποίος δημιουργεί τη λίστα επεισοδίων.
     */
    public MusicShow() {
        episodes = new ArrayList<>();
    }
        
    /**
     * Δημιουργός της κλάσης με παραμέτρους. Αρχικά καλεί τον προκαθορισμένο δημιουργό και στη συνέχεια αναθέτει το δοθέν κωδικό και τίτλο
     * @param code Ο κωδικός της Εκπομπής
     * @param title  Ο τίτλος της Εκπομπής
     */
    public MusicShow(int code, String title) {
        this();
        this.code = code;
        this.title = title;        
    }
    
    /**
     * Δημιουργεί ένα Επεισόδιο με τον δοθέν αριθμό επεισοδίου, ημερομηνία και διάρκεια και το προθέτει στη λίστα επεισοδίων της εκπομπής. Επί της
     * ουσίας υλοποιεί το composition μεταξύ των 2 κλάσεων καθώς με τον τρόπο αυτό τα επεισόδια δημιουργούνται μόνο μέσω
     * της κλάσης της Εκπομπής και δε μπορούν να υπάρχουν αυτόνομα. Αν καταστραφεί το object της εκπομπής θα καταστραφούν
     * και τα επεισόδια που της ανήκουν. Επιπλέον υλοποιεί την πολλαπλότητα 1:10 του διαγράμματος κλάσεων καθώς απαγορεύει τη δημιουργία
     * περισσότερων από 10 επεισοδίων
     * @param num Ο αριθμός του επεισοδίου
     * @param date Η ημερομηνία που θα προβληθεί το επεισόδιο
     * @param durationMin Η διάρκεια του επεισοδίου
     * @throws EpisodesExceededException Δημιουργείται σε περίπτωση που ο αριθμός επεισοδίων υπερβεί το μέγιστο επιτρεπόμενο
     */
    public void createEpisode(int num, Date date, int durationMin) throws EpisodesExceededException{ 
        if (episodes.size()<EPISODES_LIMIT)
            episodes.add(new Episode(num, date, durationMin));
        else throw new EpisodesExceededException("Υπέρβαση αριθμού επεισοδίων για τη συγκεκριμένη εκπομπή!");
    }
    
    /**
     * Επιστρέφει το επεισόδιο που αντιστοιχεί στον δοθέν αριθμό
     * @param num Ο αριθμός του επεισοδίου που ζητείται
     * @return  Το επεισόδιο που αντιστοιχεί στον δοθέν αριθμό
     */
    public Episode getEpisode(int num){
        for (Episode e:episodes)
            if (e.getNum() == num)
                return e;
        return null;
    }
    
    /**
     * Αναθέτει τη δοθείσα κριτική επιτροπή στην Εκπομπή
     * @param p Η κριτική επιτροπή
     */
    public void addJudgingPanel(JudgingPanel p){
        judgingPanel = p;
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
    }
    
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
    

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public JudgingPanel getJudgingPanel() {
        return judgingPanel;
    }

    public void setJudgingPanel(JudgingPanel judgingPanel) {
        this.judgingPanel = judgingPanel;
    }
}
