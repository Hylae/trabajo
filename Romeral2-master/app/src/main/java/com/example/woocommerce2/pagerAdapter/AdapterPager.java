package com.example.woocommerce2.pagerAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.woocommerce2.fragments.PrimerFragment;
import com.example.woocommerce2.fragments.SegundoFragment;

import java.util.ArrayList;

public class AdapterPager extends FragmentPagerAdapter {

    private ArrayList<Fragment> listaFragment = new ArrayList<>();
    private ArrayList<String> fragmentTitulo = new ArrayList<>();

    public AdapterPager(FragmentManager fm, int behavior){
        super(fm,behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return listaFragment.get(position);
    }

    @Override
    public int getCount() {
        return listaFragment.size();
    }

    public void addfragments(Fragment fragment, String titulo){
        listaFragment.add(fragment);
        fragmentTitulo.add(titulo);
    }

    public CharSequence getPageTitle(int position){
        return (CharSequence) fragmentTitulo.get(position);
    }



}
