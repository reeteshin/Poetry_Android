package com.KIPTSOFT.Shayari;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
// import com.theartofdev.edmodo.cropper.CropImage;
// import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

// import top.defaults.colorpicker.ColorPickerPopup;


public class Allinone extends AppCompatActivity implements AxDialog.AxDialogListener {
    int[] backcolore, fontcolorearray, cangeimage;
    ImageView background, font, colore, down, image, selectimagefromgallary;
    TextView textTitle;
    RelativeLayout Rele;
    float xDown = 0, yDown = 0;
    int STORAGE_PERMISSION_CODE = 1;
    TextView Dragtext;
    Uri mImageUri;
    float[] lastEvent = null;
    float d = 0f;
    float newRot = 0f;
    private boolean isZoomAndRotate;
    private boolean isOutSide;
    private static final int NONE = 0;
    private static final int DRAG = 1;
    private static final int ZOOM = 2;
    private int mode = NONE;
    private PointF start = new PointF();
    private PointF mid = new PointF();
    float oldDist = 1f;
    private float xCoOrdinate, yCoOrdinate;
    ImageView gallary;
    ImageView imageviewgallary;
    private GestureDetector gd;
    private int nm = 0;
    private int grcolor = 0;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allinone);
        Intent i = getIntent();

        String title = i.getStringExtra("tit");
        textTitle = findViewById(R.id.allinone);
        Rele = findViewById(R.id.rr1);

        //********************************
        FrameLayout frameLayout = findViewById(R.id.sheet);
        BottomSheetBehavior.from(frameLayout).setPeekHeight(50);
        BottomSheetBehavior.from(frameLayout).setHideable(false);
        BottomSheetBehavior.from(frameLayout).setDraggable(true);


        //********************************
        textTitle.setText(title);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //*************************************************************************
        ImageView likhe = findViewById(R.id.likhe);
        likhe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
//******************************************************************************
        findViewById(R.id.backbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });

        //***************************************************************************

        //*************************************************************************
        gallary = findViewById(R.id.gallary);
        imageviewgallary = findViewById(R.id.imagegallary);
        gallary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*
                try {
                    CropImage.activity()

                            .setAutoZoomEnabled(true)
                            .start(Allinone.this);
                }catch (Exception e)
                {
                    Toast.makeText(Allinone.this, "Pls Select Img Again", Toast.LENGTH_SHORT).show();
                }
*/

            }
        });
        //************************************************************
        //Double tap handel Event
        findViewById(R.id.aa).setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                 if(nm==0)
                 {
                     textTitle.setGravity(Gravity.CENTER | Gravity.START);
                     //Toast.makeText(Allinone.this, "Start", Toast.LENGTH_SHORT).show();
                     nm++;
                 }
                 else if(nm==1)
                 {
                     textTitle.setGravity(Gravity.CENTER | Gravity.END);
                   //  Toast.makeText(Allinone.this, "End", Toast.LENGTH_SHORT).show();
                     nm++;
                 }
                 else if(nm==2)
                 {
                     textTitle.setGravity(Gravity.CENTER);
                    // Toast.makeText(Allinone.this, "Center", Toast.LENGTH_SHORT).show();
                     nm=0;
                 }






               // textTitle.requestApplyInsets();
            }
        });



        //***********************************************************
        //Drag Google Ref..
        Dragtext = (TextView) findViewById(R.id.allinone);
        Dragtext.setOnTouchListener(new View.OnTouchListener() {

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                TextView view = (TextView) v;
                view.bringToFront();
                viewTransformation(view, event);
                return true;
            }
        });

        //*********************************************************

//********************************************************
        //all in one animation
      /* RelativeLayout Sc = findViewById(R.id.rr1);
        AnimationDrawable animationDrawable = (AnimationDrawable) Sc.getBackground();
        animationDrawable.setEnterFadeDuration(-100);
        animationDrawable.setExitFadeDuration(100);
        animationDrawable.start();*/

        // ****************************************************************

        SeekBar seekBar2= findViewById(R.id.seekbar2);
        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
               try {
                   imageviewgallary.setImageAlpha(progress);
               }catch (Exception e)
               {
                   Toast.makeText(Allinone.this, "Pls ReStart The App", Toast.LENGTH_SHORT).show();
               }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        //************************************************************************
        //ssekbarforfont
        SeekBar seekBar = findViewById(R.id.seekbar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textTitle.setTextSize(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // textTitle.setText(seekBar.getMax());
            }
        });

//***********************************
        background = findViewById(R.id.m);
        font = findViewById(R.id.font);
        colore = findViewById(R.id.colore);
        down = findViewById(R.id.Download);
        image = findViewById(R.id.cangeimage);
//**************************************


        fontcolorearray = new int[]{R.color.r28, R.color.r1, R.color.r2, R.color.r3, R.color.r4, R.color.r5,
                R.color.r6, R.color.r7, R.color.r8, R.color.c9, R.color.c10, R.color.c11, R.color.c12, R.color.c13, R.color.c14, R.color.c15, R.color.c16};

        backcolore = new int[]{R.drawable.gardiant, R.drawable.gardiant2, R.drawable.grlist3,
                R.drawable.grlist4, R.drawable.grlist5, R.drawable.grlist5, R.drawable.grlist7,
                R.drawable.grlist8, R.drawable.grlist9, R.drawable.grlist10, R.drawable.grlist11, R.drawable.grlist12};


        cangeimage = new int[]{R.drawable.i1,R.drawable.i2,R.drawable.i3,R.drawable.i5,R.drawable.i6,
                R.drawable.i8,R.drawable.i9,R.drawable.i7,R.drawable.i10};
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int array = cangeimage.length;
                Random random = new Random();
                int Num = random.nextInt(array);
                Rele.setBackground(ContextCompat.getDrawable(getApplicationContext(), cangeimage[Num]));

            }
        });
        background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int array = backcolore.length;
                Random random = new Random();
                int Num = random.nextInt(array);
                Rele.setBackground(ContextCompat.getDrawable(getApplicationContext(), backcolore[Num]));
                //*******************************************************************


            }
        });
        //*******************************************************

        colore.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(Allinone.this,colore);
                popup.getMenuInflater().inflate(R.menu.colorepopup,popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch(item.getItemId())
                        {
/*
                            case R.id.FontColore:
                                new ColorPickerPopup.Builder(Allinone.this)
                                        .initialColor(Color.RED) // Set initial color
                                        .enableBrightness(true) // Enable brightness slider or not
                                        .enableAlpha(true) // Enable alpha slider or not
                                        .okTitle("SET")
                                        .onlyUpdateOnTouchEventUp(true)
                                        .cancelTitle("CANCEL")
                                        .showIndicator(true)
                                        .showValue(true)
                                        .build()

                                        .show(v, new ColorPickerPopup.ColorPickerObserver() {
                                            @Override
                                            public void onColorPicked(int color) {
                                                textTitle.setTextColor(color);
                                            }
                                        });
                                break;
                            case R.id.BackColore:
                                new ColorPickerPopup.Builder(Allinone.this)
                                        .initialColor(Color.RED) // Set initial color
                                        .enableBrightness(true) // Enable brightness slider or not
                                        .enableAlpha(true) // Enable alpha slider or not
                                        .okTitle("SET")
                                        .onlyUpdateOnTouchEventUp(true)
                                        .cancelTitle("CANCEL")
                                        .showIndicator(true)
                                        .showValue(true)
                                        .build()

                                        .show(v, new ColorPickerPopup.ColorPickerObserver() {
                                            @Override
                                            public void onColorPicked(int color) {
                                                Rele.setBackgroundColor(color);
                                            }
                                        });
                                     break;
*/
                        }
                        return false;
                    }
                });
                popup.show();
            }
        });

        //*******************************************************
        font.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random generator = new Random();
                int n = 8;
                n = generator.nextInt(n);
                if (n == 0) {
                    Typeface typeface = ResourcesCompat.getFont(Allinone.this, R.font.allura);
                    textTitle.setTypeface(typeface);
                }
                if (n == 1) {
                    Typeface typeface = ResourcesCompat.getFont(Allinone.this, R.font.satisfy);
                    textTitle.setTypeface(typeface);
                }
                if (n == 2) {
                    Typeface typeface = ResourcesCompat.getFont(Allinone.this, R.font.concertoneregular);
                    textTitle.setTypeface(typeface);
                }
                if (n == 3) {
                    Typeface typeface = ResourcesCompat.getFont(Allinone.this, R.font.berkshireswash);
                    textTitle.setTypeface(typeface);
                }
                if (n == 4) {
                    Typeface typeface = ResourcesCompat.getFont(Allinone.this, R.font.hindi4);
                    textTitle.setTypeface(typeface);
                }
                if (n == 5) {
                    Typeface typeface = ResourcesCompat.getFont(Allinone.this, R.font.hindi3);
                    textTitle.setTypeface(typeface);
                }
                if (n == 6) {
                    Typeface typeface = ResourcesCompat.getFont(Allinone.this, R.font.hindi2);
                    textTitle.setTypeface(typeface);
                }
                if (n == 7) {
                    Typeface typeface = ResourcesCompat.getFont(Allinone.this, R.font.hindi1);
                    textTitle.setTypeface(typeface);
                }

            }
        });
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

// *******************************************************************
                Bitmap Bitmap = getBitmapFromView(Rele);
                // imageView.setImageBitmap(Bitmap);
                SaveImage(Bitmap);
                Toast.makeText(Allinone.this, "Folder: /Storage/ShayariStatus Saved", Toast.LENGTH_SHORT).show();

            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
/*
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                imageviewgallary.setImageURI(resultUri);


            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
*/
    }

    public void openDialog() {
        AxDialog exampleDialog = new AxDialog();

        exampleDialog.show(getSupportFragmentManager(), "example dialog");
    }

    @Override
    public void applyTexts(String shayari) {
        
        textTitle.setText(shayari);

    }


    public static void SaveImage(Bitmap finalBitmap) {

        File myDir = new File(Environment.getExternalStorageDirectory() + "/ShayariStatus");
        myDir.mkdirs();
        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);
        String FileName = "Image-" + n + ".jpg";
        File file = new File(myDir, FileName);
        if (file.exists()) file.delete();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private Bitmap getBitmapFromView(View view) {
        //Define a bitmap with the same size as the view
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        //Bind a canvas to it
        Canvas canvas = new Canvas(returnedBitmap);
        //Get the view's background
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null) {
            //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas);
        } else {
            //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE);
        }
        // draw the view on the canvas
        view.draw(canvas);
        //return the bitmap
        return returnedBitmap;
    }

    private void viewTransformation(View view, MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                xCoOrdinate = view.getX() - event.getRawX();
                yCoOrdinate = view.getY() - event.getRawY();

                start.set(event.getX(), event.getY());
                isOutSide = false;
                mode = DRAG;
                lastEvent = null;
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                oldDist = spacing(event);
                if (oldDist > 10f) {
                    midPoint(mid, event);
                    mode = ZOOM;
                }

                lastEvent = new float[4];
                lastEvent[0] = event.getX(0);
                lastEvent[1] = event.getX(1);
                lastEvent[2] = event.getY(0);
                lastEvent[3] = event.getY(1);
                d = rotation(event);
                break;
            case MotionEvent.ACTION_UP:
                isZoomAndRotate = false;
                if (mode == DRAG) {
                    float x = event.getX();
                    float y = event.getY();
                }
            case MotionEvent.ACTION_OUTSIDE:
                isOutSide = true;
                mode = NONE;
                lastEvent = null;
            case MotionEvent.ACTION_POINTER_UP:
                mode = NONE;
                lastEvent = null;
                break;
            case MotionEvent.ACTION_MOVE:
                if (!isOutSide) {
                    if (mode == DRAG) {
                        isZoomAndRotate = false;
                        view.animate().x(event.getRawX() + xCoOrdinate).y(event.getRawY() + yCoOrdinate).setDuration(0).start();
                    }
                    if (mode == ZOOM && event.getPointerCount() == 2) {
                        float newDist1 = spacing(event);
                        if (newDist1 > 10f) {
                            float scale = newDist1 / oldDist * view.getScaleX();
                            view.setScaleX(scale);
                            view.setScaleY(scale);
                        }
                        if (lastEvent != null) {
                            newRot = rotation(event);
                            view.setRotation((float) (view.getRotation() + (newRot - d)));
                        }
                    }
                }
                break;
        }
    }

    private float rotation(MotionEvent event) {
        double delta_x = (event.getX(0) - event.getX(1));
        double delta_y = (event.getY(0) - event.getY(1));
        double radians = Math.atan2(delta_y, delta_x);
        return (float) Math.toDegrees(radians);
    }

    private float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (int) Math.sqrt(x * x + y * y);
    }

    private void midPoint(PointF point, MotionEvent event) {
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        point.set(x / 2, y / 2);
    }
}