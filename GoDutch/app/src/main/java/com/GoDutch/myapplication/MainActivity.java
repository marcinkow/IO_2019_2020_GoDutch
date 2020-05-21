package com.GoDutch.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
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
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button captureImageBtn, detectTextBtn, tempBut;
    ImageView imageView;
    TextView textView, textView2;
    Bitmap productBitmap;
    Bitmap priceBitmap;
    Uri mainPic;
    Uri productPic = null;
    Uri pricePic = null;
    Uri temp = null;
    int helper = 0;
    static final int REQUEST_IMAGE_CAPTURE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        captureImageBtn = findViewById(R.id.capture_image);
        detectTextBtn = findViewById(R.id.detect_text_image);
        imageView = findViewById(R.id.image_view);
        textView = findViewById(R.id.text_display);
        textView2 = findViewById(R.id.text_display2);

        tempBut = findViewById(R.id.button);

        tempBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cropProduct();
                cropPrice();
                //openOsobyDoPodzialu();
            }
        });

        captureImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //Toast.makeText(getBaseContext(), "Dokonaj wstępnego przycięcia zdjęcia!", Toast.LENGTH_LONG).show(); -- nie bangla
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

    private void cropProduct() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null)
        {
            CropImage.activity(mainPic)
                    .start(this);
        }
    }

    private void cropPrice() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null)
        {
            CropImage.activity(mainPic)
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
                if(helper == 0) //jesli main jest pusty
                {
                    temp = result.getUri();
                    mainPic = temp;
                    imageView.setImageURI(mainPic);
                    helper = 1;
                }
                else if (helper == 1) // jesli nie ma wycietych produktow
                {
                    temp = result.getUri();
                    productPic = temp;
                    try {
                        productBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), productPic);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    helper = 2;
                }
                else if(helper == 2) //jezeli nie ma wycietych cen
                {
                    temp = result.getUri();
                    pricePic = temp;
                    try {
                        priceBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), pricePic);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
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
        FirebaseVisionImage firebaseVisionProduct = FirebaseVisionImage.fromBitmap(productBitmap);
        FirebaseVisionImage firebaseVisionPrice = FirebaseVisionImage.fromBitmap(priceBitmap);
        FirebaseVisionTextRecognizer firebaseVisionProductDetector = FirebaseVision.getInstance().getOnDeviceTextRecognizer();
        FirebaseVisionTextRecognizer firebaseVisionPriceDetector = FirebaseVision.getInstance().getOnDeviceTextRecognizer();
        Task<FirebaseVisionText> resultProduct =
            firebaseVisionProductDetector.processImage(firebaseVisionProduct)
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

        Task<FirebaseVisionText> resultPrice =
                firebaseVisionPriceDetector.processImage(firebaseVisionPrice)
                        .addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>() {
                            @Override
                            public void onSuccess(FirebaseVisionText firebaseVisionText2) {
                                // Task completed successfully
                                displayTextFromImage2(firebaseVisionText2);
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

    private void displayTextFromImage2(FirebaseVisionText firebaseVisionText) {
        textView2.setText(null);
        textView2.setMovementMethod(new ScrollingMovementMethod());

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
                textView2.append(line.getText() + "\n");
//                }
            }

        }
    }


}
