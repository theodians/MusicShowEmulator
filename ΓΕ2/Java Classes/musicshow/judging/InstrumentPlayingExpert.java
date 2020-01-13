package musicshow.judging;

import musicshow.contestants.Band;
import musicshow.contestants.Contestant;
import musicshow.contestants.InstrumentPlayer;

public class InstrumentPlayingExpert extends Judge {
    public InstrumentPlayingExpert(String name) {
        super(name);
    }

    @Override
    public double finalGrade(int grade, Contestant myContestant) {
        double sum=0;
        if(myContestant instanceof InstrumentPlayer){
            sum = grade+ (grade*0.4);
        }
        else if(myContestant instanceof Band){
            sum = grade+(grade*0.25);
        }
        else{
            sum = grade+(grade*0.1);
        }
        return sum;
    }
}
