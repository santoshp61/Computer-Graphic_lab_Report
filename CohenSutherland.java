public class CohenSutherland {

    // Define 4-bit region codes
    private static final int INSIDE = 0; // 0000
    private static final int LEFT   = 1; // 0001
    private static final int RIGHT  = 2; // 0010
    private static final int BOTTOM = 4; // 0100
    private static final int TOP    = 8; // 1000

    // Define rectangle bounds (Clipping Window)
    private static final double xMin = 10.0;
    private static final double yMin = 10.0;
    private static final double xMax = 100.0;
    private static final double yMax = 100.0;

    /**
     * Compute region code for a point (x, y) relative to the clipping window.
     */
    private static int computeCode(double x, double y) {
        int code = INSIDE;

        if (x < xMin) {
            code |= LEFT;
        } else if (x > xMax) {
            code |= RIGHT;
        }

        if (y < yMin) {
            code |= BOTTOM;
        } else if (y > yMax) {
            code |= TOP;
        }

        return code;
    }

    /**
     * Clips a line segment from (x1, y1) to (x2, y2) against the window.
     */
    public static void clipLine(double x1, double y1, double x2, double y2) {
        int code1 = computeCode(x1, y1);
        int code2 = computeCode(x2, y2);

        boolean accept = false;

        while (true) {
            // Case 1: Both endpoints inside window (Trivial Accept)
            if ((code1 == 0) && (code2 == 0)) {
                accept = true;
                break;
            }
            // Case 2: Both endpoints share an outside region (Trivial Reject)
            else if ((code1 & code2) != 0) {
                break;
            }
            // Case 3: Line needs clipping
            else {
                int codeOut;
                double x = 0, y = 0;

                // Pick an endpoint that is outside the clip window
                codeOut = (code1 != 0) ? code1 : code2;

                // Find intersection point using line equation y = y1 + m * (x - x1) or x = x1 + (1/m) * (y - y1)
                if ((codeOut & TOP) != 0) {
                    // Point is above the clip window
                    x = x1 + (x2 - x1) * (yMax - y1) / (y2 - y1);
                    y = yMax;
                } else if ((codeOut & BOTTOM) != 0) {
                    // Point is below the clip window
                    x = x1 + (x2 - x1) * (yMin - y1) / (y2 - y1);
                    y = yMin;
                } else if ((codeOut & RIGHT) != 0) {
                    // Point is to the right of clip window
                    y = y1 + (y2 - y1) * (xMax - x1) / (x2 - x1);
                    x = xMax;
                } else if ((codeOut & LEFT) != 0) {
                    // Point is to the left of clip window
                    y = y1 + (y2 - y1) * (xMin - x1) / (x2 - x1);
                    x = xMin;
                }

                // Replace the outside point with the intersection point and update its code
                if (codeOut == code1) {
                    x1 = x;
                    y1 = y;
                    code1 = computeCode(x1, y1);
                } else {
                    x2 = x;
                    y2 = y;
                    code2 = computeCode(x2, y2);
                }
            }
        }

        if (accept) {
            System.out.printf("Line accepted from (%.2f, %.2f) to (%.2f, %.2f)\n", x1, y1, x2, y2);
        } else {
            System.out.println("Line completely rejected (Outside window)");
        }
    }

    public static void main(String[] args) {
        System.out.printf("Clipping Window Bounds: [%.1f, %.1f] to [%.1f, %.1f]\n\n", xMin, yMin, xMax, yMax);

        // Test Case 1: Completely Inside
        System.out.print("Line 1 (50, 50) to (70, 70): ");
        clipLine(50, 50, 70, 70);

        // Test Case 2: Completely Outside
        System.out.print("Line 2 (120, 50) to (150, 70): ");
        clipLine(120, 50, 150, 70);

        // Test Case 3: Partially Inside (Needs clipping)
        System.out.print("Line 3 (5, 5) to (120, 120): ");
        clipLine(5, 5, 120, 120);
    }
}