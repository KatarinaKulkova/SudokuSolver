package grid;

public class Grid {

    private final int EMPTY_SQR_VAL = 0;
    private final int GRID_SIZE = 9;
    private final int BOX_SIZE = 3;
    private final int START = 0;

    /** All rows, columns, boxes and squares in the grid. **/
    private Row[] rows = new Row[GRID_SIZE];
    private Column[] columns = new Column[GRID_SIZE];
    private Box[] boxes = new Box[GRID_SIZE];
    private Square[][] grid;


    /** Constructs new grid from squares. Creates all the columns, rows and boxes
        and associates them with the relevant squares. **/
    public Grid(Square[][] grid){

        this.grid = grid;

        for (int x = START; x < GRID_SIZE; x++) {
            for (int y = START; y < GRID_SIZE; y++) {
                Square sqr = this.grid[x][y];
                int index = this.getBox(x,y);
                int pos = this.getPosInBox(x,y);

                if (x == START) { this.rows[y] = new Row(); }
                if (y == START) { this.columns[x] = new Column(); }
                if (pos == START) { this.boxes[index] = new Box(); }

                sqr.setRow(this.rows[y]);
                sqr.setColumn(this.columns[x]);
                sqr.setBox(this.boxes[index]);

                this.rows[y].addSquare(sqr, x);
                this.columns[x].addSquare(sqr, y);
                this.boxes[index].addSquare(sqr, pos);
            }
        }

    }

    /** Retrieves square from the grid based on coordinates. **/
    public Square getSquare(int x, int y){
        return grid[x][y];
    }


    /** Recursive solve method. **/
    public boolean solve() {
        for (int x = START; x < GRID_SIZE; x++) {
            for (int y = START; y < GRID_SIZE; y++) {
                Square sqr = this.getSquare(x, y);
                if (sqr.isEmpty()) {
                    for (int n = START + 1; n <= GRID_SIZE; n++) {
                        sqr.setValue(n);
                        if (isValid(sqr) && this.solve()) {
                            return true;
                        }
                        sqr.setValue(EMPTY_SQR_VAL);
                    }
                    return false;
                }
            }
        }
        return true;
    }


    /** Returns true if the updated square value is Valid, false otherwise. **/
    private boolean isValid(Square sqr) {
        return (sqr.getRow().noIssue()
                && sqr.getColumn().noIssue()
                && sqr.getBox().noIssue());
    }


    /** Gets the index of the box from the boxes array of Grid. **/
    private int getBox(int x, int y) {
        int xComponent = x / BOX_SIZE;
        int yComponent = y / BOX_SIZE;
        return xComponent*BOX_SIZE + yComponent;
    }


    /** Gets the position of a square in a give box. **/
    private int getPosInBox (int x, int y) {
        int xComponent = x % BOX_SIZE;
        int yComponent = y % BOX_SIZE;
        return xComponent*BOX_SIZE + yComponent;
    }
}
