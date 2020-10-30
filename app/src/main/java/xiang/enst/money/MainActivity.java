package xiang.enst.money;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
    private final static int sendUser = 1;
    private  EditText et_rates, et_euros, et_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_rates = (EditText)findViewById(R.id.edit_rate);
        et_euros = (EditText)findViewById(R.id.edit_euro);
        et_result = (EditText)findViewById(R.id.edit_result);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == sendUser && resultCode == RESULT_OK) {
            String value = data.getStringExtra("rates");
            et_rates.setText(value);
        }
    }


    public void convert(View view)
    {
        String str_euros= et_euros.getText().toString();
        double euros = Double.valueOf(str_euros);
        String str_rates= et_rates.getText().toString();
        double rates = Double.valueOf(str_rates);
        double result =euros * rates;
        et_result.setText(Double.toString(result));
    }

    public void rate(View view)
    {
        Intent intent=new Intent(MainActivity.this,MainActivity2.class);
        startActivityForResult(intent, sendUser);
    }


}