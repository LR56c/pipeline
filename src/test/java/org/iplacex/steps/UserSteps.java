package org.iplacex.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserSteps {
    private String resultadoObtenido;
    private final HttpClient client = HttpClient.newHttpClient();

    @Given("que existe el usuario con id {string} y nombre {string}")
    public void que_existe_el_usuario_con_id_y_nombre(String id, String nombre) {
    }

    @Given("que no existe un usuario con id {string}")
    public void que_no_existe_un_usuario_con_id(String id) {
    }

    @When("solicito el saludo para el id {string}")
    public void solicito_el_saludo_para_el_id(String id) throws IOException, InterruptedException {
        String url = "http://localhost:8090/user/" + id;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        resultadoObtenido = response.body();
    }

    @Then("debería recibir el mensaje {string}")
    public void deberia_recibir_el_mensaje(String mensajeEsperado) {
        assertEquals(mensajeEsperado, resultadoObtenido);
    }
}