package edu.kh.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class JDBCExample6 {
	public static void main(String[] args) {
		
		// 아이디, 비밀번호, 이름을 입력받아
		// 아이디, 비밀번호가 일치하는 사용자(TB_USER)의
		// 이름을 수정(UPDATE)
		
		Connection conn = null;
		
		PreparedStatement pstmt = null;
		
		try {
			
			// Connection 생성
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:XE";
			String userName = "kh_cmh";
			String password = "kh1234";
			
			conn = DriverManager.getConnection(url, userName, password);
			
			// AutoCommit 끄기
			conn.setAutoCommit(false);
			
			// SQL 작성
			Scanner sc = new Scanner(System.in);
			
			System.out.print("아이디 입력 : ");
			String id = sc.nextLine();
			
			System.out.print("비밀번호 입력 : ");
			String pw = sc.nextLine();
			
			System.out.print("수정할 이름 입력 : ");
			String name = sc.nextLine();
			
			
			String sql = """
						UPDATE TB_USER SET
						USER_NAME = ?
						WHERE USER_ID = ?
						AND USER_PW = ?
					""";
			
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// ? 에 알맞은 값 세팅
			pstmt.setString(1, name);
			pstmt.setString(2, id);
			pstmt.setString(3, pw);
			// -> SQL 세팅 끝! -> 실행 결과 반환 받기
			
			int result = pstmt.executeUpdate();
			
			// 성공 시 "수정 성공" + COMMIT
			if(result > 0) {
				System.out.println("수정 성공!");
				conn.commit();
			}
			
			// 실패 시 "아이디 또는 비밀번호 불일치" + ROLLBACK
			else {
				System.out.println("아이디 또는 비밀번호 불일치");
				conn.rollback();
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		
	}
}