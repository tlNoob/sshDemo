package core.hibernateDemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class SSHUtils {
    //创建配置对象
    private static Configuration config = new Configuration().configure();
    //创建服务注册对象
    private static ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config .getProperties()).build();


    private static SSHUtils SSHUtils =new SSHUtils();
    private SSHUtils(){
    }

    public static SSHUtils getSSHUtils(){
       return SSHUtils;
    }


    public static SessionFactory getSessionFactory(){
        return config.buildSessionFactory(serviceRegistry);
    }
    /*public static Session getSession(){
        return  sessionFactory.openSession();
    }*/
    //开启事务
    //Transaction transaction = session.beginTransaction();
}
