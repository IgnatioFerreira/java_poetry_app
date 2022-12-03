
public class test {

  public static void main(String[] args) {
    String poetLink = "https://mypoeticside.com/poets/bh-fairchild";
    String poetName = poetLink.substring(poetLink.lastIndexOf("/") + 1);
    if (poetLink.indexOf("-poems") > -1) {
      poetName = poetLink.substring(poetLink.lastIndexOf("/") + 1, poetLink.indexOf("-poems"));
    }

    System.out.println(poetName);

  }

}