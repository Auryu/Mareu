package mathieu.lahet.mareu.service;

import java.util.List;

import mathieu.lahet.mareu.model.Meeting;

public interface MeetingApiService {

    /**
     * Get all my Meetings
     * @return {@link List}
     */
    List<Meeting> getMeetings();

    List<Meeting> getFilteredMeetingsByDate(String date);

    List<Meeting> getFilteredMeetingsByRoom(String room);

    /**
     * Create a meeting
     * @param meeting
     */
    void createMeeting(Meeting meeting);

    /**
     * Deletes a meeting
     * @param meeting
     */
    void deleteMeeting(Meeting meeting);


}
