package at.ic15.vcs;

import java.io.PrintStream;

public class MenuGerman implements Menu {

	private String menuItems[] = {
		"<<",
		"Eins ausgeben",
		"Zwei ausgeben",
		"Drei ausgeben",
		"Vier ausgeben"
	};
	
	@Override
	public String GetName() {
		return "DE";
	}

	@Override
	public String GetDescription() {
		return "This menu is in german";
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
		out.println("Du hast 1 ausgewählt");
	}
	
	private void Item2Selected(PrintStream out) {
		out.println("Du hast 2 ausgewählt");
	}
	
	private void Item3Selected(PrintStream out) {
		out.println("Du hast 3 ausgewählt");
	}
	
	private void Item4Selected(PrintStream out) {
		out.println("Du hast 4 ausgewählt");
	}

}
