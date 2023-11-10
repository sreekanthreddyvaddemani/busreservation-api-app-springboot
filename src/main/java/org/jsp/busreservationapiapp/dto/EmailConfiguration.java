package org.jsp.busreservationapiapp.dto;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class EmailConfiguration {
	private String to, subject, text;
}
