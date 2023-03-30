import io.restassured.http.ContentType;
import org.junit.Assert;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

    public class GetTests {

        private static final String BASE_URL = "http://localhost:8080";
        private static final String BOOKS_ENDPOINT = "/api/books";

        @Test
        public void testGetAllBooks() {
            given()
                    .when()
                    .get(BASE_URL + BOOKS_ENDPOINT)
                    .then()
                    .statusCode(200)
                    .body(notNullValue());
        }

        @Test
        public void testGetSomeBook() {
            Integer book_id = 2;
            SuccessCreate successCreate = given()
                    .when()
                    .contentType(ContentType.JSON)
                    .get(BASE_URL + BOOKS_ENDPOINT + "/" + book_id)
                    .then()
                    .statusCode(200)
                    .log().all()
                    .extract().as(SuccessCreate.class);

            Assert.assertEquals(book_id, successCreate.getBook().getId());
        }

        @Test
        public void testGetNonExistentBook() {
            Integer book_id = 9999;
            given()
                    .when()
                    .contentType(ContentType.JSON)
                    .get(BASE_URL + BOOKS_ENDPOINT + "/" + book_id)
                    .then()
                    .statusCode(404)
                    .body("error", equalTo("Book with id " + book_id + " not found"));
        }
}
