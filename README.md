
![Generic badge](https://img.shields.io/badge/Java-17-red.svg) ![Generic badge](https://img.shields.io/badge/Quarkus-2.13.3-blue.svg)   ![Generic badge](https://img.shields.io/badge/Maven-3.8.*-<COLOR>.svg)

#  Poverty Indicator Project

Este projeto tem finalidade de oferecer serviços REST para consulta do indicador econômico que avalia a quantidade de pessoas em situação de extrema pobreza no mundo, vivendo com até $ 1,90/dia (dólar). 
Viste o swagger em: `/swagger-ui.html` para os serviços oferecidos.

## Build (manual):
Após clone do projeto rodar a aplicação com o comando:

    ./mvnw quarkus:dev 

## Build (docker):
Executar os comandos abaixo:

    docker build -t povertyapi .
    
    docker run -p 8080:8080 --network host povertyapi:latest 

## Sonarqube
Para análise do sonar, subir o sonarcube local com o comando:

    docker run --rm -d  -p 9000:9000/tcp sonarqube:latest

Será preciso configurar o sonar com projeto manual no endereço: http://localhost:9000
Project key: poverty-indicator

Rodar os seguinte comandos para análise do sonar:

    mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent install

    mvn -Dsonar.login=<TOKEN-GERADO-NA-CONFIG-ACIMA> org.jacoco:jacoco-maven-plugin:report sonar:sonar

Após verificar a análise do sonar em [http://localhost:9000](http://localhost:9000)

