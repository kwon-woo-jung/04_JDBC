package edu.kh.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// ctrl + f11 실행
public class JDBCExample1 {
	
	public static void main(String[] args) {
	
		/*
		 * JDBC(Java DataBase Connectivity)
		 * 
		 * - Java에서 DB에 연결 할 수 있게 해주는
		 * Java API (Java에서 제공하는 코드)
		 * -> java.sql 패키지에 존재함
		 * 
		 * */
		
		// Java 코드를 이용해
		// EMPLOYEE 테이블에서
		// 사번, 이름, 부서코드, 직급코드, 급여, 입사일 조회 후
		// 이클립스 콘솔에 출력하기
		
		/* 1. JDBC 객체 참조용 변수 선언 */
		
		// java.sql.Connection
		// 특정 DBMS와 연결하기위한 정보를 저장한 객체
		// == DBever에서 사용하는 DB 연결과 같은 역할의 객체
		// 	  (DB 서버 주소, 포트번호, DB 이름, 계정명, 비밀번호)
		Connection conn = null;
		
		// java.sql.Statement
		// - 1) SQL을 java -> DB에 전달
		// - 2) DB에서 SQL 수행 결과를 반환 받아옴 (DB -> Java)
		Statement stmt = null;
		
		// java.sql.ResultSet
		// - SELECT 조회 결과를 저장하는 객체
		ResultSet rs = null;
//		ResultSet 을 만들어 준것은 조회 하겠단 뜻이다 바로 사번, 이름, 부서코드, 직급코드, 급여, 입사일 등등
		
		try {
		
		/* 2. DriverManager 객체를 이용해서 Connection 객체 생성하기*/
//		자바하서 DB하고 연결하려면 통로가 필요하다 그래서 DriverManager를 사용할 것이다
		
		// java.sql.DriverManager
//		- DB 연결 정보와 JDBC 드라이버를 이용해서
//		원하는 DB와 연결할 수 있는 Connection 객체를 생성하는 객체
		
		// 2-1 ) Oracle JDBC Driver 객체를 메모리에 로드 (적재) 하기 DriverManager 객체를 이용하려면? 
//		어떻게 로드하면 좋을까? 
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		// Class.forName("패키지명+클래스명");
		// - 해당 클래스를 읽어 메모리에 적재
		// -> JVM이 프로그램 동작에 사용할 객체를 생성하는 구문
		
		// oracle.jdbc.driver.OracleDriver
		// - Oracle DBMS 연결 시 필요한 코드가 담겨있는 클래스
		// Oracle 에서 만들어서 준 클래스
		
		// 2-2 ) JDBC 드라이브 적재하고 난뒤 그다음 DB 연결하는걸 두번쨰로 함
//		DB 연결 정보 작성
		String type = "jdbc:oracle:thin:@"; // 드라이브의 종류
		
		String host = "localhost"; // DB 서버 컴퓨터의 IP 또는 도메인 주소
//		현재 사용하고 있는 컴퓨터 니까 "localhost" 임
		
		String port = ":1521"; // 프로그램 연결을 위한 포트 번호
//		: 붙이는 이유는 DB에서 : 를 붙이기 때문이다 

		String dbName = ":XE"; // 우리가 사용하려는 DBMS 이름(eXpress Edition)
//		그렇다면 dbNAME 도 : 왜 필요하나? DBMS 이름(XE == eXpress Edition) 이기 때문이다
//		jdbx:oracle:thin:@localhost:1521:XE 
		
		String userName = "kh_cmh"; // 사용자 계정명
		
		String password = "kh1234"; // 계정 비밀번호
		
		
		// 2-3) DB 연결 정보와 DriverManager를 이용해서 Connection 객체 생성
		conn = DriverManager.getConnection(type + host + port + dbName,
									userName, 
									password );
				
		
		
		

		
		
//		내가 정보 만들어 줄게 정보 저장해줘
		
		
		
		/*
		 * JDBC를 참조해줄 객체를 만들어 줘야 함
		 * 
		 * 
		 * */

		 
		// Connection 객체가 잘 생성되었는지 확인 (객체 주소 반환)
		// == DB 연결 정보에 오타가 없는지 확인
		// 만약 DB 연결 정보가 잘못되거나 혹은 객체 생성에 문자가 생기면 SQLException 발생
		
		System.out.println(conn); // oracle.jdbc.driver.T4CConetion@397fbdb
		
		/* 3. SQL 작성 */
		// !! 주의사항 !!
		// -> JDBC 코드에서 SQL 작성 시
		// 세미콜론(;) 을 작성하면 안됨!!
		// 만약 DBABER 에서 처럼 ; 을 찍는다면 "SQL 명령어가 올바르게 종료되지 않았습니다 " 예외 발생 해버림
		
		
		// EMPLOYEE 테이블에서
		// 사번, 이름, 부서코드, 직급코드, 급여, 입사일 조회 후
		
		String sql = "SELECT "
				+ "EMP_ID, EMP_NAME, DEPT_CODE, JOB_CODE, SALARY, HIRE_DATE "
				+ "FROM EMPLOYEE";
		
		
		
		/* 4. Statement 객체 생성*/
		// java.sql.Statement
		
		stmt = conn.createStatement();
		// 연결된 DB에 SQL을 전달하고 결과를 반환 받을
		// Statement 객체를 생성
		
		/* 5. Statement 객체를 이용해서 SQL 수행 후 결과 반환 받기*/
		// 1) ResultSet Statment.executeQuery(sql);
		//	-> sql이 SELECT문일 때 결과로 java.sql.ResultSet 반환
		
		
		// 2) int Statment.executeUpdate(sql);
		// -> DML(INSERT, UPDATE, DELETE) 실행 메서드
//		 결과로 int 형 반환(삽입, 수정, 삭제된 행의 개수)
		
		rs = stmt.executeQuery(sql);
		
		/* 6. 조회 결과가 담겨있는 ResultSet 을
		 * 커서(Cursor)를 이용해 
		 * 1행 씩 접근해 각 행에 작성된 컬럼 값 얻어오기
		 * 
		 * */
		
		// rs.next() : 커서를 다음 행으로 이동 시킨 후
		//	 이동된 행에 값이 있으면 true, 없으면 false 반환
		//	 맨 처음 호출 시 1행 부터 시작
//		re.next()를 while 문 조건문으로 하려 함
		While(rs.next()) {
			// 200 선동일 D9 J1 800000 1990-02-06 00:00:00.000
			
			// rs.get자료형(컬럼명|| 순서);
			// - 현재 행에서 지정된 컬럼의 값을 얻어와 반환
			// -> 지정된 자료형 형태로 값이 반환됨
			// (자료형을 잘못 지정하면 예외 발생)
			
			// [java]		[db]
			// 자바의 String형 변수에 들어올수 있는 db 변수는 CHAR, VARCHAR2 이다	
			// int, long 		db 에서는 NUMBER (정수만 저장된 컬럼)
			// float, double 			NUMBER (정수 + 실수)
			// java.sql.Date			DATE
			String empId = rs.getString("EMP_ID");
			String empName = rs.getString("EMP_NAME");
			String deptCode = rs.getString("DEPT_CODE");
			String jobCode = rs.getString("JOB_CODE");
			int salary = rs.getInt("JOB_CODE");
			Date hireDate = rs.getDate("HIRE_DATE");
			
		}
		
		
		
		// SQL 자바 작성시 주의사항은? 공백을 주의 해야 한다! 만약
		
		/*"  SELECT EMP_ID, EMP_NAME, 
		 * + DEPT_CODE, JOB_CODE, SALARY,
		 * + HIRE_DATE FROM EMPLOYEE"; 등등 띄울 때 적절한 공백이 있어야 명령이 수행 됨
		 * 
		 * */
		
		
		} catch(ClassNotFoundException e) {
			
			System.out.println("해당 Class 를 찾을 수 없습니다.");
			e.printStackTrace();
			
		} catch(SQLException e) {
			// SQLException : DB 연결과 관련된 모든 예외의 최상위 부모
			e.printStackTrace();
		}
		
		
		
	}
	
}
