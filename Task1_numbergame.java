import java.util.Random;
import java.util.Scanner;

class Main {
   void generateNumber() {
   
      System.out.println("---= NUMBER GAME =---");
      Random number = new Random();
      Scanner scan = new Scanner(System.in);
      
      boolean userStates;
      boolean states = true;
      int score = 0;
      String response;
      
      while(states != false) {
         for(int count = 0; count < 3; count++) {
            int num1 = 1 + number.nextInt(100); 
            
            System.out.println("Please choose a number from 1 to 100 : ");
            String input = scan.nextLine().trim();

            if (!isInteger(input)) {
               System.out.println("INVALID INPUT. Please enter an integer.");
               continue;
               }
            
            int user = Integer.parseInt(input);
            
            if (user < 1 || user > 100) {
               System.out.println("INVALID INPUT.");
               
            }
            else {
               
               if(num1 == user) {
                  System.out.println("CORRECT");
                  score++;
               }
               else if(num1 < user) { 
                  System.out.println("TOO HIGH, TRY AGAIN");
               }
               else {
                  System.out.println("TOO LOW, TRY AGAIN");
               }        
            }
                                    
         System.out.println(num1); 
         }
         
         System.out.println("Score :" + score);
         System.out.println("Do you wish to continue (Y/N) ? : ");
         response = scan.nextLine().trim().toLowerCase();
         
         if(response.equals("y")) {
            states = true;
         }
         else if(response.equals("n")) {
            states = false;
            System.out.println("GOOD BYE");
         }
         else {
            //System.out.println("Invalid input. Please enter Y or N.");
            //response = scan.nextLine();
            states = false;
         }
          
      }
   scan.close();         
   }
   
   
   static boolean isInteger(String str) {
      try{
         Integer.parseInt(str);
         return true;
 
      } catch (NumberFormatException e) {
         return false;
      }
   }
   
   public static void main(String[] arg){ 
    
      Main run = new Main();
      run.generateNumber();      
   }
}