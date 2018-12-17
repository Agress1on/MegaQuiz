package com.example.alexandr.megaquiz.app;

import android.app.Application;
import android.content.Context;


/**
 * Created by Alexandr Mikhalev on 20.10.2018.
 *
 * @author Alexandr Mikhalev
 */
public class App extends Application {

   // private static AppComponent sAppComponent;

    private ComponentsHolder mComponentsHolder;


    public static App getApp(Context context) {
        return (App) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        /*
        sAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        */
        mComponentsHolder = new ComponentsHolder(this);
        mComponentsHolder.init();
    }
    public ComponentsHolder getComponentsHolder() {
        return mComponentsHolder;
    }

    /*
    public static AppComponent getAppComponent() {
        return sAppComponent;
    }
    */

}
