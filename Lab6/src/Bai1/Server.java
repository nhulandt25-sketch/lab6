package bai1;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) {
        try {
      
            Registry registry = LocateRegistry.createRegistry(1099);
            
            // Khởi tạo đối tượng từ xa
            Hello obj = new HelloImpl();
            
          
            registry.rebind("HelloService", obj);
            
            System.out.println("RMI Server đang chạy...");
        } catch (Exception e) {
            System.err.println("Lỗi Server: " + e.getMessage());
            e.printStackTrace();
        }
    }
}