package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping("/add-movie")
    public ResponseEntity addMovie(@RequestBody Movie movie){
        movieService.addMovie(movie);
        return new ResponseEntity("Movie added Successfully!" ,HttpStatus.OK);
    }

    @PostMapping("/add-director")
    public ResponseEntity addDirector(@RequestBody Director director){
        movieService.addDirector(director);
        return new ResponseEntity("Director added Successfully!" ,HttpStatus.OK);
    }

    @PutMapping("/add-movie-director-pair")
    public ResponseEntity addMovieDirectorPair(@RequestParam("movie") String movieName , @RequestParam("director") String directorName){
        movieService.addMovieDirectorPair(movieName,directorName);
        return new ResponseEntity("Director-movie pair added Successfully!",HttpStatus.OK);
    }

    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable("name") String name){
       Movie moi = movieService.getMovieByName(name);
       return new ResponseEntity<Movie>(moi ,HttpStatus.OK);
    }

    @GetMapping("/get-director-by-name/{name}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable("name") String name){
        Director directs = movieService.getDirectorByName(name);
        return new ResponseEntity<Director>(directs ,HttpStatus.OK);
    }

    @GetMapping("/get-movies-by-director-name/{director}")
    public ResponseEntity<List<Movie>> getMoviesByDirectorName(@PathVariable("director") String directorName){
      List<Movie> movi = movieService.getMoviesByDirectorName(directorName);
      return new ResponseEntity<List<Movie>>(movi,HttpStatus.OK);
    }

    @GetMapping("get-all-movies")
    public ResponseEntity<List<String>> findAllMovies(){
        List<String> ans = movieService.findAllMovies();
        return new ResponseEntity<>(ans, HttpStatus.OK);
    }

    @DeleteMapping("delete-director-by-name")
    public ResponseEntity<String> deleteDirectorByName(@RequestParam("name") String directorName){
        movieService.deleteDirectorByName(directorName);
        return new ResponseEntity<>("Movies deleted successfully!", HttpStatus.OK);
    }

    @DeleteMapping("delete-all-directors")
    public ResponseEntity<String> deleteAllDirectors(){

        movieService.deleteAllDirectors();
        return new ResponseEntity<>("All movies deleted of every director successfully!", HttpStatus.OK);
    }

}
