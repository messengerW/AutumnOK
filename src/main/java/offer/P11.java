package offer;

/*
 * File: P11.java
 * Date: 2020-05-12 13:56
 * Author: msw
 * P10: 请实现一个函数用来找出字符流中第一个只出现一次的字符。例如，
 * 当从字符流中只读出前两个字符"go"时，第一个只出现一次的字符是"g"。
 * 当从该字符流中读出前六个字符“google"时，第一个只出现一次的字符是"l"。
*/
public class P11 {

    static String str = "";
    static int flag[] = new int[256];

    //Insert one char from stringstream
    public static void Insert(char ch)
    {
        str += ch;
        flag[ch]++;
    }
    //return the first appearence once char in current stringstream
    public static char FirstAppearingOnce()
    {
        char s[] = str.toCharArray();
        for (char c : s)
            if (flag[c] == 1)
                return c;
        return '#';
    }

    public static void main(String[] args) {
        String s = "helloworld";
        char test[] = s.toCharArray();
        String str2 = String.valueOf(test);

        for (int i = 0; i <test.length; i++)
            Insert(test[i]);
        char result = FirstAppearingOnce();
        System.out.println(result);
    }
}
