package jp.co.isid.mos.bird.bizsupport.moschickenstateconfirm.logic.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizsupport.moschickenstateconfirm.dao.UIOnerInfoDao;
import jp.co.isid.mos.bird.bizsupport.moschickenstateconfirm.logic.CheckOnerExistLogic;
import jp.co.isid.mos.bird.bizsupport.moschickenstateconfirm.util.MosChickenStateConfirmUtil;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.GenericMessageException;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;

/**
 * 予約・在庫状況確認(モスチキン用画面）
 * オーナー存在チェックロジック
 * 
 * @author xkinu
 *
 */
public class CheckOnerExistLogicImpl implements CheckOnerExistLogic {
    /** ロジックID */
    public static final String LOGIC_ID = MosChickenStateConfirmUtil.SCREEN_ID+"L04";
    /* パラメーターキー：会社コード */
    public static final String PK_COMPANY_CD = "companyCd";
    /* パラメーターキー：オーナーコード */
    public static final String PK_ONER_CD = "onerCd";
    /* パラメーターキー：ユーザー情報 */
    public static final String PK_USERINFO = "userInfo";
    /*DAO【対象オーナー情報】*/
    private UIOnerInfoDao mosChickenStateConfirmUIOnerInfoDao;
    
    /**
     * 事前条件処理
     * @param map
     * @throws ApplicationException
     */
    private void validate(final Map params) {
        String companyCd=(String)params.get(PK_COMPANY_CD);
        if(MosChickenStateConfirmUtil.isNull(companyCd)){
            //MSG【E0202】(がありません。)
            throw new MissingDataException("会社コード");
        }
        String onerCd = MosChickenStateConfirmUtil.checkOnerCd((String)params.get(PK_ONER_CD));
        params.put(PK_ONER_CD, onerCd);
    }
    /**
     * メイン実行処理
     */
    public List execute(Map params) {
        //１．事前条件判断処理
        validate(params);
        //２．リターン値Listをインスタンス化する。
        List list = new ArrayList();
        String companyCd=(String)params.get(PK_COMPANY_CD);
        String onerCd = (String)params.get(PK_ONER_CD);
        //３．Dao【対象オーナー情報】の検索を実行する。
        list = getMosChickenStateConfirmUIOnerInfoDao().select(companyCd, onerCd);
        if(list == null || list.size() < 1){
            throw new GenericMessageException("そのオーナーコードは存在しません。", "onerCd",0);
        }
        
        //４．リターン値Listをリターンする。
        return list;
    }
    /**
     * @return mosChickenStateConfirmUIOnerInfoDao を戻します。
     */
    public UIOnerInfoDao getMosChickenStateConfirmUIOnerInfoDao() {
        return mosChickenStateConfirmUIOnerInfoDao;
    }
    /**
     * @param mosChickenStateConfirmUIOnerInfoDao 設定する mosChickenStateConfirmUIOnerInfoDao。
     */
    public void setMosChickenStateConfirmUIOnerInfoDao(
            UIOnerInfoDao mosChickenStateConfirmUIOnerInfoDao) {
        this.mosChickenStateConfirmUIOnerInfoDao = mosChickenStateConfirmUIOnerInfoDao;
    }
}
