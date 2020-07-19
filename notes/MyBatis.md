## MyBatis



### JDBC编程有哪些不足，Mybatis如何解决

（1）数据库连接频繁的创建、释放造成系统资源浪费从而影响了性能。 

​		解决：在SQLMapConfig.xml中配置数据连接池，使用数据库连接池管理数据库连接。 

（2）SQL语句在写代码中不容易维护，事件需求中SQL变化的可能性很大，SQL变动需要改变JAVA代码。

​		解决：将SQL语句配置在mapper.xml文件中与java代码分离。

（3）向SQL语句传递参数麻烦，因为SQL语句的where条件不一定，可能多，也可能少，占位符需要和参数一一对应。

​		解决：Mybatis自动将java对象映射到sql语句。

（4）对结果集解析麻烦，sql变化导致解析代码变化，且解析前需要遍历，如果能将数据库记录封装成pojo对象解析比较方便。

​		解决：Mybatis自动将SQL执行结果映射到java对象。 



### 什么是 Mybatis

MyBatis 是一个可以自定义 SQL、存储过程和高级映射的持久层框架。

（1）Mybatis是一个**半ORM**（对象关系映射）框架，它内部封装了JDBC，开发时只需要关注SQL语句本身，不需要花费精力去处理加载驱动、创建连接、创建statement等繁杂的过程。程序员直接编写原生态sql，可以严格控制sql执行性能，灵活度高。

（2）MyBatis 可以使用 XML 或注解来配置和映射原生信息，将 POJO映射成数据库中的记录，避免了几乎所有的 JDBC 代码和手动设置参数以及获取结果集。

（3）通过xml 文件或注解的方式将要执行的各种 statement 配置起来，并通过java对象和 statement中sql的动态参数进行映射生成最终执行的sql语句，最后由mybatis框架执行sql并将结果映射为java对象并返回。（从执行sql到返回result的过程）。



### 为什么说 Mybatis 是半自动 ORM 映射工具

Hibernate属于全自动ORM映射工具，使用Hibernate查询关联对象或者关联集合对象时，可以根据对象关系模型直接获取，所以它是全自动的。

而Mybatis在查询关联对象或关联集合对象时，需要手动编写sql来完成，所以称之为半自动ORM映射工具。



### Mybatis 与 Hibernate 有哪些不同

（1）Mybatis和hibernate不同，它不完全是一个ORM框架，因为MyBatis需要程序员自己编写Sql语句。

（2）Mybatis直接编写原生态sql，可以严格控制sql执行性能，灵活度高，非常适合对关系数据模型要求不高的软件开发，因为这类软件需求变化频繁，一但需求变化要求迅速输出成果。但是灵活的前提是mybatis无法做到数据库无关性，如果需要实现支持多种数据库的软件，则需要自定义多套sql映射文件，工作量大。 

（3）Hibernate对象/关系映射能力强，数据库无关性好，对于关系模型要求高的软件，如果用hibernate开发可以节省很多代码，提高效率。 



###  Mybaits 的优点/缺点/场合

**优点：**

（1）基于SQL语句编程，相当灵活，不会对应用程序或者数据库的现有设计造成任何影响，SQL写在XML里，解除sql与程序代码的耦合，便于统一管理；提供XML标签，支持编写动态SQL语句，并可重用。

（2）与JDBC相比，减少了50%以上的代码量，消除了JDBC大量冗余的代码，不需要手动开关连接；

（3）很好的与各种数据库兼容（因为MyBatis使用JDBC来连接数据库，所以只要JDBC支持的数据库MyBatis都支持）。

（4）能够与Spring很好的集成；

（5）提供映射标签，支持对象与数据库的ORM字段关系映射；提供对象关系映射标签，支持对象关系组件维护。

**缺点：**

（1）SQL语句的编写工作量较大，尤其当字段多、关联表多时，对开发人员编写SQL语句的功底有一定要求。

（2）SQL语句依赖于数据库，导致数据库移植性差，不能随意更换数据库。

**使用场合：**

（1）MyBatis专注于SQL本身，是一个足够灵活的DAO层解决方案。

（2）对性能的要求很高，或者需求变化较多的项目，如互联网项目，MyBatis将是不错的选择。



### Mybatis 编程步骤

Step1：创建SQLSessionFactory

Step2：通过SQLSessionFactory创建SQLSession

Step3：通过SQLSession执行数据库操作

Step4：调用session.commit()提交事物

Step5：调用session.close()关闭会话



### [Mybatis 的映射文件](https://blog.csdn.net/majinggogogo/article/details/72123185)

Mybatis 真正强大的在于它的映射文件，它和JDBC代码进行比较，可以省掉95%的代码，Mybatis就是针对SQL进行构建。 SQL映射文件中几个顶级的元素：

• cache – 给定命名空间的缓存配置

• cache-ref – 其他命名空间缓存配置的引用

• resultMap – 是最复杂也是最强大的元素，用来描述如何从数据库结果集中来加载对象

• sql – 可被其他语句引用的可重用语句块

• insert – 映射插入语句

• update – 映射更新语句

• delete – 映射删除语句

• select – 映射查询语句 



### [Mybatis 动态SQL](https://www.cnblogs.com/ashleyboy/p/9271597.html)

Mybatis提供了9种动态sql标签：trim|where|set|foreach|if|choose|when|otherwise|bind。 

**作用**：可以让我们在Xml映射文件内，以标签的形式编写动态sql，完成逻辑判断和动态拼接sql的功能 。

**执行原理**：使用OGNL从sql参数对象中计算表达式的值，根据表达式的值动态拼接sql，以此来完成动态sql的功能。 



### Mybatis 分页查询

Mybatis 使用 RowBounds 对象进行分页查询，但是并不是正真的分页查询，它是把数据查出来放在内存里面，你想要什么就给你什么。要想实现真分页查询，就要用sql语句来实现。MySQL和Oracle两种数据库的实现方法是不一样的。 

MySQL：

```mysql
mysql> SELECT * FROM table LIMIT 5,10; // 检索记录行 6-15  
     
mysql> SELECT * FROM table LIMIT 95,-1; // 检索 96 及之后的所有记录行 
   
mysql> SELECT * FROM table LIMIT 5; // 检索前 5 个记录行  
```

 Oracle实现分页查询：采用伪列ROWNUM 



### Mybais 常用注解

```java
@Insert ： 插入sql , 和xml insert sql语法完全一样
@Select ： 查询sql,  和xml select sql语法完全一样
@Update ： 更新sql,  和xml update sql语法完全一样
@Delete ： 删除sql,  和xml delete sql语法完全一样
@Param ：  入参
@Results ：结果集合
@Result ： 结果
```



### Mybatis 的表关联的映射

（1）一对一关联 Property: 对象属性名称 javaType: 对象属性的类型 column: 所对应的外键字段的名称 select: 使用另一个查询封装的结果 

（2）一对多关联 

（3）多对多关联 

```xml
<mapper namespace="com.lcb.mapping.userMapper">  
    <!--association  一对一关联查询 -->  
    <select id="getClass" parameterType="int" resultMap="ClassesResultMap">  
        select * from class c,teacher t where c.teacher_id=t.t_id and c.c_id=#{id}  
    </select>  
    <resultMap type="com.lcb.user.Classes" id="ClassesResultMap">  
        <!-- 实体类的字段名和数据表的字段名映射 -->  
        <id property="id" column="c_id"/>  
        <result property="name" column="c_name"/>  
        <association property="teacher" javaType="com.lcb.user.Teacher">  
            <id property="id" column="t_id"/>  
            <result property="name" column="t_name"/>  
        </association>  
    </resultMap>  

    <!--collection  一对多关联查询 -->  
    <select id="getClass2" parameterType="int" resultMap="ClassesResultMap2">  
        select * from class c,teacher t,student s where c.teacher_id=t.t_id and c.c_id=s.class_id and c.c_id=#{id}  
    </select>  
    <resultMap type="com.lcb.user.Classes" id="ClassesResultMap2">  
        <id property="id" column="c_id"/>  
        <result property="name" column="c_name"/>  
        <association property="teacher" javaType="com.lcb.user.Teacher">  
            <id property="id" column="t_id"/>  
            <result property="name" column="t_name"/>  
        </association>  
        <collection property="student" ofType="com.lcb.user.Student">  
            <id property="id" column="s_id"/>  
            <result property="name" column="s_name"/>  
        </collection>  
    </resultMap>  

</mapper>  
```



### Mybatis 实现一对一

**实现方式：** 联合查询、嵌套查询 

（1）联合查询是几个表联合查询,只查询一次, 通过在resultMap里面配置`association`节点配置一对一的类就可以完成； 

（2）嵌套查询 是先查一个表，根据这个表里面的结果的外键id，再去另外一个表里面查询数据，也是通过`association`配置，但另外一个表的查询通过select属性配置  



### MyBatis 实现一对多

**实现方式：**联合查询和嵌套查询。

（1）联合查询是几个表联合查询,只查询一次,通过在resultMap里面的`collection`节点配置一对多的类就可以完成；

（2）嵌套查询是先查一个表，根据这个表里面的结果的外键id，再去另外一个表里面查询数据，也是通过配置`collection`，但另外一个表的查询通过select节点配置。



### Mybatis 延迟加载

Mybatis仅支持association关联对象和collection关联集合对象的延迟加载，association指的就是一对一，collection指的就是一对多查询。

在Mybatis配置文件中，可以配置是否启用延迟加载lazyLoadingEnabled=true|false。

它的原理是，使用CGLIB创建目标对象的代理对象，当调用目标方法时，进入拦截器方法，比如调用a.getB().getName()，拦截器invoke()方法发现a.getB()是null值，那么就会单独发送事先保存好的查询关联B对象的sql，把B查询上来，然后调用a.setB(b)，于是a的对象b属性就有值了，接着完成a.getB().getName()方法的调用。这就是延迟加载的基本原理。

当然了，不光是Mybatis，几乎所有的包括Hibernate，支持延迟加载的原理都是一样的。



### Mybatis 不同的Xml映射文件id是否可以重复

答：不同的Xml映射文件，如果配置了namespace，那么id可以重复；如果没有配置namespace，那么id不能重复；毕竟namespace不是必须的，只是最佳实践而已。

原因就是namespace+id是作为Map<String, MappedStatement>的key使用的，如果没有namespace，就剩下id，那么，id重复会导致数据互相覆盖。有了namespace，自然id就可以重复，namespace不同，namespace+id自然也就不同。



###  [Mybatis 的一级、二级缓存](https://www.cnblogs.com/happyflyingpig/p/7739749.html)

（1）一级缓存是SqlSession级别的缓存。在操作数据库时需要构造sqlSession对象，在对象中有一个数据结构（HashMap）用于存储缓存数据。不同的sqlSession之间的缓存数据区域（HashMap）是互相不影响的。 在同一个SqlSession中，执行相同的查询SQL，第一次会去数据库进行查询，并写到缓存中；第二次以后是直接去缓存中取。当执行SQL查询中间发生了增删改的操作，MyBatis会把SqlSession的缓存清空。 

（2） 二级缓存是mapper级别的缓存，多个SqlSession去操作同一个Mapper的sql语句，多个SqlSession可以共用二级缓存，二级缓存是跨SqlSession的。  UserMapper有一个二级缓存区域（按namespace分），其它mapper也有自己的二级缓存区域（按namespace分）。每一个namespace的mapper都有一个二级缓存区域，两个mapper的namespace如果相同，这两个mapper执行sql查询到数据将存在相同的二级缓存区域中。 

（3）对于缓存数据更新机制，当某一个作用域(一级缓存 Session/二级缓存Namespaces)的进行了C/U/D 操作后，默认该作用域下所有 select 中的缓存将被 clear。



### MyBatis 的接口绑定

接口绑定就是在MyBatis中任意定义接口，然后把接口里面的方法和SQL语句绑定，我们直接调用接口方法就可以，这样比起原来了SqlSession提供的方法我们可以有更加灵活的选择和设置。 

接口绑定有两种实现方式：

- 一种是通过注解绑定，就是在接口的方法上面加上@Select、@Update等注解里面包含Sql语句来绑定；
- 另外一种就是通过xml里面写SQL来绑定，在这种情况下要指定xml映射文件里面的namespace必须为接口的全路径名.



### MyBatis mapper 接口

调用Mapper接口时的要求:

① Mapper接口方法名和mapper.xml中定义的每个sql的id相同；
② Mapper接口方法的输入参数类型和mapper.xml中定义的每个sql 的parameterType的类型相同；
③ Mapper接口方法的输出参数类型和mapper.xml中定义的每个sql的resultType的类型相同；
④ Mapper.xml文件中的namespace即是mapper接口的类路径。 

Mapper 接口编写方式：

**方式1** 接口实现类集成SQLSessionDaoSupport：使用此种方法需要编写mapper接口，mapper接口实现类、mapper.xml文件；

**方式2** 使用org.mybatis.spring.mapper.MapperFactoryBean：此方法需要在SqlMapConfig.xml中配置mapper.xml的位置，还需定义mapper接口；

**方式3**：使用mapper扫描器：需要编写mapper.xml文件，需要mapper接口，配置mapper扫描器，使用扫描器从spring容器中获取mapper的实现对象。 



### Mybatis 的插件运行原理

**原理：**Mybatis仅可以编写针对ParameterHandler、ResultSetHandler、StatementHandler、Executor这4种接口的插件，Mybatis使用JDK的动态代理，为需要拦截的接口生成代理对象以实现接口方法拦截功能，每当执行这4种接口对象的方法时，就会进入拦截方法，具体就是InvocationHandler的invoke()方法，当然，只会拦截那些你指定需要拦截的方法。

**编写插件：**实现Mybatis的Interceptor接口并复写intercept()方法，然后在给插件编写注解，指定要拦截哪一个接口的哪些方法即可，记住，别忘了在配置文件中配置你编写的插件。



### #{} 和 ${} 的区别是什么

#{}是预编译处理，${}是字符串替换。

Mybatis在处理#{}时，会将sql中的#{}替换为?号，调用PreparedStatement的set方法来赋值；

Mybatis在处理\${}时，就是把\${}替换成变量的值。

使用#{}可以有效的防止SQL注入，提高系统安全性。

```sql
用法
select * from user where name = #{name}; 
select * from user where name = '${name}'; 
```

