package com.rc.pgapimodule.base;

import com.rc.pgapimodule.http.HttpBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BaseController {

	@Autowired
	protected HttpBuilder httpBuilder;

}
