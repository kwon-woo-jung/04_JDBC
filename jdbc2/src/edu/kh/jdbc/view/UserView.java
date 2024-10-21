package edu.kh.jdbc.view;

import edu.kh.jdbc.service.UserService;

public class UserView {
	
	// 필드
	private Scanner sc = new Scanner(System.in);
	private UserService service = new UserService();
	
	
	
	/**
	 * JDBCTemplate 사용 테스트
	 */
	
	public void test() {
		
		// 입력된 ID와 일치하는 USER 정보 조회
		System.out.print("ID 입력 : ");
		String input = sc.newxLine();
		
		// 서비스 호출 후 결과 반환 받기
		User user = service.selectId(input);
		
		// 결과출력
		System.out.println(user);
		
	}
	
	
	
	
	
}
