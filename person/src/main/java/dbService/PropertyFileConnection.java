package dbService;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Enumeration;
import java.util.ResourceBundle;
public class PropertyFileConnection {
 
Connection con;
String applicationName ,databaseName,dbUser, dbpassword,url,driver;
String db;
public static void closeConnection(Connection con)
{
	try{
		con.close();
	}
	catch(Exception e)
	{
		System.out.println("Exception while closing connection"+e);
	}
}
public String getDb() {
	return db;
}
public void setDb(String db) {
	this.db = db;
}
public String getUrl() {
	return url;
}
public void setUrl(String url) {
	this.url = url;
}
public String getDriver() {
	return driver;
}
public void setDriver(String driver) {
	this.driver = driver;
}
public String getApplicationName() {
	return applicationName;
}
public void setApplicationName(String applicationName) {
	this.applicationName = applicationName;
}
public String getDatabaseName() {
	return databaseName;
}
public void setDatabaseName(String databaseName) {
	this.databaseName = databaseName;
}
public String getDbUser() {
	return dbUser;
}
public void setDbUser(String dbUser) {
	this.dbUser = dbUser;
}
public String getDbpassword() {
	return dbpassword;
}
public void setDbpassword(String dbpassword) {
	this.dbpassword = dbpassword;
}
	
public Connection getConnection()
{
	this.readProperty();
	
    try{
      Class.forName(this.getDriver());
      con = DriverManager.getConnection(this.getUrl()+this.getDatabaseName(),this.getDbUser(),this.getDbpassword());
    }
    catch (Exception e){
	      e.printStackTrace();
	    }
    return con;
}
	
public void readProperty() {
	
	
	ResourceBundle labels = ResourceBundle.getBundle("dbService.config");
	Enumeration<String> bundleKeys = labels.getKeys();
	while (bundleKeys.hasMoreElements()) {
	    String key = (String)bundleKeys.nextElement();
	    String value = labels.getString(key);
	    this.setDatabaseName(labels.getString("database"));
	    this.setDbUser(labels.getString("dbuser"));
	    this.setDbpassword(labels.getString("dbpassword"));
	    this.setUrl(labels.getString("url"));
	    this.setDriver(labels.getString("driver"));
	    this.setDb(labels.getString("db"));
	}
	
}
}
