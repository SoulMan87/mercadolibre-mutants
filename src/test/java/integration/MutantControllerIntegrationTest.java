package integration;

import com.soulrebel.ml.MutantsApplication;
import com.soulrebel.ml.model.DnaResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = MutantsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MutantControllerIntegrationTest {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Test
    public void testIntegrationServiceMutantDnaIsMutant() {

        var dnaResponse = new DnaResponse();

        String[] dna = { "AAAA", "CAGT", "TTTT", "AGAA" };

        dnaResponse.setDna(dna);

        var entity = new HttpEntity<>(dnaResponse, headers);

        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort(), HttpMethod.POST, entity, String.class);

        HttpStatus status = response.getStatusCode();

        Assert.assertEquals(HttpStatus.OK, status);

    }

    @Test
    public void testIntegrationServiceMutantDnaIsNotMutant() {

        var dnaResponse = new DnaResponse();

        String[] dna = { "ATGC", "CAGT", "TTCT", "AGAA" };

        dnaResponse.setDna(dna);

        var entity = new HttpEntity<>(dnaResponse, headers);

        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort(), HttpMethod.POST, entity, String.class);

        HttpStatus status = response.getStatusCode();

        Assert.assertEquals(HttpStatus.FORBIDDEN, status);
    }


    @Test
    public void testIntegrationServiceMutantDnaBadFormat() {

        var dnaResponse = new DnaResponse();

        String[] dna = { "aTC", "C$GT", "*TcT", "A?AA" };

        dnaResponse.setDna(dna);

        var entity = new HttpEntity<>(dnaResponse, headers);

        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort(), HttpMethod.POST, entity, String.class);

        HttpStatus status = response.getStatusCode();

        Assert.assertEquals(HttpStatus.BAD_REQUEST, status);
    }

    private String createURLWithPort() {
        return "http://localhost:" + port + "/mutants";
    }
}
