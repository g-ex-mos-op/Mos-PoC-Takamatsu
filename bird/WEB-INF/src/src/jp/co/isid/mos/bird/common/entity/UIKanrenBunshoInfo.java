package jp.co.isid.mos.bird.common.entity;

import java.sql.Timestamp;

import jp.co.isid.mos.bird.common.code.InfoShu;

/**
 * 
 * ì¬“ú:
 * @author 
 *
 * XV“úF2011/04/15 ASAPC u‚¨’m‚ç‚¹v‚©‚çuƒCƒ“ƒtƒHƒ[ƒVƒ‡ƒ“v‚Ö‰æ–Ê–¼Ì•ÏX‘Î‰
 */
public class UIKanrenBunshoInfo {
    
    public static final String TABLE = "BT11RLFL";
    
    public static final String infoShu_COLUMN = "INFO_SHU";
    
    public static final String regDate_COLUMN = "REG_DATE";
    
    public static final String seq_COLUMN = "SEQ";
    
    public static final String relInfoShu_COLUMN = "REL_INFO_SHU";
    
    public static final String relRegDate_COLUMN = "REL_REG_DATE";
    
    public static final String relSeq_COLUMN = "REL_SEQ";
    
    public static final String firstUser_COLUMN = "FIRST_USER";
    
    public static final String firstPgm_COLUMN = "FIRST_PGM";
    
    public static final String firstTmsp_COLUMN = "FIRST_TMSP";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";
    
    public static final String lastTmsp_COLUMN = "LAST_TMSP";
    
    public static final String bt02RegDate_COLUMN = "BT02REG_DATE";
    
    public static final String bt02Seq_COLUMN = "BT02SEQ";
    
    public static final String bt02Title_COLUMN = "BT02TITLE";
    
    public static final String bt02FileName_COLUMN = "BT02FILE_NAME";
    
    public static final String bt03RegDate_COLUMN = "BT03REG_DATE";
    
    public static final String bt03Seq_COLUMN = "BT03SEQ";
    
    public static final String bt03Title_COLUMN = "BT03TITLE";
    
    public static final String bt03FileName_COLUMN = "BT03FILE_NAME";
    
    public static final String bt04RegDate_COLUMN = "BT04REG_DATE";
    
    public static final String bt04Seq_COLUMN = "BT04SEQ";
    
    public static final String bt04Title_COLUMN = "BT04TITLE";
    
    public static final String bt04FielName_COLUMN = "BT04FILE_NAME";

    public static final String bt01InfoTitle_COLUMN = "BT01_INFO_TITLE";
    
    public static final String bt02InfoTitle_COLUMN = "BT02_INFO_TITLE";
    
    public static final String bt03InfoTitle_COLUMN = "BT03_INFO_TITLE";
    
    public static final String bt04InfoTitle_COLUMN = "BT04_INFO_TITLE";
    
    public static final String bt05InfoTitle_COLUMN = "BT05_INFO_TITLE";
    
    /**
     * î•ñí•Ê
     */
    private String infoShu;
    
    /**
     * î•ñ“o˜^“ú
     */
    private String regDate;
    
    /**
     * î•ñƒV[ƒPƒ“ƒX”Ô†
     */
    private String seq;
    
    /**
     * ŠÖ˜Aæî•ñí•Ê
     */
    private String relInfoShu;
    
    /**
     * ŠÖ˜Aæ“o˜^“ú
     */
    private String relRegDate;
    
    /**
     * ŠÖ˜AæƒV[ƒPƒ“ƒX”Ô†
     */
    private String relSeq;
    
    /**
     * “o˜^ƒ†[ƒU[
     */
    private String firstUser;
    
    /**
     * “o˜^ƒvƒƒOƒ‰ƒ€
     */
    private String firstPgm;
    
    /**
     * “o˜^À²Ñ½ÀİÌß
     */
    private Timestamp firstTmsp;
    
    /**
     * C³ƒ†[ƒU[
     */
    private String lastUser;
    
    /**
     * C³ƒvƒƒOƒ‰ƒ€
     */
    private String lastPgm;
    
    /**
     * C³À²Ñ½ÀİÌß
     */
    private Timestamp lastTmsp;
    
    /**
     * ‚a‚s‚O‚Q‚Ì“o˜^“ú
     */
    private String bt02RegDate;
    
    /**
     * ‚a‚s‚O‚Q‚ÌƒV[ƒPƒ“ƒX”Ô†
     */
    private String bt02Seq;
    
    /**
     * ‚a‚s‚O‚Q‚Ìƒ^ƒCƒgƒ‹
     */
    private String bt02Title;
    
    /**
     * ‚a‚s‚O‚Q‚Ìƒtƒ@ƒCƒ‹–¼
     */
    private String bt02FileName;
    
    /**
     * ‚a‚s‚O‚R‚Ì“o˜^“ú
     */
    private String bt03RegDate;
    
    /**
     * ‚a‚s‚O‚R‚ÌƒV[ƒPƒ“ƒX”Ô†
     */
    private String bt03Seq;
    
    /**
     * ‚a‚s‚O‚R‚Ìƒ^ƒCƒgƒ‹
     */
    private String bt03Title;
    
    /**
     * ‚a‚s‚O‚R‚Ìƒtƒ@ƒCƒ‹–¼
     */
    private String bt03FileName;
    
    /**
     * ‚a‚s‚O‚S‚Ì“o˜^“ú
     */
    private String bt04RegDate;
    
    /**
     * ‚a‚s‚O‚S‚ÌƒV[ƒPƒ“ƒX”Ô†
     */
    private String bt04Seq;
    
    /**
     * ‚a‚s‚O‚S‚Ìƒ^ƒCƒgƒ‹
     */
    private String bt04Title;
    
    /**
     * ‚a‚s‚O‚S‚Ìƒtƒ@ƒCƒ‹–¼
     */
    private String bt04FileName;
    
    /**
     * î•ñƒ^ƒCƒgƒ‹i‚a‚s‚O‚P‚Ìê‡j
     */
    private String bt01InfoTitle;
    
    /**
     * î•ñƒ^ƒCƒgƒ‹i‚a‚s‚O‚Q‚Ìê‡j
     */
    private String bt02InfoTitle;
    
    /**
     * î•ñƒ^ƒCƒgƒ‹i‚a‚s‚O‚R‚Ìê‡j
     */
    private String bt03InfoTitle;
    
    /**
     * î•ñƒ^ƒCƒgƒ‹i‚a‚s‚O‚S‚Ìê‡j
     */
    private String bt04InfoTitle;
    
    /**
     * î•ñƒ^ƒCƒgƒ‹i‚a‚s‚O‚T‚Ìê‡j
     */
    private String bt05InfoTitle;
    
    /**
     * î•ñí•Ê‚ğæ“¾‚µ‚Ü‚·B
     * @return î•ñí•Ê
     */
    public String getInfoShu() {
        return infoShu;
    }
    /**
     * î•ñí•Ê‚ğİ’è‚µ‚Ü‚·B
     * @param infoShu î•ñí•Ê
     */
    public void setInfoShu(String infoShu) {
        this.infoShu = infoShu;
    }
    
    /**
     * î•ñ“o˜^“ú‚ğæ“¾‚µ‚Ü‚·B
     * @return î•ñ“o˜^“ú
     */
    public String getRegDate() {
        return regDate;
    }
    /**
     * î•ñ“o˜^“ú‚ğİ’è‚µ‚Ü‚·B
     * @param regDate î•ñ“o˜^“ú
     */
    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }
    
    /**
     * î•ñƒV[ƒPƒ“ƒX”Ô†‚ğæ“¾‚µ‚Ü‚·B
     * @return î•ñƒV[ƒPƒ“ƒX”Ô†
     */
    public String getSeq() {
        return seq;
    }
    /**
     * î•ñƒV[ƒPƒ“ƒX”Ô†‚ğİ’è‚µ‚Ü‚·B
     * @param seq î•ñƒV[ƒPƒ“ƒX”Ô†
     */
    public void setSeq(String seq) {
        this.seq = seq;
    }
    
    /**
     * ŠÖ˜Aæî•ñí•Ê‚ğæ“¾‚µ‚Ü‚·B
     * @return ŠÖ˜Aæî•ñí•Ê
     */
    public String getRelInfoShu() {
        return relInfoShu;
    }
    /**
     * ŠÖ˜Aæî•ñí•Ê‚ğİ’è‚µ‚Ü‚·B
     * @param relInfoShu ŠÖ˜Aæî•ñí•Ê
     */
    public void setRelInfoShu(String relInfoShu) {
        this.relInfoShu = relInfoShu;
    }
    
    /**
     * ŠÖ˜Aæ“o˜^“ú‚ğæ“¾‚µ‚Ü‚·B
     * @return ŠÖ˜Aæ“o˜^“ú
     */
    public String getRelRegDate() {
        return relRegDate;
    }
    /**
     * ŠÖ˜Aæ“o˜^“ú‚ğİ’è‚µ‚Ü‚·B
     * @param relRegDate ŠÖ˜Aæ“o˜^“ú
     */
    public void setRelRegDate(String relRegDate) {
        this.relRegDate = relRegDate;
    }
    
    /**
     * ŠÖ˜AæƒV[ƒPƒ“ƒX”Ô†‚ğæ“¾‚µ‚Ü‚·B
     * @return ŠÖ˜AæƒV[ƒPƒ“ƒX”Ô†
     */
    public String getRelSeq() {
        return relSeq;
    }
    /**
     * ŠÖ˜AæƒV[ƒPƒ“ƒX”Ô†‚ğİ’è‚µ‚Ü‚·B
     * @param relSeq ŠÖ˜AæƒV[ƒPƒ“ƒX”Ô†
     */
    public void setRelSeq(String relSeq) {
        this.relSeq = relSeq;
    }
    
    /**
     * “o˜^ƒ†[ƒU[‚ğæ“¾‚µ‚Ü‚·B
     * @return “o˜^ƒ†[ƒU[
     */
    public String getFirstUser() {
        return firstUser;
    }
    /**
     * “o˜^ƒ†[ƒU[‚ğİ’è‚µ‚Ü‚·B
     * @param firstUser “o˜^ƒ†[ƒU[
     */
    public void setFirstUser(String firstUser) {
        this.firstUser = firstUser;
    }
    
    /**
     * “o˜^ƒvƒƒOƒ‰ƒ€‚ğæ“¾‚µ‚Ü‚·B
     * @return “o˜^ƒvƒƒOƒ‰ƒ€
     */
    public String getFirstPgm() {
        return firstPgm;
    }
    /**
     * “o˜^ƒvƒƒOƒ‰ƒ€‚ğİ’è‚µ‚Ü‚·B
     * @param firstPgm “o˜^ƒvƒƒOƒ‰ƒ€
     */
    public void setFirstPgm(String firstPgm) {
        this.firstPgm = firstPgm;
    }
    
    /**
     * “o˜^À²Ñ½ÀİÌß‚ğæ“¾‚µ‚Ü‚·B
     * @return “o˜^À²Ñ½ÀİÌß
     */
    public Timestamp getFirstTmsp() {
        return firstTmsp;
    }
    /**
     * “o˜^À²Ñ½ÀİÌß‚ğİ’è‚µ‚Ü‚·B
     * @param firstTmsp “o˜^À²Ñ½ÀİÌß
     */
    public void setFirstTmsp(Timestamp firstTmsp) {
        this.firstTmsp = firstTmsp;
    }
    
    /**
     * C³ƒ†[ƒU[‚ğæ“¾‚µ‚Ü‚·B
     * @return C³ƒ†[ƒU[
     */
    public String getLastUser() {
        return lastUser;
    }
    /**
     * C³ƒ†[ƒU[‚ğİ’è‚µ‚Ü‚·B
     * @param lastUser C³ƒ†[ƒU[
     */
    public void setLastUser(String lastUser) {
        this.lastUser = lastUser;
    }
    
    /**
     * C³ƒvƒƒOƒ‰ƒ€‚ğæ“¾‚µ‚Ü‚·B
     * @return C³ƒvƒƒOƒ‰ƒ€
     */
    public String getLastPgm() {
        return lastPgm;
    }
    /**
     * C³ƒvƒƒOƒ‰ƒ€‚ğİ’è‚µ‚Ü‚·B
     * @param lastPgm C³ƒvƒƒOƒ‰ƒ€
     */
    public void setLastPgm(String lastPgm) {
        this.lastPgm = lastPgm;
    }
    
    /**
     * C³À²Ñ½ÀİÌß‚ğæ“¾‚µ‚Ü‚·B
     * @return C³À²Ñ½ÀİÌß
     */
    public Timestamp getLastTmsp() {
        return lastTmsp;
    }
    /**
     * C³À²Ñ½ÀİÌß‚ğİ’è‚µ‚Ü‚·B
     * @param lastTmsp C³À²Ñ½ÀİÌß
     */
    public void setLastTmsp(Timestamp lastTmsp) {
        this.lastTmsp = lastTmsp;
    }
    
    /**
     * ‚a‚s‚O‚Q‚Ì“o˜^“ú‚ğæ“¾‚µ‚Ü‚·B
     * @return ‚a‚s‚O‚Q‚Ì“o˜^“ú
     */
    public String getBt02RegDate() {
        return bt02RegDate;
    }
    /**
     * ‚a‚s‚O‚Q‚Ì“o˜^“ú‚ğİ’è‚µ‚Ü‚·B
     * @param bt02RegDate ‚a‚s‚O‚Q‚Ì“o˜^“ú
     */
    public void setBt02RegDate(String bt02RegDate) {
        this.bt02RegDate = bt02RegDate;
    }
    
    /**
     * ‚a‚s‚O‚Q‚ÌƒV[ƒPƒ“ƒX”Ô†‚ğæ“¾‚µ‚Ü‚·B
     * @return ‚a‚s‚O‚Q‚ÌƒV[ƒPƒ“ƒX”Ô†
     */
    public String getBt02Seq() {
        return bt02Seq;
    }
    /**
     * ‚a‚s‚O‚Q‚ÌƒV[ƒPƒ“ƒX”Ô†‚ğİ’è‚µ‚Ü‚·B
     * @param bt02Seq ‚a‚s‚O‚Q‚ÌƒV[ƒPƒ“ƒX”Ô†
     */
    public void setBt02Seq(String bt02Seq) {
        this.bt02Seq = bt02Seq;
    }
    
    /**
     * ‚a‚s‚O‚Q‚Ìƒ^ƒCƒgƒ‹‚ğæ“¾‚µ‚Ü‚·B
     * @return ‚a‚s‚O‚Q‚Ìƒ^ƒCƒgƒ‹
     */
    public String getBt02Title() {
        return bt02Title;
    }
    /**
     * ‚a‚s‚O‚Q‚Ìƒ^ƒCƒgƒ‹‚ğİ’è‚µ‚Ü‚·B
     * @param bt02Title ‚a‚s‚O‚Q‚Ìƒ^ƒCƒgƒ‹
     */
    public void setBt02Title(String bt02Title) {
        this.bt02Title = bt02Title;
    }
    
    /**
     * ‚a‚s‚O‚Q‚Ìƒtƒ@ƒCƒ‹–¼‚ğæ“¾‚µ‚Ü‚·B
     * @return ‚a‚s‚O‚Q‚Ìƒtƒ@ƒCƒ‹–¼
     */
    public String getBt02FileName() {
        return bt02FileName;
    }
    /**
     * ‚a‚s‚O‚Q‚Ìƒtƒ@ƒCƒ‹–¼‚ğİ’è‚µ‚Ü‚·B
     * @param bt02FileName ‚a‚s‚O‚Q‚Ìƒtƒ@ƒCƒ‹–¼
     */
    public void setBt02FileName(String bt02FileName) {
        this.bt02FileName = bt02FileName;
    }
    
    /**
     * ‚a‚s‚O‚R‚Ì“o˜^“ú‚ğæ“¾‚µ‚Ü‚·B
     * @return ‚a‚s‚O‚R‚Ì“o˜^“ú
     */
    public String getBt03RegDate() {
        return bt03RegDate;
    }
    /**
     * ‚a‚s‚O‚R‚Ì“o˜^“ú‚ğİ’è‚µ‚Ü‚·B
     * @param bt03RegDate ‚a‚s‚O‚R‚Ì“o˜^“ú
     */
    public void setBt03RegDate(String bt03RegDate) {
        this.bt03RegDate = bt03RegDate;
    }
    
    /**
     * ‚a‚s‚O‚R‚ÌƒV[ƒPƒ“ƒX”Ô†‚ğæ“¾‚µ‚Ü‚·B
     * @return ‚a‚s‚O‚R‚ÌƒV[ƒPƒ“ƒX”Ô†
     */
    public String getBt03Seq() {
        return bt03Seq;
    }
    /**
     * ‚a‚s‚O‚R‚ÌƒV[ƒPƒ“ƒX”Ô†‚ğİ’è‚µ‚Ü‚·B
     * @param bt03Seq ‚a‚s‚O‚R‚ÌƒV[ƒPƒ“ƒX”Ô†
     */
    public void setBt03Seq(String bt03Seq) {
        this.bt03Seq = bt03Seq;
    }
    
    /**
     * ‚a‚s‚O‚R‚Ìƒ^ƒCƒgƒ‹‚ğæ“¾‚µ‚Ü‚·B
     * @return ‚a‚s‚O‚R‚Ìƒ^ƒCƒgƒ‹
     */
    public String getBt03Title() {
        return bt03Title;
    }
    /**
     * ‚a‚s‚O‚R‚Ìƒ^ƒCƒgƒ‹‚ğİ’è‚µ‚Ü‚·B
     * @param bt03Title ‚a‚s‚O‚R‚Ìƒ^ƒCƒgƒ‹
     */
    public void setBt03Title(String bt03Title) {
        this.bt03Title = bt03Title;
    }
    
    /**
     * ‚a‚s‚O‚R‚Ìƒtƒ@ƒCƒ‹–¼‚ğæ“¾‚µ‚Ü‚·B
     * @return ‚a‚s‚O‚R‚Ìƒtƒ@ƒCƒ‹–¼
     */
    public String getBt03FileName() {
        return bt03FileName;
    }
    /**
     * ‚a‚s‚O‚R‚Ìƒtƒ@ƒCƒ‹–¼‚ğİ’è‚µ‚Ü‚·B
     * @param bt03FileName ‚a‚s‚O‚R‚Ìƒtƒ@ƒCƒ‹–¼
     */
    public void setBt03FileName(String bt03FileName) {
        this.bt03FileName = bt03FileName;
    }
    
    /**
     * ‚a‚s‚O‚S‚Ì“o˜^“ú‚ğæ“¾‚µ‚Ü‚·B
     * @return ‚a‚s‚O‚S‚Ì“o˜^“ú
     */
    public String getBt04RegDate() {
        return bt04RegDate;
    }
    /**
     * ‚a‚s‚O‚S‚Ì“o˜^“ú‚ğİ’è‚µ‚Ü‚·B
     * @param bt04RegDate ‚a‚s‚O‚S‚Ì“o˜^“ú
     */
    public void setBt04RegDate(String bt04RegDate) {
        this.bt04RegDate = bt04RegDate;
    }
    
    /**
     * ‚a‚s‚O‚S‚ÌƒV[ƒPƒ“ƒX”Ô†‚ğæ“¾‚µ‚Ü‚·B
     * @return ‚a‚s‚O‚S‚ÌƒV[ƒPƒ“ƒX”Ô†
     */
    public String getBt04Seq() {
        return bt04Seq;
    }
    /**
     * ‚a‚s‚O‚S‚ÌƒV[ƒPƒ“ƒX”Ô†‚ğİ’è‚µ‚Ü‚·B
     * @param bt04Seq ‚a‚s‚O‚S‚ÌƒV[ƒPƒ“ƒX”Ô†
     */
    public void setBt04Seq(String bt04Seq) {
        this.bt04Seq = bt04Seq;
    }
    
    /**
     * ‚a‚s‚O‚S‚Ìƒ^ƒCƒgƒ‹‚ğæ“¾‚µ‚Ü‚·B
     * @return ‚a‚s‚O‚S‚Ìƒ^ƒCƒgƒ‹
     */
    public String getBt04Title() {
        return bt04Title;
    }
    /**
     * ‚a‚s‚O‚S‚Ìƒ^ƒCƒgƒ‹‚ğİ’è‚µ‚Ü‚·B
     * @param bt04Title ‚a‚s‚O‚S‚Ìƒ^ƒCƒgƒ‹
     */
    public void setBt04Title(String bt04Title) {
        this.bt04Title = bt04Title;
    }
    
    /**
     * ‚a‚s‚O‚S‚Ìƒtƒ@ƒCƒ‹–¼‚ğæ“¾‚µ‚Ü‚·B
     * @return ‚a‚s‚O‚S‚Ìƒtƒ@ƒCƒ‹–¼
     */
    public String getBt04FileName() {
        return bt04FileName;
    }
    /**
     * ‚a‚s‚O‚S‚Ìƒtƒ@ƒCƒ‹–¼‚ğİ’è‚µ‚Ü‚·B
     * @param bt04FielName ‚a‚s‚O‚S‚Ìƒtƒ@ƒCƒ‹–¼
     */
    public void setBt04FileName(String bt04FileName) {
        this.bt04FileName = bt04FileName;
    }
    /**
     * î•ñƒ^ƒCƒgƒ‹i‚a‚s‚O‚P‚Ìê‡j‚ğæ“¾‚µ‚Ü‚·B
     * @return î•ñƒ^ƒCƒgƒ‹i‚a‚s‚O‚P‚Ìê‡j
     */
    public String getBt01InfoTitle() {
        return bt01InfoTitle;
    }
    /**
     * î•ñƒ^ƒCƒgƒ‹i‚a‚s‚O‚P‚Ìê‡j‚ğİ’è‚µ‚Ü‚·B
     * @param bt01InfoTitle î•ñƒ^ƒCƒgƒ‹i‚a‚s‚O‚P‚Ìê‡j
     */
    public void setBt01InfoTitle(String bt01InfoTitle) {
        this.bt01InfoTitle = bt01InfoTitle;
    }
    
    /**
     * î•ñƒ^ƒCƒgƒ‹i‚a‚s‚O‚Q‚Ìê‡j‚ğæ“¾‚µ‚Ü‚·B
     * @return î•ñƒ^ƒCƒgƒ‹i‚a‚s‚O‚Q‚Ìê‡j
     */
    public String getBt02InfoTitle() {
        return bt02InfoTitle;
    }
    /**
     * î•ñƒ^ƒCƒgƒ‹i‚a‚s‚O‚Q‚Ìê‡j‚ğİ’è‚µ‚Ü‚·B
     * @param bt02InfoTitle î•ñƒ^ƒCƒgƒ‹i‚a‚s‚O‚Q‚Ìê‡j
     */
    public void setBt02InfoTitle(String bt02InfoTitle) {
        this.bt02InfoTitle = bt02InfoTitle;
    }
    
    /**
     * î•ñƒ^ƒCƒgƒ‹i‚a‚s‚O‚R‚Ìê‡j‚ğæ“¾‚µ‚Ü‚·B
     * @return î•ñƒ^ƒCƒgƒ‹i‚a‚s‚O‚R‚Ìê‡j
     */
    public String getBt03InfoTitle() {
        return bt03InfoTitle;
    }
    /**
     * î•ñƒ^ƒCƒgƒ‹i‚a‚s‚O‚R‚Ìê‡j‚ğİ’è‚µ‚Ü‚·B
     * @param bt03InfoTitle î•ñƒ^ƒCƒgƒ‹i‚a‚s‚O‚R‚Ìê‡j
     */
    public void setBt03InfoTitle(String bt03InfoTitle) {
        this.bt03InfoTitle = bt03InfoTitle;
    }
    
    /**
     * î•ñƒ^ƒCƒgƒ‹i‚a‚s‚O‚S‚Ìê‡j‚ğæ“¾‚µ‚Ü‚·B
     * @return î•ñƒ^ƒCƒgƒ‹i‚a‚s‚O‚S‚Ìê‡j
     */
    public String getBt04InfoTitle() {
        return bt04InfoTitle;
    }
    /**
     * î•ñƒ^ƒCƒgƒ‹i‚a‚s‚O‚S‚Ìê‡j‚ğİ’è‚µ‚Ü‚·B
     * @param bt04InfoTitle î•ñƒ^ƒCƒgƒ‹i‚a‚s‚O‚S‚Ìê‡j
     */
    public void setBt04InfoTitle(String bt04InfoTitle) {
        this.bt04InfoTitle = bt04InfoTitle;
    }
    
    /**
     * î•ñƒ^ƒCƒgƒ‹i‚a‚s‚O‚T‚Ìê‡j‚ğæ“¾‚µ‚Ü‚·B
     * @return î•ñƒ^ƒCƒgƒ‹i‚a‚s‚O‚T‚Ìê‡j
     */
    public String getBt05InfoTitle() {
        return bt05InfoTitle;
    }
    /**
     * î•ñƒ^ƒCƒgƒ‹i‚a‚s‚O‚T‚Ìê‡j‚ğİ’è‚µ‚Ü‚·B
     * @param bt05InfoTitle î•ñƒ^ƒCƒgƒ‹i‚a‚s‚O‚T‚Ìê‡j
     */
    public void setBt05InfoTitle(String bt05InfoTitle) {
        this.bt05InfoTitle = bt05InfoTitle;
    }
    
    /**
     * ƒ^ƒCƒgƒ‹‚ğæ“¾‚µ‚Ü‚·B
     * @return ƒ^ƒCƒgƒ‹
     */
    public String getTitle(){
        if(InfoShu.CONTACT.equals(getInfoShu())){
            return getBt01InfoTitle();

        }else if(InfoShu.TUTAU.equals(getInfoShu())){
            return getBt02InfoTitle();
        
        }else if(InfoShu.BUNSHO.equals(getInfoShu())){
            return getBt03InfoTitle();
            
        }else if(InfoShu.FORM.equals(getInfoShu())){
            return getBt04InfoTitle();

        }else if(InfoShu.OSIRASE.equals(getInfoShu())){
            return getBt05InfoTitle();
        }
        return "";    
    }
    
    /**
     * î•ñí•Ê–¼Ì‚ğæ“¾‚µ‚Ü‚·B
     * @return
     * XV“úF2011/04/15 ASAPC u’Ê’Bv‚©‚çu‚¨’m‚ç‚¹v‚Ö‰æ–Ê–¼Ì•ÏX‘Î‰
     * XV“úF2011/04/15 ASAPC u‚¨’m‚ç‚¹v‚©‚çuƒCƒ“ƒtƒHƒ[ƒVƒ‡ƒ“v‚Ö‰æ–Ê–¼Ì•ÏX‘Î‰
     */
    public String getInfoShuName(){
        return InfoShu.getName(getInfoShu());    
    }

    
}
