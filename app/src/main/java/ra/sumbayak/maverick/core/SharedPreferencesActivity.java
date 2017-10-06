package ra.sumbayak.maverick.core;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;

public class SharedPreferencesActivity extends AppCompatActivity {
    
    public SPHelper getSPHelper () {
        return new SPHelper ();
    }
    
    protected class SPHelper {
        
        private SharedPreferences.Editor spEditor;
        private SharedPreferences sp;
        
        SPHelper () {
            int spnameId = getResources ().getIdentifier ("spname", "string", getPackageName ());
            String spname = getApplicationContext ().getString (spnameId);
            sp = getSharedPreferences (spname, MODE_PRIVATE);
        }
    
        public boolean contains (String key) {
            return sp.contains (key);
        }
        
        public boolean contains (@StringRes int stringId) {
            return contains (getApplicationContext ().getString (stringId));
        }
        
        public String getString (String key) {
            return sp.getString (key, null);
        }
        
        public String getString (@StringRes int stringId) {
            return getString (getApplicationContext ().getString (stringId));
        }
        
        public int getInt (String key) {
            return sp.getInt (key, 0);
        }
        
        public int getInt (@StringRes int stringId) {
            return getInt (getApplicationContext ().getString (stringId));
        }
        
        public boolean getBoolean (String key) {
            return sp.getBoolean (key, false);
        }
        
        public boolean getBoolean (@StringRes int stringId) {
            return getBoolean (getApplicationContext ().getString (stringId));
        }
        
        @SuppressLint ("CommitPrefEdits")
        public SPHelper put (String key, String value) {
            if (spEditor == null)
                spEditor = sp.edit ();
            spEditor.putString (key, value);
            return this;
        }
    
        public SPHelper put (@StringRes int stringId, String value) {
            return put (getApplicationContext ().getString (stringId), value);
        }
    
        @SuppressLint ("CommitPrefEdits")
        public SPHelper put (String key, int value) {
            if (spEditor == null)
                spEditor = sp.edit ();
            spEditor.putInt (key, value);
            return this;
        }
    
        public SPHelper put (@StringRes int stringId, int value) {
            return put (getApplicationContext ().getString (stringId), value);
        }
        
        @SuppressLint ("CommitPrefEdits")
        public SPHelper put (String key, boolean value) {
            if (spEditor == null)
                spEditor = sp.edit ();
            spEditor.putBoolean (key, value);
            return this;
        }
        
        public SPHelper put (@StringRes int stringId, boolean value) {
            return put (getApplicationContext ().getString (stringId), value);
        }
        
        public void apply () {
            spEditor.apply ();
            spEditor = null;
        }
    }
}
