import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

public class SantoshrefParallel_to_X extends Frame {
    int[] deboss_x = new int[3];
    int[] deboss_y = new int[3];
    int deboss_lineY;

    public SantoshrefParallel_to_X(int[] inputX, int[] inputY, int lineY) {
        for (int i = 0; i < 3; i++) {
            deboss_x[i] = inputX[i];
            deboss_y[i] = inputY[i];
        }
        this.deboss_lineY = lineY;

        setTitle("Reflection Parallel to X-Axis (y = " + deboss_lineY + ") -> Santosh KC");
        setSize(600, 400);
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
        g.setColor(Color.GRAY);
        g.drawLine(0, deboss_lineY, getWidth(), deboss_lineY);
        g.drawString("y = " + deboss_lineY, 10, deboss_lineY - 5);

        g.setColor(Color.BLUE);
        g.drawPolygon(deboss_x, deboss_y, 3);
        g.drawString("Before", deboss_x[0] + 10, deboss_y[0] - 10);

        int[] deboss_reflectY = new int[3];
        for (int i = 0; i < 3; i++) {
            deboss_reflectY[i] = 2 * deboss_lineY - deboss_y[i];
        }

        g.setColor(Color.RED);
        g.drawPolygon(deboss_x, deboss_reflectY, 3);
        g.drawString("After", deboss_x[0] + 10, deboss_reflectY[0] - 10);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[] deboss_userX = new int[3];
        int[] deboss_userY = new int[3];

        System.out.println("Enter co-ordinates for 3 vertices of the triangle:");
        for (int i = 0; i < 3; i++) {
            System.out.print("Vertex " + (i + 1) + " X: ");
            deboss_userX[i] = scan.nextInt();

            System.out.print("Vertex " + (i + 1) + " Y: ");
            deboss_userY[i] = scan.nextInt();
        }

        System.out.print("Enter Y-coordinate (k) of the horizontal mirror line y = k: ");
        int deboss_k = scan.nextInt();

        scan.close();
        new SantoshrefParallel_to_X(deboss_userX, deboss_userY, deboss_k);
    }
}