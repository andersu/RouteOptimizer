package no.postnord.routeoptimizer.view;

import android.app.Activity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.FragmentById;

import no.postnord.routeoptimizer.R;
import no.postnord.routeoptimizer.model.Route;
import no.postnord.routeoptimizer.util.MapUtil;

@EActivity(R.layout.activity_map)
public class MapActivity extends Activity implements OnMapReadyCallback, GoogleMap.OnCameraChangeListener {

	boolean firstCameraChange = true;

	@FragmentById(R.id.fragment_map)
	MapFragment fragmentMap;

	@Extra
	Route route;

	private GoogleMap map;

	@AfterViews
	void getMap() {
		fragmentMap.getMapAsync(this);
	}

	@Override
	public void onMapReady(GoogleMap map) {
		this.map = map;
		map.setOnCameraChangeListener(this);

		MapUtil.addMarkers(map, route);
		MapUtil.drawPolyLines(map, route);
	}

	@Override
	public void onCameraChange(CameraPosition cameraPosition) {
		if (firstCameraChange) {
			MapUtil.moveCamera(map, route.getBounds());
			firstCameraChange = false;
		}
	}
}
