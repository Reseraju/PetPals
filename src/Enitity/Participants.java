package Enitity;

public class Participants {
    private int id;
    private int eventId;
    private String participantName;
    private String participantEmail;

    // Constructor
    public Participants(int eventId, String participantName, String participantEmail) {
        this.eventId = eventId;
        this.participantName = participantName;
        this.participantEmail = participantEmail;
    }

    // Getters
    public int getEventId() { return eventId; }
    public String getParticipantName() { return participantName; }
    public String getParticipantEmail() { return participantEmail; }
}
