package vn.com.vuong.constant;

public class Config {
	public static class ConfigServer {
		public static String SERVER_IP = "localhost";
		public static int SERVER_PORT = 27017;
		public static int CONNECTION_PER_HOST = 1500;
	}
	
	public static class UserDB {
		public static String DATABASE_NAME = "admin";
		public static String USERNAME = "Nexiv";
		public static String PASSWORD = "100manUser";
	} 
	
	public static class DBConfig {
		public static String HOMETAG_OS = "hometagos";
	}
	
	public static class CollectionConfig {
		public static String COMPLEX = "complex";
	}
}
