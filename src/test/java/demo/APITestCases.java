package demo;

import java.util.HashMap;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class APITestCases {
	public String baseURL = "";

	@Test(priority = 1)
	public void PositiveAuthenticateCall() {
		RestAssured.baseURI = "https://api.coindcx.com";
		RequestSpecification request = RestAssured.given();
		HashMap<String, Object> requestBody = new HashMap<String, Object>();
		requestBody.put("email", "balashankar.parthipan2020@gmail.com");
		requestBody.put("password",
				"OWd4SINSnTy6ggw2nCEkdBZ/gKvGEcW/X7o0EewFUs/jTANS9mjPNVb4DNxJ7RQiT8hf1XFVQVXB9if+GxoaiAtiRakrPAupEHMRGe8g+vFbwv4wTk7inFHXaTVG/MZ/hUD8zsvHG+iUgjrrKvhCRyUqtp+VfwgcSHODTs+vIOvfR7S/fExJ9vB6tNkR6cLoTepLq6wb21DYKKyjUpzVCqq0Iqe8eQ1avfzG82hMeFHM5K8H0fMtwx22dMo6vTwkYQ/0bSVDerbmbwdArck+1+0G0/xGvcGpyCVso3yES9szcuyyX5laS1WOjGGV24Lc962Uwv3S73uF35FRpEWTIglRRL+FNsGIZeSzW/IOnnVW4W6c7X+zmdeouoDjZ8eWkNVY+pgs1Jirto/Ojsro1Cu53s5v7332pASlR5uQ0vs30RSQG2YCOjEubp85JTuDgtvFT6BL27tMNFWEWslDI+9K9aXu0nvxv2fhX/Zyf1fAdUfhh0yzuxemObBAd2K66KWJ0tiiY9mZGZ3NYys4FwCGYt+qf6fV3kK+UkzVh8sakBIqXRsLxuHyRYHt1BDrZhHWVmNcPMDXz40UcSRg7ojhVagxwar/X7wpX2cwTq3rEDwNh9jaC796ZhpICbjJQK4cD9mRf8egqqFpaxYQYTn9+3JUUNcMfyNrmgJw5aE=");
		requestBody.put("pe", "true");
		requestBody.put("purpose", "authenticate");
		JSONObject requestParams = new JSONObject(requestBody);
		request.body(requestParams.toJSONString());
		request.header("Content-Type", "application/json");
		Response response = request.post("/api/v3/authenticate");
		int statusCode = response.getStatusCode();
		String message = response.jsonPath().get("message");
		String user = response.jsonPath().get("user.authentication_via");
		Boolean verify_device = response.jsonPath().get("verify_device");
		Boolean flag = true;
		Assert.assertEquals(statusCode, 200, "SuccessCode not matched");
		Assert.assertEquals(message, "Otp sent", "Message not matched");
		Assert.assertEquals(user, "via_any", "user info not matched");
		Assert.assertEquals(verify_device, flag, "verify device not matched");
	}

	@Test(priority = 2)
	public void NegativeAuthenticateCallInvaildMailId() {
		RestAssured.baseURI = "https://api.coindcx.com";
		RequestSpecification request = RestAssured.given();
		HashMap<String, Object> requestBody = new HashMap<String, Object>();
		requestBody.put("email", "balashankar.parthipan2020gmail.com");
		requestBody.put("password",
				"OWd4SINSnTy6ggw2nCEkdBZ/gKvGEcW/X7o0EewFUs/jTANS9mjPNVb4DNxJ7RQiT8hf1XFVQVXB9if+GxoaiAtiRakrPAupEHMRGe8g+vFbwv4wTk7inFHXaTVG/MZ/hUD8zsvHG+iUgjrrKvhCRyUqtp+VfwgcSHODTs+vIOvfR7S/fExJ9vB6tNkR6cLoTepLq6wb21DYKKyjUpzVCqq0Iqe8eQ1avfzG82hMeFHM5K8H0fMtwx22dMo6vTwkYQ/0bSVDerbmbwdArck+1+0G0/xGvcGpyCVso3yES9szcuyyX5laS1WOjGGV24Lc962Uwv3S73uF35FRpEWTIglRRL+FNsGIZeSzW/IOnnVW4W6c7X+zmdeouoDjZ8eWkNVY+pgs1Jirto/Ojsro1Cu53s5v7332pASlR5uQ0vs30RSQG2YCOjEubp85JTuDgtvFT6BL27tMNFWEWslDI+9K9aXu0nvxv2fhX/Zyf1fAdUfhh0yzuxemObBAd2K66KWJ0tiiY9mZGZ3NYys4FwCGYt+qf6fV3kK+UkzVh8sakBIqXRsLxuHyRYHt1BDrZhHWVmNcPMDXz40UcSRg7ojhVagxwar/X7wpX2cwTq3rEDwNh9jaC796ZhpICbjJQK4cD9mRf8egqqFpaxYQYTn9+3JUUNcMfyNrmgJw5aE=");
		requestBody.put("pe", "true");
		requestBody.put("purpose", "authenticate");
		JSONObject requestParams = new JSONObject(requestBody);
		request.body(requestParams.toJSONString());
		request.header("Content-Type", "application/json");
		Response response = request.post("/api/v3/authenticate");
		int statusCode = response.getStatusCode();
		int code = response.jsonPath().get("code");
		String message = response.jsonPath().get("message");
		String status = response.jsonPath().get("status");
		Assert.assertEquals(statusCode, 422, "SuccessCode not matched");
		Assert.assertEquals(code, 422, "Message not matched");
		Assert.assertEquals(message, "Email is invalid", "user info not matched");
		Assert.assertEquals(status, "error", "verify device not matched");
	}

	@Test(priority = 3)
	public void NegativeAuthenticateCallEmptyMailId() {
		RestAssured.baseURI = "https://api.coindcx.com";
		RequestSpecification request = RestAssured.given();
		HashMap<String, Object> requestBody = new HashMap<String, Object>();
		requestBody.put("email", "");
		requestBody.put("password",
				"OWd4SINSnTy6ggw2nCEkdBZ/gKvGEcW/X7o0EewFUs/jTANS9mjPNVb4DNxJ7RQiT8hf1XFVQVXB9if+GxoaiAtiRakrPAupEHMRGe8g+vFbwv4wTk7inFHXaTVG/MZ/hUD8zsvHG+iUgjrrKvhCRyUqtp+VfwgcSHODTs+vIOvfR7S/fExJ9vB6tNkR6cLoTepLq6wb21DYKKyjUpzVCqq0Iqe8eQ1avfzG82hMeFHM5K8H0fMtwx22dMo6vTwkYQ/0bSVDerbmbwdArck+1+0G0/xGvcGpyCVso3yES9szcuyyX5laS1WOjGGV24Lc962Uwv3S73uF35FRpEWTIglRRL+FNsGIZeSzW/IOnnVW4W6c7X+zmdeouoDjZ8eWkNVY+pgs1Jirto/Ojsro1Cu53s5v7332pASlR5uQ0vs30RSQG2YCOjEubp85JTuDgtvFT6BL27tMNFWEWslDI+9K9aXu0nvxv2fhX/Zyf1fAdUfhh0yzuxemObBAd2K66KWJ0tiiY9mZGZ3NYys4FwCGYt+qf6fV3kK+UkzVh8sakBIqXRsLxuHyRYHt1BDrZhHWVmNcPMDXz40UcSRg7ojhVagxwar/X7wpX2cwTq3rEDwNh9jaC796ZhpICbjJQK4cD9mRf8egqqFpaxYQYTn9+3JUUNcMfyNrmgJw5aE=");
		requestBody.put("pe", "true");
		requestBody.put("purpose", "authenticate");
		JSONObject requestParams = new JSONObject(requestBody);
		request.body(requestParams.toJSONString());
		request.header("Content-Type", "application/json");
		Response response = request.post("/api/v3/authenticate");
		int statusCode = response.getStatusCode();
		int code = response.jsonPath().get("code");
		String message = response.jsonPath().get("message");
		String status = response.jsonPath().get("status");
		Assert.assertEquals(statusCode, 422, "SuccessCode not matched");
		Assert.assertEquals(code, 422, "code not matched");
		Assert.assertEquals(message, "Email can't be blank", "message not matched");
		Assert.assertEquals(status, "error", "status not matched");
	}

	@Test(priority = 4)
	public void NegativeAuthenticateCallWithoutpe() {
		RestAssured.baseURI = "https://api.coindcx.com";
		RequestSpecification request = RestAssured.given();
		HashMap<String, Object> requestBody = new HashMap<String, Object>();
		requestBody.put("email", "balashankar.parthipan@gmail.com");
		requestBody.put("password",
				"OWd4SINSnTy6ggw2nCEkdBZ/gKvGEcW/X7o0EewFUs/jTANS9mjPNVb4DNxJ7RQiT8hf1XFVQVXB9if+GxoaiAtiRakrPAupEHMRGe8g+vFbwv4wTk7inFHXaTVG/MZ/hUD8zsvHG+iUgjrrKvhCRyUqtp+VfwgcSHODTs+vIOvfR7S/fExJ9vB6tNkR6cLoTepLq6wb21DYKKyjUpzVCqq0Iqe8eQ1avfzG82hMeFHM5K8H0fMtwx22dMo6vTwkYQ/0bSVDerbmbwdArck+1+0G0/xGvcGpyCVso3yES9szcuyyX5laS1WOjGGV24Lc962Uwv3S73uF35FRpEWTIglRRL+FNsGIZeSzW/IOnnVW4W6c7X+zmdeouoDjZ8eWkNVY+pgs1Jirto/Ojsro1Cu53s5v7332pASlR5uQ0vs30RSQG2YCOjEubp85JTuDgtvFT6BL27tMNFWEWslDI+9K9aXu0nvxv2fhX/Zyf1fAdUfhh0yzuxemObBAd2K66KWJ0tiiY9mZGZ3NYys4FwCGYt+qf6fV3kK+UkzVh8sakBIqXRsLxuHyRYHt1BDrZhHWVmNcPMDXz40UcSRg7ojhVagxwar/X7wpX2cwTq3rEDwNh9jaC796ZhpICbjJQK4cD9mRf8egqqFpaxYQYTn9+3JUUNcMfyNrmgJw5aE=");
		requestBody.put("purpose", "authenticate");
		JSONObject requestParams = new JSONObject(requestBody);
		request.body(requestParams.toJSONString());
		request.header("Content-Type", "application/json");
		Response response = request.post("/api/v3/authenticate");
		int statusCode = response.getStatusCode();
		int code = response.jsonPath().get("code");
		String message = response.jsonPath().get("message");
		String status = response.jsonPath().get("status");
		Assert.assertEquals(statusCode, 401, "SuccessCode not matched");
		Assert.assertEquals(code, 401, "code not matched");
		Assert.assertEquals(message, "Invalid Credentials", "message not matched");
		Assert.assertEquals(status, "unauthorized", "status not matched");
	}

	@Test(priority = 5)
	public void NegativeAuthenticateCallpeAsFalse() throws InterruptedException {
		RestAssured.baseURI = "https://api.coindcx.com";
		RequestSpecification request = RestAssured.given();
		HashMap<String, Object> requestBody = new HashMap<String, Object>();
		requestBody.put("email", "balashankar.parthipan@gmail.com");
		requestBody.put("password",
				"OWd4SINSnTy6ggw2nCEkdBZ/gKvGEcW/X7o0EewFUs/jTANS9mjPNVb4DNxJ7RQiT8hf1XFVQVXB9if+GxoaiAtiRakrPAupEHMRGe8g+vFbwv4wTk7inFHXaTVG/MZ/hUD8zsvHG+iUgjrrKvhCRyUqtp+VfwgcSHODTs+vIOvfR7S/fExJ9vB6tNkR6cLoTepLq6wb21DYKKyjUpzVCqq0Iqe8eQ1avfzG82hMeFHM5K8H0fMtwx22dMo6vTwkYQ/0bSVDerbmbwdArck+1+0G0/xGvcGpyCVso3yES9szcuyyX5laS1WOjGGV24Lc962Uwv3S73uF35FRpEWTIglRRL+FNsGIZeSzW/IOnnVW4W6c7X+zmdeouoDjZ8eWkNVY+pgs1Jirto/Ojsro1Cu53s5v7332pASlR5uQ0vs30RSQG2YCOjEubp85JTuDgtvFT6BL27tMNFWEWslDI+9K9aXu0nvxv2fhX/Zyf1fAdUfhh0yzuxemObBAd2K66KWJ0tiiY9mZGZ3NYys4FwCGYt+qf6fV3kK+UkzVh8sakBIqXRsLxuHyRYHt1BDrZhHWVmNcPMDXz40UcSRg7ojhVagxwar/X7wpX2cwTq3rEDwNh9jaC796ZhpICbjJQK4cD9mRf8egqqFpaxYQYTn9+3JUUNcMfyNrmgJw5aE=");
		requestBody.put("purpose", "authenticate");
		requestBody.put("pe", "false");
		JSONObject requestParams = new JSONObject(requestBody);
		request.body(requestParams.toJSONString());
		request.header("Content-Type", "application/json");
		Response response = request.post("/api/v3/authenticate");
		int statusCode = response.getStatusCode();
		int code = response.jsonPath().get("code");
		String message = response.jsonPath().get("message");
		String status = response.jsonPath().get("status");
		Assert.assertEquals(statusCode, 401, "SuccessCode not matched");
		Assert.assertEquals(code, 401, "code not matched");
		Assert.assertEquals(message, "Invalid Credentials", "message not matched");
		Assert.assertEquals(status, "unauthorized", "status not matched");
	}

	@Test(priority = 6)
	public void NegativeAuthenticateCallExceed5Attempts() throws InterruptedException {
		RestAssured.baseURI = "https://api.coindcx.com";
		RequestSpecification request = RestAssured.given();
		HashMap<String, Object> requestBody = new HashMap<String, Object>();
		requestBody.put("email", "balashankar.parthipan@gmail.com");
		requestBody.put("password",
				"OWd4SINSnTy6ggw2nCEkdBZ/gKvGEcW/X7o0EewFUs/jTANS9mjPNVb4DNxJ7RQiT8hf1XFVQVXB9if+GxoaiAtiRakrPAupEHMRGe8g+vFbwv4wTk7inFHXaTVG/MZ/hUD8zsvHG+iUgjrrKvhCRyUqtp+VfwgcSHODTs+vIOvfR7S/fExJ9vB6tNkR6cLoTepLq6wb21DYKKyjUpzVCqq0Iqe8eQ1avfzG82hMeFHM5K8H0fMtwx22dMo6vTwkYQ/0bSVDerbmbwdArck+1+0G0/xGvcGpyCVso3yES9szcuyyX5laS1WOjGGV24Lc962Uwv3S73uF35FRpEWTIglRRL+FNsGIZeSzW/IOnnVW4W6c7X+zmdeouoDjZ8eWkNVY+pgs1Jirto/Ojsro1Cu53s5v7332pASlR5uQ0vs30RSQG2YCOjEubp85JTuDgtvFT6BL27tMNFWEWslDI+9K9aXu0nvxv2fhX/Zyf1fAdUfhh0yzuxemObBAd2K66KWJ0tiiY9mZGZ3NYys4FwCGYt+qf6fV3kK+UkzVh8sakBIqXRsLxuHyRYHt1BDrZhHWVmNcPMDXz40UcSRg7ojhVagxwar/X7wpX2cwTq3rEDwNh9jaC796ZhpICbjJQK4cD9mRf8egqqFpaxYQYTn9+3JUUNcMfyNrmgJw5aE=");
		requestBody.put("purpose", "authenticate");
		requestBody.put("pe", "false");
		JSONObject requestParams = new JSONObject(requestBody);
		request.body(requestParams.toJSONString());
		request.header("Content-Type", "application/json");
		Response response = request.post("/api/v3/authenticate");
		int statusCode = response.getStatusCode();
		int code = response.jsonPath().get("code");
		String message = response.jsonPath().get("message");
		String status = response.jsonPath().get("status");
		Assert.assertEquals(statusCode, 429, "SuccessCode not matched");
		Assert.assertEquals(code, 429, "code not matched");
		Assert.assertEquals(message, "Maximum 5 attempts allowed in 5 minutes", "message not matched");
		Assert.assertEquals(status, "error", "status not matched");
	}

	@Test(priority = 7, enabled = false)
	public void PositiveAuthenticateCallWithoutPurpose() {

		RestAssured.baseURI = "https://api.coindcx.com";
		RequestSpecification request = RestAssured.given();
		HashMap<String, Object> requestBody = new HashMap<String, Object>();
		requestBody.put("email", "balashankar.parthipan2020@gmail.com");
		requestBody.put("password",
				"OWd4SINSnTy6ggw2nCEkdBZ/gKvGEcW/X7o0EewFUs/jTANS9mjPNVb4DNxJ7RQiT8hf1XFVQVXB9if+GxoaiAtiRakrPAupEHMRGe8g+vFbwv4wTk7inFHXaTVG/MZ/hUD8zsvHG+iUgjrrKvhCRyUqtp+VfwgcSHODTs+vIOvfR7S/fExJ9vB6tNkR6cLoTepLq6wb21DYKKyjUpzVCqq0Iqe8eQ1avfzG82hMeFHM5K8H0fMtwx22dMo6vTwkYQ/0bSVDerbmbwdArck+1+0G0/xGvcGpyCVso3yES9szcuyyX5laS1WOjGGV24Lc962Uwv3S73uF35FRpEWTIglRRL+FNsGIZeSzW/IOnnVW4W6c7X+zmdeouoDjZ8eWkNVY+pgs1Jirto/Ojsro1Cu53s5v7332pASlR5uQ0vs30RSQG2YCOjEubp85JTuDgtvFT6BL27tMNFWEWslDI+9K9aXu0nvxv2fhX/Zyf1fAdUfhh0yzuxemObBAd2K66KWJ0tiiY9mZGZ3NYys4FwCGYt+qf6fV3kK+UkzVh8sakBIqXRsLxuHyRYHt1BDrZhHWVmNcPMDXz40UcSRg7ojhVagxwar/X7wpX2cwTq3rEDwNh9jaC796ZhpICbjJQK4cD9mRf8egqqFpaxYQYTn9+3JUUNcMfyNrmgJw5aE=");
		requestBody.put("pe", "true");
		JSONObject requestParams = new JSONObject(requestBody);
		request.body(requestParams.toJSONString());
		request.header("Content-Type", "application/json");
		Response response = request.post("/api/v3/authenticate");
		int statusCode = response.getStatusCode();
		String message = response.jsonPath().get("message");
		String user = response.jsonPath().get("user.authentication_via");
		Boolean verify_device = response.jsonPath().get("verify_device");
		Boolean flag = true;
		Assert.assertEquals(statusCode, 200, "SuccessCode not matched");
		Assert.assertEquals(message, "Otp sent", "Message not matched");
		Assert.assertEquals(user, "via_any", "user info not matched");
		Assert.assertEquals(verify_device, flag, "verify device not matched");
	}

	@Test(priority = 8, enabled = false)
	public void NegativeAuthenticateCallEmptyPassword() {
		RestAssured.baseURI = "https://api.coindcx.com";
		RequestSpecification request = RestAssured.given();
		HashMap<String, Object> requestBody = new HashMap<String, Object>();
		requestBody.put("email", "balashankar.parthipan@gmail.com");
		requestBody.put("password", "");
		requestBody.put("purpose", "authenticate");
		requestBody.put("pe", "true");
		JSONObject requestParams = new JSONObject(requestBody);
		request.body(requestParams.toJSONString());
		request.header("Content-Type", "application/json");
		Response response = request.post("/api/v3/authenticate");
		System.out.println("Response" + response.asString());
		int statusCode = response.getStatusCode();
		int code = response.jsonPath().get("code");
		String message = response.jsonPath().get("message");
		String status = response.jsonPath().get("status");
		Assert.assertEquals(statusCode, 422, "SuccessCode not matched");
		Assert.assertEquals(code, 422, "code not matched");
		Assert.assertEquals(message, "Password can't be blank", "message not matched");
		Assert.assertEquals(status, "error", "status not matched");
	}

	@Test(priority = 9, enabled = false)
	public void NegativeAuthenticateCallInvalidPassword() {
		RestAssured.baseURI = "https://api.coindcx.com";
		RequestSpecification request = RestAssured.given();
		HashMap<String, Object> requestBody = new HashMap<String, Object>();
		requestBody.put("email", "balashankar.parthipan@gmail.com");
		requestBody.put("password", "Password@1");
		requestBody.put("purpose", "authenticate");
		requestBody.put("pe", "true");
		JSONObject requestParams = new JSONObject(requestBody);
		request.body(requestParams.toJSONString());
		request.header("Content-Type", "application/json");
		Response response = request.post("/api/v3/authenticate");
		System.out.println("Response" + response.asString());
		int statusCode = response.getStatusCode();
		int code = response.jsonPath().get("code");
		String message = response.jsonPath().get("message");
		String status = response.jsonPath().get("status");
		Assert.assertEquals(statusCode, 401, "SuccessCode not matched");
		Assert.assertEquals(code, 401, "code not matched");
		Assert.assertEquals(message, "Invalid credentials", "message not matched");
		Assert.assertEquals(status, "unauthorized", "status not matched");
	}

	@Test(priority = 10, enabled = false)
	public void NegativeAuthenticateCallWithoutPassword() {
		RestAssured.baseURI = "https://api.coindcx.com";
		RequestSpecification request = RestAssured.given();
		HashMap<String, Object> requestBody = new HashMap<String, Object>();
		requestBody.put("email", "balashankar.parthipan@gmail.com");
		requestBody.put("password", "");
		requestBody.put("purpose", "authenticate");
		requestBody.put("pe", "true");
		JSONObject requestParams = new JSONObject(requestBody);
		request.body(requestParams.toJSONString());
		request.header("Content-Type", "application/json");
		Response response = request.post("/api/v3/authenticate");
		int statusCode = response.getStatusCode();
		int code = response.jsonPath().get("code");
		String message = response.jsonPath().get("message");
		String status = response.jsonPath().get("status");
		Assert.assertEquals(statusCode, 422, "SuccessCode not matched");
		Assert.assertEquals(code, 422, "code not matched");
		Assert.assertEquals(message, "Password can't be blank", "message not matched");
		Assert.assertEquals(status, "error", "status not matched");
	}
}
