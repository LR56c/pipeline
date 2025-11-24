pipeline {
    agent any
    tools {
        maven 'Maven 3.8.6'
        jdk 'jdk11'
    }

    stages {
        stage('Build') {
            steps {
                echo 'Iniciando etapa de Compilación...'
                bat 'mvn clean package -DskipTests'
            }
        }
        stage('Unit Tests') {
            steps {
                echo 'Ejecutando Pruebas Unitarias...'
                bat 'mvn test'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }
        stage('Integration Tests') {
            steps {
                echo 'Ejecutando Pruebas de Integración...'
                bat 'mvn verify -DskipUnitTests'
            }
        }
        stage('Deploy to Staging') {
            steps {
                echo 'Desplegando contenedor en Staging'
                script {
                    bat "docker stop %CONTAINER_NAME% || exit 0"
                    bat "docker rm %CONTAINER_NAME% || exit 0"
                    bat "docker build -t %IMAGE_NAME%:v%BUILD_NUMBER% ."
                    bat "docker run -d --name %CONTAINER_NAME% -p 8080:8080 %IMAGE_NAME%:v%BUILD_NUMBER%"
                }
            }
        }
        stage('Acceptance Tests') {
            steps {
                echo '--- Ejecutando Pruebas de Aceptacion (Cucumber BDD) ---'
                bat 'mvn test -Dcucumber.filter.tags="@acceptance"'
            }
        }
        stage('Deploy to Production') {
            input {
                message "Staging validado correctamente. ¿Autorizar pase a Producción?"
                ok "Sí, Desplegar"
            }
            steps {
                echo 'Desplegando en produccion'
            }
        }
    }
    post {
        failure {
            echo 'Iniciando Rollback'
        }
        success {
            echo 'Pipeline completado'
        }
    }
}