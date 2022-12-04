import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Crosses extends JPanel {
    static JFrame frame;
    static List<JButton> fieldButtons;
    static JLabel infoLabel;

    static String[] gameField; // Значение клеток на поле
    static String turn; // Текущий ход
    static String winner; // Строка с победителем
    static int cellValue;

    public static void main(String[] args) {
        frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Crosses crosses = new Crosses();
        frame.setContentPane(crosses);
        frame.setVisible(true);

        fieldButtons = new ArrayList<>();
        infoLabel = new JLabel("Хеллоу май френд");

        gameField = new String[9];
        turn = "X";
        winner = null;
        cellValue = 0;

        emptyField();
    }

    static void emptyField() {
        frame.setLayout(null);
        infoLabel.setBounds(10, 160, 400,400);
        frame.add(infoLabel);
        int xInc;
        int yInc = -100;

        for (int i = 0; i < 9; i++) {
            gameField[i] = "empty";
        }

        for (int y = 0; y < 3;  y++) {
            xInc = 0;
            yInc = yInc + 100;

            for (int x = 0; x < 3; x++) {
                fieldButtons.add(new JButton(""));

                fieldButtons.get(cellValue).setBounds(xInc, yInc, 100, 100);
                frame.add(fieldButtons.get(cellValue));

                fieldButtons.get(cellValue).addActionListener(
                    new ActionListener() {
                        final int value = cellValue;
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (winner == null && gameField[value].equals("empty")) {
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
                            } else {
                                infoLabel.setText("Эта клетка занята");
                            }
                        }
                    });
                cellValue++;
                xInc = xInc + 100;
            }
            frame.setSize(316, 450);
            frame.setResizable(false);
        }
    }

    static void winOrNot() {
        winner = checkWinner();
        if (winner != null) {
            if (winner.equalsIgnoreCase("draw")) {
                infoLabel.setText("Ничья");
            } else {
                infoLabel.setText("Игрок " + winner + " победил! Спасибо за игру");
            }
        }
    }

    private static String checkWinner() {
        for (int i = 0; i < 8; i++) {
            String line = switch (i) {
                case 0 -> gameField[0] + gameField[1] + gameField[2];
                case 1 -> gameField[3] + gameField[4] + gameField[5];
                case 2 -> gameField[6] + gameField[7] + gameField[8];
                case 3 -> gameField[0] + gameField[3] + gameField[6];
                case 4 -> gameField[1] + gameField[4] + gameField[7];
                case 5 -> gameField[2] + gameField[5] + gameField[8];
                case 6 -> gameField[0] + gameField[4] + gameField[8];
                case 7 -> gameField[2] + gameField[4] + gameField[6];
                default -> null;
            };
            if (line.equals("XXX")) {
                return "X";
            } else if (line.equals("000")) {
                return "0";
            }
        }
        for (int i = 0; i < 9; i++) {
            if (Arrays.asList(gameField).contains("empty")) {
                break;
            } else if (i == 8) return "draw";
        }
        infoLabel.setText("Xoд игрока " + turn);
        return null;
    }
}
