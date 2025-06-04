// Error state from a method that returns WinningItem.
public class ErrorItem implements WinningItem {
    private String errorMessage;

    public ErrorItem(String message) {
        this.errorMessage = message;
    }

    public String toString() {
        return "ErrorItem: " + errorMessage;
    }
    
}
