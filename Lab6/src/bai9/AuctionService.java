package bai9;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

public interface AuctionService extends Remote {
    void addProduct(String productName, double startPrice) throws RemoteException;
    Map<String, Double> getProductList() throws RemoteException;
    boolean placeBid(String productName, double bidAmount, String bidderName) throws RemoteException;
    double getCurrentPrice(String productName) throws RemoteException;
}