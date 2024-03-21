public class ContactRequestHandler implements FeedBackHandler{
    private FeedBackHandler nextHandler;

    @Override
    public void setNextHandler(FeedBackHandler handler) {
        this.nextHandler = handler;
    }

    @Override
    public void handleFeedback(FeedbackMessage message) {
        if (message.getType() == FeedbackType.contact_request) {
            System.out.println("Contact Request Handler: Request received from " + message.getSenderEmail());
            System.out.println("Content: " + message.getContent());

            // Handle contact request logic here
        } else if (nextHandler != null) {
            nextHandler.handleFeedback(message);
        }
    }

    public void setNextHandler(FeedBackHandler handler) {
        nextHandler = handler;
    }
}
