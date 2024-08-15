# Sistema de Gestión de Viajes 🚀✈️

Este proyecto es un completo sistema de gestión de viajes desarrollado con microservicios, lo que permite una arquitectura escalable, flexible y robusta.  Se compone de varios módulos interconectados que gestionan distintos aspectos del viaje, como la reserva de vuelos, alojamiento y gestión de usuarios.

![Microservices Architecture](https://media.giphy.com/media/26tn33aiTi1jkl6H6/giphy.gif) 

## Características Clave ✨

* **Arquitectura de Microservicios:** Divide el sistema en componentes independientes, facilitando el desarrollo, despliegue y mantenimiento.
* **Escalabilidad:** Permite escalar individualmente los microservicios según la demanda, optimizando el uso de recursos.
* **Resiliencia:** La falla de un microservicio no afecta al sistema completo, garantizando una alta disponibilidad.
* **Flexibilidad:** Facilita la integración de nuevas funcionalidades y tecnologías sin impactar en otros componentes.
* **Gestión de Vuelos:** Ofrece funcionalidades para la búsqueda, reserva y gestión de vuelos.
* **Gestión de Alojamiento:** Permite buscar, reservar y gestionar alojamientos.
* **Gestión de Usuarios:**  Gestiona la autenticación, autorización y perfiles de usuario.
* **Eureka Server:**  Servicio de descubrimiento que permite a los microservicios registrarse y localizarse entre sí.
* **API Gateway:**  Punto único de entrada para todas las solicitudes al sistema, simplificando la comunicación cliente-servidor.
* **Keycloak:**  Proveedor de identidad y gestión de accesos (IAM) para una autenticación y autorización seguras.
* **RabbitMQ:**  Sistema de mensajería para la comunicación asíncrona entre microservicios, mejorando la eficiencia y la escalabilidad.
* **Docker Compose:**  Facilita la configuración y ejecución de todo el sistema en un entorno dockerizado.

## Módulos del Sistema 🏗️

* **airline-service:**  Microservicio responsable de la gestión de aerolíneas y vuelos.
* **hosting-service:**  Microservicio para la gestión de alojamientos.
* **reservation-service:**  Microservicio para la gestión de reservas.
* **user-service:**  Microservicio para la gestión de usuarios.
* **authentication-service:**  Microservicio que implementa la autenticación y autorización con Keycloak.
* **eureka-service:**  Servicio Eureka para el registro y descubrimiento de microservicios.
* **gateway-service:**  API Gateway para enrutar las solicitudes a los microservicios correspondientes.

## Instalación y Ejecución 🛠️

1. **Clona el repositorio:** `git clone https://github.com/tu-repositorio/sistema-de-viajes.git`
2. **Construye las imágenes Docker:**  `docker-compose build`
3. **Inicia los servicios:**  `docker-compose up -d`

## Uso 🧭

Una vez que los servicios estén en funcionamiento, puedes acceder a la interfaz web a través del API Gateway en `http://localhost:8780`.

## Tecnologías Utilizadas 💻

* Java
* Spring Boot
* Spring Cloud
* Eureka
* API Gateway
* Keycloak
* RabbitMQ
* PostgreSQL
* Docker
* Docker Compose

## Contribuciones 🤝

¡Las contribuciones son bienvenidas! Si tienes alguna sugerencia o mejora, no dudes en crear un fork del repositorio y enviar un pull request.


---

<div align="center">
  <p>Creado con ❤️ por <a href="https://github.com/JohanMorales211" target="_blank">Johan Morales</a></p>
  <img src="https://media.giphy.com/media/SWoSkN6DxTszq/giphy.gif" width="200" alt="Animación de código">
</div>

---

**¡Empieza a gestionar tus viajes de forma eficiente con este sistema de microservicios!** ✨
