import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

public class DDA_Santosh extends Frame {
    int x1, y1, x2, y2;

    public DDA_Santosh(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;

        setTitle("DDA Line Drawing -> Santosh");
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
        g.drawLine(0, centerY, width, centerY);

        g.drawLine(centerX, 0, centerX, height);

        int dx = x2 - x1;
        int dy = y2 - y1;
        int steps = Math.max(Math.abs(dx), Math.abs(dy));

       
        if (steps == 0) {
            int px = x1 + centerX;
            int py = centerY - y1;
            g.setColor(Color.RED);
            g.fillRect(px, py, 1, 1);
            return;
        }

        float Xinc = (float) dx / steps;
        float Yinc = (float) dy / steps;

        float x = x1;
        float y = y1;

        g.setColor(Color.RED);
        for (int i = 0; i <= steps; i++) {
            int px = Math.round(x) + centerX;
            int py = centerY - Math.round(y);  
            g.fillRect(px, py, 1, 1);
            x += Xinc;
            y += Yinc;
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

        new DDA_Santosh(x1, y1, x2, y2);
    }
}