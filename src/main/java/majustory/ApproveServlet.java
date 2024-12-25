package majustory;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import cart.CartServiceImpl;
import cart.CartVO;
import cart.OrderVO;
/**
 * Servlet implementation class ApproveServlet
 */
public class ApproveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    private static final String ADMIN_KEY = "5832b3c34f0547288c5570bd08ec4454"; // Admin 키
    private static final String CID = "TC0ONETIME"; // 테스트 CID
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApproveServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String path = request.getContextPath();
		System.out.println("===> ApproveServlet 넘어옴 !!" );
		
        String pgToken = request.getParameter("pg_token");
        
        String tid = (String) request.getSession().getAttribute("tid");
        String partner_order_id = (String) request.getSession().getAttribute("partner_order_id");
        String partner_user_id = (String) request.getSession().getAttribute("partner_user_id");
        
        System.out.println("===> ApproveServlet pgToken 확인" +  pgToken);
        System.out.println("===> ApproveServlet tid 확인:" + tid );
        
        
        String apiUrl = "https://kapi.kakao.com/v1/payment/approve";
        URL url = new URL(apiUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "KakaoAK " + ADMIN_KEY);
        conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        conn.setDoOutput(true);

        System.out.println("===>ApproveServlet conn 확인: " + conn );
        
        String params = "cid=" + CID +
                        "&tid=" + tid +
                        "&partner_order_id=" + partner_order_id +
                        "&partner_user_id=" + partner_user_id +
                        "&pg_token=" + pgToken;

        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = params.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int code = conn.getResponseCode();
        Scanner sc;
        if (code == 200) {
            sc = new Scanner(conn.getInputStream());
            System.out.println("===>ApproveServlet sc 성공 확인: " + sc );
        } else {
            sc = new Scanner(conn.getErrorStream());
            System.out.println("===>ApproveServlet sc 실패 확인: " + sc );
        }
        
        StringBuilder result = new StringBuilder();
        while (sc.hasNext()) {
            result.append(sc.nextLine());
        }
            

        OrderVO  cvo =  new OrderVO();
        cvo.setMid(partner_user_id);
        System.out.println("===> (1)" + cvo) ;
        CartServiceImpl service = new CartServiceImpl();
        List<HashMap<String, Object>>  li =  service.cartSelect(cvo);
        System.out.println("===> (2)" + li.size()) ;
        for (HashMap<String, Object> member : li) {
        	
			OrderVO vo = new OrderVO();
			vo.setOrderG(partner_order_id);
			vo.setCart_id(Integer.parseInt(String.valueOf(member.get("cart_id"))));
			vo.setMid(String.valueOf(member.get("mid")));
			vo.setPid(String.valueOf(member.get("pid")));
			vo.setAmount(Integer.parseInt(String.valueOf(member.get("amount"))));
			
			service.orderInsert(vo);
        }
        
        CartVO  vo =  new CartVO();
        vo.setMid(partner_user_id);
        service.delete(vo);
        
        OrderVO ovo = new OrderVO();
        ovo.setMid(partner_user_id);
        request.setAttribute("li", service.orderList(ovo));	
        
		RequestDispatcher	dispatcher
		    =request.getRequestDispatcher("/cart/order_list.jsp");
		dispatcher.forward(request, response);	
        
	    sc.close();
		
        /*
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(result.toString());

        response.sendRedirect( path + "/Test1004/success.jsp");
        */
        
        
        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
