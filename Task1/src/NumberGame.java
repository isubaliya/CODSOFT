import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
public class NumberGame extends JFrame implements ActionListener {
    JLabel msg, scoreLabel, attemptLabel;
    JTextField tf;
    JButton check, newGame, exit;
    Random r = new Random();
    int number = r.nextInt(100) + 1;
    int attempts = 5;
    int score = 0;
    NumberGame() {
        setTitle("Number Game");
        setSize(400,300);
        setLayout(null);
        getContentPane().setBackground(new Color(245,245,255));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        msg = new JLabel("Guess Number 1 - 100");
        msg.setBounds(110,30,200,30);
        msg.setForeground(new Color(80,80,120));
        add(msg);
        tf = new JTextField();
        tf.setBounds(130,70,120,30);
        tf.setBackground(Color.WHITE);
        add(tf);
        check = new JButton("Check");
        check.setBounds(40,130,90,30);
        check.setBackground(new Color(180,255,180));
        check.setForeground(Color.BLACK);
        check.addActionListener(this);
        add(check);
        newGame = new JButton("New");
        newGame.setBounds(145,130,90,30);
        newGame.setBackground(new Color(255,230,180));
        newGame.setForeground(Color.BLACK);
        newGame.addActionListener(this);
        add(newGame);
        exit = new JButton("Exit");
        exit.setBounds(250,130,90,30);
        exit.setBackground(new Color(255,180,180));
        exit.setForeground(Color.BLACK);
        exit.addActionListener(this);
        add(exit);
        attemptLabel = new JLabel("Attempts : 5");
        attemptLabel.setBounds(50,200,120,30);
        attemptLabel.setForeground(new Color(70,130,180));
        add(attemptLabel);
        scoreLabel = new JLabel("Score : 0");
        scoreLabel.setBounds(230,200,120,30);
        scoreLabel.setForeground(new Color(150,100,200));
        add(scoreLabel);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==check) {
            int guess = Integer.parseInt(tf.getText());
            attempts--;
            if(guess==number) {
                score += attempts * 10;
                JOptionPane.showMessageDialog(this,"Correct Guess!");
                scoreLabel.setText("Score : " + score);
                number = r.nextInt(100) + 1;
                attempts = 5;
            }
            else if(guess>number) {
                msg.setText("Too High!");
            }
            else {
                msg.setText("Too Low!");
            }
            if(attempts==0 && guess!=number) {
                JOptionPane.showMessageDialog(this,
                "Game Over\nNumber was : " + number);
                number = r.nextInt(100) + 1;
                attempts = 5;
            }
            attemptLabel.setText("Attempts : " + attempts);
            tf.setText("");
        }
        if(e.getSource()==newGame) {
            number = r.nextInt(100) + 1;
            attempts = 5;
            msg.setText("Guess Number 1 - 100");
            attemptLabel.setText("Attempts : 5");
            tf.setText("");
        }
        if(e.getSource()==exit) {
            System.exit(0);
        }
    }
    public static void main(String[] args) {
        new NumberGame();
    }
}