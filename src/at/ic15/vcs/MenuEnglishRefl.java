package at.ic15.vcs;

import java.io.PrintStream;

public class MenuEnglishRefl extends CBCMenu<MenuEnglishRefl> implements Menu {
	public MenuEnglishRefl() {
		super(MenuEnglishRefl.class);
	}

	@Override
	public String GetName() {
		return "EN R";
	}

	@Override
	public String GetDescription() {
		return "This menu is in English and uses reflection";
	}

	public Menu.CallResult MI_0_Back(PrintStream out) {
		return Menu.CallResult.Back;
	}

	public Menu.CallResult MI_1_One(PrintStream out) {
		out.println("You selected 1");
		return Menu.CallResult.Again;
	}

	public Menu.CallResult MI_2_Two(PrintStream out) {
		out.println("You selected 2");
		return Menu.CallResult.Again;
	}

	public Menu.CallResult MI_3_Three(PrintStream out) {
		out.println("You selected 3");
		return Menu.CallResult.Again;
	}

	public Menu.CallResult MI_4_Four(PrintStream out) {
		out.println("You selected 4");
		return Menu.CallResult.Again;
	}
	
	@SuppressWarnings("unused")
	private Menu.CallResult MI_5_Five(PrintStream out) {
		out.println("You selected 5");
		return Menu.CallResult.Again;
	}
}
