import java.awt.*;
import java.util.Scanner;
import javax.swing.*;

public class scalingOrigin_2D extends JPanel {
    private int[] x, y;
    private double sx, sy;

    public scalingOrigin_2D(int[] x, int[] y, double sx, double sy) {
        this.x = x;
        this.y = y;
        this.sx = sx;
        this.sy = sy;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = getWidth();
        int height = getHeight();

        // Draw X and Y axes centered on the screen
        g.setColor(Color.LIGHT_GRAY);
        g.drawLine(0, height / 2, width, height / 2); // X-axis
        g.drawLine(width / 2, 0, width / 2, height); // Y-axis

        // Offset vertices to origin-centered coordinate space (Y flipped for screen coordinates)
        int[] adjustedX = new int[3];
        int[] adjustedY = new int[3];
        for (int i = 0; i < 3; i++) {
            adjustedX[i] = x[i] + width / 2;
            adjustedY[i] = height / 2 - y[i];
        }

        // Draw original triangle
        g.setColor(Color.BLACK);
        g.drawPolygon(adjustedX, adjustedY, 3);
        g.drawString("Original Triangle", adjustedX[0] - 10, adjustedY[0] - 10);

        // Apply scaling relative to the origin
        int[] scaledX = new int[3];
        int[] scaledY = new int[3];
        for (int i = 0; i < 3; i++) {
            scaledX[i] = (int) (x[i] * sx) + width / 2;
            scaledY[i] = height / 2 - (int) (y[i] * sy);
        }

        // Draw scaled triangle
        g.setColor(Color.RED);
        g.drawPolygon(scaledX, scaledY, 3);
        g.drawString("Scaled Triangle", scaledX[0] - 10, scaledY[0] - 10);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] x = new int[3];
        int[] y = new int[3];

        System.out.println("Enter the coordinates:");
        for (int i = 0; i < 3; i++) {
            System.out.print("Enter x" + (i + 1) + " & y" + (i + 1) + " coordinates: ");
            x[i] = scanner.nextInt();
            y[i] = scanner.nextInt();
        }

        System.out.print("Enter the scaling factor for X (Sx): ");
        double sx = scanner.nextDouble();
        System.out.print("Enter the scaling factor for Y (Sy): ");
        double sy = scanner.nextDouble();

        JFrame frame = new JFrame("Lamichhane Dai Ko 2D Scaling Through Origin");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        scalingOrigin_2D plot = new scalingOrigin_2D(x, y, sx, sy);
        frame.add(plot);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        scanner.close();
    }
}