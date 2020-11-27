package automatedTicketingSystem.components;

public class ParkingTicket {
    
    /**
     * A parking ticket contains information about 
     *   - Registration number of the car parked.
     *   - Age of the driver of the vehicle.
     */
    private String vehicleRegistrationNo;

    private int ageOfDriver;

    /**
     * Constructor
     */
    public ParkingTicket() {}


    /**
     * Constructor
     * @param vehicleRegistrationNo
     * @param ageOfDriver
     */
	public ParkingTicket(String vehicleRegistrationNo, int ageOfDriver) {
		this.vehicleRegistrationNo = vehicleRegistrationNo;
		this.ageOfDriver = ageOfDriver;
	}

    public String getVehicleRegistrationNo() {
        return vehicleRegistrationNo;
    }

    public void setVehicleRegistrationNo(String vehicleRegistrationNo) {
        this.vehicleRegistrationNo = vehicleRegistrationNo;
    }

    public int getAgeOfDriver() {
        return ageOfDriver;
    }

    public void setAgeOfDriver(int ageOfDriver) {
        this.ageOfDriver = ageOfDriver;
    }

}
