import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

public class ShearingY_Santosh extends Frame {
    int boss_x1, boss_y1, boss_x2, boss_y2, boss_x3, boss_y3;
    double boss_shy;
    int boss_sx1, boss_sy1, boss_sx2, boss_sy2, boss_sx3, boss_sy3;

    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 800;
    private final int LEGEND_OFFSET_X = 20;
    private final int LEGEND_OFFSET_Y = 40;
    private final int LEGEND_SPACING = 20;
    private final int LEGEND_BOX_SIZE = 15;

    public ShearingY_Santosh() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter x1 (relative to center)");
        boss_x1 = sc.nextInt();
        System.out.println("Enter y1 (relative to center)");
        boss_y1 = sc.nextInt();

        System.out.println("Enter x2 (relative to center)");
        boss_x2 = sc.nextInt();
        System.out.println("Enter y2 (relative to center)");
        boss_y2 = sc.nextInt();

        System.out.println("Enter x3 (relative to center)");
        boss_x3 = sc.nextInt();
        System.out.println("Enter y3 (relative to center)");
        boss_y3 = sc.nextInt();

        System.out.println("Enter shearing factor in y-direction (shy)");
        boss_shy = sc.nextDouble();

        sc.close();

        // Shearing in Y-direction transformation: x' = x, y' = y + shy * x
        boss_sx1 = boss_x1;
        boss_sy1 = (int) Math.round(boss_y1 + boss_shy * boss_x1);

        boss_sx2 = boss_x2;
        boss_sy2 = (int) Math.round(boss_y2 + boss_shy * boss_x2);

        boss_sx3 = boss_x3;
        boss_sy3 = (int) Math.round(boss_y3 + boss_shy * boss_x3);

        this.setTitle("Shearing in Y-Direction -> @Diwas");
        this.setLayout(null);
        this.setBounds(100, 100, WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }

    private Point toAWTCoords(int x, int y) {
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        return new Point(centerX + x, centerY - y);
    }

    @Override
    public void paint(Graphics g) {
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        g.setColor(Color.BLACK);
        g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);

        g.drawLine(0, centerY, getWidth(), centerY);
        g.drawLine(centerX, 0, centerX, getHeight());

        g.drawLine(getWidth() - 10, centerY - 3, getWidth(), centerY);
        g.drawLine(getWidth() - 10, centerY + 3, getWidth(), centerY);
        g.drawLine(centerX - 3, 10, centerX, 0);
   
        g.setColor(Color.BLUE);
        Point p1_orig_awt = toAWTCoords(boss_x1, boss_y1);
        Point p2_orig_awt = toAWTCoords(boss_x2, boss_y2);
        Point p3_orig_awt = toAWTCoords(boss_x3, boss_y3);

        g.drawLine(p1_orig_awt.x, p1_orig_awt.y, p2_orig_awt.x, p2_orig_awt.y);
        g.drawLine(p2_orig_awt.x, p2_orig_awt.y, p3_orig_awt.x, p3_orig_awt.y);
        g.drawLine(p3_orig_awt.x, p3_orig_awt.y, p1_orig_awt.x, p1_orig_awt.y);
        g.drawString("Original", p1_orig_awt.x, p1_orig_awt.y - 5);

        g.setColor(Color.RED);
        Point p1_sheared_awt = toAWTCoords(boss_sx1, boss_sy1);
        Point p2_sheared_awt = toAWTCoords(boss_sx2, boss_sy2);
        Point p3_sheared_awt = toAWTCoords(boss_sx3, boss_sy3);

        g.drawLine(p1_sheared_awt.x, p1_sheared_awt.y, p2_sheared_awt.x, p2_sheared_awt.y);
        g.drawLine(p2_sheared_awt.x, p2_sheared_awt.y, p3_sheared_awt.x, p3_sheared_awt.y);
        g.drawLine(p3_sheared_awt.x, p3_sheared_awt.y, p1_sheared_awt.x, p1_sheared_awt.y);
        g.drawString("Sheared", p1_sheared_awt.x, p1_sheared_awt.y - 5);

        int legendX = LEGEND_OFFSET_X;
        int legendY = LEGEND_OFFSET_Y;

        g.setColor(Color.BLUE);
        g.fillRect(legendX, legendY, LEGEND_BOX_SIZE, LEGEND_BOX_SIZE);
        g.setColor(Color.BLACK);
        g.drawString("Original", legendX + LEGEND_BOX_SIZE + 5, legendY + LEGEND_BOX_SIZE - 3);

        legendY += LEGEND_SPACING;
        g.setColor(Color.RED);
        g.fillRect(legendX, legendY, LEGEND_BOX_SIZE, LEGEND_BOX_SIZE);
        g.setColor(Color.BLACK);
        g.drawString("Sheared", legendX + LEGEND_BOX_SIZE + 5, legendY + LEGEND_BOX_SIZE - 3);

        legendY += LEGEND_SPACING;
        g.setColor(Color.BLACK);
        g.drawString("Black: Border", legendX, legendY + LEGEND_BOX_SIZE - 3);

        legendY += LEGEND_SPACING;
        g.setColor(Color.BLACK);
        g.drawString("Black: Axes", legendX, legendY + LEGEND_BOX_SIZE - 3);
    }

    public static void main(String[] args) {
        new ShearingY_Santosh();
    }
}