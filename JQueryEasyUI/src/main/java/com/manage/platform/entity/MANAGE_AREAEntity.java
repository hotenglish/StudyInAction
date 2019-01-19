package com.manage.platform.entity;

import java.io.Serializable;

public class MANAGE_AREAEntity  implements Serializable{

	public String ICODE		;
	public String NO			;
	public String NAME		;
	public String SPELLNO		;
	public String CUSTOMNO	;
	public String PARENTICODE	;
	public int STOPFLAG	;
	public String ADDRESS		;
	public String ZIP			;
	public String TEL			;
	public String LEVEL1		;
	
	
	public String getSPELLNO() {
		return SPELLNO;
	}
	public void setSPELLNO(String sPELLNO) {
		SPELLNO = sPELLNO;
	}
	public int getSTOPFLAG() {
		return STOPFLAG;
	}
	public void setSTOPFLAG(int sTOPFLAG) {
		STOPFLAG = sTOPFLAG;
	}
	public String getICODE() {
		return ICODE;
	}
	public void setICODE(String iCODE) {
		ICODE = iCODE;
	}
	public String getNO() {
		return NO;
	}
	public void setNO(String nO) {
		NO = nO;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	
	public String getCUSTOMNO() {
		return CUSTOMNO;
	}
	public void setCUSTOMNO(String cUSTOMNO) {
		CUSTOMNO = cUSTOMNO;
	}
	public String getPARENTICODE() {
		return PARENTICODE;
	}
	public void setPARENTICODE(String pARENTICODE) {
		PARENTICODE = pARENTICODE;
	}
	
	public String getADDRESS() {
		return ADDRESS;
	}
	public void setADDRESS(String aDDRESS) {
		ADDRESS = aDDRESS;
	}
	public String getZIP() {
		return ZIP;
	}
	public void setZIP(String zIP) {
		ZIP = zIP;
	}
	public String getTEL() {
		return TEL;
	}
	public void setTEL(String tEL) {
		TEL = tEL;
	}
	public String getLEVEL1() {
		return LEVEL1;
	}
	public void setLEVEL1(String lEVEL1) {
		LEVEL1 = lEVEL1;
	}
	
}
