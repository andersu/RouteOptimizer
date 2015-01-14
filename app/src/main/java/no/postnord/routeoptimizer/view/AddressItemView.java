package no.postnord.routeoptimizer.view;

import android.content.Context;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import no.postnord.routeoptimizer.R;

@EViewGroup(R.layout.address_item)
public class AddressItemView extends RelativeLayout {

	@ViewById
	TextView textViewAddress;

	@ViewById
	ImageView imageViewDelete;

	public AddressItemView(Context context) {
		super(context);
	}

	public void bind(String address) {
		textViewAddress.setText(address);
	}

	@Click
	void imageViewDeleteClicked() {

	}
}
