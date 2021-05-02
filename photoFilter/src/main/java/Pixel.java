public class Pixel {
    private int alpha;
    private int red;
    private int green;
    private int blue;

    public Pixel(int RGB) {
        alpha = (RGB>>24) & 0xff;
        red = (RGB>>16) & 0xff;
        green = (RGB>>8) & 0xff;
        blue = RGB & 0xff;
    }

    public void setGrey() {
        int avg = (red + green + blue) / 3;
        red = avg;
        green = avg;
        blue = avg;
    }

    public void setSepia() {
        int tr = (int)(0.393*red + 0.769*green + 0.189*blue);
        int tg = (int)(0.349*red + 0.686*green + 0.168*blue);
        int tb = (int)(0.272*red + 0.534*green + 0.131*blue);
        red = Math.min(tr, 255);
        green = Math.min(tg, 255);
        blue = Math.min(tb, 255);
    }

    public void setInvert() {
        red = 255 - red;
        green = 255 - green;
        blue = 255 - blue;
    }

    public int getRGB() {
        int p = (alpha<<24) | (red<<16) | (green<<8) | blue;
        return p;
    }
}
