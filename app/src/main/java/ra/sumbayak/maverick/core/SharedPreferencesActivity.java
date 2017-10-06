package ra.sumbayak.maverick.core;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;

public class SharedPreferencesActivity extends AppCompatActivity {
    
    private SPHelper sp;
    
    public SPHelper getSPHelper () {
        return sp;
    }
    
    protected class SPHelper {
        
        private SharedPreferences.Editor spEditor;
        private SharedPreferences sp;
        
        SPHelper () {
            int spnameId = getResources ().getIdentifier ("spname", "string", getPackageName ());
            String spname = getString (spnameId);
            sp = getSharedPreferences (spname, MODE_PRIVATE);
        }
        
        public boolean containsSP (@StringRes int stringId) {
            return containsSP (getString (stringId));
        }
        
        public boolean containsSP (String key) {
            return sp.contains (key);
        }
        
        @SuppressLint ("CommitPrefEdits")
        public SPHelper putSP (String key, String value) {
            if (spEditor == null)
                spEditor = sp.edit ();
            spEditor.putString (key, value);
            return this;
        }
        
        @SuppressLint ("CommitPrefEdits")
        public SPHelper putSP (String key, int value) {
            if (spEditor == null)
                spEditor = sp.edit ();
            spEditor.putInt (key, value);
            return this;
        }
        
        @SuppressLint ("CommitPrefEdits")
        public SPHelper putSP (String key, boolean value) {
            if (spEditor == null)
                spEditor = sp.edit ();
            spEditor.putBoolean (key, value);
            return this;
        }
        
        public SPHelper putSP (@StringRes int stringId, String value) {
            return putSP (getString (stringId), value);
        }
        
        public SPHelper putSP (@StringRes int stringId, int value) {
            return putSP (getString (stringId), value);
        }
        
        protected SPHelper putSP (@StringRes int stringId, boolean value) {
            return putSP (getString (stringId), value);
        }
        
        public void applySP () {
            spEditor.apply ();
            spEditor = null;
        }
    }
}
