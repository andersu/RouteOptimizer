package no.postnord.routeoptimizer.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.List;

import no.postnord.routeoptimizer.model.Leg;
import no.postnord.routeoptimizer.view.LegItemView;
import no.postnord.routeoptimizer.view.LegItemView_;

@EBean
public class LegListAdapter extends BaseAdapter {

	private List<Leg> legs;

	@RootContext
	Context context;

	@Override
	public int getCount() {
		if (legs == null) {
			return 0;
		}

		return legs.size();
	}

	@Override
	public Leg getItem(int position) {
		return legs.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		LegItemView legItemView;
		if (convertView == null) {
			legItemView = LegItemView_.build(context);
		} else {
			legItemView = (LegItemView) convertView;
		}

		legItemView.bind(getItem(position));

		return legItemView;
	}

	public void setLegs(List<Leg> legs) {
		this.legs = legs;
		notifyDataSetChanged();
	}
}
