import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class inputReader {

  // Reads a line of text from a file and converts into an array of strings
  public String[] readLine(String filePath) {
    String data = null;
    // if there is another line of text, scanner scans over it and returns it into data variable 
    try {
      Scanner scanner = new Scanner(new File(filePath));
      if (scanner.hasNextLine()) {
        data = scanner.nextLine();
      }
      scanner.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    // splits line of text into an array of strings, each string within array is a name
    String[] names = data.split(" ");
    return names;
  }

  //testing
  public static void main(String[] args) {
    inputReader ir = new inputReader();
    String[] names = ir.readLine("test-socialnetworks/social-network1.txt");
  }
}
