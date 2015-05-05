package naihui;


/**
 * @author Naihui Wang
 * 
 * This class in charge of input transformation for the website main page input.
 *
 */


public class MyDate {
	
	  private StringBuffer dateBuffer=new StringBuffer();
	  private Boolean offsetOne=false;
	  private Boolean offsetTwo=false; 
	  private int indexOne=4,indexTwo=0;
	  
	  public  MyDate(String date)
	  {
	  this.dateBuffer=new StringBuffer(date);
		  
	  }

	 
	/**
	 * @return a formatted date which can used for the java search function
	 */
	public String dateGet()
	  {
	 this.dateBuffer.deleteCharAt(4);
	 this.dateBuffer.deleteCharAt(6);
	 if(this.dateBuffer.charAt(4)=='0')
	 {
	 offsetOne=true;

	 }
	 
	 if(this.dateBuffer.charAt(6)=='0')
	 {
	 offsetTwo=true;
	 
	 }
	 
	 if(offsetOne)
	 {
	 this.dateBuffer.deleteCharAt(4);
	 indexOne=4;
	 }
	 if(offsetTwo)
	 {
	 int size=this.dateBuffer.length();
	 this.dateBuffer.deleteCharAt(size-2);
	 indexTwo=size-2;
	 }
	 else
	 {
	 indexTwo=this.dateBuffer.length()-2;
	 }
	 this.dateBuffer.insert(indexTwo,'_');
	 
	 this.dateBuffer.insert(indexOne,'_');
	 String finalDate=this.dateBuffer.toString();
	 return finalDate;
	  }
}

