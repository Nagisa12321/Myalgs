import java.util.Stack;

/************************************************
 * @description
 * @author jtchen
 * @date 2020/11/29 16:52
 * @version 1.0
 ************************************************/
public class Test {
    public static void main(String[] args) {
        Stack<Integer> a = new Stack<>();
        a.push(1);
        Stack<Integer> b = (Stack<Integer>) a.clone();
        b.push(2);
        System.out.println(a.peek());
        System.out.println(b.peek());
    }
}
