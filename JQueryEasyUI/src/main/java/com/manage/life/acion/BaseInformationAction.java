package com.manage.life.acion;

import com.manage.life.dao.BaseInformationDao;
import com.manage.platform.action.ActionBase;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BaseInformationAction extends ActionBase implements Action {

    private BaseInformationDao baseInformationDao;

    public BaseInformationDao getBaseInformationDao() {
        return baseInformationDao;
    }

    public void setBaseInformationDao(BaseInformationDao baseInformationDao) {
        this.baseInformationDao = baseInformationDao;
    }

    private String name;
    private String sex;
    private String birth;
    private String mobile;
    private String communication;
    private String message;
    private String hobby;
    private String remark;
    private String nationality;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCommunication() {
        return communication;
    }

    public void setCommunication(String communication) {
        this.communication = communication;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @Override
    public String execute() throws Exception {

        System.out.println("sssssssssssssss");

        System.out.println("name===" + getName());
        System.out.println("sex===" + getSex());
        System.out.println("birth===" + getBirth());
        System.out.println("mobile===" + getMobile());
        System.out.println("communication===" + getCommunication());
        System.out.println("message===" + getMessage());
        System.out.println("hobby===" + getHobby());
        System.out.println("remark===" + getRemark());
        System.out.println("nationality===" + getNationality());

        String name = getName();
        String sex = getSex();
        String birth = getBirth();
        String mobile = getMobile();
        String communication = getCommunication();
        String message = getMessage();
        String hobby = getHobby();
        String remark = getRemark();
        String nationality = getNationality();
        String id = UUID.randomUUID().toString();

        Map<String, String> baseInformation = new HashMap<String, String>();
        baseInformation.put("id", id);
        baseInformation.put("name", name);
        baseInformation.put("sex", sex);
        baseInformation.put("birth", birth);
        baseInformation.put("mobile", mobile);
        baseInformation.put("communication", communication);
        baseInformation.put("message", message);
        baseInformation.put("hobby", hobby);
        baseInformation.put("remark", remark);
        baseInformation.put("nationality", nationality);

        baseInformationDao.baseInformationInsert(baseInformation);
        //传值1
        //ServletActionContext.getRequest().getSession().setAttribute("permission","test1");
        //传值2
//		HttpServletRequest request = ServletActionContext.getRequest ();
//    	  ActionContext ct= ActionContext.getContext();
////		 HttpServletRequest request=
////		(HttpServletRequest)ct.get(ServletActionContext.HTTP_REQUEST);
//		Map session=ct.getSession();
//		//Map session=(Map)ActionContext.getContext().getActionContext.SESSION);
//		ct.put("permission","test1");

        // 传值3
        ActionContext actionContext = ActionContext.getContext();
        actionContext.put("req", "这是一个request");
        actionContext.getSession().put("sess", "这是一个seesion");
        actionContext.getApplication().put("app", "这是一个application");

        //传值4
        HttpServletRequest request = ServletActionContext.getRequest();
        request.setAttribute("req2", "这是一个request2");
        request.getSession().setAttribute("sess2", "这是一个seesion2");
        ServletContext context = ServletActionContext.getServletContext();
        context.setAttribute("app2", "这是一个application2");
        return SUCCESS;
    }

}
