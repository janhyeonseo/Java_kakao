package cart;

import java.util.HashMap;
import java.util.List;

public interface CartDao {
   void  insert(CartVO vo);
   
   void  orderInsert(OrderVO vo);
   List<HashMap<String, Object>>  orderDetails(OrderVO vo);
   
   void  update(CartVO vo);
   void  delete(CartVO vo);
   List<HashMap<String, Object>>  cartSelect(OrderVO vo);
   
   List<HashMap<String, Object>>  orderList(OrderVO vo);
}
