package com.example.mushfiq.practiceprojects;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.os.Bundle;
import android.content.pm.PackageInfo;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import static android.media.MediaRecorder.VideoSource.CAMERA;

public class cameraActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView ImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        ImageView = (ImageView) findViewById(R.id.image);
          /*  SharedPreferences shared = getSharedPreferences("img", MODE_PRIVATE);
            String photo = shared.getString("imagePreferance", "photo");
            byte[] b = Base64.decode(photo, Base64.DEFAULT);
            ByteArrayInputStream is = new ByteArrayInputStream(b);
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            ImageView.setImageBitmap(bitmap);*/
    }

    public void launchCamera(View view){
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

           // SharedPreferences shrdf=getSharedPreferences("img",MODE_PRIVATE);
           // SharedPreferences.Editor editor = shrdf.edit();
           // editor.putString("imagePreferance", imageEncoded);
           // editor.commit();

            try{
                if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
                    Bundle extras = data.getExtras();
                    Bitmap photo = (Bitmap) data.getExtras().get("data");
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    photo.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    byte[] image = baos.toByteArray();
                    //String imageEncoded = Base64.encodeToString(image, Base64.DEFAULT);
                    //Log.i("Image Log:", imageEncoded);
                    SQLiteDatabase imageDB = this.openOrCreateDatabase("Image", MODE_PRIVATE, null);
                    imageDB.execSQL("CREATE TABLE IF NOT EXISTS imageTable (image BLOB , id INT(3))");
                    imageDB.execSQL("INSERT INTO imageTable (image,id) VALUES (image,1)");
                    Cursor c = imageDB.rawQuery("SELECT * FROM imageTable", null);

                    int idIndex = c.getColumnIndex("id");
                    c.moveToFirst();

                    while (c != null) {

                        byte[] b = c.getBlob(c.getColumnIndex("image"));
                        Bitmap bitmap = BitmapFactory.decodeByteArray(b,0,b.length);
                        ImageView.setImageBitmap(bitmap);
                        Log.i("id",Integer.toString(c.getInt(idIndex)));

                    }
                }

            } catch (Exception e){
                Log.i("CameraActivity", "onActivityResult: "+String.valueOf(e) );
            }

        }
    }

   /*private void SaveImage(Bitmap finalBitmap) {


        String root = Environment.getExternalStorageDirectory().getAbsolutePath()+ "/"+"Mushfiq"+"/Image";
        //Toast.makeText(this,root, Toast.LENGTH_LONG).show();

       Log.e("CameraActivity", String.valueOf(root));
      File myDir = new File(root);
        if (!myDir.exists()) {
            if(myDir.mkdirs()){

            };
        }
        String fname = "Image"+ +System.currentTimeMillis() +".jpg";
        File file = new File (myDir, fname);
         FileOutputStream out;
        try {
            out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("CameraActivity", String.valueOf(e));
        }
    }
    private void saveImage(Bitmap bitmap) {
        String tempImageName = imageName = TextUtils.isEmpty(imageName) ? System.currentTimeMillis()+"_"+new Random().nextInt()+".jpg" : imageName+ ".jpg";
        String file_path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/"+imageDirName+"/Image";
        File dir = new File(file_path);
        if (!dir.exists())
            dir.mkdirs();
        File file = new File(dir, tempImageName);
        imageFile = file;
        FileOutputStream fOut;
        try {
            fOut = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
            fOut.flush();
            fOut.close();
        } catch (Exception e) {
            Log.e(TAG, "saveImage: "+e.getMessage().toString() );
        }
        imageFilePath = file_path+"/"+tempImageName;
    }*/
