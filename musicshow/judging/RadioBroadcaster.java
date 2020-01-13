package musicshow.judging;

import musicshow.contestants.*;

public class RadioBroadcaster extends Judge {
    public RadioBroadcaster(String name) {
        super(name);
    }

    @Override
    public double finalGrade(int grade, Contestant myContestant) {
        double sum = 0;
        if(myContestant instanceof Singer || myContestant instanceof InstrumentPlayer || myContestant instanceof SongWriter){
            sum = grade+(grade*0.3);
        }
        else if(myContestant instanceof Band){
            sum = grade + (grade*0.15);
        }
        return sum;
    }
}
