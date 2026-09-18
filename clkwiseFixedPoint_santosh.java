import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

public class clkwiseFixedPoint_santosh extends Frame {
    int[] diwasey_x = {100, 150, 125};
    int[] diwasey_y = {100, 100, 50};
    double theta;
    int xr, yr;

    public clkwiseFixedPoint_santosh(double angle, int xr, int yr) {
        this.theta = Math.toRadians(angle);
        this.xr = xr;
        this.yr = yr;

        setTitle("Rotation about Arbitrary(Fixed) Point - Clockwise @Diwas Lamichhane");
        setSize(600, 600);
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
        g.setColor(Color.BLUE);
        g.drawPolygon(diwasey_x, diwasey_y, 3);

        int originalCentroidX = 0;
        int originalCentroidY = 0;
        for (int i = 0; i < 3; i++) {
            originalCentroidX += diwasey_x[i];
            originalCentroidY += diwasey_y[i];
        }
        originalCentroidX /= 3;
        originalCentroidY /= 3;

        g.setColor(Color.BLUE);
        g.drawString("Original", originalCentroidX + 10, originalCentroidY);

        int[] rx = new int[3];
        int[] ry = new int[3];
        for (int i = 0; i < 3; i++) {
            int tx = diwasey_x[i] - xr;
            int ty = diwasey_y[i] - yr;

            rx[i] = (int) Math.round(tx * Math.cos(theta) + ty * Math.sin(theta)) + xr;
            ry[i] = (int) Math.round(-tx * Math.sin(theta) + ty * Math.cos(theta)) + yr;
        }
        g.setColor(Color.RED);
        g.drawPolygon(rx, ry, 3);

        int rotatedCentroidX = 0;
        int rotatedCentroidY = 0;
        for (int i = 0; i < 3; i++) {
            rotatedCentroidX += rx[i];
            rotatedCentroidY += ry[i];
        }
        rotatedCentroidX /= 3;
        rotatedCentroidY /= 3;

        g.setColor(Color.RED);
        g.drawString("After Rotation", rotatedCentroidX + 10, rotatedCentroidY);

        g.setColor(Color.GREEN);
        g.fillOval(xr - 3, yr - 3, 6, 6);
        g.drawString("Pivot Point", xr + 5, yr - 5);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter angle of rotation: ");
        double angle = sc.nextDouble();

        System.out.print("Enter x-coordinate of fixed point: ");
        int xr = sc.nextInt();

        System.out.print("Enter y-coordinate of fixed point: ");
        int yr = sc.nextInt();

        sc.close();

        new clkwiseFixedPoint_santosh(angle, xr, yr);
    }
}