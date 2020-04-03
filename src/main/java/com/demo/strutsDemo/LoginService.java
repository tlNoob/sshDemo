package com.demo.strutsDemo;

import com.demo.hibernateDemo.TLUserInfo;
import com.opensymphony.xwork2.ActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LoginService {
    @Resource
    private SessionFactory sessionFactory;
    public LoginService(){
        System.out.println("==============创建LoginService==============");
    }
    @Transactional(readOnly=true,propagation=Propagation.REQUIRED,rollbackFor = RuntimeException.class)
    public String login(String userName,String passWord){
        String err=null;
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        Query query = session.createQuery("from TLUserInfo where userId = ? and  password=?");
        //2、填写上一步中占位符的内容
        query.setParameter(0, userName);
        query.setParameter(1,passWord);
        //3、使用Query对象的list方法得到数据集合
        List<TLUserInfo> list = query.list();
        if(list!=null && list.size()>0){
            TLUserInfo tlUserInfo=list.get(0);
            ActionContext.getContext().getSession().put("user",tlUserInfo);
        }else {
            err="用户名或者密码错误,请重新输入！";
        }
        transaction.commit();
        session.close();
        return err;
    }

}
