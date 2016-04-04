package apppnc.udommeng.grade;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by NB on 04/04/59.
 */
public class MyManage {

    //ประกาศตัวแปร
    private MyOpenHelper myOpenHelper;
    private SQLiteDatabase sqLiteDatabase;

    public static final String user_table = "userTABLE";
    public static final String column_id = "_id";
    public static final String column_user = "User";
    public static final String column_pass = "Password";
    public static final String column_name = "Name";
    public static final String column_id_teacher = "ID_Teacher";
    public static final String column_image = "Image";
    public static final String column_status = "Status";

    public MyManage(Context context) {

        //สร้าง Database และ Connect
        myOpenHelper = new MyOpenHelper(context);
        sqLiteDatabase = myOpenHelper.getWritableDatabase();

    } //Constructor

    public long addUser(String strUser,
                        String strPassword,
                        String strName,
                        String strIDteacher,
                        String strImage,
                        String strStatus) {

        ContentValues contentValues = new ContentValues();

        contentValues.put(column_user, strUser);
        contentValues.put(column_pass , strPassword );
        contentValues.put(column_name, strName);
        contentValues.put(column_id_teacher, strIDteacher);
        contentValues.put(column_image, strImage);
        contentValues.put(column_status , strStatus);


        return sqLiteDatabase.insert(user_table,null,contentValues);
    }
}//Main Class
