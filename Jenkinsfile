pipeline {
    agent any
    tools {
        jdk 'jdk8'
        maven 'maven_3_6_3'
    }
    environment {
        //once you sign up for Docker hub, use that user_id here
        registry = "qubedprince/concrete-compressive-strength-api-image"
        //- update your credentials ID after creating credentials for connecting to Docker Hub
        registryCredential = '	dockerHub'
    }
    stages {
        stage ('Version'){
            steps {
                sh 'mvn --version'
            }
        }
        stage ('Dependency'){
            steps {
                sh 'mvn dependency:tree'
            }
        }
        stage ('Test') {
            steps {
               sh 'mvn test -Dspring.profiles.active=test'
            }
        }
        stage ('Verify and LoadTest'){
            steps {
               sh 'mvn clean verify -Dspring.profiles.active=test'
            }
         }

         stage("Vulnerability - Dependency Check") {
             steps{
                 sh 'mvn dependency-check:check'
                 dependencyCheckPublisher pattern: 'target/dependency-check-report.xml'
             }
        }

        stage ('Code Analysis -SAST Initiated'){
            steps {
                withSonarQubeEnv('QubedSonar') {
                    sh 'mvn sonar:sonar -Dspring.profiles.active=staging -Dsonar.dependencyCheck.jsonReportPath=target/dependency-check-report.json -Dsonar.dependencyCheck.xmlReportPath=target/dependency-check-report.xml -Dsonar.dependencyCheck.htmlReportPath=target/dependency-check-report.html'
                }
            }
        }

       stage("Code Analysis - SAST Quality gate check") {
            steps {
                waitForQualityGate abortPipeline: true
            }
        }

        stage('Run Vulnerability Scan - Docker Image') {
          steps {
            sh 'grype qubedprince/concrete-compressive-strength-api-image:latest --scope AllLayers --fail-on=critical'
          }
        }

        stage ('Build Package') {
            steps {
                sh 'mvn package -Dspring.profiles.active=staging'
            }
        }

	    stage('Build Docker image') {
	      steps{
            script {
              dockerImage = docker.build registry
            }
	      }
	    }

	     // Uploading Docker images into Docker Hub
	    stage('Upload Docker Image to Repository') {
	     steps{
             script {
                docker.withRegistry( '', registryCredential ) {
                dockerImage.push()
                }
            }
	      }
	    }


        stage ('Deploy on Server & Cleanup'){
            steps{
                sh 'ansible-playbook deploy.yml'
                sh "docker rmi ${registry}"
            }
        }

    }
    post {
        always {
          emailext body: '''Good Day Engineer,
          Loki issued a report as follows:
          $PROJECT_NAME - Build # $BUILD_NUMBER - $BUILD_STATUS.
          Check console output at $BUILD_URL to view the results.''', subject: '$PROJECT_NAME - Build # $BUILD_NUMBER - $BUILD_STATUS!', to: 'no1416165h@students.nust.ac.zw'
        }

        failure {
           telegramSend(message: "*JOB* : *${env.JOB_NAME}*\n*Report* : ${env.BUILD_TAG} \n*Branch*: main \n*Build* : ${env.BUILD_ID} \n*Status*: _FAILED_ \n*Vist* : ${env.BUILD_URL} for more informantion\n", chatId: 717316992 )
        }
         success {
            telegramSend(message: "*JOB* : *${env.JOB_NAME}*\n*Report* : ${env.BUILD_TAG} \n*Branch*: main \n*Build* : ${env.BUILD_ID} \n*Status*: _SUCCESS_ \n*Vist* : ${env.BUILD_URL} for more informantion\n", chatId: 717316992)

        }
         unsuccessful {
            telegramSend(message: "*JOB* : *${env.JOB_NAME}*\n*Report* : ${env.BUILD_TAG} \n*Branch*: main \n*Build* : ${env.BUILD_ID} \n*Status*: _UNSTABLE_ \n*Vist* : ${env.BUILD_URL} for more informantion\n", chatId: 717316992)

        }
    }
}