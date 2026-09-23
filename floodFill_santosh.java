import java.awt.*;
import java.awt.event.*;

public class floodFill_santosh extends Frame {
    private int[][] image;
    private final int WIDTH = 500, HEIGHT = 500;
    private final int COLOR_FILL = 3;
    private final int COLOR_EMPTY = 1;

    public floodFill_santosh() {
        setTitle("Flood Fill Algorithm - Santosh");
        setSize(WIDTH, HEIGHT);
        setVisible(true);
        setLocation(100, 100);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        image = new int[WIDTH][HEIGHT];
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                if (i % 2 == 0 && j % 2 == 0) {
                    image[i][j] = COLOR_EMPTY;
                } else {
                    image[i][j] = 2; // Initial color
                }
            }
        }

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();

                
                if (x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT) {
                    int targetColor = image[x][y];
                    if (targetColor != COLOR_FILL) {
                        santoshFloodFill(x, y, targetColor, COLOR_FILL);
                        repaint(); 
                    }
                }
            }
        });
    }
    public void santoshFloodFill(int startX, int startY, int targetColor, int replacementColor) {
        if (targetColor == replacementColor) return;

        java.util.Queue<Point> queue = new java.util.LinkedList<>();
        queue.add(new Point(startX, startY));

        while (!queue.isEmpty()) {
            Point p = queue.poll();
            int x = p.x;
            int y = p.y;

            if (x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT) continue;
            if (image[x][y] != targetColor) continue;

            image[x][y] = replacementColor;
            queue.add(new Point(x + 1, y));
            queue.add(new Point(x - 1, y));
            queue.add(new Point(x, y + 1));
            queue.add(new Point(x, y - 1));
        }
    }

    @Override
    public void paint(Graphics g) {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                switch (image[i][j]) {
                    case 1:
                        g.setColor(Color.gray);
                        break; // Empty color
                    case 2:
                        g.setColor(Color.magenta);
                        break; // Initial color
                    case 3:
                        g.setColor(Color.blue);
                        break; // Fill color
                }
                g.fillRect(i, j, 1, 1); 
            }
        }
    }

    public static void main(String[] args) {
        new floodFill_santosh();
    }
}