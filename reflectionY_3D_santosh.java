import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class reflectionY_3D_santosh extends JPanel {
    private int[][] originalCube = new int[8][2];
    private int[][] reflectedCube = new int[8][2];

    public reflectionY_3D_santosh(int[][] vertices) {
        for (int i = 0; i < 8; i++) {
            originalCube[i][0] = vertices[i][0];
            originalCube[i][1] = vertices[i][1];
        }
        for (int i = 0; i < 8; i++) {
            reflectedCube[i][0] = -vertices[i][0];
            reflectedCube[i][1] = vertices[i][1];
        }
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
        drawCube(g, originalCube, Color.BLACK, "Original Cube", "CUBE 1", xOffset);
        drawCube(g, reflectedCube, Color.BLUE, "Reflected Y", "CUBE RY", xOffset);
    }

    private void drawCube(Graphics g, int[][] cube, Color color, String label, String name, int xOffset) {
        g.setColor(color);
        drawLine(g, cube, 0, 1, xOffset);
        drawLine(g, cube, 1, 2, xOffset);
        drawLine(g, cube, 2, 3, xOffset);
        drawLine(g, cube, 3, 0, xOffset);
        drawLine(g, cube, 4, 5, xOffset);
        drawLine(g, cube, 5, 6, xOffset);
        drawLine(g, cube, 6, 7, xOffset);
        drawLine(g, cube, 7, 4, xOffset);

        for (int i = 0; i < 4; i++) {
            drawLine(g, cube, i, i + 4, xOffset);
        }

        for (int i = 0; i < 8; i++) {
            g.fillOval(
                cube[i][0] + xOffset - 4,
                flipY(cube[i][1]) - 4,
                8, 8
            );
        }

        g.setFont(new Font("Arial", Font.PLAIN, 14));
        g.drawString(
            label,
            cube[0][0] + xOffset - 20,
            flipY(cube[0][1]) - 20
        );
        g.setFont(new Font("Arial", Font.BOLD, 16));
    }

    private void drawLine(Graphics g, int[][] cube, int i, int j, int xOffset) {
        g.drawLine(
            cube[i][0] + xOffset, flipY(cube[i][1]),
            cube[j][0] + xOffset, flipY(cube[j][1])
        );
    }

    private int flipY(int y) {
        return getHeight() / 2 - y;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] vertices = new int[8][2];

        System.out.println("Enter 8 vertices of the cube:");
        for (int i = 0; i < 8; i++) {
            System.out.print("Vertex " + (i + 1) + ": ");
            vertices[i][0] = sc.nextInt();
            vertices[i][1] = sc.nextInt();
        }
        sc.close();

        JFrame frame = new JFrame("SANTOSH --> 3D Cube Reflection on Y-axis");
        frame.add(new reflectionY_3D_santosh(vertices));
        frame.setSize(1200, 800);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}