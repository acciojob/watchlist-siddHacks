package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public void addMovie(Movie movie)
    {
        Map<String,Movie> movieDB=movieRepository.getMovieDB();
        String primaryKey=movie.getName();
        movieDB.put(primaryKey,movie);
        return;
    }

    public void addDirector(Director director)
    {
        Map<String,Director> directorDB=movieRepository.getDirectorDB();
        String primaryKey=director.getName();
        directorDB.put(primaryKey,director);
        return;
    }

    public void addMovieDirectorPair(String movieName, String directorName)
    {
        movieRepository.addMovieDirectorPair(movieName,directorName);
    }

    public Movie getMovieByName(String movieName) {
        return movieRepository.getMovieByName(movieName);
    }

    public Director getDirectorByName(String directorName) {
        return movieRepository.getDirectorByName(directorName);
    }

    public List<String> getMoviesByDirectorName(String directorName) {
        return movieRepository.getMoviesByDirectorName(directorName);
    }

    public List<String> findAllMovies() {
        return movieRepository.findAllMovies();
    }

    public void deleteDirectorByName(String directorName) {
        movieRepository.deleteDirectorByName(directorName);
    }

    public void deleteAllDirectors() {
        movieRepository.deleteAllDirectors();
    }
}
