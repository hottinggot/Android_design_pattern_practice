package c.myapplication.katok_mvvm_practice.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import java.util.List;

import c.myapplication.katok_mvvm_practice.model.dao.UserDao;
import c.myapplication.katok_mvvm_practice.model.repository.UserRepository;

public class UserViewModel extends AndroidViewModel {

    UserRepository userRepository;
    LiveData<List<UserDao.UserChat>> userEntityList;

    public UserViewModel(Application application){
        super(application);
        userRepository = new UserRepository(application);
        userEntityList = userRepository.findListInformation();
    }

    public LiveData<List<UserDao.UserChat>> getAllUserChat(){
        return userEntityList;
    }

    public void insertUser(int id, String name){
        userRepository.insertUser(id, name);
    }

    //이래도 되려나...
    public int getLastId(){
        if(userEntityList.getValue().size()==0){
            return 0;
        } else {
            return userEntityList.getValue().get(0).userId;
        }
    }

}