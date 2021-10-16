package ast.adrs.vo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.armoomragames.denketa.R;

public class MainActivity extends AppCompatActivity  {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        AppConfig.getInstance().performLangCheck(getWindow());
        if (savedInstanceState != null) {
            return;
        }


    }



}
