package grid;

/** Abstract class to represent all possible sets of nine numbers. **/
abstract class SetOfNine {

    /** Returns false if two numbers that are the same have been found in the set.
        Returns true otherwise. **/
    abstract boolean noIssue();

    /** Adds square to the set. **/
    abstract void addSquare(Square sqr, int pos);

}
