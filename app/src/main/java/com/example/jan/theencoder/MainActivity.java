package com.example.jan.theencoder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private RadioButton vigenereRadBut;
    private RadioButton autokeyRadBut;
    private RadioButton playfairRadBut;

    private EditText inputEditText;
    private EditText inputEditKey;

    private Button encrypteBtn;
    private Button decrypteBtn;

    private TextView outTextView;

    private Cipher currentCipher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


        // initializations

        vigenereRadBut = findViewById(R.id.vigenereRadButID);
        autokeyRadBut = findViewById(R.id.autokeyRadButID);
        playfairRadBut = findViewById(R.id.playfairRadButID);

        inputEditText = findViewById(R.id.inputEditTextID);
        inputEditKey = findViewById(R.id.inputEditKeyID);

        encrypteBtn = findViewById(R.id.encrBtnID);
        decrypteBtn = findViewById(R.id.decrBtnID);

        outTextView  = findViewById(R.id.outEditTextID);
        outTextView.setEnabled(false);

        currentCipher = new VigenereCipher();
        vigenereRadBut.setChecked(true);

    }

    // RadioButtons events
    public void playfairRadButClickEvent( View v ){
        currentCipher = new PlayfairCipher();
    }
    public void vigenereRadButClickEvent( View v ){
        currentCipher = new VigenereCipher();
    }
    public void autokeyRadButClickEvent( View v ){
        currentCipher = new AutokeyCipher();
    }

    // encypte/decrypte events
    public void encrypteClickEvent( View v ){
        if(inputEditKey.getText().toString().length() != 0) {
            outTextView.setText(currentCipher.encrypt(inputEditText.getText().toString(), inputEditKey.getText().toString()));
            outTextView.setEnabled(true);
        }

    }

    public void decrypteClickEvent( View v ){
        if(inputEditKey.getText().toString().length() != 0) {
            outTextView.setText(currentCipher.decrypt(inputEditText.getText().toString(), inputEditKey.getText().toString()));
            outTextView.setEnabled(true);
        }
    }




}
