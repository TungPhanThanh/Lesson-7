package android.tungpt.lesson7;

public class Repo {
    private String mId;
    private String mName;
    private String mHtmlUrl;

    public Repo(String mId, String mName, String mHtmlUrl) {
        this.mId = mId;
        this.mName = mName;
        this.mHtmlUrl = mHtmlUrl;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmHtmlUrl() {
        return mHtmlUrl;
    }

    public void setmHtmlUrl(String mHtmlUrl) {
        this.mHtmlUrl = mHtmlUrl;
    }
}
