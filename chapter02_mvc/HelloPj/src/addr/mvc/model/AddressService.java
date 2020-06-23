package addr.mvc.model;

import java.util.ArrayList;

import mvc.domain.Address;

public class AddressService {
	//SingleTon Object Model
	private AddressDAO dao;
	private static final AddressService INSTANCE = new AddressService();
	
	private AddressService() {
		dao = new AddressDAO();
	}
	
	public static AddressService getInstance() {
		return INSTANCE;
	}
	
	public ArrayList<Address> listS(){
		return dao.list();
	}
	
	public void delS(int seq) {
		dao.del(seq);
	}
	
	public boolean insertS(Address addr) {
		return dao.insert(addr);
	}
}
