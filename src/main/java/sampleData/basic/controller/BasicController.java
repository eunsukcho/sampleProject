package sampleData.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/basic")
public class BasicController {

	@RequestMapping("/one.do")
	public ModelAndView one(){
		ModelAndView mv = new ModelAndView("/basic/basicOneView");
		return mv;
	}
	
	@RequestMapping("/two.do")
	public ModelAndView two(){
		ModelAndView mv = new ModelAndView("/basic/basicTwoView");
		return mv;
	}
	
}
