/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicshow.contestants;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import musicshow.Episode;
import musicshow.Score;

/**
 * Αφηρημένη κλάση που αναπαριστά τον Διαγωνιζόμενο
 * @author nicolas
 */
public abstract class Contestant{
    protected String name;
    private String phone;
    private String addesss;        
    public List<Score> scores;
    private List<Episode> episodes;
    private String category;
    private Double sum;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Contestant() {
        scores = new ArrayList<>();
        episodes = new ArrayList<>();
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public Contestant(String name) {
        this();
        this.name = name;
        this.category = category;
        this.sum = sum;
    }
    
    
    
    public void addScore(Score s){
        scores.add(s);
    }
    
    public void addEpisode(Episode e){
        episodes.add(e);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddesss() {
        return addesss;
    }

    public void setAddesss(String addesss) {
        this.addesss = addesss;
    }

    public  void perform(){
        if(this instanceof  Singer){
            if(getName().contentEquals("Frank Sinatra")){
                System.out.println("The Legend!");
            }
            System.out.println("Hi I am "+this.getName()+" and i will compete in singing");
            this.setCategory("Singers");
        }
        if (this instanceof SongWriter){
            System.out.println("Hi I am "+this.getName()+" and i compose");
            this.setCategory("Songwriters");
        }
        if(this instanceof InstrumentPlayer){
            System.out.println("Hi I am "+this.getName()+" and i play guitar");
            this.setCategory("Instrument Players");
        }
        if(this instanceof Band){
            if (getName().contentEquals("Scorpions")){
                System.out.println("******For a Final Show At Mamoulada...Οι Σκορπιοοοονς******");
                System.out.println(" ");
            }
            if (getName().contentEquals("Rolling Stones")){
                System.out.println("******We are searching for satisfaction!!!******");
                System.out.println(" ");
            }
            if (getName().contentEquals("The Beatles")){
                System.out.println("******We have a yellow submarine!!!******");
                System.out.println(" ");
            }
            System.out.println("Hi we are the Legendary "+this.getName()+" and we Rock!!!");
            this.setCategory("Bands");
        }

    }



    private static DecimalFormat decimalFormat = new DecimalFormat("#.##");

    @Override
    public String toString() {
        return name + " Σύνολο:" + decimalFormat.format(sum);
    }

    public abstract Date getFormationDate();
}
