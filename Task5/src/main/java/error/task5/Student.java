/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package error.task5;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author admin
 */
public class Student {
    private String nameTextPane;
    private String surnameTextPane;
    private int studentIDTextPane;
    private String gradeTextPane;
    private int ageTextPane;
    private String ganderTextPane;

    public Student(int studentIDTextPane, String nameTextPane, String surnameTextPane, String gradeTextPane, int ageTextPane, String ganderTextPane) {
        this.studentIDTextPane = studentIDTextPane;
        this.nameTextPane = nameTextPane;
        this.surnameTextPane = surnameTextPane;
        this.gradeTextPane = gradeTextPane;
        this.ageTextPane = ageTextPane;
        this.ganderTextPane = ganderTextPane;
    }

    Student() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getStudentIDTextPane() {
        return studentIDTextPane;
    }
    
    public String getNameTextPane() {
        return nameTextPane;
    }

    public String getSurnameTextPane() {
        return surnameTextPane;
    }

    public String getGradeTextPane() {
        return gradeTextPane;
    }

    public int getAgeTextPane() {
        return ageTextPane;
    }
    
    public String getGanderTextPane() {
        return ganderTextPane;
    }
    
    //SET
    public void setNameTextPane(String nameTextPane) {
        this.nameTextPane = nameTextPane;
    }

    public void setStudentIDTextPane(int studentIDTextPane) {
        this.studentIDTextPane = studentIDTextPane;
    }

    public void setSurnameTextPane(String surnameTextPane) {
        this.surnameTextPane = surnameTextPane;
    }

    public void setGradeTextPane(String gradeTextPane) {
        this.gradeTextPane = gradeTextPane;
    }

    public void setAgeTextPane(int ageTextPane) {
        this.ageTextPane = ageTextPane;
    }
    
    public void setGanderTextPane(String ganderTextPane) {
        this.ganderTextPane = ganderTextPane;
    }

    @Override
    public String toString() {
        return "Student ID"+ studentIDTextPane +",Name: " + nameTextPane + ", Surname: " + surnameTextPane + ", Grade: " + gradeTextPane + ", Age: " + ageTextPane + ", Gender: " + ganderTextPane;
    }
    
    public void storeDataInTextFile() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("student_data.txt", true))) {
            writer.write("Student ID: " + studentIDTextPane);
            writer.newLine();
            writer.write("Name: " + nameTextPane);
            writer.newLine();
            writer.write("Surname: " + surnameTextPane);
            writer.newLine();
            writer.write("Grade: " + gradeTextPane);
            writer.newLine();
            writer.write("Age: " + ageTextPane);
            writer.newLine();
            writer.write("Gender: " + ganderTextPane);
            writer.newLine();
            writer.write("Date: " + formattedDate);
            writer.newLine();
            writer.newLine(); 
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
    
}