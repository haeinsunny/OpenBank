package bank;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

import common.MyRequest;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class MovieRequest {

	public static void main(String[] args) {
		boxOffice(); //아래의 매소드 실행
		
		String response = boxOffice();  //결과라고 해준다.
		//string -> 자바객체
		Gson gson = new Gson();	
		MovieList list = gson.fromJson(response, MovieList.class);  //API 결과값을  MovieList.class에 담았는데, Gson을 통해 자바객체로 변환한다.
		
		//영화제목출력		
		ArrayList<Movie> mvlist = list.boxOfficeResult.dailyBoxOfficeList;
		
		//일반 for
		for(int i=0; i<mvlist.size(); i++) {  //배열은 size로 객체는 length.
			System.out.println(mvlist.get(i).movieNm);  //ArrayList(배열과 비슷하지만 다름)에서 꺼낼때는 "get(index):얘가 객체가 됨" ".필드명으로 값 꺼내기"
		}
		
		//확장for
		for(Movie m : mvlist) {  //자바에서는 JS의 of가 in인데, 자바에서는 in을 :로 표현한다.
			System.out.println(m.movieNm);  //m이 mvlist에서 뽑아온 한 배열(객체)가 된다. 바로 값 뽑으면 됨.
		}
		
		//json_lib 영화 이름뽑기
		System.out.println("===========json_lib===========");
		JSONObject obj = JSONObject.fromObject(response);
		JSONObject obj2 = obj.getJSONObject("boxOfficeResult");
		JSONArray arr = (JSONArray)obj2.get("dailyBoxOfficeList");	
		for(int i=0; i<arr.size(); i++) {
			JSONObject temp = arr.getJSONObject(i);	
			System.out.println(temp.getString("movieNm"));			
		}
	}

	public static String boxOffice() {
		StringBuilder sb = new StringBuilder(); // Stirng을 이어줄 빌더 만들고
		String strUrl = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=430156241533f1d058c603178cc3ca0e&targetDt=20201202";
		try {
			URL url = new URL(strUrl); // 1. URL객체 선언 자바패키지
			HttpURLConnection con = (HttpURLConnection) url.openConnection(); // 2. 서버연결 자바패키지, HttpURLConnection에 서버녕ㄴ결
																				// 정보 담아놓음
			if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				BufferedReader br = new BufferedReader( // 4. 버퍼리더 생성
						new InputStreamReader(con.getInputStream(), "utf-8")); // 3. 서버연결 후 InputStream():ajax로부터 결과
																				// 받아온것
				String line;
				while ((line = br.readLine()) != null) { // 5. 버퍼리더를 거친것을 sb에 담는다.
					sb.append(line).append("\n");
				}
				br.close(); // 버퍼닫고
				System.out.println("" + sb.toString());
			} else {
				System.out.println(con.getResponseMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sb.toString(); // 버퍼안의 스트링값 던져줌

	}
}
