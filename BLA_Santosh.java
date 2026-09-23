import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

public class BLA_Santosh extends Frame {
    int x1, y1, x2, y2;

    public BLA_Santosh(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;

        setTitle("Bresenham's Line Drawing Algorithm (BLA) - SANTOSH");
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
        int width = getWidth();
        int height = getHeight();
        int centerX = width / 2;
        int centerY = height / 2;
        g.setColor(Color.BLACK);
        g.drawLine(0, centerY, width, centerY);  // X-axis
        g.drawLine(centerX, 0, centerX, height); // Y-axis

        int xStart = x1;
        int yStart = y1;
        int xEnd = x2;
        int yEnd = y2;

        int dx = xEnd - xStart;
        int dy = yEnd - yStart;
        int absDx = Math.abs(dx);
        int absDy = Math.abs(dy);

        int x = xStart;
        int y = yStart;

        g.setColor(Color.RED);

        if (absDy <= absDx) { 
                 int p = 2 * absDy - absDx;
            int stepX = dx >= 0 ? 1 : -1;
            int stepY = dy >= 0 ? 1 : -1;

            for (int i = 0; i <= absDx; i++) {
                int px = x + centerX;
                int py = centerY - y; 
                g.fillRect(px, py, 1, 1);
                x += stepX;

                if (p < 0) {
                    p += 2 * absDy;
                } else {
                    y += stepY;
                    p += 2 * (absDy - absDx);
                }
            }
        } else { 
    
            int p = 2 * absDx - absDy;
            int stepX = dx >= 0 ? 1 : -1;
            int stepY = dy >= 0 ? 1 : -1;

            for (int i = 0; i <= absDy; i++) {
                int px = x + centerX;
                int py = centerY - y; 

                g.fillRect(px, py, 1, 1);
                y += stepY;

                if (p < 0) {
                    p += 2 * absDx;
                } else {
                    x += stepX;
                    p += 2 * (absDx - absDy);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter x1 (starting point X): ");
        int x1 = scanner.nextInt();
        System.out.print("Enter y1 (starting point Y): ");
        int y1 = scanner.nextInt();
        System.out.print("Enter x2 (ending point X): ");
        int x2 = scanner.nextInt();
        System.out.print("Enter y2 (ending point Y): ");
        int y2 = scanner.nextInt();

        scanner.close();

        new BLA_Santosh(x1, y1, x2, y2);
    }
}