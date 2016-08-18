package prototype.com.br.exemplochart.Main;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;

import java.util.ArrayList;

import prototype.com.br.exemplochart.R;

/**
 * Created by helen on 10/08/2016.
 */
public class MainActivity extends Activity {

    BarEntry bar = new BarEntry(4f,3);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RadarChart bar = (RadarChart) findViewById(R.id.chart);

        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry(4f,0));
        entries.add(new Entry(8f,1));
        entries.add(new Entry(6f,2));
        entries.add(new Entry(12f,3));
        entries.add(new Entry(18f,4));
        entries.add(new Entry(9f,5));

        RadarDataSet dataset = new RadarDataSet(entries, "# of Calls");

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("January");
        labels.add("February");
        labels.add("March");
        labels.add("April");
        labels.add("May");
        labels.add("June");

        RadarChart chart = new RadarChart(MainActivity.this);
        setContentView(chart);
        RadarData data = new RadarData(labels, dataset);
        chart.setData(data);
        chart.setDescription("# of times Alice called Bob");

    }
}
