/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicshow;

import java.util.*;

import musicshow.contestants.Contestant;
import musicshow.judging.Judge;

/**
 * Αναπαριστά ένα Επεισόδιο Εκπομπής
 * @author nicolas
 */
public class Episode {
    // Αριθμός επεισοδίου
    private int num;
    // Ημερομηνία προβολής
    private Date date;
    // Διάρκεια
    private int durationMin;
    // Λίστα διαγωνιζομένων. Υλοποιεί τη συσχέτιση "διαγωνίζονται" με την κλάση Διαγωνιζόμενος
    private List<Contestant> contestants;
    // Λίστα βαθμολογιών. Υλοποιεί τη συσχέτιση "αφορά σε παράσταση" με την κλάση Βαθμολογία
    private final List<Score> scores;


    /**
     * O προκαθορισμένος δημιουργός ο οποίος δημιουργεί τις λίστες διαγωνιζομένων και βαθμολογιών.
     */
    public Episode() {
        scores = new  ArrayList<>();
        contestants = new ArrayList<>();
    }

    /**
     * Δημιουργός της κλάσης με παραμέτρους. Αρχικά καλεί τον προκαθορισμένο δημιουργό και στη συνέχεια αναθέτει το δοθέν αριθμό
     * επεισοδίου, ημερομηνία και διάρκεια
     * @param num Ο αριθμός επεισοδίου
     * @param date Η ημερομηνία προβολής
     * @param durationMin Η διάρκεια σε λεπτά
     */
    public Episode(int num, Date date, int durationMin) {
        this();
        this.num = num;
        this.date = date;
        this.durationMin = durationMin;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    /**
     * Προσθέτει ένα διαγωνιζόμενο στο επεισόδιο
     * @param c
     */
    public void addContestant(Contestant c){
        contestants.add(c);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getDurationMin() {
        return durationMin;
    }

    public void setDurationMin(int durationMin) {
        this.durationMin = durationMin;
    }

    public List<Score> getScores() {
        return scores;
    }

    public List<Contestant> getContestants() {
        return contestants;
    }

    public void setContestants(List<Contestant> contestants) {
        this.contestants = contestants;
    }

    public void createContestantScore(Judge myJudge, Contestant myContestant,int grade,String comment,int i){
        Score score = new Score(grade,comment, myContestant,this,myJudge);
        this.getContestants().get(i).scores.add(score);
    }
    public void sortEpisodeFinalScores() {

        //Ταξινόμηση διαγωνιζόμενων κατά αύξουσα βαθμολογική σειρά
        this.contestants.sort(Comparator.comparing(Contestant::getSum).reversed());

        System.out.println("*** Εκτύπωση Διαγωνιζόμενων Κατά Αύξουσα Βαθμολογία ***");
        for (int i = 0; i < contestants.size(); i++) {
            System.out.println(contestants.get(i));
        }
        System.out.println("");
    }

    public Contestant findLastEpisodeContestant() {
        //Εύρεση του Min
        Comparator<Contestant> minContestant = Comparator.comparing(Contestant::getSum);
        Contestant myContestant = contestants.stream().min(minContestant).get();
        return myContestant;
    }

    public Contestant findTopEpisodeContestant() {
        //Εύρεση του Min σε Λίστα Αντικειμένων με βάση τιμή
        Comparator<Contestant> maxContestant = Comparator.comparing(Contestant::getSum);
        Contestant myContestant = contestants.stream().max(maxContestant).get();
        return myContestant;
    }

    @Override
    public String toString() {
        return "Episode{" + "num=" + num + ", date=" + date + ", durationMin=" + durationMin + ", contestants=" + contestants + ", scores=" + scores + '}';
    }

}
