package id.usk.inongmuslimah;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.TextView;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.text.DateFormat;
import java.util.Calendar;

import id.usk.inongmuslimah.databinding.ActivityDashboardBinding;

public class Dashboard extends AppCompatActivity {

    ActivityDashboardBinding binding;
    CalendarView calendarView;
    TextView myDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        calendarView = (CalendarView) findViewById(R.id.calenderView);
        myDate = (TextView) findViewById(R.id.myDate);


        replaceFragment(new Home1Fragment());


        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.home:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.calender:
                    replaceFragment(new CalenderFragment());
                    break;
                case R.id.prohibition:
                    replaceFragment(new ProhibitionFragment());
                    break;
            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();

    }
}
