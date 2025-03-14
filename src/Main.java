import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

  public static void main(String[] args) {
    String instructions = """
        Essentially, this is the idea.
        Different dates stay in separate rows (column 1)
        Different Marshes stay in separate rows (column 2)
        Different Traps (within marshes and dates) stay in separate rows (column 3
        Different Species stay in separate rows (column 4)
        
        Essentially, all instances of a Spp (species) that are in the same trap, same marsh, and same date should be put in the same row. For all of the rows that get condensed into one row, a new column should be made to the right with the total number of rows that got condensed. For example, if 5 rows all have the same trap#, spp, and date, a new column in the same row should have 5.
        
        You should keep the columns Date, Marsh, Trap#, and Spp. The rest of the rows can be disregarded.
        """;

    BufferedReader reader;
    try {
      // Create csv reader
      reader = Files.newBufferedReader(Paths.get("input.csv"));

      // We want a hashmap keyed off of Date, Marsh, Trap number, species, and species count within
      // Loop through every line creating hashmap
      while (reader.ready()) {
        String entry = reader.readLine();
        String[] splitEntry = entry.split(",");
        TrapEntry trapEntry = new TrapEntry(splitEntry[0], splitEntry[1], splitEntry[2], splitEntry[3]);
        System.out.println(splitEntry[1]);
      }

      reader.close();
    } catch (IOException e) {
      System.out.println("Failed to read input.csv file in repo");
      throw new RuntimeException(e);
    }

  }
}