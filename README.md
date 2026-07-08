# Climalert - Sistema de Monitoreo Climático y Alertas

| Nombre | Legajo | Correo |
| :--- | :--- | :--- |
| Nicolas Fonseca Giudici | 155.837-7 | nicofonsecagiudici@frba.utn.edu.ar |

---

## Descripción del Proyecto
Este proyecto consiste en el diseño y desarrollo de **Climalert**, un servicio autónomo (sin interfaz gráfica) desarrollado en **Spring Boot**. Su objetivo principal es conectarse periódicamente a un proveedor meteorológico externo, procesar los datos obtenidos y enviar notificaciones automáticas por correo electrónico cuando se detecten condiciones climáticas críticas.

### Reglas de Negocio (Primera Iteración)
* **Condición de Alerta:** Se genera una alerta si la temperatura es mayor a 35°C y la humedad es superior al 60%.
* **Frecuencia de Consulta:** El sistema obtiene datos climáticos cada 5 minutos y los almacena localmente.
* **Frecuencia de Procesamiento:** Cada 1 minuto se analiza la última información disponible para evaluar si corresponde disparar una alerta.
* **Destinatarios de Alertas:** En caso de emergencia, se envía un correo con el detalle completo del clima a:
  * admin@clima.com
  * emergencies@clima.com
  * meteorologia@clima.com

---

## Configuración del Entorno

Para que la aplicación funcione correctamente, es necesario configurar las siguientes variables de entorno en el IDE antes de ejecutarla:

* **WEATHER_API_KEY**: Clave de acceso provista por WeatherAPI para consumir el endpoint `/current.json`.
* **SENDGRID_API_KEY**: Clave de API de SendGrid utilizada para la infraestructura del envío de correos electrónicos.

---

## Arquitectura y Diseño

### Diagrama de Clases
El diseño de clases y la estructura de la solución se encuentran en la siguiente ruta del proyecto:

`climalert/src/main/java/docs/Diagrama de Clases.puml`

---

## Tecnologías Utilizadas
* Java
* Spring Boot
* WeatherAPI (Integración REST externa)
* SendGrid (Servicio de mensajería)
