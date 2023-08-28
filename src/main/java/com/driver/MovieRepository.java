package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MovieRepository {

    Map<String,Movie> movdb = new HashMap<>();
    Map<String ,Director> directdb = new HashMap<>();

    Map<String, List<Movie>> mixeddb = new HashMap<>();
    public void addMovie(Movie movie) {

      movdb.put(movie.getName(),movie);
    }

    public void addDirector(Director director) {
        directdb.put(director.getName(),director);
    }

    public void addMovieDirectorPair(String movieName, String directorName) {
       Movie move = movdb.get(movieName);
       if(move != null && directdb.containsKey(directorName)){
           List<Movie> li = mixeddb.getOrDefault(directorName,new ArrayList<>());
           li.add(move);
           mixeddb.put(directorName,li);
       }
    }

    public Movie getMovieByName(String name) {
       return movdb.get(name);
    }

    public Director getDirectorByName(String name) {
        return directdb.get(name);
    }


    public List<Movie> getMoviesByDirectorName(String directorName) {
        return mixeddb.get(directorName);
    }

    public List<String> findAllMovies() {
        return new ArrayList<>(movdb.keySet());
    }

    public void deleteAllDirectors() {
        for(List<Movie> movieList : mixeddb.values()){
            for(Movie movie : movieList){
                String movieName=movie.getName();
                movdb.remove(movieName);
            }
        }
        directdb.clear();
        mixeddb.clear();
    }

    public void deleteDirectorByName(String directorName) {
        if(mixeddb.containsKey(directorName)){
            List<Movie> movieList =mixeddb.get(directorName);
            for(Movie movie : movieList){
                String movieName=movie.getName();
                movdb.remove(movieName);
            }
        }
        mixeddb.remove(directorName);
        directdb.remove(directorName);
    }
}
