package com.commonsware.cwac.camera.demo;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

public class DisplayActivity extends Activity {
  static byte[] imageToShow=null;

  static final String EXTRA_LOCK_TO_LANDSCAPE=
          "com.commonsware.cwac.camera.demo.EXTRA_LOCK_TO_LANDSCAPE";
  static final String EXTRA_LOCK_TO_PORTRAIT=
          "com.commonsware.cwac.camera.demo.EXTRA_LOCK_TO_PORTRAIT";

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    if (imageToShow==null) {
      Toast.makeText(this, R.string.no_image, Toast.LENGTH_LONG).show();
      finish();
    }
    else {
      Bundle extras = getIntent().getExtras();

      if (extras!=null) {
        if (extras.getBoolean(EXTRA_LOCK_TO_LANDSCAPE, false)) {
          setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else if (extras.getBoolean(EXTRA_LOCK_TO_PORTRAIT, false)) {
          setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
      }

      ImageView iv=new ImageView(this);
      BitmapFactory.Options opts=new BitmapFactory.Options();

      opts.inPurgeable=true;
      opts.inInputShareable=true;
      opts.inMutable=false;
      opts.inSampleSize=2;

      iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
      iv.setImageBitmap(BitmapFactory.decodeByteArray(imageToShow,
                                                      0,
                                                      imageToShow.length,
                                                      opts));
      imageToShow=null;

      iv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
      setContentView(iv);
    }
  }
}
