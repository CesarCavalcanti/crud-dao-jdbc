package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Seller;

public class Program {
	
	public static void main(String[] args) {
		
		SellerDao sld = DaoFactory.createSellerDao();
		
		Seller sl = sld.findById(1);
		
		System.out.println(sl);
	}

}
