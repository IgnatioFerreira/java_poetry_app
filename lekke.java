import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class lekke {

    public static void main(String[] args) throws IOException {
        // getPoemAndPrintToFile("https://mypoeticside.com/show-classic-poem-33069",
        // "i","isaac-watts", "Hymn 93");


        for (char letter = 'j'; letter <= 'z'; letter++) {
        browseByLetterAndGetPoetLinks("https://mypoeticside.com/" + letter +
        "-browse");
        }

        // browseByLetterAndGetPoetLinks("https://mypoeticside.com/b-browse");

        // getListOfPoemLinksFromAuthor("https://mypoeticside.com/poets/isaac-rosenberg-poems",
        // "i", "isaac-rosenberg");
    }

    public static void browseByLetterAndGetPoetLinks(String url) throws IOException {
        new File("C:\\Users\\User\\java_poetry_app\\poems\\"
                + url.substring(url.lastIndexOf("/"), url.lastIndexOf("-")))
                .mkdirs();

        PrintStream out = new PrintStream(new FileOutputStream("output_browseByLetterAndGetPoetLinks.txt"));
        System.setOut(out);

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
        System.out.println(content);

        PrintStream out2 = new PrintStream(new FileOutputStream("processed_browseByLetterAndGetPoetLinks.txt"));
        System.setOut(out2);

        File file = new File("C:\\Users\\User\\java_poetry_app\\output_browseByLetterAndGetPoetLinks.txt");
        Scanner sc = new Scanner(file);
        String line = "";
        boolean go = false;
        System.out.println(go);
        if (!url.equals("https://mypoeticside.com/i-browse")) {
            go = true;
        }
        System.out.println(go);
        while (sc.hasNextLine()) {
            line = sc.nextLine();

            if (line.matches(".+list-poems.+")) {

                while (line.indexOf("href") > 0) {
                    System.setOut(out2);

                    line = line.substring(line.indexOf("href=\"https://mypoeticside.com/poets/") + 6);
                    String poetLink = line.substring(0, line.indexOf("\">"));
                    String poetName = poetLink.substring(poetLink.lastIndexOf("/") + 1);
                    if (poetLink.indexOf("-poems") > -1) {
                        poetName = poetLink.substring(poetLink.lastIndexOf("/") + 1, poetLink.indexOf("-poems"));
                    }
                    System.out.println(poetName);
                    if (poetName.equals("isaac-taylor") ) {
                        go = true;
                    }
                    if (go) {
                        getListOfPoemLinksFromAuthor(poetLink,
                                url.substring(url.lastIndexOf("/"), url.lastIndexOf("-")),
                                poetName);
                    }
                }

            }

        }
        sc.close();

    }

    public static void getListOfPoemLinksFromAuthor(String url, String letter, String poetName) throws IOException {

        // new File("C:\\Users\\User\\java_poetry_app\\poems\\" + letter)
        // .mkdirs();

        new File("C:\\Users\\User\\java_poetry_app\\poems\\" + letter + "\\" + poetName)
                .mkdirs();

        PrintStream out = new PrintStream(new FileOutputStream("2\\output_getListOfPoemLinksFromAuthor.txt"));
        System.setOut(out);

        // https://stackoverflow.com/questions/31462/how-to-fetch-html-in-java
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
        System.out.println(content);

        PrintStream out2 = new PrintStream(new FileOutputStream("2\\processed_getListOfPoemLinksFromAuthor.txt"));
        System.setOut(out2);

        File file = new File("C:\\Users\\User\\java_poetry_app\\2\\output_getListOfPoemLinksFromAuthor.txt");
        Scanner sc = new Scanner(file);
        String line = "";
        boolean inrange = false;
        while (sc.hasNextLine()) {
            line = sc.nextLine();
            if (line.matches(".+class=\"container-list-poems-2\".+")) {
                inrange = true;
            }
            if (inrange & line.matches(".+</div>.+")) {
                break;
            }//TODO
            if (inrange & line.matches(".+<ul.+")) {
                if (!line.matches(".+/ul.+")) {
                    System.out.print(line);
                    
                } else {
                    
                    System.out.println(line);
                }
                while ( !line.matches(".+/ul.+")) {
                    line = sc.nextLine();
                    System.out.print(line);
                }
                System.out.println();
            }

           
        }
        sc.close();

        PrintStream processed_lines_out = new PrintStream(new FileOutputStream("2\\processed_lines_getListOfPoemLinksFromAuthor.txt"));
        System.setOut(processed_lines_out);


        file = new File("C:\\Users\\User\\java_poetry_app\\2\\processed_getListOfPoemLinksFromAuthor.txt");
        sc = new Scanner(file);
        line = "";

        while (sc.hasNextLine()) {
            line = sc.nextLine();

            while (line.indexOf("href") > 0) {
                System.out.println(line);
                line = line.substring(line.indexOf("href") + 6);
                String poemlink = line.substring(0, line.indexOf("\">"));
                String poemName = line.substring(line.indexOf("\">") + 2, line.indexOf("</a>"));
                getPoemAndPrintToFile("https:" + poemlink, letter, poetName, poemName);
            }

        }
        sc.close();

    }

    public static void getPoemAndPrintToFile(String url, String letter, String poet, String poemName)
            throws IOException {
        // https://stackoverflow.com/questions/1994255/how-to-write-console-output-to-a-txt-file
        PrintStream out = new PrintStream(new FileOutputStream("1\\output_getPoemAndPrintToFile.txt"));
        System.setOut(out);

        // https://stackoverflow.com/questions/31462/how-to-fetch-html-in-java
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
        System.out.println(content);

        PrintStream poemProccessing = new PrintStream(
                new FileOutputStream("1\\poemProccessing.txt"));
        System.setOut(poemProccessing);

        // https://www.geeksforgeeks.org/different-ways-reading-text-file-java/
        File file = new File("C:\\Users\\User\\java_poetry_app\\1\\output_getPoemAndPrintToFile.txt");
        Scanner sc = new Scanner(file);
        String line = "";
        boolean inrange = false;
        while (sc.hasNextLine()) {
            line = sc.nextLine();
            if (inrange) {
                if (line.matches(".+</div.+")) {

                    break;
                }
                System.out.println(line);
            }
            if (line.matches(".+class=\"poem-entry\".+")) {
                inrange = true;
            }
        }
        sc.close();

        PrintStream out2 = new PrintStream(
                new FileOutputStream(
                        "poems\\" + letter + "\\" + poet + "\\" + poemName.replaceAll("[?<>*|\"/:\\\\]", "") + ".txt"));
        System.setOut(out2);

        file = new File("C:\\Users\\User\\java_poetry_app\\1\\poemProccessing.txt");
        sc = new Scanner(file);
        line = "";
        inrange = false;
        System.out.println(poemName);
        System.out.println();

        line = sc.nextLine();
        line = line.substring(line.indexOf("<p>") + 3);
        if (line.indexOf("<br />") > -1) {
            line = line.substring(0, line.indexOf("<br />"));
        }
        if (line.indexOf("</p>") > -1) {
            line = line.substring(0, line.indexOf("</p>"));
        }
        System.out.println(line);
        while (sc.hasNextLine()) {
            line = sc.nextLine();
            if (line.indexOf("<br />") > -1) {
                line = line.substring(0, line.indexOf("<br />"));
            }
            if (line.indexOf("</p>") > -1) {
                line = line.substring(0, line.indexOf("</p>"));
            }

            System.out.println(line);
        }
        sc.close();

    }
}