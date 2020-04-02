package com.demo.strutsDemo;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.demo.hibernateDemo.TLUserInfo;
import com.demo.utils.JDBCUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.util.List;
@Controller
@ParentPackage("struts-default")
@Namespace("/")
public class ssLogionAction extends ActionSupport {
    private String userName;
    private String passWord;
    private String err;
    @Resource
    private SessionFactory sessionFactory;

    public ssLogionAction(){
        System.out.println("=====================创建ssLogionAction==========================");
    }
    @Override
    public String execute() throws Exception {
        String sql="select *  from tl_user_info where userId= ?";
        ResultSet resultSet=JDBCUtils.query(sql,userName);
        try{
            if(resultSet!=null && resultSet.next()){
                if(resultSet.getString("password").equals(passWord)){
                    ActionContext.getContext().getSession().put("userName",userName);
                    return SUCCESS;
                }else{
                    err="您输入的用户名密码不符，请重新输入";
                }
            }else {
                err="用户不存在！";
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        if(err!=null && !err.equals("")){
            return ERROR;

        }
        return SUCCESS;
    }

    @Action(value="login", results={
            @Result(name="success",location="/easyUI/main.jsp"),
            @Result(name="error",location="/index.jsp")
    })
    public String loginAction() {
        //SessionFactory sessionFactory=SSHUtils.getSessionFactory();
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        Query query = session.createQuery("from TLUserInfo where userId = ? ");
        //2、填写上一步中占位符的内容
        query.setParameter(0, userName);
        //3、使用Query对象的list方法得到数据集合
        List<TLUserInfo> list = query.list();
        if(list!=null && list.size()>0){
            TLUserInfo tlUserInfo=list.get(0);
            if(tlUserInfo.getPassWord().equals(passWord)){
                ActionContext.getContext().getSession().put("userName",userName);
                transaction.commit();
                session.close();
                sessionFactory.close();
                return SUCCESS;
            }
        }else {
            err="用户不存在！";
        }

        if(err!=null && !err.equals("")){
            transaction.commit();
            session.close();
            //sessionFactory.close();
            return ERROR;

        }
        transaction.commit();
        session.close();
        //sessionFactory.close();
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
