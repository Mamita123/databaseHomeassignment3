public class CompensationClaimHandler implements FeedBackHandler {
    private FeedBackHandler nextHandler;

    @Override
    public void setNextHandler(FeedBackHandler handler) {
        this.nextHandler = handler;
    }

    @Override
    public void handleFeedback(FeedbackMessage message) {
        if (message.getType() == FeedbackType.compensation_claim) {
            System.out.println("Compensation Claim Handler: Claim received from " + message.getSenderEmail());
            System.out.println("Content: " + message.getContent());
            // Handle compensation claim logic here
        } else if (nextHandler != null) {
            nextHandler.handleFeedback(message);
        }
    }

    public void setNextHandler(FeedBackHandler handler) {
        nextHandler = handler;
    }
}
