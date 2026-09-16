import java.awt.*;
import java.util.Scanner;
import javax.swing.*;

public class Santosh2DTranslation extends JPanel {
    private int[] x, y;
    private int diwas_tx, diwas_ty;

    public Santosh2DTranslation(int[] x, int[] y, int diwas_tx, int diwas_ty) {
        this.x = x;
        this.y = y;
        this.diwas_tx = diwas_tx;
        this.diwas_ty = diwas_ty;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw coordinate axes centered at (400, 400)
        g.setColor(Color.LIGHT_GRAY);
        g.drawLine(0, 400, 800, 400); // X-axis
        g.drawLine(400, 0, 400, 800); // Y-axis

        // Offset vertices to origin-centered coordinate space (Y flipped for screen coordinates)
        int[] adjustedX = new int[3];
        int[] adjustedY = new int[3];
        for (int i = 0; i < 3; i++) {
            adjustedX[i] = x[i] + 400;
            adjustedY[i] = 400 - y[i]; 
        }

        // Draw original triangle
        g.setColor(Color.BLACK);
        g.drawPolygon(adjustedX, adjustedY, 3);
        g.drawString("Original Triangle", adjustedX[0] - 10, adjustedY[0] - 10);

        // Calculate translation transformation
        int[] translatedX = new int[3];
        int[] translatedY = new int[3];
        for (int i = 0; i < 3; i++) {
            translatedX[i] = adjustedX[i] + diwas_tx;
            translatedY[i] = adjustedY[i] - diwas_ty; // Subtract because graphics Y moves downward
        }

        // Draw translated triangle
        g.setColor(Color.RED);
        g.drawPolygon(translatedX, translatedY, 3);
        g.drawString("Translated Triangle", translatedX[0] - 10, translatedY[0] - 10);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter coordinates of the triangle's vertices:");
        int[] x = new int[3];
        int[] y = new int[3];

        for (int i = 0; i < 3; i++) {
            System.out.print("Enter x" + (i + 1) + " and y" + (i + 1) + " coordinates: ");
            x[i] = scanner.nextInt();
            y[i] = scanner.nextInt();
        }

        System.out.print("Enter the translation X (tx): ");
        int diwas_tx = scanner.nextInt();
        System.out.print("Enter the translation Y (ty): ");
        int diwas_ty = scanner.nextInt();

        // Create window
        JFrame frame = new JFrame("Diwas ko 2D Translation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);

        Santosh2DTranslation plot = new Santosh2DTranslation(x, y, diwas_tx, diwas_ty);
        frame.add(plot);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        scanner.close();
    }
}