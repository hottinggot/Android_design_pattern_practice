package c.myapplication.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import c.myapplication.model.entity.ChatEntity;

@Dao
public interface ChatDao {
    @Query("select * from chat where userId=:userId")
    LiveData<List<ChatEntity>> findChatList(int userId);

    @Insert
    void insertChat(ChatEntity chatEntity);
}
