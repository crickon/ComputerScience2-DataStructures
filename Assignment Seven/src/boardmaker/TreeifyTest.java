package boardmaker;

import java.lang.reflect.Field;
import java.util.HashMap;

public class TreeifyTest
{
	public static String var = "TREEIFY_THRESHOLD";
	
	private static HashMap<String, Boolean> map = new HashMap<String, Boolean>();
	
	public static void main (String...args) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
	{
		Field field = map.getClass().getDeclaredField(var);
		field.setAccessible(true);
		int threshold = (int) field.get(map);
		field.setInt(map, 10);
		//System.out.println(threshold);
	}
}
