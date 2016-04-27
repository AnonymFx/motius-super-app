package de.thomas.pettinger.motiussuperapp;

/**
 * A DAO for the data received from the Motius API
 */
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

    /**
     * Returns the first 250 characters of the body
     */
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
