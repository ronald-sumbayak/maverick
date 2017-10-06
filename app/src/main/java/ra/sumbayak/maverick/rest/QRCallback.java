package ra.sumbayak.maverick.rest;

import android.support.annotation.NonNull;
import android.util.Log;

import retrofit2.*;

public abstract class QRCallback<T> implements Callback<T> {
    
    @Override
    public void onResponse (@NonNull Call<T> call, @NonNull Response<T> response) {
        T body = response.body ();
        Log.i ("QRCallback", call.request ().toString ());
        Log.i ("QRCallback", response.toString ());
        Log.i ("QRCallback", String.valueOf (body));
        
        if (response.isSuccessful ())
            if (body != null && isSuccess (body)) onSuccessful (body);
            else onFailure ();
        else if (response.code () == 401) onUnauthorized ();
        else onFailure ();
        onExit ();
    }
    
    @Override
    public void onFailure (@NonNull Call<T> call, @NonNull Throwable t) {
        Log.e ("QRCallback", call.request ().toString ());
        t.printStackTrace ();
        onFailure ();
    }
    
    protected abstract void onSuccessful (@NonNull T body);
    
    protected boolean isSuccess (@NonNull T body) {
        return true;
    }
    
    protected void onUnauthorized () {
        
    }
    
    protected void onFailure () {
        
    }
    
    protected void onExit () {
        
    }
}
