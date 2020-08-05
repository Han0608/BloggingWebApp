package com.song.demo.controller;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.song.demo.domain.Admin;
import com.song.demo.domain.Article;
import com.song.demo.domain.User;
import com.song.demo.repository.UserRepository;
import com.song.demo.service.ArticleService;
import com.song.demo.service.UserService;

@Controller
@RequestMapping("/front/*")
public class UserController {

	@Autowired
	UserService userService;
	@Autowired
	ArticleService articleService;
	@Autowired
	UserRepository userRepository;

	@RequestMapping("/")
	public String index(Model model) {
		
		List<Article> list = articleService.findAll();
		model.addAttribute("list", list);
		return "index";
	}

	@RequestMapping("/success/{currentPage}")
	public String index1(Map<String,Object> map,Model model,@PathVariable("currentPage") String currentPage, HttpSession httpSession) {
		User user = (User) httpSession.getAttribute("user");
		model.addAttribute("user", user);
		Page<Article> list1=articleService.findAll(Integer.parseInt(currentPage),3);
		List<Article> list = articleService.findAll();
		model.addAttribute("list", list);
		int count=list.size();
		System.out.println(count);
		int pageSize=3;
		int totalPage=count%pageSize==0?count/pageSize-1:count/pageSize;
		map.put("totalPage",totalPage);
		map.put("currentPage", Integer.parseInt(currentPage));
		map.put("list", list1.iterator());
		//model.addAttribute("list", list);
		return "success";
	}
	

	@RequestMapping("/logout")
	public String index2(Model model) {
		List<Article> list = articleService.findAll();
		// model.addAttribute("list",list);
		return "redirect:/front/";
	}
	// 注册

	@GetMapping("/register")
	public String addUser() {
		return "register";
	}

	@PostMapping("/register")
	public String addUser(User user) {
		userService.save(user);
		return "login";
	}

	// 登录
	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@PostMapping("/dologin")
	public String login(Map<String,Object> map,HttpServletRequest request, Model model, HttpSession httpSession) {
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");

		// String enable= request.getParameter("enable");
		User user = userService.FindNameAndPsw(userName, password);
		model.addAttribute("user", user);
		// request.getSession(true).setAttribute("user", user);

		// String str = "";
		if (user != null && user.getBan() == 0) {

			httpSession.setAttribute("user", user);
			Page<Article> list1=articleService.findAll(0,3);
			List<Article> list = articleService.findAll();
			int count=list.size();
			int pageSize=3;
			int totalPage=count%pageSize==0?count/pageSize-1:count/pageSize;
			map.put("totalPage",totalPage);
			map.put("currentPage", 0);
			
			map.put("list",list1.iterator());
			//model.addAttribute("list", list);
			return "success";

		} else {
			return "login";
		}

	}

	  
	  @PostMapping("/search") 
	  public String search(HttpServletRequest request,Model model,HttpSession httpSession) {
		  String search=request.getParameter("search"); 
		  User user=(User)httpSession.getAttribute("user"); 
		  List<Article> list=articleService.findByTitle(search);
		  model.addAttribute("user",user);
	      model.addAttribute("list",list); 
	      return "search";
	 }
	

	// 用户查看个人信息

	@GetMapping("/findById/{userId}")
	public String findById(@PathVariable("userId") Integer userId, Model model) {
		User user = userService.findById(userId);
		System.out.println("当前登录用户" + user.getUserName());
		model.addAttribute("user", user);
		return "info";
	}

//用户修改个人信息  
	// 来到信息页面
	@GetMapping("/update/{userId}")
	public String update(@PathVariable("userId") Integer userId, Model model) {
		User user = userService.findById(userId);
		model.addAttribute("user", user);

		return "update";
	}

	// 保存修改
	@PostMapping("/updateInfo")
	public String updateInfo(User user, Model model) {

		System.out.println("修改的用户数据：" + user);
		userService.update(user);

		model.addAttribute("user", user);

		return "info";
	}

	// 管理员查找所有用户

	@GetMapping("/listUser")
	public String findAll(Model model, HttpSession httpSession) {
		Admin admin = (Admin) httpSession.getAttribute("admin");
		List<User> list = userService.findAll();
		Collections.reverse(list);
		model.addAttribute("list", list);
		model.addAttribute("admin", admin);
		return "admin/listUser";
	}

	// 管理员修改用户
	// 来到修改页面
	@GetMapping("/updateUser/{userId}")
	public String toUpdate(@PathVariable("userId") Integer userId, Model model) {
		User user = userService.findById(userId);
		model.addAttribute("user", user);

		return "updateUser";
	}

	// 保存修改
	@PostMapping("/updateUser")
	public String updateUser(User user, Model model) {

		System.out.println("修改的用户数据：" + user);
		userService.update(user);
		List<User> list = userService.findAll();
		model.addAttribute("list", list);
		return "redirect:/front/listUser";
	}

	// 管理员删除用户

	@GetMapping("/delete/{userId}")
	public String deleteById(@PathVariable("userId") Integer userId, HttpSession httpSession, Model model) {

		System.out.println("删除用户");
		userService.deleteById(userId);
		Admin admin = (Admin) httpSession.getAttribute("admin");

		model.addAttribute("admin", admin);
		return "redirect:/front/listUser";
	}

	// ban user

	@RequestMapping("/ban/{userId}")
	public String ban(@PathVariable("userId") Integer userId, Model model, HttpSession httpSession) {
		User user = userService.findById(userId);
		user.setBan(1);
		userService.save(user);
		model.addAttribute("user", user);
		Admin admin = (Admin) httpSession.getAttribute("admin");
		model.addAttribute("admin", admin);
		return "redirect:/front/listUser";
	}

	@RequestMapping("/start/{userId}")
	public String start(@PathVariable("userId") Integer userId, Model model, HttpSession httpSession) {
		User user = userService.findById(userId);
		user.setBan(0);
		userService.save(user);
		model.addAttribute("user", user);
		Admin admin = (Admin) httpSession.getAttribute("admin");
		model.addAttribute("admin", admin);
		return "redirect:/front/listUser";
	}
}
