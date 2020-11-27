package automatedTicketingSystem.components;

public class ParkingSlot {

    /**
     * ParkingSlot represents a slot where a car can be parked or is parked.
     * 
     * Each ParkigSlot is is either assigned a ParkingTicket or not assigned a ParkingTicket.
     * 
     * If no ticket is assigned to a ParkingSlot then it represents that the perticular Parking Slot is unoccupied.  
     */
    
    private boolean occupied; 

    private ParkingTicket ticket;

    /**
     * Constructor
     *
     */
    public ParkingSlot() {
        this.occupied = false;
    }

    public ParkingTicket getTicket() {
        return this.ticket;
    }

    public void setTicket(ParkingTicket ticket) {
        this.ticket = ticket;
    }

    public boolean isOccupied() {
        return this.occupied;
    }

    public void setOccupiedStatus(boolean occupiedStatus) {
        this.occupied = occupiedStatus;
        if (occupiedStatus == false) {
            setTicket(null);
        }
    }

}
