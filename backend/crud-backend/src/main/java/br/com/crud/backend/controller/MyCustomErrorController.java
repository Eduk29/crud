package br.com.crud.backend.controller;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyCustomErrorController implements ErrorController {
//	https://www.logicbig.com/tutorials/spring-framework/spring-boot/implementing-error-controller.html
	@RequestMapping(value = "/error", produces = {"application/json", "text/html; charset=utf-8"})
	@ResponseBody
	public String handleError(HttpServletRequest request) {

		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");

		if ("application/json".equals(request.getContentType())) {
			return String.format(
					"{\r\n" + "    \"timestamp\": \"%s\",\r\n" + "    \"status\": %s,\r\n"
							+ "    \"error\": \"Internal Server Error\",\r\n" + "    \"message\": \"%s\",\r\n"
							+ "    \"path\": \"/documento\"\r\n" + "}", Instant.now(),
					statusCode, exception == null ? "N/A" : exception.getCause().getLocalizedMessage());
		} else {

			return String.format(
					"<html><body><h2>Error Page</h2><div>Status code: <b>%s</b></div>"
							+ "<div>Message: <b>%s</b></div><body></html>",
					statusCode, exception == null ? "N/A" : exception.getCause().getLocalizedMessage());

		}

	}

	@Override
	public String getErrorPath() {
		return "/error";
	}
}