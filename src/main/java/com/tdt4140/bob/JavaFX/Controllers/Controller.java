package com.tdt4140.bob.JavaFX.Controllers;

import com.tdt4140.bob.JavaFX.Bob;

public abstract class Controller {
	public Bob app;

	/**
	 * A function to set application.<p>
	 * Used for access to database and navigation through the main class (Bob.java)
	 * @param app Application-class used
	 * @author KimRobin
	 */
	public void setApp(Bob app) {
		this.app = app;
	}

	/**
	 * A function used to run when a view opens (if the class has onLoad())
	 * @author KimRobin
	 */
	public void onLoad() {}
}
