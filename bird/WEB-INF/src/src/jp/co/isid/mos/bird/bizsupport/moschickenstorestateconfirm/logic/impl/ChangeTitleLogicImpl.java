package jp.co.isid.mos.bird.bizsupport.moschickenstorestateconfirm.logic.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizsupport.common.dao.MstKanriKikanDao;
import jp.co.isid.mos.bird.bizsupport.moschickenstorestateconfirm.dao.CodShokuhouzaiListDao;
import jp.co.isid.mos.bird.bizsupport.moschickenstorestateconfirm.dao.CodSibuListDao;
import jp.co.isid.mos.bird.bizsupport.moschickenstorestateconfirm.logic.ChangeTitleLogic;
import jp.co.isid.mos.bird.bizsupport.moschickenstorestateconfirm.util.MosChickenStoreStateConfirmUtil;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;

/**
 * 店別予約状況一覧(モスチキン用画面）
 * 条件項目情報の取得ロジック
 * 
 * @author xkinu
 *
 */
public class ChangeTitleLogicImpl implements ChangeTitleLogic {
    /** ロジックID */
    public static final String LOGIC_ID = MosChickenStoreStateConfirmUtil.SCREEN_ID+"L02";
    /* パラメーターキー：指定管理番号 */
    public static final String PK_CKANRI_NO = "ckanriNo";
    /* パラメーターキー：管理対象期間情報 */
    public static final String PK_LIST_KIKAN = "listMstKikan";
    /* パラメーターキー：会社コード */
    public static final String PK_COMPANY_CD = "companyCd";
    /* パラメーターキー：ユーザー情報 */
    public static final String PK_USERINFO = "userInfo";
    /* DAO【モスチキン管理対象期間】*/
    private MstKanriKikanDao mosChickenMstKanriKikanDao;
    /*DAO【食包材情報】*/
    private CodShokuhouzaiListDao mosChickenStoreStateConfirmCodShokuhouzaiListDao;
    /*DAO【支部取得】*/
    private CodSibuListDao mosChickenStoreStateConfirmCodSibuListDao;
    
    /**
     * 事前条件処理
     * @param map
     * @throws ApplicationException
     */
    private void validate(final Map params) {
        String companyCd=(String)params.get(PK_COMPANY_CD);
        if(MosChickenStoreStateConfirmUtil.isNull(companyCd)){
            //MSG【E0202】(がありません。)
            throw new MissingDataException("会社コード");
        }
        String ckanriNo = (String)params.get(PK_CKANRI_NO);
        if(MosChickenStoreStateConfirmUtil.isNull(ckanriNo)){
            //MSG【E0202】(がありません。)
            throw new MissingDataException("管理番号");
        }
        List listMstKikan = (List)params.get(PK_LIST_KIKAN);
        if(listMstKikan == null && listMstKikan.size() == 0){
            //MSG【E0202】(がありません。)
            throw new MissingDataException("管理対象期間情報");
        }
        BirdUserInfo userInfo = (BirdUserInfo)params.get(PK_USERINFO);
        if(userInfo == null){
            //MSG【E0202】(がありません。)
            throw new MissingDataException("ユーザー情報");
        }
    }
    /**
     * ロジック実行処理
     * 
     */
    public List execute(Map params) {
        //１．事前条件判断処理
        validate(params);
        //２．リターン値Listをインスタンス化する。
        List list = new ArrayList();
        String ckanriNo = (String)params.get(PK_CKANRI_NO);
        //３．Dao【管理対象期間】の検索を実行する。
        List listKanriKikan = (List)params.get(PK_LIST_KIKAN);
        //４．Dao【対象食包材】の検索を実行し、条件項目対象食包材の情報を取得する。
        List listShokuhouzai = getMosChickenStoreStateConfirmCodShokuhouzaiListDao().select(ckanriNo);
        //５．ユーティル【Staticメソッドクラス】.指定管理番号条件項目情報の取得を実行する。
        List listDate = MosChickenStoreStateConfirmUtil.createTargetDateList(ckanriNo, listKanriKikan);
        //６．条件項目情報をリターン値Listへ設定します。
        list.add(0,listShokuhouzai);
        list.add(1,listDate);
        //８．リターン値Listをリターンする。
        return list;
    }
    /**
     * @return mosChickenMstKanriKikanDao を戻します。
     */
    public MstKanriKikanDao getMosChickenMstKanriKikanDao() {
        return mosChickenMstKanriKikanDao;
    }
    /**
     * @param mosChickenMstKanriKikanDao 設定する mosChickenMstKanriKikanDao。
     */
    public void setMosChickenMstKanriKikanDao(
            MstKanriKikanDao mosChickenMstKanriKikanDao) {
        this.mosChickenMstKanriKikanDao = mosChickenMstKanriKikanDao;
    }
    /**
     * @return mosChickenStoreStateConfirmCodSibuListDao を戻します。
     */
    public CodSibuListDao getMosChickenStoreStateConfirmCodSibuListDao() {
        return mosChickenStoreStateConfirmCodSibuListDao;
    }
    /**
     * @param mosChickenStoreStateConfirmCodSibuListDao 設定する mosChickenStoreStateConfirmCodSibuListDao。
     */
    public void setMosChickenStoreStateConfirmCodSibuListDao(
            CodSibuListDao mosChickenStoreStateConfirmCodSibuListDao) {
        this.mosChickenStoreStateConfirmCodSibuListDao = mosChickenStoreStateConfirmCodSibuListDao;
    }
    /**
     * @return mosChickenStoreStateConfirmCodShokuhouzaiListDao を戻します。
     */
    public CodShokuhouzaiListDao getMosChickenStoreStateConfirmCodShokuhouzaiListDao() {
        return mosChickenStoreStateConfirmCodShokuhouzaiListDao;
    }
    /**
     * @param mosChickenStoreStateConfirmCodShokuhouzaiListDao 設定する mosChickenStoreStateConfirmCodShokuhouzaiListDao。
     */
    public void setMosChickenStoreStateConfirmCodShokuhouzaiListDao(
            CodShokuhouzaiListDao mosChickenStoreStateConfirmCodShokuhouzaiListDao) {
        this.mosChickenStoreStateConfirmCodShokuhouzaiListDao = mosChickenStoreStateConfirmCodShokuhouzaiListDao;
    }

}
