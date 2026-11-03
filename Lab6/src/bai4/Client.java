package bai4;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            ContactService contact = (ContactService) registry.lookup("ContactService");
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("\n--- QUẢN LÝ DANH BẠ ---");
                System.out.println("1. Thêm liên hệ");
                System.out.println("2. Tìm kiếm liên hệ");
                System.out.println("3. Xóa liên hệ");
                System.out.println("0. Thoát");
                System.out.print("Chọn chức năng: ");
                int choice = Integer.parseInt(scanner.nextLine());

                if (choice == 1) {
                    System.out.print("Nhập tên: ");
                    String name = scanner.nextLine();
                    System.out.print("Nhập số điện thoại: ");
                    String phone = scanner.nextLine();
                    contact.addContact(name, phone);
                    System.out.println("Thêm thành công!");
                } else if (choice == 2) {
                    System.out.print("Nhập tên cần tìm: ");
                    String name = scanner.nextLine();
                    String result = contact.findContact(name);
                    System.out.println("Kết quả: " + result);
                } else if (choice == 3) {
                    System.out.print("Nhập tên cần xóa: ");
                    String name = scanner.nextLine();
                    boolean deleted = contact.deleteContact(name);
                    if (deleted) System.out.println("Đã xóa thành công!");
                    else System.out.println("Không tìm thấy tên để xóa.");
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