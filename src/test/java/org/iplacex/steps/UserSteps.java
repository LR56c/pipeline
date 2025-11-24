package org.iplacex.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.iplacex.UserRepository;
import org.iplacex.UserService;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class UserSteps {
    private UserRepository userRepository = Mockito.mock(UserRepository.class);
    private UserService userService = new UserService(userRepository);
    private String resultadoObtenido;

    @Given("que existe el usuario con id {string} y nombre {string}")
    public void que_existe_el_usuario_con_id_y_nombre(String id, String nombre) {
        when(userRepository.findById(id)).thenReturn(nombre);
    }

    @Given("que no existe un usuario con id {string}")
    public void que_no_existe_un_usuario_con_id(String id) {
        when(userRepository.findById(id)).thenReturn(null);
    }
    @When("solicito el saludo para el id {string}")
    public void solicito_el_saludo_para_el_id(String id) {
        resultadoObtenido = userService.getUser(id);
    }
    @Then("debería recibir el mensaje {string}")
    public void deberia_recibir_el_mensaje(String mensajeEsperado) {
        assertEquals(mensajeEsperado, resultadoObtenido);
    }
}