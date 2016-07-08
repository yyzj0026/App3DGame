package com.winds.app3dgame.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/6/24.
 */
public class MySqliteOpenHelper extends SQLiteOpenHelper{
    public MySqliteOpenHelper(Context context) {
        super(context, "news.db",null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table if not exists info(id integer primary key," +
                "typeid int," +
                "typeid2 int," +
                "sortrank int," +
                "flag varchar(10)," +
                "ismake int," +
                "channel int," +
                "arcrank int," +
                "click int," +
                "money int," +
                "title varchar(50)," +
                "shorttitle varchar(30)," +
                "color varchar(10)," +
                "writer varchar(20)," +
                "source varchar(15)," +
                "litpic varchar(60)," +
                "pubdate int," +
                "senddate int," +
                "mid int," +
                "keywords varchar(30)," +
                "lastpost int," +
                "scores int," +
                "goodpost int," +
                "badpost int," +
                "voteid int," +
                "notpost int," +
                "description varchar(100)," +
                "filename varchar(10)," +
                "dutyadmin int," +
                "tackid int," +
                "mtype int," +
                "weight int," +
                "fby_id int," +
                "game_id int," +
                "feedback int," +
                "typedir varchar(30)," +
                "typename varchar(50)," +
                "corank int," +
                "isdefault int," +
                "defaultname varchar(50)," +
                "namerule varchar(50)," +
                "namerule2 varchar(50)," +
                "ispart int," +
                "moresite int," +
                "siteurl varchar(10)," +
                "sitepath varchar(30)," +
                "arcurl varchar(100)," +
                "typeurl varchar(100))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
