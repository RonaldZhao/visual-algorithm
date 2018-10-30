import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyFrame extends JFrame {

    private int width;
    private int height;
    private Circle[] circles;

    public MyFrame(String title, int width, int height) {

        super(title);

        this.width = width;
        this.height = height;

        setSize(width, height);

        MyPanel panel = new MyPanel();

        setContentPane(panel);
        pack(); // 使窗口大小显示为适合内容面板的大小

        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    public MyFrame(String title) {
        this(title, 800, 600);
    }

    public void render(Circle[] circles) {
        this.circles = circles;
        repaint();
    }

    public int getFrameWidth() {
        return width;
    }

    public int getFrameHeight() {
        return height;
    }

    private class MyPanel extends JPanel {

        public MyPanel() {
            super(true); // 开启双缓存
        }

        @Override
        public void paintComponent(Graphics g) {

            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;

            // 抗锯齿
            RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.addRenderingHints(hints);

            // settings and draw

            MyHelper.setStrokeWidth(g2d, 1);
            MyHelper.setColor(g2d, Color.RED);
            for (Circle circle : circles) {
                if (circle.isFill) {
                    MyHelper.fillCircle(g2d, circle.x, circle.y, circle.getR());
                } else {
                    MyHelper.drawCircle(g2d, circle.x, circle.y, circle.getR());
                }
            }

        }

        @Override
        public Dimension getPreferredSize() {
            /**
             * 在创建画布的时候会自动调用此方法根据窗口大小设置画布的大小
             */
            return new Dimension(width, height);
        }

    }

}