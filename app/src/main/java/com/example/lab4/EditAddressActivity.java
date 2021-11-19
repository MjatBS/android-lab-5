package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

import java.util.StringJoiner;
import java.util.function.Supplier;

public class EditAddressActivity extends AppCompatActivity {

    private TextInputEditText countryText;
    private TextInputEditText townText;
    private TextInputEditText addressText;
    private Button saveButton;
    private Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_address);

        countryText = this.findViewById(R.id.countryEditText);
        townText = this.findViewById(R.id.townEditText);
        addressText = this.findViewById(R.id.addressEditText);
        saveButton = this.findViewById(R.id.addressSaveButton);
        cancelButton = this.findViewById(R.id.addressCancelButton);

        ListenerFactory factory = ListenerFactory.getInstance();
        cancelButton.setOnClickListener(factory.createCancelButtonListener(this));
        Supplier<String> newValue = () -> {
            StringJoiner joiner = new StringJoiner(", ");
            joiner.add(countryText.getText());
            joiner.add(townText.getText());
            joiner.add(addressText.getText());;
            return joiner.toString();
        };
        saveButton.setOnClickListener(factory.createSaveButtonListener(this, "address", newValue));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edit_activity_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.cancelMenuItem:
                CancelConfirmationDialog dialog = new CancelConfirmationDialog();
                dialog.show(getSupportFragmentManager(), "addressEditCancelConfirmationDialog");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}