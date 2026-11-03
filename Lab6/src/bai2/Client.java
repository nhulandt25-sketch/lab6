package bai2;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            Calculator comp = (Calculator) registry.lookup("CalcService");
            
            Scanner scanner = new Scanner(System.in);
            System.out.print("Nhập số a: ");
            int a = scanner.nextInt();
            System.out.print("Nhập số b: ");
            int b = scanner.nextInt();
            
            int result = comp.add(a, b);
            System.out.println("Tổng từ Server trả về: " + a + " + " + b + " = " + result);
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}