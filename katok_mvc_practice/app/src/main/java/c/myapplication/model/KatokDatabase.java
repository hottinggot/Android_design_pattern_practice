package c.myapplication.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import c.myapplication.model.dao.ChatDao;
import c.myapplication.model.dao.UserDao;
import c.myapplication.model.entity.ChatEntity;
import c.myapplication.model.entity.UserEntity;

@Database(entities = {UserEntity.class, ChatEntity.class}, version = 1, exportSchema = false)
@TypeConverters({RoomTypeConverter.class})
public abstract class KatokDatabase extends RoomDatabase {

    abstract public ChatDao chatDao();
    abstract public UserDao userDao();

    private static KatokDatabase INSTANCE;

    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(2);

    public static KatokDatabase getInstance(Context context) {
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    KatokDatabase.class, "katok_db").build();
        }

        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

}
