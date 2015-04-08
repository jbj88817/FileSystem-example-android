package com.bojie.filesystem_example;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class MainActivity2Activity extends ActionBarActivity {

    EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2);
        mEditText = (EditText) findViewById(R.id.editText);

    }


    public void loadInternalCache(View view) {

        File folder = getCacheDir();
        File myFile = new File(folder, "MyData1.txt");
        String data = readData(myFile);
        if(data != null){
            mEditText.setText(data);
        } else {
            mEditText.setText("No data was returned");
        }
    }

    public void loadExternalCache(View view) {

        File folder = getExternalCacheDir();
        File myFile = new File(folder, "MyData2.txt");
        String data = readData(myFile);
        if(data != null){
            mEditText.setText(data);
        } else {
            mEditText.setText("No data was returned");
        }
    }

    public void loadPrivateExternalFile(View view) {

        File folder = getExternalFilesDir("Bojie");
        File myFile = new File(folder, "MyData3.txt");
        String data = readData(myFile);
        if(data != null){
            mEditText.setText(data);
        } else {
            mEditText.setText("No data was returned");
        }
    }

    public void loadPublicExternalFile(View view) {

        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File myFile = new File(folder, "MyData4.txt");
        String data = readData(myFile);
        if(data != null){
            mEditText.setText(data);
        } else {
            mEditText.setText("No data was returned");
        }
    }

    private String readData(File myFile) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(myFile);
            int read = -1;
            StringBuffer sb = new StringBuffer();
            while ((read = fileInputStream.read()) != -1) {
                sb.append((char) read);
            }

            return sb.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(fileInputStream != null){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }


    public void previous(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void message(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
