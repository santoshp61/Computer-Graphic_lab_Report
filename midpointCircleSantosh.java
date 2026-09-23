import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

public class midpointCircleSantosh extends Frame {
    int xc, yc, r;

    public midpointCircleSantosh(int xc, int yc, int r) {
        this.xc = xc;
        this.yc = yc;
        this.r = r;

        setTitle("Midpoint Circle Drawing Algorithm -> SANTOSH");
        setSize(600, 400);
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        int frameCenterX = getWidth() / 2;
        int frameCenterY = getHeight() / 2;

        g.setColor(Color.LIGHT_GRAY);
        g.drawLine(0, frameCenterY, getWidth(), frameCenterY); 
        g.drawLine(frameCenterX, 0, frameCenterX, getHeight());

        int screenCenterX = frameCenterX + xc;
        int screenCenterY = frameCenterY - yc; 

        int x = 0;
        int y = r;
        int p = 1 - r; 

        g.setColor(Color.RED);

        drawCirclePoints(g, screenCenterX, screenCenterY, x, y);

        while (x < y) {
            x++;
            if (p < 0) {
                p = p + 2 * x + 1;
            } else {
                y--;
                p = p + 2 * (x - y) + 1;
            }
            drawCirclePoints(g, screenCenterX, screenCenterY, x, y);
        }
    }

    public void drawCirclePoints(Graphics g, int centerX, int centerY, int x, int y) {
        g.fillRect(centerX + x, centerY - y, 1, 1);
        g.fillRect(centerX - x, centerY - y, 1, 1);
        g.fillRect(centerX + x, centerY + y, 1, 1);
        g.fillRect(centerX - x, centerY + y, 1, 1);
        g.fillRect(centerX + y, centerY - x, 1, 1);
        g.fillRect(centerX - y, centerY - x, 1, 1);
        g.fillRect(centerX + y, centerY + x, 1, 1);
        g.fillRect(centerX - y, centerY + x, 1, 1);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter xc (center X relative to origin): ");
        int xc = scanner.nextInt();
        System.out.print("Enter yc (center Y relative to origin): ");
        int yc = scanner.nextInt();
        System.out.print("Enter r (radius): ");
        int r = scanner.nextInt();

        scanner.close();

        new midpointCircleSantosh(xc, yc, r);
    }
}