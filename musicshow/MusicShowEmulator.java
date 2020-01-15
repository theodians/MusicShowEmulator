package musicshow;

import musicshow.contestants.*;
import musicshow.judging.*;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MusicShowEmulator {
    public static void main(String[] args) throws ParseException,EpisodesExceededException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");

        TvShowsCompany tvShowsCompany = new TvShowsCompany();
        tvShowsCompany.createMusicShow(1,"Τhe Βο'ί'δς");
        MusicShow musicShow = tvShowsCompany.getMusicShow(1);
        //Δημιουργία επεισοδίων //
        musicShow.createEpisode(1,dateFormat.parse("13/01/2020"),120);
        musicShow.createEpisode(2,dateFormat.parse("14/01/2020"),120);
        musicShow.createEpisode(3,dateFormat.parse("15/01/2020"),120);
        musicShow.createEpisode(4,dateFormat.parse("16/01/2020"),120);
        musicShow.createEpisode(5,dateFormat.parse("17/01/2020"),120);
        musicShow.createEpisode(6,dateFormat.parse("18/01/2020"),120);
        musicShow.createEpisode(7,dateFormat.parse("19/01/2020"),120);
        musicShow.createEpisode(8,dateFormat.parse("20/01/2020"),120);
        musicShow.createEpisode(9,dateFormat.parse("21/01/2020"),120);
        musicShow.createEpisode(10,dateFormat.parse("22/01/2020"),120);


        //Δημιουργία κριτών//
        Judge firstJudge = new SingExpert("Έλενα Παπαρίζου");
        Judge secondJudge = new InstrumentPlayingExpert("Σάκης Ρουβάς");
        Judge thirdJudge = new SongWritingExpert("Γιώργος Θεοφάνους");
        Judge fourthJudge = new RadioBroadcaster("Μιχάλης Τσαουσόπουλος");
        Judge fifthJudge = new StageExpert("Φωκάς Ευαγγελινός");

        JudgingPanel judgingPanel = new JudgingPanel(dateFormat.parse("13/01/2020)"));
        judgingPanel.addJudge(firstJudge);
        judgingPanel.addJudge(secondJudge);
        judgingPanel.addJudge(thirdJudge);
        judgingPanel.addJudge(fourthJudge);
        judgingPanel.addJudge(fifthJudge);

        musicShow.addJudgingPanel(judgingPanel);

        //Δημιουργεία διαγωνιζομένων //
        Contestant firstSinger = new Singer("Frank Sinatra");
        Contestant secondSinger = new Singer("Adele");
        Contestant thirdSinger = new Singer ("Madonna");
        Contestant firstSongWriter = new SongWriter("John Lennon");
        Contestant secondSongWriter = new SongWriter("Bob Dylan");
        Contestant firstInstrumentPlayer = new InstrumentPlayer("Gary Moore");
        Contestant secondIntrumentPlayer = new InstrumentPlayer("Keith Richards");
        Contestant firstBand = new Band("Scorpions",dateFormat.parse("01/03/1965"));//Last Concert at Nioxori//
        Contestant secondBand = new Band("The Beatles",dateFormat.parse("25/08/1956"));
        Contestant thirdBand = new Band("The Rolling Stones",dateFormat.parse("12/10/1950"));

        //Συσχέτιση με επεισόδιο 1//
        ArrayList<Contestant> myContestantList = new ArrayList<>();
        myContestantList.add(firstSinger);
        myContestantList.add(secondSinger);
        myContestantList.add(thirdSinger);
        myContestantList.add(firstSongWriter);
        myContestantList.add(secondSongWriter);
        myContestantList.add(firstInstrumentPlayer);
        myContestantList.add(secondIntrumentPlayer);
        myContestantList.add(firstBand);
        myContestantList.add(secondBand);
        myContestantList.add(thirdBand);

        musicShow.getEpisode(1).setContestants(myContestantList);

        musicShow.runEpisodes();

        ReportShow.CreateXml(musicShow);

    }

}
