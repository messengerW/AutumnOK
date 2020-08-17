## Java 基础

###  Queue.add()/offer()

①add()和offer()都是向队列尾部插入一个元素，区别是当超出队列界限时，add方法会抛出异常，而offer()会返回false。

②remove()和poll()都是从队列头部移除一个元素并返回，区别是队列为空时remove()方法会抛出异常，poll()方法则是返回null值。

③element()和 peek() 都是用于查询队列头部的元素，区别时队列为空时， element() 抛出一个异常，而 peek() 返回 null。



### String、StringBuffer、StringBuilder的区别

String：字符串常量，字符串长度不变，是被声明程final的，一次赋值不可改变

StringBuffer：字符串变量，线程安全，如果需要频繁的对字符串内容进行修改，处于效率考虑最好使用StringBuffer;

StringBuilder：字符串变量，非线程安全，与StringBuffer的区别是StringBuilder它是在单线程环境下使用的，所以效率要比StringBuffer。

---

String 和 StringBuffer、StringBuilder 的区别在于 String 声明的是不可变的对象，每次操作都会生成新的 String 对象，然后将指针指向新的 String 对象，而 StringBuffer、StringBuilder 可以在原有对象的基础上进行操作，所以在经常改变字符串内容的情况下最好不要使用 String。

StringBuffer 和 StringBuilder 最大的区别在于，StringBuffer 是**线程安全**的，而 StringBuilder 是**非线程安全**的，但 StringBuilder 的性能却高于 StringBuffer，所以在单线程环境下推荐使用 StringBuilder，多线程环境下推荐使用 StringBuffer。

---

String str="i"的方式，Java 虚拟机会将其分配到**常量池**中；

而 String str=new String("i") 则会被分到**堆内存**中。 

---

使用 StringBuilder 或者 stringBuffer 的 reverse() 方法 **反转字符串**



String 类常用方法：

1.  indexOf(): 返回指定字符的索引值
2. charAt(): 返回指定索引处的字符
3. replace(char oldChar, char newChar): 替换指定字符
4. trim(): 去除首位空白
5. toLowerCase()、toUpperCase()
6. subString(int beginIndex, int endIndex): 截取字符串
7. equals(): 比较字符串是否相等（值比较） 



### Java 字符串拼接

①使用+直接拼接，String 是final对象，不会被修改，每次使用 +进行拼接都会创建新的对象，而不是改变原来的对象，效率低，是线程安全的。

②使用StringBuffer可变字符串，效率较高，是线程安全的（StringBuffer的方法使用了synchronized关键字进行修饰）。

③使用StringBuilder可变字符串，效率最高，但是线程不安全。


###  String 类的常用方法

- indexOf()：返回指定字符的索引。
- charAt()：返回指定索引处的字符。
- replace()：字符串替换。
- trim()：去除字符串两端空白。
- split()：分割字符串，返回一个分割后的字符串数组。
- getBytes()：返回字符串的 byte 类型数组。
- length()：返回字符串长度。
- toLowerCase()：将字符串转成小写字母。
- toUpperCase()：将字符串转成大写字符。
- substring()：截取字符串。
- equals()：字符串比较。



### Files的常用方法

- Files. exists()：检测文件路径是否存在。
- Files. createFile()：创建文件。
- Files. createDirectory()：创建文件夹。
- Files. delete()：删除一个文件或目录。
- Files. copy()：复制文件。
- Files. move()：移动文件。
- Files. size()：查看文件个数。
- Files. read()：读取文件。
- Files. write()：写入文件。



### Java 数据类型

1. **基本数据类型**包括：数值型（byte、short、int、long、float、double），字符型（char）以及布尔型（boolean）。

2. 除了基本类型外，其他数据类型都属于**引用类型**，包括类、接口、数组等。 

![](数据类型.jpg)



### int和Intneger有什么区别

Java是一个近乎纯洁的面向对象编程语言，但是为了编程的方便还是引入了基本数据类型，但是为了能够将这些基本数据类型当成对象操作，Java为每一个基本数据类型都引入了对应的包装类型（wrapper class），**int的包装类就是Integer**，从Java 5开始引入了自动装箱/拆箱机制，使得二者可以相互转换。
Java 为每个原始类型提供了包装类型：

原始类型: boolean，char，byte，short，int，long，float，double
包装类型：Boolean，Character，Byte，Short，Integer，Long，Float，Double





### final、finally和finalize区别

①final可以修饰类，方法和变量，被final修饰的类不可继承，被final修饰的方法不可重写，被final修饰的变量叫常量，常量必须初始化，初始化之后值就不能被修改。 

②finally用于try-catch代码块中，无论是否发生异常最后都将执行，作用是释放资源。

③finalize是Object类的方法，在对象被垃圾回收之前将调用一次，一般用于资源的释放。



### == 和 equals 

== ：引用类型，比较的是引用对象是否相等；基本类型，比较两个值是否相等

```java
/*因为 x 和 y 指向的是同一个引用，所以 == 也是 true，而 new String()方法则重写开辟了内存空间，
 *所以 == 结果为 false，而 equals 比较的一直是值，所以结果都为 true。*/

String x = "string";
String y = "string";
String z = new String("string");
System.out.println(x==y); // true
System.out.println(x==z); // false
System.out.println(x.equals(y)); // true
System.out.println(x.equals(z)); // true
```



**equals 解读**

equals 本质上就是 ==，只不过 String 和 Integer 等重写了 equals 方法，把它变成了值比较。 所以一般情况下 equals 比较的是值是否相等。 



###  抽象类相关

1. 抽象类必须要有抽象方法吗？

   答：不需要，抽象类不一定非要有抽象方法。

2. 普通类和抽象类有哪些区别？

- 普通类不能包含抽象方法，抽象类可以包含抽象方法。
- 抽象类不能直接实例化，普通类可以直接实例化。

3. 抽象类能使用 final 修饰吗？

   不能，定义抽象类就是让其他类继承的，如果定义为 final 该类就不能被继承，这样彼此就会产生矛盾，所以 final 不能修饰抽象类。

4. 接口和抽象类有什么区别？

   - 实现：抽象类的子类使用 extends 来继承；接口必须使用 implements 来实现接口。
   - 构造函数：抽象类可以有构造函数；接口不能有。
   - 实现数量：类可以实现很多个接口；但是只能继承一个抽象类。
   - 访问修饰符：接口中的方法默认使用 public 修饰；抽象类中的方法可以是任意访问修饰符。



### &和&&

①&具有按位与和逻辑与两个功能。

②&&作为逻辑与具有短路的特点，当前面的条件表达式为false时就不会进行后面条件表达式的判断，可以用来避免空指针异常。 



### Array和ArrayList

①Array长度在定义之后就不运行改变了，而ArrayList是长度可变的，可以自动扩容。

②Array只能存储相同类型的数据，ArrayList可以存储不同类型的数据。

③ArrayList提供了更多操作数据的方法。 



### ArrayList 和 LinkedList

ArrayList和LinkedList都实现了List接口，有以下的不同点：

- 数据结构实现：ArrayList 是动态数组的数据结构实现，而 LinkedList 是双向链表的数据结构实现。
- 随机访问效率：ArrayList 比 LinkedList 在随机访问的时候效率要高，因为 LinkedList 是线性的数据存储方式，所以需要移动指针从前往后依次查找。
- 增加和删除效率：在非首尾的增加和删除操作，LinkedList 要比 ArrayList 效率要高，因为 ArrayList 增删操作要影响数组内的其他数据的下标。



### Files的常用方法

- Files. exists()：检测文件路径是否存在。
- Files. createFile()：创建文件。
- Files. createDirectory()：创建文件夹。
- Files. delete()：删除一个文件或目录。
- Files. copy()：复制文件。
- Files. move()：移动文件。
- Files. size()：查看文件个数。
- Files. read()：读取文件。
- Files. write()：写入文件。



### BIO、NIO、AIO

- BIO：Block IO 同步阻塞式 IO，就是我们平常使用的传统 IO，它的特点是模式简单使用方便，并发处理能力低。
- NIO：Non IO 同步非阻塞 IO，是传统 IO 的升级，客户端和服务器端通过 Channel（通道）通讯，实现了多路复用。
- AIO：Asynchronous IO 是 NIO 的升级，也叫 NIO2，实现了异步非堵塞 IO ，异步 IO 的操作基于事件和回调机制。



### Java 中 IO 流

按功能来分：输入流（input）、输出流（output）。

按类型来分：字节流和字符流。

字节流和字符流的区别是：字节流按 8 位传输以字节为单位输入输出数据，字符流按 16 位传输以字符为单位输入输出数据。



### Java 容器

Java 容器分为 Collection 和 Map 两大类，其下又有很多子类，如下所示：

==Collection==

​		**List**

- ArrayList

- LinkedList

- Vector

- Stack

  **Set**

- HashSet

- LinkedHashSet

- TreeSet

==Map==

- HashMap
  - LinkedHashMap
- TreeMap
- ConcurrentHashMap
- Hashtable



### 哪些集合类是线程安全的

①Vector，是线程安全的ArrayList，底层用数组实现，通过synchronized修饰方法保证线程安全

②HashTable，是线程安全的HashMap，继承自Dictionary，通过synchronized修饰方法保证线程安全，性能较差。

③ConcurrentHashMap，线程安全的HashMap，通过分段锁实现线程安全，性能较好。



### Collection 和 Collections

- Collection 是一个集合接口，它提供了对集合对象进行基本操作的通用接口方法，所有集合都是它的子类，比如 List、Set 等。
- Collections 则是Collection的一个工具类，为Collection类型的对象提供了很多方便的方法，例如addAll可以直接对Collection集合批量添加元素，shuffle可以随机打乱List集合的元素顺序，sort可以对List集合进行默认或按比较器进行排序。



### 迭代器  Iterator 

迭代器是一种设计模式，它是一个对象，它可以遍历并选择序列中的对象，而开发人员不需要了解该序列的底层结构。迭代器通常被称为“轻量级”对象，因为创建它的代价小。 

①迭代器实现了Iterator接口，是用于遍历Collection集合元素的一个指针。

②主要有三个方法：通过iterator()获得集合的迭代器；通过hasNext()判断集合当中是否还有元素，如果有返回true，没有则返回false，初始时迭代器位于第一个元素之前；通过next()获取集合的下一个元素，并向后移动一个元素的单位。

使用foreach循环遍历元素集合时不能修改或删除元素，通过java -c查看字节码可以发现foreach循环实际上是用Iterator迭代器实现的，如果进行添加或删除元素会抛出ConcurrentModificationException异常，因为添加或删除元素会改变modCount的值，modCount是集合类的一个成员变量，代表集合的修改次数，当modCount的值和预期的exceptedModCount值不一致时就会抛出ConcurrentModificationException异常。

(1) 使用方法iterator()要求容器返回一个Iterator。第一次调用Iterator的next()方法时，它返回序列的第一个元素。注意：iterator()方法是java.lang.Iterable接口,公共基类Collection提供iterator()方法。

(2) 使用next()获得序列中的下一个元素。

(3) 使用hasNext()检查序列中是否还有元素。

(4) 使用remove()将迭代器新返回的元素删除。




### List、Set、Map 区别

List、Set、Map 的区别主要体现在两个方面：元素是否有序、是否允许元素重复。 

![](list、set、map.jpg)



### HashMap 和 Hashtable 有什么区别

- 存储：HashMap 运行 key 和 value 为 null，而 Hashtable 不允许。
- 线程安全：Hashtable 是线程安全的，而 HashMap 是非线程安全的。
- 推荐使用：在 Hashtable 的类注释可以看到，Hashtable 是保留类不建议使用，推荐在单线程环境下使用 HashMap 替代，如果需要多线程使用则用 ConcurrentHashMap 替代。



### HashMap 实现原理

HashMap 基于 Hash 算法实现的，我们通过 put(key,value)存储，get(key)来获取。当传入 key 时，HashMap 会根据 key. hashCode() 计算出 hash 值，根据 hash 值将 value 保存在 bucket 里。当计算出的 hash 值相同时，我们称之为 hash 冲突，HashMap 的做法是用链表和红黑树存储相同 hash 值的 value。当 hash 冲突的个数比较少时，使用链表否则使用红黑树。 



### HashSet 实现原理

HashSet 是基于 HashMap 实现的，HashSet 底层使用 HashMap 来保存所有元素，因此 HashSet 的实现比较简单，相关 HashSet 的操作，基本上都是直接调用底层 HashMap 的相关方法来完成，HashSet 不允许重复的值。 



### 数组和 List 之间的转换

- 数组转 List：使用 Arrays. asList(array) 进行转换。
- List 转数组：使用 List 自带的 toArray() 方法。



###  十种常见的异常

​	IOException
​     FileNotFoundException
​     RunnTimeException
​     ArithmeticException
​     lllegalArgumentException
​     ArrayIndexOutOfBoundsException
​	 NullPointerException
​	 ArrayStoreException
​	 ClassCastException
​	 NumberFormatException



### 简述JDK8的新特性

①接口中可以添加default修饰的非抽象方法，可以有方法体和内容。

②可以使用lambda表达式，减少代码冗余。

③函数式接口，使用@FunctionalInterface注解标明，该接口有且仅有一个抽象方法。

④方法引用，可以直接引用已有Java类或对象的方法或构造器，进一步简化lambda表达式。

⑤stream流，用于解决已有集合/数组类库的弊端，简化其操作，有foreach遍历、filter过滤、map映射、concat合并等功能。

⑥增加日期相关的API。



### JDK 和 JRE 有什么区别

JDK：Java Development Kit 的简称，java 开发工具包，提供了 java 的开发环境和运行环境。

JRE：Java Runtime Environment 的简称，java 运行环境，为 java 的运行提供了所需环境。具体来说 JDK 其实包含了 JRE，同时还包含了编译 java 源码的编译器 javac，还包含了很多 java 程序调试和分析的工具。简单来说：如果你需要运行 java 程序，只需安装 JRE 就可以了，如果你需要编写 java 程序，需要安装 JDK。



### public、private、protected、default

- **default** (即默认，什么也不写）: 在同一包内可见，不使用任何修饰符。使用对象：类、接口、变量、方法。
- **private** : 在同一类内可见。使用对象：变量、方法。 **注意：不能修饰类（外部类）**
- **public** : 对所有类可见。使用对象：类、接口、变量、方法
- **protected** : 对同一包内的类和所有子类可见。使用对象：变量、方法。 **注意：不能修饰类（外部类）**。



### 类、抽象类、接口

**类与抽象类：**

- 抽象类包含抽象方法，普通类没有；
- 类可以被直接实例化，抽象类不可直接实例化（new），但可以通过父类的引用来指向子类的实例间接对父类实例化（因为子类实例化之前会先实例化其父类）；

```java
abstract class B {
    private String str;
    
    public B(String a) {
        System.out.println("父类已经实例化");
        this.str = a;
        System.out.println(str);
    }
    
    public abstract void play();
}

public class A extends B{

    public A(String a) {
        super(a);
        System.out.println("子类已经实例化");
    }

    @Override
    public void play() {
        System.out.println("我实现了父类的抽象方法");
    }
    
    public static void main(String[] args) {
        B aa=new A("a");
    }
}

// output

父类已经实例化
a
子类已经实例化
```



**抽象类与接口：**

- 抽象类可以被**继承（extends）**，接口可以被**实现（implement）**；
- 类只可以继承一个抽象类，但可以实现多个接口；
- 抽象类可以有构造方法，而接口没有；
- 抽象类可以有mian方法，接口不能有；
- 方法访问修饰符：接口只能是public，抽象类可以是public、protected、default、private
- 变量声明：接口需用 static final修饰





## Java 多线程

### 进程与线程

**进程** 是计算机中的程序关于某数据集合上的一次运行活动，是**系统进行资源分配和调度的基本单位**，是操作系统结构的基础。 在当代面向线程设计的计算机结构中，**进程是线程的容器**。程序是指令、数据及其组织形式的描述，进程是程序的实体。是计算机中的程序关于某数据集合上的一次运行活动，是系统进行资源分配和调度的基本单位，是操作系统结构的基础。程序是指令、数据及其组织形式的描述，**进程是程序的实体**。 

**线程** 是**操作系统能够进行运算调度的最小单位**。它被包含在进程之中，是**进程中的实际运作单位**。一条线程指的是进程中一个单一顺序的控制流，一个进程中可以并发多个线程，每条线程并行执行不同的任务。

**进程与线程的区别：**

1. 线程是程序执行的最小单位，而进程是操作系统分配资源的最小单位；

2. 一个进程由一个或多个线程组成，线程是一个进程中代码的不同执行路线；

3. 进程之间相互独立，但同一进程下的各个线程之间共享程序的内存空间(包括代码段，数据集，堆等)及一些进程级的资源(如打开文件和信号等)，某进程内的线程在其他进程不可见；

4. 调度和切换：线程上下文切换比进程上下文切换要快得多。



### 创建线程有哪几种方式

①继承Thread类，重写run()方法即可。

②实现Runnable接口，重写run()方法，并将该实现类作为参数传入Thread(target)构造器。

③实现Callable接口，重写call()方法，并包装成FutureTask对象，再作为参数传入Thread构造器。优点是相比方式二可以获取返回值，缺点是实现复杂

④可以通过线程池创建



#### Thread 和 Runnable

Thread：优点是编码简单，缺点是不能继承其他类，功能单一；

Runnable：优点是可以继承其他类，**避免了单继承的局限性**；适合多个相同程序代码的线程共享一个资源（同一个线程任务对象可被包装成多个线程对象），**实现解耦操作，代码和线程独立**，缺点是实现相对复杂



#### Runnable 和 Callable

Runnable接口中的run()方法的返回值是void ；

Callable接口中的call()方法是有返回值的，是一个泛型，和Future、FutureTask配合可以用来获取异步执行的结果 



### 线程有哪些状态

- New：新创建一个线程是处于该状态。

- Runnable：线程的调度是由操作系统可以决定，因此Runnable是包含Ready和Running。当我们调用了start()方法后，当前的线程处于一个Ready的状态，等待操作系统线程调用到当前线程分配CPU执行时间，若当前线程获得CPU执行时间时，线程就处于一个Running的状态。而在Running状态的情况下，我们可以调用yield()方法，放弃当前线程的CPU执行。而调用yield后当前线程处于一个Ready的状态，这种状态下操作系统在线程调度的时候分配CPU执行时间给当前的线程。

- Blocked：阻塞状态下代表着当前的线程被挂起。而这挂起的原因的线程在等待一个锁。如我们在一个方法或者代码块中使用Synchronized时，同一时间有2个线程进入该方法的时候，先获取到锁的线程执行。而没有获得锁的线程就处于这种阻塞状态。

- WAITING：等待状态下，当前线程不被执行和操作系统不会给该线程进行线程调度。而当前线程处于等待其他线程唤醒。只有被唤醒后，操作系统才会给该线程进行线程调度。这种线程的等待的主要作用是为了线程之间的协作。一般情况下通过Synchronized获得锁后，调用锁的wait的方法进入等待状态，而调用wait方法后，当前的线程会释放锁，而另外一个线程获得锁后，通过notifyall()/notify()进行唤醒处于等待的线程。

- TIMED_WAITING：处于这种有限期的等待的情况下，在限期内当前线程不会被执行和操作系统不会给该线程进行线程调度。在限期过后，操作系统才给该线程进行线程调度。

- TERMINATED：该状态下线程处于终止，而这种终止引起的原因分为正常的执行完毕的终止和非正常情况下的终止，而非正常情况下可能是线程执行异常或者调用interrupt()中止线程引起。



<img src="线程状态转换.png" style="zoom:67%;" />



### start() & run()

start()方法用来启动线程，真正实现了多线程运行。这时无需等待run方法体代码执行完毕，可以直接继续执行下面的代码；通过调用Thread类的start()方法来启动一个线程， 这时此线程是**处于就绪状态， 但并没有运行**。 一旦得到cpu时间片，就开始执行run()方法，这里的run()方法称为线程体，它包含了要执行的这个线程的内容，Run方法运行结束，此线程随即终止，然后CPU再调度其它线程。

run()方法只是类的一个普通方法而已，如果直接调用Run方法，程序中依然只有主线程这一个线程，其程序执行路径还是只有一条，还是要顺序执行，还是要等待run方法体执行完毕后才可继续执行下面的代码，这样就没有达到写线程的目的。



### sleep() & wait()

sleep()方法是Thread类的静态方法，是线程用来控制自身流程的；而wait()方法是Object类的方法，用于线程间的通信；

调用wait()的时候方法会释放当前持有的锁，而sleep方法不会释放锁；

① sleep()方法导致了程序暂停执行指定的时间，让出cpu该其他线程，但是他的监控状态依然保持者，当指定的时间到了又会自动恢复运行状态。
② 调用wait()方法的时候，线程会放弃对象锁，进入等待此对象的等待锁定池，只有针对此对象调用notify()方法后本线程才进入对象锁定池准备



### 创建线程池的方式

①. newFixedThreadPool(int nThreads)
创建一个固定长度的线程池，每当提交一个任务就创建一个线程，直到达到线程池的最大数量，这时线程规模将不再变化，当线程发生未预期的错误而结束时，线程池会补充一个新的线程。
②. newCachedThreadPool()
创建一个可缓存的线程池，如果线程池的规模超过了处理需求，将自动回收空闲线程，而当需求增加时，则可以自动添加新线程，线程池的规模不存在任何限制。
③. newSingleThreadExecutor()
这是一个单线程的Executor，它创建单个工作线程来执行任务，如果这个线程异常结束，会创建一个新的来替代它；它的特点是能确保依照任务在队列中的顺序来串行执行。
④. newScheduledThreadPool(intcorePoolSize)
创建了一个固定长度的线程池，而且以延迟或定时的方式来执行任务，类似于Timer。



### Java 内存模型 JMM

 JMM(Java Memory Model)，是一种基于计算机内存模型（定义了共享内存系统中多线程程序读写操作行为的规范），屏蔽了各种硬件和操作系统的访问差异的，保证了Java程序在各种平台下对内存的访问都能保证效果一致的机制及规范。保证共享内存的**原子性**、**可见性**、**有序性**。 

并发编程中三个重要的概念：

**原子性**：

> 对共享内存的操作必须是要么全部执行直到执行结束，且中间过程不能被任何外部因素打断，要么就不执行。

**可见性**：

> 多线程操作共享内存时，执行结果能够及时的同步到共享内存，确保其他线程对此结果及时可见。

**有序性**：

> 程序的执行顺序按照代码顺序执行，在单线程环境下，程序的执行都是有序的，但是在多线程环境下，JMM 为了性能优化，编译器和处理器会对指令进行重排，程序的执行会变成无序。



### 线程安全

什么是线程安全？ 简单地说，**在多线程环境中，能永远保证程序的正确性**

如果你的代码所在的进程中有多个线程在同时运行，而这些线程可能会同时运行这段代码。如果每次运行结果和单线程运行的结果是一样的，而且其他的变量的值也和预期的是一样的，就是线程安全的。一个线程安全的计数器类的同一个实例对象在被多个线程使用的情况下也不会出现计算失误。

- **线程安全：**就是当多线程访问时，采用了加锁的机制；即当一个线程访问该类的某个数据时，会对这个数据进行保护，其他线程不能对其访问，直到该线程读取完之后，其他线程才可以使用。防止出现数据不一致或者数据被污染的情况。
- **线程不安全：**就是不提供数据访问时的数据保护，多个线程能够同时操作某个数据，从而出现数据不一致或者数据污染的情况。
- 对于线程不安全的问题，一般会使用synchronized关键字加锁同步控制。



### 线程安全的集合类

#### 线程安全的集合类

- Vector
- HashTable
- StringBuffer

#### 非线程安全的集合对象

- ArrayList ：
- LinkedList：
- HashMap：（ ConcurrentHashMap ）
- HashSet：
- TreeMap：
- TreeSet：
- StringBulider：

#### Vector、ArrayList、LinkedList

1. Vector：Vector与ArrayList一样，也是通过数组实现的，不同的是它支持线程的同步，即某一时刻只有一个线程能够写Vector，避免多线程同时写而引起的不一致性，但实现同步需要很高的花费，因此，访问它比访问ArrayList慢。

2. ArrayList：
   　　a. 当操作是在一列数据的后面添加数据而不是在前面或者中间，并需要随机地访问其中的元素时，使用ArrayList性能比较好。
   b. ArrayList是最常用的List实现类，内部是通过数组实现的，它允许对元素进行快速随机访问。数组的缺点是每个元素之间不能有间隔，当数组大小不满足时需要增加存储能力，就要讲已经有数组的数据复制到新的存储空间中。当从ArrayList的中间位置插入或者删除元素时，需要对数组进行复制、移动、代价比较高。因此，它**适合随机查找和遍历，不适合插入和删除。**

3. LinkedList：
   　　a. 当对一列数据的前面或者中间执行添加或者删除操作时，并且按照顺序访问其中的元素时，要使用LinkedList。
    b. LinkedList是用链表结构存储数据的，**很适合数据的动态插入和删除，随机访问和遍历速度比较慢**。另外，他还提供了List接口中没有定义的方法，专门用于操作表头和表尾元素，可以当作堆栈、队列和双向队列使用。

   

   Vector和ArrayList在使用上非常相似，都可以用来表示一组数量可变的对象应用的集合，并且可以随机的访问其中的元素。 Vectors是可同步的，是线程安全的。ArrayList是不可同步的，不是线程安全的。所以，**一般单线程推荐用ArrayList，多线程中则用Vector **。



####  HashTable、HashMap、HashSet

1. HashMap：
   a. 采用**数组方式存储key-value**构成的Entry对象，无容量限制；
   b. 基于**key hash查找**Entry对象存放到数组的位置，对于hash冲突采用链表的方式去解决；
   c. 在插入元素时，可能会扩大数组的容量，在扩大容量时须要重新计算hash，并复制对象到新的数组中；
   d. 是**非线程安全**的；
   e. 遍历使用的是**Iterator**迭代器；

2. HashTable
   a. 是**线程安全**的；
   b. 无论是key还是value**都不允许有null值的存在**；在HashTable中调用Put方法时，如果key为null，直接抛出NullPointerException异常；
   c. 遍历使用的是**Enumeration**列举；

3. HashSet
   a. 基于HashMap实现，无容量限制；
   b. 是**非线程安全**的；
   c. **不保证数据的有序**；



### volatile关键字的作用

**保证被 Volatile 关键字描述变量的操作具有可见性和有序性（禁止指令重排）**

注意：

1. Volatile 只对基本类型 (byte、char、short、int、long、float、double、boolean) 的赋值 操作和对象的引⽤赋值操作有效。
2. 对于 `i++` 此类复合操作， Volatile 无法保证其有序性和原子性。
3. 相对 Synchronized 来说 Volatile 更加轻量一些。 

### synchronized关键字的作用

1. 保证方法或代码块操作的**原子性**
2. 保证监视资源的**可见性**
3. 保证线程间操作的**有序性**



### synchronized的内部都包括哪些区域？

答：synchronized内部包括6个不同的区域，每个区域的数据都代表锁的不同状态。①ContentionList：锁竞争队列，所有请求锁的线程都被放在竞争队列中。②EntryList：竞争候选列表，在锁竞争队列中有资格成为候选者来竞争锁资源的线程被移动到候选列表中。③WaitSet：等待集合，调用wait方法后阻塞的线程将被放在WaitSet。④OnDeck：竞争候选者，在同一时刻最多只有一个线程在竞争锁资源，该线程的状态被称为OnDeck。⑤Owner：竞争到锁资源的线程状态。⑥!Owner：释放锁后的状态。

### synchronized的实现原理

答：①收到新的锁请求时首先自旋，如果通过自旋也没有获取锁资源，被放入ContentionList（该做法对于已经进入队列的线程是不公平的，体现了synchronized的不公平性）。②为了防止ContentionList尾部的元素被大量线程进行CAS访问影响性能，Owner线程会在是释放锁时将ContentionList的部分线程移动到EntryList并指定某个线程（一般是最先进入的）为OnDeck线程。Owner并没有将锁直接传递给OnDeck线程而是把锁竞争的权利交给他，该行为叫做竞争切换，牺牲了公平性但提高了性能。③获取到锁的OnDeck线程会变为Owner线程，未获取到的仍停留在EntryList中。④Owner线程在被wait阻塞后会进入WaitSet，直到某个时刻被唤醒再次进入EntryList。⑤ContentionList、EntryList、WaitSet中的线程均为阻塞状态。⑥当Owner线程执行完毕后会释放锁资源并变为!Owner状态。

### JDK对synchronized做了哪些优化？

答：JDK1.6中引入了适应自旋、锁消除、锁粗化、轻量级锁以及偏向锁等以提高锁的效率。锁可以从偏向锁升级到轻量级锁，再升级到重量级锁，这种过程叫做锁膨胀。JDK1.6中默认开启了偏向锁和轻量级锁，可以通过-XX:UseBiasedLocking禁用偏向锁。

### volatile和synchronized的区别？

答：①volatile只能修饰实例变量和类变量,而synchronized可以修饰方法以及代码块。②
volatile只能保证数据的可见性，但是不保证原子性，synchronized是一种排它机制，可以保证原子性。只有在特殊情况下才适合取代synchronized：对变量的写操作不依赖于当前值（例如i++），或者是单纯的变量赋值；该变量没有被包含在具有其他变量的不等式中，不同的volatile变量不能互相依赖，只有在状态真正独立于程序内的其它内容时才能使用volatile。③volatile是一种轻量级的同步机制，在访问volatile修饰的变量时并不会执行加锁操作，线程不会阻塞，使用synchronized加锁会阻塞线程。

### 讲一讲ReentrantLock

答：①ReentrantLock是Lock接口的实现类，是一个可重入式的独占锁，通过AQS实现。②支持公平锁与非公平锁，还提供了可响应中断锁（线程在等待锁的过程中可以根据需要取消对锁的请求，通过interrupt方法中断）、可轮询锁（通过tryLock获取锁，如果有可用锁返回true否则立即返回false）、定时锁（通过带long时间参数的tryLock方法获取锁，如果在给定时间内获取到可用锁且当前线程未被中断返回true，如果超过指定时间则返回false，如果获取锁时被终断则抛出异常并清除已终止状态）等避免死锁的方法。③通过lock和unlock方法显式地加锁和释放锁。

### synchronized和ReentrantLock有哪些区别？

答：①synchronized是隐式锁，ReentrantLock是显式锁，使用时必须在finally代码块中进行释放锁的操作。②synchronized是非公平锁，ReentrantLock可以实现公平锁。③ReentrantLock可响应中断，可轮回，为处理锁提高了更多灵活性。④synchronized是一个关键字，是JVM级别，ReentrantLock是一个接口，是API级别。⑤synchronized采用悲观并发策略，ReentrantLock采用的是乐观并发策略，会先尝试以CAS方式获取锁。

### Lock接口有哪些方法？

答：①lock()：给对象加锁。②tryLock()/tryLock(long time,TimeUnit unit)：尝试给对象加锁，成功返回true，可以无参也可以指定等待时间。③unlock()：释放锁，锁只能由持有者释放否则抛出异常。④newCondition()：创建条件对象，使用条件对象管理那些已经获得锁但不满足有效条件的线程，调用await()方法把线程进入等待集，调用sign()/signAll()解除阻塞。⑤lockInterruptibly()：如果当前线程未被中断则获取该锁。

### Java中的锁有什么作用？有哪些分类？

答：①Java中的锁主要用于保障多并发情况下数据的一致性，线程必须先获取锁才能进行操作，可以保证数据的安全。②从乐观和悲观的角度可以分为乐观锁和悲观锁。③从获取资源的公平性可以分为公平锁和非公平锁。④从是否共享资源的角度可以分为共享锁和排它锁。⑤从锁的状态角度可分为偏向锁、轻量级锁和重量级锁。同时在JVM中还设计了自旋锁以更快地使用CPU资源。





## JVM 相关

