import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

public class reflectionOverY_Santosh extends Frame {
    int kanxi_x1, kanxi_y1, kanxi_x2, kanxi_y2, kanxi_x3, kanxi_y3;
    int kanxi_rx1, kanxi_ry1, kanxi_rx2, kanxi_ry2, kanxi_rx3, kanxi_ry3;

    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 800;

    public reflectionOverY_Santosh() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter x1");
        kanxi_x1 = sc.nextInt();
        System.out.println("Enter y1");
        kanxi_y1 = sc.nextInt();

        System.out.println("Enter x2");
        kanxi_x2 = sc.nextInt();
        System.out.println("Enter y2");
        kanxi_y2 = sc.nextInt();

        System.out.println("Enter x3");
        kanxi_x3 = sc.nextInt();
        System.out.println("Enter y3");
        kanxi_y3 = sc.nextInt();

        sc.close();

        kanxi_rx1 = -kanxi_x1;
        kanxi_ry1 = kanxi_y1;

        kanxi_rx2 = -kanxi_x2;
        kanxi_ry2 = kanxi_y2;

        kanxi_rx3 = -kanxi_x3;
        kanxi_ry3 = kanxi_y3;

        this.setTitle("Diwas Lamichhane -> Reflection Through Y-axis");
        this.setLayout(null);
        this.setBounds(100, 100, WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }

    private Point toAWTCoords(int x, int y) {
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        return new Point(centerX + x, centerY - y);
    }

    @Override
    public void paint(Graphics g) {
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        // Draw canvas border
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);

        // Draw coordinate axes
        g.drawLine(0, centerY, getWidth(), centerY);
        g.drawLine(centerX, 0, centerX, getHeight());

        // Draw axis arrows
        g.drawLine(getWidth() - 10, centerY - 3, getWidth(), centerY);
        g.drawLine(getWidth() - 10, centerY + 3, getWidth(), centerY);
        g.drawLine(centerX - 3, 10, centerX, 0);
        g.drawLine(centerX + 3, 10, centerX, 0);

        // Draw original triangle (Blue for contrast)
        g.setColor(Color.BLUE);
        Point p1_orig_awt = toAWTCoords(kanxi_x1, kanxi_y1);
        Point p2_orig_awt = toAWTCoords(kanxi_x2, kanxi_y2);
        Point p3_orig_awt = toAWTCoords(kanxi_x3, kanxi_y3);

        g.drawLine(p1_orig_awt.x, p1_orig_awt.y, p2_orig_awt.x, p2_orig_awt.y);
        g.drawLine(p2_orig_awt.x, p2_orig_awt.y, p3_orig_awt.x, p3_orig_awt.y);
        g.drawLine(p3_orig_awt.x, p3_orig_awt.y, p1_orig_awt.x, p1_orig_awt.y);
        g.drawString("Before Reflection", p1_orig_awt.x, p1_orig_awt.y - 5);

        // Draw reflected triangle (Red)
        g.setColor(Color.RED);
        Point p1_reflected_awt = toAWTCoords(kanxi_rx1, kanxi_ry1);
        Point p2_reflected_awt = toAWTCoords(kanxi_rx2, kanxi_ry2);
        Point p3_reflected_awt = toAWTCoords(kanxi_rx3, kanxi_ry3);

        g.drawLine(p1_reflected_awt.x, p1_reflected_awt.y, p2_reflected_awt.x, p2_reflected_awt.y);
        g.drawLine(p2_reflected_awt.x, p2_reflected_awt.y, p3_reflected_awt.x, p3_reflected_awt.y);
        g.drawLine(p3_reflected_awt.x, p3_reflected_awt.y, p1_reflected_awt.x, p1_reflected_awt.y);
        g.drawString("After Reflection", p1_reflected_awt.x, p1_reflected_awt.y - 5);
    }

    public static void main(String[] args) {
        new reflectionOverY_Santosh();
    }
}