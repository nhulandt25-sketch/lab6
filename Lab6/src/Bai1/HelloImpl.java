package bai1;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class HelloImpl extends UnicastRemoteObject implements Hello {
    public HelloImpl() throws RemoteException {
        super(); // Khởi tạo stub/skeleton ở cổng mặc định
    }

    @Override
    public String sayHello() throws RemoteException {
        return "Hello, World!";
    }
}