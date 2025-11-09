# star-songs
Access and Update a Collection of Celestial Themed Music

## Architecture

This project consists of two microservices:
- **songdata**: Java/Spring Boot REST API backend (port 8086)
- **webui**: React frontend + Spring Boot BFF proxy (port 8080)

The webui uses React with Vite for the frontend, served by Spring Boot which proxies API requests to songdata.

## Development

### Run projects from command line

Start the backend service:
```bash
songdata> ./gradlew bootRun
```

Start the web UI service (in a separate terminal):
```bash
webui> ./gradlew bootRun --args='--songdata.host=localhost'
```

Browse http://localhost:8080

### React Development

For faster React development with hot module replacement:

1. Start the songdata backend:
   ```bash
   songdata> ./gradlew bootRun
   ```

2. Start the webui Spring Boot backend:
   ```bash
   webui> ./gradlew bootRun --args='--songdata.host=localhost'
   ```

3. Start the Vite dev server (in a separate terminal):
   ```bash
   webui/frontend> npm run dev
   ```

   Then browse http://localhost:5173 (Vite dev server with HMR)

## Build Docker Images

The React app is automatically built as part of the Gradle build:

```bash
songdata> ./gradlew bootBuildImage

webui> ./gradlew bootBuildImage
```

## Run with Docker Compose

```bash
star-songs> docker compose up
```

Browse http://localhost (note port 80)

## Shutdown Docker Compose

```bash
star-songs> docker compose down
```
