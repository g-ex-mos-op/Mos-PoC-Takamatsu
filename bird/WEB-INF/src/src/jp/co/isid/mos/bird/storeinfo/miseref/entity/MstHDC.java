package jp.co.isid.mos.bird.storeinfo.miseref.entity;

public class MstHDC {
    
    public static final String TABLE = "BM27HDCR";
    
    public static final String hdcDt_COLUMN = "HDC_DT";
    
    public static final String hdcNaiyou_COLUMN = "HDC_NAIYOU";
    
    /**
     * HDC“X•ÜÜŒ
     */
    private String hdcDt;
    
    /**
     * HDC“X•ÜÜ“à—e
     */
    private String hdcNaiyou;
    
    /**
     * HDC“X•ÜÜŒ‚ğæ“¾‚µ‚Ü‚·B
     * @return HDC“X•ÜÜŒ
     */
    public String getHdcDt() {
        return hdcDt;
    }
    /**
     * HDC“X•ÜÜŒ‚ğİ’è‚µ‚Ü‚·B
     * @param hdcDt HDC“X•ÜÜŒ
     */
    public void setHdcDt(String hdcDt) {
        this.hdcDt = hdcDt;
    }
    
    /**
     * HDC“X•ÜÜ“à—e‚ğæ“¾‚µ‚Ü‚·B
     * @return HDC“X•ÜÜ“à—e
     */
    public String getHdcNaiyou() {
        return hdcNaiyou;
    }
    /**
     * HDC“X•ÜÜ“à—e‚ğİ’è‚µ‚Ü‚·B
     * @param hdcNaiyou HDC“X•ÜÜ“à—e
     */
    public void setHdcNaiyou(String hdcNaiyou) {
        this.hdcNaiyou = hdcNaiyou;
    }
    
}
