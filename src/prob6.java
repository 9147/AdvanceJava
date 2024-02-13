/*
Write a Java program to demonstrate how the standard operations on a bank account can be
synchronized.
 */
import java.util.*;

public class prob6 {
 public static void main(String args[]) throws InterruptedException{
     Bank b1 = new Bank(10000,500);
        Deposit d1 = new Deposit(b1,1000);
        Withdraw w1 = new Withdraw(b1,2000);
        Deposit d2 = new Deposit(b1,2000);
        Withdraw w2 = new Withdraw(b1,3000);

 }
}

class Bank{
    private int amount;
    private int minBalance;

    Bank(int amount,int minBalance){
        this.amount = amount;
        this.minBalance=minBalance;
    }
    Bank(){
        this.amount = 10000;
        this.minBalance=500;
    }
    synchronized void deposit(int amount) throws InterruptedException{
        this.amount+=amount;
        System.out.println("Amount deposited: "+amount);
        this.checkBalance();
        Thread.sleep(1000);
    }

    synchronized void withdraw(int amount) throws InterruptedException{
        if(this.amount-amount<minBalance){
            System.out.println("Insufficient balance");
            return;
        }
        this.amount-=amount;
        System.out.println("Amount withdrawn: "+amount);
        this.checkBalance();
        Thread.sleep(1000);
    }

    void checkBalance() throws InterruptedException{
        System.out.println("Total amount: "+this.amount);
        Thread.sleep(1000);
    }

}

class Deposit extends Thread{
    Bank obj;
    Thread trd;
    int amount;
    Deposit(Bank obj,int amount){
        this.obj=obj;
        this.amount=amount;
        trd = new Thread(this,"Deposit");
        trd.start();
    }
    public void run(){
        try{
            obj.deposit(amount);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}

class Withdraw implements Runnable{
    Bank obj;
    Thread trd;
    int amount;
    Withdraw(Bank obj,int amount){
        this.obj=obj;
        this.amount=amount;
        trd = new Thread(this,"withdraw");
        trd.start();
    }

    public void run(){
        try{
            obj.withdraw(amount);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
