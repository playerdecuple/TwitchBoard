package com.devKOR_decuple.TwitchBoard.Core;

import com.devKOR_decuple.TwitchBoard.TwitchBoardMain;

public class ConfigurationHelper {

    public static Streamer loadStreamer() {

        String config = TwitchBoardMain.r.readString(TwitchBoardMain.file);
        String streamerInfo = config.split("\n")[0];
        String[] streamer = streamerInfo.split(",");

        String streamerId = streamer[1];
        String oauthKey = streamer[2].replace("\n","");

        return new Streamer(streamerId, oauthKey);

    }

    public static int getTimerPeriod() {

        String config = TwitchBoardMain.r.readString(TwitchBoardMain.file);
        String timerInfo = config.split("\n")[1];
        String[] timer = timerInfo.split(",");

        return Integer.parseInt(timer[1]);

    }
    
    public static int getKeyReleasingTime() {

        String config = TwitchBoardMain.r.readString(TwitchBoardMain.file);
        String timerInfo = config.split("\n")[4];
        String[] timer = timerInfo.split(",");

        return Integer.parseInt(timer[1]);

    }

    public static boolean verbose() {

        String config = TwitchBoardMain.r.readString(TwitchBoardMain.file);
        String verboseInfo = config.split("\n")[2];
        String[] verbose = verboseInfo.split(",");

        return verbose[1].replace("\n","").equalsIgnoreCase("true");

    }

    public static boolean verboseAll() {

        String config = TwitchBoardMain.r.readString(TwitchBoardMain.file);
        String verboseInfo = config.split("\n")[3];
        String[] verbose = verboseInfo.split(",");

        return verbose[1].replace("\n","").equalsIgnoreCase("true");

    }

}
