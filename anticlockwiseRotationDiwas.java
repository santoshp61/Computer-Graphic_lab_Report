import java.awt.*;
import java.util.Scanner;
import javax.swing.*;

public class anticlockwiseRotationDiwas extends JPanel {
    private int[] x;
    private int[] y;
    private double angle;

    public anticlockwiseRotationDiwas(int[] x, int[] y, double angle) {
        this.x = x;
        this.y = y;
        this.angle = Math.toRadians(angle);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = getWidth();
        int height = getHeight();

        g.setColor(Color.LIGHT_GRAY);
        g.drawLine(0, height / 2, width, height / 2); 
        g.drawLine(width / 2, 0, width / 2, height); 

        int[] adjustedX = new int[3];
        int[] adjustedY = new int[3];
        for (int i = 0; i < 3; i++) {
            adjustedX[i] = x[i] + width / 2;
            adjustedY[i] = height / 2 - y[i];
        }

        g.setColor(Color.BLUE);
        g.drawPolygon(adjustedX, adjustedY, 3);
        g.drawString("Original", adjustedX[0] + 5, adjustedY[0] - 5);

     
        int[] rotatedX = new int[3];
        int[] rotatedY = new int[3];
        for (int i = 0; i < 3; i++) {
            double rx = x[i] * Math.cos(angle) - y[i] * Math.sin(angle);
            double ry = x[i] * Math.sin(angle) + y[i] * Math.cos(angle);

            rotatedX[i] = (int) Math.round(rx) + width / 2;
            rotatedY[i] = height / 2 - (int) Math.round(ry);
        }

        g.setColor(Color.RED);
        g.drawPolygon(rotatedX, rotatedY, 3);
        g.drawString("After Rotation", rotatedX[0] + 5, rotatedY[0] - 5);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] x = new int[3];
        int[] y = new int[3];

        System.out.println("Enter coordinates of the triangle: ");
        for (int i = 0; i < 3; i++) {
            System.out.print("Point " + (i + 1) + " (x y): ");
            x[i] = sc.nextInt();
            y[i] = sc.nextInt();
        }

        System.out.print("Enter the rotation angle in degrees (anticlockwise): ");
        double angle = sc.nextDouble();

        JFrame frame = new JFrame("Anticlockwise Rotation Through the Origin Pani Herdim");
        anticlockwiseRotationDiwas panel = new anticlockwiseRotationDiwas(x, y, angle);

        frame.add(panel);
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        sc.close();
    }
}