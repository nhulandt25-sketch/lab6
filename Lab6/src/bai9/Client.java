package bai9;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Map;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            AuctionService auction = (AuctionService) registry.lookup("AuctionService");
            Scanner scanner = new Scanner(System.in);

            System.out.print("Nhập tên định danh của bạn (Username): ");
            String username = scanner.nextLine();

            while (true) {
                System.out.println("\n--- HỆ THỐNG ĐẤU GIÁ TRỰC TUYẾN ---");
                System.out.println("1. Xem danh sách sản phẩm & giá hiện tại");
                System.out.println("2. Thêm sản phẩm mới (Dành cho Seller)");
                System.out.println("3. Đặt giá thầu (Dành cho Bidder)");
                System.out.println("0. Thoát");
                System.out.print("Chọn chức năng: ");
                int choice = Integer.parseInt(scanner.nextLine());

                if (choice == 1) {
                    Map<String, Double> list = auction.getProductList();
                    System.out.println("\n--- DANH SÁCH SẢN PHẨM ĐANG ĐẤU GIÁ ---");
                    if (list.isEmpty()) {
                        System.out.println("Hiện chưa có sản phẩm nào.");
                    } else {
                        for (Map.Entry<String, Double> entry : list.entrySet()) {
                            System.out.println("- Sản phẩm: " + entry.getKey() + " | Giá cao nhất hiện tại: " + entry.getValue());
                        }
                    }
                } else if (choice == 2) {
                    System.out.print("Nhập tên sản phẩm muốn đấu giá: ");
                    String name = scanner.nextLine();
                    System.out.print("Nhập giá khởi điểm: ");
                    double price = Double.parseDouble(scanner.nextLine());
                    auction.addProduct(name, price);
                    System.out.println("Thêm sản phẩm thành công lên hệ thống!");
                } else if (choice == 3) {
                    System.out.print("Nhập tên sản phẩm muốn đấu giá: ");
                    String name = scanner.nextLine();
                    double current = auction.getCurrentPrice(name);
                    if (current == -1) {
                        System.out.println("Không tìm thấy sản phẩm này trong hệ thống!");
                        continue;
                    }
                    System.out.println("--> Giá hiện tại của [" + name + "] là: " + current);
                    System.out.print("Nhập mức giá thầu của bạn (phải cao hơn giá hiện tại): ");
                    double bid = Double.parseDouble(scanner.nextLine());
                    
                    boolean success = auction.placeBid(name, bid, username);
                    if (success) {
                        System.out.println("Chúc mừng! Đặt giá thầu thành công.");
                    } else {
                        System.out.println("Thất bại! Giá thầu của bạn phải cao hơn mức giá hiện tại.");
                    }
                } else if (choice == 0) {
                    System.out.println("Đã thoát ứng dụng.");
                    break;
                }
            }
            scanner.close();
        } catch (Exception e) {
            System.err.println("Lỗi Client: " + e.getMessage());
            e.printStackTrace();
        }
    }
}