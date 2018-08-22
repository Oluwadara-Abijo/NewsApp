package com.example.dara.newsapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class News implements Parcelable {

    private String mSectionName;

    private String mWebTitle;

    private String mWebUrl;

    private String mWebPublicationDate;

    //Constructor which creates a News object
    public News(String sectionName, String webTitle, String webUrl, String webPublicationdate) {
        mSectionName = sectionName;
        mWebTitle = webTitle;
        mWebUrl = webUrl;
        mWebPublicationDate = webPublicationdate;
    }

    public String getmSectionName() {
        return mSectionName;
    }

    public String getmWebTitle() {
        return mWebTitle;
    }

    public String getmWebUrl() {
        return mWebUrl;
    }

    public String getmWebPublicationDate() {
        return mWebPublicationDate;
    }

    protected News(Parcel in) {
        mSectionName = in.readString();
        mWebTitle = in.readString();
        mWebUrl = in.readString();
        mWebPublicationDate = in.readString();
    }

    public static final Creator<News> CREATOR = new Creator<News>() {
        @Override
        public News createFromParcel(Parcel in) {
            return new News(in);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mSectionName);
        parcel.writeString(mWebTitle);
        parcel.writeString(mWebUrl);
        parcel.writeString(mWebPublicationDate);
    }
}
