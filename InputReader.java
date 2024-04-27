import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class InputReader {
  private String filePath;

  public String readLine(String filePath) {
    String data = null;
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
    return data;
  }
  // String[] data = line.split(" ");
}
