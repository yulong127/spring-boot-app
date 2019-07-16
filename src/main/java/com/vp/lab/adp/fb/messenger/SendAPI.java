package com.vp.lab.adp.fb.messenger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import com.vp.lab.config.AppConfig;
import com.vp.lab.model.Message;
import com.vp.lab.model.Messaging;
import com.vp.lab.model.User;

public class SendAPI {

	@Autowired
	private AppConfig appConfig;

	public void send(String messagingType, String tag, String recipient, String message) {
		
		Messaging req = new Messaging();
		req.setMessaging_type(messagingType);
		req.setTag(tag);
		req.setRecipient(new User(recipient));
		req.setMessage(new Message(message));
		
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.postForObject(appConfig.getSendApiUri() + "?access_token=" + appConfig.getSendApiToken(), req, String.class);
		System.out.println("Send result: " + result);
	}
}
