package no.postnord.routeoptimizer.view;

import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.EditorAction;
import org.androidannotations.annotations.ItemSelect;
import org.androidannotations.annotations.ViewById;
import org.apache.commons.lang3.StringUtils;

import no.postnord.routeoptimizer.R;
import no.postnord.routeoptimizer.adapter.AddressListAdapter;
import no.postnord.routeoptimizer.service.client.DirectionsClient;

@EFragment(R.layout.fragment_main)
public class CreateRouteFragment extends Fragment {

	@ViewById
	RecyclerView recyclerViewAddresses;

	@ViewById
	Spinner spinnerOrigin;

	@ViewById
	Spinner spinnerDestination;

	@ViewById
	ImageButton imageButtonCalculateRoute;

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

	private RecyclerView.LayoutManager layoutManager;

	@AfterViews
	void initViews() {
		spinnerOrigin.setAdapter(ArrayAdapter.createFromResource(getActivity(), R.array.terminals,
				android.R.layout.simple_spinner_dropdown_item));
		spinnerDestination.setAdapter(ArrayAdapter.createFromResource(getActivity(), R.array.terminals,
				android.R.layout.simple_spinner_dropdown_item));

		recyclerViewAddresses.setHasFixedSize(true);
		layoutManager = new LinearLayoutManager(getActivity());
		recyclerViewAddresses.setLayoutManager(layoutManager);

		recyclerViewAddresses.setAdapter(addressListAdapter);
	}

	@Click
	void imageButtonCalculateRouteClicked() {
		imageButtonCalculateRoute.animate().translationX(300);

		String waypoints = "optimize:true|";
		for (int i = 0; i < addressListAdapter.getItemCount(); i++) {
			waypoints += addressListAdapter.getItem(i);
			waypoints += "|";
		}
		directionsClient.getDirections(selectedOrigin, selectedDestination, waypoints);
	}

	@Click
	void buttonAddClicked() {
		addAddress();
	}

	private void addAddress() {
		String address = editTextNewAddress.getText().toString();
		if (StringUtils.isNotBlank(address)) {
			addressListAdapter.addAddress(address);
		}
		editTextNewAddress.setText("");
	}

	@ItemSelect
	void spinnerOriginItemSelected(boolean selected, String origin) {
		selectedOrigin = origin;
	}

	@ItemSelect
	void spinnerDestinationItemSelected(boolean selected, String destination) {
		selectedDestination = destination;
	}

	@EditorAction
	void editTextNewAddress(KeyEvent keyEvent) {
		if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
			addAddress();
		}
	}

	public void moveCarButton() {
		imageButtonCalculateRoute.animate().translationX(0);
	}
}
