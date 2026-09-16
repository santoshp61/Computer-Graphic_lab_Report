import java.awt.*;
import java.util.Scanner;
import javax.swing.*;

public class clockwiseRotationDiwas extends JPanel {
    private int[] diwasx, diwasy;
    private double theta;

    public clockwiseRotationDiwas(int[] diwasx, int[] diwasy, double theta) {
        this.diwasx = diwasx;
        this.diwasy = diwasy;
        // Convert degrees to radians for Math trig functions
        this.theta = Math.toRadians(theta);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int w = getWidth();
        int h = getHeight();

        // Draw coordinate axes centered at (w/2, h/2)
        g.setColor(Color.LIGHT_GRAY);
        g.drawLine(0, h / 2, w, h / 2); // X-axis
        g.drawLine(w / 2, 0, w / 2, h); // Y-axis

        // Translate Cartesian coordinates to Java screen coordinates (Y inverted)
        int[] adjustedX = new int[3];
        int[] adjustedY = new int[3];
        for (int i = 0; i < 3; i++) {
            adjustedX[i] = diwasx[i] + w / 2;
            adjustedY[i] = h / 2 - diwasy[i];
        }

        // Draw original triangle (Black)
        g.setColor(Color.BLACK);
        g.drawPolygon(adjustedX, adjustedY, 3);
        g.drawString("Original", adjustedX[0] - 10, adjustedY[0] - 10);

        // Clockwise rotation matrix formulas:
        // x' = x * cos(θ) + y * sin(θ)
        // y' = -x * sin(θ) + y * cos(θ)
        int[] rotatedX = new int[3];
        int[] rotatedY = new int[3];
        for (int i = 0; i < 3; i++) {
            double rx = diwasx[i] * Math.cos(theta) + diwasy[i] * Math.sin(theta);
            double ry = -diwasx[i] * Math.sin(theta) + diwasy[i] * Math.cos(theta);

            rotatedX[i] = (int) Math.round(rx) + w / 2;
            rotatedY[i] = h / 2 - (int) Math.round(ry);
        }

        // Draw rotated triangle (Green)
        g.setColor(Color.GREEN);
        g.drawPolygon(rotatedX, rotatedY, 3);
        g.drawString("After Rotation", rotatedX[0] - 10, rotatedY[0] - 10);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] diwas_x = new int[3];
        int[] diwas_y = new int[3];

        System.out.println("Enter coordinates of triangle:");
        for (int i = 0; i < 3; i++) {
            System.out.print("Point " + (i + 1) + " (x y): ");
            diwas_x[i] = sc.nextInt();
            diwas_y[i] = sc.nextInt();
        }

        System.out.print("Enter the rotation angle in degrees (clockwise): ");
        double theta = sc.nextDouble();

        JFrame frame = new JFrame("Clockwise 2D Rotation Herdim");
        clockwiseRotationDiwas panel = new clockwiseRotationDiwas(diwas_x, diwas_y, theta);

        frame.add(panel);
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        sc.close();
    }
}