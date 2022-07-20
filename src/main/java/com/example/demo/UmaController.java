package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Dao.UmaDao;
import com.example.demo.Umaentity.UmaEntity;


@Controller
public class UmaController {
//SampleDaoの用意
	private final UmaDao umadao;
	@Autowired
	public UmaController(UmaDao umadao) {
		this.umadao = umadao;
	}
	
	@RequestMapping("/view")
	public String view(Model model) {
		List<UmaEntity> list = umadao.searchDb();
		model.addAttribute("dbList",list);
		model.addAttribute("title","一覧ページ");
		
		return "form/view";
	}
	@RequestMapping("/input")
	public String input(Model model,Umainput umainput) {
		
		return "form/input";
	}
	@RequestMapping("/input/exe")
	public String add(Model model,Umainput umainput) {
		UmaEntity UmaEntity = new UmaEntity();
		UmaEntity.setName(umainput.getName());
		UmaEntity.setSex(umainput.getSex());
		UmaEntity.setAge(umainput.getAge());
		UmaEntity.setMother(umainput.getMother());
		UmaEntity.setFather(umainput.getFather());
		UmaEntity.setWin(umainput.getWin());
		UmaEntity.setLose(umainput.getLose());
		umadao.insertDb(UmaEntity);
		
		return "redirect:/view";
	}

}