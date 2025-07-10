import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

// CalendarService.java - Core calendar service with DSA implementations
public class CalendarService {
    private Map<String, String> holidays;
    private Map<String, String> events;
    private Stack<CalendarOperation> undoStack;
    
    public CalendarService() {
        holidays = new HashMap<>();
        events = new HashMap<>();
        undoStack = new Stack<>();
        
        // Initialize some default holidays
        holidays.put("01-01", "New Year");
        holidays.put("02-14", "Valentine's Day");
        holidays.put("07-04", "Independence Day");
        holidays.put("12-25", "Christmas");
    }
    
    public boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }
    
    public int getDaysInMonth(int month, int year) {
        if (month < 1 || month > 12) return 0;
        
        // Array representing days in each month (DSA concept)
        int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        
        // Adjust for leap year
        if (month == 2 && isLeapYear(year)) {
            return 29;
        }
        
        return daysInMonth[month - 1];
    }
    
    public void addEvent(String date, String eventName) {
        events.put(date, eventName);
        undoStack.push(new CalendarOperation("add_event", date, null));
    }
    
    public String getMonthCalendar(int month, int year) {
        StringBuilder calendar = new StringBuilder();
        
        String[] monthNames = {"January", "February", "March", "April", "May", "June", 
                              "July", "August", "September", "October", "November", "December"};
        
        calendar.append(monthNames[month - 1]).append(" ").append(year).append("\n");
        calendar.append("Sun Mon Tue Wed Thu Fri Sat\n");
        
        int daysInMonth = getDaysInMonth(month, year);
        LocalDate firstDay = LocalDate.of(year, month, 1);
        int firstDayOfWeek = firstDay.getDayOfWeek().getValue() % 7; // Sunday=0
        
        // Add leading spaces
        for (int i = 0; i < firstDayOfWeek; i++) {
            calendar.append("    ");
        }
        
        // Print days of month
        for (int day = 1; day <= daysInMonth; day++) {
            String dayStr = String.format("%3d", day);
            
            String dateKey = String.format("%02d-%02d", month, day);
            if (holidays.containsKey(dateKey)) {
                dayStr = "*" + dayStr.substring(1);
            }
            
            calendar.append(dayStr).append(" ");
            
            if ((day + firstDayOfWeek) % 7 == 0 || day == daysInMonth) {
                calendar.append("\n");
            }
        }
        
        return calendar.toString();
    }
    
    public String getEvent(String date) {
        return events.getOrDefault(date, "No event scheduled");
    }
    
    public String getHoliday(String monthDay) {
        return holidays.getOrDefault(monthDay, "No holiday");
    }
    
    public void undo() {
        if (!undoStack.isEmpty()) {
            CalendarOperation op = undoStack.pop();
            switch (op.getOperation()) {
                case "add_event":
                    events.remove(op.getKey());
                    break;
                // Additional operations can be handled here
            }
        }
    }
    
    // Helper class for undo operations (DSA concept)
    private static class CalendarOperation {
        private String operation;
        private String key;
        private String value;
        
        public CalendarOperation(String operation, String key, String value) {
            this.operation = operation;
            this.key = key;
            this.value = value;
        }
        
        public String getOperation() {
            return operation;
        }
        
        public String getKey() {
            return key;
        }
        
        public String getValue() {
            return value;
        }
    }
}
