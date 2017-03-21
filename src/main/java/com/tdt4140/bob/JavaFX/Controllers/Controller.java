package com.tdt4140.bob.JavaFX.Controllers;

import com.tdt4140.bob.JavaFX.Bob;

public abstract class Controller {
	public Bob app;
	
	public void setApp(Bob app) {
		this.app = app;
	}
	public void onLoad() {}

}
