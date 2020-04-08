package com.demo.base;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("myDaoSupport")
public class MyDaoSupport extends HibernateDaoSupport {
    /**
     * 说明:
     * 1.在既使用注解又使用HibernateDaoSupport的情况下,只能这么写,
     * 原因是HibernateDaoSupport是抽象类,且方法都是final修饰的,
     * 这样就不能为其注入sessionFactory或者hibernateTemplate
     * 2.若使用xml配置的话,就可以直接给HibernateDaoSupport注入.
     */
    //而使用HibernateDaosupport,又必须为其注入sessionFactory或者hibernateTemplate

    //这里为其注入sessionFactory,最后只需要让自己的Dao继承这个MyDaoSupport.
    //不直接在自己的Dao里继承HibernateDaoSupport的原因是这样可以简化代码,
    //不用每次都为其注入sessionFactory或者hibernateTemplate了,在这里注入一次就够了.
    @Resource(name="sessionFactory")
    public void setSuperSessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }

//	或者为其注入hibernateTemplate
//	@Resource(name="hibernateTemplate")
//	public void setSuperHibernateTemplate(HibernateTemplate hibernateTemplate){
//		super.setHibernateTemplate(hibernateTemplate);
//	}
}

