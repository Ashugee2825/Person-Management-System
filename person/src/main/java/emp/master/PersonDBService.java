package emp.master;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
public class PersonDBService {
	Connection con;
	
	
	public PersonDBService()
	{
		DBConnectionDTO conDTO = new DBConnectionDTO();
		con=conDTO.getConnection();
	}
	
	public void closeConnection()
	{
		try {
			con.close();
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
public int getTotalPages(int limit)
	{
		String query="select count(*) from person";
	    int totalRecords=0;
	    int totalPages=0;
		try {
		Statement stmt = con.createStatement();
	    ResultSet rs = stmt.executeQuery(query);
	    if(rs.next()) {
	    	totalRecords= rs.getInt(1);
	    }
	    stmt.close();
	    rs.close();
		}
		catch (Exception e) {
			System.out.println(e);
		}
		totalPages=totalRecords/limit;
		if(totalRecords%limit!=0)
		{
			totalPages+=1;
		}
		return totalPages;
	}
	
	//pagination
	public int getTotalPages(Person person,int limit)
	{
		String query=getDynamicQuery2(person);
		int totalRecords=0;
	    int totalPages=0;
		try {
		Statement stmt = con.createStatement();
	    ResultSet rs = stmt.executeQuery(query);
	    if(rs.next()) {
	    	totalRecords= rs.getInt(1);
	    }
	    stmt.close();
	    rs.close();
		}
		catch (Exception e) {
			System.out.println(e);
		}
		totalPages=totalRecords/limit;
		if(totalRecords%limit!=0)
		{
			totalPages+=1;
		}
		return totalPages;
	}
	
	
	public int getPersonId(Person person)
	{
		int id=0;
		String query="select id from person";
String whereClause = " where "+ "name=? and city=?";
	    query+=whereClause;
		System.out.println(query);
		try {
PreparedStatement pstmt = con.prepareStatement(query);
pstmt.setString(1, person.getName());
pstmt.setString(2, person.getCity());
	    ResultSet rs = pstmt.executeQuery();
	    if(rs.next()) {
	       	id = rs.getInt("id");
	    }
		}
	    catch (Exception e) {
	    	System.out.println(e);
		}
	    
	    return id;
	}
	public void createPerson(Person person)
	{
		
String query="INSERT INTO person(name,city) VALUES(?,?)";
	
    System.out.println(query);
		try {
PreparedStatement pstmt = con.prepareStatement(query);
pstmt.setString(1, person.getName());
pstmt.setString(2, person.getCity());
	    int x = pstmt.executeUpdate();
	    }
	    catch (Exception e) {
	  
  	System.out.println(e);
		}
		int id = getPersonId(person);
		person.setId(id);
	}
	public void updatePerson(Person person)
	{
		
String query="update person set "+"name=?,city=? where id=?";
	   
 System.out.println(query);
		try {
PreparedStatement pstmt = con.prepareStatement(query);
pstmt.setString(1, person.getName());
pstmt.setString(2, person.getCity());
pstmt.setInt(3, person.getId());
	    int x = pstmt.executeUpdate();
	    }
	    catch (Exception e) {
	    	System.out.println(e);
		}
		
	}
	public String getValue(String code,String table) {
		
		String value="";
		String query="select value from "+table+" where code='"+code+"'";
	    System.out.println(query);
		try {
		Statement stmt = con.createStatement();
	    ResultSet rs = stmt.executeQuery(query);
	    if(rs.next()) {
	    	
	    	value=rs.getString("value");
	    }
		}
		catch (Exception e) {
			System.out.println(e);
		}
	    return value;
	}
	
	public Person getPerson(int id)
	{
		Person person =new Person();
		String query="select * from person where id="+id;
	    System.out.println(query);
		try {
		Statement stmt = con.createStatement();
	    ResultSet rs = stmt.executeQuery(query);
	    if(rs.next()) {
	    	
	
person.setId(rs.getInt("id")==0?0:rs.getInt("id"));
person.setName(rs.getString("name")==null?"":rs.getString("name"));
person.setCity(rs.getString("city")==null?"":rs.getString("city"));
	    	
	    }
		}
	    catch (Exception e) {
	    	System.out.println(e);
		}
	    
	    return person;
	}
	
	
	public ArrayList<Person> getPersonList()
	{
		ArrayList<Person> personList =new ArrayList<Person>();
		String query="select * from person";
	    System.out.println(query);
		try {
		Statement stmt = con.createStatement();
	    ResultSet rs = stmt.executeQuery(query);
	    while(rs.next()) {
	    	Person person =new Person();
person.setId(rs.getInt("id")==0?0:rs.getInt("id"));
person.setName(rs.getString("name")==null?"":rs.getString("name"));
person.setCity(rs.getString("city")==null?"":rs.getString("city"));
	    	personList.add(person);
	    }
		}
	    catch (Exception e) {
	    	System.out.println(e);
		}
	    
	    return personList;
	}
	
	public ArrayList<Person> getPersonList(int pageNo,int limit)
	{
		ArrayList<Person> personList =new ArrayList<Person>();
String query="select * from person limit "+limit +" offset "+limit*(pageNo-1);
	    System.out.println(query);
		try {
		Statement stmt = con.createStatement();
	    ResultSet rs = stmt.executeQuery(query);
	    while(rs.next()) {
	    	Person person =new Person();
person.setId(rs.getInt("id")==0?0:rs.getInt("id"));
person.setName(rs.getString("name")==null?"":rs.getString("name"));
person.setCity(rs.getString("city")==null?"":rs.getString("city"));
	    	personList.add(person);
	    }
		}
	    catch (Exception e) {
	    	System.out.println(e);
		}
	    
	    return personList;
	}
	
	public void deletePerson(int id) {
		
			String query="delete from person where id="+id;
		    System.out.println(query);
				
			
		    try {
			Statement stmt = con.createStatement();
		    int x = stmt.executeUpdate(query);
		    }
		    catch (Exception e) {
		    	System.out.println(e);
			}
		
	}
	
public String getDynamicQuery(Person person)
{
String query="select * from person ";
String whereClause="";
if(!whereClause.equals(""))
query+=" where "+whereClause;
System.out.println("Search Query= "+query);
    return query;
}
public String getDynamicQuery2(Person person)
{
String query="select count(*) from person ";
String whereClause="";
if(!whereClause.equals(""))
query+=" where "+whereClause;
System.out.println("Search Query= "+query);
    return query;
}
public ArrayList<Person> getPersonList(Person person)
{
ArrayList<Person> personList =new ArrayList<Person>();
String query=getDynamicQuery(person);
System.out.println("Search Query= "+query);
try {
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery(query);
while(rs.next()) {
Person person2 =new Person();
person2.setId(rs.getInt("id")==0?0:rs.getInt("id"));
person2.setName(rs.getString("name")==null?"":rs.getString("name"));
person2.setCity(rs.getString("city")==null?"":rs.getString("city"));
    	personList.add(person2);
    }
	}
    catch (Exception e) {
    	System.out.println(e);
	}
    return personList;
}
	
public ArrayList<Person> getPersonList(Person person,int pageNo,int limit)
{
ArrayList<Person> personList =new ArrayList<Person>();
String query=getDynamicQuery(person);
query+= " limit "+limit +" offset "+limit*(pageNo-1);
System.out.println("Search Query= "+query);
try {
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery(query);
while(rs.next()) {
Person person2 =new Person();
person2.setId(rs.getInt("id")==0?0:rs.getInt("id"));
person2.setName(rs.getString("name")==null?"":rs.getString("name"));
person2.setCity(rs.getString("city")==null?"":rs.getString("city"));
    	personList.add(person2);
    }
	}
    catch (Exception e) {
    	System.out.println(e);
	}
    return personList;
}
	
	
	public static void main(String[] args) {
		
		PersonDBService personDBService =new PersonDBService();
		
		
		
		 //Test-1 : Create Person
		  
		  Person person = new Person(); person.setDefaultValues();
		  personDBService.createPerson(person);
		  
		 ArrayList<Person> personList = personDBService.getPersonList();
		PersonService personService =new PersonService();
		personService.displayList(personList);
		
	}
}
