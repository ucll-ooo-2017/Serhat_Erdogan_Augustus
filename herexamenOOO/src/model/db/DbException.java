//@author Serhat Erdogan

package model.db;

public class DbException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public DbException(){
		super();
	}
	
	public DbException(String message)
	{
		super(message);
	}
}
