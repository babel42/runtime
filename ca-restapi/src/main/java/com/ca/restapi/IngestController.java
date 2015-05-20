package com.ca.restapi;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/demo")
public class IngestController {
	@RequestMapping(value="/ott/wechat/send",method=RequestMethod.POST)
	public String publish(@RequestParam("msg") String msg, @RequestParam("phone") String phone) {
		System.out.println("Producer: Phone[" + phone + "], msg[" + msg+"]");
			
		return null;
	}

	@RequestMapping(value="/ott/wechat/get",method=RequestMethod.GET)
	public void consume(@RequestParam("group") String group, 
			@RequestParam("topic") String topic, @RequestParam("numOfThreads") int numOfThreads) {
		
	}	
	
}