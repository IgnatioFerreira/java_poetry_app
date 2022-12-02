import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class lekke {

    public static void main(String[] args) throws IOException {
        browseByLetterAndGetPoetLinks("https://mypoeticside.com/z-browse");
    }

    public static void browseByLetterAndGetPoetLinks(String url) throws IOException {
        new File("C:\\Users\\IFerreira\\Desktop\\java_poetry_app\\poems\\"
                + url.substring(url.lastIndexOf("/"), url.lastIndexOf("-")))
                .mkdirs();

        PrintStream out = new PrintStream(new FileOutputStream("output.txt"));
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

        PrintStream out2 = new PrintStream(new FileOutputStream("processed.txt"));
        System.setOut(out2);

        File file = new File("C:\\Users\\IFerreira\\Desktop\\java_poetry_app\\output.txt");
        Scanner sc = new Scanner(file);
        String line = "";
        while (sc.hasNextLine()) {
            line = sc.nextLine();

            if (line.matches(".+list-poems.+")) {

                while (line.indexOf("href") > 0) {

    
                    line = line.substring(line.indexOf("href=\"https://mypoeticside.com/poets/") + 6);
                    String poetLink = line.substring(0, line.indexOf("\">"));
                    System.out.println(poetLink);
    
                }



            }

        }
        sc.close();

    }

    public static void getListOfPoemLinksFromAuthor(String url) throws IOException {

        // new File("C:\\Users\\IFerreira\\Desktop\\java_poetry_app\\poems\\" + letter)
        // .mkdirs();

        new File("C:\\Users\\IFerreira\\Desktop\\java_poetry_app\\poems\\" + url.substring(url.lastIndexOf("/")))
                .mkdirs();

        PrintStream out = new PrintStream(new FileOutputStream("output.txt"));
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

        PrintStream out2 = new PrintStream(new FileOutputStream("processed.txt"));
        System.setOut(out2);

        File file = new File("C:\\Users\\IFerreira\\Desktop\\java_poetry_app\\output.txt");
        Scanner sc = new Scanner(file);
        String line = "";
        boolean inrange = false;
        while (sc.hasNextLine()) {
            line = sc.nextLine();

            if (inrange & line.matches(".+</div>.+")) {
                break;
            }
            if (inrange & line.matches(".+<ul.+")) {
                System.out.println(line);
            }

            if (line.matches(".+class=\"container-list-poems-2\".+")) {
                inrange = true;
            }
        }
        sc.close();

        PrintStream out3 = new PrintStream(new FileOutputStream("list_of_poem_links.txt"));
        System.setOut(out3);

        file = new File("C:\\Users\\IFerreira\\Desktop\\java_poetry_app\\processed.txt");
        sc = new Scanner(file);
        line = "";

        while (sc.hasNextLine()) {
            line = sc.nextLine();

            while (line.indexOf("href") > 0) {
                System.setOut(out3);

                line = line.substring(line.indexOf("href") + 6);
                String poemlink = line.substring(0, line.indexOf("\">"));
                System.out.println(line);
                System.out.println(poemlink);

                getPoemAndPrintToFile("https:" + poemlink, url.substring(url.lastIndexOf("/")));
            }

        }
        sc.close();

    }

    public static void getPoemAndPrintToFile(String url, String dir) throws IOException {
        // https://stackoverflow.com/questions/1994255/how-to-write-console-output-to-a-txt-file
        PrintStream out = new PrintStream(new FileOutputStream("output.txt"));
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

        String filename = url.substring(url.lastIndexOf("/"));
        PrintStream out2 = new PrintStream(new FileOutputStream("poems\\" + dir + "\\" + filename + ".txt"));
        System.setOut(out2);

        // https://www.geeksforgeeks.org/different-ways-reading-text-file-java/
        File file = new File("C:\\Users\\IFerreira\\Desktop\\java_poetry_app\\output.txt");
        Scanner sc = new Scanner(file);
        String line = "";
        boolean inrange = false;
        while (sc.hasNextLine()) {
            line = sc.nextLine();
            if (inrange) {
                if (line.matches(".+</div>.+")) {
                    break;
                }
                System.out.println(line);
            }
            if (line.matches(".+class=\"poem-entry\".+")) {
                inrange = true;
            }
        }
        sc.close();
    }
}