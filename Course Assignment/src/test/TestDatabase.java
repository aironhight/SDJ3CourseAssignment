package test;

import dao.DAO;

public class TestDatabase 
{
	public static void main(String[] args)
	{
		DAO dao = new DAO();
		
		dao.printEverything();
	}
}
