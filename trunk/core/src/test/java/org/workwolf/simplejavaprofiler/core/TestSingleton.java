package src.main;


import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;




public class TestSingleton implements Serializable{
	
	
	private static HashMap<String, String> connLineInstance = null;
	private static TestSingleton instance = null; // instance of this class
	private static final Object lock = new Object(); // Lock Object
	private static String teststr=null; 
	
	private TestSingleton(){
	}
	public static TestSingleton getInstance(String cacheName){

		if (instance == null) {
			synchronized (lock) {
				if (instance == null) {
					load(cacheName);
					instance = new TestSingleton();
				}
			}
		}else{
			updateMap(cacheName);
		}
		return instance;
	}
	private static void load(String cacheName){
		System.out.println("In Load");
		synchronized (lock) {
			teststr=cacheName;
			connLineInstance = new HashMap();
			connLineInstance.put(cacheName, cacheName);
		}
	}
	public static  void  updateMap(String cacheName){
		teststr=cacheName;
		System.out.println("In update");
		connLineInstance.put(cacheName, cacheName);
		
		
	}
	public String toString(){
		String str = "Map = " +teststr+ ",";
		if(connLineInstance !=null){
			
		for(String key : connLineInstance.keySet() ) {
			str += key+"-"+connLineInstance.get(key)+",";
		}
		}
		return str;
	}


}

