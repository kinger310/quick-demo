package com.wd.demo.design.p08_facade;

/**
 * @Author: wangd
 * @Date: 2023/4/28 17:19
 */
public class HomeTheaterFacade {
    private TheaterLight theaterLight = TheaterLight.getInstance();
    private Popcorn popcorn = Popcorn.getInstance();
    private Stereo stereo = Stereo.getInstance();
    private Projector projector = Projector.getInstance();
    private Screen screen = Screen.getInstance();
    private DVDPlayer dVDPlayer = DVDPlayer.getInstanc();

    public HomeTheaterFacade() {
    }

    public void ready() {
        this.popcorn.on();
        this.popcorn.pop();
        this.screen.down();
        this.projector.on();
        this.stereo.on();
        this.dVDPlayer.on();
        this.theaterLight.dim();
    }

    public void play() {
        this.dVDPlayer.play();
    }

    public void pause() {
        this.dVDPlayer.pause();
    }

    public void end() {
        this.popcorn.off();
        this.theaterLight.bright();
        this.screen.up();
        this.projector.off();
        this.stereo.off();
        this.dVDPlayer.off();
    }
}

