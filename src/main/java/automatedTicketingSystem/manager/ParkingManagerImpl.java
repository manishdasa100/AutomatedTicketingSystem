package automatedTicketingSystem.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import automatedTicketingSystem.components.Parking;
import automatedTicketingSystem.components.ParkingSlot;
import automatedTicketingSystem.components.ParkingTicket;

public class ParkingManagerImpl implements ParkingManager {

    /**
     * This class is responsible for managing the implementation of all the requests intercepted by this application.
     */

    
    private Parking parking;

    /**
     * Constructor
     * @param parking 
     */    
    public ParkingManagerImpl(Parking parking) {
        this.parking = parking;
    }


    
    /**
     * This function issues a new ticket in the nearest vacant slot. After issuing a ticket it returns an appropriate msg. If the parking is full then it returns a msg that the parking is currently full. 
     */
    @Override
    public String issueNewTicket(String vehicleRegistrationNo, int driverAge) {
        
        ParkingSlot[] parkingSlots = parking.getParkingSlots();

        for (int i = 0; i < parkingSlots.length; i++) {

            if (!parkingSlots[i].isOccupied()) {

                // If slot is unoccupied

                ParkingTicket ticket = new ParkingTicket(vehicleRegistrationNo, driverAge);
                parkingSlots[i].setOccupiedStatus(true);
                parkingSlots[i].setTicket(ticket);

                return "Car with vehicle registration number \"" +vehicleRegistrationNo+ "\" has been parked at slot number " +String.valueOf(i+1);
            }
        }

        // If all the slots are already occupied
        return "Parking is currently full!!";
    }

    
    
    
    /**
     * This function accepts a driver age and returns all the slot numbers which has driver age equal to its argument.
     */
    @Override
    public String getSlotNumbersForDriverAge(int driverAge) {
        
        List<Integer> slotNumbers = new ArrayList<>();

        ParkingSlot[] parkingSlots = parking.getParkingSlots();

        for (int i = 0; i < parkingSlots.length; i++) {

            // Scanning over the array of ParkingSlots and if the slot is occupied and matches the age of diver then adding slot number to the list.
            
            ParkingSlot parkingSlot = parkingSlots[i];
            
            if (parkingSlot.isOccupied()) { 
                
                ParkingTicket ticket = parkingSlot.getTicket();

                if (ticket.getAgeOfDriver() == driverAge) {
                    slotNumbers.add(i+1);
                }
    
            }
        }

        // Formating the list content into a string seperated by comma. 
        String result = slotNumbers.stream().map(n -> String.valueOf(n)).collect(Collectors.joining(",", "", ""));

        return result;
    }

    
    
    
    
    /**
     * This function accepts a driver age and returns all the vehicle numbers matching the provided driver age.
     */
    @Override
    public String getVehicleRegistrationNumbersForDriverOfAge(int driverAge) {
        
        List<String> vehicleRegNumbers = new ArrayList<>();

        ParkingSlot[] parkingSlots = parking.getParkingSlots();

        for (int i = 0; i < parkingSlots.length; i++) {

            // Scanning over the array of ParkingSlots and if the slot is occupied then adding the vehicle registration number to the list.
            
            ParkingSlot parkingSlot = parkingSlots[i];
            
            if (parkingSlot.isOccupied()) { 
                
                ParkingTicket ticket = parkingSlot.getTicket();

                if (ticket.getAgeOfDriver() == driverAge) {
                    vehicleRegNumbers.add(parkingSlot.getTicket().getVehicleRegistrationNo());
                }
                
            }
        }

        // Formating the list content into a string seperated by comma. 
        String result = vehicleRegNumbers.stream().map(n -> String.valueOf(n)).collect(Collectors.joining(",", "", ""));

        return result;
    }

    
    
    /**
     * This function accepts a vehicle registration number as string and returns the vehicle's slot number in which it is parked. 
     * If no vehicle is found to be parked with the given registration number then it will return an error message.
     */
    @Override
    public String getSlotNumber(String vehicleRegistrationNo) {
        
        ParkingSlot[] parkingSlots = parking.getParkingSlots();

        for (int i = 0; i < parkingSlots.length; i++) {

            ParkingSlot slot = parkingSlots[i];

            if (slot.isOccupied() && slot.getTicket().getVehicleRegistrationNo().equals(vehicleRegistrationNo)) return String.valueOf(i+1);
        }

        // If no car with the given vehicle registration number is found then return -1
        return "No car with vehicle registration number " +vehicleRegistrationNo+ " found!";
    }

    
    
    /**
     * This function receives a slot number and vacates that slot if it is occupied. If the given slot number is already vacant then it returns an error message.
     */
    @Override
    public String vacateSlot(int slotNumber) {
        
        ParkingSlot slot = parking.getParkingSlots()[slotNumber-1];

        if (slot.isOccupied()) {

            // If the slot is occupied and the driver wants to vacate the slot.
            
            ParkingTicket ticket = slot.getTicket();

            slot.setOccupiedStatus(false);

            return "Slot number " +slotNumber+ " vacated, the car with vehicle registration number \"" +ticket.getVehicleRegistrationNo()+ "\" left the space, the driver of the car was of age " +ticket.getAgeOfDriver();

        }

        // If the slot is already vacant 
        return "The slot with slot number " +slotNumber+ " is already vacant";

    }
    
}
