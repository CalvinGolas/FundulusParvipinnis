public record TrapEntry(String date, String marsh, String trapName, String species) {
  static String COMPOSITE_KEY_FORMATTED_STRING = "%s,%s,%s,%s";

  public String getCompositeKy() {
    return String.format(COMPOSITE_KEY_FORMATTED_STRING, date, marsh, trapName, species);
  }
  public static TrapEntry splitCompositeKey(String compositeKey) {
    String[] splitKey = compositeKey.split(",");
    return new TrapEntry(splitKey[0], splitKey[1], splitKey[2], splitKey[3]);
  }
}
