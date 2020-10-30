package xiang.enst.money;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity2 extends AppCompatActivity {
    private Intent intent;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        this.setTitle("Sous");

    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_t_euros:
                if (checked)
                    // Pirates are the best
                    break;
            case R.id.radio_t_pounds:
                if (checked)
                    // Pirates are the best
                    break;
            case R.id.radio_t_dollars:
                if (checked)
                    // Ninjas rule
                    break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void convert(View view)
    {
//        EditText editText = (EditText)findViewById(R.id.result);
//        String str= editText.getText().toString();
//        editText.setText(str);


        JSONObject rates = getJSONObject();
        double r = 0.0;
        try {
            r = rates.getJSONObject("rates").getDouble("GBP");
            //Log.d("r:", Double.toString(r));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        intent = getIntent();
        // 发送返回数据  key可自己定义，但前后必须一样
        intent.putExtra("rates", Double.toString(r));
        Log.d("r:", Double.toString(r));
        // RESULT_OK 系统定义的int 值 为-1
        setResult(RESULT_OK, intent);
        // 关闭当前 Activity
        finish();

//        //建立一个意图,参数为（当前的Activity类对象，需要开的的Activity类）
//        Intent intent = new Intent(MainActivity2.this, MainActivity.class);
//        intent.putExtra("rates", Double.toString(r));
//        Log.d("r:", Double.toString(r));
//        //启动意图
//        startActivity(intent);


    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public JSONObject getJSONObject(){
        InputStream inputStream = getResources().openRawResource(R.raw.taux_2017_11_02);
        StringBuilder stringBuilder = new StringBuilder();
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))){
            String line = null;
            while((line = reader.readLine())!= null){
                stringBuilder.append(line + "\n");
                //Log.d("test", "line" + line);
            }
            String jsonString = stringBuilder.toString();
            //Log.d("test",jsonString);
            return new JSONObject(jsonString);
        }
        catch(IOException e){
            System.err.println("Warning: could not read rate:" + e.getLocalizedMessage());
        }
        catch(JSONException e){
            System.err.println("Warning: could not read rate:" + e.getLocalizedMessage());
        }
        return null;
    }

}