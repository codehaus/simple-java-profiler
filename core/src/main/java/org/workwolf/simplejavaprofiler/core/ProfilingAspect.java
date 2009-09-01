package org.workwolf.simplejavaprofiler.core;



import java.util.logging.*;

 

import org.aspectj.lang.*;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
 
@Aspect
public class ProfilingAspect {

    private Logger logger = Logger.getLogger("org.workwolf.profiler.core");

    
    @Around(value = "execution(* *(..)) && @annotation(SimpleProfiler)", argNames = "pjp, SimpleProfiler")
    public Object doPerfLogging(ProceedingJoinPoint pjp, SimpleProfiler SimpleProfiler) throws Throwable {
    	long start = 0l;
    	long freeMemoryBefore = 0l;
		if (SimpleProfiler.profileExecutionTime() == true ){
	    	start = System.currentTimeMillis();
		}
		if (SimpleProfiler.profileMemory() == true ){
			System.gc();
    		freeMemoryBefore = Runtime.getRuntime().freeMemory();        	    		
    	}
        try {
            return  pjp.proceed();
        } finally {
        	long end = 0l;
        	long freeMemoryAfter = 0l;
        	if (SimpleProfiler.profileExecutionTime() == true ){
	            end = System.currentTimeMillis();
    		}
    		if (SimpleProfiler.profileMemory() == true ){
    			System.gc();
        		freeMemoryAfter = Runtime.getRuntime().freeMemory();        	    		
        	}
            recordTime(start, end, 
                pjp.getSignature());
            recordMemory(freeMemoryBefore, freeMemoryAfter, 
                    pjp.getSignature());
            
        }
    }

    
    void recordTime(long start, long end, Signature signature){
        logger.log(Level.INFO,"Timing ["
                + signature.getDeclaringType().getName() + "."
                + signature.getName() + "] = " + (end - start) + "ms");
    }

    void recordMemory(long freeMemoryBefore, long freeMemoryAfter, Signature signature){
        logger.log(Level.INFO,"Timing ["
                + signature.getDeclaringType().getName() + "."
                + signature.getName() + "] = " + (freeMemoryBefore - freeMemoryAfter)/1024000 + "KB");
    }
        
    
}