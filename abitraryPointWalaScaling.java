import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

public class abitraryPointWalaScaling extends Frame {
    int diwasx1, diwasy1, diwasx2, diwasy2, diwasx3, diwasy3;
    float diwassx, diwassy;
    int diwastx, diwasty;
    int diwaspx1, diwaspx2, diwaspy1, diwaspy2, diwaspx3, diwaspy3;

    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 800;

    public abitraryPointWalaScaling() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter x1: ");
        diwasx1 = sc.nextInt();
        System.out.println("Enter y1: ");
        diwasy1 = sc.nextInt();

        System.out.println("Enter x2: ");
        diwasx2 = sc.nextInt();
        System.out.println("Enter y2: ");
        diwasy2 = sc.nextInt();

        System.out.println("Enter x3: ");
        diwasx3 = sc.nextInt();
        System.out.println("Enter y3: ");
        diwasy3 = sc.nextInt();

        System.out.println("Enter scaling factor (Sx): ");
        diwassx = sc.nextFloat();
        System.out.println("Enter scaling factor (Sy): ");
        diwassy = sc.nextFloat();

        System.out.println("Enter arbitrary point tx: ");
        diwastx = sc.nextInt();
        System.out.println("Enter arbitrary point ty: ");
        diwasty = sc.nextInt();

        sc.close();

        // Calculate scaling relative to the arbitrary point (tx, ty)
        diwaspx1 = (int) (diwasx1 * diwassx + diwastx * (1 - diwassx));
        diwaspy1 = (int) (diwasy1 * diwassy + diwasty * (1 - diwassy));

        diwaspx2 = (int) (diwasx2 * diwassx + diwastx * (1 - diwassx));
        diwaspy2 = (int) (diwasy2 * diwassy + diwasty * (1 - diwassy));

        diwaspx3 = (int) (diwasx3 * diwassx + diwastx * (1 - diwassx));
        diwaspy3 = (int) (diwasy3 * diwassy + diwasty * (1 - diwassy));

        this.setTitle("Arbitrary Point ko through wala scaling 2D ma");
        this.setLayout(null);
        this.setBounds(100, 100, WINDOW_WIDTH, WINDOW_HEIGHT);
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

        // Draw border
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);

        // Draw X and Y axes
        g.drawLine(0, centerY, getWidth(), centerY);
        g.drawLine(centerX, 0, centerX, getHeight());

        // Draw axis arrows
        g.drawLine(getWidth() - 10, centerY - 3, getWidth(), centerY);
        g.drawLine(getWidth() - 10, centerY + 3, getWidth(), centerY);
        g.drawLine(centerX - 3, 10, centerX, 0);
        g.drawLine(centerX + 3, 10, centerX, 0);

        // Draw original triangle (Blue)
        g.setColor(Color.BLUE);
        Point p1_orig_awt = toAWTCoords(diwasx1, diwasy1);
        Point p2_orig_awt = toAWTCoords(diwasx2, diwasy2);
        Point p3_orig_awt = toAWTCoords(diwasx3, diwasy3);
        g.drawLine(p1_orig_awt.x, p1_orig_awt.y, p2_orig_awt.x, p2_orig_awt.y);
        g.drawLine(p2_orig_awt.x, p2_orig_awt.y, p3_orig_awt.x, p3_orig_awt.y);
        g.drawLine(p3_orig_awt.x, p3_orig_awt.y, p1_orig_awt.x, p1_orig_awt.y);
        g.drawString("before scaling", p1_orig_awt.x, p1_orig_awt.y - 5);

        // Draw scaled triangle (Red)
        g.setColor(Color.RED);
        Point p1_scaled_awt = toAWTCoords(diwaspx1, diwaspy1);
        Point p2_scaled_awt = toAWTCoords(diwaspx2, diwaspy2);
        Point p3_scaled_awt = toAWTCoords(diwaspx3, diwaspy3);
        g.drawLine(p1_scaled_awt.x, p1_scaled_awt.y, p2_scaled_awt.x, p2_scaled_awt.y);
        g.drawLine(p2_scaled_awt.x, p2_scaled_awt.y, p3_scaled_awt.x, p3_scaled_awt.y);
        g.drawLine(p3_scaled_awt.x, p3_scaled_awt.y, p1_scaled_awt.x, p1_scaled_awt.y);
        g.drawString("after scaling", p1_scaled_awt.x, p1_scaled_awt.y - 5);

        // Draw arbitrary scaling point (Green)
        g.setColor(Color.GREEN);
        Point arbitrary_point_awt = toAWTCoords(diwastx, diwasty);
        g.fillOval(arbitrary_point_awt.x - 3, arbitrary_point_awt.y - 3, 6, 6);
        g.drawString("Pivot Point", arbitrary_point_awt.x + 5, arbitrary_point_awt.y - 5);
    }

    public static void main(String[] args) {
        new abitraryPointWalaScaling();
    }
}