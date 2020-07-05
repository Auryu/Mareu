package mathieu.lahet.mareu.service;

import java.util.List;

import mathieu.lahet.mareu.model.Meeting;

/**
 * Dummy mock for the Api
 */
public class DummyMeetingApiService implements MeetingApiService{

    private List<Meeting> meetings = DummyMeetingGenerator.generateMeetings();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Meeting> getMeetings() {return meetings;}

    /**
     * {@inheritDoc}
     * @param meeting
     */
    @Override
    public void createMeeting(Meeting meeting) { meetings.add(meeting); }


    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteMeeting(Meeting meeting) { meetings.remove(meeting); }
}
