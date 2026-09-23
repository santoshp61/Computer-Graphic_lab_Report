import java.util.Scanner;
import java.awt.*;
import java.awt.event.*;

class midpointEllipseSantosh extends Frame {
    int santoshCenterX, santoshCenterY, santoshRx, santoshRy;
    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 800;
    private final int LEGEND_OFFSET_X = 20;
    private final int LEGEND_OFFSET_Y = 40;
    private final int LEGEND_SPACING = 20;
    private final int LEGEND_BOX_SIZE = 15;

    public midpointEllipseSantosh() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the x-coordinate of the center :");
        santoshCenterX = sc.nextInt();
        System.out.println("Enter the y-coordinate of the center :");
        santoshCenterY = sc.nextInt();
        System.out.println("Enter the x-radius (rx):");
        santoshRx = sc.nextInt();
        System.out.println("Enter the y-radius (ry):");
        santoshRy = sc.nextInt();
        sc.close();

        this.setTitle("Midpoint Ellipse Drawing -> By Santosh");
        this.setLayout(null);
        this.setBounds(100, 100, WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);

        this.addWindowListener(new WindowAdapter() {
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
        g.drawLine(centerX + 3, 10, centerX, 0);

        santoshMidpointEllipse(g, santoshCenterX, santoshCenterY, santoshRx, santoshRy);

        g.setColor(Color.BLUE);
        Point center_awt = toAWTCoords(santoshCenterX, santoshCenterY);
        g.fillOval(center_awt.x - 4, center_awt.y - 4, 8, 8);
        g.drawString("Center (" + santoshCenterX + "," + santoshCenterY + ")", center_awt.x + 10, center_awt.y);

        int legendX = LEGEND_OFFSET_X;
        int legendY = LEGEND_OFFSET_Y;

        g.setColor(Color.BLUE);
        g.fillRect(legendX, legendY, LEGEND_BOX_SIZE, LEGEND_BOX_SIZE);
        g.setColor(Color.BLACK);
        g.drawString("Ellipse Center", legendX + LEGEND_BOX_SIZE + 5, legendY + LEGEND_BOX_SIZE - 3);

        legendY += LEGEND_SPACING;
        g.setColor(Color.RED);
        g.fillRect(legendX, legendY, LEGEND_BOX_SIZE, LEGEND_BOX_SIZE);
        g.setColor(Color.BLACK);
        g.drawString("Drawn Ellipse", legendX + LEGEND_BOX_SIZE + 5, legendY + LEGEND_BOX_SIZE - 3);

        legendY += LEGEND_SPACING;
        g.setColor(Color.BLACK);
        g.drawString("Black: Border/Axes", legendX, legendY + LEGEND_BOX_SIZE - 3);
    }

    private void santoshMidpointEllipse(Graphics santoshg, int santoshPlotCenterX_std, int santoshPlotCenterY_std, int santoshPlotRx, int santoshPlotRy) {
        double santoshdx, santoshdy, santoshd1, santoshd2;
        int santoshx_offset, santoshy_offset;

        santoshx_offset = 0;
        santoshy_offset = santoshPlotRy;

        santoshd1 = santoshPlotRy * santoshPlotRy - santoshPlotRx * santoshPlotRx * santoshPlotRy + 0.25 * santoshPlotRx * santoshPlotRx;
        santoshdx = 2 * santoshPlotRy * santoshPlotRy * santoshx_offset;
        santoshdy = 2 * santoshPlotRx * santoshPlotRx * santoshy_offset;

        santoshEllipsePoints(santoshg, santoshPlotCenterX_std, santoshPlotCenterY_std, santoshx_offset, santoshy_offset);

        while (santoshdx < santoshdy) {
            if (santoshd1 < 0) {
                santoshx_offset++;
                santoshdx = santoshdx + 2 * santoshPlotRy * santoshPlotRy;
                santoshd1 = santoshd1 + santoshdx + santoshPlotRy * santoshPlotRy;
            } else {
                santoshx_offset++;
                santoshy_offset--;
                santoshdx = santoshdx + 2 * santoshPlotRy * santoshPlotRy;
                santoshdy = santoshdy - 2 * santoshPlotRx * santoshPlotRx;
                santoshd1 = santoshd1 + santoshdx - santoshdy + santoshPlotRy * santoshPlotRy;
            }
            santoshEllipsePoints(santoshg, santoshPlotCenterX_std, santoshPlotCenterY_std, santoshx_offset, santoshy_offset);
        }

        santoshd2 = santoshPlotRy * santoshPlotRy * (santoshx_offset + 0.5) * (santoshx_offset + 0.5) +
                    santoshPlotRx * santoshPlotRx * (santoshy_offset - 1) * (santoshy_offset - 1) -
                    santoshPlotRx * santoshPlotRx * santoshPlotRy * santoshPlotRy;

        while (santoshy_offset >= 0) {
            santoshEllipsePoints(santoshg, santoshPlotCenterX_std, santoshPlotCenterY_std, santoshx_offset, santoshy_offset);
            if (santoshd2 > 0) {
                santoshy_offset--;
                santoshdy = santoshdy - 2 * santoshPlotRx * santoshPlotRx;
                santoshd2 = santoshd2 + santoshPlotRx * santoshPlotRx - santoshdy;
            } else {
                santoshy_offset--;
                santoshx_offset++;
                santoshdx = santoshdx + 2 * santoshPlotRy * santoshPlotRy;
                santoshdy = santoshdy - 2 * santoshPlotRx * santoshPlotRx;
                santoshd2 = santoshd2 + santoshdx - santoshdy + santoshPlotRx * santoshPlotRx;
            }
        }
    }

    private void santoshEllipsePoints(Graphics santoshg, int santoshPlotCenterX_std, int santoshPlotCenterY_std, int santoshPlotX_offset, int santoshPlotY_offset) {
        santoshg.setColor(Color.RED);
        Point p_awt;

        p_awt = toAWTCoords(santoshPlotCenterX_std + santoshPlotX_offset, santoshPlotCenterY_std + santoshPlotY_offset);
        santoshg.fillRect(p_awt.x, p_awt.y, 1, 1);

        p_awt = toAWTCoords(santoshPlotCenterX_std - santoshPlotX_offset, santoshPlotCenterY_std + santoshPlotY_offset);
        santoshg.fillRect(p_awt.x, p_awt.y, 1, 1);

        p_awt = toAWTCoords(santoshPlotCenterX_std + santoshPlotX_offset, santoshPlotCenterY_std - santoshPlotY_offset);
        santoshg.fillRect(p_awt.x, p_awt.y, 1, 1);

        p_awt = toAWTCoords(santoshPlotCenterX_std - santoshPlotX_offset, santoshPlotCenterY_std - santoshPlotY_offset);
        santoshg.fillRect(p_awt.x, p_awt.y, 1, 1);
    }

    public static void main(String[] args) {
        new midpointEllipseSantosh();
    }
}