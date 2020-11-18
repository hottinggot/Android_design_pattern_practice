package c.myapplication.presenter;

import androidx.lifecycle.LiveData;

import java.util.List;

import c.myapplication.contract.ChattingContract;
import c.myapplication.model.entity.ChatEntity;
import c.myapplication.model.repository.ChatRepository;

public class ChattingPresenter implements ChattingContract.ChatPresenter {

    private ChattingContract.ChatView chatView;
    private ChatRepository chatRepository;

    public ChattingPresenter(ChattingContract.ChatView chatView) {
        this.chatView = chatView;
    }

    private void setChatRepository() {
        chatRepository = new ChatRepository(chatView.getMyApplication());
    }

    @Override
    public LiveData<List<ChatEntity>> makeChatList() {
        setChatRepository();
        return chatRepository.findChatList(chatView.getUserId());
    }

    @Override
    public void setChatList(List<ChatEntity> chatEntityList){
        chatView.setAdapter(chatEntityList);
    }

    @Override
    public void insertChat() {
        setChatRepository();
        chatRepository.insertChat(chatView.getUserId(), chatView.getTextMessage());
    }
}
