package com.GoDutch.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;
import com.google.firebase.ml.vision.text.RecognizedLanguage;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button captureImageBtn, detectTextBtn, tempBut;
    ImageView imageView;
    TextView textView;
    Bitmap imageBitmap;
    static final int REQUEST_IMAGE_CAPTURE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        captureImageBtn = findViewById(R.id.capture_image);
        detectTextBtn = findViewById(R.id.detect_text_image);
        imageView = findViewById(R.id.image_view);
        textView = findViewById(R.id.text_display);

        tempBut = findViewById(R.id.button);

        tempBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOsobyDoPodzialu();
            }
        });

        captureImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                dispatchTakePictureIntent();
                textView.setText("");
            }
        });

        detectTextBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                detectTextFromImage();
            }
        });
    }

    private void openOsobyDoPodzialu()
    {
        Intent intent = new Intent(this, OsobyDoPodzialu.class);
        startActivity(intent);
    }

    private void dispatchTakePictureIntent()
    {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null)
        {
            CropImage.activity()
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .start(this);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE)
        {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if(resultCode == RESULT_OK)
            {
                Uri resultUri = result.getUri();
                imageView.setImageURI(resultUri);
                try {
                    imageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), resultUri);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE)
            {
                Exception error  = result.getError();
            }
        }
    }

    private void detectTextFromImage()
    {
        FirebaseVisionImage firebaseVisionImage = FirebaseVisionImage.fromBitmap(imageBitmap);
        FirebaseVisionTextRecognizer firebaseVisionTextDetector = FirebaseVision.getInstance().getOnDeviceTextRecognizer();
        Task<FirebaseVisionText> result =
                firebaseVisionTextDetector.processImage(firebaseVisionImage)
                        .addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>() {
                            @Override
                            public void onSuccess(FirebaseVisionText firebaseVisionText) {
                                // Task completed successfully
                                displayTextFromImage(firebaseVisionText);
                            }
                        })
                        .addOnFailureListener(
                                new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        // Task failed with an exception
                                        Toast.makeText(MainActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();

                                        Log.d("Error: ", e.getMessage());
                                    }
                                });
    }


//    private void displayTextFromImage(FirebaseVisionText firebaseVisionText)
//    {
//        List<FirebaseVisionText.TextBlock> blockList = firebaseVisionText.getTextBlocks();
//        if(blockList.size() == 0)
//        {
//            Toast.makeText(this, "No text found!", Toast.LENGTH_SHORT).show();
//        }
//        else
//        {
//            int ile=0;
//            String [] tekst;
//            for (FirebaseVisionText.TextBlock block : firebaseVisionText.getTextBlocks())
//            {
//                tekst[ile]=block.getText();
////                String text = block.getText();
//                ++ile;
////                textView.setText(text);
//                textView.setText(tekst[ile]);
////                textView.setText("1");
//                System.out.println(ile);
//            }
//
//        }
//
//    }

    private void displayTextFromImage(FirebaseVisionText firebaseVisionText) {
        textView.setText(null);
        textView.setMovementMethod(new ScrollingMovementMethod());

        if (firebaseVisionText.getTextBlocks().size() == 0) {
            Toast.makeText(this, "No Text Found", Toast.LENGTH_LONG).show();
            return;
        }
        for (FirebaseVisionText.TextBlock block : firebaseVisionText.getTextBlocks()) {
//            textView.append(block.getText());



            //każda linia
//
			for (FirebaseVisionText.Line line: block.getLines()) {
//				for (FirebaseVisionText.Element element: line.getElements()) { // z tym kazde pojedyncze słowo 
//                    textView.append(element.getText() + "\n");
                    textView.append(line.getText() + "\n");
//                }
			}

        }
    }


}
