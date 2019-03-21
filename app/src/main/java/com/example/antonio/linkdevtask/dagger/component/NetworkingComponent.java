package com.example.antonio.linkdevtask.dagger.component;

import com.example.antonio.linkdevtask.dagger.module.NetworkingModule;
import com.example.antonio.linkdevtask.service.ServicesInterface;
import com.example.antonio.linkdevtask.ui.main.HomeNewsFeedPresenter;
import javax.inject.Singleton;
import dagger.Component;

/**
 * Created by antonio on 1/16/19.
 */

@Singleton
@Component(modules = {NetworkingModule.class})
public interface NetworkingComponent {
    ServicesInterface getServicesInterface();

    void inject(HomeNewsFeedPresenter homeNewsFeedPresenter);
}