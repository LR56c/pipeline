@acceptance
Feature: Verificación de Servicio de Saludos
  Como usuario del sistema
  Quiero consultar mi saludo de bienvenida
  Para verificar que el sistema me reconoce correctamente

  Scenario: Saludo exitoso de usuario existente
    Given que existe el usuario con id "1" y nombre "Juan"
    When solicito el saludo para el id "1"
    Then debería recibir el mensaje "Hola, Juan"

  Scenario: Validación de usuario no encontrado
    Given que no existe un usuario con id "99"
    When solicito el saludo para el id "99"
    Then debería recibir el mensaje "Usuario no encontrado"