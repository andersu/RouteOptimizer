package no.postnord.routeoptimizer.service.client;

import android.app.ProgressDialog;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;

import no.postnord.routeoptimizer.view.MainActivity;
import no.postnord.routeoptimizer.model.DirectionResponse;
import no.postnord.routeoptimizer.service.DirectionsService;
import retrofit.RestAdapter;

@EBean
public class DirectionsClient {

    @RootContext
    MainActivity activity;

	private ProgressDialog progressDialog;

	@UiThread
	void showProgressDialog() {
		progressDialog = ProgressDialog.show(activity, "Ruteberegning", "Beregner rute");
	}

    @Background
    public void getDirections(String origin, String destination, String waypoints) {
		showProgressDialog();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("https://maps.googleapis.com/maps/api/directions")
                .build();

        DirectionsService directionsService = restAdapter.create(DirectionsService.class);

        updateUI(directionsService.getDirections(origin, destination, waypoints, "no"));
    }

    @UiThread
    void updateUI(DirectionResponse result) {
		progressDialog.dismiss();
        activity.showResult(result);
    }
}
