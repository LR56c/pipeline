
import org.iplacex.UserRepository;
import org.iplacex.UserService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserServiceIT {

    @Test
    public void testIntegrationWithRealObject() {
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