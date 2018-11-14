package com.mattniehoff.backlog.utils;

import com.mattniehoff.backlog.model.igdb.IgdbImageSize;

public class IgdbImageUtils {
    public static String generateImageUrl(String hash, IgdbImageSize size){
        if (hash == null || hash.isEmpty()){
            return "";
        }

        return "http://images.igdb.com/igdb/image/upload/t_"
                + size.toString()
                +"/"
                + hash
                + ".jpg";
    }
}

