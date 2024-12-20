package app;

public class RevDigits {

	public static void main(String[] args) {
	
		int number=12345;
		
		int r=0;
		while(number>0)
		{
			r=r*10+(number % 10);
			  number/=10;
		}
         System.out.println("The Reverse of Given Digits is=>" +r);
	}

}
