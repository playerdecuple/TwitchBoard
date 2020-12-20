package com.devKOR_decuple.TwitchBoard.Core;

public class EasyEqual {

    public EasyEqual() {

    }

    public static boolean eq(String obj1, String obj2) {
        return obj1.equalsIgnoreCase(obj2);
    }

    public static boolean eq(String ... obj) {

        for (int i = 1; i < obj.length; i++) {

            if (obj[0].equalsIgnoreCase(obj[i])) {

                return true;

            }

        }

        return false;

    }

    public static boolean eqNull(String ... obj) {

        for (String s : obj) {

            if (s == null) {

                return true;

            }

        }

        return false;

    }

    public static boolean eq(int ... obj) {

        int j = obj.length;

        for (int i = 1; i < obj.length; i++) {

            if (obj[0] == obj[i]) {

                return true;

            }

        }

        return false;

    }

    public static boolean con(String first, String ... obj) {

        for (String s : obj) {
            if (first.toLowerCase().contains(s.toLowerCase())) {
                return true;
            }
        }

        return false;

    }

}
