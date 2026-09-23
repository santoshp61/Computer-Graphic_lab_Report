import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

public class ShearingXY_Santosh extends Frame {
    int[] santosh_realX = new int[4];
    int[] santosh_realY = new int[4];
    int[] santosh_shearX = new int[4];
    int[] santosh_shearY = new int[4];

   
    int[] draw_origY = new int[4];
    int[] draw_shearY = new int[4];

    double santosh_shx, santosh_shy;

    public ShearingXY_Santosh(int[] x, int[] y, double shx, double shy) {
        for (int i = 0; i < 4; i++) {
            santosh_realX[i] = x[i];
            santosh_realY[i] = y[i];

        
            santosh_shearX[i] = (int) Math.round(x[i] + shx * y[i]);
            santosh_shearY[i] = (int) Math.round(y[i] + shy * x[i]);

           
            draw_shearY[i] = -santosh_shearY[i];
        }
        this.santosh_shx = shx;
        this.santosh_shy = shy;

        setTitle("Shearing in XY-direction -> @DIWAS");
        setSize(800, 600);
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

        // Move origin (0, 0) to frame center
        g2.translate(getWidth() / 2, getHeight() / 2);

        // Draw coordinate axes
        g2.setColor(Color.LIGHT_GRAY);
        g2.drawLine(-getWidth() / 2, 0, getWidth() / 2, 0); // X-axis
        g2.drawLine(0, -getHeight() / 2, 0, getHeight() / 2); // Y-axis

        // Draw original 4-sided polygon (Blue)
        g2.setColor(Color.BLUE);
        g2.drawPolygon(santosh_realX, draw_origY, 4);
        g2.drawString("Original", santosh_realX[0] + 10, draw_origY[0] - 10);

        // Draw sheared 4-sided polygon (Red)
        g2.setColor(Color.RED);
        g2.drawPolygon(santosh_shearX, draw_shearY, 4);
        g2.drawString("Sheared", santosh_shearX[0] + 10, draw_shearY[0] - 10);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] kanxo_x = new int[4];
        int[] kanxo_y = new int[4];

        System.out.println("Enter coordinates for 4 vertices of the shape:");

        for (int i = 0; i < 4; i++) {
            System.out.print("Enter Vertex " + (i + 1) + " X : ");
            kanxo_x[i] = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter Vertex " + (i + 1) + " Y : ");
            kanxo_y[i] = Integer.parseInt(scanner.nextLine());
        }

        System.out.print("Enter the shear factor in X direction (shx): ");
        double kanxi_shx = Double.parseDouble(scanner.nextLine());

        System.out.print("Enter the shear factor in Y direction (shy): ");
        double kanxi_shy = Double.parseDouble(scanner.nextLine());

        scanner.close();

        new ShearingXY_Santosh(kanxo_x, kanxo_y, kanxi_shx, kanxi_shy);
    }
}