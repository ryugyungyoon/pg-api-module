package com.rc.pgapimodule.core.base;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 환경 변수 static
 *
 * @author ryuky
 */
@Component
public final class GlobalCode {

	//실행 환경
	public static String ONOFF_SPRING_PROFILES;

	@Value("${spring.config.activate.on-profile}")
	public void setOnoffSpringProfiles(String profiles) {
		ONOFF_SPRING_PROFILES = profiles;
	}

}
