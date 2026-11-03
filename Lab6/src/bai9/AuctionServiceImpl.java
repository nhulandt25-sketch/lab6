package bai9;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class AuctionServiceImpl extends UnicastRemoteObject implements AuctionService {

    private HashMap<String, Double> products = new HashMap<>();

    public AuctionServiceImpl() throws RemoteException {
        super();
   
        products.put("Laptop Gaming", 15000.0);
        products.put("Smartphone", 8000.0);
    }

    @Override
    public synchronized void addProduct(String productName, double startPrice) throws RemoteException {
        products.put(productName, startPrice);
        System.out.println("[Server LOG] Seller đã thêm sản phẩm mới: " + productName + " (Giá khởi điểm: " + startPrice + ")");
    }

    @Override
    public synchronized Map<String, Double> getProductList() throws RemoteException {
        return new HashMap<>(products);
    }

    @Override
    public synchronized boolean placeBid(String productName, double bidAmount, String bidderName) throws RemoteException {
        if (!products.containsKey(productName)) return false;
        
        double currentPrice = products.get(productName);
        if (bidAmount > currentPrice) {
            products.put(productName, bidAmount);
            System.out.println("[Server THÔNG BÁO] Người dùng [" + bidderName + "] vừa đặt giá thầu mới cho " + productName + " với mức giá: " + bidAmount);
            return true;
        }
        return false;
    }

    @Override
    public synchronized double getCurrentPrice(String productName) throws RemoteException {
        return products.getOrDefault(productName, -1.0);
    }
}