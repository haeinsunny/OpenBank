package bank;

import com.google.gson.Gson;

import common.MyRequest;
import net.sf.json.JSONObject;

public class EmpRequest2 {

	public static void main(String[] args) { //

		String strUrl = "http://192.168.0.79/bank/empList2.jsp";
		String response = MyRequest.get(strUrl); // MyRequest클래스 통해서 위의 URL이 가지고있는 데이터를 가져온다.
		
		//gson 방법
		Gson gson = new Gson();
		Emp emp = gson.fromJson(response, Emp.class); // 배열로 바로 파싱
		System.out.println(emp.name); //다른패키지에 vo가 존재하는경우 emp.getName()으로 값을 가져온다.

		
		//json_lib 방법: 결과를 자바객체로 바로 파싱
		JSONObject obj = JSONObject.fromObject(response);	
		System.out.println("========json_lib========");
		System.out.println(obj.getString("name"));	
		System.out.println(obj.getString("age"));
	}

}
