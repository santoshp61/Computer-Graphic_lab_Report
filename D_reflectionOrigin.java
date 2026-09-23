import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

public class D_reflectionOrigin extends Frame {
    int[] boss_x = new int[3];
    int[] boss_y = new int[3];
    int dboss_canvasWidth = 600;
    int dboss_canvasHeight = 400;

    public D_reflectionOrigin(int[] boss_xInput, int[] boss_yInput) {
        for (int i = 0; i < 3; i++) {
            boss_x[i] = boss_xInput[i];
            boss_y[i] = boss_yInput[i];
        }

        setTitle("Diwas Lamichhane -> 2D Reflection Through Origin");
        setSize(dboss_canvasWidth, dboss_canvasHeight);
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);

        // Window listener to handle closing the application window gracefully
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        // Draw coordinate axes
        g.setColor(Color.GRAY);
        g.drawLine(0, dboss_canvasHeight / 2, dboss_canvasWidth, dboss_canvasHeight / 2); // X-axis
        g.drawLine(dboss_canvasWidth / 2, 0, dboss_canvasWidth / 2, dboss_canvasHeight);  // Y-axis

        // Original Triangle Transformation (Centered on Canvas)
        int[] centeredX = new int[3];
        int[] centeredY = new int[3];
        for (int i = 0; i < 3; i++) {
            centeredX[i] = dboss_canvasWidth / 2 + boss_x[i];
            centeredY[i] = dboss_canvasHeight / 2 - boss_y[i];
        }

        // Draw original triangle (Blue)
        g.setColor(Color.BLUE);
        g.drawPolygon(centeredX, centeredY, 3);
        g.drawString("Before", centeredX[0] + 10, centeredY[0] - 10);

        // Reflection through Origin Transformation: (x, y) -> (-x, -y)
        int[] reflectX = new int[3];
        int[] reflectY = new int[3];
        for (int i = 0; i < 3; i++) {
            reflectX[i] = dboss_canvasWidth / 2 - boss_x[i];
            reflectY[i] = dboss_canvasHeight / 2 + boss_y[i];
        }

        // Draw reflected triangle (Red)
        g.setColor(Color.RED);
        g.drawPolygon(reflectX, reflectY, 3);
        g.drawString("After", reflectX[0] + 10, reflectY[0] - 10);
    }

    public static void main(String[] args) {
        Scanner diwas_input = new Scanner(System.in);
        int[] userX = new int[3];
        int[] userY = new int[3];

        System.out.println("Enter co-ordinates for 3 vertices of triangle:");
        for (int i = 0; i < 3; i++) {
            System.out.print("Vertex " + (i + 1) + " X: ");
            userX[i] = diwas_input.nextInt();

            System.out.print("Vertex " + (i + 1) + " Y: ");
            userY[i] = diwas_input.nextInt();
        }

        diwas_input.close();
        new D_reflectionOrigin(userX, userY);
    }
}