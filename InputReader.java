import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class inputReader {

  // Reads a line of text from a file and converts into an array of strings
  public ArrayList<String> readLine(String filePath) {
    ArrayList<String> lines = new ArrayList<>();
    
    // if there is another line of text, scanner scans over it and returns it into data variable 
    try {
      Scanner scanner = new Scanner(new File(filePath));
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        lines.add(line);
      }
      scanner.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    return lines;
  }

  public String[] formatLine(ArrayList<String> data, int i) {
    // splits line of text into an array of strings, each string within array is a name
    String[] names = data.get(i).split(" ");
    return names;
  }

  //testing
  public static void main(String[] args) {
    inputReader ir = new inputReader();
    ArrayList<String> data = ir.readLine("test-socialnetworks/social-network1.txt");
    System.out.println(data);
  }
}
