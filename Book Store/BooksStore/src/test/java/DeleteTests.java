import io.restassured.http.ContentType;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DeleteTests {

    private static final String BASE_URL = "http://localhost:8080";
    private static final String BOOKS_ENDPOINT = "/api/books";

    @Test
    public void testDeleteSomeBook() {

        String author = "Джеймс Уиттакер";
        Boolean isElectronicBook = false;
        String name = "Как тестируют в Google";
        Integer year = 2014;

        BookData book = new BookData(author, isElectronicBook, name, year);

        SuccessCreate successCreate = given()
                .contentType(ContentType.JSON)
                .body(book)
                .when()
                .post(BASE_URL + BOOKS_ENDPOINT)
                .then()
                .statusCode(201)
                .log().all()
                .extract().as(SuccessCreate.class);

        Integer book_id = successCreate.getBook().getId();

        given()
                .when()
                .delete(BASE_URL + BOOKS_ENDPOINT + "/" + book_id)
                .then()
                .statusCode(200);
    }

    @Test
    public void testDoubleDelete() {

        String author = "Джеймс Уиттакер";
        Boolean isElectronicBook = false;
        String name = "Как тестируют в Google";
        Integer year = 2014;

        BookData book = new BookData(author, isElectronicBook, name, year);

        SuccessCreate successCreate = given()
                .contentType(ContentType.JSON)
                .body(book)
                .when()
                .post(BASE_URL + BOOKS_ENDPOINT)
                .then()
                .statusCode(201)
                .log().all()
                .extract().as(SuccessCreate.class);

        Integer book_id = successCreate.getBook().getId();

        given()
                .when()
                .delete(BASE_URL + BOOKS_ENDPOINT + "/" + book_id)
                .then()
                .statusCode(200);
        given()
                .when()
                .delete(BASE_URL + BOOKS_ENDPOINT + "/" + book_id)
                .then()
                .statusCode(404)
                .body("error", equalTo("Book with id " + book_id + " not found"));
    }
    @Test
    public void testDeleteNonExistentBook() {

        Integer book_id = 99;

        given()
                .when()
                .delete(BASE_URL + BOOKS_ENDPOINT + "/" + book_id)
                .then()
                .statusCode(404);
    }

    @Test
    public void testDeleteALLBook() {

        given()
                .when()
                .delete(BASE_URL + BOOKS_ENDPOINT)
                .then()
                .statusCode(405);
    }
}
