package com.micmiu.framework.web.v1.demo.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.micmiu.framework.web.v1.demo.entity.Blog;
import com.micmiu.framework.web.v1.demo.service.BlogService;

/**
 * datagrid demo
 * 
 * @author <a href="http://www.micmiu.com">Michael Sun</a>
 * 
 */
@Controller
@RequestMapping(value = "/demo/datagrid.do")
public class DatagridAction {

	private static final String PREFIX = "demo.dg";

	@Autowired
	private BlogService blogService;

	@RequestMapping(params = { "method=dg4base" })
	public String list4base(Model model) {
		List<Blog> list = blogService.findAll();
		model.addAttribute("blogs", list);
		return PREFIX + ".blog.list.base";
	}

	@RequestMapping(params = { "method=dg4tb" })
	public String list4tb(Model model) {
		List<Blog> list = blogService.findAll();
		model.addAttribute("blogs", list);
		return PREFIX + ".blog.list.tb";
	}

	@RequestMapping(params = { "method=dg4export" })
	public String list4export(Model model) {
		List<Blog> list = blogService.findAll();
		model.addAttribute("blogs", list);
		return PREFIX + ".blog.list.export";
	}

}
