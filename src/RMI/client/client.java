package RMI.client;

import RMI.common.Bank;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class client {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            Bank bankingService = (Bank) registry.lookup("BankingService");

            // Perform operations
            String accountNumber = "123456";
            System.out.println("Initial balance: " + bankingService.getBalance(accountNumber));
            int choice;
            Scanner scn = new Scanner(System.in);
            while(true){
                System.out.println("Enter the choice: ");
                System.out.println("1)Deposit\n2)Withdraw\n3)Check Balance\n4)exit");
                choice = scn.nextInt();
                switch (choice){
                    case 1:
                        System.out.println("Enter amount to deposit: ");
                        int amount = scn.nextInt();
                        bankingService.Deposit(accountNumber, amount);
                        System.out.println("New balance: " + bankingService.getBalance(accountNumber));
                        break;
                    case 2:
                        System.out.println("Enter amount to withdraw: ");
                        amount = scn.nextInt();
                        bankingService.Withdraw(accountNumber, amount);
                        System.out.println("New balance: " + bankingService.getBalance(accountNumber));
                        break;
                    case 3:
                        System.out.println("Current balance: " + bankingService.getBalance(accountNumber));
                        break;
                    case 4:
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
