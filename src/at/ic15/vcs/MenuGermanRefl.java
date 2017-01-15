package at.ic15.vcs;

import java.io.PrintStream;

/* A
 * @MenuDetails(name = "DE R", description = "This menu is in german and uses reflection")
 */

public class MenuGermanRefl extends CBCMenu<MenuGermanRefl> implements Menu {
	public MenuGermanRefl() {
		super(MenuGermanRefl.class);
	}
	
	@Override
	public String GetName() {
		return "DE R";
	}

	@Override
	public String GetDescription() {
		return "This menu is in German and uses reflection";
	}

	public Menu.CallResult MI_0_Zurück(PrintStream out) {
		return Menu.CallResult.Back;
	}

	public Menu.CallResult MI_1_Eins(PrintStream out) {
		out.println("Du hast 1 ausgewählt");
		return Menu.CallResult.Again;
	}
	
	public Menu.CallResult MI_2_Zwei(PrintStream out) {
		out.println("Du hast 2 ausgewählt");
		return Menu.CallResult.Again;
	}
	
	public Menu.CallResult MI_3_Drei(PrintStream out) {
		out.println("Du hast 3 ausgewählt");
		return Menu.CallResult.Again;
	}
	
	public Menu.CallResult MI_4_Vier(PrintStream out) {
		out.println("Du hast 4 ausgewählt");
		return Menu.CallResult.Again;
	}
	
	public Menu.CallResult MI_5_Fünf(PrintStream out) {
		out.println("Du hast 5 ausgewählt");
		return Menu.CallResult.Again;
	}
}
