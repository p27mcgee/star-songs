# star-songs
Access and Update a Collection of Celestial Themed Music

## To run projects from command line

songdata> \$ ./gradlew bootRun

webui> \$ ./gradlew bootRun  --args='--songdata.host=localhost'

Browse http://localhost:8080


## To build docker images

songdata> \$ ./gradlew bootBuildImage

webui> \$ ./gradlew bootBuildImage


## Run locally with Docker Compose

star-songs> \$ docker compose up

Browse http://localhost. (note port 80)

star-songs> \$ docker compose down to shutdown
