package bai9;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            AuctionService service = new AuctionServiceImpl();
            registry.rebind("AuctionService", service);
            System.out.println("Auction Server đang chạy tại cổng 1099...");
        } catch (Exception e) {
            System.err.println("Lỗi Auction Server: " + e.getMessage());
            e.printStackTrace();
        }
    }
}