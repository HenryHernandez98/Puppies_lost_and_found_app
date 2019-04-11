package sigaplication.nicadeveloper.com.sig_aplication.databaseDao;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import sigaplication.nicadeveloper.com.sig_aplication.models.Picture;
import sigaplication.nicadeveloper.com.sig_aplication.models.User;
import sigaplication.nicadeveloper.com.sig_aplication.models.UserPicture;

public interface UserPictureDao {

    @Query("SELECT * FROM UserPicture WHERE user_id = :id")
    User loadByUserId(int id);

    @Insert
    void insert(UserPicture userPicture);

    @Delete
    void delete(UserPicture userPicture);

    @Update
    void update(UserPicture userPicture);

    @Query("DELETE FROM UserPicture WHERE id = :id")
    void deleteUserPicture(int id);

    @Query("DELETE FROM UserPicture")
    void deleteAll();

    @Query("Select * from UserPicture order by id DESC LIMIT 1")
    User getLastId();
}
