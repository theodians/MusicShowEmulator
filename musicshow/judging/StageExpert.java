package musicshow.judging;

import musicshow.contestants.Band;
import musicshow.contestants.Contestant;

public class StageExpert extends Judge {
    public StageExpert(String name) {
        super(name);
    }

    @Override
    public double finalGrade(int grade, Contestant myContestant) {
        double sum = 0;
        if(myContestant instanceof Band){
            sum=grade+(grade*0.15);
        }
        else {
            sum=grade+(grade*0.1);
        }
        return sum;
    }
}
