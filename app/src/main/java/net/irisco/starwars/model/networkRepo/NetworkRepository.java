package net.irisco.starwars.model.networkRepo;

import androidx.lifecycle.MutableLiveData;
import net.irisco.starwars.pojo.ResultModel;
import net.irisco.starwars.utils.RetrofitGenerator;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkRepository {
    RetrofitInterface service =
            RetrofitGenerator.createService(RetrofitInterface.class);

    public MutableLiveData<ResultModel> resultData = new MutableLiveData<>();

    public Observable<ResultModel> getResult(String str) {
        return service.getPeople(str);
    }


}
