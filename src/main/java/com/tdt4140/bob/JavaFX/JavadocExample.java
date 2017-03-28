package com.tdt4140.bob.JavaFX;

public class JavadocExample {

	public JavadocExample() {
		JavadocExample a = new JavadocExample();
		a.aFuntion(1, "skin");
	}
	
    /** 
     * First paragraph
     * <p>
     * Function that takes the size and color of melons and returns a descriptive <code>String</code>
     *
     * @param size		size of the melons
     * @param color		color of the melons
     * 					another line is allowed
     * @return          <code>String</code> with description with size and color
     * @see             JavadocExample
     * @author 			Olafur
     */
	public String aFuntion(int size, String color) {
		return "those melons are size " +  size + "and has a shade of " + color ;
	}
}
