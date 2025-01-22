The Drone
This application manages drones and their operations, including loading medications, checking availability, and monitoring drone battery levels. It is built using Spring Boot and an H2 in-memory database.

Prerequisites
- Java 17 or later
- Maven (for build and dependency management)
- A REST client (e.g., Postman) for testing APIs

Build and Run Instructions
1. Build the Application
- Right-click on the project and select "Build Module 'drone'"
2. Run the Application
- Run the app using: Spring Boot: DroneApplication

The application will start at:
**http://localhost:8080**

Test Instructions
1. Preloaded Data
- The application uses an H2 in-memory database with preloaded data for testing purposes. On application startup, schema.sql and data.sql files load the database schema and dummy data.

Dummy Data Example:
1. Drones:
- DRONE123: Lightweight, 500g max, 75% battery, IDLE
- DRONE456: Heavyweight, 1000g max, 85% battery, IDLE
- DRONE789: Middleweight, 700g max, 90% battery, IDLE
2. Medications:
- Paracetamol_500 (500g, code: PARA_500)
- Ibuprofen_300 (300g, code: IBU_300)
- Aspirin_200 (200g, code: ASP_200)

Testing with Postman
- You can test each endpoint using the provided Postman collection. Ensure that the application is running at http://localhost:8080 before sending requests.

Available Endpoints
 Drone
1. POST /drone 
Register a new drone
  - Request Body: Drone object
  - Response: Created drone details
2. GET /drone 
Get a list of all drones
  - Response: List of all drones
3. GET /drone/{id} 
Get details of a drone by its ID
  - Path Variable: id (Drone ID)
  - Response: Drone details
4. PUT /drone/{id}
Update a drone's details
  - Path Variable: id (Drone ID)
  - Request Body: Updated Drone object
  - Response: Updated drone details
5. DELETE /drone/{id}
Delete a drone by its ID
  - Path Variable: id (Drone ID)
  - Response: No Content

Medication
1. POST /medication
Register a new medication
  - Request Body: Medication object
  - Response: Created medication details
2. GET /medication
Get a list of all medications
  - Response: List of all medications
3. GET /medication/{id}
Get details of a medication by its ID
  - Path Variable: id (Medication ID)
  - Response: Medication details
4. PUT /medication/{id}
Update medication details
  - Path Variable: id (Medication ID)
  - Request Body: Updated Medication object
  - Response: Updated medication details
5. DELETE /medication/{id}
Delete a medication by its ID
  - Path Variable: id (Medication ID)
  - Response: No Content

Drone Medication
1. POST /droneMedication/{droneId}/load
Load a drone with medications
  - Path Variable: droneId (Drone ID)
  - Request Body: List of medication IDs
  - Response: Updated drone with loaded medications
2. GET /droneMedication/{droneId}/medications
Get medications loaded onto a drone
  - Path Variable: droneId (Drone ID)
  - Response: List of medications loaded onto the drone
3. GET /droneMedication/available
Get available drones for loading
  - Response: List of available drones
4. GET /droneMedication/{droneId}/battery
Check the battery level of a drone
  - Path Variable: droneId (Drone ID)
  - Response: Drone's battery level (percentage)
5. POST /droneMedication/{droneId}/complete-delivery
Complete the medication delivery and reduce battery level
  - Path Variable: droneId (Drone ID)
  - Response: Success or error message indicating the delivery status
