package javanexuspots.services;

public interface LoggingService<T> {
    void writeActionLog(String id, String executedBy, Enum<?> action, String details);
    String getEditDetails(T original, T updated);
}
