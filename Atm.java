package rajs;

import java.util.Scanner;

class InvalidNumberException extends Exception{
	private static final long serialVersionUID = 1L;
	InvalidNumberException(){
		super();
	}
}

public class Atm {
	
	public static int verifyAccId(String[][] users, int accId)throws InvalidNumberException{
		int count1=-1;
		for(int i=0;i<4;i++){
			if (accId==Integer.parseInt(users[i][1])){
				count1=i;
			}
		}
		if (count1==-1){
			System.out.println("You have entered an invalid acoount number!\nPlease Enter a valid account number.");
			throw new InvalidNumberException();
		}
		return count1;
	}
	
	public static void verifyPin(String[][] users, int pin, int count1)throws InvalidNumberException{
		if (!(pin==Integer.parseInt(users[count1][2]))){
			System.out.println("Incorrect PIN !!!");
			throw new InvalidNumberException();
		}
	}
	
	public static void main(String[] args) throws InvalidNumberException{
		Scanner sc = new Scanner (System.in);
		int ch, flag=1, count1=-1, count2=-1;
		
		String[][] users = {
				{"PATRICK JANE", "12345678", "1234", "5000.00"},
				{"AMY COOPER", "98765432", "4321", "2000.00"},
				{"MONICA MORGAN", "78912346", "9876", "3500.00"},
				{"SARAH MILLER", "35715982", "6405", "9300.00"}
		};
		
		System.out.println("Hello, Welcome to the ATM!");
		System.out.print("Please Enter your 8 digit Account ID : ");
		int accId=sc.nextInt();
		count1=verifyAccId(users, accId);
		System.out.print("Enter your PIN : ");
		int pin = sc.nextInt();
		verifyPin(users, pin, count1);
		System.out.println(users[count1][0]);
		double balance=Double.parseDouble(users[count1][3]), newBal1, newBal2;
		while(flag>0){
			System.out.println("Enter your choice : ");
			System.out.println("1.View Balance\n2.Withdraw Funds\n3.Deposit Funds\n4.Transfer Funds\n5.Exit");
			ch= sc.nextInt();
			Operations o = new Operations();
			switch(ch){
				case 1: System.out.println("Balance : Rs." + o.viewBalance(balance));
						break;
				case 2: double withdrawAmt;
						System.out.print("Enter withdrawal Amount : ");
						withdrawAmt=sc.nextDouble();
						newBal1=o.withdrawFunds(withdrawAmt, balance);
						System.out.println("Balance : "+ newBal1);
						users[count1][3]=Double.toString(newBal1);
						balance=Double.parseDouble(users[count1][3]);
						break;
				case 3: double depAmt;
						System.out.print("Enter Amount to be deposited : ");
						depAmt=sc.nextDouble();
						newBal1=o.depositFunds(depAmt,balance);
						System.out.println("Balance : "+ newBal1);
						users[count1][3]=Double.toString(newBal1);
						balance=Double.parseDouble(users[count1][3]);
						break;
				case 4: double transAmt;
				 		System.out.println("Enter Beneficiary Account Number : ");
				 		int accId2=sc.nextInt();
				 		for(int i=0;i<4;i++){
				 			if(i==count1){
				 				i++;
				 			}
								if (accId2==Integer.parseInt(users[i][1])){
									count1=0;
									count2=i;
									break;
								}
								else{
									count1=-1;
								}
						}
				 		if(count1==-1){
				 			System.out.println("You have entered an invalid acoount number!\nPlease Enter a valid account number.");
							throw new InvalidNumberException();
				 		}
				 		System.out.print("Enter Transfer Amount : ");
						transAmt=sc.nextDouble();
						newBal1=o.transferFunds(transAmt, balance);
						newBal2=o.transferFund(transAmt, Double.parseDouble(users[count2][3]), balance);
						System.out.println("Balance : "+newBal1);
						users[count1][3]=Double.toString(newBal1);
						balance=Double.parseDouble(users[count1][3]);
						users[count2][3]=Double.toString(newBal2);
						break;
				case 5: o.exitMessage();
						flag=0;
						break;
			}
		}
	}
}
