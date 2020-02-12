package grid;

public class Box extends SetOfNine {

    /** All the squares associated with box. **/
    Square[] box = new Square[9];

    @Override
    public boolean noIssue() {
        for (int i = 0; i < 9; i++) {
            for (int j = i+1; j < 9; j++){
                if (!this.box[i].isEmpty() && this.box[i].getValue() == this.box[j].getValue()){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void addSquare(Square sqr, int pos) {
        this.box[pos] = sqr;
    }

}
