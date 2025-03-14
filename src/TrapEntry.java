public record TrapEntry(String date, String marsh, String trapName, String species) {
  public String getCompositeKy() {
    return date + marsh + trapName + species;
  }
}
