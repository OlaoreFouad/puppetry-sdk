package dev.olaore.puppetry_sdk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.concurrent.ExecutorService;

import dev.olaore.puppetry_sdk.api.Puppetry;

public class MainActivity extends AppCompatActivity {

    private Puppetry puppetry;
    private ExecutorService executor;

    //    puppet container
    FrameLayout puppetContainer;

    //    action buttons
    Button moveRightArmButton;
    Button moveLeftArmButton;
    Button sayPhraseButton;

    //    input to accept phrase to be spoken.
    EditText phraseInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        grab an instance of the executor service initialized in the application class to run the initialization of the Puppetry class in a background thread.
//        also check AndroidManifest.xml because we the application name (MyApplication) under the attribute android:name
        this.executor = ((MyApplication) getApplication()).executorService;
//        execute the background thread and initialize the object so it can be used without blocking the UI thread
        this.executor.execute(new Runnable() {
            @Override
            public void run() {
                puppetry = Puppetry.initailizeAndGet();
                //        function to prepare the buttons and details concerning the puppet.
//                only call prepare puppet when it has been initialized so as to not run into a null pointer exception
                preparePuppet();
            }
        });

//        grab the views defined in XML
        this.puppetContainer = findViewById(R.id.puppet_container);
        this.moveRightArmButton = findViewById(R.id.move_right_arm_button);
        this.moveLeftArmButton = findViewById(R.id.move_left_arm_button);
        this.sayPhraseButton = findViewById(R.id.say_phrase_button);
        this.phraseInput = findViewById(R.id.enter_phrase_input);

    }

    private void preparePuppet() {
//        show the puppet on the FrameLayout defined in the XML
        this.puppetry.show(this.puppetContainer);

//        set click listeners to move the right arm in certain directions when the button is clicked
        this.moveRightArmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                puppetry.moveRightArm(10, 20, 30);
            }
        });

//        set click listeners to move the left arm in certain directions when the button is clicked
        this.moveLeftArmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                puppetry.moveLeftArm(10, 20, 30);
            }
        });

        this.sayPhraseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get the text entered into the input field.
                String textToSay = phraseInput.getText().toString();

                // make sure the text is valid (meaning there's something inside of it)
                if (textToSay != "") {
                    // ask the puppet to say the phrase.
                    puppetry.say(textToSay);
                } else {
                    // tell the user to enter something to say with a toast.
                    Toast.makeText(getApplicationContext(), "Please enter text to be said", Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}