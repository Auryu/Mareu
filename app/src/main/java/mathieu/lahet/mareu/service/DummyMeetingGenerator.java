package mathieu.lahet.mareu.service;

import mathieu.lahet.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyMeetingGenerator {

    public static List<Meeting> DUMMY_MEETINGS = Arrays.asList(
            new Meeting(1, "", "","","","",""),
            new Meeting(2, "", "","","","",""),
            new Meeting(3, "", "","","","",""),
            new Meeting(4, "", "","","","",""),
            new Meeting(5, "", "","","","",""),
            new Meeting(6, "", "","","","",""),
            new Meeting(7, "", "","","","",""),
            new Meeting(8, "", "","","","",""),
            new Meeting(9, "", "","","","",""),
            new Meeting(10, "", "","","","","")
    );

    static List<Meeting> generateMeetings(){return new ArrayList<>(DUMMY_MEETINGS);}
}
