package bank;

import com.google.gson.Gson;

import common.MyRequest;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class EmpRequest3 {

	public static void main(String[] args) {
		String strUrl = "http://192.168.0.79/bank/empList.jsp";
		String response = MyRequest.get(strUrl); // MyRequest클래스 통해서 위의 URL이 가지고있는 데이터를 가져온다.
		
//		//gson 방법
//		Gson gson = new Gson();
//		Emp[] emp = gson.fromJson(response, Emp[].class);
//		
//		for (Emp e : emp) {
//		System.out.println(e.name); 
//		}
	
		//json_lib 방법: 결과를 자바객체로 바로 파싱
		System.out.println("========json_lib========");
//		//3일때
//		JSONArray arr = JSONArray.fromObject(response);	//Array(배열)을 파싱
//		
//		for(int i=0; i<arr.size(); i++) { //[JSONArray]안에 {JSONObject}있는 구조
//			JSONObject temp = arr.getJSONObject(i);  //배열 파싱한거 안에 있는 오브젝트 파싱
//			System.out.println(temp.getString("name"));
//		}
		
		JSONObject obj = JSONObject.fromObject(response);	
		JSONArray arr = (JSONArray)obj.get("empList");  //obj.getJSONArray("");
		for(int i=0; i<arr.size(); i++) {  //배열 개수만큼 for문 돌림
			JSONObject temp = arr.getJSONObject(i);	
			System.out.println(temp.getString("name"));
		}
		
	}
}
