package net.irisco.starwars.model.networkRepo;

import net.irisco.starwars.pojo.FilmsModel;
import net.irisco.starwars.pojo.PeopleModel;
import net.irisco.starwars.pojo.PlanetsModel;
import net.irisco.starwars.pojo.ResultModel;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitInterface {

    @GET("people/?")
    Observable<ResultModel> getPeople(@Query("search") String characterName);

    @GET("planets/")
    Observable<PlanetsModel> getPlanets(@Query("id") String id);

    @GET("starships/")
    Observable<PlanetsModel> getStarships(@Query("id") String id);

    @GET("films/")
    Observable<FilmsModel> getFilms(@Query("id") String id);

    @GET("people/")
    Observable<PeopleModel> getPeoples(@Query("id") String id);


}
