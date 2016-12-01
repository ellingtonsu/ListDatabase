package tw.edu.au.csie.listdatabase;

/**
 * Created by Wei-Tsung on 2016/12/1.
 */

public class Event {
    int mId;
    String mEvent;
    String mDate;

    Event(int id, String event, String date) {
        mId = id;
        mEvent = event;
        mDate = date;
    }

    int getId() {return mId;}
    String getEvent() {return mEvent;}
    String getDate() {return mDate;}
}
