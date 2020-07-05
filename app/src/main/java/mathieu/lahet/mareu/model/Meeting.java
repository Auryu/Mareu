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
    private String time;

    /** Meeting name */
    private String name;

    /** Meeting subject */
    private String subject;

    /** Room */
    private String room;

    /** Mail adress */
    private String mail;

    /**
     * Constructor
     * @param id
     * @param date
     * @param time
     * @param name
     * @param subject
     * @param room
     * @param mail
     */
    public Meeting (long id, String date, String time, String name, String subject, String room, String mail) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.name = name;
        this.subject = subject;
        this.room = room;
        this.mail = mail;
    }

    public Long getId(){return id;}

    public void setId(long id){this.id = id;}

    public String getDate(){return date;}

    public void setDate(String date){this.date = date;}

    public String getTime(){return time;}

    public void setTime(String time){this.time = time;}

    public String getName(){return name;}

    public void setName(String name){this.name = name;}

    public String getSubject(){return subject;}

    public void setSubject(String subject){this.subject = subject;}

    public String getRoom(){return room;}

    public void setRoom(String room){this.room = room;}

    public String getMail(){return mail;}

    public void setMail(String mail){this.mail = mail;}

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
