package jp.co.isid.mos.bird.entry.projectplanoffer.entity;

public class UIOfferNoticeInfo {
    
    public static final String TABLE = "BR51ENCI";
    
    public static final String notice1_COLUMN = "NOTICE1";
    
    public static final String note_COLUMN = "NOTE";
    
    /**
     * •¶Œ¾
     */
    private String notice1;
    
    /**
     * ˆÏ”Có•¶Œ¾
     */
    private String note;
    
    /**
     * •¶Œ¾‚ğæ“¾‚µ‚Ü‚·B
     * @return •¶Œ¾
     */
    public String getNotice1() {
        return notice1;
    }
    /**
     * •¶Œ¾‚ğİ’è‚µ‚Ü‚·B
     * @param notice •¶Œ¾
     */
    public void setNotice1(String notice1) {
        this.notice1 = notice1;
    }
    
    /**
     * ˆÏ”Có•¶Œ¾‚ğæ“¾‚µ‚Ü‚·B
     * @return ˆÏ”Có•¶Œ¾
     */
    public String getNote() {
        return note;
    }
    /**
     * ˆÏ”Có•¶Œ¾‚ğİ’è‚µ‚Ü‚·B
     * @param notice ˆÏ”Có•¶Œ¾
     */
    public void setNote(String note) {
        this.note = note;
    }
    
}
