import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class ThreeDTranslation_santosh extends JPanel {
    private int[][] santosh_cube1 = new int[8][2];
    private int[][] santosh_cube2 = new int[8][2];

    public ThreeDTranslation_santosh(int[][] as_vertices, int ab_tx, int ab_ty) {
        for (int i = 0; i < 8; i++) {
            santosh_cube1[i] = as_vertices[i];
            santosh_cube2[i][0] = as_vertices[i][0] + ab_tx;
            santosh_cube2[i][1] = as_vertices[i][1] + ab_ty;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int w = getWidth();
        int h = getHeight();

        g.setColor(Color.GRAY);
        g.drawLine(0, h / 2, w, h / 2);
        g.drawLine(w / 2, 0, w / 2, h);

        int xOffset = w / 2;
        int yOffset = h / 2;

        as_drawCube(g, santosh_cube1, Color.BLACK, "Original", "Cube 1", xOffset, yOffset);
        as_drawCube(g, santosh_cube2, new Color(150, 75, 0), "Translated", "Cube 2", xOffset, yOffset);
    }

    private void as_drawCube(Graphics g, int[][] ba_cube, Color as_edgeColor, String ab_label, String ab_name, int xOffset, int yOffset) {
        g.setColor(as_edgeColor);
        as_drawLine(g, ba_cube, 0, 1, xOffset, yOffset);
        as_drawLine(g, ba_cube, 1, 2, xOffset, yOffset);
        as_drawLine(g, ba_cube, 2, 3, xOffset, yOffset);
        as_drawLine(g, ba_cube, 3, 0, xOffset, yOffset);
        as_drawLine(g, ba_cube, 4, 5, xOffset, yOffset);
        as_drawLine(g, ba_cube, 5, 6, xOffset, yOffset);
        as_drawLine(g, ba_cube, 6, 7, xOffset, yOffset);
        as_drawLine(g, ba_cube, 7, 4, xOffset, yOffset);

        for (int i = 0; i < 4; i++) {
            as_drawLine(g, ba_cube, i, i + 4, xOffset, yOffset);
        }

        for (int i = 0; i < 8; i++) {
            g.fillOval(ba_cube[i][0] + xOffset - 4, flipY(ba_cube[i][1]) - 4, 8, 8);
        }

        g.setFont(new Font("Arial", Font.PLAIN, 14));
        g.drawString(ab_label, ba_cube[0][0] + xOffset - 20, flipY(ba_cube[0][1]) - 20);
        g.setFont(new Font("Arial", Font.BOLD, 16));
    }

    private void as_drawLine(Graphics g, int[][] ba_cube, int santosh_i, int santosh_j, int xOffset, int yOffset) {
        g.drawLine(ba_cube[santosh_i][0] + xOffset, flipY(ba_cube[santosh_i][1]), ba_cube[santosh_j][0] + xOffset, flipY(ba_cube[santosh_j][1]));
    }

    private int flipY(int y) {
        return getHeight() / 2 - y;
    }

    public static void main(String[] args) {
        Scanner santosh_scanner = new Scanner(System.in);
        int[][] sigdel_vertices = new int[8][2];

        System.out.println("Enter 8 vertices of the cube :");
        for (int i = 0; i < 8; i++) {
            System.out.print("Vertex " + (i + 1) + ": ");
            sigdel_vertices[i][0] = santosh_scanner.nextInt();
            sigdel_vertices[i][1] = santosh_scanner.nextInt();
        }

        System.out.print("Enter translation in x (tx): ");
        int santosh_tx = santosh_scanner.nextInt();
        System.out.print("Enter translation in y (ty): ");
        int santosh_ty = santosh_scanner.nextInt();
        santosh_scanner.close();

        JFrame santosh_frame = new JFrame("Santosh ko 3D Cube Translation");
        ThreeDTranslation_santosh santosh_panel = new ThreeDTranslation_santosh(sigdel_vertices, santosh_tx, santosh_ty);

        santosh_frame.add(santosh_panel);
        santosh_frame.setSize(1200, 800);
        santosh_frame.setLocationRelativeTo(null);
        santosh_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        santosh_frame.setVisible(true);
    }
}