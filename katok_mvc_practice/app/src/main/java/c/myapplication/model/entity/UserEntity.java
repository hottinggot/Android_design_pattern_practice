package c.myapplication.model.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.Data;

@Data
@Entity(tableName = "user")
public class UserEntity {
    @PrimaryKey
    public int userId;
    public String userName;
    public String profileImage;

}
