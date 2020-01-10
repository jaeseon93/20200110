package co.jessie.dowhile;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import co.jessie.countries.CountryDto;
import co.jessie.countries.CountryService;
import co.jessie.countries.CountryServiceImpl;

/* 1.직원관리 2.급여관리 3.부서관리 4.종료 
 * ==============================
 * 원하는작업을 입력하세요.
 * 1 = 1.직원조회 2.직원등록 3.직원갱신 4.직원삭제 5.돌아가기(메인메뉴로돌아가는기능)
 * 1-1.직원정보출력*/
public class Menu {
	Scanner sc = new Scanner(System.in);

	public void mainMenu() {
		int choice; // 숫자로 입력받을것이기때문에 int
		boolean b = true;

		do {
			System.out.println("==========MENU==========");
			System.out.println("       1.직원관리 ");
			System.out.println("       2.급여관리 ");
			System.out.println("       2.부서관리 ");
			System.out.println("       4.종      료 ");
			System.out.println("========================");
			System.out.println("원하는 메뉴번호를 입력하세요.");
			choice = sc.nextInt();
			sc.nextLine();

			switch (choice) {
			case 1:
				employeeMenu();
				break;
			case 2:
				salaryMenu();
				break;
			case 3:
				departmentMenu();
				break;
			case 4:
				b = false; // 실행문 빠져나감.
				System.out.println("프로그램이 종료 되었습니다.");
				break;
			}

		} while (b);
	}

	public void employeeMenu() {
		int choice; // 숫자로 입력받을것이기때문에 int
		boolean b = true;

		do {
			System.out.println("==========직원관리==========");
			System.out.println("       1.직원조회 ");
			System.out.println("       2.직원등록 ");
			System.out.println("       3.직원갱신 ");
			System.out.println("       4.직원삭제 ");
			System.out.println("       5.돌아가기 ");
			System.out.println("========================");
			System.out.println("원하는 메뉴번호를 입력하세요.");
			choice = sc.nextInt();
			sc.nextLine();
			CountryService service = new CountryServiceImpl();
			CountryDto dto = new CountryDto();
			switch (choice) {
			case 1:
				//국가 조회하는 화면을 연결해볼것임
				System.out.println("직원 조회하는 화면입니다.");
				List<CountryDto> list = new ArrayList<CountryDto>();
				list = service.allSelect();
				for(CountryDto dto1 : list) {
					System.out.print(dto1.getCountry_id() + " : ");
					System.out.print(dto1.getCountry_name() + " : ");
					System.out.println(dto1.getRegion_id());
				}
				break;
			case 2:
				System.out.println("직원 등록하는 화면입니다.");		
				System.out.println("등록할 직원 아이디를 입력해주세요.");
				dto.setCountry_id(sc.nextLine());
				System.out.println("등록할 직원 이름을 입력해주세요.");
				dto.setCountry_name(sc.nextLine());
				System.out.println("등록할 직원의 지역 아이디를 입력해주세요.");
				dto.setRegion_id(sc.nextInt());
				try {					
					service.insert(dto);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				System.out.println("정상적으로 직원이 등록되었습니다.");										
				break;
			case 3: //케이스안에 구문이 많아서 간단하게 하고싶으면 따로 메소드를 만들어서 메소드만호출한다.
				System.out.println("직원 갱신하는 화면입니다.");
				dto = new CountryDto(); //위에서 초기화한 dto가 case2에서 쓰여졌기때문에 다시한번더 초기화시켜서 case3에쓰기.
				System.out.println("갱신할 직원ID를 입력해주세요.");
				String key = sc.nextLine();
				try {					
					dto = service.select(key);
					System.out.println(dto.getCountry_id() + " : " +dto.getCountry_name() + " : " + dto.getRegion_id());
				} catch (SQLException e) {
					e.printStackTrace();
				}
				System.out.println("업데이트 할 직원ID를 입력하세요.");
				dto.setCountry_id(sc.nextLine());
				System.out.println("업데이트 할 직원 이름을 입력하세요.");
				dto.setCountry_name(sc.nextLine());
				System.out.println("업데이트 할 직원 지역 ID를 입력하세요.");
				dto.setRegion_id(sc.nextInt());			
				try {
					int n = service.update(dto);
					if (n != 0)
						System.out.println("정상적으로 업데이트 되었습니다.");
					else
						System.out.println("업데이트에 실패하였습니다.");
				} catch (SQLException e) {
					e.printStackTrace();
				}
				sc.close();
				
				break;
			case 4:
				dto = new CountryDto(); //초기화
				System.out.println("직원 삭제하는 화면입니다.");
				System.out.println("삭제 할 직원ID를 입력하세요.");
				String str = sc.nextLine();
				try {
					dto.setCountry_id(str);
					int n = service.delete(dto);
					if (n != 0)
						System.out.println("정상적으로 삭제 되었습니다.");
					else
						System.out.println("삭제 되지 않았습니다.");
				} catch (SQLException e) {
					e.printStackTrace();
				}			
				break;
			case 5:
				b = false; // 실행문 빠져나감.
				break;
			}

		} while (b);
	}

	public void salaryMenu() {
		int choice; // 숫자로 입력받을것이기때문에 int
		boolean b = true;

		do {
			System.out.println("==========급여관리==========");
			System.out.println("       1.급여조회 ");
			System.out.println("       2.급여등록 ");
			System.out.println("       2.급여변경 ");
			System.out.println("       4.급여삭제 ");
			System.out.println("       5.돌아가기 ");
			System.out.println("========================");
			System.out.println("원하는 메뉴번호를 입력하세요.");
			choice = sc.nextInt();
			sc.nextLine();

			switch (choice) {
			case 1:
				System.out.println("급여 조회하는 화면입니다.");
				break;
			case 2:
				System.out.println("급여 등록하는 화면입니다.");
				break;
			case 3:
				System.out.println("급여 변경하는 화면입니다.");
				break;
			case 4:
				System.out.println("급여 삭제하는 화면입니다.");
				break;
			case 5:
				b = false; // 실행문 빠져나감.
				break;
			}

		} while (b);
	}

	public void departmentMenu() {
		int choice; // 숫자로 입력받을것이기때문에 int
		boolean b = true;

		do {
			System.out.println("==========부서관리==========");
			System.out.println("       1.부서조회 ");
			System.out.println("       2.부서등록 ");
			System.out.println("       2.부서변경 ");
			System.out.println("       4.부서삭제 ");
			System.out.println("       5.돌아가기 ");
			System.out.println("========================");
			System.out.println("원하는 메뉴번호를 입력하세요.");
			choice = sc.nextInt();
			sc.nextLine();

			switch (choice) {
			case 1:
				System.out.println("부서 조회하는 화면입니다.");
				break;
			case 2:
				System.out.println("부서 등록하는 화면입니다.");
				break;
			case 3:
				System.out.println("부서 변경하는 화면입니다.");
				break;
			case 4:
				System.out.println("부서 삭제하는 화면입니다.");
				break;
			case 5:
				b = false; // 실행문 빠져나감.
				break;
			}

		} while (b);
	}

}
