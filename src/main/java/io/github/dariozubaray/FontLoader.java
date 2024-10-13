package io.github.dariozubaray;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

public class FontLoader {

    public static final String MARU_MONICA = "x12y16pxMaruMonica";
    public static final String PURISA_BOLD = "Purisa Bold";

    public static Font loadFont(String fontName) {
        Font font = null;
        try {
            InputStream is = FontLoader.class.getResourceAsStream("/font/"+fontName+".ttf");
            font = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return font;
    }
}
