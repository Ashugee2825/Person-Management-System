package emp.master;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Enumeration;
import java.util.ResourceBundle;

import dbService.PropertyFileConnection;

public class DBConnectionDTO {


Connection con;

String databaseName,dbUser, dbpassword,url,driver,db;


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
	//setdefaultDB();
	//readProperty();
    //try{
    //  Class.forName(this.getDriver());
    //  con = DriverManager.getConnection(this.getUrl()+this.getDatabaseName(),this.getDbUser(),this.getDbpassword());
      
   // }
   // catch (Exception e){
	 //     e.printStackTrace();
	  //  }

	PropertyFileConnection propertyFileConnection = new PropertyFileConnection();
	con=propertyFileConnection.getConnection();
    return con;
}

public  void readProperty() {
	
	ResourceBundle labels = ResourceBundle.getBundle("dbService.config");
	Enumeration<String> bundleKeys = labels.getKeys();

	while (bundleKeys.hasMoreElements()) {
	    String key = (String)bundleKeys.nextElement();
	    String value = labels.getString(key);
	    //System.out.println( key + " = " + value);
	    this.setDatabaseName(labels.getString("database"));
	    this.setDbUser(labels.getString("dbemp.master"));
	    this.setDbpassword(labels.getString("dbpassword"));
	    this.setUrl(labels.getString("url"));
	    this.setDriver(labels.getString("driver"));
	    this.setDb(labels.getString("db"));
	}

}

public void setdefaultDB()
{
	setDatabaseName("dbName");
	setDbUser("root");
	setUrl("jdbc:mysql://localhost:3306/");
	//setDriver("com.mysql.jdbc.Driver");//old driver
	setDriver("com.mysql.cj.jdbc.Driver");
	setDbpassword("dbPwd");
}

}
