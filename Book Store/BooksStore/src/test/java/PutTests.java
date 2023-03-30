import io.restassured.http.ContentType;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class PutTests {

    private static final String BASE_URL = "http://localhost:8080";
    private static final String BOOKS_ENDPOINT = "/api/books";

    @Test
    public void testUpdateBook() {

        Integer book_id = 1;
        Integer year = 2015;
        String putRequest = "{" +
                "\"author\":" + "\"Джеймс Уиттакер\", " +
                "\"isElectronicBook\":" + true + ", " +
                "\"name\":" + "\"Как тестируют в Google\", " +
                "\"year\":" + year +
                "}";

        SuccessCreate successCreate = given()
                .contentType(ContentType.JSON)
                .body(putRequest)
                .when()
                .put(BASE_URL + BOOKS_ENDPOINT + "/" + book_id)
                .then()
                .statusCode(200)
                .log().all()
                .extract().as(SuccessCreate.class);

        assertEquals(book_id, successCreate.getBook().getId());
        assertEquals("Джеймс Уиттакер", successCreate.getBook().getAuthor());
        assertEquals(true, successCreate.getBook().getElectronicBook());
        assertEquals("Как тестируют в Google", successCreate.getBook().getName());
        assertEquals(year, successCreate.getBook().getYear());
    }

    @Test
    public void testUpdateNonexistentBook() {

        Integer book_id = 99;
        String putRequest = "{" +
                "\"author\":" + "\"Джеймс Уиттакер\", " +
                "\"isElectronicBook\":" + true + ", " +
                "\"name\":" + "\"Как тестируют в Google\", " +
                "\"year\":" + 2015 +
                "}";

        given()
                .contentType(ContentType.JSON)
                .body(putRequest)
                .when()
                .put(BASE_URL + BOOKS_ENDPOINT + "/" + book_id)
                .then()
                .statusCode(404);
    }

    @Test
    public void testUpdateWithoutName() {

        Integer book_id = 1;
        String putRequest = "{" +
                "\"author\":" + "\"Джеймс Уиттакер\", " +
                "\"isElectronicBook\":" + true + ", " +
                "\"year\":" + 2015 +
                "}";

        given()
                .contentType(ContentType.JSON)
                .body(putRequest)
                .when()
                .put(BASE_URL + BOOKS_ENDPOINT + "/" + book_id)
                .then()
                .statusCode(400);
    }

    @Test
    public void testUpdateWithoutAuthor() {

        Integer book_id = 1;
        String putRequest = "{" +
                "\"isElectronicBook\":" + true + ", " +
                "\"name\":" + "\"Как тестируют в Google\", " +
                "\"year\":" + 2015 +
                "}";

        given()
                .contentType(ContentType.JSON)
                .body(putRequest)
                .when()
                .put(BASE_URL + BOOKS_ENDPOINT + "/" + book_id)
                .then()
                .statusCode(400);
    }

    @Test
    public void testUpdateWithoutYear() {

        Integer book_id = 1;
        String putRequest = "{" +
                "\"author\":" + "\"Джеймс Уиттакер\", " +
                "\"isElectronicBook\":" + true + ", " +
                "\"name\":" + "\"Как тестируют в Google\", " +
                "}";

        given()
                .contentType(ContentType.JSON)
                .body(putRequest)
                .when()
                .put(BASE_URL + BOOKS_ENDPOINT + "/" + book_id)
                .then()
                .statusCode(400);
    }

    @Test
    public void testUpdateWithoutType() {

        Integer book_id = 1;
        String putRequest = "{" +
                "\"author\":" + "\"Джеймс Уиттакер\", " +
                "\"name\":" + "\"Как тестируют в Google\", " +
                "\"year\":" + 2015 +
                "}";

        given()
                .contentType(ContentType.JSON)
                .body(putRequest)
                .when()
                .put(BASE_URL + BOOKS_ENDPOINT + "/" + book_id)
                .then()
                .statusCode(400);
    }
}
