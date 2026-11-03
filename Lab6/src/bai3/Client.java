package bai3;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            PrimeService comp = (PrimeService) registry.lookup("PrimeService");
            
            Scanner scanner = new Scanner(System.in);
            System.out.print("Nhập một số nguyên cần kiểm tra: ");
            int n = scanner.nextInt();
            
            boolean result = comp.isPrime(n);
            if (result) {
                System.out.println(n + " LÀ số nguyên tố.");
            } else {
                System.out.println(n + " KHÔNG PHẢI là số nguyên tố.");
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}