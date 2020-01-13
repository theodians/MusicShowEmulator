package musicshow.judging;

import musicshow.contestants.Band;
import musicshow.contestants.Contestant;
import musicshow.contestants.SongWriter;

public class SongWritingExpert extends Judge {
    public SongWritingExpert(String name) {
        super(name);
    }

    @Override
    public double finalGrade(int grade, Contestant myContestant) {
        double sum = 0;
        if(myContestant instanceof SongWriter){
            sum = grade+(grade*0.4);
        }
        else if(myContestant instanceof Band){
            sum = grade+(grade*0.2);
        }
        else{
            sum = grade+(grade*0.1);
        }
        return sum;
    }
}
