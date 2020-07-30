/* Based on the ecs 100 template
 * Code for Movie Rater
 * Name: SACH
 * Date: 02/07/2020
 */

 

import ecs100.*;
import java.util.*;
import java.io.*;
import java.awt.Color;
import java.util.HashMap;


/** Manage all movie objects stored in hash-map
 * and provide a recommendation
 */
public class MovieManager{

    // Create Fields
    private String title;
    private String genre;
    private String director;
    private boolean rating;
    private String bill;
    
    // Set constants
    private static double WIDTH = 400;
    private static double HEIGHT = 100;
    private static double LEFT = 20;
    private double top;
    
    // Setting up hashmap for movie storage
    HashMap<String, Movie> movieRatings = new HashMap<String, Movie>();
    
    public MovieManager() {
        UI.initialise();
        
        UI.addTextField("Title / Taitara", this::setTitle);
        UI.addTextField("Genre / Momo", this::setgenre);
        UI.addTextField("Director / Kaitohu", this::setdirector);
        UI.addButton("Like / Matareka", this::setRatingGood);
        UI.addButton("Dislike / Matakawa", this::setRatingBad);
        
        UI.addButton("Submit / Tuku kōrero", this::submit);
        UI.setDivider(0.3);
        UI.setFontSize(20);
    }
    
    private void setRatingGood() {
        rating = true;
    }
    
    private void setRatingBad() {
        rating = false;
    }
    
    private void setTitle(String usertitle) {
        title = usertitle;
    }
    
    private void setgenre(String usergenre) {
        genre = usergenre;
    }
    
    private void setdirector(String userdirector) {
        director = userdirector;
    }

    private void submit(){
        // if statement to check all fields are not empty
        if(title != null && genre != null && director != null) {
            Movie test = new Movie(title, genre, director, rating);
            movieRatings.put(title, test);
            title = null;
            genre = null;
            director = null;
            drawMovie();
        }
    }
    
    private void drawMovie() {
        top = 20;
        UI.clearGraphics();
        int j = 1;
        for (Movie i : movieRatings.values()) {
                UI.drawRect(LEFT, top, WIDTH, HEIGHT);
                UI.drawString(i.getTitle(), LEFT + 10, top + 30);
                UI.drawString("Genre: " + i.getGenre(), LEFT + 10, top + 60);
                UI.drawString("Director: " + i.getDirector(), LEFT + 10, top + 80);
                top += 120;
        }
        
    }
    
    public static void main(String[] args) {
        MovieManager obj = new MovieManager();
    }

}

