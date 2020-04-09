package com.demo.dao.impl;

import com.demo.base.MyDaoSupport;
import com.demo.dao.MenuInfoDao;
import com.demo.model.TLMenuInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MenuInfoDaoImpl extends MyDaoSupport implements MenuInfoDao {

    public List<TLMenuInfo> findAllMenu() {
        return ( List<TLMenuInfo>)this.getHibernateTemplate().find("from TLMenuInfo");
    }
}
