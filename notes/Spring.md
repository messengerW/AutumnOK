# Spring



### 介绍一下 Spring 框架

Spring 是一个轻量级、松散耦合的开源应用框架，是为Java应用程序提供基础性服务的一套框架，目的是用于简化企业应用程序的开发，使开发者只需要关心业务需求。 

优点如下：

**轻量级：** 基础版本的Spring框架大约只有2MB ;

**控制反转(IoC)：** Spring通过控制反转实现了松散耦合，对象们给出它们的依赖，而不是创建或查找依赖的对象；

**面向切面的编程(AOP)：**  Spring支持面向切面的编程，并且把应用业务逻辑和系统服务分开 ;

**容器：**Spring 包含并管理应用中对象的生命周期和配置；

**[MVC框架](https://link.zhihu.com/?target=http%3A//www.woniuxy.com/)**：Spring的WEB框架是个精心设计的框架，是Web框架的一个很好的替代品；

**事务管理：**Spring 提供一个持续的事务管理接口，可以扩展到上至本地事务下至全局事务（JTA）；

**异常处理：**Spring 提供方便的API把具体技术相关的异常（比如由JDBC，Hibernate or JDO抛出的）转化为一致的unchecked 异常。



### Spring 主要模块

Spring Core：核心类库，提供IOC服务；

Spring Context：提供框架式的Bean访问方式，以及企业级功能（JNDI、定时任务等）；

Spring AOP：AOP服务；

Spring DAO：对JDBC的抽象，简化了数据访问异常的处理；

Spring ORM：对现有的ORM框架的支持；

Spring Web：提供了基本的面向Web的综合特性，例如多方文件上传；

Spring MVC：提供面向Web应用的Model-View-Controller实现。



### [对 Spring IoC 的理解](https://www.jianshu.com/p/4007079cb6c0)

IoC就是**控制反转**，就是把对象创建的控制权移交给Spring来管理。

（1）以前创建对象的主动权和时机是由自己把控的，而现在这种权力转移到Spring容器中，并由容器根据配置文件去创建实例和管理各个实例之间的依赖关系，对象与对象之间松散耦合，也利于功能的复用。DI依赖注入，和控制反转是同一个概念的不同角度的描述，即 应用程序在运行时依赖IoC容器来动态注入对象需要的外部资源。

（2）最直观的表达就是，IOC让对象的创建不用去new了，可以由spring自动生产，使用java的反射机制，根据配置文件在运行时动态的去创建对象以及管理对象，并调用对象的方法的。

（3）Spring的IOC有三种注入方式 ：构造器注入、setter方法注入、接口方式注入。

优点：  最小化应用程序中的代码量 ； 使应用程序易于测试，因为它不需要单元测试用例中的任何单例或 JNDI 查找机制 ； 以最小的影响和最少的侵入机制促进松耦合 ； 支持即时的实例化和延迟加载服务 。



### 什么是依赖注入（DI）

DI就是**依赖注入**， 在依赖注入中，不必创建对象，但必须描述如何创建它们。不是直接在代码中将组件和服务连接在一起，而是描述配置文件中哪些组件需要哪些服务。由 IoC 容器将它们装配在一起。 

 依赖注入可以通过三种方式完成 ：构造方法注入、setter方法注入、接口方式注入。

![](.\Spring依赖注入.png)



### 构造方法注入和setter注入之间的区别

1. 在Setter注入,可以将依赖项部分注入,构造方法注入不能部分注入，因为调用构造方法如果传入所有的参数就会报错。
2. 如果我们为同一属性提供Setter和构造方法注入，Setter注入将覆盖构造方法注入。但是构造方法注入不能覆盖setter注入值。显然，构造方法注入被称为创建实例的第一选项。
3. 使用setter注入你不能保证所有的依赖都被注入,这意味着你可以有一个对象依赖没有被注入。在另一方面构造方法注入直到你所有的依赖都注入后才开始创建实例。
4. 在构造函数注入,如果A和B对象相互依赖：A依赖于B,B也依赖于A,此时在创建对象的A或者B时，Spring抛出`ObjectCurrentlyInCreationException`。所以Spring可以通过setter注入,从而解决循环依赖的问题。



### Spring IoC 容器

有两种: beanfactory 和 applicationcontext

###  **BeanFactory和ApplicationContext有什么区别** 

二者区别： 

1. beanfactory是Spring里面最底层的接口，包含了各种Bean的定义，读取bean配置文档，管理bean的加载、实例化，控制bean的生命周期，维护bean之间的依赖关系。 Applicationcontext是扩展了beanfactory的接口 ， 在 BeanFactory 基础上提供了一些额外的功能 比如：
   ①继承MessageSource，因此支持国际化。
   ②统一的资源文件访问方式。
   ③提供在监听器中注册bean的事件。
   ④同时加载多个配置文件。
   ⑤载入多个（有继承关系）上下文 ，使得每一个上下文都专注于一个特定的层次，比如应用的web层。

2.  BeanFactroy采用的是延迟加载形式来注入Bean的，即只有在使用到某个Bean时(调用getBean())，才对该 Bean进行加载实例化；

    ApplicationContext，它是在容器启动时一次性创建了所有的Bean。  



### 对 Spring AOP 的理解

 [AOP](https://blog.csdn.net/JinXYan/article/details/89302126) 一般称为面向切面编程，是对面向对象编程的一种补充 。用于将那些与业务无关，但却对多个对象产生影响的公共行为和逻辑，抽取并封装为一个可重用的模块（这个模块也就是“切面”），减少系统中的重复代码，降低了模块间的耦合度，同时提高了系统的可维护性。可用于权限认证、日志、事务处理。

优点： 允许在业务逻辑之前或之后添加或删除功能， 维护方便。  

名词解释：

（1）切面（Aspect）：被抽取的公共模块，可能会横切多个对象。 在Spring AOP中，切面可以使用通用类（基于模式的风格） 或者在普通类中以 @AspectJ 注解来实现。

（2）连接点（Join point）：指方法，在Spring AOP中，一个连接点 总是 代表一个方法的执行。 

（3）通知（Advice）：在切面的某个特定的连接点（Join point）上执行的动作。通知有各种类型，其中包括“around”、“before”和“after”等通知。许多AOP框架，包括Spring，都是以拦截器做通知模型， 并维护一个以连接点为中心的拦截器链。

（4）切入点（Pointcut）：切入点是指 我们要对哪些Join point进行拦截的定义。通过切入点表达式，指定拦截的方法，比如指定拦截add*、search*。

（5）引入（Introduction）：（也被称为内部类型声明（inter-type declaration））。声明额外的方法或者某个类型的字段。Spring允许引入新的接口（以及一个对应的实现）到任何被代理的对象。例如，你可以使用一个引入来使bean实现 IsModified 接口，以便简化缓存机制。

（6）目标对象（Target Object）： 被一个或者多个切面（aspect）所通知（advise）的对象。也有人把它叫做 被通知（adviced） 对象。 既然Spring AOP是通过运行时代理实现的，这个对象永远是一个 被代理（proxied） 对象。

（7）织入（Weaving）：指把增强应用到目标对象来创建新的代理对象的过程。Spring是在运行时完成织入。



###  Spring通知（Advice）类型

（1）前置通知（Before advice）：在某连接点（join point）之前执行的通知，但这个通知不能阻止连接点前的执行（除非它抛出一个异常）。

（2）返回后通知（After returning advice）：在某连接点（join point）正常完成后执行的通知：例如，一个方法没有抛出任何异常，正常返回。 

（3）抛出异常后通知（After throwing advice）：在方法抛出异常退出时执行的通知。 

（4）后通知（After (finally) advice）：当某连接点退出的时候执行的通知（不论是正常返回还是异常退出）。 

（5）环绕通知（Around Advice）：包围一个连接点（join point）的通知，如方法调用。这是最强大的一种通知类型。 环绕通知可以在方法调用前后完成自定义的行为。它也会选择是否继续执行连接点或直接返回它们自己的返回值或抛出异常来结束执行。 环绕通知是最常用的一种通知类型。大部分基于拦截的AOP框架，例如Nanning和JBoss4，都只提供环绕通知。 



### Spring Bean 生命周期

**①** ：实例化Bean ：

对于BeanFactory容器，当客户向容器请求一个尚未初始化的bean时，或初始化bean的时候需要注入另一个尚未初始化的依赖时，容器就会调用createBean进行实例化；

对于ApplicationContext容器，当容器启动结束后，通过获取BeanDefinition对象中的信息，实例化所有的bean。

**②** ： 设置对象属性（依赖注入） ： 按照Bean定义（ BeanDefinition ）信息配置信息，注入所有的属性 ；

**③** ： 如果Bean实现了BeanNameAware接口，会回调该接口的setBeanName()方法，传入该Bean的id，此时该Bean就获得了自己在配置文件中的id ；

**④** ： 如果Bean实现了BeanFactoryAware接口,会回调该接口的setBeanFactory()方法，传入该Bean的BeanFactory，这样该Bean就获得了自己所在的BeanFactory ；

**⑤** ：  如果Bean实现了ApplicationContextAware接口,会回调该接口的setApplicationContext()方法，传入该Bean的ApplicationContext，这样该Bean就获得了自己所在的ApplicationContext；

**⑥** ：  如果有Bean实现了BeanPostProcessor接口，则会回调该接口的postProcessBeforeInitialzation()方法；

**⑦** ：  如果Bean实现了InitializingBean接口，则会回调该接口的afterPropertiesSet()方法；

⑧ ：  如果Bean配置了init-method方法，则会执行init-method配置的方法；

⑨ ：  如果有Bean实现了BeanPostProcessor接口，则会回调该接口的postProcessAfterInitialization()方法；

⑩ ：   以上几个步骤完成后，Bean就已经被正确创建了，之后就可以使用这个Bean了；

11：   当Bean不再需要时，会经过清理阶段，如果Bean实现了DisposableBean这个接口，会调用其实现的destroy()方法；

12：  最后，如果这个Bean的Spring配置中配置了destroy-method属性，会自动调用其配置的销毁方法。 



### Bean 作用域

1. 单例singleton：默认情况下都是单例的，它要求在每个spring 容器内不论你请求多少次这个实例，都只有一个实例。单例特性是由beanfactory本身维护的。
2. 原型prototype：这个bean的实例和单例相反,一个新的请求产生一个新的bean实例。
3. 请求request：在一个请求内,将会为每个web请求的客户端创建一个新的bean实例。一旦请求完成后,bean将失效，然后被垃圾收集器回收掉。
4. 会话session：就像请求范围,这样可以确保每个用户会话bean的一个实例。当用户结束其会话,bean失效。
5. 全局会话global-session：应用到Portlet应用程序。基于Servlet的应用程序和会话相同。



### 单例bean是线程安全的吗？

Spring框架不对单例的bean做任何多线程的处理。单例的bean的并发问题和线程安全是开发人员的责任。

而实际上,大多数spring bean没有可变状态(例如服务和DAO的类),这样的话本身是线程安全的。但如果您的bean有可变状态(例如视图模型对象),这就需要你来确保线程安全。

这个问题最简单和明显的解决方案是改变bean Scope，可变的bean从“单例”到“原型”。



### [Spring 如何处理线程并发](https://blog.csdn.net/qq_43425833/article/details/88722765?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.nonecase&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.nonecase)

在一般情况下，只有无状态的Bean才可以在多线程环境下共享，在Spring中，绝大部分Bean都可以声明为singleton作用域，因为Spring对一些Bean中非线程安全状态采用ThreadLocal进行处理，解决线程安全问题。

ThreadLocal和线程同步机制都是为了解决多线程中相同变量的访问冲突问题。同步机制采用了“时间换空间”的方式，仅提供一份变量，不同的线程在访问前需要获取锁，没获得锁的线程则需要排队。而ThreadLocal采用了“空间换时间”的方式。

ThreadLocal会为每一个线程提供一个独立的变量副本，从而隔离了多个线程对数据的访问冲突。因为每一个线程都拥有自己的变量副本，从而也就没有必要对该变量进行同步了。ThreadLocal提供了线程安全的共享对象，在编写多线程代码时，可以把不安全的变量封装进ThreadLocal



###  [Spring基于xml注入bean的几种方式](https://blog.csdn.net/a745233700/article/details/89307518)

（1）Set方法注入；

（2）构造器注入：①通过index设置参数的位置；②通过type设置参数类型；

（3）静态工厂注入；

（4）实例工厂；



### [在Spring里注入Java集合](https://www.cnblogs.com/davidwang456/p/10615892.html)

Spring提供了四种类型的配置元素集合,如下:

list：帮助注入一组值,允许重复。
set：帮助注入一组值,不允许重复。
map：帮助注入一个K-V的集合,名称和值可以是任何类型的。
props：帮助注入一个名称-值对集合，名称和值都是字符串。



### Bean自动注入模式

 在Spring有五个自动注入模式：

1. no:默认情况下，spring框架的自动注入选项，即默认情况不开启自动注入。这意味着你必须使用标签在bean定义中显式地设置依赖项。
2. byName:这个选项使基于bean的名称的依赖项注入。当自动装配在bean属性,用属性名搜索匹配的bean定义配置文件。如果找到这样的bean,注入属性。如果没有找到这样的bean,就会产生一个错误。
3. byType:该选项允许基于bean的类型的依赖项注入。当在bean属性需要自动注入时,使用属性类的类型来搜索匹配的bean定义配置文件。如果找到这样的bean,注入属性。如果没有找到这样的bean,就会产生一个错误。
4. constructor:构造方法类似于byType自动注入,但适用于构造方法的参数。在自动注入bean时,它在所有构造函数参数类型中寻找匹配的构造函数的类类型参数,然后进行自动注入,。请注意,如果在容器中没有一个bean构造函数参数类型满足,会抛出一个致命错误。
5. autodetect:自动侦测使用两种模式即构造函数或byType模式的自动注入。首先它将试图寻找有效的构造方法参数,如果发现构造方法模式则选择。如果没有构造方法中定义bean,或者明确的默认无参构造方法,则选择byType模式自动注入。

基于注解的方式：

使用@Autowired注解来自动装配指定的bean。在使用@Autowired注解之前需要在Spring配置文件进行配置，<context:annotation-config />。在启动spring IoC时，容器自动装载了一个AutowiredAnnotationBeanPostProcessor后置处理器，当容器扫描到@Autowied、@Resource或@Inject时，就会在IoC容器自动查找需要的bean，并装配给该对象的属性。在使用@Autowired时，首先在容器中查询对应类型的bean：

如果查询结果刚好为一个，就将该bean装配给@Autowired指定的数据；

如果查询的结果不止一个，那么@Autowired会根据名称来查找；

如果上述查找的结果为空，那么会抛出异常。解决方法时，使用required=false。

要启用`@Autowired`,你必须注册`AutowiredAnnotationBeanPostProcessor`,你可以用两种方式。

1. 在bean配置文件使用``。

```xml
<beans>
    <context:annotation-config />
</beans>
```



2. 直接将`AutowiredAnnotationBeanPostProcessor放到`bean配置文件。

```xml
<beans>
    <bean class="org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor"/>
</beans>
```



### @required 注解

在大规模的应用程序中,IoC容器中可能会有成百上千的bean声明,以及它们之间的依赖关系通常是非常复杂的。

setter注入的缺点之一是,很难给你检查出所需的所有属性是否已经注入。

为了克服这个问题,您可以设置bean的“dependency-check”属性，可以设置四个属性的其中之一即 none, simple, objects or all (没有一个是默认选项)。

在现实生活中应用程序中,您将不会感兴趣检查所有上下文中的bean属性配置文件。而你想要检查一些特定的bean是否已设置特定的属性。在这种情况下，Spring的依赖项检查功能将不再适用,。

为了解决这个问题,您可以使用`@Required`注解。

这个注解表明bean的属性必须在配置的时候设置，通过一个bean定义的显式的属性值或通过自动装配，若@Required注解的bean属性未被设置，容器将抛出BeanInitializationException。 

在bean属性使用`@Required`注解的setter方法类文件如下：

```java
public class EmployeeFactoryBean extends AbstractFactoryBean<Object>
{
    private String designation;
      
    public String getDesignation() {
        return designation;
    }
  
    @Required
    public void setDesignation(String designation) {
        this.designation = designation;
    }
      
    //more code here
}
```



### @autowired 注解

`@Autowired` 注解提供了更细粒度的控制，包括在何处以及如何完成自动装配。它的用法和@Required一样，修饰setter方法、构造器、属性或者具有任意名称和/或多个参数的PN方法。 

例如,您可以使用`@Autowired`注解的setter方法来代替在XML配置文件中的``元素。当Spring找到一个`@Autowired`注解的方法,它尝试使用byType自动注入的方法。

您可以将`@Autowired应用到`构造方法。一个构造方法使用`@Autowired注解`表明，即使在XML文件没有配置bean`的`元素，当创建bean时，构造方法也会自动注入.

```java
public class TextEditor {
   private SpellChecker spellChecker;
 
   @Autowired
   public TextEditor(SpellChecker spellChecker){
      System.out.println("Inside TextEditor constructor." );
      this.spellChecker = spellChecker;
   }
 
   public void spellCheck(){
      spellChecker.checkSpelling();
   }
}
```



### @qualifier 注解

当有多个相同类型的bean却只有一个需要自动装配时，将@Qualifier 注解和@Autowire 注解结合使用以消除这种混淆，指定需要装配的确切的bean。 

参见下面的例子,

```
public class Customer
{
    @Autowired
    private Person person;
}
```



### [Spring框架使用的设计模式](https://blog.csdn.net/chao821/article/details/92400186?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.nonecase&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.nonecase)

（1）工厂模式：BeanFactory就是简单工厂模式的体现，用来创建对象的实例；

（2）单例模式：Bean默认为单例模式。

（3）代理模式：Spring的AOP功能用到了JDK的动态代理和CGLIB字节码生成技术；

（4）模板方法：用来解决代码重复的问题。比如. RestTemplate, JmsTemplate, JpaTemplate；

（5）观察者模式：定义对象键一种一对多的依赖关系，当一个对象的状态发生改变时，所有依赖于它的对象都会得到通知被制动更新，如Spring中listener的实现--ApplicationListener；



### 使用Spring框架的好处

- 通过依赖注入(DI)方式,在构造方法或者java bean属性上，依赖关系是明确的和明显的。
- IoC容器往往是轻量级的,特别是与EJB容器相比。这是有利于在有限的内存和CPU资源的计算机上开发和部署应用程序。
- Spring不重新发明轮子，相反,它利用一些现有的技术如几个ORM框架,日志框架,JEE,quartz和JDK计时器,其他视图技术等。
- Spring是模块化的。尽管包和类很重要,你只关心你需要的模块,忽略其它模块。
- 在Spring测试应用程序很简单,因为依赖环境的代码被移入到框架本身。此外,通过使用JavaBean-style pojo方式,使用依赖注入注入测试数据变得更容易。
- Spring的web框架是一个设计良好的web MVC框架,它可以很好的替代其它web框架如struts或者其它web框架。
- Spring提供了一致的事务管理界面,可以管理小到一个本地事务(例如,使用一个数据库)和大到全局事务(例如,使用JTA)。



### Spring 事务的实现方式

- 编程式事务管理：这意味着你可以通过编程的方式管理事务，这种方式带来了很大的灵活性，但很难维护。
- 声明式事务管理：这种方式意味着你可以将事务管理和业务代码分离。你只需要通过注解或者XML配置管理事务。



###  Spring 事务的实现原理

 Spring事务的本质其实就是数据库对事务的支持，没有数据库的事务支持，spring是无法提供事务功能的。真正的数据库层的事务提交和回滚是通过binlog或者redo log实现的。 



### Spring 框架的事务管理有哪些优点

- 它为不同的事务API(如JTA, JDBC, Hibernate, JPA, 和JDO)提供了统一的编程模型。
- 它为编程式事务管理提供了一个简单的API而非一系列复杂的事务API(如JTA).
- 它支持声明式事务管理。
- 它可以和Spring 的多种数据访问技术很好的融合。



### Spring 事务定义的传播规则

- PROPAGATION_REQUIRED: 支持当前事务，如果当前没有事务，就新建一个事务。这是最常见的选择。
- PROPAGATION_SUPPORTS: 支持当前事务，如果当前没有事务，就以非事务方式执行。
- PROPAGATION_MANDATORY: 支持当前事务，如果当前没有事务，就抛出异常。
- PROPAGATION_REQUIRES_NEW: 新建事务，如果当前存在事务，把当前事务挂起。
- PROPAGATION_NOT_SUPPORTED: 以非事务方式执行操作，如果当前存在事务，就把当前事务挂起。
- PROPAGATION_NEVER: 以非事务方式执行，如果当前存在事务，则抛出异常。
- PROPAGATION_NESTED: 如果当前存在事务，则在嵌套事务内执行。如果当前没有事务，则进行与PROPAGATION_REQUIRED类似的操作。



###  Spring 中的隔离级别

① ISOLATION_DEFAULT：这是个 PlatfromTransactionManager 默认的隔离级别，使用数据库默认的事务隔离级别。

② ISOLATION_READ_UNCOMMITTED：读未提交，允许另外一个事务可以看到这个事务未提交的数据。

③ ISOLATION_READ_COMMITTED：读已提交，保证一个事务修改的数据提交后才能被另一事务读取，而且能看到该事务对已有记录的更新。

④ ISOLATION_REPEATABLE_READ：可重复读，保证一个事务修改的数据提交后才能被另一事务读取，但是不能看到该事务对已有记录的更新。

⑤ ISOLATION_SERIALIZABLE：一个事务在执行的过程中完全看不到其他事务对数据库所做的更新。



###  Spring 提供了5种标准事件 

（1）上下文更新事件（ContextRefreshedEvent）：在调用ConfigurableApplicationContext 接口中的refresh()方法时被触发。

（2）上下文开始事件（ContextStartedEvent）：当容器调用ConfigurableApplicationContext的Start()方法开始/重新开始容器时触发该事件。

（3）上下文停止事件（ContextStoppedEvent）：当容器调用ConfigurableApplicationContext的Stop()方法停止容器时触发该事件。

（4）上下文关闭事件（ContextClosedEvent）：当ApplicationContext被关闭时触发该事件。容器被关闭时，其管理的所有单例Bean都被销毁。

（5）请求处理事件（RequestHandledEvent）：在Web应用中，当一个http请求（request）结束触发该事件。

如果一个bean实现了ApplicationListener接口，当一个ApplicationEvent 被发布以后，bean会自动被通知。



---

### Spring M、V、C

MVC 是 Model、View 和 Controller 的缩写，分别代表 Web 应用程序中的 3 种职责。

- 模型：用于存储数据以及处理用户请求的业务逻辑。
- 视图：向控制器提交数据，显示模型中的数据。
- 控制器：根据视图提出的请求判断将请求和数据交给哪个模型处理，将处理后的有关结果交给哪个视图更新显示。

 基于 [Servlet](http://c.biancheng.net/servlet/) 的 MVC 模式的具体实现如下 ：

- 模型：一个或多个 JavaBean 对象，用于存储数据（实体模型，由 JavaBean 类创建）和处理业务逻辑（业务模型，由一般的 Java 类创建）。
- 视图：一个或多个 [JSP](http://c.biancheng.net/jsp/) 页面，向控制器提交数据和为模型提供数据显示，JSP 页面主要使用 HTML 标记和 JavaBean 标记来显示数据。
- 控制器：一个或多个 Servlet 对象，根据视图提交的请求进行控制，即将请求转发给处理业务逻辑的 JavaBean，并将处理结果存放到实体模型 JavaBean 中，输出给视图显示。



### Spring MVC 运行流程

第一步：发起请求到前端控制器(DispatcherServlet)

第二步：前端控制器请求HandlerMapping查找 Handler（ 可以根据xml配置、注解进行查找）

第三步：处理器映射器HandlerMapping向前端控制器返回Handler

第四步：前端控制器调用处理器适配器去执行Handler

第五步：处理器适配器去执行Handler

第六步：Handler执行完成给适配器返回ModelAndView

第七步：处理器适配器向前端控制器返回ModelAndView（ModelAndView是springmvc框架的一个底层对象，包括Model和view）

第八步：前端控制器请求视图解析器去进行视图解析（根据逻辑视图名解析成真正的视图(jsp)）

第九步：视图解析器向前端控制器返回View

第十步：前端控制器进行视图渲染（ 视图渲染将模型数据(在ModelAndView对象中)填充到request域）

第十一步：前端控制器向用户响应结果



 ### Spring MVC 的优点

（1）可以支持各种视图技术,而不仅仅局限于JSP；

（2）与Spring框架集成（如IoC容器、AOP等）；

（3）清晰的角色分配：前端控制器(dispatcherServlet) , 请求到处理器映射（handlerMapping), 处理器适配器（HandlerAdapter), 视图解析器（ViewResolver）；

（4） 支持各种请求资源的映射策略。



### Spring MVC 的主要组件

（1）前端控制器 DispatcherServlet（不需要程序员开发）

作用：接收请求、响应结果，相当于转发器，有了DispatcherServlet 就减少了其它组件之间的耦合度。

（2）处理器映射器HandlerMapping（不需要程序员开发）

作用：根据请求的URL来查找Handler

（3）处理器适配器HandlerAdapter

注意：在编写Handler的时候要按照HandlerAdapter要求的规则去编写，这样适配器HandlerAdapter才可以正确的去执行Handler。

（4）处理器Handler（需要程序员开发）

（5）视图解析器 ViewResolver（不需要程序员开发）

作用：进行视图的解析，根据视图逻辑名解析成真正的视图（view）

（6）视图View（需要程序员开发jsp）

View是一个接口， 它的实现类支持不同的视图类型（jsp，freemarker，pdf等等）



### [Spring MVC 和 Struts2 的区别](https://blog.csdn.net/chenleixing/article/details/44570681)

（1）springmvc的入口是一个servlet即前端控制器（DispatchServlet），而struts2入口是一个filter过虑器（StrutsPrepareAndExecuteFilter）。

（2）springmvc是基于方法开发(一个url对应一个方法)，请求参数传递到方法的形参，可以设计为单例或多例(建议单例)，struts2是基于类开发，传递参数是通过类的属性，只能设计为多例。

（3）Struts采用值栈存储请求和响应的数据，通过OGNL存取数据，springmvc通过参数解析器是将request请求内容解析，并给方法形参赋值，将数据和视图封装成ModelAndView对象，最后又将ModelAndView中的模型数据通过reques域传输到页面。Jsp视图解析器默认使用jstl



### [Spring MVC 其他问题](https://blog.csdn.net/a745233700/article/details/80963758)

http://c.biancheng.net/spring_mvc/

