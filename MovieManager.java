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
        UI.addTextField("Title", this::setTitle);
        UI.addTextField("Genre", this::setgenre);
        UI.addTextField("Director", this::setdirector);
        //UI.addButton("Good", this::setRating);
        //UI.addButton("Bad", this::setRating);
        UI.addButton("Submit", this::submit);
    }
    
    public void setTitle(String usertitle) {
        title = usertitle;
    }
    
    public void setgenre(String usergenre) {
        genre = usergenre;
    }
    
    public void setdirector(String userdirector) {
        director = userdirector;
    }
    
    public void submit(){
        // if statement to check all fields are not empty
        if(title != null && genre != null && director != null) {
            Movie test = new Movie(title, genre, director);
            title = null;
            genre = null;
            director = null;
        }
    }
    
    public static void main(String[] args) {
        MovieManager obj = new MovieManager();
    }

}

