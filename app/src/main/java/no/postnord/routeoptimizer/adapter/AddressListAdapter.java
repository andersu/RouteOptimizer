package no.postnord.routeoptimizer.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;
import java.util.List;

import no.postnord.routeoptimizer.view.AddressItemView;
import no.postnord.routeoptimizer.view.AddressItemView_;

@EBean
public class AddressListAdapter extends BaseAdapter {

	private List<String> addresses;

	@RootContext
	Context context;

	@Override
	public int getCount() {
		if (addresses == null) {
			return 0;
		}

		return addresses.size();
	}

	@Override
	public String getItem(int position) {
		return addresses.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		AddressItemView addressItemView;
		if (convertView == null) {
			addressItemView = AddressItemView_.build(context);
		} else {
			addressItemView = (AddressItemView) convertView;
		}

		addressItemView.bind(getItem(position));

		return addressItemView;
	}

	public void addAddress(String address) {
		if (addresses == null) {
			addresses = new ArrayList<String>();
		}

		addresses.add(address);
		notifyDataSetChanged();
	}
}
