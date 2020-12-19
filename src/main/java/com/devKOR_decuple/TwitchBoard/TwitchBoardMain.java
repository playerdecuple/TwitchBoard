/*
 * TwitchBoard by DeveloperDecuple(devKOR_Decuple)
 * Copyright (c) 2020 DeveloperDecuple(in Team Decuple). All rights reserved.
 */

package com.devKOR_decuple.TwitchBoard;

import com.devKOR_decuple.TwitchBoard.Core.ReadFile;
import com.devKOR_decuple.TwitchBoard.Core.Streamer;
import com.gikk.twirk.Twirk;
import com.gikk.twirk.TwirkBuilder;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import static com.devKOR_decuple.TwitchBoard.Core.ConfigurationHelper.loadStreamer;

public class TwitchBoardMain {

    public static final ReadFile r = new ReadFile();
    public static final File file = new File(System.getProperty("user.dir") + "/.config.txt");

    public static void main(String[] args) throws AWTException, IOException, InterruptedException {

        Streamer streamer = loadStreamer();

        System.out.println("커넥트 완료 : streamer - " + streamer.getStreamer() + " / oauth - " + streamer.getOauthKey() + " /");
        Twirk twirk = new TwirkBuilder(streamer.getStreamer(), "TwitchBoard", streamer.getOauthKey()).build();
        twirk.addIrcListener(new TwitchListener());
        twirk.connect();

    }

}
