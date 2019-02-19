package com.example.mushfiq.practiceprojects;
import android.Manifest;
import android.app.Activity;
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
import android.support.v4.app.ActivityCompat;
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
import com.squareup.picasso.Picasso;
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
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        verifyStoragePermissions(this);
        //Toast.makeText(this, "Item : "+item, Toast.LENGTH_SHORT).show();
        ImageView = findViewById(R.id.image);
        SharedPreferences shared = getSharedPreferences("img", MODE_PRIVATE);
        String path = shared.getString("imagePreferance","");
       // Toast.makeText(this, path, Toast.LENGTH_SHORT).show();
        Log.e("CameraActivity", String.valueOf(path));
        if (path.isEmpty()!= true){
               Bitmap bitmap = BitmapFactory.decodeFile(path);
              ImageView.setImageBitmap(bitmap);
            //Toast.makeText(this, path, Toast.LENGTH_SHORT).show();
           // File f= new File(path);
            // Picasso.get().load(new File(Uri.decode(path))).into(ImageView);
        }
    }

    public void launchCamera(View view){
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            //Bitmap resized = Bitmap.createScaledBitmap(photo,1080, 1920, true);
            ImageView.setImageBitmap(photo);
            SaveImage(photo);
            }
        }
   public void SaveImage(Bitmap finalBitmap) {
        File myDir = new File(Environment.getExternalStorageDirectory(),"Mushfiq"+"/"+"Image");
        if (!myDir.exists()) {
            myDir.mkdirs();
        }
        String fname = "Image"+System.currentTimeMillis() +".jpg";
        File file = new File (myDir, fname);
        String path = file.getAbsolutePath();
        //Toast.makeText(this, path, Toast.LENGTH_LONG).show();
       SharedPreferences shrdf=getSharedPreferences("img",MODE_PRIVATE);
       SharedPreferences.Editor editor = shrdf.edit();
       editor.putString("imagePreferance", path);
       editor.commit();
         FileOutputStream out;
        try {
            out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG,100, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("CameraActivity", String.valueOf(e));
        }
    }
    public static void verifyStoragePermissions(Activity activity) {
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permissionn = ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED && permissionn != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
        }
    }
}