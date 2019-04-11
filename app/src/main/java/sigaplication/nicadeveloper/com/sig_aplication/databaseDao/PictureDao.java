package sigaplication.nicadeveloper.com.sig_aplication.databaseDao;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import sigaplication.nicadeveloper.com.sig_aplication.models.Picture;
import sigaplication.nicadeveloper.com.sig_aplication.models.User;

public interface PictureDao {

    @Query("SELECT * FROM Picture WHERE complaint_id = :id")
    User loadByComplaintId(int id);

    @Insert
    void insert(Picture picture);

    @Delete
    void delete(Picture picture);

    @Update
    void update(Picture picture);

    @Query("DELETE FROM Picture WHERE id = :id")
    void deletePicture(int id);

    @Query("DELETE FROM Picture")
    void deleteAll();

    @Query("Select * from Picture order by id DESC LIMIT 1")
    User getLastId();
}
