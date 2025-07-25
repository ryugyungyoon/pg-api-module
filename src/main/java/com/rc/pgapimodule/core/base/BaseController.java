package com.rc.pgapimodule.core.base;

import com.rc.pgapimodule.core.http.HttpBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BaseController {

	@Autowired
	protected HttpBuilder httpBuilder;

}
