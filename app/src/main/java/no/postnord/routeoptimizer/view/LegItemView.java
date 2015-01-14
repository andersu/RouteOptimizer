package no.postnord.routeoptimizer.view;

import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import no.postnord.routeoptimizer.R;
import no.postnord.routeoptimizer.model.Leg;

@EViewGroup(R.layout.leg_item)
public class LegItemView extends RelativeLayout {

	@ViewById
	TextView textViewOrigin;

	@ViewById
	TextView textViewDestination;

	@ViewById
	TextView textViewDistance;

	@ViewById
	TextView textViewDuration;

	public LegItemView(Context context) {
		super(context);
	}

	public void bind(Leg leg) {
		textViewOrigin.setText(leg.getStart_address());
		textViewDestination.setText(leg.getEnd_address());
		textViewDistance.setText(leg.getDistance().getText());
		textViewDuration.setText(leg.getDuration().getText());
	}
}
