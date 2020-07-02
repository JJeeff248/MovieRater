/* Based on the ecs 100 template
 * Code for Movie Rater
 * Name: SACH
 * Date: 02/07/2020
 */


import ecs100.*;
import java.util.*;
import java.io.*;
import java.awt.Color;


/** Manage all movie objects stored in hashmap
 * and provide a recomendation
 */
public class MovieManager{

    // Create Fields
    private String title;
    private String genre;
    private String director;
    private boolean rating;
    
    public MovieManager() {
        UI.initialise();
        UI.addTextField("Title", this::setTitle);
        UI.addTextField("Genre", this::setgenre);
        UI.addTextField("Director", this::setdirector);
        UI.addButton("Submit", this::submit);
        UI.addButton("Quit", UI::quit);
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
        UI.println(title);
        UI.println(genre);
        UI.println(director);
        // if statement to check all fields are not empty
    }
    
    public static void main(String[] args) {
        MovieManager obj = new MovieManager();
    }

}

