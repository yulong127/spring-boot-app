package com.vp.lab.adp.fb.messenger;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.vp.lab.config.AppConfig;
import com.vp.lab.model.Message;
import com.vp.lab.model.Messaging;
import com.vp.lab.model.User;

public class SendAPI {

	private SendAPI() {
	}

	private static SendAPI INSTANCE = new SendAPI();

	public static SendAPI getInstance() {
		return INSTANCE;
	}

	@Autowired
	private AppConfig appConfig;
	private RestTemplate restTemplate = new RestTemplate();

	public void send(String messagingType, String tag, String recipient, String message) {

		System.out.println("Sending message: " + message);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
//		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		Messaging body = new Messaging();
		body.setMessaging_type(messagingType);
		body.setTag(tag);
		body.setRecipient(new User(recipient));
		body.setMessage(new Message(message));
		
		
		
		String fake = "{\r\n" + 
				"  \"messaging_type\": \"MESSAGE_TAG\",\r\n" + 
				"  \"tag\": \"BUSINESS_PRODUCTIVITY\",\r\n" + 
				"  \"recipient\": {\r\n" + 
				"    \"id\": \"2510495532348174\"\r\n" + 
				"  },\r\n" + 
				"  \"message\": {\r\n" + 
				"    \"text\": \"đm dễ vcl!\"\r\n" + 
				"  }\r\n" + 
				"}";

		HttpEntity<String> req = new HttpEntity<String>(fake, headers);
		
		

//		String url =appConfig.getSendApiUri() + "?access_token=" + appConfig.getSendApiToken();
		String url = "https://graph.facebook.com/v3.3/me/messages?access_token=EAAgyMVnfZA2EBALvSrUvPxRnRps15hSmyWQsC9ZB9s0Yw9jFrjaVX1gkptKcbjQzER5ZAz0rjzG85SZC8RyConFZB4QpffGi64SYiHiMuZAttXVNFr9ZCSZAsPQNDI7ZCwcmBZCQ2CwBYiWjx83FQZAYkZAHexAaOotQ05zTMevbWJDH8BmG7XRY3bjX";
		System.out.println("URL : " + url);
		String result = restTemplate.exchange(url, HttpMethod.POST, req, String.class).getBody();
		System.out.println("Send result: " + result);
	}
}
