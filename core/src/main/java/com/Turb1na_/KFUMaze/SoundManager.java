package com.Turb1na_.KFUMaze;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class SoundManager {
    final Main game;
    public float MusicVolume;
    public float SoundVolume;
    public Sound SoundBtn = Gdx.audio.newSound(Gdx.files.internal("Audio/ButtonSound.wav"));
    public Music MenuMusic = Gdx.audio.newMusic(Gdx.files.internal("Audio/MenuMusic.mp3"));
    public Music Blocked = Gdx.audio.newMusic(Gdx.files.internal("Audio/blocked.mp3"));
    public Sound Star= Gdx.audio.newSound(Gdx.files.internal("Audio/Star.mp3"));
    public Music Win = Gdx.audio.newMusic(Gdx.files.internal("Audio/Win.mp3"));
    public Sound WinStar = Gdx.audio.newSound(Gdx.files.internal("Audio/WinStar.wav"));
    public Sound Die = Gdx.audio.newSound(Gdx.files.internal("Audio/Die.mp3"));
    public com.badlogic.gdx.audio.Music GameMusic = Gdx.audio.newMusic(Gdx.files.internal("Audio/GameMusic.mp3"));


    public SoundManager(final Main game){
        this.game=game;
        MusicVolume = game.prefs.getFloat("Music");
        SoundVolume= game.prefs.getFloat("Sound");

        MenuMusic.setLooping(true);
        GameMusic.setLooping(true);
    }

    public void setVolume(){
        MenuMusic.setVolume(MusicVolume);
        Blocked.setVolume(SoundVolume);
        Win.setVolume(MusicVolume);
        GameMusic.setVolume(MusicVolume);
    }

    public void dispose(){
        SoundBtn.dispose();
        MenuMusic.dispose();
        Blocked.dispose();
        Star.dispose();
        Win.dispose();
        WinStar.dispose();
        Die.dispose();
        GameMusic.dispose();
    }

    public void MenuMusicPlay(){
        MenuMusic.play();
    }

    public void MenuMusicStop(){
        MenuMusic.stop();
    }
}
