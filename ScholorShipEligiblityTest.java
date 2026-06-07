import java.util.Scanner;

class ScholorShipEligiblityTest {

    static Scanner sc = new Scanner(System.in);

    static void UserInput() {
        System.out.println("===== SCHOLARSHIP ELIGIBILITY TEST =====");

        System.out.print("Enter SSC Hall Ticket Number: ");
        long hallTicket = sc.nextLong();

        studentDetails(hallTicket);
    }

    static void studentDetails(long hallTicket) {

        System.out.print("Enter Student Name: ");
        String name = sc.next();

        System.out.print("Enter Father Name: ");
        String fatherName = sc.next();

        System.out.print("Enter District: ");
        String district = sc.next();

        marks(hallTicket, name, fatherName, district);
    }

    static void marks(long hallTicket, String name,
                      String fatherName, String district) {

        System.out.print("Enter First Language Marks: ");
        int firstLanguage = sc.nextInt();

        System.out.print("Enter Second Language Marks: ");
        int secondLanguage = sc.nextInt();

        System.out.print("Enter Third Language Marks: ");
        int thirdLanguage = sc.nextInt();

        System.out.print("Enter Mathematics Marks: ");
        int mathematics = sc.nextInt();

        System.out.print("Enter Science Marks: ");
        int science = sc.nextInt();

        System.out.print("Enter Social Studies Marks: ");
        int socialStudies = sc.nextInt();

        int total = calculateTotal(
                firstLanguage,
                secondLanguage,
                thirdLanguage,
                mathematics,
                science,
                socialStudies);

        double percentage = calculatePercentage(total);

        String grade = calculateGrade(percentage);

        displayResult(
                hallTicket,
                name,
                fatherName,
                district,
                total,
                percentage,
                grade,
                mathematics,
                science);
    }

    static int calculateTotal(int fl, int sl, int tl,
                              int maths, int science, int social) {

        return fl + sl + tl + maths + science + social;
    }

    static double calculatePercentage(int total) {
        return (total / 600.0) * 100;
    }

    static String calculateGrade(double percentage) {

        if (percentage >= 90) {
            return "A+";
        } else if (percentage >= 80) {
            return "A";
        } else if (percentage >= 70) {
            return "B";
        } else if (percentage >= 60) {
            return "C";
        } else {
            return "D";
        }
    }

    static void displayResult(long hallTicket,
                              String name,
                              String fatherName,
                              String district,
                              int total,
                              double percentage,
                              String grade,
                              int mathematics,
                              int science) {

        System.out.println("\n========== STUDENT REPORT ==========");

        System.out.println("Hall Ticket : " + hallTicket);
        System.out.println("Student Name: " + name);
        System.out.println("Father Name : " + fatherName);
        System.out.println("District    : " + district);
        System.out.println("Total Marks : " + total);
        System.out.printf("Percentage  : %.2f%%\n", percentage);
        System.out.println("Grade       : " + grade);

        System.out.println("\n===== ELIGIBILITY RESULT =====");

        if (total >= 300 && mathematics >= 70 && science >= 60) {

            System.out.println("Congratulations " + name + "!");
            System.out.println("You are ELIGIBLE for the Scholarship Test.");

            if (percentage >= 90) {
                System.out.println("Scholarship Amount: Rs.50,000");
            } else if (percentage >= 80) {
                System.out.println("Scholarship Amount: Rs.30,000");
            } else {
                System.out.println("Scholarship Amount: Rs.15,000");
            }

        } else {
            System.out.println("Sorry, you are NOT ELIGIBLE for the Scholarship Test.");
        }
    }

    public static void main(String[] args) {
        UserInput();
    }
}