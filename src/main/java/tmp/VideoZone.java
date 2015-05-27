package tmp;

//import processing.core.PApplet;
//import processing.video.Movie;
//import vialab.SMT.Touch;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import org.gstreamer.elements.PlayBin2;
import processing.video.Movie;
import vialab.SMT.ButtonZone;
import vialab.SMT.Touch;
import vialab.SMT.Zone;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.Callable;


public class VideoZone extends Zone {
    private final String movieUrl;
    private Movie movie;
    public PlayBin2 playbin;
    private ButtonZone playButton;
    private boolean isPlaying = false;
    private String localUrl;
    private ListeningExecutorService service;

    public VideoZone(String name, int x, int y, int width, int height, String movieUrl) {
        super(name, x, y, width, height);

        this.movieUrl = movieUrl;
        setup();

    }

    public void setup() {

        isPlaying = false;
        playButton = new ButtonZone("bz", 0, getHeight() - 50, getWidth(), 50, "Play") {

            @Override
            public void touchUp(Touch touch) {
                if (!isPlaying) {
                    movie.loop();
                    playButton.setText("Pause");
                } else {
                    movie.jump(0);
                    movie.stop();
                    playButton.setText("Play");
                }
                isPlaying = !isPlaying;
            }
        };
        this.add(playButton);

        movie = new Movie(VideoZone.this.applet, movieUrl);
        movie.play();
        movie.jump(0);
        movie.pause();
        isPlaying = false;
        playButton.setText("Play");

//       service = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());
//
//        Futures.addCallback(downloadMovie(movieUrl), new FutureCallback<File>() {
//            // we want this handler to run immediately after we push the big red button!
//            public void onSuccess(File movieFile) {
//                System.out.println("PATH " + movieFile.toString());
//                movie = new Movie(VideoZone.this.applet, movieFile.getPath());
//                        movie.play();
//                        movie.jump(0);
//                        movie.pause();
//                        isPlaying = false;
//                        playButton.setText("Play");
//            }
//
//            public void onFailure(Throwable thrown) {
//                thrown.printStackTrace();
//            }
//        });
//
//    }
    }


    public ListenableFuture<java.io.File> downloadMovie(String movieUrl) {
        return service.submit(new Callable<java.io.File>() {
            @Override
            public java.io.File call() throws Exception {

                String ext = com.google.common.io.Files.getFileExtension(movieUrl);
                if( ext.isEmpty() )
                    ext = "mov";

                Path tempFile = Files.createTempFile(null, "." + ext);


                org.apache.commons.io.FileUtils.copyURLToFile(new URL(movieUrl), tempFile.toFile());






                return tempFile.toFile();
            }
        });
    }

    @Override
    public void draw() {


        fill(0);
        rect(0,0, getWidth(), getHeight());

        if( movie != null) {

            if( movie.available() ) {
                System.out.println("VideoZone.draw");
            }
            image(movie, 0, 0,getWidth(),getHeight());
        }





//        stroke(0);
//        strokeWeight(2);



    }

    public void movieEvent(Movie m) {
        System.out.println("VideoZone.movieEvent");
        System.out.println("READING ME");
        m.read();

    }


    public String getLocalUrl() {
        return localUrl;
    }

    public void setLocalUrl(String localUrl) {
        this.localUrl = localUrl;
    }
}