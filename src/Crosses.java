import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class Crosses extends JPanel {
    static JFrame frame;
    static List<JButton> fieldButtons;
    static JLabel infoLabel;

    static String[] gameField; // Значение клеток на поле
    static String turn; // Текущий ход
    static String winner; // Строка с победителем

    public static void main(String[] args) {
        frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Crosses crosses = new Crosses();
        frame.setContentPane(crosses);
        frame.setVisible(true);

        fieldButtons = new ArrayList<JButton>();
        infoLabel = new JLabel("Хеллоу май френд");

        gameField = new String[9];
        turn = "X";
        winner = null;
    }

    static void emptyField() {
        frame.setLayout(null);
        infoLabel.setBounds(10, 160, 400,400);
        frame.add(infoLabel);
        int xInc = 0;
        int yInc = -100;

        for (int i = 0; i < 9; i++) {
            gameField[i] = "empty";
        }

        for (int y = 0; y < 3;  y++) {
            fieldButtons.add(new JButton(""));

            fieldButtons.get(cellValue).addActionListener(
                    new ActionListener() {
                        int value = cellValue;
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (winner == null && gameField[value] == "empty") {
                                gameField[value] = turn;

                                fieldButtons.get(value).setText(turn);

                                if (turn.equals("X")) {
                                    turn = "0";
                                } else {
                                    turn = "X";
                                }
                                winOrNot();
                            }
                            else if (winner != null) {
                                infoLabel.setText("Игра закончена");
                            }

                        }
                    }
            );
        }

    }
}
