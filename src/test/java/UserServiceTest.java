
import org.iplacex.UserRepository;
import org.iplacex.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Test
    public void saludoExitoso() {
        when(userRepository.findById("1")).thenReturn("Juan");
        String resultado = userService.getUser("1");
        assertEquals("Hola, Juan", resultado);
    }
}