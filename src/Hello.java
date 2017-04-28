/**
 * Created by USER on 02.03.2017.
 */
import java.rmi.Remote;
import java.rmi.RemoteException;
public interface Hello extends Remote {
    String sayHello(String b) throws RemoteException;
}