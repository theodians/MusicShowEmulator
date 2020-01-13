package musicshow.judging;

import musicshow.contestants.Band;
import musicshow.contestants.Contestant;
import musicshow.contestants.Singer;

public class SingExpert extends Judge {
    public SingExpert(String name) {
        super(name);
    }

    @Override
    public double finalGrade (int grade, Contestant myContestant){
        double sum=0;
        if(myContestant instanceof Singer){
            sum=grade+(grade*0.4);
        }
        else if(myContestant instanceof Band){
            sum=grade+(grade*0.30);
        }
        else{
            sum=grade+(grade*0.1);
        }
        return sum;
    }
}
