package automatedTicketingSystem;

import java.util.Scanner;

import automatedTicketingSystem.components.Parking;
import automatedTicketingSystem.handlers.RequestHandler;
import automatedTicketingSystem.manager.ParkingManager;
import automatedTicketingSystem.manager.ParkingManagerImpl;
import automatedTicketingSystem.utils.Helper;

public class AutomatedTicketingSystem implements RequestHandler{

    
    private Parking parking;

    private ParkingManager parkingManager;

    
    /**
     * processRequest function 
     *  - accepts an incomming request as string.
     *  - splits the request into two parts -> command part and arguments part.
     *  - checks the command and invokes approprite method of the parkingManger.
     *  - gets a response back from parkingManager and returns the response.
     */
    @Override
    public String processRequest(String request) {

        String[] requestParts = request.split(" ", 2);

        String command = requestParts[0];

        String[] args = requestParts[1].split(" ");

        String result = "";

        if (command.equals(RequestHandler.CREATE_NEW_PARKING_LOT)) {
            
            parking = new Parking(Integer.parseInt(args[0]));
            parkingManager = new ParkingManagerImpl(parking);

            result = "Created parking of " +args[0]+ " slots";
        
        } else if (command.equals(RequestHandler.PARK_CAR)) {
            
            result = parkingManager.issueNewTicket(args[0], Integer.parseInt(args[2]));
        
        } else if (command.equals(RequestHandler.GET_SLOT_NUMBERS_FOR_DRIVER_AGE)) {

            result = parkingManager.getSlotNumbersForDriverAge(Integer.parseInt(args[0]));

        } else if (command.equals(RequestHandler.GET_SLOT_NUMBER_FOR_CAR_WITH_NO)) {

            result = parkingManager.getSlotNumber(args[0]);

        } else if (command.equals(RequestHandler.GET_CAR_NUMBERS_FOR_DRIVER_WITH_AGE)) {
            
            result = parkingManager.getVehicleRegistrationNumbersForDriverOfAge(Integer.parseInt(args[0]));
        
        } else if (command.equals(RequestHandler.VACATE_SLOT)) {
            
            result = parkingManager.vacateSlot(Integer.parseInt(args[0]));

        } else {
            result = String.format(RequestHandler.REQUEST_NOT_FOUND, request);
        }

        return result;
    }
    
    
    
    
    
    /**
     * main function 
     *  - checks if a filepath is provided in the arguments while running the program.
     *  - creates a new AutomatedTicketingSystem object.
     *  - reads the file content of the provided file as a string and passes it over to the processRequest function for processing the request and gets a response back.
     *  - Prints the response to System.out.
     * @param args
     */
    
    public static void main(String[] args) {
        
        if (args.length == 0) {
            System.out.println("Please provide the path of the input file.");
            return;
        } 

        AutomatedTicketingSystem automatedTicketingSystem = new AutomatedTicketingSystem();

        String inputFilePath = args[0];

        String fileContent = Helper.resolveFile(inputFilePath);

        Scanner scanner = new Scanner(fileContent);

        while (scanner.hasNextLine()) {

            String request = scanner.nextLine();

            String result = automatedTicketingSystem.processRequest(request);

            System.out.println(result);

        }

        scanner.close();
    }
}
