package apppnc.udommeng.grade;

import android.database.sqlite.SQLiteDatabase;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    //ประกาศตัวแปร
    private MyManage myManage;
    private EditText userEditText, passwordEditText;
    private String userString, passwordString;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bind Widget
        userEditText = (EditText) findViewById(R.id.editText);
        passwordEditText = (EditText) findViewById(R.id.editText2);



        //ทำการเรียกใช้ SQLite
        myManage = new MyManage(this);

        //ทดสอบ เพิ่ม ข้อมูล User
        testAddValue();
        deleteSQLite();

        synJSON();


    } //Main Method


    //-- จะเห็ฯที่ xml
    public void clickSignIn(View view) {

        userString = userEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();

        //เช็ค ช่องว่าง
        if (userString.equals("") || passwordString.equals("")) {
            //มีช่องว่าง
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this,"มีช่องว่าง","กรุณากรอกทุกช่อง");
        } else {
            //ไม่มีชอ่งว่าง
        }



    }// ClickSignIN

    private void synJSON() {
        //Connect http
        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy
                .Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadPolicy);

        int intTable = 0;
        InputStream inputStream = null;
        String strJSON, strLine;
        String[] urlJSONStrings = {"http://swiftcodingthai.com/Ton/php_get_user_fon.php"};

        while (intTable <= 0) {
            try {
                //-- Creae InputStream
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(urlJSONStrings[intTable]);
                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();
                inputStream = httpEntity.getContent();

                //--Create JSON string
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                StringBuilder stringBuilder = new StringBuilder();
                while ((strLine = bufferedReader.readLine()) != null) {

                    stringBuilder.append(strLine); //-- ทำการต่อ String
                }// End While

                inputStream.close();
                strJSON = stringBuilder.toString();

                //Update To SQLite
                JSONArray jsonArray = new JSONArray(strJSON);
                for (int i = 0; i < jsonArray.length();i++ ) {
                    JSONObject jsonObject = jsonArray.getJSONObject((i));
                    switch (intTable) {
                        case 0:
                            //for userTable
                            String strUser = jsonObject.getString(MyManage.column_user);
                            String strPassword = jsonObject.getString(MyManage.column_pass);
                            String strName= jsonObject.getString(MyManage.column_name);
                            String strIDteacher= jsonObject.getString(MyManage.column_id_teacher);
                            String strImage= jsonObject.getString(MyManage.column_image);
                            String strStatus= jsonObject.getString(MyManage.column_status);

                            myManage.addUser(strUser, strPassword, strName, strIDteacher, strImage, strStatus);

                            break;
                    }

                }//End For

            } catch (Exception e) {
                Log.d("Teacher", "My Error ==> " + e.toString());
            }
            intTable += 1;
        }


    }// synJSON

    private void deleteSQLite() {
        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
                MODE_PRIVATE, null);
        sqLiteDatabase.delete(MyManage.user_table,null,null);


    }
    private void testAddValue() {
        myManage.addUser("user", "pass", "name", "id_1234", "image", "status");
    }
}//Main Class
