package UnifyEngine;

public class Debug {
	public static boolean enable = false;
	public static void Log(String _str) {
		if(!enable) return;
		System.out.println(_str);
	}
	public static void LogError(String _str) {
		if(!enable) return;
		System.out.println("ERROR: "+_str);
	}
}
