package automatedTicketingSystem.handlers;

public interface RequestHandler {

    final String CREATE_NEW_PARKING_LOT = "Create_parking_lot";
    final String PARK_CAR = "Park";
    final String GET_SLOT_NUMBERS_FOR_DRIVER_AGE = "Slot_numbers_for_driver_of_age";
    final String GET_SLOT_NUMBER_FOR_CAR_WITH_NO = "Slot_number_for_car_with_number";
    final String VACATE_SLOT = "Leave";
    final String GET_CAR_NUMBERS_FOR_DRIVER_WITH_AGE = "Vehicle_registration_number_for_driver_of_age";
    final String REQUEST_NOT_FOUND = "The request \"%s\" is currently not available!";

    String processRequest(String request);
    
}
