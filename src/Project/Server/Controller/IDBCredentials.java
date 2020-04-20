package Project.Server.Controller;

public interface IDBCredentials {
	
	// JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost/appdatabase?serverTimezone=MST";

	   //  Database credentials
	   static final String USERNAME = "root";
	   static final String PASSWORD = "test1";

}
