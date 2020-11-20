package c.myapplication.katok_mvvm_practice.model.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.Date;
import java.util.List;

import c.myapplication.katok_mvvm_practice.model.KatokDatabase;
import c.myapplication.katok_mvvm_practice.model.dao.ChatDao;
import c.myapplication.katok_mvvm_practice.model.entity.ChatEntity;

public class ChatRepository {
    private ChatDao chatDao;
    private LiveData<List<ChatEntity>> chatEntityList;

    private ChatEntity chat;

    public ChatRepository(Application application){
        KatokDatabase db = KatokDatabase.getInstance(application);
        chatDao = db.chatDao();
    }

    public LiveData<List<ChatEntity>> findChatList(int userId){
        chatEntityList = chatDao.findChatList(userId);
        return chatEntityList;
    }

    public void insertChat(int userId, String msg) {
        chat = new ChatEntity();
        chat.userId = userId;
        chat.contents = msg;
        chat.isMe = true;
        chat.time = new Date();

        KatokDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                chatDao.insertChat(chat);
            }
        });
    }

}
