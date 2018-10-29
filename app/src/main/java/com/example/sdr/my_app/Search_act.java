package com.example.sdr.my_app;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Locale;

public class Search_act extends AppCompatActivity {
    private static final int REQ_SPEECH_INPUT = 100;
    private static final int REQ_SPEECH_INPUTA = 101;
    EditText goog;
    ImageButton sg,spg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_act);
        goog=findViewById(R.id.editText2);
        sg=(ImageButton) findViewById(R.id.serg);
        spg=(ImageButton)findViewById(R.id.speakg);
        final Spinner spinner1 = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> myadapt = new ArrayAdapter<String>(Search_act.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.names));
        myadapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(myadapt);
        sg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = goog.getText().toString();
                String text = spinner1.getSelectedItem().toString();
                if(query.trim().length()>0){
                    if(text.equals("Google")) {
                        String escapedQuery = Uri.encode(query, "UTF-8");
                        Uri uri = Uri.parse("http://www.google.com/#q=" + escapedQuery);
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }
                    else if(text.equals("Youtube")){
                        String escapedQuery = Uri.encode(query, "UTF-8");
                        Uri uri = Uri.parse("http://www.youtube.com/results?search_query=" + escapedQuery);
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);

                    }
                }
                else{
                    Toast.makeText(Search_act.this,"Please enter something",Toast.LENGTH_SHORT).show();
                }
            }
        });
        spg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = spinner1.getSelectedItem().toString();
                if (text.equals("Google")) {
                    startVoiceInputG();
                }
                else if(text.equals("Youtube"))
                {
                    startVoiceInputY();
                }
            }
        });
    }
    private void startVoiceInputG() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say something");
        try {
            startActivityForResult(intent, REQ_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(this, "No such Text", Toast.LENGTH_SHORT).show();
        }
    }
    private void startVoiceInputY() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say something");
        try {
            startActivityForResult(intent, REQ_SPEECH_INPUTA);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(this, "No such Text", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQ_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    goog.setText(result.get(0));
                    String location = goog.getText().toString();
                    Uri gmmIntentUri = Uri.parse("http://www.google.com/#q=" + Uri.encode(location));
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    startActivity(mapIntent);
                }
                break;
            }
            case REQ_SPEECH_INPUTA:{
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    goog.setText(result.get(0));
                    String location = goog.getText().toString();
                    Uri gmmIntentUri = Uri.parse("http://www.youtube.com/results?search_query=" + Uri.encode(location));
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    startActivity(mapIntent);
                }
                break;
            }
        }
    }
}
