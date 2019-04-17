package sigaplication.nicadeveloper.com.sig_aplication.databaseDao;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import sigaplication.nicadeveloper.com.sig_aplication.model.Complaint;
import sigaplication.nicadeveloper.com.sig_aplication.model.Image;
import sigaplication.nicadeveloper.com.sig_aplication.model.User;
import sigaplication.nicadeveloper.com.sig_aplication.model.UserPicture;

@Database(entities = {User.class, Complaint.class, Image.class, UserPicture.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();
    public abstract ComplaintDao complaintDao();
    public abstract UserPictureDao userPictureDao();
    public abstract ProfileDao profileDao();
    public abstract ImageDao pictureDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "user-database")
                            // allow queries on the main thread.
                            // Don't do this on a real app! See PersistenceBasicSample for an example.
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
