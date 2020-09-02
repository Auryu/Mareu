package mathieu.lahet.mareu.model;

import java.util.Objects;

/**
 * Model object representing a Meeting
 */
public class Meeting {
    /** Id */
    private long id;

    /** Meeting date */
    private String date;

    /** Meeting hour */
    private String hour;

    /** Meeting name */
    private String creatorName;

    /** Meeting subject */
    private String subject;

    /** Room */
    private String room;

    /** Mail adress */
    private String participant;

    /**
     * Constructor
     * @param id
     * @param date
     * @param hour
     * @param creatorName
     * @param subject
     * @param room
     * @param participant
     */
    public Meeting (long id, String date, String hour, String creatorName, String subject, String room, String participant) {
        this.id = id;
        this.date = date;
        this.hour = hour;
        this.creatorName = creatorName;
        this.subject = subject;
        this.room = room;
        this.participant = participant;
    }

    public Long getId(){return id;}

    public void setId(long id){this.id = id;}

    public String getDate(){return date;}

    public void setDate(String date){this.date = date;}

    public String getHour(){return hour;}

    public void setHour(String time){this.hour = hour;}

    public String getCreatorName(){return creatorName;}

    public void setCreatorName(String creatorName){this.creatorName = creatorName;}

    public String getSubject(){return subject;}

    public void setSubject(String subject){this.subject = subject;}

    public String getRoom(){return room;}

    public void setRoom(String room){this.room = room;}

    public String getParticipant(){return participant;}

    public void setParticipant(String participant){this.participant = participant;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meeting meeting = (Meeting) o;
        return Objects.equals(id, meeting.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
