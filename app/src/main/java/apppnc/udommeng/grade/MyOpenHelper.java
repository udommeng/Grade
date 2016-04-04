package apppnc.udommeng.grade;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by NB on 04/04/59.
 */
public class MyOpenHelper extends SQLiteOpenHelper{

    public static final String database_name = "MyTeacher.db"; //-- ชื่อฐานข้อมูล ประกาศตัวแปรแบบค่าคงที่
    private static final int database_version = 1;


    private static final String create_user_table = "create table userTABLE(" +
            "_id integer primary key," +
            "User text," +
            "Password text," +
            "Name text," +
            "ID_Teacher text," +
            "Image text," +
            "Status text);";


    public MyOpenHelper(Context context) {
        super(context,database_name,null,database_version);
        // ประกาศตัวแปร



    }// สร้าง Construtor โดยการกด Alt+Insert


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(create_user_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}// Main Class
