/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicshow;

import java.util.ArrayList;
import java.util.Collections;
import musicshow.contestants.Contestant;
import java.util.Date;
import java.util.List;
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

    public int getNum() {
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

    public int getDurationMin() {
        return durationMin;
    }

    public void setDurationMin(int durationMin) {
        this.durationMin = durationMin;
    }

    public List<Contestant> getContestants() {
        return contestants;
    }

    public void setContestants(List<Contestant> contestants) {
        this.contestants = contestants;
    }

    public void createContestantScore(Judge myJudge, Contestant myContestant,int grade,String comment){

    }
       
    
}
