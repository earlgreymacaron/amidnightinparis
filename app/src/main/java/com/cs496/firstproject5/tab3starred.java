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
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.disableDependentsState;
import static android.R.attr.label;
import static android.R.attr.onClick;
import static com.cs496.firstproject5.R.id.priceB;
import static com.github.mikephil.charting.components.Legend.LegendPosition.LEFT_OF_CHART_INSIDE;
import static com.github.mikephil.charting.components.Legend.LegendPosition.RIGHT_OF_CHART_CENTER;
import static com.github.mikephil.charting.components.Legend.LegendPosition.RIGHT_OF_CHART_INSIDE;

public class tab3starred extends Fragment {
    private List<Hotel> list;
    private BarChart barChart;
    private RadioGroup features;
    private List<BarEntry> entries;
    private ArrayList<String> hotels;
    private Legend legend;
    private XAxis xAxis;
    private YAxis yAxis;
    private Description des;
    private TextView label;
    private TextView msg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab3starred, container, false);
        list = MyApplication.starredList;
        entries = new ArrayList<>();
        hotels = new ArrayList<>();
        des = new Description();

        msg = (TextView) rootView.findViewById(R.id.msg);
        label = (TextView) rootView.findViewById(R.id.label);
        msg.setText("You starred " + MyApplication.starredList.size() + " hotels.");
        features = (RadioGroup) rootView.findViewById(R.id.features);

        barChart = (BarChart) rootView.findViewById(R.id.bar);
        barChart.setNoDataText(" ");
        barChart.setNoDataTextColor(ColorTemplate.rgb("8f7cee"));
        des.setTextColor(ColorTemplate.rgb("000000"));
        barChart.setDescription(des);
        barChart.invalidate();
        barChart.notifyDataSetChanged();
        legend = barChart.getLegend();
        legend.setEnabled(false);
        xAxis = barChart.getXAxis();
        xAxis.setDrawLabels(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setDrawGridLines(false);
        yAxis = barChart.getAxisLeft();
        yAxis.setDrawLabels(false);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        msg.setText("You starred " + MyApplication.starredList.size() + " hotels.");
        barChart.invalidate();
        features.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup g, int id) {
                //int id = features.getCheckedRadioButtonId();
                entries = new ArrayList<>();
                hotels = new ArrayList<>();
                switch (id) {
                    case R.id.priceB:
                        barChart.invalidate();
                        des.setText("$");
                        barChart.setDescription(des);
                        Collections.sort(list, new Comparator<Hotel>() {
                            public int compare(Hotel obj1, Hotel obj2) {
                                return (obj1.getPrice() < obj2.getPrice()) ? -1 :
                                        (obj1.getPrice() > obj2.getPrice()) ? 1 : 0;
                            }
                        });
                        for (Hotel h : list) {
                            entries.add(new BarEntry(list.indexOf(h), h.getPrice()));
                            hotels.add(h.getName());
                        }
                        break;

                    case R.id.cityB:
                        barChart.invalidate();
                        des.setText("miles");
                        barChart.setDescription(des);
                        Collections.sort(list, new Comparator<Hotel>() {
                            public int compare(Hotel obj1, Hotel obj2) {
                                return (obj1.getToCity() < obj2.getToCity()) ? -1 :
                                        (obj1.getToCity() > obj2.getToCity()) ? 1 : 0;
                            }
                        });
                        for (Hotel h : list) {
                            entries.add(new BarEntry(list.indexOf(h), (float) h.getToCity()));
                            hotels.add(h.getName());
                        }
                        break;
                    case R.id.eiffelB:
                        des.setText("miles");
                        barChart.setDescription(des);
                        Collections.sort(list, new Comparator<Hotel>() {
                            public int compare(Hotel obj1, Hotel obj2) {
                                return (obj1.getToEiffel() < obj2.getToEiffel()) ? -1 :
                                        (obj1.getToEiffel() > obj2.getToEiffel()) ? 1 : 0;
                            }
                        });
                        for (Hotel h : list) {
                            entries.add(new BarEntry(list.indexOf(h), (float) h.getToEiffel()));
                            hotels.add(h.getName());
                        }
                        break;
                    case R.id.ratingB:
                        des.setText("out of 5");
                        barChart.setDescription(des);
                        barChart.invalidate();
                        Collections.sort(list, new Comparator<Hotel>() {
                            public int compare(Hotel obj1, Hotel obj2) {
                                return (obj1.getRating() > obj2.getRating()) ? -1 :
                                        (obj1.getRating() < obj2.getRating()) ? 1 : 0;
                            }
                        });
                        for (Hotel h : list) {
                            entries.add(new BarEntry(list.indexOf(h), (float) h.getRating()));
                            hotels.add(h.getName());
                        }
                        break;
                    case R.id.starB:
                        barChart.invalidate();
                        des.setText("star rating");
                        barChart.setDescription(des);
                        Collections.sort(list, new Comparator<Hotel>() {
                            public int compare(Hotel obj1, Hotel obj2) {
                                return (obj1.getStar() > obj2.getStar()) ? -1 :
                                        (obj1.getStar() < obj2.getStar()) ? 1 : 0;
                            }
                        });
                        for (Hotel h : list) {
                            entries.add(new BarEntry(list.indexOf(h), h.getStar()));
                            hotels.add(h.getName());
                        }
                        break;
                    default:
                }

                BarDataSet barDataSet = new BarDataSet(entries, "hotels");
                barDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
                BarData data = new BarData(barDataSet);

                StringBuilder labels = new StringBuilder();
                for (String h : hotels) {
                    if (h == "Gardette Park Hotel")
                        h = "Gardette Park";
                    else if (h == "Paris France Hôtel")
                        h = "Paris France";
                    else if (h == "Hotel Pullman Paris Tour Eiffel")
                        h = "Hotel Pullman";
                    else if (h == "Hôtel du Louvre, A Hyatt Hotel")
                        h = "A Hyatt Hotel";
                    else if (h == "Hotel Pullman Paris Tour Eiffel")
                        h = "Hotel Pullman";
                    else if (h == "Mercure Paris Terminus Nord")
                        h = "Mercure Paris";
                    else if (h == "Shangri-La Hotel, Paris")
                        h = "Shangri-La";
                    else if (h == "The Westin Paris")
                        h = "The Westin";
                    else if (h == "Renaissance Paris Vendome Hotel")
                        h = "Renaissance";
                    else if (h == "ibis budget Paris Porte de Montmartre")
                        h = "ibis Montmartre";
                    else if (h == "Villa Royale Montsouris")
                        h = "Villa Royale";
                    else if (h == "Les Jardins du Marais")
                        h = "Jardins du Marais";
                    else if (h == "Starhotels Castille Paris")
                        h = "Starhotels";
                    else if (h == "Hotel Ares Eiffel")
                        h = "Ares Eiffel";
                    else if (h == "Hôtel ibis Paris Tour Eiffel Cambronne")
                        h = "ibis Cambronne";
                    else if (h == "AC Hotel Paris Porte Maillot")
                        h = "AC Hotel";
                    else if (h == "Hôtel Libertel Canal Saint-Martin")
                        h = "Libertel Canal";
                    else if (h == "Novotel Paris Gare de Lyon")
                        h = "Novotel Lyon";

                    labels.append("  |  " + h);
                }
                labels.append("  |");
                label.setText(labels);
                barChart.setData(data);
                barChart.setFitBars(true);
                barChart.setTouchEnabled(true);
                barChart.setDragEnabled(true);
                barChart.setScaleEnabled(true);
                barChart.animateY(1500);
                barChart.notifyDataSetChanged();
                barChart.invalidate();
            }
        });

    }
}



