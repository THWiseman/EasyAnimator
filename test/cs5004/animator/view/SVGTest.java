package cs5004.animator.view;

import cs5004.animator.model.Canvas;
import cs5004.animator.model.CanvasImpl;
import cs5004.animator.util.AnimationReader;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * A JUnit test for the SVGView class.
 */
public class SVGTest {
    Canvas canvas1;
    Canvas canvas2;

    public SVGTest() {
    }

    @Before
    public void setUp() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader((new FileReader("resources/testText")));
        } catch (FileNotFoundException e) {
            System.out.println("No file passed");
        }
        canvas1 = AnimationReader.parseFile(reader, new CanvasImpl.Builder());
        BufferedReader reader2 = null;
        try {
            reader2 = new BufferedReader((new FileReader("resources/testText2.txt")));
        } catch (FileNotFoundException e) {
            System.out.println("No file passed");
        }
        canvas2 = AnimationReader.parseFile(reader2, new CanvasImpl.Builder());
    }

    @Test
    public void testBadGo() {
        SVGView svg = new SVGView(canvas1);
        try {
            svg.go(-5);
            fail("An exception should have been thrown");
        } catch (IllegalArgumentException e) {
            //Test passes
        }
        try {
            svg.go(-5, "badTest.svg");
            fail("An exception should have been thrown");
        } catch (IllegalArgumentException e) {
            //Test passes
        }
    }

    @Test
    public void testText() {
        StringBuilder testText = new StringBuilder();
        SVGView svg = new SVGView(testText, canvas1);
        svg.go(20);
        assertEquals("<svg width=\"1000\" height=\"1000\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n"
                + "   <rect id=\"disk1\" x=\"190\" y=\"180\" width=\"20\" height=\"30\" fill=\"rgb(0,49,90)\" visibility=\"visible\" >\n"
                + "      <animate attributeType=\"xml\" begin=\"0.05s\" dur=\"0.45s\" attributeName=\"x\" from=\"190\" to=\"80\" fill=\"freeze\" />\n"
                + "      <animate attributeType=\"xml\" begin=\"0.05s\" dur=\"0.45s\" attributeName=\"y\" from=\"180\" to=\"180\" fill=\"freeze\" />\n"
                + "      <animate attributeName=\"fill\" attributeType=\"fill\" begin=\"0.5s\" dur=\"2.0s\" from=\"rgb(20, 100, 120)\" to=\"rgb(60, 70, 80)\" fill=\"freeze\" />\n"
                + "      <animate attributeType=\"xml\" begin=\"0.5s\" dur=\"2.0s\" attributeName=\"x\" from=\"200\" to=\"20\" fill=\"freeze\" />\n"
                + "      <animate attributeType=\"xml\" begin=\"0.5s\" dur=\"2.0s\" attributeName=\"y\" from=\"200\" to=\"30\" fill=\"freeze\" />\n"
                + "      <animate attributeType=\"xml\" begin=\"0.5s\" dur=\"2.0s\" attributeName=\"width\" from=\"30\" to=\"40\" fill=\"freeze\" />\n"
                + "      <animate attributeType=\"xml\" begin=\"0.5s\" dur=\"2.0s\" attributeName=\"height\" from=\"40\" to=\"50\" fill=\"freeze\" />\n"
                + "   </rect>\n"
                + "</svg>", testText.toString());
    }

    @Test
    public void testTextEllipse() {
        StringBuilder testText = new StringBuilder();
        SVGView svg = new SVGView(testText, canvas2);
        svg.go(20);
        assertEquals("<svg width=\"1000\" height=\"1000\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n"
                + "   <ellipse id=\"disk1\" cx=\"190\" cy=\"180\" rx=\"20\" ry=\"30\" fill=\"rgb(0,49,90)\" visibility=\"visible\" >\n"
                + "      <animate attributeType=\"xml\" begin=\"0.05s\" dur=\"0.45s\" attributeName=\"x\" from=\"190\" to=\"80\" fill=\"freeze\" />\n"
                + "      <animate attributeType=\"xml\" begin=\"0.05s\" dur=\"0.45s\" attributeName=\"y\" from=\"180\" to=\"180\" fill=\"freeze\" />\n"
                + "      <animate attributeName=\"fill\" attributeType=\"fill\" begin=\"0.5s\" dur=\"2.0s\" from=\"rgb(20, 100, 120)\" to=\"rgb(60, 70, 80)\" fill=\"freeze\" />\n"
                + "      <animate attributeType=\"xml\" begin=\"0.5s\" dur=\"2.0s\" attributeName=\"x\" from=\"200\" to=\"20\" fill=\"freeze\" />\n"
                + "      <animate attributeType=\"xml\" begin=\"0.5s\" dur=\"2.0s\" attributeName=\"y\" from=\"200\" to=\"30\" fill=\"freeze\" />\n"
                + "      <animate attributeType=\"xml\" begin=\"0.5s\" dur=\"2.0s\" attributeName=\"width\" from=\"30\" to=\"40\" fill=\"freeze\" />\n"
                + "      <animate attributeType=\"xml\" begin=\"0.5s\" dur=\"2.0s\" attributeName=\"height\" from=\"40\" to=\"50\" fill=\"freeze\" />\n"
                + "   </ellipse>\n"
                + "</svg>", testText.toString());
    }

    @Test
    public void testFile() throws IOException {
        SVGView svg = new SVGView(canvas1);
        svg.go(20, "viewTest.svg");
        Path filePath = Path.of("viewTest.svg");
        String test = "";
        try {
            test = Files.readString(filePath);
        } catch (IOException e) {
            fail("exception should not have been thrown");
            System.out.println("No file passed");
        }
        assertEquals("<svg width=\"1000\" height=\"1000\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n"
                + "   <rect id=\"disk1\" x=\"190\" y=\"180\" width=\"20\" height=\"30\" fill=\"rgb(0,49,90)\" visibility=\"visible\" >\n"
                + "      <animate attributeType=\"xml\" begin=\"0.05s\" dur=\"0.45s\" attributeName=\"x\" from=\"190\" to=\"80\" fill=\"freeze\" />\n"
                + "      <animate attributeType=\"xml\" begin=\"0.05s\" dur=\"0.45s\" attributeName=\"y\" from=\"180\" to=\"180\" fill=\"freeze\" />\n"
                + "      <animate attributeName=\"fill\" attributeType=\"fill\" begin=\"0.5s\" dur=\"2.0s\" from=\"rgb(20, 100, 120)\" to=\"rgb(60, 70, 80)\" fill=\"freeze\" />\n"
                + "      <animate attributeType=\"xml\" begin=\"0.5s\" dur=\"2.0s\" attributeName=\"x\" from=\"200\" to=\"20\" fill=\"freeze\" />\n"
                + "      <animate attributeType=\"xml\" begin=\"0.5s\" dur=\"2.0s\" attributeName=\"y\" from=\"200\" to=\"30\" fill=\"freeze\" />\n"
                + "      <animate attributeType=\"xml\" begin=\"0.5s\" dur=\"2.0s\" attributeName=\"width\" from=\"30\" to=\"40\" fill=\"freeze\" />\n"
                + "      <animate attributeType=\"xml\" begin=\"0.5s\" dur=\"2.0s\" attributeName=\"height\" from=\"40\" to=\"50\" fill=\"freeze\" />\n"
                + "   </rect>\n"
                + "</svg>", Files.readString(filePath));
    }

    @Test
    public void testFileNoSpeed() throws IOException {
        SVGView svg = new SVGView(canvas1);
        svg.go("viewTest.svg");
        Path filePath = Path.of("viewTest.svg");
        String test = "";
        try {
            test = Files.readString(filePath);
        } catch (IOException e) {
            fail("exception should not have been thrown");
            System.out.println("No file passed");
        }
        assertEquals("<svg width=\"1000\" height=\"1000\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n"
            + "   <rect id=\"disk1\" x=\"190\" y=\"180\" width=\"20\" height=\"30\" fill=\"rgb(0,49,90)\" visibility=\"visible\" >\n"
            + "      <animate attributeType=\"xml\" begin=\"1.0s\" dur=\"9.0s\" attributeName=\"x\" from=\"190\" to=\"80\" fill=\"freeze\" />\n"
            + "      <animate attributeType=\"xml\" begin=\"1.0s\" dur=\"9.0s\" attributeName=\"y\" from=\"180\" to=\"180\" fill=\"freeze\" />\n"
            + "      <animate attributeName=\"fill\" attributeType=\"fill\" begin=\"10.0s\" dur=\"40.0s\" from=\"rgb(20, 100, 120)\" to=\"rgb(60, 70, 80)\" fill=\"freeze\" />\n"
            + "      <animate attributeType=\"xml\" begin=\"10.0s\" dur=\"40.0s\" attributeName=\"x\" from=\"200\" to=\"20\" fill=\"freeze\" />\n"
            + "      <animate attributeType=\"xml\" begin=\"10.0s\" dur=\"40.0s\" attributeName=\"y\" from=\"200\" to=\"30\" fill=\"freeze\" />\n"
            + "      <animate attributeType=\"xml\" begin=\"10.0s\" dur=\"40.0s\" attributeName=\"width\" from=\"30\" to=\"40\" fill=\"freeze\" />\n"
            + "      <animate attributeType=\"xml\" begin=\"10.0s\" dur=\"40.0s\" attributeName=\"height\" from=\"40\" to=\"50\" fill=\"freeze\" />\n"
            + "   </rect>\n"
            + "</svg>", Files.readString(filePath));
    }

}