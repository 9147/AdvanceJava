package RMI.common;

import java.rmi.*;
public interface Bank extends Remote {
    public void Deposit(String accountno,int amount) throws RemoteException;
    public void Withdraw(String accountno,int amount) throws RemoteException;
    public int getBalance(String accountno) throws RemoteException;
}
