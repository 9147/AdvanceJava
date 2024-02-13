package RMI.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import RMI.common.Bank;

public class Account extends UnicastRemoteObject implements Bank{
    private Map<String, Integer> accounts;

    protected Account() throws RemoteException {
        accounts = new HashMap<>();
    }

    @Override
    public void Deposit(String accountno,int amount) throws RemoteException {
        accounts.put(accountno, accounts.getOrDefault(accountno,0)+amount);
    }

    @Override
    public void Withdraw(String accountno,int amount) throws RemoteException {
        if(accounts.get(accountno) < amount){
            System.out.println("Insufficient balance");
            return;
        }else{
            accounts.put(accountno, accounts.get(accountno)-amount);
        }

    }

    @Override
    public int getBalance(String accountno) throws RemoteException {
        return accounts.getOrDefault(accountno,0);
    }
}
