package com.example.agasthya.travelbooking;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;




public class MainActivity extends AppCompatActivity {
ChildEventListener mchild;
    private ListView mMessageListView;
    private MessageAdapter mMessageAdapter;
    private ProgressBar mProgressBar;
    private FirebaseDatabase mFirebaseDatabase;
    private EditText mMessageEditText;
    private EditText mMessageEditText2;
    private EditText mMessageEditText3;
    private Button mSendButton;


    private DatabaseReference mMessagesDataBaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Display display = getWindowManager().getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();

        mFirebaseDatabase=FirebaseDatabase.getInstance();
            mMessageListView = (ListView) findViewById(R.id.messageListView);

        mMessagesDataBaseReference=mFirebaseDatabase.getReference().child("travelbooking");
            List<Messages> friendlyMessages = new ArrayList<>();

            mMessageAdapter = new MessageAdapter(this, R.layout.list_item, friendlyMessages);
            mMessageListView.setAdapter(mMessageAdapter);

            mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
            mProgressBar.setVisibility(ProgressBar.INVISIBLE);
        mMessageEditText = (EditText) findViewById(R.id.messageEditText);

        mMessageEditText2 = (EditText) findViewById(R.id.messageEditText2);
        mMessageEditText3 = (EditText) findViewById(R.id.messageEditText3);
        mSendButton = (Button) findViewById(R.id.sendButton);
        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: Send messages on click
                Messages friendlyMessage = new Messages(mMessageEditText.getText().toString(),mMessageEditText2.getText().toString(),mMessageEditText3.getText().toString());
                mMessagesDataBaseReference.push().setValue(friendlyMessage);

                // Clear input box
                mMessageEditText.setText("");mMessageEditText2.setText("");mMessageEditText3.setText("");
            }
        });


        mchild=new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Messages friendlyMessage = dataSnapshot.getValue(Messages.class);
                mMessageAdapter.add(friendlyMessage);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        mMessagesDataBaseReference.addChildEventListener(mchild);
    }
}
