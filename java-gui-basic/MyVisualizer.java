import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyVisualizer {

    private Circle[] circles;
    private MyFrame frame;
    private boolean isAnimated = true;

    public MyVisualizer(String title, int sceneWidth, int sceneHeight, int N, int R) {
        circles = new Circle[N];
        for (int i = 0; i < N; i++) {
            int x = (int) (Math.random() * (sceneWidth - 2 * R)) + R;
            int y = (int) (Math.random() * (sceneHeight - 2 * R)) + R;
            int vx = (int) (Math.random() * 10) - 5;
            int vy = (int) (Math.random() * 10) - 5;
            circles[i] = new Circle(x, y, R, vx, vy);
        }

        EventQueue.invokeLater(() -> {
            frame = new MyFrame(title, sceneWidth, sceneHeight);
            frame.addKeyListener(new MyKeyListener());
            frame.addMouseListener(new MyMouseListener());

            new Thread(() -> {
                anim(0, 0, sceneWidth, sceneHeight);
            }).start();
        });
    }

    private void anim(int minx, int miny, int maxx, int maxy) {
        while (true) {
            frame.render(circles);
            MyHelper.pause(20);
            if (isAnimated) {
                for (Circle circle : circles) {
                    circle.move(minx, miny, maxx, maxy);
                }
            }
        }
    }

    private class MyKeyListener extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent event) {
            if (event.getKeyChar() == ' ') {
                isAnimated = !isAnimated;
            }
        }
    }

    private class MyMouseListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent event) {
            event.translatePoint(0, -(frame.getBounds().height - frame.getFrameHeight()) / 2);
            for (Circle circle : circles) {
                if (circle.isContain(event.getPoint())) {
                    circle.isFill = !circle.isFill;
                }
            }
        }
    }

    public static void main(String[] args) {

        int sceneWidth = 800;
        int sceneHeight = 800;
        int R = 50, N = 10;
        new MyVisualizer("Just a title", sceneWidth, sceneHeight, N, R);

    }

}