package mybank.controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import mybank.model.AccountList;
import mybank.model.TransactionVo;


@WebServlet("/GetTransactionList")
public class GetTransactionList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public GetTransactionList() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//잔액조회
		TransactionVo vo = new TransactionVo();
		
		long lt = System.currentTimeMillis();	
		String str = Long.toString(lt).substring(4, 13);
		
		LocalDate ld = LocalDate.now();
		ld.minusMonths(1);
		
		//request.getParameter("")
		vo.setAccess_token("");
		vo.setBank_tran_id("T991676640U" + str); //sys.currentmil() 뒤 9자리 잘라서 사용
		vo.setFintech_use_num("");
		vo.setInquiry_type("A");
		vo.setInquiry_base("");
		vo.setFrom_date(ld.minusMonths(1));  //조회시작일자: 오늘날짜 -한 달
		vo.setTo_date("");  //오늘날짜
		vo.setSort_order("");
		vo.setTran_dtime("");  //현재일시 yyyymmddhhmiss
		
		String result = OpenBank.getTransactionList(vo);  //거래내역
		
		//result를 list<Transaction>에 request에 속성 추가한 다음에 
		request.setAttribute("list", result);  //받아온 거래내역을 담아줌	
		//view페이지로 전달
		request.getRequestDispatcher("/transaction.jsp")
		.forward(request, response);
		
		
		
	}



}
