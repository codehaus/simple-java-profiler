package src.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
 
public class TestEverything {

	
//	public static void main(String[] args) {
//		
//		try {
//			
//		TestSingleton ts = TestSingleton.getInstance("TestChandraDeep");
//		
//		FileOutputStream fos = new FileOutputStream(new File("d:\\temp\\test\\a.out"));
//		ObjectOutputStream oos = new ObjectOutputStream(fos);
//		oos.writeObject(ts);
//
//		Logger.getLogger("com.iflex.flexnet.core.test.TestEverything").debug("Write success..");
//		
//		
//		FileInputStream fis = new FileInputStream(new File("d:\\temp\\test\\a.out"));
//		ObjectInputStream ois = new ObjectInputStream(fis);
//		TestSingleton ts1 = (TestSingleton)ois.readObject();
//		
//		System.out.println("Read success..");
//		
//		System.out.println(ts1.toString());
//		if ( ts == ts1 )
//			System.out.println("Ok!!");
//		else
//			System.out.println("Fraud!!");
//		
//		}catch ( Exception e) {
//			e.printStackTrace();
//		}
//		
//		
//		
//	}

	@SimpleProfiler (profileMemory=true, profileExecutionTime=true )
	public  void test() {
		System.out.println("Test called");
		try {
			
		TestSingleton ts = TestSingleton.getInstance("dhirendra");
		
		FileOutputStream fos = new FileOutputStream(new File("d:\\temp\\test\\a.out"));
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(ts);

		
		
		FileInputStream fis = new FileInputStream(new File("d:\\temp\\test\\a.out"));
		ObjectInputStream ois = new ObjectInputStream(fis);
		TestSingleton ts1 = (TestSingleton)ois.readObject();
		
		System.out.println("Read success..");
		
		System.out.println(ts1.toString());
		if ( ts == ts1 )
			System.out.println("Ok!!");
		else
			System.out.println("Fraud!!");
		
		}catch ( Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	public  void test1(){
		System.out.println("private method");
	}
}
