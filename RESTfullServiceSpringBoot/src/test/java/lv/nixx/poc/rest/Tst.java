package lv.nixx.poc.rest;

import java.util.*;

import org.junit.Test;

public class Tst {
	
	@Test
	public void testTest() {
		
		String str = "*/5 * * * * SUN-FRI,      */5 * * * * SUN-FRI";
		List<String> items = Arrays.asList(str.split("\\s*,\\s*"));
		
		System.out.println(items);
	}

}
