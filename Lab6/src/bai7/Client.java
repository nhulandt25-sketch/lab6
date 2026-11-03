package bai7;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            GeometryService geo = (GeometryService) registry.lookup("GeometryService");
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("\n--- TÍNH DIỆN TÍCH HÌNH HỌC ---");
                System.out.println("1. Hình chữ nhật");
                System.out.println("2. Hình tròn");
                System.out.println("3. Hình tam giác");
                System.out.println("0. Thoát");
                System.out.print("Chọn hình: ");
                int choice = Integer.parseInt(scanner.nextLine());

                if (choice == 1) {
                    System.out.print("Nhập chiều rộng: ");
                    double w = Double.parseDouble(scanner.nextLine());
                    System.out.print("Nhập chiều cao: ");
                    double h = Double.parseDouble(scanner.nextLine());
                    System.out.println("Diện tích HCN: " + geo.rectangleArea(w, h));
                } else if (choice == 2) {
                    System.out.print("Nhập bán kính: ");
                    double r = Double.parseDouble(scanner.nextLine());
                    System.out.println("Diện tích Hình tròn: " + geo.circleArea(r));
                } else if (choice == 3) {
                    System.out.print("Nhập cạnh đáy: ");
                    double b = Double.parseDouble(scanner.nextLine());
                    System.out.print("Nhập chiều cao: ");
                    double h = Double.parseDouble(scanner.nextLine());
                    System.out.println("Diện tích Tam giác: " + geo.triangleArea(b, h));
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