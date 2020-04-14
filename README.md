## 项目介绍
1.此项目的框架使用Spring+Struts2+Hibernate,使用Maven来构建项目,数据库使用MySQL.<br>
2.该项目的功能:实现登陆,以及用户的管理以及权限控制<br>
3.实现该项目的目的主要是学习ssh整合的相关知识<br>
## 涉及相关技术
### Spring
 Spring框架简单来说就是一个超级大工厂.其本质就是工厂模式,代理模式,单例模式等各种设计模式的具体实现.主要作用就是管理各种bean以及依赖关系.核心思想是控制反转.具体实现是依赖注入.面试的时候有时会问到控制反转和依赖注入的区别.我看过的书中有说控制反转和依赖注入没有区别,本质是一样的,目的是为了更高层次的解耦,也有的说控制反转包含依赖注入.控制反转是一种思想,而依赖注入是一种只是一种实现,而还有另一种好像是自动装配.我个人觉得没什么意思,反正目的是一样的,就是更高层次的解耦.spring实现了BeanFactory和ApplicationContext两个工厂.ApplicationContext是BeanFactory的子接口.首先我们想要使用spring首先要引入相关jar包,具体的引入依赖去查看一下pom文件吧,这里就不多比比了.引入jar包之后我们要创建spring的配置文件,并配置相关信息.spring支持以配置文件的方式管理bean,也支持使用注解的方式.本项目全部使用注解的方式.本人感觉还是使用注解的方式更简单一些,不用去重复修改配置文件.首先是创建ApplicationContext.xml配置文件.使用注解的方式的话不用去一个一个配置bean,我们只需要配置上spring允许使用注解,并且需要扫描哪些包.配置完之后spring会自动进行扫描对应的包,将所有进行注解的类进行管理.关键代码如下:<br>
 
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
struts2相当于mvc中的C,也就是控制层,同时也是一个web层的mvc框架.一开始使用给人的最大感受就是其代替掉了servlet开发,提高了开发效率,使应用分层,也更容易后期的维护和扩展.我们想要在项目中使用struts2,首先需要在web.xml中配置上StrutsPrepareAndExecuteFilter过滤器.具体代码如下:<br>
 `<filter>`<br>
        `<filter-name>struts2</filter-name>`<br>
        `<filter-class>org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter</filter-class>`<br>
    `</filter>`<br>

    `<filter-mapping>`<br>
        `<filter-name>struts2</filter-name>`<br>
        `<url-pattern>/*</url-pattern>`<br>
    `</filter-mapping>`<br>
在web.xml中配置上如上信息后,struts2就能够拦截所有的请求,然后再调度不通的action.struts2定义了一个action接口,该接口定义一些基本的规范,并且提供了一个ActionSupport实现类.自己开发action类时需要继承该类.struts2将struts.xml作为默认配置文件.所有关于action的配置都将在这里面进行配置.该项目使用的是@action基于注解的方式进行开发,所以也并不需要在该配置文件中配置所有的action,只需要配置一个使用注解的方式就可以.具体配置信息如下:<br>
 `<constant name="struts.convention.package.locators" value="action,actions,struts,struts2,com" />`<br>
如上信息配置的是struts需要扫描的路径.<br>
完成如上配置,我们就可以在项目愉快的使用struts2来撸代码了.然后再需要说明的是spring整合struts2进行的相关操作.这里spring整合struts2的思想是让spring管理控制器,并使用依赖注入的方式注入业务层组件.这里我们需要在struts.xml配置文件中配置如下信息:
`<constant name="struts.objectFactory" value="spring"></constant>`<br>
这样就实现了spring管理struts的action.还需要注意的一点的是,spring管理bean默认是单例模式,而struts2是多例模式.spring管理action,由于是单例模式,且action又是共用成员属性,所以容易造成线程安全问题.需要将spring管理的action配置成多例模式.次项目中使用的是基于注解的方式配置action,而这种方式是默认多例模式,所以此处也不需要进行格外的处理

### Hibernate
hibernate是orm框架.因为java语言是面向对象的语言,而现在主流的多数数据库又是关系型数据库,所以orm框架就是比较必要的了.它可以让我们以面向对象的编程方式进行开发.hibernate主要实现原理就是将持久类与数据库中的表进行映射,映射完成后我们操作持久类就会生成对应的sql进行执行.持久类和表进行映射可以通过配置文件进行映射,也可以使用注解的方式进行映射.本项目使用注解的方式.此外hibernate需要一个*.cfg.xml文件进行数据库连接的配置.<br>
关于spring整合hibernate.首先spring需要进行管理sessionFactory.在applicationContext.xml中进行如下配置:<br>


`<context:property-placeholder location="classpath:/hibernate/jdbc.properties" />`<br>
	`<bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">`<br>
	
		<property name="user" value="${jdbc.username}"></property><br>
		<property name="password" value="${jdbc.password}"></property><br>
		<property name="jdbcUrl" value="${jdbc.url}"></property><br>
		<property name="driverClass" value="${jdbc.driverClassName}"></property><br>

		<!--初始化时获取的连接数，取值应在minPoolSize与maxPoolSize之间。Default: 3 --><br>
		<property name="initialPoolSize" value="3"></property><br>
		<!--连接池中保留的最大连接数。Default: 15 --><br>
		<property name="maxPoolSize" value="20"></property><br>
		<!--连接池中保留的最小连接数。 --><br>
		<property name="minPoolSize" value="5" ></property><br>
		<!--最大空闲时间,60秒内未使用则连接被丢弃。若为0则永不丢弃。Default: 0 --><br>
		<property name="maxIdleTime" value="0" ></property><br>

		<!--当连接池中的连接耗尽的时候c3p0一次同时获取的连接数。Default: 3 --><br>
		<property name="acquireIncrement" value="3" ></property><br>

		<!--JDBC的标准参数，用以控制数据源内加载的PreparedStatements数量。但由于预缓存的statements 属于单个connection而不是整个连接池。
			所以设置这个参数需要考虑到多方面的因素。如果maxStatements与maxStatementsPerConnection均为0，则缓存被关闭。Default:
			0 --><br>
		<property name="maxStatements" value="100" /><br>

		<!--每60秒检查所有连接池中的空闲连接。Default: 0 --><br>
		<property name="idleConnectionTestPeriod" value="0" ></property><br>

		<!--定义在从数据库获取新连接失败后重复尝试的次数。Default: 30 --><br>
		<property name="acquireRetryAttempts" value="30" ></property><br>

		<!--获取连接失败将会引起所有等待连接池来获取连接的线程抛出异常。但是数据源仍有效 保留，并在下次调用getConnection()的时候继续尝试获取连接。
			如果设为true，那么在尝试 获取连接失败后该数据源将申明已断开并永久关闭。Default: false --><br>
		<property name="breakAfterAcquireFailure" value="false" ></property><br>

		<!--因性能消耗大请只在需要的时候使用它。如果设为true那么在每个connection提交的 时候都将校验其有效性。建议使用idleConnectionTestPeriod
			或automaticTestTable 等方法来提升连接测试的性能。Default: false --><br>
		<property name="testConnectionOnCheckout" value="false" ></property><br>
	</bean><br>

	<!-- 3、配置sessionfactory相关信息 --><br>
	<bean name="sessionFactory" class="org.springframework.orm.hibernate4.LocalSessionFactoryBean"><br>
		<property name="dataSource" ref="dataSource"/><br>
		<property name="hibernateProperties"><br>
			<props><br>
				<prop key="hibernate.dialect">org.hibernate.dialect.MySQLDialect</prop><br>
				<!-- 可选配置 --><br>
				<prop key="hibernate.show_sql">true</prop><br>
				<prop key="hibernate.format_sql">true</prop><br>
				<prop key="hibernate.hbm2ddl.auto">update</prop><br>
			</props><br>
		</property><br>

		<!--<property name="annotatedClasses"><br>
			<list><br>
				<value>com.demo.model.TLUserInfo</value><br>
			</list><br>
		</property>--><br>
		<property name="packagesToScan"><br>
			<list><br>
				<value>com.demo.model</value><br>
			</list><br>
		</property><br>

	</bean><br>
完成如上配置后,spring就管理了sessionFactory.我们就可以以声明的方式使用它了.不再需要手动去创建它了.使用spring管理之后,似乎hibernate的配置文件中也不再需要配置数据库连接的配置了.spring同时也提供了三个接口来支持dao组件的实现:HibernateDaoSupport,HibernateTemplate,HibernateCallback.<br>
一个项目中如果没有使用事务,那一定是不可想象的.我们使用spring来进行事务的配置.此项目配置事务依旧是使用注解的方式.在applicationContext.xml中进行如下配置:<br>


`<tx:annotation-driven/>`<br>
`<!-- 开户事务注解功能 -->`<br>
`<!-- 事务管理器配置, Hibernate单数据源事务 -->`<br>
`<bean id="transactionManager" class="org.springframework.orm.hibernate4.HibernateTransactionManager">`<br>
	`<property name="sessionFactory" ref="sessionFactory" />`<br>
`</bean>`<br>

`<!-- 使用annotation定义事务 -->`<br>
`<tx:annotation-driven transaction-manager="transactionManager" proxy-target-class="true" />`<br>
完成如上配置后就可以使用注解的方式进行事务配置了.

### Maven
使用maven来编译和管理项目依赖.<br>
就我现在阶段掌握和使用到的,第一个目的就是编译和打包项目.<br>
第二个是用来管理jar包.如果不使用maven,我们如果需要使用相关框架,首先得自己到官网下载下来jar,然后在添加到项目对应目录下,而使用maven则只需要在pom文件中引入相关依赖就可以自动下载jar以及管理jar文件.maven下载下来需要首先修改配置文件.maven有本地仓库,私有仓库,以及中央仓库这几个概念.maven安装和使用可以查看以下博客:<br>
博客地址:https://blog.csdn.net/qq_43188045/article/details/103274234<br>
maven相关依赖地址查询:https://mvnrepository.com/
### MySQL


