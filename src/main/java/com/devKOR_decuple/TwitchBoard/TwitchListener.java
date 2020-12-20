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

    private int getKeyType(int indexNum) {

        /*
         * StreamerKey| Array Index          | Type    | ChatKey
         * ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ [ Basic ]
         * A ~ Z      : Array Index 0 ~ 25   : Type 0  : A ~ Z
         * 0 ~ 9      : Array Index 26 ~ 35  : Type 1  : 0 ~ 9
         * F1 ~ F12   : Array Index 36 ~ 48  : Type 2  : F1 ~ F12
         * Ins ~ PgDn : Array Index 49 ~ 55  : Type 3  : Ins ~ PgDn
         * ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ [ Utility ]
         * Escape     : Array Index 56       : Type 4  : Esc
         * Tab        : Array Index 57       : Type 5  : Tab
         * LShift     : Array Index 58       : Type 6  : LS, LShift, Shift
         * LCtrl      : Array Index 59       : Type 7  : LC, LCtrl
         * LAlt       : Array Index 60       : Type 8  : LA, LAlt
         * Space bar  : Array Index 61       : Type 9  : SP, SpaceBar
         * BackSpace  : Array Index 62       : Type 10 : BS, BackSpace
         * Return     : Array Index 63       : Type 11 : Return, Enter, RT, EN
         * RShift     : Array Index 64       : Type 12 : RS, RShift
         * RCtrl      : Array Index 65       : Type 13 : RC, RCtrl
         * RAlt       : Array Index 66       : Type 14 : RA, RAlt
         * Arrow Key  : Array Index 67 ~ 71  : Type 15 : <, +, _, >
         * Mouse      : Array Index 72 ~ 76  : Type 16 : <<, ++, __, >>
         */

        if (indexNum >= 0 && indexNum <= 25) return 0; // Alphabet
        if (indexNum >= 26 && indexNum <= 35) return 1; // Number
        if (indexNum >= 36 && indexNum <= 48) return 2; // Function Key
        if (indexNum >= 49 && indexNum <= 55) return 3; // Navigation Key(Except Arrow Key)
        if (indexNum == 56) return 4; // Escape key
        if (indexNum == 57) return 5; // Tab Key
        if (indexNum == 58) return 6; // LS
        if (indexNum == 59) return 7; // LC
        if (indexNum == 60) return 8; // LA
        if (indexNum == 61) return 9; // SP
        if (indexNum == 62) return 10; // BS
        if (indexNum == 63) return 11; // RT
        if (indexNum == 64) return 12; // RS
        if (indexNum == 65) return 13; // RC
        if (indexNum == 66) return 14; // RA
        if (indexNum >= 67 && indexNum <= 71) return 15; // Arrow Keys
        if (indexNum >= 72 && indexNum <= 76) return 16; // Mouse

        return -1;

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
