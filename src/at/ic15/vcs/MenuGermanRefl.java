package at.ic15.vcs;

import java.io.PrintStream;

@MenuDetails(name = "DE R", description = "This menu is in german and uses reflection")

public class MenuGermanRefl extends CBCMenu<MenuGermanRefl> implements Menu {
	public MenuGermanRefl() {
		super(MenuGermanRefl.class);
	}

	@MenuItemDetails(name="<< Zurück")
	public Menu.CallResult MI_0(PrintStream out) {
		return Menu.CallResult.Back;
	}

	@MenuItemDetails(name="Eins ausgaben")
	public Menu.CallResult MI_1(PrintStream out) {
		out.println("Du hast 1 ausgewählt");
		return Menu.CallResult.Again;
	}

	@MenuItemDetails(name="Zwei ausgaben")
	public Menu.CallResult MI_2(PrintStream out) {
		out.println("Du hast 2 ausgewählt");
		return Menu.CallResult.Again;
	}

	@MenuItemDetails(name="Drei ausgaben")
	public Menu.CallResult MI_3(PrintStream out) {
		out.println("Du hast 3 ausgewählt");
		return Menu.CallResult.Again;
	}

	@MenuItemDetails(name="Vier ausgaben")
	public Menu.CallResult MI_4(PrintStream out) {
		out.println("Du hast 4 ausgewählt");
		return Menu.CallResult.Again;
	}

	@MenuItemDetails(name="Fünf ausgaben")
	public Menu.CallResult MI_5(PrintStream out) {
		out.println("Du hast 5 ausgewählt");
		return Menu.CallResult.Again;
	}
}
