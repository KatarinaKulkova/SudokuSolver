package grid;

/** Class that represents one square in the grid. **/
public class Square {

    private int x;
    private int y;
    private int value = 0;
    private Row row;
    private Column col;
    private Box box;


    /** Constructs an empty square. **/
    public Square(int x, int y){
        this.x = x;
        this.y = y;
    }

    /** Constructs a square with a numerical value. **/
    public Square(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    /** Getters and setters for the row, column and box associated with the square. **/
    public Row getRow() { return this.row; }

    public Column getColumn() { return this.col; }

    public Box getBox() { return this.box; }

    public void setRow(Row row) { this.row = row; }

    public void setColumn(Column col) { this.col = col; }

    public void setBox(Box box) { this.box = box; }


    /** Checks whether square has a numerical value.**/
    public boolean isEmpty() {
        return value == 0;
    }


    /** Getter and setter for value. **/
    public int getValue() { return this.value; }

    public void setValue(int value){
        this.value = value;
    }

}
