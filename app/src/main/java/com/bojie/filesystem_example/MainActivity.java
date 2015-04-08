package com.bojie.filesystem_example;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class MainActivity extends ActionBarActivity {

    EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditText = (EditText) findViewById(R.id.userName);
    }


    public void saveInternalCache(View view) {

        String data = mEditText.getText().toString();
        File folder = getCacheDir();
        File myFile = new File(folder, "MyData1.txt");
        writeData(myFile, data);
    }


    public void saveExternalCache(View view) {

        String data = mEditText.getText().toString();
        File folder = getExternalCacheDir();
        File myFile = new File(folder, "MyData2.txt");
        writeData(myFile, data);

    }

    public void savePrivateExternalFile(View view) {

        String data = mEditText.getText().toString();
        File folder = getExternalFilesDir("Bojie");
        File myFile = new File(folder, "MyData3.txt");
        writeData(myFile, data);
    }

    public void savePublicExternalFile(View view) {

        String data = mEditText.getText().toString();
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File myFile = new File(folder, "MyData4.txt");
        writeData(myFile, data);
    }


    private void writeData(File myFile, String data){

        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(myFile);
            fileOutputStream.write(data.getBytes());
            message(data + " was written successfully " + myFile.getAbsolutePath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if(fileOutputStream != null){
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void next(View view) {

        Intent intent = new Intent(this, MainActivity2Activity.class);
        startActivity(intent);
    }

    public void message(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
