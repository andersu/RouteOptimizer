package no.postnord.routeoptimizer.view;

import android.support.v4.app.Fragment;
import android.widget.ListView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import java.io.Serializable;
import java.util.List;

import no.postnord.routeoptimizer.R;
import no.postnord.routeoptimizer.adapter.LegListAdapter;
import no.postnord.routeoptimizer.model.Leg;

@EFragment(R.layout.fragment_route_legs)
public class RouteLegsFragment extends Fragment {

	@FragmentArg("legs")
	Serializable legs;

	@ViewById
	ListView listViewLegs;

	@Bean
	LegListAdapter legListAdapter;

	@AfterViews
	void initViews() {
		listViewLegs.setAdapter(legListAdapter);
		legListAdapter.setLegs((List<Leg>) legs);
	}
}
