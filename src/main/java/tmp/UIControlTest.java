package tmp;

import ltg.commons.SimpleMQTTClient;
import org.apache.log4j.Logger;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PVector;
import processing.video.Movie;
import vialab.SMT.SMT;
import vialab.SMT.TouchDraw;
import vialab.SMT.TouchSource;


@SuppressWarnings("ALL")
public class UIControlTest extends PApplet {


    public static PFont helveticaFont = null;
    private static SimpleMQTTClient sc;
    int buttonWidth = 100;
    int buttonHeight = buttonWidth;
    int buttonStartX = 20;
    int buttonStartY = buttonStartX;
    int whiteColor = color(255);
    int greenButtonColor = color(35, 147, 70);
    int yellowButtonColor = color(244, 208, 63);
    private PVector target1;
    private PVector target2;
    private PVector point2;

    public static void main(String args[]) {

        //System.loadLibrary("/resources/gstreamer");
       System.setProperty( "jna.library.path", "lib/gstreamer/macosx64/" );
        System.setProperty( "gstreamer.library.path", "lib/gstreamer/macosx64/" );
        System.setProperty( "gstreamer.plugin.path", "lib/gstreamer/macosx64/plugins/" );

//        Field fieldSysPath = null;
//        try {
//            fieldSysPath = ClassLoader.class.getDeclaredField( "sys_paths" );
//            fieldSysPath.setAccessible( true );
//            fieldSysPath.set( null, null );
//
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//
//
//        System.loadLibrary("*");
        Logger logger = Logger.getLogger(UIControlTest.class.getName());
        logger.setLevel(org.apache.log4j.Level.ALL);

        String[] appletArgs = new String[]{"tmp.UIControlTest"};
        if (args != null) {
            PApplet.main(concat(appletArgs, args));
        } else {
            PApplet.main(appletArgs);
        }
    }


    public void doInit() {
//        helveticaFont = loadFont(Resources.getResource("Roboto-Light-48.vlw").getPath());
        // MQTTPipe.getInstance();
    }

    @Override
    public void setup() {

        thread("doInit");
        final int screen_width = 1200;
        final int screen_height = 800;
        size(800, 800, SMT.RENDERER);
        SMT.init(this, TouchSource.AUTOMATIC);
        // SMT.debug = true;
        SMT.setTouchDraw(TouchDraw.NONE);
        SMT.setWarnUnimplemented(false);
        SMT.debug = false;


        String link = "https://drive.google.com/open?id=0B8M0y5b1JU46VFYzSVU5VUpTams&authuser=0";
        String local = "/Users/aperritano/Desktop/IMG_0023.mov";
        String local2 = "/Users/aperritano/Desktop/transit.mov";
        VideoZone vz = new VideoZone("vz", 100,100, 640, 360, local2);
        SMT.add(vz);

//        ButtonZone b = new ButtonZone("er", 100,100,100,100);
//        SMT.add(b);b

    }


    @Override
    public void draw() {
        background(150);
        fill(0);
        text(round(frameRate) + "fps, # of zones: " + SMT.getZones().length, width / 2, 10);
        //text("color zone: " + colorZone.toString() + " newZone " + newZone.toString(), 250, 20);
    }

    public void movieEvent(Movie m) {
        //System.out.println("READING");
        m.read();
    }


}
