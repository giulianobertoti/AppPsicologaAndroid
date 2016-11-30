package object;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mari.menu.R;

import java.util.ArrayList;

/**
 * Created by Victor on 24/11/2016.
 */

public class ColorAdapter extends ArrayAdapter<Color> {

    Context context;
    int layoutResourceId;
    ArrayList<Color> data;

    public ColorAdapter(Context context, int layoutResourceId, ArrayList<Color> data) {

        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ColorHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new ColorHolder();
            holder.txtTitle = (TextView)row.findViewById(R.id.txtTitle);
            holder.imgIcon = (ImageView)row.findViewById(R.id.imgIcon);


            row.setTag(holder);
        }
        else
        {
            holder = (ColorHolder)row.getTag();
        }


        Color color = data.get(position);
        holder.txtTitle.setText(color.name);
        holder.imgIcon.setImageResource(color.icon);

        return row;
    }


    /*
        STATIC CLASS
     */
    static class ColorHolder
    {
        TextView txtTitle;
        ImageView imgIcon;

    }


}