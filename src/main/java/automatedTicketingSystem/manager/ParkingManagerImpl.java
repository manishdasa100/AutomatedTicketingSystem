package automatedTicketingSystem.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import automatedTicketingSystem.components.Parking;
import automatedTicketingSystem.components.ParkingSlot;
import automatedTicketingSystem.components.ParkingTicket;

public class ParkingManagerImpl implements ParkingManager {

    private Parking parking;

    public ParkingManagerImpl(Parking parking) {
        this.parking = parking;
    }

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

        String result = slotNumbers.stream().map(n -> String.valueOf(n)).collect(Collectors.joining(",", "", ""));

        return result;
    }

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

        String result = vehicleRegNumbers.stream().map(n -> String.valueOf(n)).collect(Collectors.joining(",", "", ""));

        return result;
    }

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
