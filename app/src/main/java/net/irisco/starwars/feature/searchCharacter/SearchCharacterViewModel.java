package net.irisco.starwars.feature.searchCharacter;


import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import net.irisco.starwars.model.networkRepo.NetworkRepository;
import net.irisco.starwars.pojo.ResultModel;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SearchCharacterViewModel extends ViewModel {

    private MutableLiveData<ResultModel> data = new MutableLiveData<>();
    NetworkRepository networkRepository = new NetworkRepository();
    CompositeDisposable disposable = new CompositeDisposable();

    public void setChar(String str) {

        networkRepository
                .getResult(str)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResultModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onNext(ResultModel resultModel) {
                        data.setValue(resultModel);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public LiveData<ResultModel> getResult() {
        return data;
    }
}
