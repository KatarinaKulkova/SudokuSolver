package main;

import grid.Grid;
import grid.Square;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SudokuSolver extends JFrame implements ActionListener {

    final private int dim = 9;
    final private int xOffset = 50;
    final private int yOffset = 75;
    final private int squareSize = 35;


    /** Swing components used in the UI. **/
    private final JTextField[][] grid = new JTextField[dim][dim];
    private final JButton button = new JButton("Solve");
    private final JButton clearB = new JButton("Clear");
    private final JLabel message = new JLabel("Enter numbers from 1 to 9. Anything " +
                                                    "else will be treated as an empty square.");

    /** Grid object that contains the solve method. **/
    private Grid sudokuGrid;

    public SudokuSolver(){
    }

    /** Function that generates an internal representation of the grid based on the UI input.
        Makes sure only valid input is passed to the grid. Instantiates the Grid object for
        the Sudoku solver.**/
    private void generateGrid() {
        Square[][] squares = new Square[dim][dim];

        for(int x = 0; x < 9; x++) {
            for(int y = 0; y < 9; y++) {
               String input = grid[x][y].getText();
               Square square;
                try {
                    int value = Integer.parseInt(input);
                    if (value > 0 && value <= dim) {
                        square = new Square(x, y, value);
                    }
                    else {
                        System.out.println("The value of square x = "+x+", y = "+y+ " should range from 1 to 9");
                        square = new Square(x, y);

                    }
                                    }
                catch(Exception e) {
                    if (input.equals("")) {
                        square = new Square(x, y);
                    }
                    else{
                        System.out.println("Square x = "+x+", y = "+y+ " should contain a numerical value.");
                        square = new Square(x, y);

                    }
                }
                squares[x][y] = square;
            }
        }
        this.sudokuGrid = new Grid(squares);
    }


    /** Function that clears the UI grid. **/
    private void clearGrid(){
        for(int x = 0; x < 9; x++) {
            for(int y = 0; y < 9; y++) {
                grid[x][y].setText("");
            }
        }
    }


    /** Function that displays the solution to the user in the UI. **/
    private void showSolution() {
        for (int x = 0; x < dim; x++) {
            for (int y = 0; y < dim; y++) {
                String txt = String.valueOf(sudokuGrid.getSquare(x,y).getValue());
                grid[x][y].setText(txt);
            }
        }
    }


    /** Sets action to be performed by buttons. **/
    @Override
    public void actionPerformed(ActionEvent event) {

        Object src = event.getSource();

        if (src == button) {
            this.generateGrid();

            boolean result = sudokuGrid.solve();

            if (!result) {
                message.setText("Sorry, this grid cannot be solved.");
                this.clearGrid();
            }
            else {
                message.setText("Here is your result.");
            }

            this.showSolution();
        }

        if (src == clearB) {
            this.clearGrid();
        }

    }


    /** Initializes UI and adds components to it. **/
    public void initUI(){
        setTitle("Sudoku Solver");

        setSize( 650, 650);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); //centering the frame

        JLabel label1 = new JLabel("Enter the numbers in the grid and click solve for results");
        label1.setBounds(50, 20, 400, 20);
        add(label1);

        button.setBounds(200, 500, 100, 40);
        add(button);
        button.addActionListener(this);

        clearB.setBounds(300, 500, 100, 40);
        add(clearB);
        clearB.addActionListener(this);

        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        Dimension fieldDimension = new Dimension(35, 35);

        /** Adds UI Sudoku grid. **/
        for (int y = 0; y < dim; y++) {
            for (int x = 0; x < dim; x++) {
                JTextField field = new JTextField();
                grid[y][x] = field;
                field.setBorder(border);
                field.setPreferredSize(fieldDimension);
                field.setBounds(xOffset + x*squareSize, yOffset + y*squareSize, squareSize, squareSize);
                add(field);
            }
        }

        message.setBounds(50, 400, 500, 40);
        add(message);

        JLabel label2 = new JLabel("");
        label2.setBounds(0, 0, 0, 0);
        add(label2);

        setVisible(true);
    }

    /** Runs application. **/
    public static void main(String[] args) {

        /** Creates a new SudokuSolver and initializes it. **/
        SudokuSolver frame = new SudokuSolver();
        frame.initUI();

    }

}
