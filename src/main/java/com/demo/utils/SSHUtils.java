package com.demo.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class SSHUtils {
    //创建配置对象
    private static Configuration config = new Configuration().configure();
    //创建服务注册对象
    private static ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config .getProperties()).build();

    private static SessionFactory sessionFactory=config.buildSessionFactory(serviceRegistry);


    private static SSHUtils SSHUtils =new SSHUtils();
    private SSHUtils(){
        System.out.println("============创建SSHUtils==================");
    }

    public static SSHUtils getSSHUtils(){
       return SSHUtils;
    }


    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
    /*public static Session getSession(){
        return  sessionFactory.openSession();
    }*/
    //开启事务
    //Transaction transaction = session.beginTransaction();
}
