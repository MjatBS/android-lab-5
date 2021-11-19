package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

import java.util.StringJoiner;
import java.util.function.Supplier;

public class EditNameActivity extends AppCompatActivity {

    private TextInputEditText nameText;
    private TextInputEditText surnameText;
    private Button saveButton;
    private Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_name);

        nameText = this.findViewById(R.id.nameEditText);
        surnameText = this.findViewById(R.id.surnameEditText);
        saveButton = this.findViewById(R.id.nameSaveButton);
        cancelButton = this.findViewById(R.id.nameCancelButton);

        ListenerFactory factory = ListenerFactory.getInstance();
        cancelButton.setOnClickListener(factory.createCancelButtonListener(this));
        Supplier<String> newValue = () -> {
            StringJoiner joiner = new StringJoiner(" ");
            joiner.add(nameText.getText());
            joiner.add(surnameText.getText());
            return joiner.toString();
        };
        saveButton.setOnClickListener(factory.createSaveButtonListener(this, "name", newValue));
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
                dialog.show(getSupportFragmentManager(), "nameEditCancelConfirmationDialog");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}