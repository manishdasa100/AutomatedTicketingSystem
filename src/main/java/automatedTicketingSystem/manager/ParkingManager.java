package automatedTicketingSystem.manager;

public interface ParkingManager {
    
    String issueNewTicket(String vehicleRegistrationNo, int driverAge);

    String getSlotNumbersForDriverAge(int driverAge);

    String getVehicleRegistrationNumbersForDriverOfAge(int driverAge);

    String getSlotNumber(String vehicleRegistrationNo);

    String vacateSlot(int slotNumber);

}
