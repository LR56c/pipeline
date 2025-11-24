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
                sh 'mvn clean package -DskipTests'
            }
        }
        stage('Unit Tests') {
            steps {
                echo 'Ejecutando Pruebas Unitarias...'
                sh 'mvn test'
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
                sh 'mvn verify -DskipUnitTests'
            }
        }
    }
}