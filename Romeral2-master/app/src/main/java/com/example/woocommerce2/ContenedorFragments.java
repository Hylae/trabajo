package com.example.woocommerce2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.woocommerce2.fragments.PrimerFragment;
import com.example.woocommerce2.fragments.SegundoFragment;
import com.example.woocommerce2.pagerAdapter.AdapterPager;
import com.google.android.material.tabs.TabLayout;

public class ContenedorFragments extends AppCompatActivity {

    private TabLayout tbLoyout;
    private ViewPager viewPager;
    private AdapterPager adapterPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fragments);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        SharedPreferences preferences = getApplicationContext().getSharedPreferences("usuario", Context.MODE_PRIVATE);

        if (preferences.getAll().size() > 0){
            int id = preferences.getInt("id", 0);
            if (preferences.getBoolean("entrar", false)){
                recordatorio(id);
            }
        }

        tbLoyout = findViewById(R.id.fragmentos);
        viewPager = findViewById(R.id.vistaPaginas);

        tbLoyout.setupWithViewPager(viewPager);
        adapterPager = new AdapterPager(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        adapterPager.addfragments(new PrimerFragment(), "Login");
        adapterPager.addfragments(new SegundoFragment(), "Registro");

        viewPager.setAdapter(null);
    }

    private void recordatorio(int id) {
        Intent intent = new Intent(getApplicationContext(), OpcionActivity.class);
        intent.putExtra("dentro", true);
        intent.putExtra("id", id);
        startActivity(intent);
    }
}
