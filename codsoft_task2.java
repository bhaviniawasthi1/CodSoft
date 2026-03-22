//TASK 2- STUDENT GRADE CALCULATOR 

import java.util.Scanner;

public class codsoft_task2 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of subjects: ");
        int subjectCount = scanner.nextInt();

        // Validate input
        if (subjectCount <= 0) {
            System.out.println("Invalid number of subjects.");
            return;
        }

        int totalMarks = 0;

        // Taking marks input for each subject
        for (int i = 1; i <= subjectCount; i++) {

            System.out.print("Enter marks for subject " + i + " (out of 100): ");
            int marks = scanner.nextInt();

            // Basic validation for marks range
            if (marks < 0 || marks > 100) {
                System.out.println("Invalid marks entered. Please restart the program.");
                return;
            }

            totalMarks += marks;
        }

        // Calculate average percentage
        double averagePercentage = (double) totalMarks / subjectCount;

        // Determine grade based on average
        char grade;

        if (averagePercentage >= 90) {
            grade = 'A';
        } else if (averagePercentage >= 75) {
            grade = 'B';
        } else if (averagePercentage >= 60) {
            grade = 'C';
        } else if (averagePercentage >= 50) {
            grade = 'D';
        } else {
            grade = 'F';
        }

        // Display results
        System.out.println("\n----- Result -----");
        System.out.println("Total Marks: " + totalMarks + " / " + (subjectCount * 100));
        System.out.printf("Average Percentage: %.2f%%\n", averagePercentage);
        System.out.println("Grade: " + grade);

        scanner.close();
    }
}