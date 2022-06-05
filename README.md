# Concrete Compressive Strength API

![nust_logo](logo_nust_png.png)

![developer_image](developer_shape.png)


This project imports tensorflow generated model for predicting the strength of concrete.

This is Repository for the purpose for an academic research project for IME at NUST
with AI using Tensorflow.

Please Take Note the Project was compiled by: DeveloperPrince (Prince Kudzai Maposa N01416165H) for practice purposes

## Requirements

Before you install the necessary frameworks you need to ensure that your machine has a minimum of 4GB of RAM, CPU that supports AVX and an additional hardware requirement of NVIDA graphics card which supports CUDA (which is an added advantage in computing).
 
1. Java 8 
2. maven 3.6.3
3. Docker
4. docker compose cli
5. grype (optional)
6. Jenkins (optional)
7. pre-commit (optional)

We assume the audience reading this already or knows how to set up java and maven on their machine (Win/Linux)
## Run the project

In order to run the project it is neccessary to connect to the internet initially

1. Import Packages

```bash
mvn dependency:tree
``` 

2. Run project

```bash
mvn spring-boot:run
```

3. Access Swagger Documentation

```bash
http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/
```

## Build a docker image and Run Container

In order to build a docker image run, this may be considered an additional step to prepare project for deployment.

1. Build Project 

```bash
mvn package
```

2. Build image
```bash
docker build -t qubedprince/concrete-compressive-strength-api-image .
```

3 Run Docker Container

```bash
docker run -d --name ccst-api -p 8080:8080 qubedprince/concrete-compressive-strength-api-image
```

#### Contact

Please take note of the following contact details for further assistance

![developer_close_shape](developer_shape3.png)

>Cellphone/Mobile Number: +263786808538/+263714272770
>Email address: princekudzaimaposa94@gmail.com