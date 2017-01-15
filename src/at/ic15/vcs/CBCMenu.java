package at.ic15.vcs;

import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.annotation.Annotation;

public abstract class CBCMenu<T> implements Menu {
	private ArrayList<Method> callableMethods = new ArrayList<Method>();
	private ArrayList<String> callableMethodDisplayNames = new ArrayList<String>();

	private String name;
	private String description;

	public CBCMenu(Class<T> currentClass) {
		//get all public methods from class 
		List<Method> methods = Arrays.asList(currentClass.getMethods());
		//sort by name
		Collections.sort(methods, (m1, m2) -> m1.getName().compareTo(m2.getName()));
		//iterate all methods and add matching to the callable* lists
		for (Method method : methods) {
			// try to get name from method-annotation
			String displayName = analyseMethodAnnotaions(method);
			
			// if no annotation 
			if(displayName == null){
				Pattern p = Pattern.compile("MI_([0-9]+?)(?:_(.*)){0,1}");
				Matcher m = p.matcher(method.getName());
				// filter matching methods
				if (!m.matches())
					continue;
				
				displayName = m.group(2);
			}
						
			callableMethodDisplayNames.add(displayName);
			callableMethods.add(method);
		}
		// gather name and description from class-annotation (if possible)
		analyseClassAnnotations(currentClass);
	}

	private String analyseMethodAnnotaions(Method method) {
		Annotation annotation = method.getAnnotation(MenuItemDetails.class);
		if (annotation instanceof MenuItemDetails) {
			MenuItemDetails menuItemDetails = (MenuItemDetails) annotation;
			return menuItemDetails.name();
		}
		return null;
	}

	private void analyseClassAnnotations(Class<T> currentClass) {
		Annotation annotation = currentClass.getAnnotation(MenuDetails.class);
		if (annotation instanceof MenuDetails) {
			MenuDetails menuDetails = (MenuDetails) annotation;
			name = menuDetails.name();
			description = menuDetails.description();
		}
	}

	@Override
	public String GetName() {
		return name;
	}

	@Override
	public String GetDescription() {
		return description;
	}

	@Override
	public void Draw(PrintStream out) {
		for (int i = 0; i < callableMethodDisplayNames.size(); i++)
			out.println(i + ") " + callableMethodDisplayNames.get(i));
	}

	@Override
	public Menu.CallResult Call(int selection, PrintStream out) throws UnsupportedOperationException {
		if (selection >= callableMethods.size())
			throw new UnsupportedOperationException("Selection '" + selection + "' not implemented!");
		try {
			return (Menu.CallResult) callableMethods.get(selection).invoke(this, out);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			return CallResult.Exit;
		}
	}
}
