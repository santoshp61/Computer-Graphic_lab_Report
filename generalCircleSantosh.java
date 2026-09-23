import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

public class generalCircleSantosh extends Frame {
    int radius;

    public generalCircleSantosh(int r) {
        radius = r;

        setTitle("General Circle Drawing -> SANTOSH LAMICHHANE");
        setSize(500, 500);
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
        int xc = getWidth() / 2;
        int yc = getHeight() / 2;

        g.setColor(Color.GRAY);
        g.drawLine(0, yc, getWidth(), yc); 
        g.drawLine(xc, 0, xc, getHeight()); 

        g.setColor(Color.BLUE);
        for (int x = -radius; x <= radius; x++) {
            double y = Math.sqrt(radius * radius - x * x);
            int xPixel = xc + x;
            int yPixel1 = (int) Math.round(yc - y);
            int yPixel2 = (int) Math.round(yc + y);

            g.fillRect(xPixel, yPixel1, 1, 1);
            g.fillRect(xPixel, yPixel2, 1, 1);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter radius of the circle: ");
        int diwas_r = sc.nextInt();
        sc.close();

        new generalCircleSantosh(diwas_r);
    }
}