package emp.master;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DropDownDBService {

Connection con;

	public DropDownDBService()
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
	
	
	public ArrayList<DropDownDTO> getList(String tableName)
	{
		
		ArrayList<DropDownDTO> list = new ArrayList<DropDownDTO>();
		
		String query="";
		query="select * from "+tableName;
		
		
		try{
			Statement st = con.createStatement();
			query+=" order by code";
			ResultSet rs = st.executeQuery(query);
			while(rs.next())
			{
				DropDownDTO dropDownDTO =new DropDownDTO();
				dropDownDTO.setId(rs.getInt("id"));
				dropDownDTO.setCode(rs.getString("code"));
				dropDownDTO.setValue(rs.getString("value"));
				list.add(dropDownDTO);
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
		return list;
		
	}
	
}
