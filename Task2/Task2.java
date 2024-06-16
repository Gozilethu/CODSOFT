import java.util.Scanner;

class Main {

   public void gradeCalculator() {
   
      Scanner scan = new Scanner(System.in);

      // Input
      System.out.print("Enter the number of subjects: ");
      int numSubjects = scan.nextInt();
      int[] marks = new int[numSubjects];
  
      for (int i = 0; i < numSubjects; i++) {
         System.out.print("Enter marks for subject " + (i + 1) + " (out of 100): ");
         marks[i] = scan.nextInt();
      }

      //Total Marks
      int totalMarks = 0;
      for (int mark : marks) {
         totalMarks += mark;
      }

      //Average
      double avg = (double) totalMarks / numSubjects;

      // Grade
      char grade;
      if (avg >= 90) {
         grade = 'A';
      } else if (avg >= 80) {
         grade = 'B';
      } else if (avg >= 70) {
         grade = 'C';
      } else if (avg >= 60) {
         grade = 'D';
      } else {
         grade = 'F';
      }

      System.out.println("Total Marks: " + totalMarks);
      System.out.println("Average Percentage: " + avg + "%");
      System.out.println("Grade: " + grade);

      scan.close();
   } 

   public static void main(String[] args) {
         Main run = new Main();
         run.gradeCalculator();
    }
}
