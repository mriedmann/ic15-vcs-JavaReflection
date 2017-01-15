package at.ic15.vcs;

import java.io.PrintStream;

public interface Menu {
	public enum CallResult {
		Exit,
		Again,
		Back
	}
	
	public String GetName();
	public String GetDescription();
	
	public void Draw(PrintStream out); 
	public Menu.CallResult Call(int selection, PrintStream out) throws UnsupportedOperationException;
}
