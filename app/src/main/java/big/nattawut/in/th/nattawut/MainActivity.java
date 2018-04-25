package big.nattawut.in.th.nattawut;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import big.nattawut.in.th.nattawut.fragment.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // Add Fregment
        if (savedInstanceState == null) {

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentMainfragement, new MainFragment())
                    .commit();

        }



    } // Main Menthod
} // Main Class
