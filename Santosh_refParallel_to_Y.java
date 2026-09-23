import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

public class Santosh_refParallel_to_Y extends Frame { 
    int[] santosh_x = new int[3];
    int[] santosh_y = new int[3];
    int santosh_lineX;

    public Santosh_refParallel_to_Y(int[] inputX, int[] inputY, int lineX) {
        for (int i = 0; i < 3; i++) {
            santosh_x[i] = inputX[i];
            santosh_y[i] = inputY[i];
        }
        this.santosh_lineX = lineX;

        setTitle("Reflection Parallel to Y-Axis (x = " + santosh_lineX + ") -> Santosh Thapa");
        setSize(600, 500);
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);

        // Window listener to enable closing window gracefully
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        // Draw vertical mirror line x = k
        g.setColor(Color.GRAY);
        g.drawLine(santosh_lineX, 0, santosh_lineX, getHeight());
        g.drawString("x = " + santosh_lineX, santosh_lineX + 5, 50);

        g.setColor(Color.BLUE);
        g.drawPolygon(santosh_x, santosh_y, 3);
        g.drawString("Original", santosh_x[0] + 10, santosh_y[0] - 10);

        
        int[] santosh_reflectX = new int[3];
        for (int i = 0; i < 3; i++) {
            santosh_reflectX[i] = 2 * santosh_lineX - santosh_x[i];
        }

        // Draw reflected triangle (Red)
        g.setColor(Color.RED);
        g.drawPolygon(santosh_reflectX, santosh_y, 3);
        g.drawString("Reflected", santosh_reflectX[0] + 10, santosh_y[0] - 10);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[] santosh_userX = new int[3];
        int[] santosh_userY = new int[3];

        System.out.println("Enter coordinates for 3 vertices of the triangle:");
        for (int i = 0; i < 3; i++) {
            System.out.print("Enter Vertex " + (i + 1) + " X: ");
            santosh_userX[i] = scan.nextInt();

            System.out.print("Enter Vertex " + (i + 1) + " Y: ");
            santosh_userY[i] = scan.nextInt();
        }

        System.out.print("Enter X-coordinate (k) of the vertical mirror line x = k: ");
        int santosh_k = scan.nextInt();

        scan.close();
        new Santosh_refParallel_to_Y(santosh_userX, santosh_userY, santosh_k);
    }
}