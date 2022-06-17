package integration;

import com.soulrebel.ml.MutantsApplication;
import com.soulrebel.ml.model.DnaResponse;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MutantsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTest {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Test
    public void testIntegrationResponse() throws JSONException {

        testIntegrationServiceMutantCDnaIsMutant();
        testIntegrationServiceMutantDnaIsNotMutant();
        testIntegrationServiceMutantDnaBadFormat();


        var entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/stats"),
                HttpMethod.GET, entity, String.class);

        String expected = "{\"countMutantDna\":1,\"countHumanDna\":1,\"ratio\":1.0}";

        JSONAssert.assertEquals(expected, response.getBody(), false);

    }

    public void testIntegrationServiceMutantCDnaIsMutant() {

        var dnaResponse = new DnaResponse();

        String[] dna = { "AAAA", "CAGT", "TTTT", "AGAA" };

        dnaResponse.setDna(dna);

        var entity = new HttpEntity<>(dnaResponse, headers);

        restTemplate.exchange(createURLWithPort("/mutants"), HttpMethod.POST, entity, String.class);

    }

    public void testIntegrationServiceMutantDnaIsNotMutant() {

        var dnaResponse = new DnaResponse();

        String[] dna = { "ATGC", "CAGT", "TTCT", "AGAA" };

        dnaResponse.setDna(dna);

        var entity = new HttpEntity<>(dnaResponse, headers);

        restTemplate.exchange(createURLWithPort("/mutants"), HttpMethod.POST, entity, String.class);
    }

    public void testIntegrationServiceMutantDnaBadFormat() {

        var dnaResponse = new DnaResponse();

        String[] dna = { "aTC", "C$GT", "*TcT", "A?AA" };

        dnaResponse.setDna(dna);

        var entity = new HttpEntity<>(dnaResponse, headers);

        restTemplate.exchange(createURLWithPort("/mutants"), HttpMethod.POST, entity, String.class);
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
