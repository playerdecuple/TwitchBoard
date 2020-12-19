/*
 * TwitchBoard by DeveloperDecuple(devKOR_Decuple)
 * Copyright (c) 2020 DeveloperDecuple(in Team Decuple). All rights reserved.
 */

package com.devKOR_decuple.TwitchBoard;

import com.gikk.twirk.events.TwirkListener;
import com.gikk.twirk.types.twitchMessage.TwitchMessage;
import com.gikk.twirk.types.users.TwitchUser;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.regex.Pattern;

import static com.devKOR_decuple.TwitchBoard.Core.ConfigurationHelper.*;

public class TwitchListener implements TwirkListener {

    private List<TwitchUser> userList = new ArrayList<>();
    private final Integer[] keyboardVotes = new Integer[26];

    public TwitchListener() throws AWTException {

        Arrays.fill(keyboardVotes, 0);
        final Robot robot = new Robot();

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (!existsVotes()) return;
                int key = getKeyboardVoteResult();

                robot.keyPress(key);
                robot.keyRelease(key);

                userList = new ArrayList<>();
                Arrays.fill(keyboardVotes, 0);
            }
        }, 1, getTimerPeriod() * 1000);

    }

    private boolean userExistsInList(long userId) {

        if (userList.size() == 0) return false;

        for (TwitchUser user : userList) {

            if (user.getUserID() == userId) return true;

        }

        return false;

    }

    private int getKeyboardVoteResult() {

        if (verbose()) {
            System.out.println("조회 완료 : [KEY] " + (char) (getArrayIndexOfMaxValue(keyboardVotes) + 65) + "(" + getArrayIndexOfMaxValue(keyboardVotes) + "), [VOTES] " + getMaxValue(keyboardVotes));
        }

        return getArrayIndexOfMaxValue(keyboardVotes) + 65;

    }

    private int getMaxValue(Integer[] integerArray) {
        int max = 0;

        for (int integer : integerArray) {
            if (integer > max) max = integer;
        }

        return max;
    }

    private int getArrayIndexOfMaxValue(Integer[] integerArray) {

        int maxValue = getMaxValue(integerArray);

        for (int i = 0; i < integerArray.length; i++) {
            if (integerArray[i] == maxValue) {
                return i;
            }
        }

        return -1;

    }

    private boolean existsVotes() {

        for (int votes : keyboardVotes) {
            if (votes != 0) return true;
        }

        return false;

    }

    @Override
    public void onPrivMsg(TwitchUser sender, TwitchMessage message) {

        if (message.getContent().length() == 1) {

            if (Pattern.matches("[a-zA-Z]", message.getContent())) {

                if (userExistsInList(sender.getUserID())) return;

                int voteKey = message.getContent().toUpperCase().charAt(0) - 65;
                keyboardVotes[voteKey]++;
                userList.add(sender);

                if (verboseAll()) System.out.println("키 받아옴 : [KEY] " + message.getContent().toUpperCase() + "(" + voteKey + "), [NOW VOTES] " + keyboardVotes[voteKey]);

            }

        }

    }

}
