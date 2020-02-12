package grid;

public class Column extends SetOfNine {

    /** All the squares associated with column. **/
    Square[] column = new Square[9];

    @Override
    public boolean noIssue() {
        for (int i = 0; i < 9; i++) {
            for (int j = i+1; j < 9; j++){
                if (!this.column[i].isEmpty() && this.column[i].getValue() == this.column[j].getValue()){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void addSquare(Square sqr, int pos) {
        this.column[pos] = sqr;
    }

}
