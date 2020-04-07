## 项目介绍
1.此项目的框架使用Spring+Struts2+Hibernate,使用Maven来构建项目,数据库使用MySQL.<br>
2.该项目的功能:实现登陆,以及用户的管理以及权限控制<br>
3.实现该项目的目的主要是学习ssh整合的相关知识<br>
## 涉及相关技术
### Spring
 >Spring框架简单来说就是一个超级大工厂.其本质就是工厂模式,代理模式,单例模式等各种设计模式的具体实现.主要作用就是管理各种bean以及依赖关系.核心思想是控制反转.具体实现是依赖注入.面试的时候有时会问到控制反转和依赖注入的区别.我看过的书中有说控制反转和依赖注入没有区别,本质是一样的,目的是为了更高层次的解耦,也有的说控制反转包含依赖注入.控制反转是一种思想,而依赖注入是一种只是一种实现,而还有另一种好像是自动装配.我个人觉得没什么意思,反正目的是一样的,就是更高层次的解耦.spring实现了BeanFactory和ApplicationContext两个工厂.ApplicationContext是BeanFactory的子接口.首先我们想要使用spring首先要引入相关jar包,具体的引入依赖去查看一下pom文件吧,这里就不多比比了.引入jar包之后我们要创建spring的配置文件,并配置相关信息.spring支持以配置文件的方式管理bean,也支持使用注解的方式.本项目全部使用注解的方式.本人感觉还是使用注解的方式更简单一些,不用去重复修改配置文件.首先是创建ApplicationContext.xml配置文件.使用注解的方式的话不用去一个一个配置bean,我们只需要配置上spring允许使用注解,并且需要扫描哪些包.配置完之后spring会自动进行扫描对应的包,将所有进行注解的类进行管理.关键代码如下:<br>
`ApplicationContext.xml配置文件中添加`<br>
`<context:annotation-config />`<br>
	`<!-- 使用annotation 自动注册bean,并检查@Required,@Autowired的属性已被注入 -->`<br>
	`<context:component-scan base-package="com.demo">`<br>
		`<!--<context:exclude-filter expression="org.springframework.stereotype.Controller" type="annotation"/>-->`<br>
	`</context:component-scan>`<br>
  `web.xml中配置信息:`<br>
 ` <context-param>`<br>
        `<param-name>contextConfigLocation</param-name>`<br>
        `<param-value>`<br>
            `classpath*:spring/*.xml`<br>
       ` </param-value>`<br>
    `</context-param>`<br>
   ` <listener>`<br>
       ` <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>`<br>
   ` </listener>`<br>

### Struts2
### Hibernate
### Maven
使用maven来编译和管理项目依赖.<br>
就我现在阶段掌握和使用到的,第一个目的就是编译和打包项目.<br>
第二个是用来管理jar包.如果不使用maven,我们如果需要使用相关框架,首先得自己到官网下载下来jar,然后在添加到项目对应目录下,而使用maven则只需要在pom文件中引入相关依赖就可以自动下载jar以及管理jar文件.maven下载下来需要首先修改配置文件.maven有本地仓库,私有仓库,以及中央仓库这几个概念.maven安装和使用可以查看以下博客:<br>
博客地址:https://blog.csdn.net/qq_43188045/article/details/103274234<br>
maven相关依赖地址查询:https://mvnrepository.com/
### MySQL
## 项目进度
目前已完成登陆功能,以及spring整合struts2


