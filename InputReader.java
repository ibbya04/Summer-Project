import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class InputReader {

  // Reads all the text from a file and adds it to the ArrayList lines 
  public ArrayList<String> readLines(String filePath) {
    ArrayList<String> lines = new ArrayList<>();

    // if there is another line of text, scanner scans over it and adds it to lines ArrayList
    // catches exception from scanner
    try {
      Scanner scanner = new Scanner(new File(filePath));

      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        lines.add(line);
      }
      scanner.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred. The file was not found");
      e.printStackTrace();
    }
    return lines;
  }

  // splits line of text into an array of strings, each string within array is a name
  public String[] formatLine(ArrayList<String> data, int i) {
    String[] names = data.get(i).split(" ");
    return names;
  }
}
