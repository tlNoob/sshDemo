package com.demo.dao;

import com.demo.model.TLMenuInfo;

import java.util.List;

public interface MenuInfoDao {
    public List<TLMenuInfo> findAllMenu();
}
