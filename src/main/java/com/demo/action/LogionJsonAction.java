package com.demo.action;

import com.alibaba.fastjson.JSONObject;
import com.demo.model.TLMenuInfo;
import com.demo.model.TLUserInfo;
import com.demo.service.LoginService;
import com.demo.utils.JsonTools;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

@Controller
@ParentPackage("json-default")
@Namespace("/LogionJsonAction")
public class LogionJsonAction extends ActionSupport {
    private static final Logger logger = LoggerFactory.getLogger(LogionJsonAction.class);
    private String userName;
    private String passWord;
    private String err;
    private List<TLMenuInfo> list;
    private List<TLUserInfo> userList;
    private JSONObject result;
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
        try{
            err=loginService.login(userName,passWord);
        } catch (Exception e){
            logger.error(e.getMessage());
        }
        return SUCCESS;
    }
    @Action(value="/initMenu", results={
            @Result(name="success",type="json")
    })
    public String initMenu() {
        list=loginService.initMenu();
        return SUCCESS;
    }
    @Action(value="/findAllUser", results={
            @Result(name="success",type="json",params={"root","result"})
    })
    public String findAllUser() {
        List<TLUserInfo>userList=loginService.findAllUser();
        result=JsonTools.ToGridJson(userList,"1000");
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

    public List<TLMenuInfo> getList() {
        return list;
    }

    public void setList(List<TLMenuInfo> list) {
        this.list = list;
    }

    public List<TLUserInfo> getUserList() {
        return userList;
    }

    public void setUserList(List<TLUserInfo> userList) {
        this.userList = userList;
    }

    public JSONObject getResult() {
        return result;
    }

    public void setResult(JSONObject result) {
        this.result = result;
    }
}
