package mybank.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

@WebServlet("/CallBack")
public class CallBack extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public CallBack() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("code");	//code: 인증하고나면 OpenBank에서 발급해주는 임시토큰
		System.out.println("code");
		
		//accessToken 발급받기
		String result = OpenBank.getAccessToken(code);	
		
		JSONObject obj = JSONObject.fromObject(result);
		String access_token = obj.getString("access_token");
		String refresh_token = obj.getString("refresh_token");	
		String user_seq_no = obj.getString("user_seq_no");
		
		//authAccount.jsp페이지에 표현하기위해
		request.getSession().setAttribute("access_token", access_token);
		request.getSession().setAttribute("refresh_token", refresh_token);
		request.getSession().setAttribute("user_seq_no", user_seq_no);
		 
		//발급받은 토큰 DB에 저장(accessToken, refreshToken, user_no)
		request.getRequestDispatcher("/authAccount.jsp")
				.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
