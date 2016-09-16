package br.com.appbackend.Object;

public class Comment
{
	
	private int ra;
	private String comment;
	
	public Comment()
	{
		
	}
	
	public Comment(int ra, String comment) {
		super();
		this.ra = ra;
		this.comment = comment;
	}
	
	public int getRa() {
		return ra;
	}
	public void setRa(int ra) {
		this.ra = ra;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
}
