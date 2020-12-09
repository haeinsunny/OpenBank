package mypet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PetDao {
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe"; // @뒤 : 포트
	private String user = "SUNNY";
	private String password = "1234";

	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;

	// 생성자 선언
	public PetDao() {
		try {
			Class.forName(driver); // Class.forName로 드라이버 로드
			conn = DriverManager.getConnection(url, user, password); // conn객체는 DriverManager를 통해서 보낼때(괄호안을)가져온다.
			System.out.println("DB연결 성공");

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	// 객체닫는처리
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

	private final String SELECT_ALL = "SELECT * FROM PET";

	// 전체조회
	public ArrayList<Pet> selectAll() {
			ArrayList<Pet> list = new ArrayList<Pet>();
			Pet vo;
			try {
				psmt = conn.prepareStatement(SELECT_ALL); // 실어보내는것
				rs = psmt.executeQuery(); // 보낸명령을 실행시켜달라
				while (rs.next()) {
					vo = new Pet();
					vo.setId(Integer.parseInt(rs.getString("id")));
					vo.setName(rs.getString("name"));
					vo.setAge(Integer.parseInt(rs.getString("age")));
					vo.setBreed(rs.getString("breed"));
					vo.setLocation(rs.getString("location"));
					
					list.add(vo);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally { // finally되면 닫아주는 프로그램 실행 (밑의 메소드 만들어서)
				close();
			}
			return list;
		}

}
