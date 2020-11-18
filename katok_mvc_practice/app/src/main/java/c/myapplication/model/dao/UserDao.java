package c.myapplication.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.Date;
import java.util.List;

import c.myapplication.model.entity.UserEntity;

@Dao
public interface UserDao {
    @Query("select user.userId as userId, user.userName as userName, user.profileImage as pI," +
            "chat.time as date, chat.contents as contents from user "
            + "inner join (select * from chat order by chatId desc) as chat on user.userId = chat.userId")
    LiveData<List<UserChat>> findListInformation();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(UserEntity userEntity);


    public static class UserChat {
        public int userId;
        public String userName;
        public String pI;
        public Date date;
        public String contents;
    }
}
