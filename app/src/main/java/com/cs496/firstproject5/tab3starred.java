package com.cs496.firstproject5;

/**
 * Created by q on 2016-12-26.
 */

import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
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
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.disableDependentsState;
import static android.R.attr.onClick;
import static com.cs496.firstproject5.R.id.priceB;

public class tab3starred extends Fragment{
    private List<Hotel> list;
    BarChart barChart;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab3starred, container, false);
        list = MyApplication.starredList;
        barChart = (BarChart) rootView.findViewById(R.id.bar);
        barChart.setNoDataText("");
        final List<BarEntry> entries = new ArrayList<>();
        final ArrayList<String> hotels = new ArrayList<>();

        TextView msg = (TextView) rootView.findViewById(R.id.msg);
        msg.setText("You starred "+list.size()+" hotels.");
        final RadioGroup features = (RadioGroup) rootView.findViewById(R.id.features);

        features.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup g, int id) {
                //int id = features.getCheckedRadioButtonId();
                switch (id) {
                    case R.id.priceB:
                        Collections.sort(list, new Comparator<Hotel>(){
                            public int compare(Hotel obj1, Hotel obj2)
                            {return (obj1.getPrice() < obj2.getPrice()) ? -1:
                                        (obj1.getPrice() > obj2.getPrice()) ? 1:0;}
                        });
                        for (Hotel h : list) {
                            entries.add(new BarEntry(list.indexOf(h), h.getPrice()));
                            hotels.add(h.getName());
                        }

                    case R.id.cityB:
                        Collections.sort(list, new Comparator<Hotel>(){
                            public int compare(Hotel obj1, Hotel obj2)
                            {return (obj1.getToCity() < obj2.getToCity()) ? -1:
                                        (obj1.getToCity() > obj2.getToCity()) ? 1:0;}
                        });
                        for (Hotel h : list) {
                            entries.add(new BarEntry(list.indexOf(h),(float) h.getToCity()));
                            hotels.add(h.getName());
                        }
                    case R.id.eiffelB:
                        Collections.sort(list, new Comparator<Hotel>(){
                            public int compare(Hotel obj1, Hotel obj2)
                            {return (obj1.getToEiffel() < obj2.getToEiffel()) ? -1:
                                        (obj1.getToEiffel() > obj2.getToEiffel()) ? 1:0;}
                        });
                        for (Hotel h : list) {
                            entries.add(new BarEntry(list.indexOf(h), (float) h.getToEiffel()));
                            hotels.add(h.getName());
                        }
                    case R.id.ratingB:
                        Collections.sort(list, new Comparator<Hotel>(){
                            public int compare(Hotel obj1, Hotel obj2)
                            {return (obj1.getRating() > obj2.getRating()) ? -1:
                                        (obj1.getRating() < obj2.getRating()) ? 1:0; }
                        });
                        for (Hotel h : list) {
                            entries.add(new BarEntry(list.indexOf(h),(float) h.getRating()));
                            hotels.add(h.getName());
                        }
                    case R.id.starB:
                        Collections.sort(list, new Comparator<Hotel>(){
                            public int compare(Hotel obj1, Hotel obj2)
                            { return (obj1.getStar() > obj2.getStar()) ? -1:
                                        (obj1.getStar() < obj2.getStar()) ? 1:0; }
                        });
                        for (Hotel h : list) {
                            entries.add(new BarEntry(list.indexOf(h),h.getStar()));
                            hotels.add(h.getName());
                        }
                    default:
                }
                BarDataSet barDataSet = new BarDataSet(entries,"Hotel");
                BarData data = new BarData(barDataSet);
                barChart.setData(data);
                barChart.setFitBars(true);
                barChart.setTouchEnabled(true);
                barChart.setDragEnabled(true);
                barChart.setScaleEnabled(true);
                barChart.invalidate();
            }
        });

        return rootView;
    }
}
