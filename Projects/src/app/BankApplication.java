package app;

import java.util.Scanner;

public class BankApplication {

	
		private String accountHolder;
		private double balance;
		
	//Constructor to initialize account details
		public BankApplication(String accountHolder,double initialBalance)
		{
			this.accountHolder=accountHolder;
			
			if(initialBalance >= 0)
			{
				this.balance=initialBalance;
			}
			else
			{
				System.out.println("Initial balance cannot be negative.");
				this.balance=0.0;
			}
		}
		
	//Method to deposit money into the account
		public void deposit(double amount)
		{
			if(amount>0)
			{
				balance+=amount;
				System.out.println("Succeefullt deposited: $"+amount);
			}
			else
			{
				System.out.println("Deposit amount must be positive.");
			}
		}
		
	//Method to withdraw money from the account
		public void withdraw(double amount)
		{
			if(amount>0)
				 if(amount<=balance)
				 {
					 balance-=amount;
					 System.out.println("Successfully withdraw:$"+amount);
				 }
				 else
				 {
					 System.out.println("Insufficient balance for withdraw.");
				 }
			     else
			     {
			    	 System.out.println("Withdrawal amount must be positive.");;
			     }
		}
		
	//Method to transfer money from his account to another account 
		public void transfer(BankApplication targetAccount,double amount)
		{
           if(amount >0 && amount <= balance)
           {
		       this.withdraw(amount);    //Withdraw from the current account
           
		       targetAccount.deposit(amount); //Deposit into the target account
		       System.out.println("Successfully transfered: $"+amount);
           }
           else
           {
        	   System.out.println("Transfer failed: Invalid amount or Insuffient balance.");
           }
		}
		
	//Method to get the current balance
		
		public double getBalance()
		{
				return balance;
		}
		
	//Method to get account holder's name
		public String getAccountHolder()
		{
			return accountHolder;
		}
	
		public static void main(String[] args)
		{
			//Create two bank accounts for demonstration
			BankApplication account1 = new BankApplication("Alice",500.0);
			BankApplication account2 = new BankApplication("Bob",300.0);
		
		   //Scanner for user input
			Scanner scanner=new Scanner(System.in);
			
		  //Main application loop 
			while(true)
			{
				//Display menu options
				System.out.println("\n----Welcome to the Banking App----");
				System.out.println("1.Check Balance");
				System.out.println("2.Deposit Money");
				System.out.println("3.Withdraw Money");
				System.out.println("4.Transfer Money");
				System.out.println("5.Exit");
				System.out.println("Choose an Option:");
				
				//Capture the user's choice
				int choice=scanner.nextInt();
				
				switch(choice)
				{  
				    case 1: //Check balance for account1
				         	System.out.println(account1.getAccountHolder()+"'s Balance: $"+account1.getBalance());
				         	break;
				         	
				    case 2: //Deposit money into account1
				    	    System.out.println("Enter deposit amount: $");
				    	    double depositAmount = scanner.nextDouble();
				    	    account1.deposit(depositAmount);
				    	    break;
				    	    
				    case 3: //Withdraw money from account1
				    	    System.out.println("Enter withdrawal amount: $");
				    	    double withdrawalAmount= scanner.nextDouble();
				    	    account1.withdraw(withdrawalAmount);
				    	    break;
				    	     
				    case 4: //Transfer money from account1 to account2
				    	    System.out.println("Enter transfer amount: $");
				    	    double transferAmount=scanner.nextDouble();
				    	    account1.transfer(account2, transferAmount);
				    	    break;
				    	    
				    case 5: // Exit
				    	    System.out.println("Thank You for using the Banking App.GoodBye!");
				    	    scanner.close();
				    	    System.exit(0);
				    	    break;
				    	    
				   default: System.out.println("Invalid choice.Please choose again."); 	    
				}
			}
		
		
		}

		
	}


