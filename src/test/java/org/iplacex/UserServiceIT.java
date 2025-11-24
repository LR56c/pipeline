package org.iplacex;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserServiceIT {

    @Test
    public void saludoExitosoConUsuarioReal() {
        UserRepository realRepo = new UserRepository() {
            @Override
            public String findById(String id) {
                if ("99".equals(id)) return "Admin";
                return null;
            }
        };
        UserService service = new UserService(realRepo);
        String resultado = service.getUser("99");
        assertEquals("Hola, Admin", resultado);
    }
}