package net.irisco.starwars.model.networkRepo;

import android.util.Log;

import net.irisco.starwars.pojo.PeopleModel;
import net.irisco.starwars.pojo.ResultModel;
import net.irisco.starwars.utils.RetrofitGenerator;

import io.reactivex.Observable;

public class NetworkRepository {
    RetrofitInterface service =
            RetrofitGenerator.createService(RetrofitInterface.class);

    public Observable<ResultModel> getResult(String str) {
        return service.getPeople(str);
    }

    public Observable<PeopleModel> getPeopleDetail(String url) {
        return service.getPeopleDetail(url);
    }
}
