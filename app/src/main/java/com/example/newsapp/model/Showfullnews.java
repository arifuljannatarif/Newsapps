package com.example.newsapp.model;

import android.annotation.SuppressLint;
import android.app.DialogFragment;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.newsapp.R;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Showfullnews.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Showfullnews#newInstance} factory method to
 * create an instance of this fragment.
 */
@SuppressLint("ValidFragment")
public class Showfullnews extends DialogFragment {

    ImageView imageView;
    TextView linklbl;
    WebView webView;
   ProgressBar progressBar;
    private OnFragmentInteractionListener mListener;
    String image,link;
    public Showfullnews(String link, String image) {
        this.image=image;
        this.link=link;
    }

    public static Showfullnews newInstance(String param1, String param2) {
        Showfullnews fragment = new Showfullnews(param1,param2);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_FRAME, R.style.FullScreenDialogStyle);


    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().getWindow().getAttributes().windowAnimations=R.style.FullScreenDialogStyle;
        Snackbar.make(getView(),"Click on red area to dissmiss",Snackbar.LENGTH_LONG).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_show_fullnews, container, false);
         webView=view.findViewById(R.id.sfdwebview);
         linklbl=view.findViewById(R.id.sfd_linklbl);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
                //super.onReceivedSslError(view, handler, error);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                return true;
            }
        });
         webView.getSettings().setUserAgentString("Android WebView");
    webView.getSettings().setJavaScriptEnabled(true);
         webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
         webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
         webView.getSettings().setAppCacheEnabled(true);
         webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
         webView.setLayerType(View.LAYER_TYPE_HARDWARE,null);
         webView.getSettings().setDomStorageEnabled(true);
         webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
         /*
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);

            }
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                    progressBar.setVisibility(View.GONE);
            }
        });
        */
        progressBar=view.findViewById(R.id.web_progress);
      // Picasso.get().load(image).error(R.drawable.error_image).into(imageView);
        linklbl.setText(link);

        webView.loadUrl(link);
        return view;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
