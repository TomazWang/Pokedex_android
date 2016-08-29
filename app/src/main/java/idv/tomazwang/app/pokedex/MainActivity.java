package idv.tomazwang.app.pokedex;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

    }

    @Override
    protected void onResume() {
        super.onResume();
        MainFragment fragment = MainFragment.newInstance("pokedex.json");

        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.frame_main,fragment).commit();

    }
}
