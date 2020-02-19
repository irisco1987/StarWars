package net.irisco.starwars.feature.detailCharacter;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import net.irisco.starwars.model.networkRepo.NetworkRepository;
import net.irisco.starwars.pojo.PeopleModel;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DetailViewModel extends ViewModel {
    NetworkRepository networkRepository = new NetworkRepository();
    MutableLiveData<PeopleModel> data = new MutableLiveData<>();
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    LiveData<PeopleModel> getDetail() {
        return data;
    }

    void setDetail(String url) {
        networkRepository.getPeopleDetail(url).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<PeopleModel>() {
            @Override
            public void onSubscribe(Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onNext(PeopleModel peopleModel) {
                data.setValue(peopleModel);
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });
    }

    @Override
    protected void onCleared() {
        compositeDisposable.dispose();
    }
}
