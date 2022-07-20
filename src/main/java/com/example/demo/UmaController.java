package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
	
//	更新画面の表示
	@RequestMapping("/edit/{id}")
	public String editView(@PathVariable Long id, Model model) {

		//DBからデータを1件取ってくる(リストの形)
		List<UmaEntity> list = umadao.selectOne(id);

		//リストから、オブジェクトだけをピックアップ
		UmaEntity UmaEntitydb = list.get(0);

		//スタンバイしているViewに向かって、データを投げる
		model.addAttribute("umainput", UmaEntitydb);
		model.addAttribute("title", "編集ページ");
		return "form/edit";
	}
	
	//更新処理(UPDATE)
		@RequestMapping("/edit/{id}/exe")
		public String editExe(@PathVariable Long id, Model model, Umainput umainput) {
			//フォームの値をエンティティに入れ直し
			UmaEntity UmaEntity = new UmaEntity();
			System.out.println(umainput.getName());//取得できているかの確認
			UmaEntity.setName(umainput.getName());
			UmaEntity.setAge(umainput.getAge());
			UmaEntity.setMother(umainput.getMother());
			UmaEntity.setFather(umainput.getFather());
			UmaEntity.setWin(umainput.getWin());
			UmaEntity.setLose(umainput.getLose());
			//更新の実行
			umadao.updateDb(id,UmaEntity);
			//一覧画面へリダイレクト
			return "redirect:/view";
		}
		


}