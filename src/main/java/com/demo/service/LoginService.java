package com.demo.service;

import com.demo.dao.MenuInfoDao;
import com.demo.dao.UserInfoDao;
import com.demo.model.TLMenuInfo;
import com.demo.model.TLUserInfo;
import com.demo.utils.MD5Utils;
import com.opensymphony.xwork2.ActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class LoginService {
    private static final Logger logger = LoggerFactory.getLogger(LoginService.class);

    @Resource
    private UserInfoDao userInfoDao;
    @Resource
    private MenuInfoDao menuInfoDao;
    public LoginService(){
        //System.out.println("==============创建LoginService==============");
        logger.debug("==============创建LoginService==============");
    }
    @Transactional(readOnly=true,propagation=Propagation.REQUIRED,rollbackFor = RuntimeException.class)
    public String login(String userId,String passWord) throws Exception{
        String err=null;

        List<TLUserInfo> list =userInfoDao.findByIDAndPwd(userId,MD5Utils.md5(passWord));
        if(list!=null && list.size()>0){
            TLUserInfo tlUserInfo=list.get(0);
            ActionContext.getContext().getSession().put("user",tlUserInfo);
        }else {
            err="用户名或者密码错误,请重新输入！";
        }
        return err;
    }

    @Transactional(readOnly=true,propagation=Propagation.REQUIRED,rollbackFor = RuntimeException.class)
    public List<TLMenuInfo> initMenu(){
        List<TLMenuInfo> allMenu=menuInfoDao.findAllMenu();
        List<TLMenuInfo> menuList=new ArrayList<TLMenuInfo>();
        for(TLMenuInfo tlMenuInfo:allMenu){
            if("#".equals(tlMenuInfo.getParentId())){
                menuList.add(tlMenuInfo);
            }
        }

        for(TLMenuInfo tlMenuInfo:menuList){
            tlMenuInfo.setChildMenu(getChildMenu(tlMenuInfo.getId(),allMenu));
        }
        return menuList;
    }

    private List<TLMenuInfo> getChildMenu(String id,List<TLMenuInfo> allMenu){
        List<TLMenuInfo> childMenu=new ArrayList<TLMenuInfo>();
        for(TLMenuInfo tlMenuInfo:allMenu){
            if(id.equals(tlMenuInfo.getParentId())){
                childMenu.add(tlMenuInfo);
            }
        }

        if(childMenu.size()==0){
            return null;
        }
        for(TLMenuInfo tlMenuInfo:childMenu){
            tlMenuInfo.setChildMenu(getChildMenu(tlMenuInfo.getId(),allMenu));
        }
        return childMenu;
    }

}
