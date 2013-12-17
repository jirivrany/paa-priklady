package cz.tul.nti.paa.rssdemo;

import java.util.ArrayList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

class VolleyAdapter extends BaseAdapter{
	private ArrayList<NewsModel> arrNews;
	private LayoutInflater inflater;
	
	
	public VolleyAdapter(LayoutInflater inflater, ArrayList<NewsModel> values) {
		this.arrNews = values;
		this.inflater = inflater;
	}

    @Override
    public int getCount() {
        return arrNews.size();
    }

    @Override
    public Object getItem(int i) {
        return arrNews.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
       ViewHolder holder ;
       if(view == null){
           holder = new ViewHolder();
           view = inflater.inflate(R.layout.row_listview,null);
           holder.tvTitle = (TextView) view.findViewById(R.id.txtTitle);
           holder.tvDate = (TextView) view.findViewById(R.id.txtDate);
           view.setTag(holder);
       }
       else {
           holder = (ViewHolder) view.getTag();
       }

        NewsModel row = arrNews.get(i);
        holder.tvTitle.setText(row.getTitle());
        holder.tvDate.setText(row.getPubDate());
        return view;
    }

     static class  ViewHolder{
         TextView tvTitle;
         TextView tvDate;
     }

}