package com.rc.pgapimodule.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse<T> {
	private boolean success;
	private String code;
	private String message;
	private T data;

	public static <T> CommonResponse<T> ok(T data) {
		return new CommonResponse<>(true, "200", "성공", data);
	}

	public static <T> CommonResponse<T> fail(String code, String message) {
		return new CommonResponse<>(false, code, message, null);
	}
}
