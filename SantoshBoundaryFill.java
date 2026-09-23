import java.awt.*;
import java.awt.event.*;

public class SantoshBoundaryFill extends Frame {
    private int[][] image;
    private final int WIDTH = 500, HEIGHT = 500;
    private final int COLOR_FILL = 3;
    private final int COLOR_BOUNDARY = 1;
    private final int COLOR_EMPTY = 2;

    public SantoshBoundaryFill() {
        setTitle("Boundary Fill Algorithm - Santosh");
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
                if (i < 100 || i > 400 || j < 100 || j > 400) {
                    image[i][j] = COLOR_BOUNDARY;
                } else {
                    image[i][j] = COLOR_EMPTY;
                }
            }
        }

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();

                if (x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT) {
                    if (image[x][y] != COLOR_BOUNDARY && image[x][y] != COLOR_FILL) {
                        santoshBoundaryFill(x, y, COLOR_FILL);
                        repaint();
                    }
                }
            }
        });
    }

    public void santoshBoundaryFill(int x, int y, int replacementColor) {

        if (x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT || image[x][y] == COLOR_BOUNDARY || image[x][y] == replacementColor) {
            return;
        }
        image[x][y] = replacementColor;

        santoshBoundaryFill(x + 1, y, replacementColor); // Right
        santoshBoundaryFill(x - 1, y, replacementColor); // Left
        santoshBoundaryFill(x, y + 1, replacementColor); // Down
        santoshBoundaryFill(x, y - 1, replacementColor); // Up
    }

    @Override
    public void paint(Graphics g) {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                switch (image[i][j]) {
                    case 1:
                        g.setColor(Color.LIGHT_GRAY);
                        break;
                    case 2:
                        g.setColor(Color.RED);
                        break;
                    case 3:
                        g.setColor(Color.BLUE);
                        break;
                }
                g.fillRect(i, j, 1, 1);
            }
        }
    }

    public static void main(String[] args) {
        new SantoshBoundaryFill();
    }
}