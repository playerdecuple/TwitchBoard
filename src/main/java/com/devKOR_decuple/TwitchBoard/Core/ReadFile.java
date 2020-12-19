package com.devKOR_decuple.TwitchBoard.Core;

import java.io.File;
import java.io.FileInputStream;

public class ReadFile {

    public ReadFile() {

    }

    public long readLong(String filePath) {
        try {
            FileInputStream fs = new FileInputStream(filePath);
            byte[] rB = new byte[fs.available()];
            while (fs.read(rB) != -1) {
            }

            return Long.parseLong(new String(rB));
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public long readLong(File file) {
        try {
            FileInputStream fs = new FileInputStream(file.getPath());
            byte[] rB = new byte[fs.available()];
            while (fs.read(rB) != -1) {
            }
            return Long.parseLong(new String(rB));
        } catch (Exception e) {
            
            e.printStackTrace();
            return 0;
        }
    }

    public int readInt(String path) {
        try {
            FileInputStream fs = new FileInputStream(path);
            byte[] rB = new byte[fs.available()];
            while (fs.read(rB) != -1) {
            }
            return Integer.parseInt(new String(rB));
        } catch (Exception e) {
            
            e.printStackTrace();
            return 0;
        }
    }

    public int readInt(File f) {
        try {
            FileInputStream fs = new FileInputStream(f.getPath());
            byte[] rB = new byte[fs.available()];
            while (fs.read(rB) != -1) {
            }

            return Integer.parseInt(new String(rB));
        } catch (Exception e) {
            
            e.printStackTrace();
            return 0;
        }
    }

    public String readString(String path) {
        try {
            FileInputStream fs = new FileInputStream(path);
            byte[] rB = new byte[fs.available()];
            while (fs.read(rB) != -1) {
            }

            return new String(rB);
        } catch (Exception e) {
            
            e.printStackTrace();
            return null;
        }
    }

    public String readString(File f) {
        try {
            FileInputStream fs = new FileInputStream(f.getPath());
            byte[] rB = new byte[fs.available()];
            while (fs.read(rB) != -1) {
            }

            return new String(rB);
        } catch (Exception e) {
            
            e.printStackTrace();
            return null;
        }
    }

}
