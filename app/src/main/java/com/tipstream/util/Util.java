package com.tipstream.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.tipstream.Tipstream;

public class Util {

    public static int convertDpToPixel(float dp) {
        Resources resources = Tipstream.getAppContext().getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return (int) (dp * (metrics.densityDpi / 160f));
    }

    public static class Tipcard {

        public String id;
        public String avatar;
        public String title;
        public String username;
        public String comment;
        public String name;
        public String address;
        public Boolean isFavorite;
        public Boolean isLiked;
        public Integer rating;

        public Tipcard(String id, String avatar, String title, String username, String comment,
                       String name, String address, Boolean isFavorite, Boolean isLiked,
                       Integer rating) {
            this.id = id;
            this.avatar = avatar;
            this.title = title;
            this.username = username;
            this.comment = comment;
            this.name = name;
            this.address = address;
            this.isFavorite = isFavorite;
            this.isLiked = isLiked;
            this.rating = rating;
        }

        public String getId() {
            return id;
        }

        public String getAvatar() {
            return avatar;
        }

        public String getTitle() {
            return title;
        }

        public String getUsername() {
            return username;
        }

        public String getComment() {
            return comment;
        }

        public String getName() {
            return name;
        }

        public String getAddress() {
            return address;
        }

        public Boolean isFavorite() {
            return isFavorite;
        }

        public Boolean isLiked() {
            return isLiked;
        }

        public Integer getRating() {
            return rating;
        }
    }
}
