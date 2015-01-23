package no.postnord.routeoptimizer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;
import java.util.List;

import no.postnord.routeoptimizer.R;

@EBean
public class AddressListAdapter extends RecyclerView.Adapter<AddressListAdapter.ViewHolder> {

	private List<String> addresses;

	@RootContext
	Context context;

	// Provide a reference to the views for each data item
	// Complex data items may need more than one view per item, and
	// you provide access to all the views for a data item in a view holder
	public static class ViewHolder extends RecyclerView.ViewHolder {

		private String address;

		private TextView textViewAddress;

		private ImageView imageViewDelete;

		public RelativeLayout addressItemView;
		public ViewHolder(View v) {
			super(v);
			textViewAddress = (TextView) v.findViewById(R.id.textViewAddress);
			imageViewDelete = (ImageView) v.findViewById(R.id.imageViewDelete);
		}
	}

	public String getItem(int position) {
		return addresses.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public int getItemCount() {
		if (addresses == null) {
			return 0;
		}

		return addresses.size();
	}

	public void addAddress(String address) {
		if (addresses == null) {
			addresses = new ArrayList<String>();
		}

		addresses.add(address);
		notifyDataSetChanged();
	}

	// Create new views (invoked by the layout manager)
	@Override
	public AddressListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
												   int viewType) {
		// create a new view
		View v = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.address_item, parent, false);
		// set the view's size, margins, paddings and layout parameters

		final ViewHolder vh = new ViewHolder(v);
		vh.imageViewDelete.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				remove(vh.textViewAddress.getText().toString());
			}
		});
		return vh;
	}

	@Override
	public void onBindViewHolder(AddressListAdapter.ViewHolder viewHolder, int i) {
		String address = addresses.get(i);
		viewHolder.textViewAddress.setText(address);
	}

	public void remove(String address) {
		int index = addresses.indexOf(address);
		addresses.remove(address);
		notifyItemRemoved(index);
	}
}
