package com.example.andrealorenzoni.appprovaandroid;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity implements DialogPopup.dialogReturnListener{
    private static final String TAG = "MyActivity";
    DialogPopup md;
    int statoBottoneTracking = 0;
    int statoBottoneDownload = 0;
    int statoBottoneRec = 0;
    AnimationDrawable animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView scritta_tocca_zona = (TextView)(this.findViewById(R.id.scritta_tocca_zona));
        scritta_tocca_zona.setVisibility(View.INVISIBLE);
        Button downloading = (Button)(this.findViewById(R.id.downloading));
        downloading.setVisibility(View.INVISIBLE);

        // push bottone setting
        ImageButton ib = (ImageButton)(this.findViewById(R.id.load_popup));
        ib.setClickable(true);
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                md = DialogPopup.newInstance(0);
                md.show(ft, "popup");
            }
        });
        // push bottone marker
        Button ib_marker = (Button)(this.findViewById(R.id.load_popup_marker));
        ib_marker.setClickable(true);
        ib_marker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                md = DialogPopup.newInstance(3);
                md.show(ft, "popup");
            }
        });

        // push bottone traccia
        Button ib_traccia = (Button)(this.findViewById(R.id.load_popup_traccia));
        ib_traccia.setClickable(true);
        ib_traccia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                md = DialogPopup.newInstance(8);
                md.show(ft, "popup");
            }
        });

        // push bottone record
        final ImageButton ib_record = (ImageButton)(this.findViewById(R.id.bottone_record));
        ib_record.setClickable(true);
        ib_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG,"clicca su record "+statoBottoneRec);
                if (statoBottoneRec==0) {
                    Log.v(TAG,"start animation");
                    ib_record.setBackgroundResource(R.drawable.animation_rec);
                    statoBottoneRec = 1;
                    animation = (AnimationDrawable) ib_record.getBackground();
                    animation.start();
                }
                else if (statoBottoneRec == 1){
                    Log.v(TAG,"stop animation");
                    //animation.stop();
                    //ib_record.setBackgroundResource(R.drawable.round_button);
                    LinearLayout recButton = (LinearLayout)findViewById(R.id.bottone_record_layout);
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    md = DialogPopup.newInstanceRecPopup(14,recButton.getLeft(),recButton.getTop());
                    md.show(ft, "popup");
                }
                else if (statoBottoneRec ==2) {
                    ib_record.setImageResource(R.drawable.record_a);
                    ib_record.setBackgroundResource(R.drawable.animation_rec);
                    statoBottoneRec = 1;
                    animation = (AnimationDrawable) ib_record.getBackground();
                    animation.start();
                }
            }
        });

        // push bottone tracking
        ImageButton ib_bottone_tracking = (ImageButton)(this.findViewById(R.id.bottone_tracking));
        ib_bottone_tracking.setClickable(true);
        ib_bottone_tracking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG,"clicca su tracking");
                if (statoBottoneTracking == 0){
                    statoBottoneTracking = 1;
                    String statoBottoneStr = Integer.toString(statoBottoneTracking);
                    Log.v(TAG,statoBottoneStr);
                    ImageButton btn_tracking = (ImageButton)findViewById(R.id.bottone_tracking);
                    btn_tracking.setImageResource(R.drawable.gps_freccia);
                    btn_tracking.setBackgroundResource(R.drawable.round_button_pressed);
                }
                else if (statoBottoneTracking == 1){
                    statoBottoneTracking = 2;
                    String statoBottoneStr = Integer.toString(statoBottoneTracking);
                    Log.v(TAG,statoBottoneStr);
                    ImageButton btn_tracking = (ImageButton)findViewById(R.id.bottone_tracking);
                    btn_tracking.setImageResource(R.drawable.gps_freccia_visore);
                }
                else if (statoBottoneTracking == 2){
                    statoBottoneTracking = 0;
                    String statoBottoneStr = Integer.toString(statoBottoneTracking);
                    Log.v(TAG,statoBottoneStr);
                    ImageButton btn_tracking = (ImageButton)findViewById(R.id.bottone_tracking);
                    btn_tracking.setImageResource(R.drawable.tracking_a);
                    btn_tracking.setBackgroundResource(R.drawable.bottone_cerchio_cambia);
                }
            }
        });

        // push bottone ricerca
        ImageButton ib_bottone_ricerca = (ImageButton)(this.findViewById(R.id.bottone_ricerca));
        ib_bottone_ricerca.setClickable(true);
        ib_bottone_ricerca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG,"clicca su ricerca");
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                md = DialogPopup.newInstance(15);
                md.show(ft, "popup");
            }
        });

        // push bottone camera
        ImageButton ib_bottone_camera = (ImageButton)(this.findViewById(R.id.bottone_camera));
        ib_bottone_camera.setClickable(true);
        ib_bottone_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG,"clicca su camera");
            }
        });

        // push bottone zoom in
        ImageButton ib_bottone_zoom_in = (ImageButton)(this.findViewById(R.id.bottone_zoom_in));
        ib_bottone_zoom_in.setClickable(true);
        ib_bottone_zoom_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG,"clicca su zoom in");
            }
        });

        // push bottone scale
        ImageButton ib_bottone_scale = (ImageButton)(this.findViewById(R.id.bottone_scale));
        ib_bottone_scale.setClickable(true);
        ib_bottone_scale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG,"clicca su scale");
            }
        });

        // push bottone zoom_out
        ImageButton ib_bottone_zoom_out = (ImageButton)(this.findViewById(R.id.bottone_zoom_out));
        ib_bottone_zoom_out.setClickable(true);
        ib_bottone_zoom_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG,"clicca su zoom out");
            }
        });

        // push bottone pro
        ImageButton ib_bottone_pro = (ImageButton)(this.findViewById(R.id.bottone_pro));
        ib_bottone_pro.setClickable(true);
        ib_bottone_pro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                md = DialogPopup.newInstance(7);
                md.show(ft, "popup");
            }
        });

        // push bottone download
        ImageButton ib_bottone_download = (ImageButton)(this.findViewById(R.id.bottone_download));
        ib_bottone_download.setClickable(true);
        ib_bottone_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG,"clicca su download");
                ImageButton btn_tracking_vis = (ImageButton)findViewById(R.id.bottone_tracking);
                ImageButton btn_cerca_vis = (ImageButton)findViewById(R.id.bottone_ricerca);
                ImageButton btn_record_vis = (ImageButton)findViewById(R.id.bottone_record);
                ImageButton btn_camera_vis = (ImageButton)findViewById(R.id.bottone_camera);
                ImageButton btn_zoomIn_vis = (ImageButton)findViewById(R.id.bottone_zoom_in);
                ImageButton btn_zoomOut_vis = (ImageButton)findViewById(R.id.bottone_zoom_out);
                ImageButton btn_scale_vis = (ImageButton)findViewById(R.id.bottone_scale);
                ImageButton btn_settings_vis = (ImageButton)findViewById(R.id.load_popup);
                LinearLayout linear_layout_fondo = (LinearLayout)findViewById(R.id.linear_layout_fondo);
                LinearLayout linear_layout_zoom = (LinearLayout)findViewById(R.id.linear_layout_zoom);
                TextView scritta_tocca_zona = (TextView)findViewById(R.id.scritta_tocca_zona);

                if (statoBottoneDownload == 0){
                    btn_tracking_vis.setVisibility(View.INVISIBLE);
                    btn_cerca_vis.setVisibility(View.INVISIBLE);
                    btn_record_vis.setVisibility(View.INVISIBLE);
                    btn_camera_vis.setVisibility(View.INVISIBLE);
                    btn_zoomIn_vis.setVisibility(View.INVISIBLE);
                    btn_zoomOut_vis.setVisibility(View.INVISIBLE);
                    btn_scale_vis.setVisibility(View.INVISIBLE);
                    btn_settings_vis.setVisibility(View.INVISIBLE);
                    linear_layout_fondo.setBackgroundColor(0xB3000000);
                    linear_layout_zoom.setBackgroundColor(0x00000000);
                    scritta_tocca_zona.setVisibility(View.VISIBLE);
                    statoBottoneDownload = 1;
                }
                else {
                    btn_tracking_vis.setVisibility(View.VISIBLE);
                    btn_cerca_vis.setVisibility(View.VISIBLE);
                    btn_record_vis.setVisibility(View.VISIBLE);
                    btn_camera_vis.setVisibility(View.VISIBLE);
                    btn_zoomIn_vis.setVisibility(View.VISIBLE);
                    btn_zoomOut_vis.setVisibility(View.VISIBLE);
                    btn_settings_vis.setVisibility(View.VISIBLE);
                    btn_scale_vis.setVisibility(View.VISIBLE);
                    linear_layout_fondo.setBackgroundColor(0x00000000);
                    linear_layout_zoom.setBackgroundResource(R.drawable.rounded_rettangle_button);
                    scritta_tocca_zona.setVisibility(View.INVISIBLE);
                    statoBottoneDownload = 0;
                    Button downloading = (Button)findViewById(R.id.downloading);
                    downloading.setVisibility(View.INVISIBLE);
                    downloading.clearAnimation();
                }

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onStop() {
        super.onStop();
        try {
            if (this.md != null)
                this.md.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dettagliMarker(View v) {
        if (this.md != null)
            this.md.closePopup(0);
        Log.v(TAG,"clicca su scritta dettagli marker");
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        this.md = DialogPopup.newInstance(4);
        this.md.show(ft, "popup");
    }

    public void dettagliMarkerSettaggi(View v) {
        Log.v(TAG,"clicca su scritta settaggi marker");
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        this.md = DialogPopup.newInstance(5);
        this.md.show(ft, "popup");
    }

    public void dettagliMarkerCoordinate(View v) {
        Log.v(TAG,"clicca su scritta settaggi marker");
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        this.md = DialogPopup.newInstance(6);
        this.md.show(ft, "popup");
    }

    public void dettagliTraccia(View v) {
        if (this.md != null)
            this.md.closePopup(0);
        Log.v(TAG,"clicca su scritta dettagli traccia");
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        this.md = DialogPopup.newInstance(9);
        this.md.show(ft, "popup");
    }

    public void dettagliTracciaSettaggi(View v) {
        Log.v(TAG,"clicca su scritta settaggi traccia");
        FragmentTransaction ft = getFragmentManager().beginTransaction();

        this.md = DialogPopup.newInstance(10);
        this.md.show(ft, "popup");
    }

    public void dettagliTracciaDurata(View v) {
        Log.v(TAG,"clicca su scritta durata traccia");
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        this.md = DialogPopup.newInstance(11);
        this.md.show(ft, "popup");
    }

    public void dettagliTracciaVelocita(View v) {
        Log.v(TAG,"clicca su scritta velocita traccia");
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        this.md = DialogPopup.newInstance(12);
        this.md.show(ft, "popup");
    }

    public void dettagliTracciaAltitudine(View v) {
        Log.v(TAG,"clicca su scritta altitudine traccia");
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        this.md = DialogPopup.newInstance(13);
        this.md.show(ft, "popup");
    }

    public void pushAiutoSito(View v) {
        Log.v(TAG,"clicca su aiuto sito");
    }

    public void pushTutorial(View v) {
        Log.v(TAG,"clicca su tutorial");
    }

    public void pushRipristinaAcquisti(View v) {
        Log.v(TAG,"clicca su ripristina acquisti");
    }

    public void pushTerminiUso(View v) {
        Log.v(TAG,"clicca su termini uso");
    }

    public void iconaTipo1(View v) {
        Log.v(TAG,"clicca su icona tipo 1");
    }

    public void iconaTipo2(View v) {
        Log.v(TAG,"clicca su icona tipo 2");
    }

    public void iconaTipo3(View v) {
        Log.v(TAG,"clicca su icona tipo 3");
    }

    public void iconaTipo4(View v) {
        Log.v(TAG,"clicca su icona tipo 4");
    }

    public void iconaTipo5(View v) {
        Log.v(TAG,"clicca su icona tipo 5");
    }

    public void iconaTipo6(View v) {
        Log.v(TAG,"clicca su icona tipo 6");
    }

    public void iconaTipo7(View v) {
        Log.v(TAG,"clicca su icona tipo 7");
    }

    public void tracciaTipo1(View v) {
        Log.v(TAG,"clicca su traccia tipo 1");
    }

    public void tracciaTipo2(View v) {
        Log.v(TAG,"clicca su traccia tipo 2");
    }

    public void tracciaTipo3(View v) {
        Log.v(TAG,"clicca su traccia tipo 3");
    }

    public void tracciaTipo4(View v) {
        Log.v(TAG,"clicca su traccia tipo 4");
    }

    public void tracciaTipo5(View v) {
        Log.v(TAG,"clicca su traccia tipo 5");
    }

    public void tracciaTipo6(View v) {
        Log.v(TAG,"clicca su traccia tipo 6");
    }

    public void tracciaTipo7(View v) {
        Log.v(TAG,"clicca su traccia tipo 7");
    }

    public void returnValue(int tipo) {
        Log.d(TAG,"return tipo "+tipo);
        if (tipo==14) {
            pauseRec();
        }
    }

    public void pauseRec() {
        this.statoBottoneRec = 2;
        ImageButton ib_record = (ImageButton)(this.findViewById(R.id.bottone_record));
        animation = (AnimationDrawable) ib_record.getBackground();
        animation.stop();
        ib_record.setBackgroundResource(R.drawable.round_button);
        ib_record.setImageResource(R.drawable.gps_freccia_visore);
        Log.d(TAG,"pause rec");
    }

    public void scaricaMappa(View v) {
        if (statoBottoneDownload==1) {
            TextView scritta_tocca_zona = (TextView) (this.findViewById(R.id.scritta_tocca_zona));
            scritta_tocca_zona.setVisibility(View.INVISIBLE);
            Button downloading = (Button) (this.findViewById(R.id.downloading));
            downloading.setVisibility(View.VISIBLE);
            final Animation aAnim = new AlphaAnimation(1.0f, 0.0f);
            aAnim.setRepeatCount(Animation.INFINITE);
            aAnim.setDuration(1000);
            downloading.startAnimation(aAnim);
        }
    }
}
