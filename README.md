# star-songs
Access and Update a Collection of Celestial Themed Music

## Architecture

This project consists of two microservices:
- **songdata**: Java/Spring Boot REST API backend (port 8086)
- **nextui**: Next.js 16 frontend with TypeScript and Server Components (port 3000)

The nextui application communicates directly with the songdata API using modern Next.js patterns including:
- App Router with React Server Components
- Server-side data fetching
- TypeScript for type safety
- TailwindCSS for styling
- Vitest for testing

## Development

### Run projects from command line

1. Start the songdata backend:
   ```bash
   cd songdata
   ./gradlew bootRun
   ```

2. In a separate terminal, start the Next.js frontend:
   ```bash
   cd nextui
   npm install  # First time only
   npm run dev
   ```

3. Browse http://localhost:3000

### Testing

Run tests for the Next.js application:
```bash
cd nextui
npm test              # Run tests in watch mode
npm run test:ui       # Run tests with UI
npm run test:coverage # Run tests with coverage report
```

### Legacy webui (React + Spring Boot)

The original webui project with React/Vite served by Spring Boot is still available:

1. Start songdata: `cd songdata && ./gradlew bootRun`
2. Start webui: `cd webui && ./gradlew bootRun --args='--songdata.host=localhost'`
3. Browse http://localhost:8080

For faster React development with HMR:
```bash
cd webui/frontend
npm run dev  # Browse http://localhost:5173
```

## Build Docker Images

### Next.js (nextui)
```bash
cd nextui
docker build -t pmcgee/starsongs.nextui:0.2.0 .
```

### SongData API
```bash
cd songdata
./gradlew bootBuildImage
```

### Legacy webui
```bash
cd webui
./gradlew bootBuildImage
```

## Run with Docker Compose

The docker-compose configuration runs nextui (Next.js) and songdata:

```bash
docker compose up --build
```

Browse http://localhost (note port 80, maps to nextui on port 3000)

To use the legacy webui instead, edit `compose.yaml` and replace the nextui service with webui.

## Shutdown Docker Compose

```bash
docker compose down
```

## Project Structure

```
star-songs/
├── songdata/          # Spring Boot REST API (Java 17)
├── nextui/            # Next.js 16 frontend (TypeScript, recommended)
├── webui/             # Legacy React + Spring Boot frontend
├── compose.yaml       # Docker Compose configuration
└── README.md
```
