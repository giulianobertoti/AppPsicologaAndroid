package br.com.appbackend.Object;

public enum UserType {
	
	STUDENT("Student"), PSYCHOLOGIST("Psychologist"), OTHER("");
	
	public String type;
	
	private UserType(String type)
	{
		this.type = type;
	}
	
	public static UserType getValue(String arg0)
	{
		UserType value;
		try
		{
			value = valueOf(arg0);
		}
		catch(Exception e)
		{
			try
			{
				value = valueOf(arg0.toUpperCase());
			}
			catch (Exception ex)
			{
				value = UserType.OTHER;
			}
		}
		return value;
	}
	
}
