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
		// �������ݿ�,����
		SQLiteDatabase db = openOrCreateDatabase("Dota2.db", MODE_PRIVATE, null);
		db.execSQL("create table if not exists Dota2(_id integer primary key autoincrement, name text not null, sex text not null, age integer not null)");
		// ���
		ContentValues values = new ContentValues();
		values.put("name", "����");
		values.put("sex", "��");
		values.put("age", 18);
		long rowId = db.insert("Dota2", null, values);
		values.clear();
		values.put("name", "����");
		values.put("sex", "Ů");
		values.put("age", 19);
		db.insert("Dota2", null, values);
		values.clear();
		values.put("name", "����");
		values.put("sex", "��");
		values.put("age", 20);
		db.insert("Dota2", null, values);
		values.clear();
		values.put("name", "����");
		values.put("sex", "Ů");
		values.put("age", 21);
		db.insert("Dota2", null, values);
		values.clear();
		values.put("name", "Ҧ��");
		values.put("sex", "��");
		values.put("age", 22);
		db.insert("Dota2", null, values);
		values.clear();
		values.put("sex", "Ů");
		db.delete("Dota2", "name like ?", new String[] { "%��%" });// ɾ�����������д����ߵ���
		db.update("Dota2", values, "_id>?", new String[] { "3" });// ��ȫ��id>3��sex�ó�Ů
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
