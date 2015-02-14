package com.example.andrealorenzoni.appprovaandroid;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;


import kankan.wheel.widget.OnWheelChangedListener;
import kankan.wheel.widget.OnWheelScrollListener;
import kankan.wheel.widget.WheelView;
import kankan.wheel.widget.adapters.ArrayWheelAdapter;

public class DialogPopup extends DialogFragment {

    private static final String TAG = "MyPopup";
    private int tipo;
    private int coordX;
    private int coordY;

    String[] wheelMenu1;
    String[] wheelMenu2;
    String[] wheelMenu3;
    // Wheel scrolled flag
    private boolean wheelScrolled = false;
    private int currentTab = 0;

    public static DialogPopup newInstance(int tipo) {
        DialogPopup frag = new DialogPopup();
        Bundle args = new Bundle();
        args.putInt("type", tipo);
        frag.setArguments(args);
        return frag;
    }

    public static DialogPopup newInstanceRecPopup(int tipo, int x, int y) {
        DialogPopup frag = new DialogPopup();
        Bundle args = new Bundle();
        args.putInt("type", tipo);
        args.putInt("coordX", x);
        args.putInt("coordY", y);
        frag.setArguments(args);

        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setRetainInstance(true);
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth);
        this.tipo = getArguments().getInt("type");
        this.coordX = getArguments().getInt("coordX");
        this.coordY = getArguments().getInt("coordY");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getDialog().setCanceledOnTouchOutside(true);

        // Get the layout inflater
        inflater = getActivity().getLayoutInflater();

        // popUp
        if (tipo == 0) {
            final View rootView = inflater.inflate(R.layout.layout1, null);

            // push bottone back home
            ImageButton ib = (ImageButton)(rootView.findViewById(R.id.bottone_back));
            ib.setClickable(true);
            ib.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    closePopup(tipo);
                }
            });
            return rootView;
        }

        // popUp
        else if (tipo == 1) {
            final View rootView = inflater.inflate(R.layout.layout1, null);

            // push bottone back home
            ImageButton ib = (ImageButton)(rootView.findViewById(R.id.bottone_back));
            ib.setClickable(true);
            ib.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    closePopup(tipo);
                }
            });
            return rootView;
        }

        // popUp
        else if (tipo == 2) {
            final View rootView = inflater.inflate(R.layout.layout1, null);

            // push bottone back home
            ImageButton ib = (ImageButton)(rootView.findViewById(R.id.bottone_back));
            ib.setClickable(true);
            ib.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    closePopup(tipo);
                }
            });
            return rootView;
        }

        // popUp
        else if (tipo == 3) {
            final View rootView = inflater.inflate(R.layout.layout_info_marker, null);


            return rootView;
        }

        // popUp
        else if (tipo == 4) {
            final View rootView = inflater.inflate(R.layout.layout_dettagli_marker, null);

            // push bottone back home
            ImageButton ib = (ImageButton)(rootView.findViewById(R.id.bottone_backHome_marker));
            ib.setClickable(true);
            ib.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    closePopup(tipo);
                }
            });
            return rootView;
        }

        // popUp
        else if (tipo == 5) {
            final View rootView = inflater.inflate(R.layout.layout_settaggi_traccia, null);

            // push bottone back home
            ImageButton ib = (ImageButton)(rootView.findViewById(R.id.bottone_back));
            ib.setClickable(true);
            ib.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    closePopup(tipo);
                }
            });
            return rootView;
        }

        else if (tipo == 6) {
            final View rootView = inflater.inflate(R.layout.layout_coordinate_marker, null);

            wheelMenu1 = new String[181];
            for (int i = 0; i <= 180; i++) {
                wheelMenu1[i] = ""+(i-90)+"°";
            }
            wheelMenu2 = new String[181];
            for (int i = 0; i <= 180; i++) {
                wheelMenu2[i] = ""+(i-90)+"°";
            }
            wheelMenu3 = new String[181];
            for (int i = 0; i <= 180; i++) {
                wheelMenu3[i] = ""+(i-90)+"°";
            }
            initWheel(rootView, R.id.p1, wheelMenu1);
            initWheel(rootView, R.id.p2, wheelMenu2);
            initWheel(rootView, R.id.p3, wheelMenu3);

            // push bottone back home
            ImageButton ib = (ImageButton)(rootView.findViewById(R.id.bottone_back));
            ib.setClickable(true);
            ib.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    closePopup(tipo);
                }
            });

            // push bottone conferma coordinate
            TextView tv = (TextView)(rootView.findViewById(R.id.coord_confirm));
            tv.setClickable(true);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    WheelView wv1 = getWheel(R.id.p1);
                    WheelView wv2 = getWheel(R.id.p2);
                    WheelView wv3 = getWheel(R.id.p3);
                    String coord1 = wheelMenu1[wv1.getCurrentItem()];
                    String coord2 = wheelMenu2[wv2.getCurrentItem()];
                    String coord3 = wheelMenu3[wv3.getCurrentItem()];
                    Log.d(TAG, "coord: " + coord1 + "," + coord2 + "," + coord3);
                    Toast.makeText(getDialog().getContext(), "coord: "+coord1+","+coord2+","+coord3,
                            Toast.LENGTH_LONG).show();
                }
            });

            return rootView;
        }

        else if (tipo == 7) {
            final View rootView = inflater.inflate(R.layout.layout_pro, null);

            getDialog().setCanceledOnTouchOutside(true);

            return rootView;
        }

        else if (tipo == 8) {
            final View rootView = inflater.inflate(R.layout.layout_info_traccia, null);


            return rootView;
        }

        else if (tipo == 9) {
            final View rootView = inflater.inflate(R.layout.layout_dettagli_traccia, null);

            // push bottone back home
            ImageButton ib = (ImageButton)(rootView.findViewById(R.id.bottone_backHome_traccia));
            ib.setClickable(true);
            ib.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    closePopup(tipo);
                }
            });

            return rootView;
        }

        else if (tipo == 10) {
            final View rootView = inflater.inflate(R.layout.layout_settaggi_traccia, null);

            // push bottone back home
            ImageButton ib = (ImageButton)(rootView.findViewById(R.id.bottone_back));
            ib.setClickable(true);
            ib.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    closePopup(tipo);
                }
            });
            return rootView;
        }

        else if (tipo == 11) {
            final View rootView = inflater.inflate(R.layout.layout_durata_traccia, null);

            // push bottone back home
            ImageButton ib = (ImageButton)(rootView.findViewById(R.id.bottone_back));
            ib.setClickable(true);
            ib.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    closePopup(tipo);
                }
            });
            return rootView;
        }

        else if (tipo == 12) {
            final View rootView = inflater.inflate(R.layout.layout_velocita_traccia, null);

            // push bottone back home
            ImageButton ib = (ImageButton)(rootView.findViewById(R.id.bottone_back));
            ib.setClickable(true);
            ib.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    closePopup(tipo);
                }
            });
            return rootView;
        }

        else if (tipo == 13) {
            final View rootView = inflater.inflate(R.layout.layout_altitudine_traccia, null);

            // push bottone back home
            ImageButton ib = (ImageButton)(rootView.findViewById(R.id.bottone_back));
            ib.setClickable(true);
            ib.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    closePopup(tipo);
                }
            });
            return rootView;
        }

        else if (tipo == 14) {
            final View rootView = inflater.inflate(R.layout.layout_rec_button, null);

            // push bottone back home
            TextView tv = (TextView)(rootView.findViewById(R.id.bottone_pause_rec));
            tv.setClickable(true);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    closePopup(tipo);
                }
            });

            Window window = getDialog().getWindow();
            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.gravity = Gravity.TOP | Gravity.LEFT;
            lp.x = this.coordX + 50;
            lp.y = this.coordY - 10;
            window.setAttributes(lp);
            return rootView;
        }

        else if (tipo == 15) {
            final View rootView = inflater.inflate(R.layout.layout_search, null);

            // get our tabHost from the xml
            final TabHost tabHost = (TabHost)rootView.findViewById(R.id.tabhost);
            tabHost.setup();

            // create tab 1
            TabHost.TabSpec spec1 = tabHost.newTabSpec("tab1");
            spec1.setIndicator("LISTVIEW1");
            spec1.setContent(R.id.tab1);
            tabHost.addTab(spec1);
            //create tab2
            TabHost.TabSpec spec2 = tabHost.newTabSpec("tab2");
            spec2.setIndicator("LISTVIEW2");
            spec2.setContent(R.id.tab2);
            tabHost.addTab(spec2);
            //create tab3
            TabHost.TabSpec spec3 = tabHost.newTabSpec("tab3");
            spec3.setIndicator("LISTVIEW3");
            spec3.setContent(R.id.tab3);
            tabHost.addTab(spec3);

            tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
                @Override
                public void onTabChanged(String arg0) {
                    currentTab = tabHost.getCurrentTab();
                    Log.d(TAG,"Tab selected "+currentTab);
                    Toast.makeText(getDialog().getContext(),"Tab selected "+currentTab,
                            Toast.LENGTH_LONG).show();
                }
            });

            return rootView;
        }

        return null;
    }

    // Wheel scrolled listener
    OnWheelScrollListener scrolledListener = new OnWheelScrollListener()  {
        @Override
        public void onScrollingStarted(WheelView wheel) {
            wheelScrolled = true;
        }
        @Override
        public void onScrollingFinished(WheelView wheel) {
            wheelScrolled = false;
            updateStatus();
        }
    };

    // Wheel changed listener
    private final OnWheelChangedListener changedListener = new OnWheelChangedListener() {
        @Override
        public void onChanged(WheelView wheel, int oldValue, int newValue) {
            Log.d(TAG, "onChanged, wheelScrolled = " + wheelScrolled);
            if (!wheelScrolled) {
                updateStatus();
            }
        }
    };

    private void updateStatus() {
    }

    private void initWheel(View rootView, int id, String[] wheelMenu) {
        WheelView wheel = (WheelView)rootView.findViewById(id);
        wheel.setViewAdapter(new ArrayWheelAdapter<String>(getDialog().getContext(), wheelMenu));
        wheel.setVisibleItems(4);
        wheel.setCurrentItem(90);
        wheel.addChangingListener(changedListener);
        wheel.addScrollingListener(scrolledListener);
    }

    private WheelView getWheel(int id) {
        return (WheelView)this.getDialog().findViewById(id);
    }

    public interface dialogReturnListener {
        void returnValue(int tipo);
    }

    public void closePopup(int tipo) {
        Log.v(TAG, "chiudo popup "+tipo);
        // safety check
        if (getDialog() == null) {
            return;
        }
        dialogReturnListener listener = (dialogReturnListener) getActivity();
        listener.returnValue(tipo);
        getDialog().dismiss();
    }

    @Override
    public void onStart() {
        super.onStart();
        // safety check
        if (getDialog() == null) {
            return;
        }
        // set the animations to use on showing and hiding the dialog
        getDialog().getWindow().setWindowAnimations(R.style.dialog_animation_fade);
    }

    @Override
    public void onResume() {
        super.onResume();
        // safety check
        if (getDialog() == null) {
            return;
        }
        if (this.tipo == 3 || this.tipo == 8 || this.tipo==14){}
        else {
            int width = getResources().getDimensionPixelSize(R.dimen.popup_width);
            int height = getResources().getDimensionPixelSize(R.dimen.popup_height_portrait);
            getDialog().getWindow().setLayout(width, height);
        }
    }

    @Override
    public void onDestroyView() {
        if (getDialog() != null && getRetainInstance()) {
            getDialog().setDismissMessage(null);
        }
        super.onDestroyView();
    }
}