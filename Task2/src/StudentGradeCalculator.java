import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class StudentGradeCalculator extends JFrame implements ActionListener {
    JLabel title, s1, s2, s3, s4, s5, total, avg, grade;
    JTextField t1, t2, t3, t4, t5;
    JButton calc, clear;
    StudentGradeCalculator() {
        setTitle("Student Grade Calculator");
        setSize(500, 550);
        setLayout(null);
        getContentPane().setBackground(new Color(240, 248, 255));
        title = new JLabel("STUDENT GRADE CALCULATOR");
        title.setBounds(70, 20, 400, 40);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setForeground(new Color(25, 25, 112));
        add(title);
        s1 = new JLabel("Subject 1 Marks:");
        s1.setBounds(60, 90, 150, 30);
        s1.setFont(new Font("Arial", Font.PLAIN, 16));
        add(s1);
        t1 = new JTextField();
        t1.setBounds(230, 90, 180, 30);
        add(t1);
        s2 = new JLabel("Subject 2 Marks:");
        s2.setBounds(60, 140, 150, 30);
        s2.setFont(new Font("Arial", Font.PLAIN, 16));
        add(s2);
        t2 = new JTextField();
        t2.setBounds(230, 140, 180, 30);
        add(t2);
        s3 = new JLabel("Subject 3 Marks:");
        s3.setBounds(60, 190, 150, 30);
        s3.setFont(new Font("Arial", Font.PLAIN, 16));
        add(s3);
        t3 = new JTextField();
        t3.setBounds(230, 190, 180, 30);
        add(t3);
        s4 = new JLabel("Subject 4 Marks:");
        s4.setBounds(60, 240, 150, 30);
        s4.setFont(new Font("Arial", Font.PLAIN, 16));
        add(s4);
        t4 = new JTextField();
        t4.setBounds(230, 240, 180, 30);
        add(t4);
        s5 = new JLabel("Subject 5 Marks:");
        s5.setBounds(60, 290, 150, 30);
        s5.setFont(new Font("Arial", Font.PLAIN, 16));
        add(s5);
        t5 = new JTextField();
        t5.setBounds(230, 290, 180, 30);
        add(t5);
        calc = new JButton("Calculate");
        calc.setBounds(90, 360, 130, 40);
        calc.setBackground(new Color(100, 149, 237));
        calc.setForeground(Color.WHITE);
        calc.setFont(new Font("Arial", Font.BOLD, 15));
        calc.addActionListener(this);
        add(calc);
        clear = new JButton("Clear");
        clear.setBounds(260, 360, 130, 40);
        clear.setBackground(new Color(220, 20, 60));
        clear.setForeground(Color.WHITE);
        clear.setFont(new Font("Arial", Font.BOLD, 15));
        clear.addActionListener(this);
        add(clear);
        total = new JLabel("Total Marks : ");
        total.setBounds(60, 430, 350, 30);
        total.setFont(new Font("Arial", Font.BOLD, 16));
        add(total);
        avg = new JLabel("Average Percentage : ");
        avg.setBounds(60, 460, 350, 30);
        avg.setFont(new Font("Arial", Font.BOLD, 16));
        add(avg);
        grade = new JLabel("Grade : ");
        grade.setBounds(60, 490, 350, 30);
        grade.setFont(new Font("Arial", Font.BOLD, 16));
        add(grade);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == calc) {
            int m1 = Integer.parseInt(t1.getText());
            int m2 = Integer.parseInt(t2.getText());
            int m3 = Integer.parseInt(t3.getText());
            int m4 = Integer.parseInt(t4.getText());
            int m5 = Integer.parseInt(t5.getText());
            int sum = m1 + m2 + m3 + m4 + m5;
            double average = sum / 5.0;
            String g;
            if (average >= 90) {
                g = "A+";
            } else if (average >= 80) {
                g = "A";
            } else if (average >= 70) {
                g = "B";
            } else if (average >= 60) {
                g = "C";
            } else if (average >= 50) {
                g = "D";
            } else {
                g = "Fail";
            }
            total.setText("Total Marks : " + sum + " / 500");
            avg.setText("Average Percentage : " + average + "%");
            grade.setText("Grade : " + g);
        }
        if (e.getSource() == clear) {
            t1.setText("");
            t2.setText("");
            t3.setText("");
            t4.setText("");
            t5.setText("");
            total.setText("Total Marks : ");
            avg.setText("Average Percentage : ");
            grade.setText("Grade : ");
        }
    }
    public static void main(String[] args) {
        new StudentGradeCalculator();
    }
}