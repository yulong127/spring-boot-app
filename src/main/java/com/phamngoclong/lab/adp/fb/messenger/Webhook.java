package com.phamngoclong.lab.adp.fb.messenger;

import java.util.Date;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.phamngoclong.lab.config.AppConfig;
import com.phamngoclong.lab.config.AppConstant;
import com.phamngoclong.lab.model.Message;

@RestController
@RequestMapping("webhook")
public class Webhook {

	@Autowired
	private AppConfig appConfig;

	@GetMapping()
	public ResponseEntity<String> verify(
			@RequestParam(name = AppConstant.WEBHOOK_PARAM_TOKEN, required = false) String token,
			@RequestParam(name = AppConstant.WEBHOOK_PARAM_CHALLENGE, required = false) String challenge,
			@RequestParam(name = AppConstant.WEBHOOK_PARAM_MODE, required = false) String mode) {

		if (mode == null) {
			// check null
		} else {
			switch (mode) {
			case AppConstant.WEBHOOK_MODE_SUBSCRIBE:
				System.out.println("Received a subscription request with token: " + token);
				String verifyToken = appConfig.getWebhookVerifyToken();
				System.out.println("Compare with: " + verifyToken);
				if (Objects.equals(token, verifyToken)) {
					System.out.println("Token verified, returning with challenge: " + challenge);
					return ResponseEntity.ok(challenge);
				} else {
					System.out.println("Token rejected");
					return ResponseEntity.status(403).body(null);
				}
			default:
				return ResponseEntity.badRequest().body("Invalid " + AppConstant.WEBHOOK_PARAM_MODE);
			}
		}

		return ResponseEntity.badRequest().body("Unknown error");
	}

	@PostMapping()
	public ResponseEntity<Message> input(@PathVariable String input) {

		Message mes = new Message();
		mes.setContent(input);
		mes.setTime(new Date());

		return ResponseEntity.ok(mes);
	}

}
