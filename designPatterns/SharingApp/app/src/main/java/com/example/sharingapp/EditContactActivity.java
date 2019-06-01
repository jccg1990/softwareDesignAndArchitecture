package com.example.sharingapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Editing a pre-existing contact consists of deleting the old contact and adding a new contact with the old
 * contact's id.
 * Note: You will not be able contacts which are "active" borrowers
 */
public class EditContactActivity extends AppCompatActivity implements Observer {

    private ContactList contact_list = new ContactList();
    private ContactListController contactListController = new ContactListController(contact_list);

    private Contact contact;
    private ContactController contactController;

    private EditText email;
    private EditText username;
    private Context context;

    private boolean onCreateUpdate = false;
    private int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        username = (EditText) findViewById(R.id.username);
        email = (EditText) findViewById(R.id.email);

        Intent intent = getIntent();
        pos = intent.getIntExtra("position", 0);
        context = getApplicationContext();

        onCreateUpdate = true;

        contactListController.addObserver(this);
        contactListController.loadContacts(context);

        onCreateUpdate = false;
    }

    public void saveContact(View view) {

        String email_str = email.getText().toString();

        if (email_str.equals("")) {
            email.setError("Empty field!");
            return;
        }

        if (!email_str.contains("@")) {
            email.setError("Must be an email address!");
            return;
        }

        String username_str = username.getText().toString();
        String id = contactController.getId(); // Reuse the contact id

        // Check that username is unique AND username is changed (Note: if username was not changed
        // then this should be fine, because it was already unique.)
        if (!contactListController.isUsernameAvailable(username_str) && !(contactController.getUsername().equals(username_str))) {
            username.setError("Username already taken!");
            return;
        }

        Contact updated_contact = new Contact(username_str, email_str, id);

        boolean success = contactListController.editContact(contact, updated_contact, context);
        if (!success) {
            return;
        }

        // End EditContactActivity
        contactListController.removeObserver(this);

        finish();
    }

    public void deleteContact(View view) {

        boolean success = contactListController.deleteContact(contact, context);
        if (!success) {
            return;
        }

        // End EditContactActivity
        contactListController.removeObserver(this);

        finish();
    }

    @Override
    public void update() {
        if (onCreateUpdate) {
            contact = contact_list.getContact(pos);
            contactController = new ContactController(contact);

            username.setText(contactController.getUsername());
            email.setText(contactController.getEmail());
        }
    }
}
