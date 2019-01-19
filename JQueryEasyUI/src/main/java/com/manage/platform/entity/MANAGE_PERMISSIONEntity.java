package com.manage.platform.entity;

import java.io.Serializable;

public class MANAGE_PERMISSIONEntity  implements Serializable{

	public String ICODE;			
	public String ROLEICODE;	
	public String MODELICODE;
	
	public String getICODE() {
		return ICODE;
	}
	public void setICODE(String iCODE) {
		ICODE = iCODE;
	}
	public String getROLEICODE() {
		return ROLEICODE;
	}
	public void setROLEICODE(String rOLEICODE) {
		ROLEICODE = rOLEICODE;
	}
	public String getMODELICODE() {
		return MODELICODE;
	}
	public void setMODELICODE(String mODELICODE) {
		MODELICODE = mODELICODE;
	}

	
}
