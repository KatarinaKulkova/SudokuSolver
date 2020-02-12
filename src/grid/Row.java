package grid;

public class Row extends SetOfNine {

    /** All the squares associated with row. **/
    Square[] row = new Square[9];

    @Override
    public boolean noIssue() {
        for (int i = 0; i < 9; i++) {
            for (int j = i+1; j < 9; j++){
                if (!this.row[i].isEmpty() && (this.row[i].getValue() == this.row[j].getValue())){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void addSquare(Square sqr, int pos) {
        this.row[pos] = sqr;
    }
}