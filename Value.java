
public class Value 
{
	private double dval;
	private String sval;
	private String tag;
	
	public Value()										//Values are constructed with tag STRING, dval 0, and sval null
	{
		tag = "STRING";				
		dval = 0;
		sval = null;
	}

	public String toString()							// Display in a field of 10 characters and double with 4 decimal places
	{
		if(this.tag.equals("DBL"))
			return String.format("%-10.4f", dval);		// double
		else 
			return String.format("%-10s", sval);		// String
	}
	
	public void setDval(double v)						// set double and change tag to DBL
	{ 
		this.dval = v; 
		this.tag = "DBL";
	}			
	public void setSval(String v)						// set String and change tag to String
	{
		this.sval =  v;
		this.tag = "STRING";
	}
	public void setTag(String s){ this.tag = s; }
	public String getTag(){ return this.tag; }
	public double getDval(){ return this.dval; }
	public boolean isNull() 		//Use for display empty string
	{
		return ( (this.getTag().equals("STRING")) && (this.sval == null) ); 
	}
	public Value plus( Value vTwo)	// Arithmetic operator Plus
	{
		Value newValue = new Value();
		if( checkValuesDBL(this, vTwo) )
		{
			newValue.setDval(this.getDval() + vTwo.getDval());	// vOne + vTwo
		}
		else { newValue.setTag("INVALID"); }					// double + String, set tage to INVALID
		return (newValue);
	}
	public Value minus(Value vTwo)	// Arithmetic operator Minus
	{
		Value newValue = new Value();
		if( checkValuesDBL(this, vTwo) )
		{
			newValue.setDval(this.getDval() - vTwo.getDval());	// vOne - vTwo
		}
		else { newValue.setTag("INVALID"); }					// double - String, set tage to INVALID
		return (newValue);
	}
	public Value star(Value vTwo)	// Arithmetic operator Minus
	{
		Value newValue = new Value();
		if( checkValuesDBL(this, vTwo) )
		{
			newValue.setDval(this.getDval() * vTwo.getDval());	// vOne * vTwo
		}
		else { newValue.setTag("INVALID"); }					// double * String, set tage to INVALID
		return (newValue);
	}
	public Value slash(Value vTwo)	// Arithmetic operator Minus
	{
		Value newValue = new Value();
		if( checkValuesDBL(this, vTwo)  )
		{
			if(vTwo.getDval() != 0)
				newValue.setDval(this.getDval() / vTwo.getDval());	// vOne - vTwo
			else													// divide by 0 
			{
				newValue.setTag("INVALID");
				System.out.println("\n<<<<<<Erro message: Zero Division>>>>>>");
			}
		}
		else { newValue.setTag("INVALID"); }					// double / String, set tage to INVALID
		return (newValue);
	}
	
	public static boolean isString(String value)		// check double or String
	{
		return value.substring(0, 1).equals("\"");
	}
	
	public static boolean checkValuesDBL(Value vOne, Value vTwo) // check tags of both Operands are DBL
	{
		if( vOne.getTag().equals("DBL") && vTwo.getTag().equals("DBL")) { return true; }
		else
			return false;
	}
}
