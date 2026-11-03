package bai6;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            BankService bank = (BankService) registry.lookup("BankService");
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("\n--- HỆ THỐNG NGÂN HÀNG RMI ---");
                System.out.println("1. Kiểm tra số dư");
                System.out.println("2. Gửi tiền (Deposit)");
                System.out.println("3. Rút tiền (Withdraw)");
                System.out.println("0. Thoát");
                System.out.print("Chọn chức năng: ");
                int choice = Integer.parseInt(scanner.nextLine());

                if (choice == 1) {
                    System.out.println("Số dư hiện tại: " + bank.getBalance());
                } else if (choice == 2) {
                    System.out.print("Nhập số tiền muốn gửi: ");
                    double amount = Double.parseDouble(scanner.nextLine());
                    bank.deposit(amount);
                    System.out.println("Gửi tiền thành công!");
                } else if (choice == 3) {
                    System.out.print("Nhập số tiền muốn rút: ");
                    double amount = Double.parseDouble(scanner.nextLine());
                    boolean success = bank.withdraw(amount);
                    if (success) System.out.println("Rút tiền thành công!");
                    else System.out.println("Thất bại! Số dư không đủ hoặc số tiền không hợp lệ.");
                } else if (choice == 0) {
                    break;
                }
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}