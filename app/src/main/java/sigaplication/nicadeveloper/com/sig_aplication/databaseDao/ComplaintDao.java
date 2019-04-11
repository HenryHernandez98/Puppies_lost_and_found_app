package sigaplication.nicadeveloper.com.sig_aplication.databaseDao;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import sigaplication.nicadeveloper.com.sig_aplication.models.Complaint;
import sigaplication.nicadeveloper.com.sig_aplication.models.User;

    public interface ComplaintDao {

        @Query("SELECT * FROM Complaint order by id desc")
        List<Complaint> getAll();

        @Query("SELECT * FROM Complaint WHERE id = :id")
        User loadById(int id);

        @Query("SELECT * FROM Complaint c WHERE c.city LIKE :city")
        List<Complaint> loadBYCity (String city);

        @Insert
        void insert(Complaint complaint);

        @Delete
        void delete(Complaint complaint);

        @Update
        void update(Complaint complaint);

        @Query("DELETE FROM Complaint WHERE id = :id")
        void deleteComplaint(int id);

        @Query("DELETE FROM Complaint")
        void deleteAll();

        @Query("Select * from Complaint order by id DESC LIMIT 1")
        User getLastId();
    }
