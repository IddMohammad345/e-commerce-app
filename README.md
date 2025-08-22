# e-commerce-app

Project Documentation: E-Commerce Application
Introduction
The E-Commerce Application is a microservices-based project designed to handle product management, customer management, order processing, payments, and notifications. It ensures scalability, reliability, and modularity by splitting functionalities into multiple services. The application leverages Spring Boot, Spring Cloud, Kafka, PostgreSQL, MongoDB, and Redis (planned).
1) Project Architecture
•	Client Side: Any frontend (e.g., Angular/React) consuming APIs via Gateway.
•	API Gateway: Spring Cloud Gateway as a single entry point for routing requests to microservices.
•	Microservices: Customer Service, Product Service, Order Service, Payment Service, Notification Service.
•	Databases: PostgreSQL for Orders, Products, and Payments; MongoDB for Customers and Notifications.
•	Messaging: Kafka used for asynchronous communication between services (orders, payments, notifications).
•	Logging: Centralized logging (Logback/Log4j2, planned Splunk integration).
•	Security: OAuth2 / JWT authentication handled by API Gateway integration with Keycloak.
•	Deployment: Docker for containerization, Kubernetes for orchestration, CI/CD with Jenkins/GitHub Actions.
2) Project Flow
1. A customer registers and logs in through the Customer Service via API Gateway.
2. Customers browse products (Product Service).
3. Customers place an order (Order Service validates product availability and customer data).
4. Payment Service processes the payment, publishes events to Kafka.
5. Notification Service consumes Kafka events and sends order/payment confirmation emails.
6. All interactions pass through the API Gateway for security and routing.
3) Use of Kafka
Kafka is used for event-driven communication: Order events trigger Payment, and Payment events trigger Notifications.
4) Use of Redis (Planned)
Redis will be integrated as an in-memory cache for product lookups and frequently accessed customer data.
5) CI/CD Pipeline
•	Version Control: GitHub.
•	Build Tool: Maven for dependency management.
•	CI: Jenkins/GitHub Actions for building, testing, and packaging services.
•	CD: Docker & Kubernetes for automated deployment.
6) Data Layer / Schemas
- Customer Service: MongoDB (Customer, Address).
- Product Service: PostgreSQL (Product, Category).
- Order Service: PostgreSQL (Order, OrderLine).
- Payment Service: PostgreSQL (Payment).
- Notification Service: MongoDB (Notification, Type).
7) REST APIs
•	Customer Service: POST /api/v1/customers, GET /api/v1/customers/{id}, PUT /api/v1/customers, DELETE /api/v1/customers/{id}.
•	Product Service: POST /api/v1/products, GET /api/v1/products/{id}, GET /api/v1/products.
•	Order Service: POST /api/v1/orders, GET /api/v1/orders/{id}, GET /api/v1/orders.
•	Payment Service: POST /api/v1/payments, GET /api/v1/payments/{id}.
•	Notification Service: Internal Kafka consumers for order and payment confirmations.
8) Resume Points
•	Developed microservices using Spring Boot for an E-Commerce platform.
•	Implemented Kafka-based asynchronous communication for orders and payments.
•	Designed and exposed REST APIs for customers, products, orders, and payments.
•	Integrated MongoDB and PostgreSQL for polyglot persistence.
•	Implemented email notifications using Kafka consumers and Spring Mail.
•	Deployed services using Docker and Kubernetes with CI/CD pipelines.
9) Interview Talking Points
This project is a microservices-based e-commerce platform. I worked on Customer, Product, Order, and Payment modules. We used Kafka for asynchronous event handling (e.g., when an order is placed, it triggers a payment event, which then triggers a notification). For persistence, we used PostgreSQL and MongoDB. I also contributed to implementing CI/CD pipelines using Jenkins and containerized deployments with Docker and Kubernetes.
