import edu.princeton.cs.algs4.Picture;

/**
 * @author jtchen
 * @version 1.0
 * @date 2021/8/4 17:16
 */
public class PictureTest {
	public static void main(String[] args) {
		Picture picture = new Picture("src/main/resources/pic.jpeg");

		Picture copy = new Picture(picture.width(), picture.height());
		for (int i = 0; i < picture.width(); i++) {
			for (int j = 0; j < picture.height(); j++) {
				copy.set(i, j, picture.get(i, j).darker().darker().darker());
			}
		}
		copy.show();
	}
}
