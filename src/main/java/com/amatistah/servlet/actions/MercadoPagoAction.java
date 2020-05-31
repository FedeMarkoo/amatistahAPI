package com.amatistah.servlet.actions;

import java.util.Random;

import com.google.gson.JsonObject;
import com.mercadopago.MercadoPago;
import com.mercadopago.core.MPApiResponse;
import com.mercadopago.core.MPRequestOptions;
import com.mercadopago.core.annotations.rest.PayloadType;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.exceptions.MPRestException;
import com.mercadopago.net.HttpMethod;
import com.mercadopago.net.MPRestClient;
import com.mercadopago.resources.Payment;
import com.mercadopago.resources.datastructures.payment.Address;
import com.mercadopago.resources.datastructures.payment.Payer;

public class MercadoPagoAction {

	private static final String ACCESS_TOKEN = "TEST-4617953405176133-052422-6fa811eaa73586c2103465e440105456-133449826";

	public static void main(String[] args) {
		try {

			MercadoPago.SDK.cleanConfiguration();
			MercadoPago.SDK.setAccessToken(System.getenv(ACCESS_TOKEN));
			Payer payer = new Payer();
			payer.setEmail("test_user_97697694@testuser.com");
			payer.setFirstName("Dummy");
			payer.setLastName("Lastname");
			payer.setAddress(new Address().setStreetName("Anywhere avennue").setStreetNumber(1500).setCity("Gotham")
					.setZipCode("12333"));

			Payment payment = new Payment();
			payment.setTransactionAmount(10000f);
			payment.setPaymentMethodId("visa");
			payment.setDescription("Payment test 100 pesos");
			payment.setToken(getCardToken(CardResultExpected.APPROVED));
			payment.setInstallments(1);
			payment.setPayer(payer);

			payment.save();

			if (201 == payment.getLastApiResponse().getStatusCode())
				;
			if (payment.getId() != null)
				;
			if ("approved" == payment.getStatus().toString())
				;
			if ("credit_card" == payment.getPaymentTypeId().toString())
				;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private enum CardResultExpected {
		APPROVED("APRO"), PENDING("CONT");

		private String CardHolderName;

		private CardResultExpected(String cardHolderName) {
			this.CardHolderName = cardHolderName;
		}

		public String getCardHolderName() {
			return this.CardHolderName;
		}

	}

	private static String getCardToken(CardResultExpected result) throws MPException {

		JsonObject jsonPayload = new JsonObject();

		Random rnd = new Random();

		int expiration_year = rnd.nextInt(20) + 2019;
		int expiration_month = 1 + rnd.nextInt(10) + 1;
		int security_code = rnd.nextInt(900) + 100;

		jsonPayload.addProperty("card_number", "4235647728025682");
		jsonPayload.addProperty("security_code", String.valueOf(security_code));
		jsonPayload.addProperty("expiration_year", expiration_year);
		jsonPayload.addProperty("expiration_month", expiration_month);

		JsonObject identification = getIdentification();
//        JsonObject identification = new JsonObject();
//        identification.addProperty("type", "DNI");
//        identification.addProperty("number", "19119119100");

		JsonObject cardHolder = new JsonObject();

		cardHolder.addProperty("name", result.getCardHolderName());
		cardHolder.add("identification", identification);

		jsonPayload.add("cardholder", cardHolder);

		MPRestClient client = new MPRestClient();
		MPApiResponse response = client.executeRequest(HttpMethod.POST,
				MercadoPago.SDK.getBaseUrl() + "/v1/card_tokens?public_key=" + System.getenv("PUBLIC_KEY_TEST"),
				PayloadType.JSON, jsonPayload, MPRequestOptions.createDefault());

		return ((JsonObject) response.getJsonElementResponse()).get("id").getAsString();
	}

	private static JsonObject getIdentification() throws MPRestException {
		MPRestClient client = new MPRestClient();
		MPApiResponse response = client.executeRequest(HttpMethod.GET,
				MercadoPago.SDK.getBaseUrl() + "/users/me?access_token=" + System.getenv("ACCESS_TOKEN_TEST"),
				PayloadType.JSON, null, MPRequestOptions.createDefault());

		JsonObject jsonObject = null;
		if (response != null && response.getJsonElementResponse() != null) {
			jsonObject = new JsonObject();
			String siteId = ((JsonObject) response.getJsonElementResponse()).get("site_id").getAsString();
			if (siteId.equals("MLA")) {
				jsonObject.addProperty("type", "DNI");
				jsonObject.addProperty("number", "19119119100");
			} else if (siteId.equals("MLB")) {
				jsonObject.addProperty("type", "CPF");
				jsonObject.addProperty("number", "60609917692");
			} else if (siteId.equals("MLC")) {
				jsonObject.addProperty("type", "RUT");
				jsonObject.addProperty("number", "96506015");
			} else if (siteId.equals("MLU")) {
				jsonObject.addProperty("type", "CI");
				jsonObject.addProperty("number", "8560749");
			} else if (siteId.equals("MCO")) {
				jsonObject.addProperty("type", "NIT");
				jsonObject.addProperty("number", "17128621626");
			} else if (siteId.equals("MPE")) {
				jsonObject.addProperty("type", "DNI");
				jsonObject.addProperty("number", "92430065B");
			} else if (siteId.equals("MLV")) {
				jsonObject.addProperty("type", "Pasaporte");
				jsonObject.addProperty("number", "1234567890");
			}
		}

		return jsonObject;
	}

}
