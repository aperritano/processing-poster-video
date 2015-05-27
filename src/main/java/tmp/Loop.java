import processing.core.PApplet;
import processing.video.Movie;


public class Loop extends PApplet {

/**
 * Loop. 
 * 
 * Shows how to load and play a QuickTime movie file.  
 *
 */



Movie movie;

public void setup() {

  size(640, 360);
  background(0);
  // Load and play the video in a loop
  movie = new Movie(this, "/Users/aperritano/Desktop/transit.mov");
  movie.loop();
}

public void movieEvent(Movie m) {
  m.read();
}

public void draw() {
  //if (movie.available() == true) {
  //  movie.read(); 
  //}
  image(movie, 0, 0, width, height);
}
  static public void main(String[] passedArgs) {

    System.setProperty( "jna.library.path", "lib/gstreamer/macosx64/" );
    System.setProperty( "gstreamer.library.path", "lib/gstreamer/macosx64/" );
    System.setProperty( "gstreamer.plugin.path", "lib/gstreamer/macosx64/plugins/" );

    String[] appletArgs = new String[] { "Loop" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
