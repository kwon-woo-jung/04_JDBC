package edu.kh.jdbc.service;

public class UserService {

// JDBC객체

// DAO 메서드 호출 후 결과 반환 받기
User user = daao.selectId(conn, input);
	


	/** User 등록 서비스
	 * @param user : 입력 받은 id,pw,name이 세팅된 객체
	 * @return 삽입 성공한 결과 행의 개수
	 */
	public int insertUser(User user) {
		
		// 1. 커넥션 생성
		Connection conn = JDBCTemplate.getConnetion();
		
		
		
		
		return 0;
		
	}


}
