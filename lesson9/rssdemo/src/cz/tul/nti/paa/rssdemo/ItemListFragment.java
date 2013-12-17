package cz.tul.nti.paa.rssdemo;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ListFragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

/**
 * A list fragment representing a list of Items. This fragment also supports
 * tablet devices by allowing list items to be given an 'activated' state upon
 * selection. This helps indicate which item is currently being viewed in a
 * {@link ItemDetailFragment}.
 * <p>
 * Activities containing this fragment MUST implement the {@link Callbacks}
 * interface.
 */
public class ItemListFragment extends ListFragment {
	
	private String TAG = this.getClass().getSimpleName();
    private RequestQueue mRequestQueue;
    private ArrayList<NewsModel> arrNews = new ArrayList<NewsModel>();
    private LayoutInflater mInflater;
    private VolleyAdapter mVolleyAdapt;
    private ProgressDialog mProgressDialog;

	/**
	 * The serialization (saved instance state) Bundle key representing the
	 * activated item position. Only used on tablets.
	 */
	private static final String STATE_ACTIVATED_POSITION = "activated_position";

	/**
	 * The fragment's current callback object, which is notified of list item
	 * clicks.
	 */
	private Callbacks mCallbacks = sDummyCallbacks;

	/**
	 * The current activated item position. Only used on tablets.
	 */
	private int mActivatedPosition = ListView.INVALID_POSITION;

	/**
	 * A callback interface that all activities containing this fragment must
	 * implement. This mechanism allows activities to be notified of item
	 * selections.
	 */
	public interface Callbacks {
		/**
		 * Callback for when an item has been selected.
		 */
		public void onItemSelected(NewsModel row);
	}

	/**
	 * A dummy implementation of the {@link Callbacks} interface that does
	 * nothing. Used only when this fragment is not attached to an activity.
	 */
	private static Callbacks sDummyCallbacks = new Callbacks() {
		@Override
		public void onItemSelected(NewsModel row) {
		}
	};

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public ItemListFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mInflater = LayoutInflater.from(getActivity());
        mVolleyAdapt = new VolleyAdapter(mInflater, arrNews);
        setListAdapter(mVolleyAdapt);
        
        //Volley request
        mRequestQueue =  Volley.newRequestQueue(getActivity());
        String url = "http://pipes.yahoo.com/pipes/pipe.run?_id=843cabf5dcc9dbbf958b847657399535&_render=json";
        mProgressDialog = ProgressDialog.show(getActivity(),"Please Wait...","Please Wait...");
        
        JsonObjectRequest jr = new JsonObjectRequest(
        								Request.Method.GET,
        								url,
        								null,
    									new Response.Listener<JSONObject>() {
								            @Override
								            public void onResponse(JSONObject response) {
								                Log.i(TAG,response.toString());
								                parseJSON(response);
								                mVolleyAdapt.notifyDataSetChanged();
								                mProgressDialog.dismiss();
								;            }
    									},
    									new Response.ErrorListener() {
								            @Override
								            public void onErrorResponse(VolleyError error) {
								                Log.i(TAG,error.getMessage());
								            }
								        });
        mRequestQueue.add(jr);
	
	}
	
	private void parseJSON(JSONObject json){
        try{
            JSONObject value = json.getJSONObject("value");
            JSONArray items = value.getJSONArray("items");
            for(int i=0;i<items.length();i++){

                    JSONObject item = items.getJSONObject(i);
                    NewsModel row = new NewsModel();
                    row.setTitle(item.optString("title"));
                    row.setPubDate(item.optString("pubDate"));
                    
                    JSONObject rl = item.getJSONObject("guid");
                    row.setRealLink(rl.optString("content"));
                    
                    arrNews.add(row);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }


    }
	
	

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		// Restore the previously serialized activated item position.
		if (savedInstanceState != null
				&& savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
			setActivatedPosition(savedInstanceState
					.getInt(STATE_ACTIVATED_POSITION));
		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		// Activities containing this fragment must implement its callbacks.
		if (!(activity instanceof Callbacks)) {
			throw new IllegalStateException(
					"Activity must implement fragment's callbacks.");
		}

		mCallbacks = (Callbacks) activity;
	}

	@Override
	public void onDetach() {
		super.onDetach();

		// Reset the active callbacks interface to the dummy implementation.
		mCallbacks = sDummyCallbacks;
	}

	@Override
	public void onListItemClick(ListView listView, View view, int position,
			long id) {
		super.onListItemClick(listView, view, position, id);
		
		NewsModel row = (NewsModel) getListAdapter().getItem(position);

		// Notify the active callbacks interface (the activity, if the
		// fragment is attached to one) that an item has been selected.
		mCallbacks.onItemSelected(row);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		if (mActivatedPosition != ListView.INVALID_POSITION) {
			// Serialize and persist the activated item position.
			outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition);
		}
	}

	/**
	 * Turns on activate-on-click mode. When this mode is on, list items will be
	 * given the 'activated' state when touched.
	 */
	public void setActivateOnItemClick(boolean activateOnItemClick) {
		// When setting CHOICE_MODE_SINGLE, ListView will automatically
		// give items the 'activated' state when touched.
		getListView().setChoiceMode(
				activateOnItemClick ? ListView.CHOICE_MODE_SINGLE
						: ListView.CHOICE_MODE_NONE);
	}

	private void setActivatedPosition(int position) {
		if (position == ListView.INVALID_POSITION) {
			getListView().setItemChecked(mActivatedPosition, false);
		} else {
			getListView().setItemChecked(position, true);
		}

		mActivatedPosition = position;
	}
}
