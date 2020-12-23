package common;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyRequest { //어디에서나 쓸수있도록 get메서드 공용클래스만듬
	
	public static String get(String strUrl) {  
		StringBuilder sb = new StringBuilder(); // Stirng을 이어줄 빌더 만들고
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
