/* Based on the ecs 100 template
 * Code for Movie Rater
 * Name: SACH
 * Date: 02/07/2020
 */

  

import ecs100.*;
import java.util.*;
import java.io.*;
import java.awt.Color;

// Data storage
import java.util.HashMap;
import java.util.ArrayList;

// File handling
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;


/** Manage all movie objects stored in hash-map
 * and provide a recommendation
 * 
 * @author SACH
 * @version 25/08/2020
 */
public class MovieManager {

    // Create Fields
    private String title;
    private ArrayList<String> genre = new ArrayList<String>();
    private String director;
    private String userRating = "";
    private String bill;
    private Movie movie;
    
    // Set constants
    private final double WIDTH = 400;
    private final double HEIGHT = 100;
    private final double LEFT = 20;
    private final double TOP = 20;
    
    // Setting up hashmap for movie storage
    HashMap<String, Movie> movieRatings = new HashMap<String, Movie>();
    
    /**
     * Constructor method for class MovieManager
     */
    public MovieManager() {
        addFromFile();
        mainMenu();
    }
    
    /**
     * Main GUI menu
     */
    private void mainMenu() {
        UI.initialise();
        UI.setDivider(0.3);
        UI.setFontSize(20);
        
        UI.addButton("Add Movie", this::addMovieUi);
        UI.addButton("Recommend", this::recommend);
        UI.addButton("Add Rating", this::updateRatingUi);
        UI.addButton("View All", this::viewAll);
        UI.addButton("Quit", UI::quit);
    }
    
    /**
     * Adds movies from a text file
     */
    private void addFromFile() {
        File file = new File("movies.txt");
        
        try {
            Scanner fileRead = new Scanner(file);
            
            while (fileRead.hasNextLine()) {
                String line = fileRead.nextLine();
                char[] chars = line.toCharArray();
                ArrayList<String> entries = new ArrayList<String>();
                String entry = "";
                for (char i : chars) {
                    if (i == 0x2c) {
                        entries.add(entry);
                        entry = "";
                    }
                    else {
                        entry += i;
                    }
                }
                String fileTitle = capString(entries.get(0).trim());
                ArrayList<String> fileGenre = new ArrayList<String>();
                String[] fileGenres = entries.get(1).split(" ");
                for (String i : fileGenres) {
                    fileGenre.add(capString(i));
                }
                fileGenre.remove("");
                String fileDirector = capString(entries.get(2).trim());
                Movie movie = new Movie(fileTitle, fileGenre,
                                         fileDirector, userRating);
                movieRatings.put(movie.getTitle(), movie);
            }
        }
        
        catch (Exception e) {
            UI.println(e);
        }
        
    }
    
    /**
     * Changes the GUI when the user is rating a movie
     */
    private void updateRatingUi() {
        UI.initialise();
        UI.setDivider(0.3);
        UI.addTextField("Title / Taitara", this::setTitle);
        UI.addButton("Like / Matareka", ()->this.userRating = "Like");
        UI.addButton("Dislike / Matakawa", ()->this.userRating = "Dislike");
        UI.addButton("Rate", this::updateRating);
        UI.addButton("Back", this::mainMenu);
    }
    
    /**
     * Changes the GUI when the user is adding a movie
     */
    private void addMovieUi() {
        UI.initialise();
        UI.setDivider(0.3);
        UI.addTextField("Title / Taitara", this::setTitle);
        UI.addTextField("Genre / Momo", this::setGenre);
        UI.addTextField("Director / Kaitohu", this::setDirector);
        UI.addButton("Like / Matareka", ()->this.userRating = "Like");
        UI.addButton("Dislike / Matakawa", ()->this.userRating = "Dislike");
        
        UI.addButton("Submit / Tuku kōrero", this::submit);
        UI.addButton("Back", this::mainMenu);
    }
    
    /**
     * Updates the rating for a given movie
     */
    private void updateRating() {
        UI.setDivider(0.3);
        UI.clearText();
        for (String i : movieRatings.keySet()) {
            if (i.equals(title)) {
                movie = movieRatings.get(i);
                movie.setRating(userRating);
                userRating = "";
            }
        }
        try {
            drawMovie(movie);
            UI.println("You updated the rating for \n" + title);
        }
        catch (Exception e) {
            UI.println(title + " is not in"
                        + " the database."
                        + "\nYou can add it in the"
                        + "\nmain menu");
        }
        
        
    }
    
    /**
     * Prints out all the stored movies
     */
    private void viewAll() {
        UI.setDivider(1);
        for (Movie i : movieRatings.values()) {
            UI.println(i.getTitle());
            UI.println(Arrays.toString(i.getGenre().toArray())
                        .replaceAll("[\\[\\]]", ""));
            UI.println(i.getDirector());
            UI.println("\n");
        }
    }
    
    /**
     * Converts input to Title Case
     */
    private String capString(String s) {
        String[] words = s.split(" ");
        ArrayList<String> converted = new ArrayList<String>();
        
        for (String word : words) {
            if (!word.equals("")) {
                converted.add(word.substring(0, 1).toUpperCase()
                            + word.substring(1));
            }
        }
        
        String titleCase = String.join(" ", converted);
        
        return titleCase;
    }
    
    /**
     * Sets the title based on user input
     */
    private void setTitle(String usertitle) {
        title = capString(usertitle.trim());
    }
    
    /**
     * Sets the genre from user input
     */
    private void setGenre(String userGenre) {
        genre.clear();
        String[] genres = userGenre.split(" ");
        for (String i : genres) {
            genre.add(capString(i));
        }
        // Remove any empty entries
        do {
            genre.remove("");
        }
        while (genre.contains(""));
    }
    
    /**
     * Sets director based on user input
     */
    private void setDirector(String userdirector) {
        director = capString(userdirector.trim());
    }

    /**
     * Creates a movie class using the user defined variables
     */
    private void submit() {
        // if statement to check all fields are not empty
        if (title != null && genre != null && director != null &&
            !title.equals("") && !genre.equals("") && !director.equals("")) {
            Movie movie = new Movie(title, genre, director, userRating);
            movieRatings.put(title, movie);
            title = null;
            userRating = "";
            UI.println("Movie added!");
            drawMovie(movie);
        } 
        else if (title == null || title.equals("")) {
            UI.println("Please enter a title");
        } 
        else if (genre == null || genre.equals("")) {
            UI.println("Please enter a genre");
        } 
        else if (director == null || director.equals("")) {
            UI.println("Please enter a director");
        }
    }
    
    /**
     * Draws the movie objects to the graphics pane
     */
    private void drawMovie(Movie movie) {
        UI.clearGraphics();
        UI.drawString(movie.getTitle(), LEFT + 10, TOP + 30);
        UI.drawRect(LEFT, TOP, WIDTH, HEIGHT);
        UI.drawString("Genre: " + Arrays.toString(movie.getGenre()
                        .toArray()).replaceAll("[\\[\\]]", ""),
                        LEFT + 10, TOP + 60);
        UI.drawString("Director: " + movie.getDirector(), LEFT + 10, TOP + 80);
        if (movie.getRating().equals("Like")) {
            UI.drawImage("images/thumbsUp.png", WIDTH - (LEFT * 2), 
                        HEIGHT / 2 + TOP, 50, 50);
        } 
        else if (movie.getRating().equals("Dislike")) {
            UI.drawImage("images/thumbsDown.png", WIDTH - (LEFT * 2), 
                        HEIGHT / 2 + TOP, 50, 50);
        }
        
    }
    
    /**
     * Recomends a movie based on Genre and/or director
     */
    private void recommend() {
        UI.clearGraphics();
        UI.clearText();
        UI.setDivider(0.3);
        
        ArrayList<Movie> likedMovies = new ArrayList<Movie>();
        ArrayList<Movie> possibleMovies = new ArrayList<Movie>();
        
        // Find movies the user liked
        for (String i : movieRatings.keySet()) {
            Movie movie = movieRatings.get(i);
            if (movie.getRating().equals("Like")) {
                likedMovies.add(movie);
            }
        }

        if (likedMovies.size() > 0) {
            // Randomly select a liked movie
            int randomLiked = (int) (Math.random() * likedMovies.size());
            Movie likedMovie = likedMovies.get(randomLiked);
            
            for (Movie a : likedMovies) {
                // find it's genre/s
                ArrayList recomendGenre = a.getGenre();
                
                // find an unrated movie with same genre/s
                for (String j : movieRatings.keySet()) {
                    Movie possibleMovie = movieRatings.get(j);
                    if (possibleMovie.getRating().equals("") && !(possibleMovie.getRating().equals("Dislike"))) { // Not dislike
                        ArrayList genres = possibleMovie.getGenre();                        
                        for (int k = 0; k < genres.size(); k++) {
                            String genre = genres.get(k).toString();
                            if (recomendGenre.contains(genre)) {
                                // Add movie to list of possible movies
                                possibleMovies.add(possibleMovie);
                            }
                        }
                    }
                }
            }
            if (possibleMovies.size() > 0) {
                // Randomly select a possible movie
                int random = (int) (Math.random() * possibleMovies.size());
                Movie recommenedMovie = possibleMovies.get(random);
                UI.println("Because you liked \n" + likedMovie.getTitle()
                            + "\nwe recommend:");
                drawMovie(recommenedMovie);
            }
            else {
                // Give an error if can't find a similar movie
                UI.println("We couldn't find any \nmovies for you!");
                UI.println("Try rating a few more movies \nthen try again.");
            }
        }
        else {
            UI.println("You haven't rated any movies");
        }
        
    }
    
    /**
     * Main class
     * @param args aguments for main class
     */
    public static void main(String[] args) {
        MovieManager obj = new MovieManager();
    }

}

