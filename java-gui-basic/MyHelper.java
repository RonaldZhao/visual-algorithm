import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class MyHelper {

    public static void setStrokeWidth(Graphics2D g2d, int width) {
        g2d.setStroke(new BasicStroke(width, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
    }

    public static void setColor(Graphics2D g2d, Color color) {
        g2d.setColor(color);
    }

    public static void drawCircle(Graphics2D g2d, int x, int y, int r) {
        Ellipse2D circle = new Ellipse2D.Double(x - r, y - r, 2 * r, 2 * r);
        g2d.draw(circle);
    }

    public static void fillCircle(Graphics2D g2d, int x, int y, int r) {
        Ellipse2D circle = new Ellipse2D.Double(x - r, y - r, 2 * r, 2 * r);
        g2d.fill(circle);
    }

    public static void pause(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

}