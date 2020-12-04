package mybank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import mybank.model.Account;

public class UserAccountDao {
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe"; // @뒤 : 포트
	private String user = "SUNNY";
	private String password = "1234";

	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;
	
	// 생성자 선언
	public UserAccountDao() { 
		try {
			Class.forName(driver); // Class.forName로 드라이버 로드
			conn = DriverManager.getConnection(url, user, password); // conn객체는 DriverManager를 통해서 보낼때(괄호안을)가져온다.
			System.out.println("DB연결 성공");

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}
	
	//객체닫는처리
	private void close() {
		try {
			if (rs != null)
				rs.close(); // rs객체가 not null이면(열려있다면) 닫아주는 역할
			if (psmt != null)
				psmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}	
	private final String SELECT_ALL = "SELECT * FROM USER_ACCOUNT"; 
	private final String INSERT = "INSERT INTO USER_ACCOUNT VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	//전체조회
		public ArrayList<Account> selectAll() {
			ArrayList<Account> list = new ArrayList<Account>();
			Account vo;
			try {
				psmt = conn.prepareStatement(SELECT_ALL); // 실어보내는것
				rs = psmt.executeQuery(); // 보낸명령을 실행시켜달라
				while (rs.next()) {
					vo = new Account(); // 초기화하고
					vo.setUser_seq_no(rs.getString("USER_SEQ_NO")); // 값들을 가져와서
					vo.setAccount_num(rs.getString("ACCT_NO"));
					vo.setFintech_use_num(rs.getString("FINTECH_USE_NUM"));
					vo.setAccount_alias(rs.getString("ACCOUNT_ALIAS "));
					vo.setBank_code_std(rs.getString("BANK_CODE_STD"));
					vo.setBank_name(rs.getString("BANK_NAME"));
					vo.setAccount_num(rs.getString("ACCOUNT_NUM"));
					vo.setAccount_type(rs.getString("ACCOUNT_TYPE"));
					vo.setAccount_state(rs.getString("ACCOUNT_STATE"));
					vo.setInquiry_agree_yn(rs.getString("INQUIRY_AGREE_YN"));
					vo.setTransfer_agree_yn(rs.getString("TRANSFER_AGREE_YN"));
					list.add(vo); // 리스트에 담아라
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally { // finally되면 닫아주는 프로그램 실행 (밑의 메소드 만들어서)
				close();
			}
			return list;
		}
			//입력
			public int insert(Account vo) {
				int n = 0;	
				try {
					psmt = conn.prepareStatement(INSERT);
					psmt.setString(1, vo.getUser_seq_no());
					psmt.setString(2, vo.getAccount_num());
					psmt.setString(3, vo.getFintech_use_num());
					psmt.setString(4, vo.getAccount_alias());
					psmt.setString(5, vo.getBank_code_std());
					psmt.setString(6, vo.getBank_name());
					psmt.setString(7, vo.getAccount_num());
					psmt.setString(8, vo.getAccount_type());
					psmt.setString(9, vo.getAccount_state());
					psmt.setString(10, vo.getInquiry_agree_yn());
					psmt.setString(11, vo.getTransfer_agree_yn());
					n = psmt.executeUpdate(); // executeUpdate메소드는 건수를 돌려준다.
				} catch (SQLException e) { // SQLException하는 catch
					e.printStackTrace();
				} finally {
					close();
				}

				return n;
			}
	
}
