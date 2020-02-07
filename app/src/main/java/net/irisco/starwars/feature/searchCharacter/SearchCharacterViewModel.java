package net.irisco.starwars.feature.searchCharacter;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import net.irisco.starwars.model.networkRepo.NetworkRepository;
import net.irisco.starwars.pojo.ResultModel;

public class SearchCharacterViewModel extends ViewModel {
    private MutableLiveData<ResultModel> data = new MutableLiveData<>();
    NetworkRepository networkRepository = new NetworkRepository();

    public void setChar(String str) {
        data = networkRepository.getResult(str);
    }

    public LiveData<ResultModel> getResult() {
        return data;
    }
}
