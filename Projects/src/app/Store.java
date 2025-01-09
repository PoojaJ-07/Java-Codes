package app;

import java.util.Scanner;

public class Store {

	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Item ID number=>");
		int number=sc.nextInt();
		
		System.out.println("Enter Item Name=>");
		String name=sc.next().toUpperCase();
		
		System.out.println("Enter Item rate=>");
		double cost=sc.nextDouble();
		
		System.out.println("Enter Item Qauntity=>");
		int quant=sc.nextInt();
		
		double price=(double) (cost*quant);
		System.out.println("The Cost is "+price);
		
	
		System.out.println("The Item is=>"+name);
		if(price >1000 && price <2000)
		{
			double discount1= 0.10*price;
			double netPrice1=price-discount1;
			System.out.println("The Discount amount is=>"+discount1);
			System.out.println("The Net Price of"+" "+ name +" "+"is"+" "+netPrice1);
		}
		else if(price >2000 && price<3000)
		{
			double discount2=0.10*price;
			double netPrice2=price-discount2;
			System.out.println("The Discount amount is=>"+discount2);
			System.out.println("The Net Price of"+" "+ name +" "+"is"+" "+netPrice2);
		}
		else 
		{
			double discount3=0.20*price;
			double netPrice3=price-discount3;
			System.out.println("The Discount amount is=>"+discount3);
			System.out.println("The Net Price of"+" "+ name +" "+"is"+" "+netPrice3);
		}
			System.out.println("Thanks for Visiting ");
	
	}

}
