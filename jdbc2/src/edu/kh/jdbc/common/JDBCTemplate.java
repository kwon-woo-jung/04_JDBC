package edu.kh.jdbc.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;



/*	Template : 양식, 틀, 주형
 * -> "미리 만들어뒸다" 의미
 * 
 * 
 * JDBCTemplate :
 * 	JDBC 관련 작업을 위한 코드를
 * 	미리 작성해서 제공하는 클래스
 * 	
 * 	
 * - Connection 생성
 * - AutoCommit false
 * - commit / rollback
 * - 각종 close() 
 * 
 * 
 * ****** 중요 **********
 * 어디서든 JDBCTemplate 클래스를
 * 객체로 만들지 않고도 메서드를 사용할 수 있도록 하기 위해
 * 모든 메서드를 public static 으로 선언
 * 
 * 
 * 
 * */
public class JDBCTemplate {

	// 필드
	private Connection conn = null;
	// -> static 메서드에서 사용 가능한 필드로 static 필드 선언
	
	// 메서드
	
	/**
	 * 호출 시 Connection 객체를 생성해서 반환하는 메서드 + AutoCommit 끄기
	 * @return conn
	 */
	
	public static Connection getConnection() {
		
		try {
			
			if(conn != null && !conn.isClosed()) {
				return conn;
			}
				
			/* driver.xml 파일 내용 읽어오기
			 * 
			 * 이유 1 : 보안상의 이유 (Github에 DB연결 정보 등 올리면 해킹하라는 뜻..)
			 * 	  --> .gitignore 파일에 driver.xml 작성하여 git 관리 X
			 * 
			 * 이유 2 : 혹시라도 DB 연결 정보가 변경될 경우
			 * 		   Java코드가 아닌
			 * 		   읽어오는 파일의 내용을 수정하면 되기 때문에
			 * 		   Java 코드 수정 X -> 추가 재컴파일 필요 X
			 *
			 * */
				
			// 1. Properties 객체 생성
			// -Map 의 자식 클래스
			// - K, V가 모두 String 타입
			// - xml 파일 입출력을 쉽게 할 수 있는 메서드 제공
			// Properties.storeToXml() -> xml 파일 만들기
			// Properties.loadFromXML() -> xml 파일 읽어오기
			
			Properties prop = new Properties();
			
			String filePath = "driver.xml";
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			conn = DriverManager.getConnection(url, userName, password);
			
			// 만들어진 Connection 에서 AutoCommit 끄기
			conn.serAutoCommit(false);
			
			
				
		} catch (Exception e) {
			System.out.println("커넥션 생성 중 예외 발생..");
			e.printStackTrace();
		}
		
		return null;	
		
	}
	
	
	
	// -----------------------------------------------------
	
	/** 전달 받은 커넥션에서 수행한 SQL을 COMMIT 하는 메서드
	 * @param conn
	 */
	public static void commit(Connection conn) {
		
		try {
			
		} catch (Exception e)
		
		if(conn != null && !conn.isClosed()) conn.commit();
		
	}
	/** 전달 받은 커넥션에서 수행한 SQL을 ROLLBACK 하는 메서드
	 * @param conn
	 * */
	
	public static void rollback(Connection conn) {
		
		try {
			if(rs != null && !rs.isClosed()) rs.close();
			
		} catch (Exception e) {
			e.printStackTrace
		}
	}
	
}
	// ----------------------------------------------------------
	
	/** 전달 받은 커넥션을 close(자원 반환) 하는 메서드
	 * @param conn
	 */
	public static void close(Connection conn) {
		
	}
	
	/** 전달 받은 Statment + PreparedStatment 둘 다 close()
	 * + 다형성 업캐스팅 적용
	 * -> PreparedStatement는 Statement 자식 
	 * @param stmt
	 */
	public static void close(Statement stmt) {
		
	}
	
	/** 전달 받은 ResultSet을 close() 하는 메서드
	 * @param rs 
	 */
	public static void close(ResultSet rs) {
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
