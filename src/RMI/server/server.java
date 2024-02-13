package RMI.server;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
public class server {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("BankingService", new Account());
            System.out.println("BankingService is running...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
