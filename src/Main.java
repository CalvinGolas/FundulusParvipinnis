import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Main {

  public static void main(String[] args) {
    // This hashmap will contain the trap entries keyed off of composite with number in each
    Map<String, Integer> trapMap = new HashMap<>();

    BufferedReader reader;
    try {
      // Create csv reader
      reader = Files.newBufferedReader(Paths.get("input.csv"));

      // We want a hashmap keyed off of Date, Marsh, Trap number, species, and species count within
      // Loop through every line creating hashmap
      while (reader.ready()) {
        String entry = reader.readLine();
        String[] splitEntry = entry.split(",");
        for (int i = 0; i < splitEntry.length; i++) {
          splitEntry[i] = splitEntry[i].trim();
        }
        TrapEntry trapEntry = new TrapEntry(splitEntry[1], splitEntry[2], splitEntry[3], splitEntry[4]);
        String compositeKey = trapEntry.getCompositeKy();
        trapMap.put(compositeKey, trapMap.getOrDefault(compositeKey, 0) + 1);
      }
      reader.close();
    } catch (IOException e) {
      System.out.println("Failed to read input.csv file in repo");
      throw new RuntimeException(e);
    }

    // now output in new CSV
    try {
      BufferedWriter writer = Files.newBufferedWriter(Paths.get("output.csv"));
      // Start by writing the column names
      writer.write("Date,Marsh,Trap#,Species,Count");
      writer.newLine();

      for (String compositeKey : trapMap.keySet()) {
        TrapEntry trapEntry = TrapEntry.splitCompositeKey(compositeKey);
        int trappedCount = trapMap.get(compositeKey);
        writer.write(trapEntry.date() + "," + trapEntry.marsh() + "," + trapEntry.trapName() + "," + trapEntry.species() + "," + trappedCount);
        writer.newLine();
      }
      writer.close();
    } catch (IOException e) {
      System.out.println("Failed to write to output.csv in repo");
      throw new RuntimeException(e);
    }
  }
}