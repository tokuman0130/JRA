package com.example.demo.Dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.Umaentity.UmaEntity;

@Repository
public class UmaDao {
	private final JdbcTemplate db;
	@Autowired
	public UmaDao(JdbcTemplate db) {
		this.db = db;
	}
	public void insertDb(UmaEntity umaentity) {
		db.update("INSERT INTO sample (name,sex,age,mother,father,win,lose) VALUES(?,?,?,?,?,?,?)",umaentity.getName(),umaentity.getSex(),umaentity.getAge(),umaentity.getMother(),umaentity.getFather(),umaentity.getWin() ,umaentity.getLose() );
	}
	
	//削除(DELETE)
	public void deleteDb(Long id) {
		//コンソールに表示
		System.out.println("削除しました");
		//DBからデータを削除
		db.update("delete from sample where id=?", id);
	}
	
	public List<UmaEntity> searchDb(){
		String sql = "SELECT * FROM sample";

		//データベースから取り出したデータをresultDB1に入れる
		List<Map<String, Object>> resultDb1 = db.queryForList(sql);

		//画面に表示しやすい形のList(resultDB2)を用意
		List<UmaEntity> resultDb2 = new ArrayList<UmaEntity>();

		//1件ずつピックアップ
		for(Map<String,Object> result1:resultDb1) {

			//データ1件分を1つのまとまりとしたUmaEntity型の「UmaEntitydb」を生成(絶対データベースに入れるにはエンティティを使う必要があるので入れ替えする）
			UmaEntity UmaEntitydb = new UmaEntity();

			//id、nameのデータをUmaEntitydbに移す
			UmaEntitydb.setId((int)result1.get("id"));
			UmaEntitydb.setName((String)result1.get("name"));
			UmaEntitydb.setSex((int)result1.get("sex"));
			UmaEntitydb.setAge((int)result1.get("age"));
			UmaEntitydb.setMother((String)result1.get("mother"));
			UmaEntitydb.setFather((String)result1.get("father"));
			UmaEntitydb.setWin((int)result1.get("win"));
			UmaEntitydb.setLose((int)result1.get("lose"));
			
			

			//移し替えたデータを持ったUmaEntitydbを、resultDB2に入れる
			resultDb2.add(UmaEntitydb);
		}

		//Controllerに渡す
		return resultDb2;
	}
	
//	kokokara
	
	public List<UmaEntity> selectOne(Long id) {

		// コンソールに表示
		System.out.println("編集画面を出します");
		// データベースから目的の1件を取り出して、そのままresultDB1に入れる
		List<Map<String, Object>> resultDb1 = db.queryForList("SELECT * FROM sample where id=?", id);
		// 画面に表示しやすい形のList(resultDB2)を用意
		List<UmaEntity> resultDb2 = new ArrayList<UmaEntity>();

		// 1件ずつピックアップ
		for (Map<String, Object> result1 : resultDb1) {
			// データ1件分を1つのまとまりとするので、UmaEntity型の「UmaEntitydb」を生成
			UmaEntity UmaEntitydb = new UmaEntity();
			// id、nameのデータをUmaEntitydbに移す
			UmaEntitydb.setId((int) result1.get("id"));
			UmaEntitydb.setName((String) result1.get("name"));
			UmaEntitydb.setSex((int) result1.get("sex"));
			UmaEntitydb.setAge((int) result1.get("age"));
			UmaEntitydb.setMother((String) result1.get("mother"));
			UmaEntitydb.setFather((String) result1.get("father"));
			UmaEntitydb.setWin((int) result1.get("win"));
			UmaEntitydb.setLose((int) result1.get("lose"));
			
			// 移し替えたデータを持ったUmaEntitydbを、resultDB2に入れる
			resultDb2.add(UmaEntitydb);
		}

		// Controllerに渡す
		return resultDb2;
	}
	

	// 更新の実行(UPDATE)
	public void updateDb(Long id, UmaEntity umaentity) {
		// コンソールに表示
		System.out.println("編集の実行");
		// UPDATEを実行
		db.update("UPDATE sample SET name = ?, sex=?,age=?, mother=?, father=?, win=?,lose=? WHERE id = ?", umaentity.getName(),umaentity.getSex(),umaentity.getAge(),umaentity.getMother(),umaentity.getFather(),umaentity.getWin() ,umaentity.getLose(), id);
	}
	

}