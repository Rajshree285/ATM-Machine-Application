package rajs;

public class Operations {
	
	double viewBalance(double balance){
		return balance;
	}
	
	double withdrawFunds(double withdrawAmt, double balance){
		if(balance>=withdrawAmt){
			balance-=withdrawAmt;
		}
		else{
			System.out.println("Insufficient Balance");
		}
		return balance;
	}
	
	double depositFunds(double depAmt, double balance){
		balance+=depAmt;
		System.out.println("Amount Deposited Sucessfully!");
		return balance;
	}
	
	double transferFunds(double transAmt, double balance){
		 if(transAmt<=balance){
			 balance-=transAmt;
			 System.out.println("Transfer Sucessfull!");
		 }
		 else
		 System.out.println("Insufficient Balance");
		return balance;
	}
	
	double transferFund(double transAmt, double balance2, double balance){
		 if(transAmt<=balance){
			 balance2+=transAmt;
		 }
		 else
		 System.out.println("Insufficient Balance");
		return balance2;
	}
	
	public void exitMessage(){
		System.out.println("Thank you for using this ATM.\nPlease Visit Again :)");
	}
	
}
