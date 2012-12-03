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
 * Ukazka vlastniho adapteru pro ListView 
 * Adapter je zalozeny na String Array 
 * Chybou ovsem je, ze pro kazdou polozku se zde znovu a znovu rozbaluje XML layout
 * Pro vetsi data, nebo komplikovanejsi layout polozky, to bude prilis pomale.
 * 
 */

public class MySlowArrayAdapter extends ArrayAdapter<String> {
	private final Context context;
	private final String[] values;

	public MySlowArrayAdapter(Context context, String[] values){
		super(context,R.layout.simple_list,values);
		this.context = context;
		this.values = values;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.simple_list, parent, false);
		TextView text = (TextView) view.findViewById(R.id.label);
		ImageView icon = (ImageView) view.findViewById(R.id.icon);
		text.setText(values[position]);
		String s = values[position];
		if (s.startsWith("A")) icon.setImageResource(R.drawable.ic_ok);
		else icon.setImageResource(R.drawable.ic_no);
		return view;
	}

	
}
