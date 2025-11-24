package org.iplacex;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String getUser(String id) {
        String name = userRepository.findById(id);
        if (name == null) {
            return "Usuario no encontrado";
        }
        return "Hola, " + name;
    }
}