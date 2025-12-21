/*
 * ì¬“ú: 2006/01/31
 *
 */
package jp.co.isid.mos.bird.common.dto;

import java.util.List;

import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;

/**
 * @author xyuchida
 *
 */
public class PublicTargetDto implements CsvOutputDto {

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
     * Š‘®‚É‚æ‚éi‚è‚±‚İî•ñ
     */
    private List listTrnControlShozoku;
    
    /**
     * ‰ïĞ‚É‚æ‚éi‚è‚±‚İî•ñ
     */
    private List listTrnControlCompany;
    
    /**
     * ‹Æ‘Ô‚É‚æ‚éi‚è‚±‚İî•ñ
     */
    private List listTrnControlGyotai;
    
    /**
     * ‹Æ‘ÔŒÂ•Ê‚É‚æ‚éi‚è‚±‚İî•ñ
     */
    private List listTrnControlGyotaiKobetu;
   
    /**
     * ŒÂ“X‚É‚æ‚éi‚è‚±‚İî•ñ
     */
    private List listTrnControlGyotaiTenpo;

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
     * ‰ïĞ‚É‚æ‚éi‚è‚±‚İî•ñ‚ğæ“¾‚µ‚Ü‚·B
     * @return ‰ïĞ‚É‚æ‚éi‚è‚±‚İî•ñ
     */
    public List getListTrnControlCompany() {
        return listTrnControlCompany;
    }
    /**
     * ‰ïĞ‚É‚æ‚éi‚è‚±‚İî•ñ‚ğİ’è‚µ‚Ü‚·B
     * @param listTrnControlCompany ‰ïĞ‚É‚æ‚éi‚è‚±‚İî•ñ
     */
    public void setListTrnControlCompany(List listTrnControlCompany) {
        this.listTrnControlCompany = listTrnControlCompany;
    }
    /**
     * ‹Æ‘Ô‚É‚æ‚éi‚è‚±‚İî•ñ‚ğæ“¾‚µ‚Ü‚·B
     * @return ‹Æ‘Ô‚É‚æ‚éi‚è‚±‚İî•ñ
     */
    public List getListTrnControlGyotai() {
        return listTrnControlGyotai;
    }
    /**
     * ‹Æ‘Ô‚É‚æ‚éi‚è‚±‚İî•ñ‚ğİ’è‚µ‚Ü‚·B
     * @param listTrnControlGyotai ‹Æ‘Ô‚É‚æ‚éi‚è‚±‚İî•ñ
     */
    public void setListTrnControlGyotai(List listTrnControlGyotai) {
        this.listTrnControlGyotai = listTrnControlGyotai;
    }
    /**
     * ‹Æ‘ÔŒÂ•Ê‚É‚æ‚éi‚è‚±‚İî•ñ‚ğæ“¾‚µ‚Ü‚·
     * @return ‹Æ‘ÔŒÂ•Ê‚É‚æ‚éi‚è‚±‚İî•ñ
     */
    public List getListTrnControlGyotaiKobetu() {
        return listTrnControlGyotaiKobetu;
    }
    /**
     * ‹Æ‘ÔŒÂ•Ê‚É‚æ‚éi‚è‚±‚İî•ñ‚ğİ’è‚µ‚Ü‚·B
     * @param listTrnControlGyotaiKobetu ‹Æ‘ÔŒÂ•Ê‚É‚æ‚éi‚è‚±‚İî•ñ
     */
    public void setListTrnControlGyotaiKobetu(List listTrnControlGyotaiKobetu) {
        this.listTrnControlGyotaiKobetu = listTrnControlGyotaiKobetu;
    }
    /**
     * ŒÂ“X‚É‚æ‚éi‚è‚±‚İî•ñ‚ğæ“¾‚µ‚Ü‚·B
     * @return ŒÂ“X‚É‚æ‚éi‚è‚±‚İî•ñ
     */
    public List getListTrnControlGyotaiTenpo() {
        return listTrnControlGyotaiTenpo;
    }
    /**
     * ŒÂ“X‚É‚æ‚éi‚è‚±‚İî•ñ‚ğİ’è‚µ‚Ü‚·
     * @param listTrnControlGyotaiTenpo ŒÂ“X‚É‚æ‚éi‚è‚±‚İî•ñ
     */
    public void setListTrnControlGyotaiTenpo(List listTrnControlGyotaiTenpo) {
        this.listTrnControlGyotaiTenpo = listTrnControlGyotaiTenpo;
    }
    /**
     * Š‘®‚É‚æ‚éi‚è‚±‚İî•ñ‚ğæ“¾‚µ‚Ü‚·B
     * @return Š‘®‚É‚æ‚éi‚è‚±‚İî•ñ
     */
    public List getListTrnControlShozoku() {
        return listTrnControlShozoku;
    }
    /**
     * Š‘®‚É‚æ‚éi‚è‚±‚İî•ñ‚ğİ’è‚µ‚Ü‚·B
     * @param listTrnControlShozoku Š‘®‚É‚æ‚éi‚è‚±‚İî•ñ
     */
    public void setListTrnControlShozoku(List listTrnControlShozoku) {
        this.listTrnControlShozoku = listTrnControlShozoku;
    }

    /**
     * Š‘®‚É‚æ‚éi‚è‚±‚İî•ñ”‚ğæ“¾‚µ‚Ü‚·
     * @return Š‘®‚É‚æ‚éi‚è‚±‚İî•ñ”
     */
    public int getListTrnControlShozokuSize() {
        return (listTrnControlShozoku == null) ? 0 : listTrnControlShozoku.size();
    }

    /**
     * ‰ïĞ‚É‚æ‚éi‚è‚±‚İî•ñ”‚ğæ“¾‚µ‚Ü‚·
     * @return ‰ïĞ‚É‚æ‚éi‚è‚±‚İî•ñ”
     */
    public int getListTrnControlCompanySize() {
        return (listTrnControlCompany == null) ? 0 : listTrnControlCompany.size();
    }

    /**
     * ‹Æ‘Ô‚É‚æ‚éi‚è‚±‚İî•ñ”‚ğæ“¾‚µ‚Ü‚·
     * @return ‹Æ‘Ô‚É‚æ‚éi‚è‚±‚İî•ñ”
     */
    public int getListTrnControlGyotaiSize() {
        return (listTrnControlGyotai == null) ? 0 : listTrnControlGyotai.size();
    }

    /**
     * ‹Æ‘ÔŒÂ•Ê‚É‚æ‚éi‚è‚±‚İî•ñ”‚ğæ“¾‚µ‚Ü‚·
     * @return ‹Æ‘ÔŒÂ•Ê‚É‚æ‚éi‚è‚±‚İî•ñ”
     */
    public int getListTrnControlGyotaiKobetuSize() {
        return (listTrnControlGyotaiKobetu == null) ? 0 : listTrnControlGyotaiKobetu.size();
    }

    /**
     * ŒÂ“X‚É‚æ‚éi‚è‚±‚İî•ñ”‚ğæ“¾‚µ‚Ü‚·
     * @return ŒÂ“X‚É‚æ‚éi‚è‚±‚İî•ñ”
     */
    public int getListTrnControlGyotaiTenpoSize() {
        return (listTrnControlGyotaiTenpo == null) ? 0 : listTrnControlGyotaiTenpo.size();
    }
}
