package edu.kh.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class JDBCExample5 {

	public static void main(String[] args) {

		// 아이디, 비밀번호, 이름을 입력 받아
		// TB_USER 테이블에 삽입(INSERT) 하기
		
		// 1. JDBC 객체 참조 변수 선언
		Connection conn = null;
//		객체가 선언되어 데이터베이스와의 연결 및 SQL 실행을 담당합니다.
		
		/*
		 * java.sql.PreparedStatement (준비된 Statement)
		 * - SQL 중간에 ? (위치홀더, placeholder)를 작성하여
		 * ? 자리에 java 값을 대입할 준비가 되어있는 Statement
		 * 
		 * 장점 1 : SQL 작성이 간단해짐
		 * 장점 2 : ?에 값 대입 시 자료형에 맞는 형태의
		 * 		   리터럴 표기법으로 대입됨!
		 * 		   ex) String 대입 -> '값' (자동으로 '' 추가)
		 * 		   ex) int 대입	-> 값
		 * 장점 3 : 성능, 속도에서 우위를 가지고 있음
		 * 
		 * ** PreparedStatement는 Statement의 자식**
		 * 
		 * */
		
		PreparedStatement pstmt = null;
//		? 라는 placeholder를 사용해 실행 시점에 데이터를 바인딩할 수 있어, 코드 작성이 간단해지고 성능이 향상됩니다.
		
		
		// SELECT 가 아니기 때문에 ResultSet 필요 없음!
		
		try {
			
			// 2. DriverManager 를 이용해서 Connection 객체 생성
			Class.forName("oracle.jdbc.driver.OracleDriver");
//			드라이버를 로드합니다.
			
			String url = "jdbc:oracle:thin@localhost:1521:XE";
			String userName = "kh_cmh";
			String password = "kh1234";
			
			conn = DriverManager.getConnection(url, userName, password);
			// DriverManager를 통해 데이터베이스와 연결됩니다
			
			// 3. SQL 작성
			Scanner sc = new Scanner(System.in);
			
			System.out.print("아이디 입력 : ");
			String id = sc.nextLine();
			
			System.out.print("비밀번호 입력 : ");
			String pw = sc.nextLine();
			
			System.out.print("이름 입력 : ");
			String name = sc.nextLine();
			
			String sql = """
					INSERT INTO TB_USER
					VALUES(SEQ_USER_NO.NECTVAL, ?, ?, ?, DEFAULT)
					""";
//			SEQ_USER_NO.NEXTVAL : 시퀀스를 사용해 자동으로 유저번호를 생성.
//			SQL 문을 준비합니다.
//			? : 사죵자로부터 입력받은 값을 바인딩할 위치
			
			
			/*
			 * AutoCommit 끄기!
			 * 개발자가 트랜잭션을 마음대로 제어하기 위해서
			 * 
			 * 
			 * */
			conn.setAutoCommit(false);
//			자동 커밋을 비활성화하여 트랜잭션을 수동으로 제어합니다.
//			데이터베이스에 정상적으로 값을 삽입되었을 경우 conn.commit() 으로 커밋하고, 그렇지 않은 경우 롤백(rollback)합니다.
			
			
			// 4. preparedStatement 객체 생성
			//	-> 객체 생성과 동시에 SQL 이 담겨지게 됨
			//	-> 미리 ? (placeholder)에 값을 받을 준비를 해야되기 때문에..
			pstmt = conn.prepareStatement(sql);
			
			// 5. ? 위치홀더에 알맞은 값 대입
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			pstmt.setString(3, name);
			
			// pstmt.set자료형(?순서, )
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			pstmt.setString(3, name);
			// -> 여기까지 실행하면 SQL이 작성 완료된 상태!
			
			// 6. SQL(INSERT) 수행 후 결과(int) 반환 받기
			// executeQuery() : SELECT 수행, ResultSet 반환
			// executeUpdate() : DML 수행, 결과 행 개수(int) 반환
			//  -> 보통 DML 실패 시 0, 성공 시 0초과된 값이 반환된다
	
//			executeUpdate() 메서드를 사용해 SQL을 실행합니다. 이 메서드는
//			INSERT, UPDATE, DELETE 등 DML 문에 사용되며, 영향받은 행의 수를 반환합니다.
			
//			SQL이 성공적으로 실행되면 성공 메시지를 출력하고 커밋을 수행하며, 실패 시 롤백을 수행합니다.
			
			
			// PreparedStatement를 이용하여 SQL 실행 시
			// executeQuery() / executeUpdate() 매개변수 자리에 아무것도 없어야 한다!!!!
			int result = pstmt.executeUpdate(sql);
		
			// 7. result 값에 따른 결과 처리 + 트랜잭션 제어처리
			if(result > 0) { // INSERT 성공 시
				System.out.println(name + "님이 추가 되었습니다");
				conn.commit(); // COMMIT 수행 -> DB에 INSERT 영구 반영
				
			} else {
				System.out.println("추가 실패");
				conn.rollback(); // 실패 시 ROLLBACK
				
			}
			
		
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			
			try {
				// 8. 사용한 JDBC 객체 자원 반환
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
//			try~catch~finally 구조를 활용해서 예외를 처리하고, finally 블록에서 데이터베이스 연결을 닫습니다.
//			pstmt와 conn은 사용 후 반드시 닫아줘야 하며, 이를 통해 메모리 누수를 방지할 수 있습니다
			
			
			
			
			
		}
		
		
	}

}
