package bai6;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            BankService service = new BankServiceImpl(1000.0); // Số dư ban đầu 1000$
            registry.rebind("BankService", service);
            System.out.println("Bank Server đang chạy (Số dư khởi tạo: 1000.0)...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}