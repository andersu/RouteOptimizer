package no.postnord.routeoptimizer.view;

import android.app.Activity;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.FragmentById;

import no.postnord.routeoptimizer.R;
import no.postnord.routeoptimizer.model.DirectionResponse;

@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {

	@FragmentById
	CreateRouteFragment fragmentCreateRoute;

	public void showResult(DirectionResponse result) {
		RouteLegsActivity_.intent(this).route(result.getRoutes().get(0)).start();
		fragmentCreateRoute.moveCarButton();
	}

}
