package com.example.coasterwait;

import android.os.Parcel;
import android.os.Parcelable;

public class Ride implements Parcelable {
    private int mId;
    private String mName;
    private String mDescription;
    private int mWait;
    private boolean mStatus;
    private int mArea;
    private int mRating;
    private boolean mFasttrack;
    private int mRideType;
    private int mLowHeight;
    private int mHighHeight;
    private String mVideo;
    public Ride(int id, String name, String description,int wait, boolean status, int rideType, int area, int rating, boolean fasttrack, int lowheight, int highheight, String video)
    {
        mId=id;
        mName=name;
        mDescription=description;
        mWait=wait;
        mStatus=status;
        mArea=area;
        mRating=rating;
        mFasttrack=fasttrack;
        mRideType=rideType;
        mLowHeight=lowheight;
        mHighHeight=highheight;
        mVideo=video;
    }

    protected Ride(Parcel in) {
        mId = in.readInt();
        mName = in.readString();
        mDescription = in.readString();
        mWait = in.readInt();
        mStatus = in.readByte() != 0;
        mArea = in.readInt();
        mRating = in.readInt();
        mFasttrack = in.readByte() != 0;
        mRideType = in.readInt();
        mLowHeight = in.readInt();
        mHighHeight = in.readInt();
        mVideo = in.readString();
    }

    public static final Creator<Ride> CREATOR = new Creator<Ride>() {
        @Override
        public Ride createFromParcel(Parcel in) {
            return new Ride(in);
        }

        @Override
        public Ride[] newArray(int size) {
            return new Ride[size];
        }
    };

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getDescription() {
        return mDescription;
    }

    public int getWait() {
        return mWait;
    }

    public boolean isStatus() {
        return mStatus;
    }

    public int getArea() {
        return mArea;
    }

    public int getRating() {
        return mRating;
    }

    public boolean isFasttrack() {
        return mFasttrack;
    }

    public int getRideType() {
        return mRideType;
    }

    public int getLowHeight() {
        return mLowHeight;
    }

    public int getHighHeight() {
        return mHighHeight;
    }

    public String getVideo() {
        return mVideo;
    }

    public void setWait(int wait) {
        mWait = wait;
    }

    public void setStatus(boolean status) {
        mStatus = status;
    }

    @Override
    public String toString() {
        return "Ride{" +
                "mId=" + mId +
                ", mName='" + mName + '\'' +
                ", mDescription='" + mDescription + '\'' +
                ", mWait=" + mWait +
                ", mStatus=" + mStatus +
                ", mArea=" + mArea +
                ", mRating=" + mRating +
                ", mFasttrack=" + mFasttrack +
                ", mRideType=" + mRideType +
                ", mLowHeight=" + mLowHeight +
                ", mHighHeight=" + mHighHeight +
                ", mVideo='" + mVideo + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mName);
        dest.writeString(mDescription);
        dest.writeInt(mWait);
        dest.writeByte((byte) (mStatus ? 1 : 0));
        dest.writeInt(mArea);
        dest.writeInt(mRating);
        dest.writeByte((byte) (mFasttrack ? 1 : 0));
        dest.writeInt(mRideType);
        dest.writeInt(mLowHeight);
        dest.writeInt(mHighHeight);
        dest.writeString(mVideo);
    }
}
