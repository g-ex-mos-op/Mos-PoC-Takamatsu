/*
 * 作成日: 2006/09/
 *
 */
package jp.co.isid.mos.bird.bizsupport.moschickensalestateview.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.moschickensalestateview.dao.UIDayMenuInfoDao;
import jp.co.isid.mos.bird.bizsupport.moschickensalestateview.logic.GetDayMenuSumInfoLogic;
import jp.co.isid.mos.bird.bizsupport.moschickensalestateview.util.MosChichenSaleStateUtil;
import jp.co.isid.mos.bird.framework.exception.NotNullException;

/**
 * 日付とメニュー毎の合計情報取得ロジック
 * 
 * @author xlee
 */
public class GetDayMenuSumInfoLogicImpl implements GetDayMenuSumInfoLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBS017L05";

    /**
     * 日付とメニュー毎の合計情報DAOを取得します。
     */
    private UIDayMenuInfoDao uiDayMenuInfoDao;

    /**
     * 日付とメニュー毎の合計情報DAOを取得します。
     * @return 日付とメニュー毎の合計情報DAO
     */
    public UIDayMenuInfoDao getUIDayMenuInfoDao() {
        return uiDayMenuInfoDao;
    }

    /**
     * 日付とメニュー毎の合計情報DAOを設定します。
     * @param uiDayMenuInfoDao 日付とメニュー毎の合計情報DAO
     */
    public void setUIDayMenuInfoDao(UIDayMenuInfoDao uiDayMenuInfoDao) {
        this.uiDayMenuInfoDao = uiDayMenuInfoDao;
    }

    /**
     * 日付とメニュー毎の合計情報を取得
     * @param　groupKbn　取得するグループの種類
     * @param　sysDate　システム日付
     * @param　ckanriNo　管理番号
     * @param　miseCd　店舗コード
     * @param　menuGroup　メニューグループ
     * @param　taishoDtTo　対象期間の開始日
     * @param　taishoDtFrom　対象期間の終了日
     * 
     * @return  合計情報
     */
    public List execute(String groupKbn, String sysDate, String ckanriNo, String miseCd, String menuGroup, String taishoDtTo, String taishoDtFrom) {
        //エラー処理：
    	if(MosChichenSaleStateUtil.isNull(sysDate)){
            throw new NotNullException("システム日付"); //E0506 ユーザーID
        }
    	if(MosChichenSaleStateUtil.isNull(groupKbn)){
            throw new NotNullException("取得するグループの種類"); //E0506 取得するグループの種類
        }
    	if(MosChichenSaleStateUtil.isNull(ckanriNo)){
            throw new NotNullException("管理番号"); //E0506 管理番号
        }
    	if(MosChichenSaleStateUtil.isNull(miseCd)){
            throw new NotNullException("店舗コード"); //E0506 店舗コード
        }
    	if(MosChichenSaleStateUtil.isNull(menuGroup)){
            throw new NotNullException("メニューグループ"); //E0506 メニューグループ
        }
    	if(MosChichenSaleStateUtil.isNull(taishoDtTo)){
            throw new NotNullException("対象期間の開始日"); //E0506 対象期間の開始日
        }
    	if(MosChichenSaleStateUtil.isNull(taishoDtFrom)){
            throw new NotNullException("対象期間の終了日"); //E0506 対象期間の終了日
        }
    	List tmpResult = getUIDayMenuInfoDao().getDayMenuSumInfo(groupKbn, sysDate, ckanriNo, miseCd, menuGroup, taishoDtTo, taishoDtFrom);

    	return tmpResult;
    }
}
