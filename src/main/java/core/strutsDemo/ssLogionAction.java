package core.strutsDemo;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import core.utils.JDBCUtils;

import javax.servlet.http.HttpSession;
import java.sql.ResultSet;

public class ssLogionAction extends ActionSupport {
    private String userName;
    private String passWord;
    private String err;
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
