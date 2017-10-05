package ra.sumbayak.maverick.rest;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.*;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class BaseApiInterface<I> {
    
    protected String AUTH_HEADER_NAME = "Authorization";
    protected String TOKEN_SPKEY = "user_token";
    protected String TOKEN_PREFIX = "Token ";
    
    protected String DOMAIN = "http://192.168.43.245/";
    protected String API_PREFIX = "api/";
    protected Context context;
    
    protected BaseApiInterface (Context context) {
        this.context = context;
        init ();
    }
    
    @NonNull protected abstract Class<I> interfaceClass ();
    @NonNull protected abstract String baseUrl ();
    @NonNull protected abstract String spName ();
    protected abstract void init ();
    
    private Interceptor tokenInterceptor (final String token) {
        return new Interceptor () {
            @Override
            public Response intercept (@NonNull Chain chain) throws IOException {
                return chain.proceed (chain.request ()
                                           .newBuilder ()
                                           .addHeader (AUTH_HEADER_NAME, TOKEN_PREFIX + token)
                                           .build ());
            }
        };
    }
    
    public I build () {
        Retrofit.Builder builder = new Retrofit.Builder ()
            .baseUrl (baseUrl ())
            .addConverterFactory (GsonConverterFactory.create ());
    
        SharedPreferences sp = context
            .getSharedPreferences (spName (), Context.MODE_PRIVATE);
        
        if (sp.contains (TOKEN_SPKEY))
            builder.client (new OkHttpClient.Builder ()
                .addInterceptor (tokenInterceptor (sp.getString (TOKEN_SPKEY, "Null")))
                .build ());
        
        return builder.build ().create (interfaceClass ());
    }
}
