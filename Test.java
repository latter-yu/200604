import java.util.Scanner;
import java.util.Stack;

public class Test {

    public static void main(String[] args) {
        // 题目：输入两个字符串，从第一字符串中删除第二个字符串中所有的字符。
        // 例如，输入”They are students.”和”aeiou”，则删除之后的字符串变成”Thy r stdnts.

        // 首先要将第一个字符串中的每个字符截取出来，截取成单个字符，然后用contains方法进去比较
        // 如果第二个字符串中包含有第一个字符串中的字符，就舍弃掉
        // 如果不包含则保留使用StringBuffer的append方法进行拼接

        Scanner sc = new Scanner(System.in);
        String str1 = sc.nextLine();
        String str2 = sc.nextLine();
        String[] str = str1.split("");
        // 将此字符串拆分为给定的 regular expression(regex: 正则表达式) 的匹配。
        // 该方法的工作原理是通过使用给定表达式和限制参数为零调用双参数 split 方法。
        // 因此，尾随的空字符串不会包含在结果数组中。
        StringBuffer s = new StringBuffer();
        //构造一个没有字符的字符串缓冲区，初始容量为16个字符
        for (int index = 0; index < str.length; index++) {
            if (!str2.contains(str[index])) {
                s.append(str[index]);
            }
        }
        System.out.println(s);
    }

    public static void main2(String[] args) {
        // 小易去附近的商店买苹果,奸许的商贩使用了捆绑交易,只提供 6 个每袋和 8 个每袋的包装(包装不可拆分)。
        // 可是小易现在只想购买恰好 n 个苹果,小易想购买尽量少的袋数方便携带。
        // 如果不能购买恰好 n 个苹果,小易将不会购买。
        // 要求:
        // 输入一个整数 n ,表示小易想购买 n (1 <n< 100)个苹果
        // 输出一个整数表示最少需要购买的袋数,
        // 如果不能买恰好 n 个苹果则输出 -1

        // 首先，6 和 8都是偶数。因此，能凑出的个数也一定是偶数。程序中若苹果总数是奇数，可以直接返回 -1.
        // 再次，偶数个苹果数对 8 取模，其结果只可能为 0, 2, 4, 6.
        // 若余数为 0，可直接输出.
        // 若余数为 6,则可以直接输出 (n/8 + 1).
        // 若余数为 4，只需回溯 1 次即可，因为8 + 4 = 12, 可得 12 % 6 = 0, 也可输出为 (n/8 + 1).
        // 若余数为 2，需回溯 2 次，因为 8 + 8 + 2 = 18, 18 / 6 = 3, 也可输出为 (n/8 + 1).
        // 综上，可以采用如下思路进行处理。

        System.out.println("请输入想要购买的苹果树 n:");
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()) {
            int n = sc.nextInt();
            System.out.println(buyApple(n));
        }
    }
    public static int buyApple(int n) {
        if (n < 6 || n % 2 != 0 || n == 10) {
            return -1;
        }
        if (n % 8 == 0) {
            return n / 8;
        }
        return n/8 + 1;
    }

    public static void count1(String str) {
        // 读入一个字符串str，输出字符串str中的连续最长的数字串
        // ps: 1 个测试输入包含 1 个测试用例，一个字符串 str，长度不超过 255。
        // 在一行内输出str中里连续最长的数字串
        // 输入: abcd12345ed125ss123456789
        // 输出: 123456789

        int end = 0;
        int max = 0;
        int count = 0;
        for (int index = 0; index < str.length(); index++) {
            if (str.charAt(index) >= '0' && str.charAt(index) <= '9') {
                count++;
                if (count > max) {
                    max = count;
                    end = index;
                }
            }else {
                count = 0;
            }
        }
        System.out.println(str.substring(end - max + 1, end + 1));
        // str.substring(int beginIndex, int endIndex);
        // 返回一个字符串，该字符串是此字符串的子字符串
    }

    public static void main1(String[] args) {
        String str = "abcd12345ed125ss123456789";
        count1(str);
    }

    public boolean isValid(String s) {
        // 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
        // 有效字符串需满足：
        // 1.左括号必须用相同类型的右括号闭合。
        // 2.左括号必须以正确的顺序闭合。
        // ps: 注意空字符串可被认为是有效字符串

        // "([)]"  false
        // "{[]}"  true
        if (s.length() == 0) {
            return true;
        }
        if (s.length() % 2 != 0 || s.length() == 1) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        char[] chars = s.toCharArray();// 构建一个 char 类型的数组
        for (char x : chars) {
            if (stack.empty()) {
                stack.push(x);// 查看栈顶的元素
            }else if ((stack.peek() == '{' && x == '}') || (stack.peek() == '[' && x == ']') || (stack.peek() == '(' && x == ')')) {
                stack.pop();
                // stack.peek(); 查看此堆栈顶部的对象，而不从堆栈中删除它
                // stack.pop();  删除此堆栈顶部的对象，并将该对象作为此函数的值返回
            }else {
                stack.push(x);
            }
        }
        return stack.empty();
    }
}
