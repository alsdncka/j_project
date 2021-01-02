package com.jj.j_project.Common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("common")
public class CommonController {

	@RequestMapping("")
	public ModelAndView commonGo() {
		ModelAndView mv = new ModelAndView("common/test.html");
		
		return mv;
	}
	
}
