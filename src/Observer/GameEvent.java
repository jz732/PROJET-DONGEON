package Observer;

public class GameEvent {
    private final GameEventType type;
    private final Object source;
    private final String message;
    private final int previousValue;
    private final int currentValue;

    public GameEvent(GameEventType type, Object source, String message) {
        this(type, source, message, -1, -1);
    }

    public GameEvent(GameEventType type, Object source, String message, int previousValue, int currentValue) {
        this.type = type;
        this.source = source;
        this.message = message;
        this.previousValue = previousValue;
        this.currentValue = currentValue;
    }

    public GameEventType getType() {
        return type;
    }

    public Object getSource() {
        return source;
    }

    public String getMessage() {
        return message;
    }

    public int getPreviousValue() {
        return previousValue;
    }

    public int getCurrentValue() {
        return currentValue;
    }
}
