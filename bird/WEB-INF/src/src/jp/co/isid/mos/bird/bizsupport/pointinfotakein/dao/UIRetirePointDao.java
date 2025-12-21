/**
 *
 */
package jp.co.isid.mos.bird.bizsupport.pointinfotakein.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.pointinfotakein.entity.UIRetirePointInfo;

/**
 * ‘ŞE¸Z—š—ğDao
 * @author yushuncheng
 *
 */
public interface UIRetirePointDao {

	public static final Class BEAN = UIRetirePointInfo.class;

    public static final String checkExist_ARGS = "nendo,userId,nyusyaDt";
    public static final String getDeleteList_ARGS = "tmsp";
    public static final String deleteBD61_SQL = "delete from BD61TSES where USER_ID = ?";

    /**
     * ‘ŞE¸Z—š—ğXV
     * @param retirePointList
     */
    public void updateRetirePointList(List retirePointList);

    /**
     * ‘ŞE¸Z—š—ğ“o˜^
     * @param retirePointList
     */
    public void insertRetirePointList(List retirePointList);

    /**
     * ‘ŞE¸Z—š—ğíœ
     * @param retirePointList
     */
    public void deleteRetirePointList(List retirePointList);

    /**
     * ‘ŞE¸Z—š—ğ‚Ìæ“¾
     * @param String ”N“x
     * @param String Ğˆõ”Ô†
     * @param String “üĞ“ú
     * @return UIRetirePointInfo
     */
    public UIRetirePointInfo checkExist(String nendo, String userId, String nyusyaDt);

    /**
     * ‘ŞE¸Zæ—š—ğ‚É‚æ‚èA‘ŞE¸Z—š—ğ‚Ìíœƒf[ƒ^‚Ìæ“¾
     * @param tmsp
     * @param pointShu
     * @return
     */
    public List<UIRetirePointInfo> getDeleteList(java.sql.Timestamp tmsp);

    public void deleteBD61(String userId);
}
