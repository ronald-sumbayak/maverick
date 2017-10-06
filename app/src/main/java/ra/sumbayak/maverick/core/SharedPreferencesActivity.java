package ra.sumbayak.maverick.core;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.*;
import android.support.v7.app.AppCompatActivity;

public class SharedPreferencesActivity extends AppCompatActivity {
    
    private SharedPreferences.Editor spEditor;
    private SharedPreferences sp;
    
    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        int spnameId = getResources ().getIdentifier ("spname", "string", getPackageName ());
        String spname = getString (spnameId);
        sp = getSharedPreferences (spname, MODE_PRIVATE);
    }
    
    @SuppressLint ("CommitPrefEdits")
    protected SharedPreferencesActivity putSP (String key, String value) {
        if (spEditor == null)
            spEditor = sp.edit ();
        spEditor.putString (key, value);
        return this;
    }
    
    @SuppressLint ("CommitPrefEdits")
    protected SharedPreferencesActivity putSP (String key, int value) {
        if (spEditor == null)
            spEditor = sp.edit ();
        spEditor.putInt (key, value);
        return this;
    }
    
    @SuppressLint ("CommitPrefEdits")
    protected SharedPreferencesActivity putSP (String key, boolean value) {
        if (spEditor == null)
            spEditor = sp.edit ();
        spEditor.putBoolean (key, value);
        return this;
    }
    
    @SuppressLint ("CommitPrefEdits")
    protected SharedPreferencesActivity putSP (@StringRes int stringId, String value) {
        if (spEditor == null)
            spEditor = sp.edit ();
        spEditor.putString (getString (stringId), value);
        return this;
        
    }
    @SuppressLint ("CommitPrefEdits")
    protected SharedPreferencesActivity putSP (@StringRes int stringId, int value) {
        if (spEditor == null)
            spEditor = sp.edit ();
        spEditor.putInt (getString (stringId), value);
        return this;
    }
    
    @SuppressLint ("CommitPrefEdits")
    protected SharedPreferencesActivity putSP (@StringRes int stringId, boolean value) {
        if (spEditor == null)
            spEditor = sp.edit ();
        spEditor.putBoolean (getString (stringId), value);
        return this;
    }
    
    protected void applySP () {
        spEditor.apply ();
        spEditor = null;
    }
}
