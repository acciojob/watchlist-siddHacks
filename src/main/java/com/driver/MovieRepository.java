package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MovieRepository {

    Map<String,Movie> movieDB = new HashMap<>();
    Map<String,Director> directorDB = new HashMap<>();
    Map<String,List<Movie>> pairDB=new HashMap<>();

    public Map<String, Director> getDirectorDB() {
        return directorDB;
    }



    public Map<String, Movie> getMovieDB() {
        return movieDB;
    }


    public void addMovieDirectorPair(String movieName, String directorName)
    {
        Movie movie= movieDB.get(movieName);
        if(movie!=null && directorDB.containsKey(directorName)) {
            List<Movie> movieList = pairDB.getOrDefault(directorName,new ArrayList<>());
            movieList.add(movie);
            pairDB.put(directorName,movieList);
        }
        return;
    }

    public void setMovieDB(Map<String, Movie> movieDB) {
        this.movieDB = movieDB;
    }

    public void setDirectorDB(Map<String, Director> directorDB) {
        this.directorDB = directorDB;
    }

    public Map<String, List<Movie>> getPairDB() {
        return pairDB;
    }

    public void setPairDB(Map<String, List<Movie>> pairDB) {
        this.pairDB = pairDB;
    }

    public Movie getMovieByName(String movieName) {
        return movieDB.get(movieName);
    }

    public Director getDirectorByName(String directorName) {
        return  directorDB.get(directorName);
    }

    public void deleteAllDirectors() {
        for(List<Movie> movieList : pairDB.values()){
            for(Movie movie : movieList){
                String movieName=movie.getName();
                movieDB.remove(movieName);
            }
        }
        directorDB.clear();
        pairDB.clear();
    }

    public List<String> findAllMovies() {
        return new ArrayList<>(movieDB.keySet());
    }

    public List<String> getMoviesByDirectorName(String directorName) {

        List<String> movieName=new ArrayList<>();
        if(pairDB.containsKey(directorName)){
            List<Movie> Film=pairDB.get(directorName);
            for(Movie movie: Film)
            {
                movieName.add(movie.getName());
            }
        }
        return movieName;
    }

    public void deleteDirectorByName(String directorName) {

        if(pairDB.containsKey(directorName)){
            List<Movie> movieList =pairDB.get(directorName);
            for(Movie movie : movieList){
                String movieName=movie.getName();
                movieDB.remove(movieName);
            }
        }
        pairDB.remove(directorName);
        directorDB.remove(directorName);
    }
}
