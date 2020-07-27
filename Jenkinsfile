pipeline{
      agent any
        tools {
        maven 'maven'
        jdk 'jdk8'
       }
      environment{
        ruta='CODIGO/covol-zuul'
    }
      stages{
        stage('Source Checkout'){
          steps {
            git branch: 'master',
            credentialsId: 'Gogs-Jenkins',
            url: 'http://10.99.240.127:3000/FN-COVOL/Zuul.git'
            sh "ls -lat"
          }
        }
      stage('Build'){
        steps{
           sh 'mvn clean'
           sh 'mvn install'
          }
        }
      stage("Test"){
        steps{
           withSonarQubeEnv("sonar-int"){
           sh "mvn sonar:sonar"
           echo 'Running unit tests...'
               sh 'mvn test'
               junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml'
                     }
        }
      }
      stage('Package'){
        steps{
          sh 'id'
          sh 'echo Package'
          sh 'mvn package'
          script {
                 sh 'docker login -u openshift -p eyJhbGciOiJSUzI1NiIsImtpZCI6IiJ9.eyJpc3MiOiJrdWJlcm5ldGVzL3NlcnZpY2VhY2NvdW50Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9uYW1lc3BhY2UiOiJkZWZhdWx0Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9zZWNyZXQubmFtZSI6ImplbmtpbnMtdG9rZW4tY3ZqOTIiLCJrdWJlcm5ldGVzLmlvL3NlcnZpY2VhY2NvdW50L3NlcnZpY2UtYWNjb3VudC5uYW1lIjoiamVua2lucyIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VydmljZS1hY2NvdW50LnVpZCI6ImZjNjc3NzdmLWQ5NjktMTFlOS1hMmY1LTAyZTI2N2NlZTA0OCIsInN1YiI6InN5c3RlbTpzZXJ2aWNlYWNjb3VudDpkZWZhdWx0OmplbmtpbnMifQ.YBwnnPMRuUdBNEqWgZQRbik-9lZ5rn0yw4EL86iUgwDtkAbfslBhPi7DIWgSVu2C3HJFtcxMaeLcFAiTfbX5kbK6EQeJFYh7U2bE7czrUeTaCCcnwbNiC0BLm416EvZccjxElgey_seVTWLa2dZoFg7Nxnh7l3VWC7oTonyrJfjeZA6FVVnFF3QbaPLB3JirQDIkXztOoTXLfUgElYE7MWtc-kjHH6H4O3pY916xhSOTZ3vZQKkbDEX2MTlqEbdzcKtohBavJTve9OiR_nY5TZORDWKhzjpLsQZ-wdIpEtoz_qSjWDjqaQQil_pc-iJohKymqKiWNxurctqgIiXVPw docker-registry-default.cnh.dev.cloudb.sat.gob.mx'
                 sh 'docker build -t ms-sat-covol-zuul:v1.0 . --tls-verify=false'  
                 sh 'echo termino build'
                 sh 'docker image ls'
            }
        }     
    }
    stage('Deploy'){
      steps{
        sh 'echo Deploy to OpenShift'
        script {
                sh 'docker tag ms-sat-covol-zuul:v1.0 docker-registry-default.cnh.dev.cloudb.sat.gob.mx/covol/ms-sat-covol-zuul:v1.0'
                sh 'docker push docker-registry-default.cnh.dev.cloudb.sat.gob.mx/covol/ms-sat-covol-zuul:v1.0'
              }
             }
            }
      }
      post{
        success {
            emailext attachLog: true, body: """<p> Se ejecutó la Tarea: ${env.JOB_NAME} - Núm. Ejecución :${env.BUILD_NUMBER} <br/><br/>  Se adjunta el log de ejecución.<br><br/>Puede validar el log también en el siguiente link <a href="${env.BUILD_URL}">${env.JOB_NAME}</a><br><br>Este correo se mandó automáticamente mediante la herramienta Jenkins <br><br/>Team AVL<br/>avl.atencion@sat.gob.mx</p>""", compressLog: true, replyTo: 'avl.atencion@sat.gob.mx',
            subject: "[Jenkins] ${currentBuild.projectName} #${env.BUILD_NUMBER} Estado : ${currentBuild.currentResult}!", to: 'jesus.covarrubias@sat.gob.mx,ivan.hernandez@sat.gob.mx,eric.rodriguez@sat.gob.mx,claudia.vazquez@sat.gob.mx,avl.versiones&compilaciones@sat.gob.mx,karina.romero@syesoftware.com,cgutierrez@syesoftware.com'
            }
        failure {
            emailext attachLog: true, body: """<p> Se ejecutó la Tarea: ${env.JOB_NAME} - Núm. Ejecución :${env.BUILD_NUMBER} <br/><br/>  Se adjunta el log de ejecución.<br><br/>Puede validar el log también en el siguiente link <a href="${env.BUILD_URL}">${env.JOB_NAME}</a><br><br>Este correo se mandó automáticamente mediante la herramienta Jenkins <br><br/>Team AVL<br/>avl.atencion@sat.gob.mx</p>""", compressLog: true, replyTo: 'avl.atencion@sat.gob.mx',
            subject: "[Jenkins] ${currentBuild.projectName} #${env.BUILD_NUMBER} Estado : ${currentBuild.currentResult}!", to: 'jesus.covarrubias@sat.gob.mx,ivan.hernandez@sat.gob.mx,eric.rodriguez@sat.gob.mx,claudia.vazquez@sat.gob.mx,avl.versiones&compilaciones@sat.gob.mx,karina.romero@syesoftware.com,cgutierrez@syesoftware.com'
            }
        }
}