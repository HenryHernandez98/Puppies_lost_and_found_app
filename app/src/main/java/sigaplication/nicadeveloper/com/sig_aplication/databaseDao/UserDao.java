package sigaplication.nicadeveloper.com.sig_aplication.databaseDao;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import sigaplication.nicadeveloper.com.sig_aplication.models.User;

public interface UserDao {

    @Query ("SELECT * FROM User order by id desc")
    List<User> getAll();

    @Query("SELECT * FROM User WHERE id = :id")
    User loadById(int id);

    @Insert
    void insert(User user);

    @Query("SELECT * FROM User u WHERE u.name LIKE :name")
    List<User> loadBYName(String name);

    @Delete
    void delete(User user);

    @Update
    void update(User user);

    @Query("DELETE FROM User")
    void deleteAll();

    @Query("Select * from User order by id DESC LIMIT 1")
    User getLastId();

}
