import java.awt.Point;

public class Circle {

    public int x, y, vx, vy;
    public boolean isFill = false;
    private int r;

    public Circle(int x, int y, int r, int vx, int vy) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.vx = vx;
        this.vy = vy;
    }

    public int getR() {
        return this.r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public void move(int minx, int miny, int maxx, int maxy) {
        x += vx;
        y += vy;
        checkCollision(minx, miny, maxx, maxy);
    }

    public void checkCollision(int minx, int miny, int maxx, int maxy) {
        if (x - r < minx) {
            x = r;
            vx = -vx;
        }
        if (x + r > maxx) {
            x = maxx - r;
            vx = -vx;
        }
        if (y - r < miny) {
            y = r;
            vy = -vy;
        }
        if (y + r > maxy) {
            y = maxy - r;
            vy = -vy;
        }
    }

    public boolean isContain(Point point) {
        return (point.x - x) * (point.x - x) + (point.y - y) * (point.y - y) < r * r;
    }

}