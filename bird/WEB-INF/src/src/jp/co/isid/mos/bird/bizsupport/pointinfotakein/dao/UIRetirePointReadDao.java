/**
 *
 */
package jp.co.isid.mos.bird.bizsupport.pointinfotakein.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.pointinfotakein.entity.UIRetirePointReadInfo;

/**
 * ‘ŞE¸Zæ—š—ğDao
 * @author yushuncheng
 *
 */
public interface UIRetirePointReadDao {

	public static final Class BEAN = UIRetirePointReadInfo.class;

    public static final String getToriSakujoList_ARGS = "tmsp";
    public static final String getUserList_ARGS = "tmsp";
    public static final String getRetiList_ARGS = "tmsp";
    public static final String getResiList_ARGS = "tmsp";
    public static final String getPointListByFlg_ARGS = "tmsp";

    /**
     * ‘ŞE¸Zæ—š—ğ“o˜^
     * @param retirePointReadList
     */
    public void insertRetirePointReadList(List retirePointReadList);


    /**
     * ‘ŞE¸Zæ—š—ğ‚Æ‘ŞE¸Z—š—ğƒŠƒXƒg‚Ìæ“¾
     */
    public List<UIRetirePointReadInfo> getToriSakujoList(java.sql.Timestamp tmsp);


    /**
     * ‘ŞE¸Zæ—š—ğ‚Æ“‡ƒ†[ƒU[i”ñ—š—ğjƒŠƒXƒg‚Ìæ“¾
     * @param tmsp
     * @return
     */
    public List<UIRetirePointReadInfo> getUserList(java.sql.Timestamp tmsp);


    /**
     * ‘ŞE¸Zæ—š—ğ‚Æ‘ŞE–—RƒŠƒXƒg‚Ìæ“¾
     * @param tmsp
     * @return
     */
    public List<UIRetirePointReadInfo> getRetiList(java.sql.Timestamp tmsp);


    /**
     * ‘ŞE¸Zæ—š—ğ‚Æ‘ŞE–—Rx‹‹—¦ƒŠƒXƒg‚Ìæ“¾
     * @param tmsp
     * @return
     */
    public List<UIRetirePointReadInfo> getResiList(java.sql.Timestamp tmsp);


    /**
     * ‘ŞE¸Zæ—š—ğ‚Æ‘ŞE–—Rx‹‹—¦‚ÆĞˆõ•t—^ƒ|ƒCƒ“ƒg—š—ğƒŠƒXƒg‚Ìæ“¾
     * @param tmsp
     * @return
     */
    public List<UIRetirePointReadInfo> getPointListByFlg(java.sql.Timestamp tmsp);

    /**
     *
     * @param retirePointReadList
     */
    public void updateRetirePointReadList(List retirePointReadList);
}
