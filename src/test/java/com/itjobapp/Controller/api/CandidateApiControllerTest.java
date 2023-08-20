package com.itjobapp.Controller.api;

import com.itjobapp.Controller.dto.CandidateDTO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CandidateApiControllerTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    public void testGetCandidates() {
        given()
                .when()
                .get("/api/candidates")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON);
    }

    @Test
    public void testCreateCandidate() {
        CandidateDTO candidateDTO = new CandidateDTO();
        candidateDTO.setFirstName("John");
        candidateDTO.setLastName("Doe");
        candidateDTO.setEmail("john.doe@example.com");
        // ... set other fields

        given()
                .contentType(ContentType.JSON)
                .body(candidateDTO)
                .when()
                .post("/api/candidates")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("firstName", equalTo("John"))
                .body("lastName", equalTo("Doe"))
                .body("email", equalTo("john.doe@example.com"));
    }

    @Test
    public void testGetCandidateProfile() {
        String candidateEmail = "john.doe@example.com";

        given()
                .pathParam("candidateEmail", candidateEmail)
                .when()
                .get("/api/candidates/{candidateEmail}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("email", equalTo(candidateEmail));
    }

    @Test
    public void testHireCandidate() {
        String candidateEmail = "john.doe@example.com";

        given()
                .pathParam("candidateEmail", candidateEmail)
                .when()
                .post("/api/candidates/hire/{candidateEmail}")
                .then()
                .statusCode(200);
    }

    @Test
    public void testGetCandidateProfileImage() {
        String candidateEmail = "john.doe@example.com";

        given()
                .pathParam("candidateEmail", candidateEmail)
                .when()
                .get("/api/candidates/image/{candidateEmail}")
                .then()
                .statusCode(200)
                .contentType(ContentType.BINARY);
    }
}
