package com.demo.service;

import com.demo.dao.UserInfoDao;
import com.demo.model.TLUserInfo;
import com.opensymphony.xwork2.ActionContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LoginService {
    @Resource
    private UserInfoDao userInfoDao;
    public LoginService(){
        System.out.println("==============创建LoginService==============");
    }
    @Transactional(readOnly=true,propagation=Propagation.REQUIRED,rollbackFor = RuntimeException.class)
    public String login(String userId,String passWord){
        String err=null;
        List<TLUserInfo> list =userInfoDao.findByIDAndPwd(userId,passWord);
        if(list!=null && list.size()>0){
            TLUserInfo tlUserInfo=list.get(0);
            ActionContext.getContext().getSession().put("user",tlUserInfo);
        }else {
            err="用户名或者密码错误,请重新输入！";
        }
        return err;
    }

}
