package bai4;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class ContactServiceImpl extends UnicastRemoteObject implements ContactService {
    private HashMap<String, String> contacts = new HashMap<>();

    public ContactServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public synchronized void addContact(String name, String phone) throws RemoteException {
        contacts.put(name, phone);
    }

    @Override
    public synchronized String findContact(String name) throws RemoteException {
        return contacts.getOrDefault(name, "Không tìm thấy liên hệ này.");
    }

    @Override
    public synchronized boolean deleteContact(String name) throws RemoteException {
        return contacts.remove(name) != null;
    }
}