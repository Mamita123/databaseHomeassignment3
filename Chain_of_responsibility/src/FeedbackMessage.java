public class FeedbackMessage {
    private FeedbackType type;
    private String content;
    private String senderEmail;

    public FeedbackMessage(FeedbackType type, String content, String senderEmail){
        this.type = type;
        this.content = content;
        this.senderEmail = senderEmail;
    }

    public FeedbackType getType(){
        return type;
    }

    public String getContent(){
        return content;
    }

    public String getSenderEmail(){
        return senderEmail;
    }
}
