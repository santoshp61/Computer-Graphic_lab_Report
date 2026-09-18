import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

public class fixedPointRotationAnticlkSantosh extends Frame {
    int santoshx1, santoshy1, santoshx2, santoshy2, santoshx3, santoshy3;
    int santoshxp, santoshyp;
    int santoshpx1, santoshpx2, santoshpy1, santoshpy2, santoshpx3, santoshpy3;
    double angle;

    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 800;

    public fixedPointRotationAnticlkSantosh() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter x1 ");
        santoshx1 = sc.nextInt();
        System.out.println("Enter y1 ");
        santoshy1 = sc.nextInt();

        System.out.println("Enter x2 ");
        santoshx2 = sc.nextInt();
        System.out.println("Enter y2 ");
        santoshy2 = sc.nextInt();

        System.out.println("Enter x3 ");
        santoshx3 = sc.nextInt();
        System.out.println("Enter y3 ");
        santoshy3 = sc.nextInt();

        System.out.println("Enter pivot point xp ");
        santoshxp = sc.nextInt();
        System.out.println("Enter pivot point yp ");
        santoshyp = sc.nextInt();

        System.out.println("Enter rotation angle ");
        angle = Math.toRadians(sc.nextDouble());

        sc.close();

        santosh_RotateTriangle();

        this.setTitle("Triangle Rotation about Arbitrary Point in Anti-Clockwise Direction @Santosh");
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

    private void santosh_RotateTriangle() {
        int santoshx1t = santoshx1 - santoshxp;
        int santoshy1t = santoshy1 - santoshyp;
        int santoshx2t = santoshx2 - santoshxp;
        int santoshy2t = santoshy2 - santoshyp;
        int santoshx3t = santoshx3 - santoshxp;
        int santoshy3t = santoshy3 - santoshyp;

        santoshpx1 = (int) Math.round(santoshx1t * Math.cos(angle) - santoshy1t * Math.sin(angle));
        santoshpy1 = (int) Math.round(santoshx1t * Math.sin(angle) + santoshy1t * Math.cos(angle));

        santoshpx2 = (int) Math.round(santoshx2t * Math.cos(angle) - santoshy2t * Math.sin(angle));
        santoshpy2 = (int) Math.round(santoshx2t * Math.sin(angle) + santoshy2t * Math.cos(angle));

        santoshpx3 = (int) Math.round(santoshx3t * Math.cos(angle) - santoshy3t * Math.sin(angle));
        santoshpy3 = (int) Math.round(santoshx3t * Math.sin(angle) + santoshy3t * Math.cos(angle));

        santoshpx1 += santoshxp;
        santoshpy1 += santoshyp;
        santoshpx2 += santoshxp;
        santoshpy2 += santoshyp;
        santoshpx3 += santoshxp;
        santoshpy3 += santoshyp;
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

        g.setColor(Color.BLACK);
        g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);

        g.drawLine(0, centerY, getWidth(), centerY);
        g.drawLine(centerX, 0, centerX, getHeight());

        g.drawLine(getWidth() - 10, centerY - 3, getWidth(), centerY);
        g.drawLine(getWidth() - 10, centerY + 3, getWidth(), centerY);
        g.drawLine(centerX - 3, 10, centerX, 0);
        g.drawLine(centerX + 3, 10, centerX, 0);

        g.setColor(Color.BLUE);
        Point p1_orig_awt = toAWTCoords(santoshx1, santoshy1);
        Point p2_orig_awt = toAWTCoords(santoshx2, santoshy2);
        Point p3_orig_awt = toAWTCoords(santoshx3, santoshy3);

        g.drawLine(p1_orig_awt.x, p1_orig_awt.y, p2_orig_awt.x, p2_orig_awt.y);
        g.drawLine(p2_orig_awt.x, p2_orig_awt.y, p3_orig_awt.x, p3_orig_awt.y);
        g.drawLine(p3_orig_awt.x, p3_orig_awt.y, p1_orig_awt.x, p1_orig_awt.y);
        g.drawString("Before Rotation", p1_orig_awt.x, p1_orig_awt.y - 5);

        g.setColor(Color.RED);
        Point p1_rotated_awt = toAWTCoords(santoshpx1, santoshpy1);
        Point p2_rotated_awt = toAWTCoords(santoshpx2, santoshpy2);
        Point p3_rotated_awt = toAWTCoords(santoshpx3, santoshpy3);

        g.drawLine(p1_rotated_awt.x, p1_rotated_awt.y, p2_rotated_awt.x, p2_rotated_awt.y);
        g.drawLine(p2_rotated_awt.x, p2_rotated_awt.y, p3_rotated_awt.x, p3_rotated_awt.y);
        g.drawLine(p3_rotated_awt.x, p3_rotated_awt.y, p1_rotated_awt.x, p1_rotated_awt.y);
        g.drawString("After Rotation", p1_rotated_awt.x, p1_rotated_awt.y - 5);

        g.setColor(Color.GREEN);
        Point pivot_awt = toAWTCoords(santoshxp, santoshyp);
        g.fillOval(pivot_awt.x - 3, pivot_awt.y - 3, 6, 6);
        g.drawString("Pivot Point", pivot_awt.x + 5, pivot_awt.y - 5);
    }

    public static void main(String[] args) {
        new fixedPointRotationAnticlkSantosh();
    }
}