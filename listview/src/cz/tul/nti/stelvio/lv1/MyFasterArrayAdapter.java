package cz.tul.nti.stelvio.lv1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * 
 * @author Jiri Vrany
 *
 * Ukazka optimalni definice vlastniho adapteru pro ListView 
 * Vyuziva se zde convertView a Holder pattern
 * Vice zde:
 * http://www.vogella.com/articles/AndroidListView/article.html#adapterperformance
 * http://www.youtube.com/watch?v=N6YdwzAvwOA 
 */

public class MyFasterArrayAdapter extends ArrayAdapter<String> {
	private final Context context;
	private final String[] values;

	public MyFasterArrayAdapter(Context context,String[] values){
		super(context, R.layout.simple_list, values);
		this.context = context;
		this.values = values;
	}
	
	static class ViewHolder {
		public ImageView icon;
		public TextView text;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.simple_list, null, true);
			holder = new ViewHolder();
			holder.text = (TextView) convertView.findViewById(R.id.label);
			holder.icon = (ImageView) convertView.findViewById(R.id.icon);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.text.setText(values[position]);
		String s = values[position];
		if (s.startsWith("A")) holder.icon.setImageResource(R.drawable.ic_ok);
		else holder.icon.setImageResource(R.drawable.ic_no);
		
		return convertView;
	}

	
}
