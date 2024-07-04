
# Kafka Monorepo

Welcome to the Kafka Monorepo, a versatile repository designed to facilitate the deployment and management of Apache Kafka along with Zookeeper and client applications using Docker Compose and Kubernetes. This setup is intended for both development and production environments, providing flexibility through Docker Compose and Kubernetes deployment options.

## Project Structure

- **`docker-compose.yml`**: Configures Kafka, Zookeeper, and client applications in a comprehensive Dockerized environment, suitable for development and testing.
- **`kafka-docker-compose.yml`**: Specialized Docker Compose file for running Kafka and Zookeeper only, tailored for use alongside Kubernetes clusters.
  
## Features

- **Integrated Kafka Setup**: Includes Kafka brokers and Zookeeper nodes fully configured for immediate use.
- **Client Applications**: Example producers and consumers that demonstrate how to interact with Kafka.
- **Scalability**: Supports scaling of Kafka brokers and Zookeeper nodes using Docker Compose or Kubernetes.
- **CI/CD Pipeline**: Automated build, test, and deployment processes to maintain high-quality code and facilitate continuous deployment.

## Getting Started

### Prerequisites

- Docker and Docker Compose
- Kubernetes cluster (if deploying using Kubernetes) - e.g. minikube, Docker Desktop environment
- kubectl (for Kubernetes deployments)
- Access to a Docker registry (for pushing Docker images)
- Account at Twilio that provides sending and receiving SMS messages (https://www.twilio.com/en-us)

### Preparation
Since it is not out of the box project, you need to do some preparation of template files.
1. First you need to fill consumer-secret.yaml with appropriate data from Twilio account.
2. Then you need to check your network ip (ex. ```ipconfig``` command on Windows / ```ifconfig``` on Linux) and put that IP to kafka-config-map.yaml and kafka-docker-compose.yml (in KAFKA_ADVERTISED_LISTENERS parameter instead of NETWORK_IP). It is necessary because of locally hosted Kubernetes cluster needs to communicate with locally hosted docker compose containers.



### Running with Docker Compose

1. **Full Setup:**
   Launch Kafka, Zookeeper, and client applications:
   ```bash
   docker-compose up -d
   ```

2. **Kafka and Zookeeper Only:**
   For environments where client applications are managed separately:
   ```bash
   docker-compose -f kafka-docker-compose.yml up -d
   ```  
   
   After that run producer and consumer in Kubernetes cluster:
    ```bash
   kubectl apply -f k8s/consumer-secret.yaml
   kubectl apply -f k8s/kafka-config-map.yaml
   kubectl apply -f k8s/consumer-depl.yaml
   kubectl apply -f k8s/producer-depl.yaml
   ```

### Accessing Kafka UI

Access the Kafka UI at `http://localhost:9000` to manage and monitor your Kafka brokers. This interface provides insights into topics, messages, and performance metrics.

## CI/CD Pipeline

This project's CI/CD pipeline, configured through GitHub Actions, includes the following key steps:

1. **Build**: Compile the client applications and build Docker images for all components.
2. **Test**: Execute automated tests to verify functionality and performance.
3. **Docker Push**: Push the Docker images to a configured registry to ensure they're available for deployment.
