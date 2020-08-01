package com.example.tixigo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tixigo.modem.Train;

import java.util.List;

public class Listview_adapter extends BaseAdapter {

    private List<Train> trains;
    public Listview_adapter(List<Train> trains){
        this.trains=trains;
    }

    @Override
    public int getCount() {
        return this.trains.size();
    }

    @Override
    public Object getItem(int i) {
        return this.trains.get(i);
    }

    @Override
    public long getItemId(int i) {
        return this.trains.get(i).hashCode();
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_entry,parent,false);
        Train train=this.trains.get(i);
        TextView train_name=view.findViewById(R.id.trainName);
        TextView train_num=view.findViewById(R.id.trainnum);

        train_name.setText(train.getTname());
        train_num.setText(train.getTnumber());

        return  view;
    }
}
