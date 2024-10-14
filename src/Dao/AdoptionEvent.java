package Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Util.*;

public class AdoptionEvent {
	private List<IAdoptable> participants;
	private DBPropertyUtil dbprops;
	
	public AdoptionEvent() {
        this.participants = new ArrayList<>();
        this.dbprops = new DBPropertyUtil();
    }
	
	
	public List<String> fetchUpcomingEvents() {
        List<String> events = new ArrayList<>();
        String query = "SELECT EventID, EventName, EventDate, Location FROM adoptionevents";

        try (Connection conn = dbprops.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int eventId = rs.getInt("EventID");
                String eventName = rs.getString("EventName");
                Date eventDate = rs.getDate("EventDate");
                String location = rs.getString("Location");

                // Format event details for display
                String eventDetails = eventId + ": " + eventName + " on " + eventDate + " at " + location;
                events.add(eventDetails);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching upcoming events: " + e.getMessage());
        } finally {
            dbprops.closeConnection(); 
        }
        return events;
    }
	
	
	public void hostEvent() {
		System.out.println("Hosting the adoption event!");
		
		for(IAdoptable participant : participants) {
			participant.adopt();
		}
	}
	
	public void registerParticipant(IAdoptable participant) {
        participants.add(participant);
        System.out.println("Participant registered: " + participant);
    }
	
}
