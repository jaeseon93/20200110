package co.jessie.countries;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
//DAO + Service
public abstract class CountryService { // abstract 추상클래스
	public Connection conn; // 연결하기위해 선언
	public PreparedStatement psmt;
	public ResultSet rs;
	
	private String driver;
	private String url;
	private String user;
	private String password;
	
	public CountryService() { // C +컨트롤+스페이스 생성자 단축키
		dbconfigulation();
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	private void dbconfigulation() {
		Properties properties = new Properties();
		Reader reader;
		try {
			reader = new FileReader("config/db.properties"); //("파일이름") 의 파일을 읽겠다. 파일의 위치는 최상위 위치에있어야한다.아니면 파일 위치경로적어줘야함.
			properties.load(reader); // 읽은 파일을 로드하겠다.
			driver = properties.getProperty("driver"); //("스트링값으로 적기")
			url = properties.getProperty("url");
			user = properties.getProperty("user");
			password = properties.getProperty("password");
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	//DAO
	public abstract List<CountryDto> allSelect(); // 추상메소드

	public abstract CountryDto select(String key) throws SQLException; // 함수원형만 지원할 것이기 때문(Object 타입......??클래스..? )

	public abstract int insert(CountryDto dto) throws SQLException;

	public abstract int update(CountryDto dto) throws SQLException;

	public abstract int delete(CountryDto dto) throws SQLException;

}
