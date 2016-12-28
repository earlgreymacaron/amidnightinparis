package com.cs496.firstproject5;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.widget.ImageView;
import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.onClick;
import static android.R.attr.state_above_anchor;

/**
 * Created by q on 2016-12-28.
 */
public class PopDetails extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_window);

        Intent intent = getIntent();
        String name = intent.getExtras().getString("name");
        int price = intent.getExtras().getInt("price");
        double toCity = intent.getExtras().getDouble("tocity");
        double toEiffel = intent.getExtras().getDouble("toeiffel");
        double rating = intent.getExtras().getDouble("rating");
        int star = intent.getExtras().getInt("star");

        TextView hName = (TextView) findViewById(R.id.h_name);
        hName.setText(name);
        TextView hStar = (TextView) findViewById(R.id.h_star);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < star; i++) {
            sb.append("★");
        }
        for (int i = 0; i < 5 - star; i++) {
            sb.append("☆");
        }
        String s = sb.toString();
        hStar.setText(s);
        TextView hPrice = (TextView) findViewById(R.id.h_price);
        hPrice.setText("\n$"+price+" /night");
        TextView hDistance = (TextView) findViewById(R.id.h_distance);
        hDistance.setText(toCity+" miles from City center\n"+toEiffel+" miles from the Eiffel Tower");
        TextView hRating = (TextView) findViewById(R.id.h_rating);
        hRating.setText("Ratings: "+ String.valueOf(rating)+ " out of 5");



        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width*0.6),(int) (height*0.43));

    }



}
