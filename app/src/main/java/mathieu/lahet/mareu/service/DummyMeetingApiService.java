package mathieu.lahet.mareu.service;

import java.util.ArrayList;
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

    @Override
    public List<Meeting> getFilteredMeetingsByDate(String date) {
        List<Meeting> dateFiltered = new ArrayList<>();
        for (Meeting meeting : meetings){
            if (meeting.getDate().equals(date)){
                dateFiltered.add(meeting);
            }
        }
        return dateFiltered;
    }

    @Override
    public List<Meeting> getFilteredMeetingsByRoom(String room) {
        List<Meeting> roomFiltered = new ArrayList<>();
        for (Meeting meeting : meetings){
            if (meeting.getRoom().equals(room)){
                roomFiltered.add(meeting);
            }
        }
        return roomFiltered;
    }

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
