package com.demo.dao;

import com.demo.model.TLUserInfo;

import java.util.List;

public interface UserInfoDao {
    public List<TLUserInfo> findByIDAndPwd(String userId, String passWord);
}
