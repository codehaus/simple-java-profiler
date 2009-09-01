package src.main;

import java.lang.reflect.Method;

public class SimpleJavaProfiler {
	
	private Object theInstance = null;

	public SimpleJavaProfiler(String className) {
		try {
			theInstance = Class.forName(className).newInstance();
		} catch (Exception ex) {
			System.out.println("Cannot instantiate class due to exception " + ex);
		} 
	} 
	
	
	public void doProfile() throws Exception {
		Method [] methods = theInstance.getClass().getDeclaredMethods();
		
		boolean profileMemory = false;
		boolean profileExecutionTime = false;

		for(int i = 0; i < methods.length; i++) {
        	Method theMethod = methods[i];
        	SimpleProfiler profiler = theMethod.getAnnotation(SimpleProfiler.class);
        	long freeMemoryBefore = 0;
        	long freeMemoryAfter = 0;
        	long timeBefore = 0;
        	long timeAfter = 0;
        	
        	if ( profiler!= null ) {
	        	if ( profiler.profileMemory() == true) {
	        		System.gc();
	        		freeMemoryBefore = Runtime.getRuntime().freeMemory();        	
	        	} 
	        
	        	if ( profiler.profileExecutionTime() == true) {
	        		timeBefore = System.currentTimeMillis();        	
	        	} 
	        
	        	if (profiler.profileExecutionTime() == true ||profiler.profileMemory() == true) {
	        		//System.out.println("Profiling " + theMethod.getName());
	        		theMethod.invoke(theInstance, null);
	        	} 
	        	
	        	if ( profiler.profileMemory() == true) {
	        		freeMemoryAfter = Runtime.getRuntime().freeMemory();
	        		double memoryUsed = freeMemoryBefore - freeMemoryAfter;
		        	//System.out.println("Profiler:  The memory required to execute " + theMethod.getName() + " for class " + theInstance.getClass().getName() + " was " + memoryUsed + profiler.memSize());
	        	} 
	        	
	        	if ( profiler.profileExecutionTime() == true) {
	        		timeAfter = System.currentTimeMillis();
	        		//System.out.println("Profiler:  The time required to execute " + theMethod.getName() + " for class " + theInstance.getClass().getName() + " was " + (timeAfter - timeBefore) + " milliseconds");
	        	}
	        } 
		}
	} 
	
	
	
	
	public static void main(String[] args) throws Exception {
		

		System.out.println("Beginning profiling of class " + args[0]);
		SimpleJavaProfiler theProfiler = new SimpleJavaProfiler(args[0]);
		theProfiler.doProfile();
		System.out.println("Done!");
	} 

} 
