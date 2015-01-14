package no.postnord.routeoptimizer.view;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemSelect;
import org.androidannotations.annotations.ViewById;
import org.apache.commons.lang3.StringUtils;

import no.postnord.routeoptimizer.R;
import no.postnord.routeoptimizer.adapter.AddressListAdapter;
import no.postnord.routeoptimizer.model.DirectionResponse;
import no.postnord.routeoptimizer.service.client.DirectionsClient;

@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {

	@ViewById
	ListView listViewAddresses;

    @ViewById
	Spinner spinnerOrigin;

	@ViewById
	Spinner spinnerDestination;

    @ViewById
    Button buttonCalculateRoute;

	@ViewById
	Button buttonAdd;

	@ViewById
	EditText editTextNewAddress;

    @Bean
    DirectionsClient directionsClient;

	@Bean
	AddressListAdapter addressListAdapter;

	private String selectedOrigin;
	private String selectedDestination;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

	@AfterViews
	void initViews() {
		/*addresses.add("Antenneveien 26");
		addresses.add("Henrik Ibsens gate 20");
		addresses.add("Akersgata 32");*/
		spinnerOrigin.setAdapter(ArrayAdapter.createFromResource(this, R.array.terminals,
				android.R.layout.simple_spinner_dropdown_item));
		spinnerDestination.setAdapter(ArrayAdapter.createFromResource(this, R.array.terminals,
				android.R.layout.simple_spinner_dropdown_item));

		listViewAddresses.setAdapter(addressListAdapter);
	}

    @Click
    void buttonCalculateRouteClicked() {

		String waypoints = "optimize:true|";
		for (int i = 0; i < addressListAdapter.getCount(); i++) {
			waypoints += addressListAdapter.getItem(i);
			waypoints += "|";
		}
        directionsClient.getDirections(selectedOrigin, selectedDestination, waypoints);
    }

	@Click
	void buttonAddClicked() {
		String address = editTextNewAddress.getText().toString();
		if (StringUtils.isNotBlank(address)) {
			addressListAdapter.addAddress(address);
		}
		editTextNewAddress.setText("");
	}
    public void showResult(DirectionResponse result) {
        Log.d("MainActivity", result.toString());

		RouteActivity_.intent(this).legs(result.getRoutes().get(0).getLegs()).start();

//		textViewResult.setText(result.getRoutes().get(0).getLegs().get(0).getDistance().getText() + " " +
//			result.getRoutes().get(0).getLegs().get(0).getDuration().getText());
    }

	@ItemSelect
	void spinnerOriginItemSelected(boolean selected, String origin) {
		selectedOrigin = origin;
	}

	@ItemSelect
	void spinnerDestinationItemSelected(boolean selected, String destination) {
		selectedDestination = destination;
	}
}
