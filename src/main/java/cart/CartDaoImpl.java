package cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import majustory.DBConnection;

public class CartDaoImpl implements CartDao{
	
	private DBConnection DBConn = DBConnection.getInstance();
	private Connection conn = null;
	private PreparedStatement pstmt =null;
	private ResultSet rs = null;
	
	@Override
	public void insert(CartVO vo) {
		System.out.println("CartVO vo확인:" + vo);
		try {
			conn = DBConn.getConnection();
			String SQL = "INSERT INTO tbl_cart "
					+ "( mid, pid, amount )"
					+ " VALUES (?,?,?) ";

			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, vo.getMid());
			pstmt.setString(2, vo.getPid());
			pstmt.setInt(3, vo.getAmount());			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}				
	}

	@Override
	public List<HashMap<String, Object>> cartSelect(OrderVO vo) {
		
			List<HashMap<String, Object>> li =new ArrayList<HashMap<String, Object>>();
			try {
			conn = DBConn.getConnection();
			String SQL = "select  p.pid as pid, pname, pprice, pimg, cart_id, mid, amount from  product  p  join tbl_cart c "
					+ " on p.pid = c.pid where  mid = ? ";
			pstmt  = conn.prepareStatement(SQL);
			pstmt.setString(1, vo.getMid());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				HashMap<String, Object>	member = new HashMap<String, Object>();
				member.put("cart_id",rs.getString("cart_id"));
				member.put("mid",rs.getString("mid"));
				member.put("pid",rs.getString("pid"));
				member.put("pname",rs.getString("pname"));
				member.put("pimg",rs.getString("pimg"));
				member.put("pprice",rs.getInt("pprice"));
				member.put("amount",rs.getInt("amount"));
				li.add(member);
			}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return li;
	}

	@Override
	public void update(CartVO vo) {
		System.out.println("CartVO vo확인:" + vo);
		try {
			conn = DBConn.getConnection();
			String SQL = "Update tbl_cart set  amount =? "
					+ " where cart_id = ? " ;

			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, vo.getAmount());
			pstmt.setString(2, vo.getCart_id());
		
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	@Override
	public void delete(CartVO vo) {
		System.out.println("CartVO vo확인:" + vo);
		try {
			conn = DBConn.getConnection();
			String SQL = "delete from tbl_cart "
					+ " where mid = ? " ;
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, vo.getMid());		
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void orderInsert(OrderVO vo) {
		System.out.println("===> OrderVO vo확인:" + vo);
		try {
			conn = DBConn.getConnection();
			String SQL = "INSERT INTO orderT  "
					+ "(orderG, cart_id, mid, pid, amount )"
					+ " VALUES (?,?,?,?,?) ";

			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, vo.getOrderG());
			pstmt.setInt(2, vo.getCart_id());
			pstmt.setString(3, vo.getMid());	
			pstmt.setString(4, vo.getPid());	
			pstmt.setInt(5, vo.getAmount());	
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
	}

	@Override
	public List<HashMap<String, Object>> orderList(OrderVO vo) {
		System.out.println("===> orderList:"+ vo );
		List<HashMap<String, Object>> li =new ArrayList<HashMap<String, Object>>();
		try {
		conn = DBConn.getConnection();
		String SQL = "select count(*) tc, OrderG, today  from  orderT"
				+ " where mid = ? "
				+ " Group  by  OrderG, today "
				+ " order by today desc , idxOrder  desc   ";
		pstmt  = conn.prepareStatement(SQL);
		pstmt.setString(1, vo.getMid());
		rs = pstmt.executeQuery();
		while(rs.next()) {
			HashMap<String, Object>	member = new HashMap<String, Object>();
			member.put("tc",rs.getString("tc"));
			member.put("OrderG",rs.getString("OrderG"));
			member.put("today",rs.getString("today"));
			System.out.println("orderList:(put)"+ member );
			li.add(member);
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return li;
	}

	@Override
	public List<HashMap<String, Object>> orderDetails(OrderVO vo) {
		System.out.println("===> orderList:"+ vo );
		List<HashMap<String, Object>> li =new ArrayList<HashMap<String, Object>>();
		try {
		conn = DBConn.getConnection();
		
		String SQL = "select  m.mid as mid,    mphone,  maddr1,  maddr2,  maddr3, mname, "
				+ "p.pid as pid , pname,  pprice,  pbaesongbi,  pdesc,   pimg , "
				+ "  orderG, amount, today  "
				+ "  from orderT o join member m  "
				+ "  on o.mid = m.mid  join product p  on p.pid = o.pid  "
				+ "  where orderG = ?  " ;
		
		pstmt  = conn.prepareStatement(SQL);
		pstmt.setString(1, vo.getOrderG());
		rs = pstmt.executeQuery();
		while(rs.next()) {
			HashMap<String, Object>	details = new HashMap<String, Object>();
			details.put("mid",rs.getString("mid"));
			details.put("mname",rs.getString("mname"));
			details.put("maddr",rs.getString("maddr1") +"-" + rs.getString("maddr2") +"-" +rs.getString("maddr3") );
			details.put("mphone",rs.getString("mphone"));
			details.put("pid",rs.getString("pid"));
			details.put("pname",rs.getString("pname"));
			details.put("pprice",rs.getString("pprice"));
			details.put("pbaesongbi",rs.getString("pbaesongbi"));
			details.put("pimg",rs.getString("pimg"));
			details.put("orderG",rs.getString("orderG"));
			details.put("amount",rs.getString("amount"));
			details.put("today",rs.getString("today"));
            System.out.println("===> details:" + details);
            
			li.add(details);
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return li;
	}


}
