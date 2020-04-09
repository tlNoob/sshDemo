package com.demo.action;

import com.demo.service.LoginService;
import com.opensymphony.xwork2.ActionContext;
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
@ParentPackage("struts-default")
@Namespace("/LoginAction")
public class LoginAction extends ActionSupport {
    private static final Logger logger = LoggerFactory.getLogger(LoginAction.class);


    public LoginAction(){
        logger.debug("=====================LoginAction==========================");
    }

    @Action(value="loginOut", results={
            @Result(name="success",location = "/index.jsp",type = "redirect")
    })
    public String loginOutAction() {
        ActionContext.getContext().getSession().remove("user");
        return SUCCESS;
    }

}
