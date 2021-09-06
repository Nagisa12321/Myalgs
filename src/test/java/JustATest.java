/**
 * @author jtchen
 * @version 1.0
 * @date 2021/3/18 13:31
 */
public class JustATest {
	public static void main(String[] args) {
		Integer integer = Integer.valueOf(124);
		Boolean a = Boolean.valueOf("true");
	}

	int A(int aaa) {
		return -1;
	}
}
class Son extends JustATest {
	@Override
	int A(int bbb) {
		return super.A(bbb);
	}
}