package bai8;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            FlightService flight = (FlightService) registry.lookup("FlightService");
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("\n--- ĐẶT VÉ MÁY BAY ---");
                System.out.println("1. Kiểm tra ghế trống (Mã: VN123 hoặc VJ456)");
                System.out.println("2. Đặt vé");
                System.out.println("3. Hủy vé");
                System.out.println("0. Thoát");
                System.out.print("Chọn chức năng: ");
                int choice = Integer.parseInt(scanner.nextLine());

                if (choice == 1) {
                    System.out.print("Nhập mã chuyến bay: ");
                    String fNum = scanner.nextLine();
                    int seats = flight.availableSeats(fNum);
                    if (seats == -1) System.out.println("Không tìm thấy chuyến bay này!");
                    else System.out.println("Số ghế trống hiện tại: " + seats);
                } else if (choice == 2) {
                    System.out.print("Nhập mã chuyến bay: ");
                    String fNum = scanner.nextLine();
                    System.out.print("Nhập số lượng ghế muốn đặt: ");
                    int seats = Integer.parseInt(scanner.nextLine());
                    boolean success = flight.bookTicket(fNum, seats);
                    if (success) System.out.println("Đặt vé thành công!");
                    else System.out.println("Đặt vé thất bại (Không đủ ghế hoặc sai mã chuyến)!");
                } else if (choice == 3) {
                    System.out.print("Nhập mã chuyến bay: ");
                    String fNum = scanner.nextLine();
                    System.out.print("Nhập số lượng ghế muốn hủy: ");
                    int seats = Integer.parseInt(scanner.nextLine());
                    boolean success = flight.cancelBooking(fNum, seats);
                    if (success) System.out.println("Hủy vé thành công!");
                    else System.out.println("Không tìm thấy chuyến bay!");
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