public class GeneralFeedbackHandler implements FeedBackHandler{
    private FeedBackHandler nextHandler;

    @Override
    public void setNextHandler(FeedBackHandler handler) {
        this.nextHandler = handler;
    }
    @Override
    public void handleFeedback(FeedbackMessage message) {
        if (message.getType() == FeedbackType.general_feedback) {
            System.out.println("General Feedback Handler: Feedback received from " + message.getSenderEmail());
            System.out.println("Content: " + message.getContent());
            // Handle general feedback logic here
        } else if (nextHandler != null) {
            nextHandler.handleFeedback(message);
        }
    }
}
