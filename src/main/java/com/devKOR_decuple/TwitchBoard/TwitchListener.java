/*
 * TwitchBoard by DeveloperDecuple(devKOR_Decuple)
 * Copyright (c) 2020 DeveloperDecuple(in Team Decuple). All rights reserved.
 */

package com.devKOR_decuple.TwitchBoard;

import com.gikk.twirk.events.TwirkListener;
import com.gikk.twirk.types.twitchMessage.TwitchMessage;
import com.gikk.twirk.types.users.TwitchUser;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.List;
import java.util.regex.Pattern;

import static com.devKOR_decuple.TwitchBoard.Core.ConfigurationHelper.*;
import static com.devKOR_decuple.TwitchBoard.Core.EasyEqual.*;

public class TwitchListener implements TwirkListener {

    private List<TwitchUser> userList = new ArrayList<>();
    private final Integer[] keyboardVotes = new Integer[565];

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

                try {
                    Thread.sleep(getKeyReleasingTime());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

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
            System.out.println("조회 완료 : [KEY] " + String.valueOf((char) (getArrayIndexOfMaxValue(keyboardVotes))).replace("\n", "LF") + "(" + getArrayIndexOfMaxValue(keyboardVotes) + "), [VOTES] " + getMaxValue(keyboardVotes));
        }

        return getArrayIndexOfMaxValue(keyboardVotes);

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

        /*
         * StreamerKey| Array Index          | Type    | ChatKey                  | Checked
         * ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ [ Basic ]
         * A ~ Z      : Array Index 0 ~ 25   : Type 0  : A ~ Z                    : [x]
         * 0 ~ 9      : Array Index 26 ~ 35  : Type 1  : 0 ~ 9                    : [x]
         * Ins ~ PgDn : Array Index 49 ~ 55  : Type 3  : Ins ~ PgDn               : [x]
         * ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ [ Utility ]
         * Escape     : Array Index 56       : Type 4  : Esc                      : [x]
         * Tab        : Array Index 57       : Type 5  : Tab                      : [x]
         * LShift     : Array Index 58       : Type 6  : SH, Shift                : [x]
         * LCtrl      : Array Index 59       : Type 7  : CT, Ctrl                 : [x]
         * LAlt       : Array Index 60       : Type 8  : AL, Alt                  : [x]
         * Space bar  : Array Index 61       : Type 9  : SP, SpaceBar             : [x]
         * BackSpace  : Array Index 62       : Type 10 : BS, BackSpace            : [x]
         * Return     : Array Index 63       : Type 11 : Return, Enter, RT, EN    : [x]
         * Arrow Key  : Array Index 64 ~ 68  : Type 15 : <, +, _, >               : [x]
         * Mouse      : Array Index 69 ~ 73  : Type 16 : <<, ++, __, >>           : [x]
         */

        // 넘락 해제해야 돼네 아 ㅡㅡ
        // (It works when disabled 'num lock'.)

        String msg = message.getContent();

        if (msg.length() == 1) {

            if (Pattern.matches("[a-zA-Z]", msg)) {

                if (userExistsInList(sender.getUserID())) return;

                int voteKey = msg.toUpperCase().charAt(0);
                keyboardVotes[voteKey]++;
                userList.add(sender);

                if (verboseAll())
                    System.out.println("키 받아옴 : [KEY] " + msg.toUpperCase() + "(" + voteKey + "), [NOW VOTES] " + keyboardVotes[voteKey]);

            }

            if (Pattern.matches("[0-9]", msg)) {

                if (userExistsInList(sender.getUserID())) return;

                int voteKey = msg.charAt(0);
                keyboardVotes[voteKey]++;
                userList.add(sender);

                if (verboseAll())
                    System.out.println("키 받아옴 : [KEY] " + msg.toUpperCase() + "(" + voteKey + "), [NOW VOTES] " + keyboardVotes[voteKey]);

            }

        }

        boolean isCorrectKey = false;
        int voteKey = 3;

        if (eq(msg, "Ins", "Insert")) {
            voteKey = KeyEvent.VK_INSERT;
            isCorrectKey = true;
        }
        if (eq(msg, "Home", "Hm")) {
            voteKey = KeyEvent.VK_HOME;
            isCorrectKey = true;
        }
        if (eq(msg, "PgUp", "PageUp", "Page Up")) {
            voteKey = KeyEvent.VK_PAGE_UP;
            isCorrectKey = true;
        }
        if (eq(msg, "Del", "Delete")) {
            voteKey = KeyEvent.VK_DELETE;
            isCorrectKey = true;
        }
        if (eq(msg, "End")) {
            voteKey = KeyEvent.VK_END;
            isCorrectKey = true;
        }
        if (eq(msg, "PgDn")) {
            voteKey = KeyEvent.VK_PAGE_DOWN;
            isCorrectKey = true;
        }
        if (eq(msg, "Esc", "Escape")) {
            voteKey = KeyEvent.VK_ESCAPE;
            isCorrectKey = true;
        }
        if (eq(msg, "Tab")) {
            voteKey = KeyEvent.VK_TAB;
            isCorrectKey = true;
        }
        if (eq(msg, "SH", "Shift")) {
            voteKey = KeyEvent.VK_SHIFT;
            isCorrectKey = true;
        }
        if (eq(msg, "CT", "Ctrl", "Control")) {
            voteKey = KeyEvent.VK_CONTROL;
            isCorrectKey = true;
        }
        if (eq(msg, "AL", "Alt", "Alternative")) {
            voteKey = KeyEvent.VK_ALT;
            isCorrectKey = true;
        }
        if (eq(msg, "SP", "Space", "Spacebar", "Space bar")) {
            voteKey = KeyEvent.VK_SPACE;
            isCorrectKey = true;
        }
        if (eq(msg, "BS", "BA", "BackSpace", "Back Space", "Erase")) {
            voteKey = KeyEvent.VK_BACK_SPACE;
            isCorrectKey = true;
        }
        if (eq(msg, "RT", "RTN", "EN", "ENT", "Enter", "Return")) {
            voteKey = KeyEvent.VK_ENTER;
            isCorrectKey = true;
        }
        if (eq(msg, "+")) {
            voteKey = KeyEvent.VK_UP;
            isCorrectKey = true;
        }
        if (eq(msg, "-")) {
            voteKey = KeyEvent.VK_DOWN;
            isCorrectKey = true;
        }
        if (eq(msg, "<")) {
            voteKey = KeyEvent.VK_LEFT;
            isCorrectKey = true;
        }
        if (eq(msg, ">")) {
            voteKey = KeyEvent.VK_RIGHT;
            isCorrectKey = true;
        }

        if (!isCorrectKey) return;
        if (userExistsInList(sender.getUserID())) return;

        keyboardVotes[voteKey]++;
        userList.add(sender);

        if (verboseAll())
            System.out.println("키 받아옴 : [KEY] " + msg.toUpperCase() + "(" + voteKey + "), [NOW VOTES] " + keyboardVotes[voteKey]);


    }

}
