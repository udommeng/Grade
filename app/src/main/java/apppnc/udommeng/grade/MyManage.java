package apppnc.udommeng.grade;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by NB on 04/04/59.
 */
public class MyManage {

    //ประกาศตัวแปร
    private MyOpenHelper myOpenHelper;
    private SQLiteDatabase sqLiteDatabase;


    public MyManage(Context context) {

        //สร้าง Database และ Connect
        myOpenHelper = new MyOpenHelper(context);
        sqLiteDatabase = myOpenHelper.getWritableDatabase();

    } //Constructor
}//Main Class
