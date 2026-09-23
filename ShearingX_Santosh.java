import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

public class ShearingX_Santosh extends Frame {
    int[] putali_origX = new int[3];
    int[] putali_origY = new int[3];
    int[] putali_shearX = new int[3];
    int[] draw_origY = new int[3];
    double putali_shx;

    public ShearingX_Santosh(int[] x, int[] y, double shx) {
        for (int i = 0; i < 3; i++) {
            putali_origX[i] = x[i];
            putali_origY[i] = y[i];
            
            putali_shearX[i] = (int) Math.round(x[i] + shx * y[i]);
     
            draw_origY[i] = -y[i];
        }
        this.putali_shx = shx;

        setTitle("Shearing in X-direction -> Santosh");
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
        Graphics2D g2 = (Graphics2D) g;
        g2.translate(getWidth() / 2, getHeight() / 2);

        g2.setColor(Color.LIGHT_GRAY);
        g2.drawLine(-getWidth() / 2, 0, getWidth() / 2, 0); // X-axis
        g2.drawLine(0, -getHeight() / 2, 0, getHeight() / 2); // Y-axis

        g2.setColor(Color.BLUE);
        g2.drawPolygon(putali_origX, draw_origY, 3);
        g2.drawString("Original", putali_origX[0] + 10, draw_origY[0] - 10);

        g2.setColor(Color.RED);
        g2.drawPolygon(putali_shearX, draw_origY, 3);
        g2.drawString("Sheared", putali_shearX[0] + 10, draw_origY[0] - 10);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] kanxi_x = new int[3];
        int[] kanxi_y = new int[3];

        System.out.println("Enter coordinates for 3 vertices of the triangle:");
        for (int i = 0; i < 3; i++) {
            System.out.print("Vertex " + (i + 1) + " X: ");
            kanxi_x[i] = scanner.nextInt();

            System.out.print("Vertex " + (i + 1) + " Y: ");
            kanxi_y[i] = scanner.nextInt();
        }

        System.out.print("Enter the shear factor in X direction (shx): ");
        double kanxi_shx = scanner.nextDouble();

        scanner.close();
        new ShearingX_Santosh(kanxi_x, kanxi_y, kanxi_shx);
    }
}