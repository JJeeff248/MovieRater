/* Based on the ecs 100 template
 * Code for Movie Rater
 * Name: SACH
 * Date: 02/07/2020
 */


import ecs100.*;
import java.util.*;
import java.io.*;
import java.awt.Color;


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
    
    public MovieManager() {
        UI.initialise();
        UI.addTextField("Title / Taitara", this::setTitle);
        UI.addTextField("Genre / Momo", this::setgenre);
        UI.addTextField("Director / Kaitohu", this::setdirector);
        UI.addButton("Good / Ka pai", this::setRatingGood);
        UI.addButton("Bad / Kiripiro", this::setRatingBad);
        
        UI.addButton("Submit / Tuku kōrero", this::submit);
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
            title = null;
            genre = null;
            director = null;
        }
    }
    
    public static void main(String[] args) {
        MovieManager obj = new MovieManager();
    }

}

