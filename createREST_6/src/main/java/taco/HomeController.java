package taco;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller//控制器
public class HomeController {

	@GetMapping("/")//处理对路径/home的请求
	public String home() {
		return "home";//返回视图名
	}

	@GetMapping("/login")//处理对路径/login的请求
	public String login() {
		return "login";//返回视图名
	}
}
