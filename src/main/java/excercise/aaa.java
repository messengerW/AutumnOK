package excercise;
/*
 * File: aaa.java
 * Date: 2020-06-09 14:41
 * Author: msw
 * PS ...
 */

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class aaa {

    public static void main(String[] args) {

//        String s1 = new String("abc");
//        StringBuffer s2 = new StringBuffer("abc");
//
//        s1 = s1 + "efg";
//        s2.append("efg");
//
//        System.out.println(s1.equals(s2));

//        ArrayList<Integer> list1 = new ArrayList<>();
//        System.out.println(list1.size());
//
//        LinkedList<Integer> list2 = new LinkedList<>();
//        System.out.println(list2);


//        String x = "string";
//        String y = "string";
//        String z = new String("string");
//        String z1 = new String("string");
//
//        System.out.println(x == y); // true
//        System.out.println(x == z); // false
//        System.out.println(x.equals(y));
//        System.out.println(x.equals(z));
//        System.out.println(System.identityHashCode(x));
//        System.out.println(System.identityHashCode(y));
//        System.out.println(System.identityHashCode(z));
//        System.out.println(System.identityHashCode(z1));

        // StringBuffer 变量
//        StringBuffer sb = new StringBuffer("abc");
//        System.out.println(System.identityHashCode(sb));
//        sb.append("efg");
//        System.out.println(System.identityHashCode(sb));
//        System.out.println("///////////////////////////////////");
//
//        StringBuilder stringBuilder = new StringBuilder("abc");
//        System.out.println(System.identityHashCode(stringBuilder));
//        stringBuilder.append("efg");
//        System.out.println(System.identityHashCode(stringBuilder));
//        System.out.println("///////////////////////////////////");

        // String 为常量，一旦定义不可修改，修改后会指向新的地址
//        String s = "abc";
//        System.out.println(System.identityHashCode(s));
//
//        s = s + "efg";
//        System.out.println(System.identityHashCode(s));

//        String s1 = new String("bbc");
//        String s2 = new String("bbc");
//        String s3 = "sss";
//        String s4 = "sss";
//        System.out.println(System.identityHashCode(s1));
//        System.out.println(System.identityHashCode(s2));
//        System.out.println(System.identityHashCode(s3));
//        System.out.println(System.identityHashCode(s4));
//
//        System.out.println("s1 hashcode: " + s1.hashCode() + "\ns2 hashcode: " + s2.hashCode());
//        System.out.println(s1 == s2);           // false
//        System.out.println(s1.equals(s2));      // true
//
//        System.out.println("s3 hashcode: " + s3.hashCode() + "\ns4 hashcode: " + s4.hashCode());
//        System.out.println(s3 == s4);           // false
//        System.out.println(s3.equals(s4));      // true
//
//
//        /*
//        * 两个obj，如果equals()相等，hashCode()一定相等;
//        * 如果hashCode()相等，equals()不一定相等（Hash散列值有冲突的情况，虽然概率很低）。
//        * */
//        System.out.println("///////////////////////////////////");
//        String str1 = "通话";
//        String str2 = "重地";
//        System.out.println(" str1 的哈希值为：" + str1.hashCode());
//        System.out.println(" str2 的哈希值为：" + str2.hashCode());
//        System.out.println(str1.equals(str2));
//
//        Object o1 = new Object();

//        System.out.println(Math.round(-0.5));       // 0
//        System.out.println(Math.round(-0.6));       // -1
//        System.out.println(Math.round(1.5));        // 2

//        short sh = 1;
//        Short shot1 = new Short(sh);
//        Long long_1 = new Long(1);
//        Integer i1 = new Integer(1);
//        String s11 = new String("");
//        StringBuffer sb11 = new StringBuffer();
//        StringBuilder sb22 = new StringBuilder();
//        sb11.append("aa");
//        sb22.append("aa");

//        Thread thread1 = new Thread();
//        thread1.run();

//        Boolean b = new Boolean(false);
//        Integer a = new Integer(3);
//        Object o = new Object();

//        System.out.println(System.getProperties());
//        System.out.println(System.getProperty("os.name"));
//        System.out.println(Boolean.hashCode(b));

//        Thread t1 = new MyThread("线程1");
//        Thread t2 = new MyThread("线程2");
//        Thread t3 = new MyThread("线程3");
//
//        try {
//            //t1先启动
//            t1.start();
//            t1.join();
//            //t2
//            t2.start();
//            t2.join();
//            //t3
//            t3.start();
//            t3.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//    static class MyThread extends Thread {
//        public MyThread(String name) {
//            setName(name);
//        }
//
//        @Override
//        public void run() {
//            System.out.println(Thread.currentThread().getName());
//        }

//        String[] arr = new String[3];
//        List<String> list = new ArrayList<>();
//
//        System.out.println("arr:" + Arrays.asList(arr));
//        System.out.println("list:" + list.toArray());

//        ArrayList<Object> arrayList = new ArrayList<>();
//
//        String s1 = "111==";
//        String s2 = "111==";
//
//        System.out.println(s1.equals(s2));
//
//        String str1 = new String("111");
//        String str2 = new String("111");
//        System.out.println(str1.equals(str2));
//
//        int a = 1;
//        Integer b = new Integer(2);
//
//        arrayList.add(a);
//        arrayList.add(b);
//        arrayList.add(3);
//
//        arrayList.add("sss");
//
//        for (Object obj : arrayList)
//            System.out.println(obj.toString());


//        String ss = new String("11");
//        System.out.println(ss.equals("11"));

        String nn = null;
        try {
            System.out.println(nn.equals("111"));

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        System.out.println("end?");
    }
}

