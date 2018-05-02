package editorders;

import java.util.Scanner;

import dao.DAO;

public class MakeOrder 
{
	public static void main(String[] args)
	{
		DAO dao = new DAO();
		Scanner in = new Scanner(System.in);
		
		dao.delete();
		
		while (true) {
			
			System.out.println("-- INSERT A NEW ORDER --");
			
			String receiverName, receiverAddress, receiverCountry;
			
			System.out.println("Receiver name :"); receiverName = in.nextLine();
			System.out.println("Receiver address :"); receiverAddress = in.nextLine();
			System.out.println("Receiver country :"); receiverCountry = in.nextLine();
			
			int orderID = dao.addOrderFromReceiver(receiverName, receiverAddress, receiverCountry);

			String partType, carMake, carModel;
			int carYear, quantity;
			
			while (true) {
				
				String chose;
				
				System.out.println("Insert 'yes' if you want to add one more part"); chose = in.nextLine();
				
				if (!chose.toLowerCase().equals("yes")) break;
				
				System.out.println("Part Type :"); partType = in.nextLine();
				
				System.out.println("Car Make :"); carMake = in.nextLine();
				
				System.out.println("Car Model :"); carModel = in.nextLine();
				
				System.out.println("Car Year :"); carYear = Integer.parseInt(in.nextLine());
				
				System.out.println("Quantity :"); quantity = Integer.parseInt(in.nextLine());
				
				dao.insertInOrderPart(partType, carMake, carModel, carYear, quantity, orderID);
			}
			
		}
	}
}
