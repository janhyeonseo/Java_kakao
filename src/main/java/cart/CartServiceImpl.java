package cart;

import java.util.HashMap;
import java.util.List;

public class CartServiceImpl implements CartService {
	
	public CartDao dao = new  CartDaoImpl() ;
	
	@Override
	public void insert(CartVO vo) {
		dao.insert(vo);		
	}

	@Override
	public List<HashMap<String, Object>> cartSelect(OrderVO vo) {
		return dao.cartSelect(vo);
	}

	@Override
	public void update(CartVO vo) {
		dao.update(vo);		
	}

	@Override
	public void delete(CartVO vo) {
		dao.delete(vo);
		
	}

	@Override
	public void orderInsert(OrderVO vo) {
		dao.orderInsert(vo);;
		
	}

	@Override
	public List<HashMap<String, Object>> orderList(OrderVO vo) {
		// TODO Auto-generated method stub
		return dao.orderList(vo);
	}

	@Override
	public List<HashMap<String, Object>> orderDetails(OrderVO vo) {
		// TODO Auto-generated method stub
		return dao.orderDetails(vo);
	}

}
