public class BookData {

    private String author;
    private Integer id;
    private Boolean isElectronicBook;
    private String name;
    private Integer year;

    private String invalidYear;
    private String invalidIsElectronicBook;

    public BookData(String author, Boolean isElectronicBook, String name, Integer year) {
        this.author = author;
        this.isElectronicBook = isElectronicBook;
        this.name = name;
        this.year = year;
    }

    public BookData() {
    }

    public BookData(Integer id) {
        this.id = id;
    }

    public BookData(String author, Boolean isElectronicBook, Integer year) {
        this.author = author;
        this.isElectronicBook = isElectronicBook;
        this.year = year;
    }

    public BookData(String author, Integer id, Boolean isElectronicBook, String name, Integer year) {
        this.author = author;
        this.id = id;
        this.isElectronicBook = isElectronicBook;
        this.name = name;
        this.year = year;
    }

    public BookData(String author, Boolean isElectronicBook, String name, String invalidYear) {
        this.author = author;
        this.isElectronicBook = isElectronicBook;
        this.name = name;
        this.invalidYear = invalidYear;
    }

    public BookData(String author, String name, Integer year, String invalidIsElectronicBook) {
        this.author = author;
        this.name = name;
        this.year = year;
        this.invalidIsElectronicBook = invalidIsElectronicBook;
    }

    public BookData(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public Boolean getIsElectronicBook() {
        return isElectronicBook;
    }

    public String getName() {
        return name;
    }

    public Integer getYear() {
        return year;
    }

    public Integer getId() {
        return id;
    }

    public String getInvalidYear() {
        return invalidYear;
    }

    public String getInvalidIsElectronicBook() {
        return invalidIsElectronicBook;
    }

    @Override
    public String toString() {
        return "{" +
                "\"author\":" + "\"" + author + "\", " +
                "\"isElectronicBook\":" + isElectronicBook + ", " +
                "\"name\":" + "\"" + name + "\", " +
                "\"year\":" + year +
                "}";
    }
}