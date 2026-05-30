import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
class Student implements Serializable {
    private String name;
    private String rollNo;
    private String grade;
    private String course;
    private String phone;
    public Student(String name, String rollNo, String grade, String course, String phone) {
        this.name = name;
        this.rollNo = rollNo;
        this.grade = grade;
        this.course = course;
        this.phone = phone;
    }
    public String getName() {
        return name;
    }
    public String getRollNo() {
        return rollNo;
    }
    public String getGrade() {
        return grade;
    }
    public String getCourse() {
        return course;
    }
    public String getPhone() {
        return phone;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }
    public void setCourse(String course) {
        this.course = course;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
public class StudentManagementSystem extends JFrame {
    private JTextField nameField;
    private JTextField rollField;
    private JTextField gradeField;
    private JTextField courseField;
    private JTextField phoneField;
    private JTextField searchField;
    private JTable studentTable;
    private DefaultTableModel tableModel;
    private ArrayList<Student> students;
    private final String FILE_NAME = "students.dat";
    public StudentManagementSystem() {
        students = new ArrayList<>();
        setTitle("Student Management System");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        // Load saved data
        loadStudents();
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(15, 23, 42));
        topPanel.setPreferredSize(new Dimension(1000, 80));
        JLabel title = new JLabel("STUDENT MANAGEMENT SYSTEM");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        topPanel.add(title);
        add(topPanel, BorderLayout.NORTH);
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Student Details"));
        formPanel.setBackground(Color.WHITE);
        nameField = new JTextField();
        rollField = new JTextField();
        gradeField = new JTextField();
        courseField = new JTextField();
        phoneField = new JTextField();
        searchField = new JTextField();
        formPanel.add(new JLabel("Student Name"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Roll Number"));
        formPanel.add(rollField);
        formPanel.add(new JLabel("Grade"));
        formPanel.add(gradeField);
        formPanel.add(new JLabel("Course"));
        formPanel.add(courseField);
        formPanel.add(new JLabel("Phone Number"));
        formPanel.add(phoneField);
        formPanel.add(new JLabel("Search Roll No"));
        formPanel.add(searchField);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        JButton addButton = createButton("Add Student", new Color(34, 197, 94));
        JButton updateButton = createButton("Update Student", new Color(59, 130, 246));
        JButton deleteButton = createButton("Delete Student", new Color(239, 68, 68));
        JButton searchButton = createButton("Search", new Color(245, 158, 11));
        JButton clearButton = createButton("Clear", new Color(107, 114, 128));
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(clearButton);
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Name");
        tableModel.addColumn("Roll No");
        tableModel.addColumn("Grade");
        tableModel.addColumn("Course");
        tableModel.addColumn("Phone");
        studentTable = new JTable(tableModel);
        studentTable.setRowHeight(30);
        studentTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(studentTable);
        refreshTable();
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(formPanel, BorderLayout.NORTH);
        centerPanel.add(buttonPanel, BorderLayout.CENTER);
        centerPanel.add(scrollPane, BorderLayout.SOUTH);
        add(centerPanel, BorderLayout.CENTER);
        addButton.addActionListener(e -> addStudent());
        updateButton.addActionListener(e -> updateStudent());
        deleteButton.addActionListener(e -> deleteStudent());
        searchButton.addActionListener(e -> searchStudent());
        clearButton.addActionListener(e -> clearFields());
        studentTable.getSelectionModel().addListSelectionListener(e -> {
            int row = studentTable.getSelectedRow();
            if (row >= 0) {
                nameField.setText(tableModel.getValueAt(row, 0).toString());
                rollField.setText(tableModel.getValueAt(row, 1).toString());
                gradeField.setText(tableModel.getValueAt(row, 2).toString());
                courseField.setText(tableModel.getValueAt(row, 3).toString());
                phoneField.setText(tableModel.getValueAt(row, 4).toString());
            }
        });
        setVisible(true);
    }
    private JButton createButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        return button;
    }
    private void addStudent() {
        String name = nameField.getText().trim();
        String roll = rollField.getText().trim();
        String grade = gradeField.getText().trim();
        String course = courseField.getText().trim();
        String phone = phoneField.getText().trim();
        // Validation
        if (name.isEmpty() || roll.isEmpty() || grade.isEmpty()
                || course.isEmpty() || phone.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "All fields are required!");
            return;
        }
        // Phone Validation
        if (!phone.matches("\\d{10}")) {
            JOptionPane.showMessageDialog(this,
                    "Phone number must contain 10 digits!");
            return;
        }
        Student student = new Student(name, roll, grade, course, phone);
        students.add(student);
        saveStudents();
        refreshTable();
        clearFields();
        JOptionPane.showMessageDialog(this,
                "Student Added Successfully!");
    }
    private void updateStudent() {
        int row = studentTable.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this,
                    "Please select a student!");
            return;
        }
        Student student = students.get(row);
        student.setName(nameField.getText());
        student.setGrade(gradeField.getText());
        student.setCourse(courseField.getText());
        student.setPhone(phoneField.getText());
        saveStudents();
        refreshTable();
        JOptionPane.showMessageDialog(this,
                "Student Updated Successfully!");
    }
    private void deleteStudent() {
        int row = studentTable.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this,
                    "Please select a student!");
            return;
        }
        students.remove(row);
        saveStudents();
        refreshTable();
        clearFields();
        JOptionPane.showMessageDialog(this,
                "Student Deleted Successfully!");
    }
    private void searchStudent() {
        String roll = searchField.getText().trim();
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getRollNo().equals(roll)) {
                studentTable.setRowSelectionInterval(i, i);
                JOptionPane.showMessageDialog(this,
                        "Student Found!");
                return;
            }
        }
        JOptionPane.showMessageDialog(this,
                "Student Not Found!");
    }
    private void refreshTable() {
        tableModel.setRowCount(0);
        for (Student student : students) {
            tableModel.addRow(new Object[]{
                    student.getName(),
                    student.getRollNo(),
                    student.getGrade(),
                    student.getCourse(),
                    student.getPhone()
            });
        }
    }
    private void clearFields() {
        nameField.setText("");
        rollField.setText("");
        gradeField.setText("");
        courseField.setText("");
        phoneField.setText("");
        searchField.setText("");
    }
    private void saveStudents() {
        try {
            ObjectOutputStream output =
                    new ObjectOutputStream(
                            new FileOutputStream(FILE_NAME));
            output.writeObject(students);
            output.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error Saving Data!");
        }
    }
    private void loadStudents() {
        try {
            ObjectInputStream input =
                    new ObjectInputStream(
                            new FileInputStream(FILE_NAME));
            students = (ArrayList<Student>) input.readObject();
            input.close();
        } catch (Exception e) {
            students = new ArrayList<>();
        }
    }
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() ->
                new StudentManagementSystem());
    }
}