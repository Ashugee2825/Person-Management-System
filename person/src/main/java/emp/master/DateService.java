package emp.master;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateService {

	
	public static boolean isDateEqual(Date d1,Date d2)
	{
		boolean dateEqual=false;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try{
		Date date1 = sdf.parse(DateService.getDTSYYYMMDDFormat(d1));
        Date date2 = sdf.parse(DateService.getDTSYYYMMDDFormat(d2));
        
        
/*        if (date1.compareTo(date2) > 0) {
            System.out.println("Date1 is after Date2");
        } else if (date1.compareTo(date2) < 0) {
            System.out.println("Date1 is before Date2");
        } else if (date1.compareTo(date2) == 0) {
            System.out.println("Date1 is equal to Date2");
        } else {
            System.out.println("How to get here?");
        }
*/        
        if (date1.compareTo(date2) == 0) {
        	dateEqual=true;
        }
        }
        catch(Exception e)
        {
        	System.out.println(e);
        }
        return dateEqual;
	}
	
	
	public static boolean isTime12PMto8PM()
	{
		boolean timeEqual=false;
		
		DateFormat dateFormat = new SimpleDateFormat("hh.mm aa");
    	String dateString = dateFormat.format(new Date()).toString();
    	
		String time = dateString.substring(0,5);
		String ampm = dateString.substring(6,8);
		//System.out.println("Current time in AM/PM: "+dateString);
		//System.out.println("Current time:"+time);
		//System.out.println("Current am pm :"+ampm);
		float timeValue = Float.parseFloat(time);
		System.out.println("timevalue="+timeValue);
		if(ampm.equals("PM") && timeValue>=12.01 && timeValue<=12.59)
		timeEqual=true;
		if(ampm.equals("PM") && timeValue>=01.00 && timeValue<=08.00)
		timeEqual=true;
		
		System.out.println(timeEqual);
		
		return timeEqual;
	}
	
	//
	public static Date getDefaultDtYYYYMMDD()
	{
		String sourceDate="1111-11-11";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		//SimpleDateFormat formatter = new SimpleDateFormat(sourceDate);
		Date date=null; 
        try {

            date = formatter.parse(sourceDate);
            //System.out.println(date);
            //System.out.println(formatter.format(date));

        } catch (ParseException e) {
            e.printStackTrace();
        }
		
		return date;
	}
	
	
	
	public static String getDTSYYYMMDDFormat(Date dt)
	{
	
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String s="";
		if(null==dt)
		{
			try{
				dt=formatter.parse("1111-11-11");
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
			
		s = formatter.format(dt);
		return s;
	}
	
	public static String getDTSDDMMYYYY(Date dt)
	{
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		if(null==dt)
		{
			try{
				dt=formatter.parse("11-11-1111");
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
		
		String s = formatter.format(dt);
		return s;
	}
	
	//
	public static Date getSTDYYYMMDDFormat(String sourceDate)
	{
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		//SimpleDateFormat formatter = new SimpleDateFormat(sourceDate);
		Date date=null; 
        try {

            date = formatter.parse(sourceDate);
            //System.out.println(date);
            //System.out.println(formatter.format(date));

        } catch (ParseException e) {
            e.printStackTrace();
        }
		
		return date;
	}

	public static Date getSTDYYYYMMDDFormat(String sourceDate)
	{
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		//SimpleDateFormat formatter = new SimpleDateFormat(sourceDate);
		Date date=null; 
        try {

            date = formatter.parse(sourceDate);
            //System.out.println(date);
            //System.out.println(formatter.format(date));

        } catch (ParseException e) {
            e.printStackTrace();
        }
		
		return date;
	}

	public static Date getDateToday()
	{
		Date mydate = Calendar.getInstance().getTime();
	    DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	    return mydate;
	}
	
	public static String getDateTime()
	{
		return DateService.getDateToday()+"_"+DateService.getTimeHHMMa(DateService.getDateToday());
	}
	
	 public static String getTimeHHMMa(Date dt)
	 {
		 SimpleDateFormat localDateFormat = new SimpleDateFormat("HH:mm a");
	     String time = localDateFormat.format(dt);
	     System.out.println(time);
	     return time;
	 }
	 
	public String getDateYYYMMDDFormat(String sourceDate)
	{
		String destDate="";
		if(sourceDate!=null && !sourceDate.equals("")){
			try{
				
					Date mydate = Calendar.getInstance().getTime();
				    DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
				    mydate=formatter.parse(sourceDate);
					
					formatter = new SimpleDateFormat("yyyy-MM-dd");
					destDate=formatter.format(mydate);
				
					
			}
			catch(Exception e){
				System.out.println("date format exception prakash"+e);
			}
			}
		return destDate;
	}
	
	public static Date getDTDYYYYMMDDFormat(Date dt)
	{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	    String s = df.format(dt);
	    
	    try {
	        dt=df.parse(s);
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
	    return dt;
	}
	
	
	public static Date getDTDMMDDYYYYFormat(Date dt)
	{
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
	    String s = df.format(dt);
	    
	    try {
	        dt=df.parse(s);
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
	    return dt;
	}
	
	public String getYYYMMDDString(Date sourceDate)
	{
		
		
		String destDateString="1111-11-11";
		
		if(sourceDate!=null && !sourceDate.equals("")){
			try{
				
					DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				    destDateString=formatter.format(sourceDate);
						
			}
			catch(Exception e){
				System.out.println("date format exception prakash"+e);
			}
			}
		return destDateString;
	}
	
	public String getDateMMDDYYYYFormat(String sourceDate)
	{
		String destDate="";
		if(sourceDate!=null && !sourceDate.equals("")){
			try{
				
					Date mydate = Calendar.getInstance().getTime();
				    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				    mydate=formatter.parse(sourceDate);
					
					formatter = new SimpleDateFormat("dd-MM-yyyy");
					destDate=formatter.format(mydate);
				
					
			}
			catch(Exception e){
				System.out.println("date format exception prakash"+e);
			}
			}
		return destDate;
	}
	
	public Date getYYYYMMDDDt(String sourceDate)
	{
		System.out.println("Source Date="+sourceDate);
		Date destDt=null;
		Date mydate = Calendar.getInstance().getTime();
		if(null==sourceDate || sourceDate.equals("")){
			
			DateFormat destFormat1 = new SimpleDateFormat("yyyy-MM-dd");
		    //convert mydate into destformat
		    String destDtString1 = destFormat1.format("1111-11-11");
		    //check dest format and convert to Date object
		    try{
		    destDt = destFormat1.parse(destDtString1);
		    }
		    catch (Exception e) {
		    	System.out.println("date format exception prakash"+e);
			}
		}
						
		else if(sourceDate!=null && !sourceDate.equals("")){
			try{
				
					DateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd");
				    //check source date format
					mydate=sourceFormat.parse(sourceDate);
				    DateFormat destFormat = new SimpleDateFormat("yyyy-MM-dd");
				    //convert mydate into destformat
				    String destDtString = destFormat.format(mydate);
				    //check dest format and convert to Date object
				    destDt = destFormat.parse(destDtString);
					
			}
			catch(Exception e){
				System.out.println("date format exception prakash"+e);
			}
			}
		
		System.out.println("Destination Date="+destDt);
		return destDt;
	}
	
	
	
	
	public Date getDDMMYYYYDt(String sourceDate)
	{
		String destDate="";
		Date destDt=null;
		DateFormat formatter1 = new SimpleDateFormat("yyyy-dd-MM");
		try{
		destDt=formatter1.parse("1111-11-11");
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
		if(sourceDate!=null && !sourceDate.equals("")){
			try{
				
					Date mydate = Calendar.getInstance().getTime();
					
					DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
				    mydate=formatter.parse(sourceDate);
					
					formatter = new SimpleDateFormat("yyyy-dd-MM");
					destDate=formatter.format(mydate);
					
					destDt = formatter.parse(destDate);
					
			}
			catch(Exception e){
				System.out.println("date format exception prakash"+e);
			}
			}
		return destDt;
	}
	
	
	
	public Date prevDate(Date dt)
	{
	
		
		Calendar cal = Calendar.getInstance();
	    cal.setTime(dt);
	    cal.add(Calendar.DATE, -1); //minus number would decrement the days
	    dt=cal.getTime();
		return dt; 
	}
	
	public static Date nextDate(Date dt)
	{
	
		
		Calendar cal = Calendar.getInstance();
	    cal.setTime(dt);
	    cal.add(Calendar.DATE, 1); //minus number would decrement the days
	    dt=cal.getTime();
		return dt; 
	}
	
	public static Date nextDate(Date dt,int nos)
	{
	
		
		Calendar cal = Calendar.getInstance();
	    cal.setTime(dt);
	    cal.add(Calendar.DATE, nos); //minus number would decrement the days
	    dt=cal.getTime();
		return dt; 
	}
	
	public static Date getDateAfterDays(Date dt,int days)
	{
		Calendar cal = Calendar.getInstance();
	    cal.setTime(dt);
	    cal.add(Calendar.DATE, days); //minus number would decrement the days
	    dt=cal.getTime();
		return dt; 
	}
	
	public static Date getDateBeforeDays(Date dt,int days)
	{
		Calendar cal = Calendar.getInstance();
	    cal.setTime(dt);
	    cal.add(Calendar.DATE, -days); //minus number would decrement the days
	    dt=cal.getTime();
		return dt; 
	}
	
	public String getDDMMYYYY(Date dt)
	{
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		String s = formatter.format(dt);
		return s;
	}
	
	 public boolean fromDtAfterToDt(String fromDt,String toDt)
	    {
	        
		 boolean found=false;
		 try{
	            
	        	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	            Date date1 = sdf.parse(fromDt);
	            Date date2 = sdf.parse(toDt);
         	    if(date1.after(date2)){
	            found=true;
	            }
		 }	    
	      catch(ParseException ex){
	            ex.printStackTrace();
	        }
	    System.out.println(found);
		 return found;
	    }
	
	 
	 public static long getNosOfDays(String fromDt,String toDt)
	 {
		 
		// System.out.println("From Dt="+fromDt);
		// System.out.println("To Dt="+toDt);
		 long nosDays=0;
		//HH converts hour in 24 hours format (0-23), day calculation
	        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

	        Date d1 = null;
	        Date d2 = null;

	        try {
	            d1 = format.parse(fromDt);
	            d2 = format.parse(toDt);

	            //in milliseconds
	            long diff = d2.getTime() - d1.getTime();

	            nosDays = diff / (24 * 60 * 60 * 1000);

	      //      System.out.print(nosDays+1 + " days, ");
	        
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

		 return nosDays;
	 }
	 
	 public static void main(String[] args) {
		
		 DateService.isTime12PMto8PM();
	}
}
