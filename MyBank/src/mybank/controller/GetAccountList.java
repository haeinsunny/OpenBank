package mybank.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import mybank.model.Account;
import mybank.model.AccountList;


@WebServlet("/GetAccountList")
public class GetAccountList extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public GetAccountList() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user_seq_no = "1100766736";
		String access_token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiIxMTAwNzY2NzM2Iiwic2NvcGUiOlsiaW5xdWlyeSIsImxvZ2luIiwidHJhbnNmZXIiXSwiaXNzIjoiaHR0cHM6Ly93d3cub3BlbmJhbmtpbmcub3Iua3IiLCJleHAiOjE2MTQ4MzU5ODUsImp0aSI6Ijk5ZDIwNjkxLWQ1YWYtNGU0MS1iYjMzLWE3ZjcxOTc3ZGIzNCJ9.RB0ZII888w9ELFcHPpEe7un7nhqyaUFms-3Y25kur4U";
		String result = OpenBank.getAccountList(user_seq_no, access_token);
		
		Gson gson = new Gson();	
		AccountList alist = gson.fromJson(result, AccountList.class);  //string -> 자바객체
		request.setAttribute("account_list", alist.getRes_list());  //jsp에 "account_list"로 출력할거임
	
		request.getRequestDispatcher("/accountList.jsp")
		.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
