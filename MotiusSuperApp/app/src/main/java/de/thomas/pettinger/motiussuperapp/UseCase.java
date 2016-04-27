package de.thomas.pettinger.motiussuperapp;

public class UseCase {

    private String mTitle;
    private String mBody;

    public UseCase(String title, String body) {
        mTitle = title;
        mBody = body;
    }

    public String getBody() {
        return mBody;
    }

    public String getBodyMax250() {
        if (mBody.length() > 250) {
            String formattedBody = mBody.substring(0, 250);
            formattedBody += "...";
            return formattedBody;
        } else {
            return mBody;
        }
    }

    public String getTitle() {
        return mTitle;
    }

}
