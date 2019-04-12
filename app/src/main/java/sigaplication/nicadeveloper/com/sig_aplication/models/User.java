package sigaplication.nicadeveloper.com.sig_aplication.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity (tableName = "User")
public class User {

    @PrimaryKey (autoGenerate = true)
    @ColumnInfo(name = "id")
    private String id;

    @ColumnInfo (name = "password")
    private String password;

    @ColumnInfo (name = "username")
    private String username;

    @ColumnInfo (name = "name")
    private String name;

    @ColumnInfo (name = "email")
    private String email;

    @Embedded
    private Complaint complaint;

    @Embedded
    private UserPicture userPicture;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Complaint getComplaint() {
        return complaint;
    }

    public void setComplaint(Complaint complaint) {
        this.complaint = complaint;
    }

    public UserPicture getUserPicture() {
        return userPicture;
    }

    public void setUserPicture(UserPicture userPicture) {
        this.userPicture = userPicture;
    }
}
