import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

public class refY_MX_C_Santosh extends Frame {
    int[] origX = new int[3];
    int[] origY = new int[3];
    double slope;
    double intercept;
    int width = 600, height = 600;

    public refY_MX_C_Santosh(int[] x, int[] y, double m, double c) {
        origX = x;
        origY = y;
        slope = m;
        intercept = c;

        setTitle("Reflection through y = mx + c -> Santosh");
        setSize(width, height);
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

        g.setColor(Color.LIGHT_GRAY);
        g.drawLine(0, height / 2, width, height / 2); // X-axis
        g.drawLine(width / 2, 0, width / 2, height);  // Y-axis

        g.setColor(Color.BLUE);
        int[] drawX = new int[3];
        int[] drawY = new int[3];
        for (int i = 0; i < 3; i++) {
            drawX[i] = width / 2 + origX[i];
            drawY[i] = height / 2 - origY[i];
        }
        g.drawPolygon(drawX, drawY, 3);

        int originalCentroidX = (drawX[0] + drawX[1] + drawX[2]) / 3;
        int originalCentroidY = (drawY[0] + drawY[1] + drawY[2]) / 3;
        g.setColor(Color.BLACK);
        g.drawString("Original", originalCentroidX + 10, originalCentroidY);

        int[] reflX = new int[3];
        int[] reflY = new int[3];
        for (int i = 0; i < 3; i++) {
            double x = origX[i];
            double y = origY[i];

            double xPrime = ((1 - slope * slope) * x + 2 * slope * y - 2 * slope * intercept) / (1 + slope * slope);
            double yPrime = ((slope * slope - 1) * y + 2 * slope * x + 2 * intercept) / (1 + slope * slope);

            reflX[i] = width / 2 + (int) Math.round(xPrime);
            reflY[i] = height / 2 - (int) Math.round(yPrime);
        }

        g.setColor(Color.RED);
        g.drawPolygon(reflX, reflY, 3);

        int reflectedCentroidX = (reflX[0] + reflX[1] + reflX[2]) / 3;
        int reflectedCentroidY = (reflY[0] + reflY[1] + reflY[2]) / 3;
        g.setColor(Color.BLACK);
        g.drawString("Reflected", reflectedCentroidX + 10, reflectedCentroidY);

        g.setColor(Color.GREEN);
        int x1 = -width / 2;
        int y1 = (int) Math.round(slope * x1 + intercept);
        int x2 = width / 2;
        int y2 = (int) Math.round(slope * x2 + intercept);

        g.drawLine(width / 2 + x1, height / 2 - y1, width / 2 + x2, height / 2 - y2);

        g.setColor(Color.BLACK);
        g.drawString("y = " + slope + "x + " + intercept, width / 2 + 10, height / 2 - (int) Math.round(intercept) - 10);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] santosh_x = new int[3];
        int[] santosh_y = new int[3];

        System.out.println("Enter coordinates of 3 triangle vertices:");
        for (int i = 0; i < 3; i++) {
            System.out.print("Enter Vertex " + (i + 1) + " X: ");
            santosh_x[i] = sc.nextInt();

            System.out.print("Enter Vertex " + (i + 1) + " Y: ");
            santosh_y[i] = sc.nextInt();
        }

        System.out.print("Enter slope (m) of line y = mx + c: ");
        double m = sc.nextDouble();

        System.out.print("Enter intercept (c) of line y = mx + c: ");
        double c = sc.nextDouble();

        sc.close();
        new refY_MX_C_Santosh(santosh_x, santosh_y, m, c);
    }
}