// CalendarGenerator.java - Main class with CLI interface
import java.util.Scanner;

public class CalendarGenerator {
    public static void main(String[] args) {
        CalendarService calendarService = new CalendarService();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Basic Calendar Generator (with DSA)");
        System.out.println("-----------------------------------");
        
        while (true) {
            System.out.println("\nOptions:");
            System.out.println("1. Show month calendar");
            System.out.println("2. Add event");
            System.out.println("3. Check holiday");
            System.out.println("4. Check leap year");
            System.out.println("5. Undo last operation");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    System.out.print("Enter month (1-12): ");
                    int month = scanner.nextInt();
                    System.out.print("Enter year: ");
                    int year = scanner.nextInt();
                    System.out.println("\n" + calendarService.getMonthCalendar(month, year));
                    break;
                    
                case 2:
                    System.out.print("Enter date (DD-MM-YYYY): ");
                    String date = scanner.nextLine();
                    System.out.print("Enter event name: ");
                    String eventName = scanner.nextLine();
                    calendarService.addEvent(date, eventName);
                    System.out.println("Event added successfully");
                    break;
                    
                case 3:
                    System.out.print("Enter month-day (MM-DD): ");
                    String monthDay = scanner.nextLine();
                    System.out.println("Holiday: " + calendarService.getHoliday(monthDay));
                    break;
                    
                case 4:
                    System.out.print("Enter year: ");
                    int checkYear = scanner.nextInt();
                    System.out.println(checkYear + " is " + 
                            (calendarService.isLeapYear(checkYear) ? "" : "not ") + "a leap year");
                    break;
                    
                case 5:
                    calendarService.undo();
                    System.out.println("Undid last operation");
                    break;
                    
                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                    
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
