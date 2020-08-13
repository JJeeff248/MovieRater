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
 * @version 11/08/2020
 */
public class MovieManager {

    // Create Fields
    private String title;
    private String genre;
    private String director;
    private String rating = "";
    private String bill;
    
    // Set constants
    private final double WIDTH = 400;
    private final double HEIGHT = 100;
    private final double LEFT = 20;
    private double top;
    
    // Setting up hashmap for movie storage
    HashMap<String, Movie> movieRatings = new HashMap<String, Movie>();
    
    /**
     * Constructor method for class MovieManager
     */
    public MovieManager() {
        addFromFile();
        //mainMenu();
    }
    
    private void mainMenu() {
        UI.initialise();
        UI.setDivider(0.3);
        UI.setFontSize(20);
        
        UI.addButton("Add Movie", this::addMovieUi);
        UI.addButton("Recommend", this::recommend);
        UI.addButton("Quit", UI::quit);
        
        drawMovie();
    }
    
    private void addFromFile() {
        File file = new File("movies.txt");
        
        try {
            Scanner fileRead = new Scanner(file);
            
            while (fileRead.hasNextLine()) {
                String line = fileRead.nextLine();
                char[] chars = line.toCharArray();
                String[] entries = new String[3];
                String entry = "";
                for (char i : chars) {
                    if (i == 0x2c) {
                        entries[entries.length - 1] = entry;
                        entry ="";
                    } else {
                        UI.println(i);
                        entry += i;
                        UI.println(entry);
                    }
                }
                Movie movie = new Movie(entries[0], entries[1], entries[2], rating);
                UI.println(entries[0]);
                movieRatings.put(movie.getTitle(), movie);
            }
        }
        
        catch (Exception e) {
            UI.println(e);
        }
        
    }
    
    private void addMovieUi() {
        UI.initialise();
        UI.addTextField("Title / Taitara", this::setTitle);
        UI.addTextField("Genre / Momo", this::setGenre);
        UI.addTextField("Director / Kaitohu", this::setDirector);
        UI.addButton("Like / Matareka", this::setRatingGood);
        UI.addButton("Dislike / Matakawa", this::setRatingBad);
        
        UI.addButton("Submit / Tuku kōrero", this::submit); // Special char c58d
        UI.addButton("Back", this::mainMenu);
    }
    
    /**
     * Sets the rating based on user input
     */
    private void setRatingGood() {
        rating = "Like";
    }
    
    /**
     * Sets rationg based on user input
     */
    private void setRatingBad() {
        rating = "Dislike";
    }
    
    /**
     * Sets the title based on user input
     */
    private void setTitle(String usertitle) {
        title = usertitle.trim();
    }
    
    /**
     * Sets the genre from user input
     */
    private void setGenre(String usergenre) {
        genre = usergenre.trim();
    }
    
    /**
     * Sets director based on user input
     */
    private void setDirector(String userdirector) {
        director = userdirector.trim();
    }

    /**
     * Creates a movie class using the user defined variables
     */
    private void submit() {
        // if statement to check all fields are not empty
        if (title != null && genre != null && director != null &&
            !title.equals("") && !genre.equals("") && !director.equals("")) {
            Movie movie = new Movie(title, genre, director, rating);
            movieRatings.put(title, movie);
            title = null;
            rating = "";
            mainMenu();
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
    private void drawMovie() {
        top = 20;
        UI.clearGraphics();
        int j = 1;
        for (Movie i : movieRatings.values()) {
            UI.drawRect(LEFT, top, WIDTH, HEIGHT);
            UI.drawString(i.getTitle(), LEFT + 10, top + 30);
            UI.drawString("Genre: " + i.getGenre(), LEFT + 10, top + 60);
            UI.drawString("Director: " + i.getDirector(), LEFT + 10, top + 80);
            if (i.getRating().equals("Like")) {
                UI.drawImage("images/thumbsUp.png", WIDTH - (LEFT * 2), 
                            HEIGHT / 2 + top, 50, 50);
            } 
            else if (i.getRating().equals("Dislike")) {
                UI.drawImage("images/thumbsDown.png", WIDTH - (LEFT * 2), 
                            HEIGHT / 2 + top, 50, 50);
            }
            top += 120;
        }
        
    }
    
    /**
     * Recomends a movie based on Genre and/or director
     */
    private void recommend() {
        UI.clearGraphics();
        UI.clearText();
        
        ArrayList<Movie> likedMovies = new ArrayList<Movie>();
        
        // Find movies the user liked
        UI.println("Recommendations for you");
        for (String i : movieRatings.keySet()) {
            Movie movie = movieRatings.get(i);
            if (movie.getRating().equals("Like")) {
                likedMovies.add(movie);
            }
        }
        // Look for similar movies based on genre and/or director
        for (Movie i : likedMovies) {
            UI.println(i.getTitle());
        }
        // Print or draw the recomendation
    }
    
    /**
     * Main class
     * @param args aguments for main class
     */
    public static void main(String[] args) {
        MovieManager obj = new MovieManager();
    }

}

