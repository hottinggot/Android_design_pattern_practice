package c.myapplication.model.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;

import lombok.Data;

@Data
@Entity(tableName = "chat", foreignKeys = @ForeignKey(entity = UserEntity.class, parentColumns = "userId", childColumns = "userId"))
public class ChatEntity {
    @PrimaryKey(autoGenerate = true)
    public int chatId;
    public int userId;
    public String contents;
    public Date time;
    public boolean isMe = false;

}
