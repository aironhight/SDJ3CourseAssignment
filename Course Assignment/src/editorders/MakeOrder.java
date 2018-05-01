package editorders;

import java.util.Scanner;

import dao.DAO;

public class MakeOrder 
{
	public static void main(String[] args)
	{
		DAO dao = new DAO();
		Scanner in = new Scanner(System.in);
		
		while (true) {
			
			System.out.println("-- INSERT A NEW ORDER --");
			
			String partName, carMake, carModel;
			int carYear, quantity;
			
			String companyName, companyAddress, companyCountry;
			
			System.out.println("Company name :"); companyName = in.nextLine();
			System.out.println("Company address :"); companyAddress = in.nextLine();
			System.out.println("Company country :"); companyCountry = in.nextLine();
			
			//dao.addOrder();
			
			while (true) {
				
				String chose;
				
				System.out.println("Insert 'yes' if you want to add one more part"); chose = in.nextLine();
				
				if (!chose.toLowerCase().equals("yes")) break;
				
				System.out.println("Part Name :"); partName = in.nextLine();
				
				System.out.println("Car Make :"); carMake = in.nextLine();
				
				System.out.println("Car Model :"); carModel = in.nextLine();
				
				System.out.println("Car Year :"); carYear = Integer.parseInt(in.nextLine());
				
				System.out.println("Quantity :"); quantity = Integer.parseInt(in.nextLine());
				
				//dao.insertInOrderPart
			}
			
		}
	}
}
