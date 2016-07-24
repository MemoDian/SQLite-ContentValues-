package com.memo.sqlitedemo2;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 创建数据库,并打开
		SQLiteDatabase db = openOrCreateDatabase("Dota2.db", MODE_PRIVATE, null);
		db.execSQL("create table if not exists Dota2(_id integer primary key autoincrement, name text not null, sex text not null, age integer not null)");
		// 添加
		ContentValues values = new ContentValues();
		values.put("name", "张三");
		values.put("sex", "男");
		values.put("age", 18);
		long rowId = db.insert("Dota2", null, values);
		values.clear();
		values.put("name", "李四");
		values.put("sex", "女");
		values.put("age", 19);
		db.insert("Dota2", null, values);
		values.clear();
		values.put("name", "王五");
		values.put("sex", "男");
		values.put("age", 20);
		db.insert("Dota2", null, values);
		values.clear();
		values.put("name", "朱七");
		values.put("sex", "女");
		values.put("age", 21);
		db.insert("Dota2", null, values);
		values.clear();
		values.put("name", "姚八");
		values.put("sex", "男");
		values.put("age", 22);
		db.insert("Dota2", null, values);
		values.clear();
		values.put("sex", "女");
		db.delete("Dota2", "name like ?", new String[] { "%七%" });// 删除所有名字中带有七的人
		db.update("Dota2", values, "_id>?", new String[] { "3" });// 将全部id>3的sex该成女
		Cursor c = db.query("Dota2", null, "_id>?", new String[] { "0" }, null, null, "name");
		if (c != null) {
			String[] columns = c.getColumnNames();
			while (c.moveToNext()) {
				for (String columnName : columns) {
					Log.i("info", c.getString(c.getColumnIndex(columnName)));
				}
			}
			c.close();
		}
		db.close();
	}
}
