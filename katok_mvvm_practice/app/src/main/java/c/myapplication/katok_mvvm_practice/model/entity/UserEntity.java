package c.myapplication.katok_mvvm_practice.model.entity;

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
