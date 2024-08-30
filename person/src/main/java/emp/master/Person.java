package emp.master;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class Person {
 
int id;
String name;
String city;

public int getId()
{
return id;
}
public void setId(int id)
{
this.id=id;
}
public String getName()
{
return name;
}
public void setName(String name)
{
this.name=name;
}
public String getCity()
{
return city;
}
public void setCity(String city)
{
this.city=city;
}


public void setRequestParam(HttpServletRequest request) {

this.setId(null!=request.getParameter("id")&&!request.getParameter("id").equals("")?Integer.parseInt((String)request.getParameter("id")):0);
this.setName(null!=request.getParameter("name")?request.getParameter("name"):"");
this.setCity(null!=request.getParameter("city")?request.getParameter("city"):"");

}

public void displayReqParam(HttpServletRequest request) {


System.out.println("------Begin:Request Param Values---------");
System.out.println("id = "+request.getParameter("id"));
System.out.println("name = "+request.getParameter("name"));
System.out.println("city = "+request.getParameter("city"));

System.out.println("------End:Request Param Values---------");
}

public void displayValues() {

System.out.println("Id = "+this.getId());
System.out.println("Name = "+this.getName());
System.out.println("City = "+this.getCity());

}

public void setDefaultValues() {

this.setName("");
this.setCity("");

}
}