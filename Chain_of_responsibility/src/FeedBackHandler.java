public interface FeedBackHandler {
    void handleFeedback(FeedbackMessage message);
    void setNextHandler(FeedBackHandler handler);
}
