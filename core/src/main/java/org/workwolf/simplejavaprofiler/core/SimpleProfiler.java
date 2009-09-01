package org.workwolf.simplejavaprofiler.core;


import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SimpleProfiler {
	public static final String MICROSEC = "MICROSEC";
	public static final String MILLISEC = "MILLISEC";
	public static final String SEC = "SEC";
	public static final String MIN = "MIN";
	public static final String KB = "KB";
	public static final String MB = "MB";
	boolean profileMemory();
	boolean profileExecutionTime();
	String memSize() default "KB";
	String execTime() default "MILLISEC";
}
