package mathieu.lahet.mareu.service;

import mathieu.lahet.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class DummyMeetingGenerator {

    public static List<Meeting> DUMMY_MEETINGS = Collections.emptyList();

    static List<Meeting> generateMeetings(){return new ArrayList<>(DUMMY_MEETINGS);}
}
