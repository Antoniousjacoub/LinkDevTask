package com.example.antonio.linkdevtask.dagger.module;

import android.app.Application;

import com.example.antonio.linkdevtask.service.ServicesInterface;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by antonio on 1/16/19.
 */

@Module
public class NetworkingModule {
    private Application application;
    private String mBaseUrl;


    public NetworkingModule(Application application, String mBaseUrl) {
        this.application = application;
        this.mBaseUrl = mBaseUrl;
    }

    private Cache provideHttpCache() {
        int cacheSize = 10 * 1024 * 1024;
       return   new Cache(application.getCacheDir(), cacheSize);
    }


    private Gson provideGson() {
        return new GsonBuilder().create();
    }

    @Provides
    @Singleton
    ServicesInterface getServicesInterface() {
        return provideRetrofit().create(ServicesInterface.class);
    }

    private OkHttpClient provideOkhttpClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//        try {
//            // Create a trust manager that does not validate certificate chains
//            final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
//                @Override
//                public void checkClientTrusted(
//                        java.security.cert.X509Certificate[] chain,
//                        String authType) throws CertificateException {
//                }
//
//                @Override
//                public void checkServerTrusted(
//                        java.security.cert.X509Certificate[] chain,
//                        String authType) throws CertificateException {
//                }
//
//                @Override
//                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
//                    return new java.security.cert.X509Certificate[0];
//                }
//            }};
//
//            // Install the all-trusting trust manager
//            final SSLContext sslContext = SSLContext.getInstance("TLS");
//            sslContext.init(null, trustAllCerts,
//                    new java.security.SecureRandom());
//            // Create an ssl socket factory with our all-trusting manager
//            final SSLSocketFactory sslSocketFactory = sslContext
//                    .getSocketFactory();

        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient = okHttpClient.newBuilder()
                .addInterceptor(logging)
                .cache(provideHttpCache())
//                    .sslSocketFactory(sslSocketFactory)
                .hostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER).build();

        return okHttpClient;
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }

    }


    private Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(provideGson()))
                .baseUrl(mBaseUrl)
                .client(provideOkhttpClient())
                .build();
    }
}
