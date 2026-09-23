import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class shearingXY3D_SANTOSH extends JPanel {
    private int[][] santosh_orgCube = new int[8][3];
    private int[][] santosh_shearedCube = new int[8][3];
    public shearingXY3D_SANTOSH(int[][] santosh_vertices, double shx, double shy) {
        for (int i = 0; i < 8; i++) {
            santosh_orgCube[i][0] = santosh_vertices[i][0];
            santosh_orgCube[i][1] = santosh_vertices[i][1];
            santosh_orgCube[i][2] = santosh_vertices[i][2];
        }
        for (int i = 0; i < 8; i++) {
            santosh_shearedCube[i][0] = (int) Math.round(santosh_vertices[i][0] + shx * santosh_vertices[i][2]);
            santosh_shearedCube[i][1] = (int) Math.round(santosh_vertices[i][1] + shy * santosh_vertices[i][2]);
            santosh_shearedCube[i][2] = santosh_vertices[i][2];
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
        drawCube(g, santosh_orgCube, Color.BLACK, "Original Cube", xOffset - 200);
        drawCube(g, santosh_shearedCube, Color.RED, "Sheared XY", xOffset + 200);
    }
    private void drawCube(Graphics g, int[][] cube, Color color, String label, int xOffset) {
        g.setColor(color);
        int[][] edges = {
            {0, 1}, {1, 2}, {2, 3}, {3, 0},
            {4, 5}, {5, 6}, {6, 7}, {7, 4},
            {0, 4}, {1, 5}, {2, 6}, {3, 7}
        };
        for (int[] edge : edges) {
            drawLine(g, cube, edge[0], edge[1], xOffset);
        }
        for (int i = 0; i < 8; i++) {
            int[] p = project(cube[i][0], cube[i][1], cube[i][2]);
            g.fillOval(p[0] + xOffset - 4, flipY(p[1]) - 4, 8, 8);
        }
        g.setFont(new Font("Arial", Font.BOLD, 14));
        int[] labelPos = project(cube[0][0], cube[0][1], cube[0][2]);
        g.drawString(label, labelPos[0] + xOffset - 30, flipY(labelPos[1]) - 10);
    }
    private void drawLine(Graphics g, int[][] cube, int i, int j, int xOffset) {
        int[] p1 = project(cube[i][0], cube[i][1], cube[i][2]);
        int[] p2 = project(cube[j][0], cube[j][1], cube[j][2]);

        g.drawLine(
            p1[0] + xOffset, flipY(p1[1]),
            p2[0] + xOffset, flipY(p2[1])
        );
    }
    private int flipY(int y) {
        return getHeight() / 2 - y;
    }
    private int[] project(int x, int y, int z) {
        return new int[]{
            x + (int) (z * 0.5),
            y - (int) (z * 0.5)
        };
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] santosh_vertices = new int[8][3];

        System.out.println("Enter 8 vertices of the cube (x y z):");
        for (int i = 0; i < 8; i++) {
            System.out.print("Vertex " + (i + 1) + ": ");
            santosh_vertices[i][0] = sc.nextInt();
            santosh_vertices[i][1] = sc.nextInt();
            santosh_vertices[i][2] = sc.nextInt();
        }
        System.out.print("Enter shearing factor in x (shx): ");
        double shx = sc.nextDouble();

        System.out.print("Enter shearing factor in y (shy): ");
        double shy = sc.nextDouble();
        sc.close();

        JFrame frame = new JFrame("@SANTOSH - 3D Cube Shearing in XY Direction");
        frame.add(new shearingXY3D_SANTOSH(santosh_vertices, shx, shy));
        frame.setSize(1200, 800);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}