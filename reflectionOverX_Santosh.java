import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

public class reflectionOverX_Santosh extends Frame {
    int dbossx1, dbossy1, dbossx2, dbossy2, dbossx3, dbossy3;
    int dbossrx1, dbossry1, dbossrx2, dbossry2, dbossrx3, dbossry3;

    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 800;

    public reflectionOverX_Santosh() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter x1 ");
        dbossx1 = sc.nextInt();
        System.out.println("Enter y1 ");
        dbossy1 = sc.nextInt();

        System.out.println("Enter x2 ");
        dbossx2 = sc.nextInt();
        System.out.println("Enter y2 ");
        dbossy2 = sc.nextInt();

        System.out.println("Enter x3 ");
        dbossx3 = sc.nextInt();
        System.out.println("Enter y3 ");
        dbossy3 = sc.nextInt();

        sc.close();

        // X-Axis Reflection: (x, y) -> (x, -y)
        dbossrx1 = dbossx1;
        dbossry1 = -dbossy1;

        dbossrx2 = dbossx2;
        dbossry2 = -dbossy2;

        dbossrx3 = dbossx3;
        dbossry3 = -dbossy3;

        this.setTitle("DBOSS Reflection Through X-axis");
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

        // Draw canvas frame border
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);

        // Draw main X and Y axes
        g.drawLine(0, centerY, getWidth(), centerY);
        g.drawLine(centerX, 0, centerX, getHeight());

        // Draw arrowheads on the axes
        g.drawLine(getWidth() - 10, centerY - 3, getWidth(), centerY);
        g.drawLine(getWidth() - 10, centerY + 3, getWidth(), centerY);
        g.drawLine(centerX - 3, 10, centerX, 0);
        g.drawLine(centerX + 3, 10, centerX, 0);

        // Draw original triangle (Blue)
        g.setColor(Color.BLUE);
        Point p1_orig_awt = toAWTCoords(dbossx1, dbossy1);
        Point p2_orig_awt = toAWTCoords(dbossx2, dbossy2);
        Point p3_orig_awt = toAWTCoords(dbossx3, dbossy3);

        g.drawLine(p1_orig_awt.x, p1_orig_awt.y, p2_orig_awt.x, p2_orig_awt.y);
        g.drawLine(p2_orig_awt.x, p2_orig_awt.y, p3_orig_awt.x, p3_orig_awt.y);
        g.drawLine(p3_orig_awt.x, p3_orig_awt.y, p1_orig_awt.x, p1_orig_awt.y);
        g.drawString("Before Reflection", p1_orig_awt.x, p1_orig_awt.y - 5);

        // Draw reflected triangle (Red)
        g.setColor(Color.RED);
        Point p1_reflected_awt = toAWTCoords(dbossrx1, dbossry1);
        Point p2_reflected_awt = toAWTCoords(dbossrx2, dbossry2);
        Point p3_reflected_awt = toAWTCoords(dbossrx3, dbossry3);

        g.drawLine(p1_reflected_awt.x, p1_reflected_awt.y, p2_reflected_awt.x, p2_reflected_awt.y);
        g.drawLine(p2_reflected_awt.x, p2_reflected_awt.y, p3_reflected_awt.x, p3_reflected_awt.y);
        g.drawLine(p3_reflected_awt.x, p3_reflected_awt.y, p1_reflected_awt.x, p1_reflected_awt.y);
        g.drawString("After Reflection", p1_reflected_awt.x, p1_reflected_awt.y - 5);
    }

    public static void main(String[] args) {
        new reflectionOverX_Santosh();
    }
}