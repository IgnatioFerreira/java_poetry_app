package v2;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

// C:\\Users\\User\\java_poetry_app\\v2\\v2.java
public class v2 {

    public static void main(String[] args) throws IOException {
        // getPoets();
        // getPoems("https://mypoeticside.com/poets/zoe-akins-poems");
        // printPoem("https://mypoeticside.com/show-classic-poem-561", 999999);
        iteratePrint();
    }

    public static void getPoets() throws IOException {
        PrintStream poetsOutput = new PrintStream(new FileOutputStream("v2\\poets.txt"));
        System.setOut(poetsOutput);

        for (char letter = 'a'; letter <= 'z'; letter++) {
            String url = "https://mypoeticside.com/" + letter + "-browse";

            String content = null;
            URLConnection connection = null;
            try {
                connection = new URL(url).openConnection();
                Scanner scanner = new Scanner(connection.getInputStream());
                scanner.useDelimiter("\\Z");
                content = scanner.next();
                scanner.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            int indexStart = content.indexOf("list-poems");
            int indexEnd = content.indexOf("</div>", indexStart);
            String htmlListOfPoets = content.substring(indexStart, indexEnd);
            while (htmlListOfPoets.indexOf("https", 5) > -1) {
                htmlListOfPoets = htmlListOfPoets
                        .substring(htmlListOfPoets.indexOf("https://mypoeticside.com/poets/", 5));
                String poetURL = htmlListOfPoets.substring(0, htmlListOfPoets.indexOf("\">"));
                System.out.println(poetURL);
                // getPoems(poetURL);
            }
        }
    }

    public static void getPoems(String poetURL) throws IOException {
        File poemsOutput = new File("v2\\poems.txt");
        FileWriter fr = new FileWriter(poemsOutput, true);

        String content = null;
        URLConnection connection = null;
        try {
            connection = new URL(poetURL).openConnection();
            Scanner scanner = new Scanner(connection.getInputStream());
            scanner.useDelimiter("\\Z");
            content = scanner.next();
            scanner.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        // System.out.println(content);

        int indexStart = content.indexOf("<ul", content.indexOf("container-list-poems"));
        int indexEnd = content.indexOf("</div>", indexStart);
        String htmlListOfPoems = content.substring(indexStart, indexEnd);

        while (htmlListOfPoems.indexOf("//mypoeticside.com", 5) > -1) {
            htmlListOfPoems = htmlListOfPoems
                    .substring(htmlListOfPoems.indexOf("//mypoeticside.com", 5));
            String poemLink = htmlListOfPoems.substring(0, htmlListOfPoems.indexOf("\">"));
            // System.out.println();
            fr.write("https:" + poemLink);
            fr.write("\n");
        }
        fr.close();
    }

    public static void printPoem(String poemURL, int fileNumber) throws IOException {
        PrintStream poemOutput = new PrintStream(new FileOutputStream("v2\\poems\\" + fileNumber + ".txt"));
        System.setOut(poemOutput);

        String content = null;
        URLConnection connection = null;
        try {
            connection = new URL(poemURL).openConnection();
            Scanner scanner = new Scanner(connection.getInputStream());
            scanner.useDelimiter("\\Z");
            content = scanner.next();
            scanner.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        int indexStart = content.indexOf("title-content\">");
        int indexEnd = content.indexOf("</h3>", indexStart);
        String poetName = content.substring(indexStart + 15, indexEnd);
        System.out.println(poetName);

        indexStart = content.indexOf("class=\"title-poem\">");
        indexEnd = content.indexOf("</h2>", indexStart);
        String poemName = content.substring(indexStart + 19, indexEnd);
        System.out.println(poemName);
        System.out.println();

        indexStart = content.indexOf("<p>", content.indexOf("class=\"poem-entry\""));
        indexEnd = content.indexOf("</p>", indexStart);
        String poem = content.substring(indexStart + 3, indexEnd);
        poem = poem.replace("<br />", "");
        System.out.println(poem);
    }

    public static void iteratePrint() throws IOException {
        File poemOutputLog = new File("v2\\poemOutputLog.txt");
        
        File file = new File("C:\\Users\\User\\java_poetry_app\\v2\\poems.txt");
        Scanner sc = new Scanner(file);
        
        int i = 0;
        String line = null;
        while (sc.hasNextLine()) {
            FileWriter fr = new FileWriter(poemOutputLog, true);
            fr.write(Integer.toString(i) + "\n");

            line = sc.nextLine();
            printPoem(line, i++);

            fr.close();
        }
        sc.close();
    }
}
