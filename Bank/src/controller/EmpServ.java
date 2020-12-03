package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.MyRequest;


@WebServlet("/EmpServ")
public class EmpServ extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String strUrl = "http://192.168.0.79/bank/empList2.jsp";
		String result = MyRequest.get(strUrl); 
		
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().append(result);
	}

}
