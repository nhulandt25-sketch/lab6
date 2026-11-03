package bai5;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            ChatService chat = (ChatService) registry.lookup("ChatService");
            Scanner scanner = new Scanner(System.in);

            System.out.println("Bắt đầu chat với Server (gõ 'exit' để thoát):");
            while (true) {
                System.out.print("Client: ");
                String msg = scanner.nextLine();
                if (msg.equalsIgnoreCase("exit")) break;

                String reply = chat.sendMessage(msg);
                System.out.println("Server phản hồi: " + reply);
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}