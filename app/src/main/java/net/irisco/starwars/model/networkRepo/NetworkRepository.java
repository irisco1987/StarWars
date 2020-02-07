package net.irisco.starwars.model.networkRepo;

import androidx.lifecycle.MutableLiveData;
import net.irisco.starwars.pojo.ResultModel;
import net.irisco.starwars.utils.RetrofitGenerator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkRepository {
    RetrofitInterface service =
            RetrofitGenerator.createService(RetrofitInterface.class);

    MutableLiveData<ResultModel> resultData = new MutableLiveData<>();

    public MutableLiveData<ResultModel> getResult(String str) {
        service.getPeople(str).enqueue(new Callback<ResultModel>() {
            @Override
            public void onResponse(Call<ResultModel> call, Response<ResultModel> response) {
                resultData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<ResultModel> call, Throwable t) {

            }
        });
        return resultData;
    }


}
