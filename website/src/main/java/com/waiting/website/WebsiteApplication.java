package com.waiting.website;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;

@SpringBootApplication
@Controller
public class WebsiteApplication {

	RestTemplate restTemplate = new RestTemplate();

	public static void main(String[] args) {
		SpringApplication.run(WebsiteApplication.class, args);
	}

	@GetMapping("/")
	public String index (
			@RequestParam(name = "user_id") Long userId,
			@RequestParam(name = "queue", defaultValue = "default") String queue,
			HttpServletRequest request
	) {
		var cookies = request.getCookies();
		var cookieName = "user-queue-%s-token".formatted(queue);

		var token = "";
		if (cookies != null) {
			var cookie = Arrays.stream(cookies).filter(i -> i.getName().equalsIgnoreCase(cookieName)).findFirst();
			token = cookie.orElse(new Cookie(cookieName, "")).getValue();
		}
		var uri = UriComponentsBuilder
				.fromUriString("http://127.0.0.1:9010")
				.path("/api/v1/queue/allowed")
				.queryParam("queue", queue)
				.queryParam("user_id", userId)
				.queryParam("token", token)
				.encode().build().toUri();

		ResponseEntity<AllowedUserResponse> response = restTemplate.getForEntity(uri, AllowedUserResponse.class);
		if (response.getBody() == null || !response.getBody().allowed()) {
			return "redirect:http://127.0.0.1:9010/waiting-room?user_id=%d&redirect_url=%s".formatted(
					userId, "https://naver.com".formatted(userId)
			);
		}

		return "index";
	}

	public record AllowedUserResponse(Boolean allowed){}

}
