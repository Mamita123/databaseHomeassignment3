public class DevelopmentSuggestionHandler implements FeedBackHandler{
    private FeedBackHandler nextHandler;

    @Override
    public void handleFeedback(FeedbackMessage message) {
        if (message.getType() == FeedbackType.development_suggestion) {
            System.out.println("Development Suggestion Handler: Suggestion received from " + message.getSenderEmail());
            System.out.println("Content: " + message.getContent());
            // Handle development suggestion logic here
        } else if (nextHandler != null) {
            nextHandler.handleFeedback(message);
        }
    }

    public void setNextHandler(FeedBackHandler handler) {
        nextHandler = handler;
    }
}
