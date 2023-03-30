import com.fasterxml.jackson.annotation.JsonProperty;

public class SuccessCreate {

    private Book book;

    public Book getBook() {
        return book;
    }

    public static class Book {
        private String author;
        private Integer id;
        private Boolean isElectronicBook;
        private String name;
        private Integer year;

        public Book (@JsonProperty("author") String author,
                             @JsonProperty("id") Integer id,
                             @JsonProperty("isElectronicBook") Boolean isElectronicBook,
                             @JsonProperty("name") String name,
                             @JsonProperty("year") Integer year) {
            this.author = author;
            this.id = id;
            this.isElectronicBook = isElectronicBook;
            this.name = name;
            this.year = year;
        }

        public String getAuthor() {
            return author;
        }

        public Integer getId() {
            return id;
        }

        public Boolean getElectronicBook() {
            return isElectronicBook;
        }

        public String getName() {
            return name;
        }

        public Integer getYear() {
            return year;
        }
    }
}
