package com.song.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.song.demo.domain.Admin;
import com.song.demo.domain.User;
import com.song.demo.repository.AdminRepository;
import com.song.demo.repository.UserRepository;
import com.song.demo.service.AdminService;
import com.song.demo.service.UserService;
 
@Controller
@RequestMapping("/admin/*")
public class AdminController {
 
	@Autowired
	AdminService adminService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	private AdminRepository ar;
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
 
	//注册
	@RequestMapping("/register")
	public String register() {
		return "admin/register";
	}
 
	
	@GetMapping("/register")
	public String addAdmin() {
		return "admin/register";
	}

	@PostMapping("/register")
	public String addAdmin(Admin admin) {

		if (adminService.findByName(admin.getAdminName()).isEmpty()) {
			adminService.save(admin);
			return "admin/login";	
		} else {	
			return "admin/register";
		}	
	}

	
 
	//登录
	@RequestMapping("/admin/login")
	public String login() {
		return "admin/login";
	}
 

	@RequestMapping("/dologin")
	public String login(HttpServletRequest request,HttpSession httpSession) {
		String adminName = request.getParameter("adminName");
		String password = request.getParameter("password");
		Admin admin = adminService.FindNameAndPsw(adminName, password);
		
		if (admin != null) {
			httpSession.setAttribute("admin", admin);
			return  "redirect:/front/listUser";
		} else {
			return  "admin/login";
		}
		
	}
	//list personal info of admin
	 @GetMapping("/findById/{adminId}")  
	  public String findById(@PathVariable("adminId") Integer adminId, Model model) {    
	   Admin admin = adminService.findById(adminId);  
	   
	    model.addAttribute("admin", admin);  
	    return "adminInfo";  
	  } 
	
	
	//查找所有管理员

	@GetMapping("/listAdmin")
	public String findAll(Model model,HttpSession httpSession) {
		Admin admin=(Admin) httpSession.getAttribute("admin");
		List<Admin> list = adminService.findAll();
		
		model.addAttribute("list",list);
		model.addAttribute("admin",admin);
		return "admin/listAdmin";
	}
	
	
	
 
}
