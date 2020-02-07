package net.irisco.starwars.model.networkRepo;

import net.irisco.starwars.pojo.FilmsModel;
import net.irisco.starwars.pojo.ResultModel;
import net.irisco.starwars.pojo.PlanetsModel;
import net.irisco.starwars.pojo.PeopleModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitInterface {

    @GET("people/?")
    Call<ResultModel> getPeople(@Query("search") String characterName);

    @GET("planets/")
    Call<PlanetsModel> getPlanets(@Query("id") String id);

    @GET("starships/")
    Call<PlanetsModel> getStarships(@Query("id") String id);

    @GET("films/")
    Call<FilmsModel> getFilms(@Query("id") String id);

    @GET("people/")
    Call<PeopleModel> getPeoples(@Query("id") String id);


}
