package emp.master;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class PersonCntrl
 */
@WebServlet("/emp/master/personCntrl")
public class PersonCntrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PersonCntrl() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String page= request.getParameter("page");
		String opr = request.getParameter("opr");
		int pageNo = (null==request.getParameter("pageNo")?0:Integer.parseInt(request.getParameter("pageNo")));
		int limit= (null==request.getParameter("pageNo")?0:Integer.parseInt(request.getParameter("limit")));
		
		RequestDispatcher rd;
		PersonDBService personDBService =new PersonDBService();
		Person person =new Person();
		//Action for close buttons
		String homeURL=(null==request.getSession().getAttribute("homeURL")?"":(String)request.getSession().getAttribute("homeURL"));		
		if(page.equals("personDashboard"))
		{
			request.getSession().setAttribute("homePage",page);
			homeURL="personCntrl?page="+page+"&opr=showAll&pageNo="+pageNo+"&limit="+limit;
			request.getSession().setAttribute("homeURL",homeURL);
			
			if(opr.equals("showAll")) 
			{
				ArrayList<Person> personList =new ArrayList<Person>();
				
				if(pageNo==0)
				personList = personDBService.getPersonList();
				else { //pagination
					int totalPages= personDBService.getTotalPages(limit);
					personList = personDBService.getPersonList(pageNo,limit);
					request.setAttribute("totalPages",totalPages);
				}
				request.setAttribute("personList",personList);
				rd = request.getRequestDispatcher("personDashboard.jsp");
				rd.forward(request, response);
			} 
			else if(opr.equals("addNew")) //CREATE
			{
				person.setDefaultValues();
				person.displayValues();
				request.setAttribute("person",person);
				rd = request.getRequestDispatcher("addNewPerson.jsp");
				rd.forward(request, response);
			}
			else if(opr.equals("edit")) //UPDATE
			{
				int id = Integer.parseInt(request.getParameter("id"));
				person = personDBService.getPerson(id);
				request.setAttribute("person",person);
				rd = request.getRequestDispatcher("updatePerson.jsp");
				rd.forward(request, response);
			}
			//Begin: modified by Dr PNH on 06-12-2022
			else if(opr.equals("editNext")) //Save and Next
			{
				int id = Integer.parseInt(request.getParameter("id"));
				person = personDBService.getPerson(id);
				request.setAttribute("person",person);
				rd = request.getRequestDispatcher("updateNextPerson.jsp");
				rd.forward(request, response);
			}
			else if(opr.equals("saveShowNext")) //Save, show & next
			{
				person.setDefaultValues();
				person.displayValues();
				request.setAttribute("person",person);
				
				ArrayList<Person> personList =new ArrayList<Person>();
				
				if(pageNo==0)
				personList = personDBService.getPersonList();
				else { //pagination
					int totalPages= personDBService.getTotalPages(limit);
					personList = personDBService.getPersonList(pageNo,limit);
					request.setAttribute("totalPages",totalPages);
				}
				request.setAttribute("personList",personList);
				rd = request.getRequestDispatcher("saveShowNextPerson.jsp");
				rd.forward(request, response);
			}
			//End: modified by Dr PNH on 06-12-2022
			else if(opr.equals("delete")) //DELETE
			{
				int id = Integer.parseInt(request.getParameter("id"));
				person.setId(id);
				personDBService.deletePerson(id);
				request.setAttribute("person",person);
				rd = request.getRequestDispatcher("deletePersonSuccess.jsp");
				rd.forward(request, response);
			}
			else if(opr.equals("view")) //READ
			{
				int id = Integer.parseInt(request.getParameter("id"));
				person = personDBService.getPerson(id);
				request.setAttribute("person",person);
				rd = request.getRequestDispatcher("viewPerson.jsp");
				rd.forward(request, response);
			}
			
		}
		else if(page.equals("addNewPerson")) 
		{
			if(opr.equals("save"))
			{
				person.setRequestParam(request);
				person.displayValues();
				personDBService.createPerson(person);
				request.setAttribute("person",person);
				if(pageNo!=0) {//pagination
					int totalPages= personDBService.getTotalPages(limit);
					homeURL="personCntrl?page=personDashboard&opr=showAll&pageNo="+totalPages+"&limit="+limit;
					request.getSession().setAttribute("homeURL", homeURL);
				}
				rd = request.getRequestDispatcher("addNewPersonSuccess.jsp");
				rd.forward(request, response);
			}
		}
		//Begin: modified by Dr PNH on 06-12-2022
		else if(page.equals("updateNextPerson")) 
		{
			if(opr.equals("update"))
			{
				person.setRequestParam(request);
				personDBService.updatePerson(person);
				request.setAttribute("person",person);
				request.getSession().setAttribute("msg", "Record saved successfully");
				response.sendRedirect("personCntrl?page=personDashboard&opr=editNext&pageNo=0&limit=0&id=10");			}
		}
		else if(page.equals("saveShowNextPerson")) 
		{
			request.getSession().setAttribute("homePage",page);
			homeURL="personCntrl?page=personDashboard&opr=saveShowNext&id=10&pageNo=0&limit=0";
			request.getSession().setAttribute("homeURL",homeURL);
			if(opr.equals("addNew")) //save new record
			{
				person.setRequestParam(request);
				person.displayValues();
				personDBService.createPerson(person);
				request.setAttribute("person",person);
				if(pageNo!=0) {//pagination
					int totalPages= personDBService.getTotalPages(limit);
					homeURL="personCntrl?page=personDashboard&opr=showAll&pageNo="+totalPages+"&limit="+limit;
					request.getSession().setAttribute("homeURL", homeURL);
				}
				request.getSession().setAttribute("msg", "Record saved successfully");
				response.sendRedirect("personCntrl?page=personDashboard&opr=saveShowNext&id=10&pageNo=0&limit=0");
				//rd = request.getRequestDispatcher("personCntrl?page=personDashboard&opr=saveShowNext&id=10&pageNo=0&limit=0");
				//rd.forward(request, response);
			}
			else if(opr.equals("edit"))
			{
				int id = Integer.parseInt(request.getParameter("id"));
				person = personDBService.getPerson(id);
				request.setAttribute("person",person);
				
				ArrayList<Person> personList =new ArrayList<Person>();
				if(pageNo==0)
				personList = personDBService.getPersonList();
				else { //pagination
					int totalPages= personDBService.getTotalPages(limit);
					personList = personDBService.getPersonList(pageNo,limit);
					request.setAttribute("totalPages",totalPages);
				}
				request.setAttribute("personList",personList);
				rd = request.getRequestDispatcher("saveShowNextPerson.jsp");
				rd.forward(request, response);
			}
			else if(opr.equals("update"))
			{
				person.setRequestParam(request);
				personDBService.updatePerson(person);
				request.setAttribute("person",person);
				request.getSession().setAttribute("msg", "Record updated successfully");
				response.sendRedirect("personCntrl?page=personDashboard&opr=saveShowNext&id=10&pageNo=0&limit=0");
			}
			else if(opr.equals("delete"))
			{
					int id = Integer.parseInt(request.getParameter("id"));
					person.setId(id);
					personDBService.deletePerson(id);
					request.setAttribute("person",person);
					request.getSession().setAttribute("msg", "Record deleted successfully");
					response.sendRedirect("personCntrl?page=personDashboard&opr=saveShowNext&id=10&pageNo=0&limit=0");		
			}
			else if(opr.equals("reset")||opr.equals("cancel"))
			{
					response.sendRedirect("personCntrl?page=personDashboard&opr=saveShowNext&id=10&pageNo=0&limit=0");		
			}
			
		}
		//End: modified by Dr PNH on 06-12-2022
		else if(page.equals("updatePerson")) 
		{
			if(opr.equals("update"))
			{
				person.setRequestParam(request);
				personDBService.updatePerson(person);
				request.setAttribute("person",person);
				rd = request.getRequestDispatcher("updatePersonSuccess.jsp");
				rd.forward(request, response);
			}
		}
		else if(page.equals("viewPerson")) 
		{
			if(opr.equals("print")) 
			{
				int id = Integer.parseInt(request.getParameter("id"));
				person = personDBService.getPerson(id);
				request.setAttribute("person",person);
				rd = request.getRequestDispatcher("printPerson.jsp");
				rd.forward(request, response);
			}
		}
		else if(page.equals("searchPerson"))
		{
			request.getSession().setAttribute("homePage",page);
			homeURL="personCntrl?page="+page+"&opr=showAll&pageNo="+pageNo+"&limit="+limit;
			request.getSession().setAttribute("homeURL",homeURL);
			if(opr.equals("search")) 
			{
				person.setRequestParam(request);
				person.displayValues();
				request.getSession().setAttribute("personSearch",person);
				request.setAttribute("opr","search");
				ArrayList<Person> personList =new ArrayList<Person>();
				if(pageNo==0)
				personList = personDBService.getPersonList(person);
				else { //pagination
					int totalPages=0;
					if(limit==0)
					totalPages=0;
					else
					totalPages=personDBService.getTotalPages(person,limit);
					pageNo=1;
					personList = personDBService.getPersonList(person,pageNo,limit);
					request.setAttribute("totalPages",totalPages);
				}
				request.setAttribute("personList",personList);
				rd = request.getRequestDispatcher("searchPerson.jsp?pageNo="+pageNo+"&limit="+limit);
				rd.forward(request, response);
			}
//begin:code for download/print button
			else if(opr.equals("downloadPrint")) 
			{
				person.setRequestParam(request);
				person.displayValues();
				request.getSession().setAttribute("personSearch",person);
				request.setAttribute("opr","person");
				ArrayList<Person> personList =new ArrayList<Person>();
				if(pageNo==0)
				personList = personDBService.getPersonList(person);
				else { //pagination
					int totalPages=0;
					if(limit==0)
					totalPages=0;
					else
					totalPages=personDBService.getTotalPages(person,limit);
					pageNo=1;
					personList = personDBService.getPersonList(person,pageNo,limit);
					request.setAttribute("totalPages",totalPages);
				}
				request.setAttribute("personList",personList);
				rd = request.getRequestDispatcher("searchPersonDownloadPrint.jsp?pageNo="+pageNo+"&limit="+limit);
				rd.forward(request, response);
			}
			//end:code for download/print button
			
			else if(opr.equals("showAll")) 
			{
				person=(Person)request.getSession().getAttribute("personSearch");
				ArrayList<Person> personList =new ArrayList<Person>();
				if(pageNo==0)
				personList = personDBService.getPersonList(person);
				else { //pagination
					int totalPages= personDBService.getTotalPages(person,limit);
					personList = personDBService.getPersonList(person,pageNo,limit);
					request.setAttribute("totalPages",totalPages);
				}
				request.setAttribute("personList",personList);
				rd = request.getRequestDispatcher("searchPerson.jsp?pageNo="+pageNo+"&limit="+limit);
				rd.forward(request, response);
			}
			else if(opr.equals("searchNext")||opr.equals("searchPrev")||opr.equals("searchFirst")||opr.equals("searchLast")) 
			{
				request.setAttribute("opr","search");
				person=(Person)request.getSession().getAttribute("personSearch");
				ArrayList<Person> personList =new ArrayList<Person>();
				if(pageNo==0)
				personList = personDBService.getPersonList(person);
				else { //pagination
					int totalPages= personDBService.getTotalPages(person,limit);
					personList = personDBService.getPersonList(person,pageNo,limit);
					request.setAttribute("totalPages",totalPages);
				}
				request.setAttribute("personList",personList);
				rd = request.getRequestDispatcher("searchPerson.jsp?pageNo="+pageNo+"&limit="+limit);
				rd.forward(request, response);
			}
			else if(opr.equals("showNone"))
			{
				person.setDefaultValues();
				person.displayValues();
				request.getSession().setAttribute("personSearch",person);
				request.setAttribute("opr","showNone");
				rd = request.getRequestDispatcher("searchPerson.jsp?pageNo="+pageNo+"&limit="+limit);
				rd.forward(request, response);
			}
			else if(opr.equals("edit")) 
			{
				int id = Integer.parseInt(request.getParameter("id"));
				person = personDBService.getPerson(id);
				request.setAttribute("person",person);
				rd = request.getRequestDispatcher("updatePerson.jsp");
				rd.forward(request, response);
			}
			else if(opr.equals("delete")) 
			{
				int id = Integer.parseInt(request.getParameter("id"));
				person.setId(id);
				personDBService.deletePerson(id);
				request.setAttribute("person",person);
				rd = request.getRequestDispatcher("deletePersonSuccess.jsp");
				rd.forward(request, response);
			}
			else if(opr.equals("view")) 
			{
 			int id = Integer.parseInt(request.getParameter("id"));
				person = personDBService.getPerson(id);
				request.setAttribute("person",person);
				rd = request.getRequestDispatcher("viewPerson.jsp");
				rd.forward(request, response);
			}
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public static void main(String[] args) throws URISyntaxException {
		URI uri = new URI("page=updatePerson&opr=close&homePage=personDashboard");
		String v = uri.getQuery();
		
	}
}
