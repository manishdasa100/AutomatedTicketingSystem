package automatedTicketingSystem.components;

public class Parking {

    /**
     * Parking represents a collection/array of ParkingSlots.
     * 
     * There is a fixed number of parking slots in a Parking which should be provided at time of creating the Parking object.
     */
    
    private ParkingSlot[] parkingSlots;

    
    /**
     * Constructor
     * @param totalNumberOfParkingSlots - Total number of slots in the parking. 
     */
    public Parking(int totalNumberOfParkingSlots) {

        // Creating an array of parking slots

        this.parkingSlots = new ParkingSlot[totalNumberOfParkingSlots];
        for (int i = 0; i < totalNumberOfParkingSlots; i++) {
            parkingSlots[i] = new ParkingSlot();
        }
    }

    
    
    // Getter
    public ParkingSlot[] getParkingSlots() {
        return this.parkingSlots;
    }

}
