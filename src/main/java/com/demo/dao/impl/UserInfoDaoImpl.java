package com.demo.dao.impl;

import com.demo.base.MyDaoSupport;
import com.demo.dao.UserInfoDao;
import com.demo.model.TLUserInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class UserInfoDaoImpl extends MyDaoSupport implements UserInfoDao {

    public List<TLUserInfo> findByIDAndPwd(String userId, String passWord) {
        TLUserInfo tlUserInfo =new TLUserInfo();
        tlUserInfo.setUserId(userId);
        tlUserInfo.setPassWord(passWord);
        return  this.getHibernateTemplate().findByExample(tlUserInfo);
    }
}
