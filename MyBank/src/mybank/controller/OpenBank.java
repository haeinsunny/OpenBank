package mybank.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair; //쿼리문자열 인식하는 라이브러리(톰캣http lib)

import mybank.model.TransactionVo;

public class OpenBank {

	// 계좌등록확인 토큰발급받기
	public static String getAccessToken(String code) { // 매개변수로 임시인증code값 받아오기

		final String strUrl = "https://testapi.openbanking.or.kr/oauth/2.0/token"; // Host
		final List<NameValuePair> postParams = new ArrayList<NameValuePair>();
		// AccessToken을 발급받기위해 OpenBank로 넘겨줄 파라미터 값 ( pdf.17 요청메시지)
		postParams.add(new BasicNameValuePair("code", code)); // 로그인 과정중 얻은 code 값
		postParams.add(new BasicNameValuePair("client_id", "oHB1oHd7ysV92a70RmXFIkwv51Xvfcq8ZdcVYcnc")); // REST API KEY
		postParams.add(new BasicNameValuePair("client_secret", "QqrYOwnYgTVLudATON8KU5mY4TiXPDPrC4rB3xfO"));
		postParams.add(new BasicNameValuePair("redirect_uri", "http://localhost/MyBank/CallBack")); // 리다이렉트 URI
		postParams.add(new BasicNameValuePair("grant_type", "authorization_code"));

		StringBuilder sb = new StringBuilder();
		try {
			URL url = new URL(strUrl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST"); // post방식으로 넘겨준다
			con.setDoInput(true); //
			con.setDoOutput(true); //
			con.addRequestProperty("Content-type", "application/x-www-form-urlencoded; charset=UTF-8");

			OutputStream os = con.getOutputStream(); // con으로부터 getOutputStream()을 읽어와서
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
			writer.write(getQuery(postParams)); // 쿼리문자열
			writer.flush();
			writer.close();
			os.close();

			// JSON 형태 반환값 처리
			if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
				String line;
				while ((line = br.readLine()) != null) {
					sb.append(line).append("\n");
				}
				br.close();
				System.out.println("" + sb.toString());
			} else {
				System.out.println(con.getResponseMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}

		return sb.toString();
	}

	// 모든계좌조회 토큰발급받기
	public static String getAccountList(String user_seq_no, String access_token) { // 매개변수로 임시인증code값 받아오기

		final List<NameValuePair> postParams = new ArrayList<NameValuePair>();
		// AccessToken을 발급받기위해 OpenBank로 넘겨줄 파라미터 값 ( pdf.40 요청메시지)
		postParams.add(new BasicNameValuePair("user_seq_no", user_seq_no));
		postParams.add(new BasicNameValuePair("include_cancel_yn", "Y")); // 리다이렉트 URI
		postParams.add(new BasicNameValuePair("sort_order", "D"));

		StringBuilder sb = new StringBuilder();

		try {
			String querystr = getQuery(postParams);
			String strUrl = "https://testapi.openbanking.or.kr/v2.0/account/list" + "?" + querystr;

			URL url = new URL(strUrl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestProperty("Authorization", "Bearer " + access_token);

			// JSON 형태 반환값 처리
			if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
				String line;
				while ((line = br.readLine()) != null) {
					sb.append(line).append("\n");
				}
				br.close();
				System.out.println("" + sb.toString());
			} else {
				System.out.println(con.getResponseMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}

		return sb.toString();
	}

	// 거래내역조회 토큰발급받기
	public static String getTransactionList(TransactionVo vo) { // 매개변수로 갖고올값 많을때 vo객체 만들기
		StringBuilder sb = new StringBuilder();

		final List<NameValuePair> postParams = new ArrayList<NameValuePair>();
		// AccessToken을 발급받기위해 OpenBank로 넘겨줄 파라미터 값 ( pdf.40 요청메시지)
		postParams.add(new BasicNameValuePair("bank_tran_id", vo.getBank_tran_id()));
		postParams.add(new BasicNameValuePair("fintech_use_num", vo.getFintech_use_num())); // 리다이렉트 URI
		postParams.add(new BasicNameValuePair("inquiry_type", vo.getInquiry_type()));
		postParams.add(new BasicNameValuePair("inquiry_base", vo.getInquiry_base()));
		postParams.add(new BasicNameValuePair("from_date", vo.getFrom_date()));
		postParams.add(new BasicNameValuePair("to_date", vo.getTo_date()));
		postParams.add(new BasicNameValuePair("sort_order", vo.getSort_order()));
		postParams.add(new BasicNameValuePair("tran_dtime", vo.getTran_dtime()));

		try {
			String querystr = getQuery(postParams);
			String strUrl = "https://testapi.openbanking.or.kr/v2.0/account/transaction_list/fin_num" + "?" + querystr;

			URL url = new URL(strUrl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestProperty("Authorization", "Bearer " + vo.getAccess_token());

			// JSON 형태 반환값 처리
			if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
				String line;
				while ((line = br.readLine()) != null) {
					sb.append(line).append("\n");
				}
				br.close();
				System.out.println("" + sb.toString());
			} else {
				System.out.println(con.getResponseMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}

		return sb.toString();
	}

	private static String getQuery(List<NameValuePair> params) throws UnsupportedEncodingException {
		StringBuilder result = new StringBuilder();
		boolean first = true;

		for (NameValuePair pair : params) {
			if (first)
				first = false;
			else
				result.append("&");

			result.append(URLEncoder.encode(pair.getName(), "UTF-8"));
			result.append("=");
			result.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
		}

		return result.toString();
	}
}
