package edu.kh.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Scanner;

public class JDBCExample2 {

	public static void main(String[] args) {
		

		// 입력 받은 급여보다 초과해서 받는 사원의
		// 사번, 이름, 급여 조회
		
		// 1. JDBC 객체 참조용 변수 선언
		Connection conn = null; // db 연결정보 저장하는 것
		Statemnet stmt = null; // SQL 수행하고 결과가 나오면 반환 까지 해줌
		ResultSet rs = null; // SELECT 수행 결과를 저장 객체
		
		try {
			// 2. DriverManager 객체를 이용해서 Connetion 객체 생성
			// 2-1) Oracle JDBC Driver 객체 메모리 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String type = "jdbc:oracle:thin:@"; // 드라이브의 종류
			String host = "localhost"; // DB 서버 컴퓨터의 IP 또는 도메인 주소
			String port = ":1521"; // 프로그램 연결을 위한 포트 번호
			String dbName = ":XE"; // 우리가 사용하려는 DBMS 이름(eXpress Edition)
			
			String userName = "kh_cmh"; // 사용자 계정명
			String password = "kh1234"; // 계정 비밀번호
			
			
			// 2-2) DB 연결 정보 작성
			
			// 2-3) DB 연결 정보와 DriverManager 를 이용해서 Connection 객체 생성
			conn = DriverManager.getConnection(type+host+port+dbName, userName, password);
			
//			System.out.println(conn); // oracle.jdbc.driver.T4CConnection@397fbdb
			
			// 3. SQL 작성 
			// 입력받은 급여 -> Scanner 필요함
			// 급여를 입력 받아서 int 형 변수인 input에 담을것 int input 급여 담기
			Scanner sc = new Scanner(System.in);
			
			System.out.print("급여 입력 : ");
			int input = sc.nextInt();
			
			String sql = "";
			
			
			
			// 4. Statment 객체 생성
			stmt = conn.createStatement(); // createStatement(); 만들 때 stmt 반드시 붙어야 함!!!
			
			// 5. Statement 객체를 이용하여 SQL을 수행 후 반환 받기
			// executeQuery() : SELECT 실행, ResultSet 반환
			// executeUpdate() : DML 실행, 결과 개수 반환(int)
			rs = stmt.extcuteQuery(sql);
			
			
			
			// 6. 조회 결과가 담겨있는 ResultSet 을
			// 커서 이용해 1행씩 접근해
			// 각 행에 작성된 컬럼값 얻어오기
			// -> while 안에서 꺼낸 데이터 출력
			
			// 201 / 송중기 / 600000원 
			// 202 / 노옹철 / 370000원 
			// 203 / 송은희 / 280000원 
			// ...
			while(rs.next()) {
				
			String empId = rs.getString("EMP_ID");
			String empName = rs.getString("EMP_NAME");
			int salary = rs.getInt("SALARY");
			
			System.out.printf("%s / %s / %d원 \n", 
						empId, empName, salary );
			}
			
			
			
			
			
			// 7. 사용 완료된 JDBC 객체 자원 반환(close)
			// -> 생성된 역순으로 close
			
		} catch (Exception e) {
			// 최상위 예외인 Exception을 이용해서 모든 예외를 처리해줄것임
			// -> 다형성 업캐스팅 적용
			e.printStackTrace();
		} finally {
			
			// 7. 사용 완료된 JDBC 객체 자원 반환(close)
			// -> 생성된 역순으로 close!
			
		}
		
	!
		
		
		
	}

	
	
	
}
