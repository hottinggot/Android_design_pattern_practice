package c.myapplication.katok_mvvm_practice.viewmodel;

import android.app.Application;

import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import c.myapplication.katok_mvvm_practice.model.entity.ChatEntity;
import c.myapplication.katok_mvvm_practice.model.repository.ChatRepository;

public class ChatViewModel extends AndroidViewModel {

    ChatRepository chatRepository;

    public ChatViewModel(Application application){
        super(application);
        chatRepository = new ChatRepository(application);
    }

    public LiveData<List<ChatEntity>> getChattingList(int userId){
        return chatRepository.findChatList(userId);
    }

    public void insertMessage(int userId, String msg) {
        chatRepository.insertChat(userId, msg);
    }

    public void getMessage(){

    }

}
