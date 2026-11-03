package bai8;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class FlightServiceImpl extends UnicastRemoteObject implements FlightService {
    private HashMap<String, Integer> flights = new HashMap<>();

    public FlightServiceImpl() throws RemoteException {
        super();
        flights.put("VN123", 50); // Chuyến bay mẫu 1
        flights.put("VJ456", 20); // Chuyến bay mẫu 2
    }

    @Override
    public synchronized int availableSeats(String flightNumber) throws RemoteException {
        return flights.getOrDefault(flightNumber, -1);
    }

    @Override
    public synchronized boolean bookTicket(String flightNumber, int seats) throws RemoteException {
        int available = flights.getOrDefault(flightNumber, -1);
        if (available >= seats) {
            flights.put(flightNumber, available - seats);
            return true;
        }
        return false;
    }

    @Override
    public synchronized boolean cancelBooking(String flightNumber, int seats) throws RemoteException {
        if (flights.containsKey(flightNumber)) {
            flights.put(flightNumber, flights.get(flightNumber) + seats);
            return true;
        }
        return false;
    }
}