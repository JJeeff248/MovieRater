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
    private String rating;
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
        UI.addTextField("Genre / Momo", this::setGenre);
        UI.addTextField("Director / Kaitohu", this::setDirector);
        UI.addButton("Like / Matareka", this::setRatingGood);
        UI.addButton("Dislike / Matakawa", this::setRatingBad);
        
        UI.addButton("Submit / Tuku kōrero", this::submit);
        UI.setDivider(0.3);
        UI.setFontSize(20);
    }
    
    private void setRatingGood() {
        rating = "Like";
    }
    
    private void setRatingBad() {
        rating = "Dislike";
    }
    
    private void setTitle(String usertitle) {
        title = usertitle.trim();
    }
    
    private void setGenre(String usergenre) {
        genre = usergenre.trim();
    }
    
    private void setDirector(String userdirector) {
        director = userdirector.trim();
    }

    private void submit(){
        // if statement to check all fields are not empty
        if(title != null && genre != null && director != null) {
            Movie test = new Movie(title, genre, director, rating);
            movieRatings.put(title, test);
            title = null;
            rating = "";
            drawMovie();
        } else {
            UI.println("Please fill in all the \nrequired feilds");
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
                if (i.getRating() == "Like") {
                    UI.drawImage("images/thumbsUp.png", WIDTH-(LEFT*2), HEIGHT/2+top,
                                50, 50);
                } else if (i.getRating() == "Dislike") {
                    UI.drawImage("images/thumbsDown.png", WIDTH-(LEFT*2), HEIGHT/2+top,
                                50, 50);
                }
                top += 120;
        }
        
    }
    
    public static void main(String[] args) {
        MovieManager obj = new MovieManager();
    }

}

