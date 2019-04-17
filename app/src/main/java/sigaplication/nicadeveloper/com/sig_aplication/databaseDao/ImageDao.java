package sigaplication.nicadeveloper.com.sig_aplication.databaseDao;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import sigaplication.nicadeveloper.com.sig_aplication.model.Image;
import sigaplication.nicadeveloper.com.sig_aplication.model.User;

public interface ImageDao {

    @Query("SELECT * FROM Image WHERE complaint_id = :id")
    Image loadByComplaintId(int id);

    @Insert
    void insert(Image image);

    @Delete
    void delete(Image image);

    @Update
    void update(Image image);

    @Query("DELETE FROM Image WHERE id = :id")
    void deletePicture(int id);

    @Query("DELETE FROM Image")
    void deleteAll();

    @Query("Select * from Image order by id DESC LIMIT 1")
    Image getLastId();
}
