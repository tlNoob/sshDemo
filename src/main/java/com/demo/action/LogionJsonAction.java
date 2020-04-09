package com.demo.action;

import com.demo.service.LoginService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Controller
@ParentPackage("json-default")
@Namespace("/LogionJsonAction")
public class LogionJsonAction extends ActionSupport {
    private static final Logger logger = LoggerFactory.getLogger(LogionJsonAction.class);
    private String userName;
    private String passWord;
    private String err;
    @Resource
    private LoginService loginService;

    public LogionJsonAction(){
        //System.out.println("=====================创建ssLogionAction==========================");
        logger.debug("=====================LogionJsonAction==========================");
    }

    @Action(value="/login", results={
            @Result(name="success",type="json")
    })
    public String loginAction() {
        err=loginService.login(userName,passWord);
        return SUCCESS;
    }
    @Action(value="/initMenu", results={
            @Result(name="success",type="json")
    })
    public String initMenu() {

        return SUCCESS;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }
}
