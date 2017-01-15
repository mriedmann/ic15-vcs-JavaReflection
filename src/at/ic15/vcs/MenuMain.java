package at.ic15.vcs;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuMain implements Menu {
	static ArrayList<Menu> menus = new ArrayList<Menu>();
	static Menu currentMenu;
	static Menu lastMenu;
	
	static {
		// Get list of all usable menu-classes
		String[] menuClassNames = getConfig();
		
		for(String menuClassName : menuClassNames) {
			try {
				// Lookup class-object
				Class<?> menuClass = Class.forName(menuClassName);
				
				System.out.println("Menu '" + menuClass.getSimpleName() + "' loaded");
				
				// Create new menu-objects
				menus.add((Menu) menuClass.newInstance());
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
		
		lastMenu = new MenuMain();
		currentMenu = new MenuMain();
	}
	
	public static String[] getConfig(){
		ArrayList<String> lines = new ArrayList<String>();
		URL configPath = MenuMain.class.getClassLoader().getResource("at/ic15/vcs/config.txt");
		try (
		    InputStream fis = new FileInputStream(configPath.getPath());
		    InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
		    BufferedReader br = new BufferedReader(isr);
		) {
			String line;
		    while ((line = br.readLine()) != null) {
		    	if(!line.startsWith("#"))
		    		lines.add(line);
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lines.toArray(new String[lines.size()]);
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		boolean loop = true;
		
		while(loop){
			System.out.println("\n\n== " + currentMenu.GetName() + " ==");
			
			currentMenu.Draw(System.out);
			
			System.out.print(">");
			int selection = 0;
			try{
				selection = in.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("ERROR - Invalid Input '" + in.next() + "'");
				continue;
			}
			
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
