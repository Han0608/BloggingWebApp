package com.song.demo.controller;

import java.time.LocalDate;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.song.demo.domain.Article;
import com.song.demo.domain.ArticleType;
import com.song.demo.domain.User;
import com.song.demo.repository.ArticleRepository;
import com.song.demo.service.ArticleService;
import com.song.demo.service.ArticleTypeService;
import com.song.demo.service.UserService;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
@RequestMapping("/article")
public class ArticleController {

	@Autowired
	ArticleService articleService;
	
	@Autowired
	ArticleTypeService articleTypeService;

	@Autowired
	UserService userService;
	
	@Autowired
	ArticleRepository articleRepository;

	
	

	 
 
//发表文章
	 
	@GetMapping("/publish")
	public String toPublishArticle(Model model,HttpSession httpSession) {
		User user = (User) httpSession.getAttribute("user");
		List<ArticleType> list = articleTypeService.findAllByUserId(user.getUserId());
		
		model.addAttribute("list", list);
	    model.addAttribute("user",user);
		return "article/publishArticle";
	}
	
	
	@PostMapping("/publish")
	public String toPublishArticle(Article article,
			@RequestParam("userId")Integer userId,
			@RequestParam("typeId")Integer  typeId,@RequestParam("content")String content,@RequestParam("title")String title,Model model) {
		
		User user = userService.findById(userId);
		ArticleType articleType = articleTypeService.findByTypeId(typeId);
		 
		article.setUser(user);
		article.setArticleType(articleType);
		article.setContent(content);
		article.setTitle(title);
		articleService.save(article);
		model.addAttribute("user",user);
		   System.out.println("发表文章");
       
		return "redirect:/article/listArticle";
		//return "article/publishResult";
		
	}

//列出发表过的所有文章
 
		@GetMapping("/listArticle")
		public String findAllByUserId(Model model,HttpSession httpSession) {
			User user = (User) httpSession.getAttribute("user");
			List<Article> list = articleService.findAllByUserId(user.getUserId());
			Collections.reverse(list);
			model.addAttribute("user",user);
			model.addAttribute("list",list);
			
			return "article/listArticle";
		}
		
		

		
//根据文章ID查看文章
		@GetMapping("/getArticle/{articleId}")
		public String findArticleById(
				@PathVariable("articleId") Integer articleId ,Model model,HttpSession httpSession) {
			Article article = articleService.findById(articleId);
			User user = (User) httpSession.getAttribute("user");
			model.addAttribute("user",user);
			model.addAttribute("article",article);
			System.out.println("查看文章");
			return "article/modifyArticle";
		}
		
//编辑指定文章
		@GetMapping("/updateArticle/{articleId}")
		public String toUpdateArticle(@PathVariable("articleId") Integer articleId, 
			Model model,HttpSession httpSession) {
			User user = (User) httpSession.getAttribute("user");

			Article  article  = articleService.findById(articleId);
			List<ArticleType> list = articleTypeService.findAllByUserId(user.getUserId());
			model.addAttribute("user",user);
			model.addAttribute("list", list);
			model.addAttribute("article", article);
			
			return "article/updateArticle";
		}

		
//保存修改
		@PostMapping("/updateArticle")
		public String updateArticle(Article article,Model model, //HttpSession httpSession,
				@RequestParam("userId")Integer userId,
				@RequestParam("typeId")Integer typeId
				) {
		
	
			User user = userService.findById(userId);
			ArticleType articleType = articleTypeService.findByTypeId(typeId);
			 
			article.setUser(user);
			article.setArticleType(articleType);
	
			articleService.updateArticle(article);
			
			List<Article> list = articleService.findAll();
			System.out.println("修改的文章的ID：");	
			
			model.addAttribute("list", list);
			return "redirect:/article/listArticle";
		} 
		
		 
		
		
		
 //添加文章类型
	
		@GetMapping("/create")
		public String createType(HttpSession httpSession, Model model) {
			User user = (User) httpSession.getAttribute("user");
			model.addAttribute("user",user);
			return "article/createArticleType";
		}
		
		
		@PostMapping("/create")
		public String createType(ArticleType articleType,@RequestParam("userId")Integer userId,@RequestParam("typeName")String typeName,Model model ) {

			
			User user = userService.findById(userId);
			articleType.setUser(user);
			articleType.setTypeName(typeName);
			articleTypeService.save(articleType);
			model.addAttribute("user", user);
			return "redirect:/article/listArticleType";
			
		}
	
	//列出所有自己创建过的文章类型
				@GetMapping("/listArticleType")
				public String findAllType(Model model,HttpSession httpSession,Map<String ,Object> map) {
					User user = (User) httpSession.getAttribute("user");

					String msg=(String) map.get("msg");
					System.out.println("-----"+msg);
					model.addAttribute("msg", msg);
					List<ArticleType> list = articleTypeService.findAllByUserId(user.getUserId());
				    Collections.reverse(list);
					model.addAttribute("list",list);
					model.addAttribute("user", user);
					return "article/listArticleType";
				}
		
	
				
	//编辑指定文章类型
		@GetMapping("/updateType/{typeId}")
		public String toUpdateType(@PathVariable("typeId") Integer typeId, Model model,HttpSession httpSession) {
			User user = (User) httpSession.getAttribute("user");
			ArticleType articleType = articleTypeService.findById(typeId);
			model.addAttribute("articleType", articleType);
			model.addAttribute("user", user);
			//回到修改页面
			return "/article/updateType";
		}
		 
	
		//保存修改
		@PostMapping("/updateType")
		public String updateType(ArticleType articleType,Model model,HttpSession httpSession
				 ) {
			User user = (User) httpSession.getAttribute("user");
		//	User user = userService.findById(userId);
			articleType.setUser(user);
			System.out.println("修改的类型数据："+articleType);	
			
			articleTypeService.updateType(articleType);
			List<ArticleType> list = articleTypeService.findAllType();
			model.addAttribute("list", list);
			
			return "redirect:/article/listArticleType";
		}
	
		 
	 
		
		
		//删除文章类型
		  @GetMapping("/deleteType/{typeId}")
		   public String deleteByTypeId(@PathVariable("typeId") Integer typeId) {
			  	Map<String,Object> map=new HashMap<>();
			  	
			  	ArticleType type=articleTypeService.findById(typeId);
				List<Article> list=articleService.findByType(type);
			    if(list.size()>0) {
			    	map.put("msg","error" );
			    	System.out.println("不能删除");
			    	return "redirect:/article/listArticleType";
			    }else {
			    	articleTypeService.deleteById(typeId);
			    	System.out.println("123123");
			    	return "redirect:/article/listArticleType";
			    }
			    
				
			} 

	
	//删除文章
	 @GetMapping("/deleteArticle/{articleId}")
	   public String deleteById(@PathVariable("articleId") Integer articleId) {
			
		  	System.out.println("删除文章");
			
		  	articleService.deleteById(articleId);	
		    
			return "redirect:/article/listArticle";
		} 
}
