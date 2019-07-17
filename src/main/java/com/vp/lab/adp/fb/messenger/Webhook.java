package com.vp.lab.adp.fb.messenger;

import java.util.Date;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vp.lab.config.AppConfig;
import com.vp.lab.config.AppConstant;
import com.vp.lab.model.Entry;
import com.vp.lab.model.Message;
import com.vp.lab.model.Messaging;
import com.vp.lab.model.User;
import com.vp.lab.model.WebhookEvent;

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
	public ResponseEntity<WebhookEvent> input(@RequestBody WebhookEvent e) {
		try {
			if (Objects.equals(e.getObject(), "page")) {
				System.out.println("Processing " + e.getEntry().size() + " items...");

				for (Entry entry : e.getEntry()) {
					System.out.println(
							"Message entry: " + entry.getMessaging().get(0).getMessage().getText());

					SendAPI.getInstance().send("MESSAGE_TAG", "BUSINESS_PRODUCTIVITY",
							entry.getMessaging().get(0).getSender().getId(), "Độ ta ko độ nàng!");

				}
				return ResponseEntity.ok(e);
			} else
				return ResponseEntity.badRequest().body(null);
//		return ResponseEntity.ok(e);

		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.ok(null);
		}
	}

}
