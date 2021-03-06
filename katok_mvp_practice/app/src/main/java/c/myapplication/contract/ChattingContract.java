package c.myapplication.contract;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import c.myapplication.model.entity.ChatEntity;

public interface ChattingContract {

    interface ChatView{
        Application getMyApplication();
        int getUserId();
        String getTextMessage();
    }

    interface ChatPresenter{
        LiveData<List<ChatEntity>> makeChatList();
        void insertChat();
    }
}
