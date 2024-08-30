package emp.master;

import java.util.ArrayList;

public class PersonService {

	public void displayList(ArrayList<Person> personList)
	{
		personList.forEach((person) -> print(person));
	}
	
	public void print(Person person)
	{
		person.displayValues();
	}
	
}
