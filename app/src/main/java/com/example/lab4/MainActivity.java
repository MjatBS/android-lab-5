package com.example.lab4;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private TextView nameTextView;
    private TextView addressTextView;
    private TextView commentTextView;
    private Button nameEditButton;
    private Button addressEditButton;
    private Button commentEditButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameTextView = this.findViewById(R.id.nameTextView);
        addressTextView = this.findViewById(R.id.addressTextView);
        commentTextView = this.findViewById(R.id.commentTextView);
        nameEditButton = this.findViewById(R.id.nameEditButton);
        addressEditButton = this.findViewById(R.id.addressEditButton);
        commentEditButton = this.findViewById(R.id.commentEditButton);

        ActivityResultLauncher<Intent> launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        String fieldUpdate = (data.getStringExtra("fieldUpdate"));
                        String newValue = data.getStringExtra("newValue");
                        switch (fieldUpdate){
                            case "name":
                                nameTextView.setText(newValue);
                                break;
                            case "address":
                                addressTextView.setText(newValue);
                                break;
                            case "comment":
                                commentTextView.setText(newValue);
                                break;
                        }
                    }
                });
        ListenerFactory factory = ListenerFactory.getInstance();
        nameEditButton.setOnClickListener(factory.createEditButtonListener(this, EditNameActivity.class, launcher));
        addressEditButton.setOnClickListener(factory.createEditButtonListener(this, EditAddressActivity.class, launcher));
        commentEditButton.setOnClickListener(factory.createEditButtonListener(this, EditCommentActivity.class, launcher));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.exitMenuItem:
                ExitConfirmationDialog dialog = new ExitConfirmationDialog();
                dialog.show(getSupportFragmentManager(), "exitConfirmationDialog");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

