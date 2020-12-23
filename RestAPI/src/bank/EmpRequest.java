package bank;

import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

import common.MyRequest;

public class EmpRequest {

	public static void main(String[] args) {
//		 //예제1 []
//		String strUrl ="http://192.168.0.79/bank/empList.jsp";
//		String response = MyRequest.get(strUrl);  //MyRequest클래스 통해서 위의  URL이 가지고있는 데이터를 가져온다.
//				
//		Gson gson = new Gson();	
//		EmpList list = gson.fromJson(response, EmpList.class);
//		
//		List<Emp> elist = list.empList;
//		
//		for(Emp e : elist) {
//			System.out.println(e.name);
//		}
//
//		// 예제2 {}: 대괄, 중괄인지 확인. 중괄만 있어서 배열은 아니고 데이터만 존재한다. 그래서 for문 필요없음.
//		String strUrl ="http://192.168.0.79/bank/empList2.jsp";
//		String response = MyRequest.get(strUrl);  //MyRequest클래스 통해서 위의  URL이 가지고있는 데이터를 가져온다.
//				
//		Gson gson = new Gson();	
//		Emp emp = gson.fromJson(response, Emp.class);
//		
//		System.out.println(emp.addr);

		// 예제3
		String strUrl = "http://192.168.0.79/bank/empList3.jsp";
		String response = MyRequest.get(strUrl); // MyRequest클래스 통해서 위의 URL이 가지고있는 데이터를 가져온다.

		Gson gson = new Gson();
		Emp[] emp = gson.fromJson(response, Emp[].class); // 배열로 바로 파싱
		List<Emp> empList = Arrays.asList(emp); // 배열을 List로 바꿔주는 메소드

		for (Emp e : empList) {
			System.out.println(e.addr);
		}
		
		//배열로만 하는경우
//		Emp[] emp = gson.fromJson(response, Emp[].class); 
//		
//		for(Emp e : emp) {
//		System.out.println(e.addr);
//		}

	}

}
