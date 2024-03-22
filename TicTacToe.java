import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe extends JFrame implements ActionListener {
    private static final int SIZE = 3;
    private static final int WINNING_LENGTH = 3;

    private JButton[][] buttons;
    private boolean playerX;
    private int moves;

    public TicTacToe() {
        buttons = new JButton[SIZE][SIZE];
        playerX = true;
        moves = 0;

        setTitle("Tic-Tac-Toe");
        setSize(300, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(SIZE, SIZE));

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                buttons[row][col] = new JButton("");
                buttons[row][col].setFont(new Font("Arial", Font.PLAIN, 48));
                buttons[row][col].setFocusPainted(false);
                buttons[row][col].addActionListener(this);
                add(buttons[row][col]);
            }
        }

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TicTacToe();
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton buttonClicked = (JButton) e.getSource();

        if (buttonClicked.getText().equals("") && playerX) {
            buttonClicked.setText("X");
            playerX = false;
            moves++;
        } else if (buttonClicked.getText().equals("") && !playerX) {
            buttonClicked.setText("O");
            playerX = true;
            moves++;
        }

        if (checkWin()) {
            if (!playerX) {
                JOptionPane.showMessageDialog(this, "X wins!");
            } else {
                JOptionPane.showMessageDialog(this, "O wins!");
            }
            resetBoard();
        } else if (moves == SIZE * SIZE) {
            JOptionPane.showMessageDialog(this, "It's a draw!");
            resetBoard();
        }
    }

    private boolean checkWin() {
        return checkRows() || checkColumns() || checkDiagonals();
    }

    private boolean checkRows() {
        for (int row = 0; row < SIZE; row++) {
            if (buttons[row][0].getText().equals(buttons[row][1].getText())
                    && buttons[row][0].getText().equals(buttons[row][2].getText())
                    && !buttons[row][0].getText().equals("")) {
                return true;
            }
        }
        return false;
    }

    private boolean checkColumns() {
        for (int col = 0; col < SIZE; col++) {
            if (buttons[0][col].getText().equals(buttons[1][col].getText())
                    && buttons[0][col].getText().equals(buttons[2][col].getText())
                    && !buttons[0][col].getText().equals("")) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonals() {
        return (buttons[0][0].getText().equals(buttons[1][1].getText())
                && buttons[0][0].getText().equals(buttons[2][2].getText())
                && !buttons[0][0].getText().equals(""))
                || (buttons[0][2].getText().equals(buttons[1][1].getText())
                && buttons[0][2].getText().equals(buttons[2][0].getText())
                && !buttons[0][2].getText().equals(""));
    }

    private void resetBoard() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                buttons[row][col].setText("");
            }
        }
        playerX = true;
        moves = 0;
    }
}
