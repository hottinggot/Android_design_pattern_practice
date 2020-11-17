package c.myapplication.presenter;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import java.util.List;

import c.myapplication.model.entity.ChatEntity;
import c.myapplication.model.repository.ChatRepository;
import c.myapplication.view.MainContract;

public class ChattingPresenter implements MainContract.ChatPresenter {

    private MainContract.ChatView chatView;
    private ChatRepository chatRepository;

    public ChattingPresenter(MainContract.ChatView chatView) {
        this.chatView = chatView;
    }

    private void setChatRepository() {
        chatRepository = new ChatRepository(chatView.getMyApplication());
    }

    @Override
    public void makeChatList() {
        setChatRepository();

        chatRepository.findChatList(chatView.getUserId())
                .observe((LifecycleOwner) chatView.getMyApplication(), new Observer<List<ChatEntity>>() {
                    @Override
                    public void onChanged(List<ChatEntity> chatEntities) {
                        chatView.setAdapter(chatEntities);
                    }
                });
    }

    @Override
    public void insertChat() {
        setChatRepository();
        chatRepository.insertChat(chatView.getUserId(), chatView.getTextMessage());
    }
}
