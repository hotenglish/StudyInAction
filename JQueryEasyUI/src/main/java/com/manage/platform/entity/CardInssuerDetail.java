package com.manage.platform.entity;


import java.io.Serializable;
import java.sql.Date;

public class CardInssuerDetail implements Serializable{

	private static final long serialVersionUID = 1526705009706221747L;
	
	//新增
	
	private String areacode;
	private String nettype;
	private String craddate;
	private String cardid;
	private String realted;
	private String username;
	private String useradd;
	private String tel;
	private String operman;
	private String duration;
	private String billDate;
	private String expDate;
	private String refer;
	private String type;
	
	
	public String getAreacode() {
		return areacode;
	}

	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}

	public String getCraddate() {
		return craddate;
	}

	public void setCraddate(String craddate) {
		this.craddate = craddate;
	}

	public String getCardid() {
		return cardid;
	}

	public void setCardid(String cardid) {
		this.cardid = cardid;
	}

	public String getRealted() {
		return realted;
	}

	public void setRealted(String realted) {
		this.realted = realted;
	}

	public String getUseradd() {
		return useradd;
	}

	public void setUseradd(String useradd) {
		this.useradd = useradd;
	}

	public String getBillDate() {
		return billDate;
	}

	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}

	public String getExpDate() {
		return expDate;
	}

	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	private String id;
	private Date reservdate;

	private String revered;
	private String city;
	private String people_id;
	private String add_card;

	private String add2;

	private String card_id;
	private String post;

	private String bill_date;
	private String exp_date;
	private String page;

	public CardInssuerDetail() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getReservdate() {
		return reservdate;
	}

	public void setReservdate(Date reservdate) {
		this.reservdate = reservdate;
	}

	public String getNettype() {
		return nettype;
	}

	public void setNettype(String nettype) {
		this.nettype = nettype;
	}

	public String getRefer() {
		return refer;
	}

	public void setRefer(String refer) {
		this.refer = refer;
	}

	public String getRevered() {
		return revered;
	}

	public void setRevered(String revered) {
		this.revered = revered;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPeople_id() {
		return people_id;
	}

	public void setPeople_id(String people_id) {
		this.people_id = people_id;
	}

	public String getAdd_card() {
		return add_card;
	}

	public void setAdd_card(String add_card) {
		this.add_card = add_card;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAdd2() {
		return add2;
	}

	public void setAdd2(String add2) {
		this.add2 = add2;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getCard_id() {
		return card_id;
	}

	public void setCard_id(String card_id) {
		this.card_id = card_id;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getOperman() {
		return operman;
	}

	public void setOperman(String operman) {
		this.operman = operman;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getBill_date() {
		return bill_date;
	}

	public void setBill_date(String bill_date) {
		this.bill_date = bill_date;
	}

	public String getExp_date() {
		return exp_date;
	}

	public void setExp_date(String exp_date) {
		this.exp_date = exp_date;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}
}
