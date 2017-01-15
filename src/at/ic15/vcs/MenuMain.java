package at.ic15.vcs;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuMain implements Menu {
	static ArrayList<Menu> menus = new ArrayList<Menu>();
	static Menu currentMenu;
	static Menu lastMenu;
	
	static {
		Class<?>[] menuClasses = { MenuEnglish.class, MenuGerman.class, MenuEnglishRefl.class };
		
		for(Class<?> menuClass : menuClasses) {
			try {
				menus.add((Menu) menuClass.newInstance());
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
		
		lastMenu = new MenuMain();
		currentMenu = new MenuMain();
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		boolean loop = true;
		
		while(loop){
			System.out.println("\n\n== " + currentMenu.GetName() + " ==");
			
			currentMenu.Draw(System.out);
			
			System.out.print(">");
			int selection = in.nextInt();
			
			Menu.CallResult result;
			try{
				result = currentMenu.Call(selection, System.out);
			} catch(UnsupportedOperationException e) {
				System.out.println("ERROR - " + e.getMessage());
				result = CallResult.Again;
			}
			switch(result){
				case Exit: loop = false; break;
				case Back: currentMenu = lastMenu; break;
				case Again:
				default: break;
			}
		}
		
		in.close();
	}	
	
	@Override
	public String GetName() {
		return "Main Menu";
	}

	@Override
	public String GetDescription() {
		return "";
	}
	
	@Override
	public void Draw(PrintStream out) {
		System.out.println("0) Exit");
		for(int i = 0; i < menus.size(); i++)
			System.out.println((i+1) + ") " + menus.get(i).GetName() + " - " + menus.get(i).GetDescription());
	}

	@Override
	public Menu.CallResult Call(int selection, PrintStream out) {
		if(selection == 0)
			return CallResult.Exit;
		else if(selection > menus.size())
			throw new UnsupportedOperationException("Invalid Selection");
		
		lastMenu = currentMenu;
		currentMenu = menus.get(selection - 1);
		return CallResult.Again;
	}
}
