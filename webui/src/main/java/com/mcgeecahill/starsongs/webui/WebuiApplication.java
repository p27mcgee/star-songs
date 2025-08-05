package com.mcgeecahill.starsongs.webui;

import com.mcgeecahill.starsongs.songdata.dto.SongDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
public class WebuiApplication {

	// TODO remove test code
	private static final Logger log = LoggerFactory.getLogger(WebuiApplication.class);

	// TODO move to application.yaml
	private static final String SONGS_URL = "http://localhost:8086/v1/songs";

	public static void main(String[] args) {
		SpringApplication.run(WebuiApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

// TODO remove test code
//	@Bean
//	@Profile("!test")
//	public CommandLineRunner runGetOne(RestTemplate restTemplate) throws Exception {
//		return args -> {
//			SongDto songDto = restTemplate.getForObject(
//					SONGS_URL + "/1", SongDto.class);
//			log.info(songDto != null ? songDto.toString() : "Null SongDto");
//		};
//	}

//	@Bean
//	@Profile("!test")
//	public CommandLineRunner runGetAll(RestTemplate restTemplate) throws Exception {
//		return args -> {
//			ResponseEntity<List<SongDto>> response = restTemplate.exchange(SONGS_URL, HttpMethod.GET, null,
//					new ParameterizedTypeReference<List<SongDto>>() {});
//            log.info("Song list: {}", response.getBody().toString());
//		};
//	}
}
