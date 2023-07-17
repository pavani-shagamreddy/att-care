package com.att.attcare.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

  @JsonProperty("access_token")
  private String accessToken;
  
  @JsonProperty("message")
  private String message;
	/*
	 * @JsonProperty("refresh_token") private String refreshToken;
	 */
}
