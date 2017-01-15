package at.ic15.vcs;

import java.io.PrintStream;

public class MenuEnglish implements Menu {

	private String menuItems[] = {
		"Back",
		"Print One",
		"Print Two",
		"Print Three",
		"Print Four"
	};
	
	@Override
	public String GetName() {
		return "EN";
	}

	@Override
	public String GetDescription() {
		return "This menu is in english";
	}

	@Override
	public void Draw(PrintStream out) {
		for(int i = 0; i < menuItems.length; i++)
			out.println(i + ") " + menuItems[i]);
	}

	@Override
	public Menu.CallResult Call(int selection, PrintStream out) throws UnsupportedOperationException{
		switch(selection) {
			case 0: return CallResult.Back;
			case 1: Item1Selected(out); break;
			case 2: Item2Selected(out); break;
			case 3: Item3Selected(out); break;
			case 4: Item4Selected(out); break;
			default:
				throw new UnsupportedOperationException("Selection '" + selection + "' not implemented!");
		}
		
		return CallResult.Again;
	}
	
	private void Item1Selected(PrintStream out) {
		out.println("You selected 1");
	}
	
	private void Item2Selected(PrintStream out) {
		out.println("You selected 2");
	}
	
	private void Item3Selected(PrintStream out) {
		out.println("You selected 3");
	}
	
	private void Item4Selected(PrintStream out) {
		out.println("You selected 4");
	}

}
