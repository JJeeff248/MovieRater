/* Based on the ecs 100 template
 * Code for Movie Rater
 * Name: SACH
 * Date: 2/07/2020
 */


import ecs100.*;
import java.util.*;
import java.io.*;
import java.awt.Color;
import java.util.ArrayList;


/** 
 * Store all information about a movie
 * Title as String
 * Genre as String
 * Director as String
 * Rating as boolean
 * 
 * @author sach
 * @version 1
 */
public class Movie {
    
    // Create Fields
    private String title;
    private ArrayList<String> genre = new ArrayList<String>();
    private String director;
    private String rating;
    
    /**
     * Initialise fields
     * 
     * @param userTitle String passed in from movieManager
     * @param userGenre ArrayList passed in from movieManager
     * @param userDirector String passed in from movieManager
     * @param userRating String passed in from movieManager
     */
    public Movie(String userTitle, ArrayList userGenre,
                    String userDirector, String userRating) {
        this.title = userTitle;
        this.genre = userGenre;
        this.director = userDirector;
        this.rating = userRating;
    }
    
    /**
     * @return movies title
     */
    public String getTitle() {
        return this.title;
    }
    
    /**
     * @return movies genres
     */
    public ArrayList getGenre() {
        return this.genre;
    }
    
    /**
     * @return movies director
     */
    public String getDirector() {
        return this.director;
    }
    
    /**
     * @return movies rating
     */
    public String getRating() {
        return this.rating;
    }
    
    /**
     * Updates the movies rating
     * @param rate String passed in from movieManager
     */
    public void setRating(String rate) {
        this.rating = rate;
    }
    
}

