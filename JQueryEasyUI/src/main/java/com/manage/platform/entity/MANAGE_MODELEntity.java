package com.manage.platform.entity;

import java.io.Serializable;

public class MANAGE_MODELEntity  implements Serializable{

	public String ICODE;
	public String URL;
	public String NAME;
	public String PARENTICODE;
	public String LEVEL1;
	
	public String getICODE() {
		return ICODE;
	}
	public void setICODE(String iCODE) {
		ICODE = iCODE;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public String getPARENTICODE() {
		return PARENTICODE;
	}
	public void setPARENTICODE(String pARENTICODE) {
		PARENTICODE = pARENTICODE;
	}
	public String getLEVEL1() {
		return LEVEL1;
	}
	public void setLEVEL1(String lEVEL1) {
		LEVEL1 = lEVEL1;
	}
	
	
}
