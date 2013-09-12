package com.micmiu.framework.web.v1.system.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexAction {
	@RequestMapping("/index.do")
	public String index() {
		return "index";
	}

}
