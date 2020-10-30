package xiang.enst.money;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity2 extends AppCompatActivity {
    // 线程变量
    private MyTask mTask;
    private Intent intent;
    private JSONObject rates;


    private class MyTask extends AsyncTask<URL, String, JSONObject> {

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected JSONObject doInBackground(URL... urls) {

            try {
                return getJSONObj();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(JSONObject result) {
            rates = result;
            Button btn = (Button)findViewById(R.id.btn_convert);
            btn.setEnabled(true);
        }


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        this.setTitle("Sous");

        mTask = new MyTask();
        mTask.execute();
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

    public void convert(View view) throws JSONException {

        intent = getIntent();
        double r = rates.getJSONObject("rates").getDouble("GBP");

        intent.putExtra("rates", Double.toString(r));
        Log.d("r:", Double.toString(r));
        setResult(RESULT_OK, intent);
        finish();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public JSONObject getJSONObj() throws IOException {
        URL exchangeRatesURL = new URL("https://perso.telecom-paristech.fr/eagan/cours/igr201/data/taux_2017_11_02.json");
        InputStream inputStream = exchangeRatesURL.openStream();
        //InputStream inputStream = getResources().openRawResource(R.raw.taux_2017_11_02);
        StringBuilder stringBuilder = new StringBuilder();
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))){
            String line = null;
            while((line = reader.readLine())!= null){
                stringBuilder.append(line + "\n");
                Log.d("test", "line" + line);
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

