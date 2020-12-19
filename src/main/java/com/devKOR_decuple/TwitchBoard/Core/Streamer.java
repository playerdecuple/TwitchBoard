package com.devKOR_decuple.TwitchBoard.Core;

public class Streamer {

    private final String streamer;
    private final String oauthKey;

    public Streamer(String streamer, String oauthKey) {
        this.streamer = streamer;
        this.oauthKey = oauthKey;
    }

    public String getStreamer() {
        return streamer;
    }

    public String getOauthKey() {
        return oauthKey;
    }

}
