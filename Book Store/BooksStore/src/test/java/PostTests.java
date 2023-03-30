import io.restassured.http.ContentType;
import org.junit.Assert;
import org.junit.Test;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class PostTests {

    private static final String BASE_URL = "http://localhost:8080";
    private static final String BOOKS_ENDPOINT = "/api/books";

    @Test
    public void testAddBook() {

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

        Assert.assertNotNull(successCreate.getBook().getId());
        assertEquals(author, successCreate.getBook().getAuthor());
        assertEquals(isElectronicBook, successCreate.getBook().getElectronicBook());
        assertEquals(name, successCreate.getBook().getName());
        assertEquals(year, successCreate.getBook().getYear());
    }

    @Test
    public void testAddEmptyBook() {

        BookData book = new BookData();

        given()
                .contentType(ContentType.JSON)
                .body(book)
                .when()
                .post(BASE_URL + BOOKS_ENDPOINT)
                .then()
                .statusCode(400);
    }

    @Test
    public void testAddNamelessBook() {

        String author = "Джеймс Уиттакер";
        Boolean isElectronicBook = false;
        Integer year = 2014;

        BookData book = new BookData(author, isElectronicBook, year);

        given()
                .contentType(ContentType.JSON)
                .body(book)
                .when()
                .post(BASE_URL + BOOKS_ENDPOINT)
                .then()
                .statusCode(400);
    }

    @Test
    public void testAddBookWithId() {

        String author = "Джеймс Уиттакер";
        Boolean isElectronicBook = false;
        String name = "Как тестируют в Google";
        Integer year = 2014;
        Integer id = 1;

        BookData book = new BookData(author, id, isElectronicBook, name, year);

        SuccessCreate successCreate = given()
                .contentType(ContentType.JSON)
                .body(book)
                .when()
                .post(BASE_URL + BOOKS_ENDPOINT)
                .then()
                .statusCode(201)
                .log().all()
                .extract().as(SuccessCreate.class);

        Assert.assertNotEquals(id, successCreate.getBook().getId());
        assertEquals(author, successCreate.getBook().getAuthor());
        assertEquals(isElectronicBook, successCreate.getBook().getElectronicBook());
        assertEquals(name, successCreate.getBook().getName());
        assertEquals(year, successCreate.getBook().getYear());
    }

    @Test
    public void testAddBookOnlyName() {

        Boolean isElectronicBook = false;
        String name = "Как тестируют в Google";


        BookData book = new BookData(name);

        SuccessCreate successCreate = given()
                .contentType(ContentType.JSON)
                .body(book)
                .when()
                .post(BASE_URL + BOOKS_ENDPOINT)
                .then()
                .statusCode(201)
                .log().all()
                .extract().as(SuccessCreate.class);

        Assert.assertNotNull(successCreate.getBook().getId());
        Assert.assertNull(successCreate.getBook().getAuthor());
        assertEquals(isElectronicBook, successCreate.getBook().getElectronicBook());
        assertEquals(name, successCreate.getBook().getName());
        Assert.assertNull(successCreate.getBook().getYear());
    }

    @Test
    public void testBookWithInvalidYear() {

        String author = "Джеймс Уиттакер";
        Boolean isElectronicBook = false;
        String name = "Как тестируют в Google";
        String invalidYear = "twentyfourteen";

        BookData book = new BookData(author, isElectronicBook, name, invalidYear);

        SuccessCreate successCreate = given()
                .contentType(ContentType.JSON)
                .body(book)
                .when()
                .post(BASE_URL + BOOKS_ENDPOINT)
                .then()
                .statusCode(201)
                .log().all()
                .extract().as(SuccessCreate.class);

        Assert.assertNotNull(successCreate.getBook().getId());
        assertEquals(author, successCreate.getBook().getAuthor());
        assertEquals(isElectronicBook, successCreate.getBook().getElectronicBook());
        assertEquals(name, successCreate.getBook().getName());
        Assert.assertNull(successCreate.getBook().getYear());
    }

    @Test
    public void testBookWithInvalidType() {

        String author = "Джеймс Уиттакер";
        String invalidIsElectronicBook = "no";
        String name = "Как тестируют в Google";
        Integer year = 2014;

        BookData book = new BookData(author, name, year, invalidIsElectronicBook);

        SuccessCreate successCreate = given()
                .contentType(ContentType.JSON)
                .body(book)
                .when()
                .post(BASE_URL + BOOKS_ENDPOINT)
                .then()
                .statusCode(201)
                .log().all()
                .extract().as(SuccessCreate.class);

        Assert.assertNotNull(successCreate.getBook().getId());
        assertEquals(author, successCreate.getBook().getAuthor());
        assertEquals(name, successCreate.getBook().getName());
        assertEquals(year, successCreate.getBook().getYear());
        assertEquals(true || false, successCreate.getBook().getElectronicBook());
    }

    @Test
    public void testLongNameBook() {

        String author = "Джеймс Уиттакер";
        Boolean isElectronicBook = false;
        String name = "ОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназвание";
        Integer year = 2014;

        BookData book = new BookData(author, isElectronicBook, name, year);

        given()
                .contentType(ContentType.JSON)
                .body(book)
                .when()
                .post(BASE_URL + BOOKS_ENDPOINT)
                .then()
                .statusCode(400);
    }

    @Test
    public void testLongAuthorName() {

        String author = "ОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназваниеОченьдлинноеназвание";
        Boolean isElectronicBook = false;
        String name = "Как тестируют в Google";
        Integer year = 2014;

        BookData book = new BookData(author, isElectronicBook, name, year);

        given()
                .contentType(ContentType.JSON)
                .body(book)
                .when()
                .post(BASE_URL + BOOKS_ENDPOINT)
                .then()
                .statusCode(400);
    }

    @Test
    public void testAddBookAfterClear() {

        String author = "Джеймс Уиттакер";
        Boolean isElectronicBook = false;
        String name = "Как тестируют в Google";
        Integer year = 2014;

        BookData newBook = new BookData(author, isElectronicBook, name, year);

        List<BookData> books = given()
                .contentType(ContentType.JSON)
                .when()
                .get(BASE_URL + BOOKS_ENDPOINT)
                .then()
                .statusCode(200)
                .extract().body().jsonPath().getList("books", BookData.class);

        for (BookData book : books) {
            given()
                    .when()
                    .delete(BASE_URL + BOOKS_ENDPOINT + "/" + book.getId())
                    .then()
                    .statusCode(200);
        }
        SuccessCreate successCreate = given()
                .contentType(ContentType.JSON)
                .body(newBook)
                .when()
                .post(BASE_URL + BOOKS_ENDPOINT)
                .then()
                .statusCode(201)
                .log().all()
                .extract().as(SuccessCreate.class);

        Assert.assertNotNull(successCreate.getBook().getId());
        assertEquals(author, successCreate.getBook().getAuthor());
        assertEquals(isElectronicBook, successCreate.getBook().getElectronicBook());
        assertEquals(name, successCreate.getBook().getName());
        assertEquals(year, successCreate.getBook().getYear());
    }
}
