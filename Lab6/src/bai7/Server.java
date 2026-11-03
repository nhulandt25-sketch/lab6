package bai7;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            GeometryService service = new GeometryServiceImpl();
            registry.rebind("GeometryService", service);
            System.out.println("Geometry Server đang chạy...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}