/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicshow;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import musicshow.contestants.*;
import musicshow.judging.*;

import java.util.List;
import java.util.Random;

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
    private ArrayList<String> judgeComments;

    public ArrayList<String> getJudgeComments() {
        return judgeComments;
    }

    public void setJudgeComments(ArrayList<String> judgeComments) {
        this.judgeComments = judgeComments;
    }

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
    public void runEpisodes(){
        //Έναρξη επεισοδίων//

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        for(int i=0; i<episodes.size();i++){
            System.out.println("Επεισόδιο:"+episodes.get(i).getNum()+
                    ", Ημερομηνία: "+dateFormat.format(episodes.get(i).getDate())+
                    ", Διάρκεια: "+episodes.get(i).getDurationMin()+" Λεπτά");

            for(int j=0;j<episodes.get(i).getContestants().size();j++){
                episodes.get(i).getContestants().get(j).perform();

                double sum = 0;
                Random myRandomNumber = new Random();

                for(int k=0;k<judgingPanel.getJudges().size(); k++){

                    int grade = myRandomNumber.nextInt(11);
                    double finalGrade = 0;




                    System.out.println("Judge "+ judgingPanel.getJudges().get(k));

                    if (judgingPanel.getJudges().get(k) instanceof SingExpert){
                        System.out.println(" ***Sing Expert*** ");
                        finalGrade = finalGrade+judgingPanel.getJudges().get(k).finalGrade(grade,episodes.get(i).getContestants().get(j));
                        sum = sum +finalGrade;
                        System.out.println(" gives " + episodes.get(i).getContestants().get(j).getName());
                        System.out.println(" grade:" + grade + " \nFinal Grade:" + decimalFormat.format(sum));
                    }
                    if (judgingPanel.getJudges().get(k) instanceof SongWritingExpert){
                        System.out.println(" ***Song Writing Expert*** ");
                        finalGrade = finalGrade+judgingPanel.getJudges().get(k).finalGrade(grade,episodes.get(i).getContestants().get(j));
                        sum = sum +finalGrade;
                        System.out.println(" gives " + episodes.get(i).getContestants().get(j).getName());
                        System.out.println(" grade:" + grade + " \nFinal Grade:" + decimalFormat.format(sum));
                    }
                    if (judgingPanel.getJudges().get(k) instanceof InstrumentPlayingExpert){
                        System.out.println(" ***Instrument Playing Expert*** ");
                        finalGrade = finalGrade+judgingPanel.getJudges().get(k).finalGrade(grade,episodes.get(i).getContestants().get(j));
                        sum = sum +finalGrade;
                        System.out.println(" gives " + episodes.get(i).getContestants().get(j).getName());
                        System.out.println(" grade:" + grade + " \nFinal Grade:" + decimalFormat.format(sum));
                        if (getJudgingPanel().getJudges().contains("Σάκης Ρουβάς")){
                            System.out.println("Αισθάνομαι, αισθάνομαι ");
                        }
                    }
                    if (judgingPanel.getJudges().get(k) instanceof StageExpert){
                        System.out.println(" ***Stage Expert*** ");
                        finalGrade = finalGrade+judgingPanel.getJudges().get(k).finalGrade(grade,episodes.get(i).getContestants().get(j));
                        sum = sum +finalGrade;
                        System.out.println(" gives " + episodes.get(i).getContestants().get(j).getName());
                        System.out.println(" grade:" + grade + " \nFinal Grade:" + decimalFormat.format(sum));
                    }
                    if (judgingPanel.getJudges().get(k) instanceof RadioBroadcaster){
                        System.out.println(" ***Radio Broadcaster*** ");
                        finalGrade = finalGrade+judgingPanel.getJudges().get(k).finalGrade(grade,episodes.get(i).getContestants().get(j));
                        sum = sum +finalGrade;
                        System.out.println(" gives " + episodes.get(i).getContestants().get(j).getName());
                        System.out.println(" grade:" + grade + " \nFinal Grade:" + decimalFormat.format(sum));
                    }

                    episodes.get(i).createContestantScore(judgingPanel.getJudges().get(k), episodes.get(i).getContestants().get(j), grade, "Μπράβο !!!",j);

                }
                episodes.get(i).getContestants().get(j).setSum(sum);

                System.out.println("\nΣυνολική Βαθμολογία:" + decimalFormat.format(episodes.get(i).getContestants().get(j).getSum()));
                System.out.println("");
            }
            episodes.get(i).sortEpisodeFinalScores();

            System.out.println("*** O Διαγωνιζόμενος με την υψηλότερη συνολική βαθμολογία.***");
            System.out.println(episodes.get(i).findTopEpisodeContestant());

            System.out.println("*** O Διαγωνιζόμενος με την χαμηλότερη συγκεντρωτική βαθμολογία.");
            System.out.println(episodes.get(i).findLastEpisodeContestant());
            System.out.println("");

            //4.4 Θα αποκλείει τον διαγωνιζόμενο με τη μικρότερη βαθμολογία σε ένα επεισόδιο και άρα δε θα διαγωνίζεται στο επόμενο
            //      επεισόδιο. Εξαίρεση αποτελεί το πρώτο επεισόδιο μετά το πέρας του οποίου δεν αποχωρεί κανένας διαγωνιζόμενος
            if (i<9)setnewEpisode(i, episodes.get(i).getContestants());

        }

        //4.5 Με το πέρας και των 10 επεισοδίων να ανακοινώνει το νικητή της μουσικής εκπομπής καθώς και αυτόν που τερμάτισε δεύτερος.
        System.out.println("***** Νικητές Μουσικού Διαγωνισμού *****");
        System.out.println("1os Νικητής " + episodes.get(9).getContestants().get(0).getName());
        System.out.println("2os Νικητής " + episodes.get(9).getContestants().get(1).getName());
        System.out.println("");
    }

    //4.4 Mέθοδος όπου εισάγουμε τους διαγωνιζόμενους στο νέο επεισόδιο
    public void setnewEpisode(int i, List<Contestant> contestants) {

        int size=0;
        //Εξαίρεση αποτελεί το πρώτο επεισόδιο μετά το πέρας του οποίου δεν αποχωρεί κανένας διαγωνιζόμενος
        if (i==0) size = contestants.size();
            //Θα αποκλείει τον διαγωνιζόμενο με τη μικρότερη βαθμολογία σε ένα επεισόδιο -1;
        else size= contestants.size() - 1;

        //Διαπερνάμε τους διαγωνιζόμενους
        for (int j = 0; j < size; j++) {
            //Ανάλογα με τι ειδικότητα είναι δημιουργούμε νέο αντικείμενο
            Contestant c = null;
            if (contestants.get(j) instanceof Singer) {
                c = new Singer(contestants.get(j).getName());
            }
            if (contestants.get(j) instanceof InstrumentPlayer) {
                c = new InstrumentPlayer(contestants.get(j).getName());
            }
            if (contestants.get(j) instanceof SongWriter) {
                c = new SongWriter(contestants.get(j).getName());
            }
            if (contestants.get(j) instanceof Band) {
                c = new Band(contestants.get(j).getName(), contestants.get(j).getFormationDate());
            }

            // Προσθέτουμε τον διαγωνιζόμενο στο νέο επεισόδιο
            episodes.get(i + 1).addContestant(c);
        }

    }
    @Override
    public String toString() {
        return "MusicShow{" + "code=" + code + ", title=" + title + ", episodes=" + episodes + ", judgingPanel=" + judgingPanel + '}';
    }
}
