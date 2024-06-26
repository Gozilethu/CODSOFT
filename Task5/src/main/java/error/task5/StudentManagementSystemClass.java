/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package error.task5;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextPane;

/**
 *
 * @author admin
 */
public class StudentManagementSystemClass {
    private List<Student> students;
    private DefaultTableModel tableModel;
    private JTable table;
    
    Student stu = new Student();
    
    
    LocalDate currentDate = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String formattedDate = currentDate.format(formatter);

    public StudentManagementSystemClass(JTable table) {
        students = new ArrayList<>();
        this.table = table;
        tableModel = (DefaultTableModel) table.getModel();
    }

    private static final String STUDENT_FILE = "students.txt";

    public void addStudent(int studentIDTextPane, String nameTextPane, String surnameTextPane, String gradeTextPane, int ageTextPane, String ganderTextPane) {
        Student newStudent = new Student(studentIDTextPane, nameTextPane, surnameTextPane, gradeTextPane, ageTextPane, ganderTextPane);
        students.add(newStudent);
        addRowToTable(newStudent);
        newStudent.storeDataInTextFile();
        JOptionPane.showMessageDialog(null, "Student added successfully: " + newStudent, "Success", JOptionPane.INFORMATION_MESSAGE);
        
        //stu.storeDataInTextFile();
    }

    public void removeStudent(int studentIDTextPane) {
        Student studentToRemove = null;
        for (Student student : students) {
            if (student.getStudentIDTextPane() == studentIDTextPane) {
                studentToRemove = student;
                break;
            }
        }

        if (studentToRemove != null) {
            students.remove(studentToRemove);
            removeRowFromTable(studentIDTextPane);
            removeFromFile(studentIDTextPane);
            JOptionPane.showMessageDialog(null, "Student removed successfully: " + studentToRemove, "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Student not found: " + studentIDTextPane, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updateStudent(int studentIDTextPane, String nameTextPane, String surnameTextPane, String gradeTextPane, int ageTextPane, String ganderTextPane) {
        Student studentToUpdate = searchStudent(studentIDTextPane);
        if (studentToUpdate != null) {
            studentToUpdate.setNameTextPane(nameTextPane);
            studentToUpdate.setSurnameTextPane(surnameTextPane);
            studentToUpdate.setGradeTextPane(gradeTextPane);
            studentToUpdate.setAgeTextPane(ageTextPane);
            studentToUpdate.setGanderTextPane(ganderTextPane);
            updateRowInTable(studentIDTextPane, nameTextPane, surnameTextPane, gradeTextPane, ageTextPane, ganderTextPane);
            updateInFile(studentIDTextPane, nameTextPane, surnameTextPane, gradeTextPane, ageTextPane, ganderTextPane);
            JOptionPane.showMessageDialog(null, "Student updated successfully: " + studentToUpdate, "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Student not found: " + studentIDTextPane, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Student searchStudent(int studentIDTextPane) {
        for (Student student : students) {
            if (student.getStudentIDTextPane() == studentIDTextPane) {
                return student;
            }
        }
        return null;
    }

    public void displayStudentsInTable() {
        tableModel.setRowCount(0);
        for (Student student : students) {
            Object[] rowData = {
                student.getStudentIDTextPane(),
                student.getNameTextPane(),
                student.getSurnameTextPane(),
                student.getGradeTextPane(),
                student.getAgeTextPane(),
                student.getGanderTextPane()
            };
            tableModel.addRow(rowData);
        }
    }

    private void addRowToTable(Student student) {
        Object[] rowData = {
            student.getStudentIDTextPane(),
            student.getNameTextPane(),
            student.getSurnameTextPane(),
            student.getGradeTextPane(),
            student.getAgeTextPane(),
            student.getGanderTextPane()
        };
        tableModel.addRow(rowData);
    }

    private void removeRowFromTable(int studentIDTextPane) {
        int rowCount = tableModel.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            int rollNumber = (int) tableModel.getValueAt(i, 0);
            if (rollNumber == studentIDTextPane) {
                tableModel.removeRow(i);
                break;
            }
        }
    }

    private void updateRowInTable(int studentIDTextPane, String nameTextPane, String surnameTextPane, String gradeTextPane, int ageTextPane, String ganderTextPane) {
        int rowCount = tableModel.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            int rollNumber = (int) tableModel.getValueAt(i, 0);
            if (rollNumber == studentIDTextPane) {
                tableModel.setValueAt(nameTextPane, i, 1);
                tableModel.setValueAt(surnameTextPane, i, 2);
                tableModel.setValueAt(gradeTextPane, i, 3);
                tableModel.setValueAt(ageTextPane, i, 4);
                tableModel.setValueAt(ganderTextPane, i, 5);
                break;
            }
        }
        updateInFile(studentIDTextPane, nameTextPane, surnameTextPane, gradeTextPane, ageTextPane, ganderTextPane);
    }

    private void removeFromFile(int studentIDTextPane) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(STUDENT_FILE));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.contains("Student ID: " + studentIDTextPane)) {
                    sb.append(line).append("\n");
                }
            }
            reader.close();

            BufferedWriter writer = new BufferedWriter(new FileWriter(STUDENT_FILE));
            writer.write(sb.toString());
            writer.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading/writing file:  "+ JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateInFile(int studentIDTextPane, String nameTextPane, String surnameTextPane, String gradeTextPane, int ageTextPane, String ganderTextPane) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(STUDENT_FILE));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("Student ID: " + studentIDTextPane)) {
                    sb.append("Student ID: ").append(studentIDTextPane).append("\n");
                    sb.append("Name: ").append(nameTextPane).append("\n");
                    sb.append("Surname: ").append(surnameTextPane).append("\n");
                    sb.append("Grade: ").append(gradeTextPane).append("\n");
                    sb.append("Age: ").append(ageTextPane).append("\n");
                    sb.append("Gender: ").append(ganderTextPane).append("\n");
                    LocalDate currentDate = LocalDate.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    String formattedDate = currentDate.format(formatter);
                    sb.append("Date: ").append(formattedDate).append("\n\n");
                } else {
                    sb.append(line).append("\n");
                }
            }
            reader.close();

            BufferedWriter writer = new BufferedWriter(new FileWriter(STUDENT_FILE));
            writer.write(sb.toString());
            writer.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading/writing file:  "+ JOptionPane.ERROR_MESSAGE);
        }
    }

    public void clearInputFields(JTextField studentIDField, JTextField nameField, JTextField surnameField, JTextField gradeField, JTextField ageField, JComboBox<String> genderComboBox) {
        studentIDField.setText("");
        nameField.setText("");
        surnameField.setText("");
        gradeField.setText("");
        ageField.setText("");
        genderComboBox.setSelectedIndex(0);
    }

    void clearInputFields(JTextPane studentIDTextPane, JTextPane nameTextPane, JTextPane surnameTextPane, JTextPane gradeTextPane, JTextPane ageTextPane, JTextPane ganderTextPane) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}