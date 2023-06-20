package com.example.nav.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import android.text.Html;
import android.text.Spanned;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

    public class HomeViewModel extends ViewModel {

        private final MutableLiveData<Spanned> mText;

        public HomeViewModel() {
            mText = new MutableLiveData<>();
            String formattedText = "<h1>Somos Moleroman</h1>"
                    + "<h>Una empresa que se dedica al diseño, desarrollo y comercialización de productos de vanguardia para el tenis.</p>"

            ;
            Spanned spannedText = Html.fromHtml(formattedText);
            mText.setValue(spannedText);
        }

        public MutableLiveData<Spanned> getText() {
            return mText;
        }
    }
