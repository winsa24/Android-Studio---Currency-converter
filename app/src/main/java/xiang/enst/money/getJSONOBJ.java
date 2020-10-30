package xiang.enst.money;

import android.os.AsyncTask;
import android.widget.Toast;

public class getJSONOBJ{

}
//public class getJSONOBJ extends AsyncTask<Void,Integer,String>  {
//
//    @Override
//    protected String doInBackground(Void... voids) {
//        int i=0;
//        while(i<10){
//            i++;
//            //publishProgress 更新进度，给onProgressUpdate()传递进度参数
//            publishProgress(i);
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//            }
//        }
//        String result = Common.postGetJson(url,postValue);
//        //第三个参数为String 所以此处return一个String类型的数据
//        return result;
//
//    }
//
//
//    /**
//     * 这里的String参数对应AsyncTask中的第三个参数（也就是接收doInBackground的返回值）
//     * 运行在ui线程中，在doInBackground()执行完毕后执行,传入的参数为doInBackground()返回的结果
//     */
//    @Override
//    protected void onPostExecute(String i) {
//        Toast.makeText(context,i,Toast.LENGTH_SHORT).show();
//        text.setText(i);
//    }
//
//
//}
