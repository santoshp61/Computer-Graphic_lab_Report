import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class santoshkoRotation_3d extends JPanel {
    private int[][] santosh_cube1 = new int[8][2];
    private int[][] santosh_cube2 = new int[8][2];

    public santoshkoRotation_3d(int[][] santosh_vertices, double rotAngle) {
        for (int i = 0; i < 8; i++) {
            santosh_cube1[i][0] = santosh_vertices[i][0];
            santosh_cube1[i][1] = santosh_vertices[i][1];
        }
        for (int i = 0; i < 8; i++) {
            santosh_cube2[i] = santosh_rotatePoint(santosh_vertices[i], rotAngle);
        }
    }
    private int[] santosh_rotatePoint(int[] point, double angleDegrees) {
        double angleRad = Math.toRadians(-angleDegrees);
        int x = point[0];
        int y = point[1];

        int xr = (int) Math.round(x * Math.cos(angleRad) - y * Math.sin(angleRad));
        int yr = (int) Math.round(x * Math.sin(angleRad) + y * Math.cos(angleRad));
        return new int[]{xr, yr};
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();

        g.setColor(Color.GRAY);
        g.drawLine(0, height / 2, width, height / 2);
        g.drawLine(width / 2, 0, width / 2, height);

        int xOffset = width / 2;
        santosh_drawCube(g, santosh_cube1, Color.BLACK, "Original", "CUBE 1", xOffset);
        santosh_drawCube(g, santosh_cube2, new Color(150, 75, 0), "Rotated", "Cube 2", xOffset);
    }
    private void santosh_drawCube(Graphics g, int[][] ba_cube, Color santosh_edgeColor, String ab_label, String ab_name, int xOffset) {
        g.setColor(santosh_edgeColor);
        santosh_drawLine(g, ba_cube, 0, 1, xOffset);
        santosh_drawLine(g, ba_cube, 1, 2, xOffset);
        santosh_drawLine(g, ba_cube, 2, 3, xOffset);
        santosh_drawLine(g, ba_cube, 3, 0, xOffset);
        santosh_drawLine(g, ba_cube, 4, 5, xOffset);
        santosh_drawLine(g, ba_cube, 5, 6, xOffset);
        santosh_drawLine(g, ba_cube, 6, 7, xOffset);
        santosh_drawLine(g, ba_cube, 7, 4, xOffset);

        for (int i = 0; i < 4; i++) {
            santosh_drawLine(g, ba_cube, i, i + 4, xOffset);
        }

        for (int i = 0; i < 8; i++) {
            g.fillOval(
                ba_cube[i][0] + xOffset - 4,
                flipY(ba_cube[i][1]) - 4,
                8, 8
            );
        }
        g.setFont(new Font("Arial", Font.PLAIN, 14));
        g.drawString(ab_label, ba_cube[0][0] + xOffset - 20, flipY(ba_cube[0][1]) - 20);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString(ab_name, ba_cube[3][0] + xOffset, flipY(ba_cube[3][1]) + 60);
    }

    private void santosh_drawLine(Graphics g, int[][] ba_cube, int santosh_i, int santosh_j, int xOffset) {
        g.drawLine(
            ba_cube[santosh_i][0] + xOffset, flipY(ba_cube[santosh_i][1]),
            ba_cube[santosh_j][0] + xOffset, flipY(ba_cube[santosh_j][1])
        );
    }
    private int flipY(int y) {
        return getHeight() / 2 - y;
    }
    public static void main(String[] args) {
        Scanner santosh_scanner = new Scanner(System.in);
        int[][] santosh_vertices = new int[8][2];

        System.out.println("Enter 8 vertices of the cube: ");
        for (int i = 0; i < 8; i++) {
            System.out.print("Vertex " + (i + 1) + ": ");
            santosh_vertices[i][0] = santosh_scanner.nextInt();
            santosh_vertices[i][1] = santosh_scanner.nextInt();
        }

        System.out.print("Enter rotation angle: ");
        double rotationAngle = santosh_scanner.nextDouble();
        santosh_scanner.close();

        JFrame santosh_frame = new JFrame("SANTOSH --> 3D Cube Rotation");
        santoshkoRotation_3d santosh_panel = new santoshkoRotation_3d(santosh_vertices, rotationAngle);

        santosh_frame.add(santosh_panel);
        santosh_frame.setSize(1200, 800);
        santosh_frame.setLocationRelativeTo(null);
        santosh_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        santosh_frame.setVisible(true);
    }
}