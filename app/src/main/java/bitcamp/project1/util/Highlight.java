package bitcamp.project1.util;

public class Highlight {

  public static void menuHighlight(String menuTitle, String color) {
    String boldAnsi = "\033[1m";
    String redAnsi = "\033[31m";
    String blueAnsi = "\033[34m";
    String yellowAnsi = "\033[33m";
    String pinkAnsi = "\033[35m";

    String resetAnsi = "\033[0m";

    String colorAnsi;
    switch (color) {
      case "pink":
        colorAnsi = pinkAnsi;
        break;
      case "blue":
        colorAnsi = blueAnsi;
        break;
      case "yellow":
        colorAnsi = yellowAnsi;
        break;
      default:
        colorAnsi = redAnsi;
        break;
    }

    System.out.printf("%s%s%s\n", (boldAnsi + redAnsi), "***************************", resetAnsi);
    System.out.printf("%s%s%s\n", (boldAnsi + colorAnsi), menuTitle, resetAnsi);
    System.out.printf("%s%s%s\n", (boldAnsi + redAnsi), "***************************", resetAnsi);
  }


}
