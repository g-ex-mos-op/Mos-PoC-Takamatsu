package jp.co.isid.mos.bird.bizsupport.moschickenstorestateconfirm.logic.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizsupport.common.dao.MstKanriKikanDao;
import jp.co.isid.mos.bird.bizsupport.common.entity.MstKanriKikan;
import jp.co.isid.mos.bird.bizsupport.moschickenstorestateconfirm.logic.ChangeTitleLogic;
import jp.co.isid.mos.bird.bizsupport.moschickenstorestateconfirm.logic.ConditionLogic;
import jp.co.isid.mos.bird.bizsupport.moschickenstorestateconfirm.dao.CodSibuListDao;
import jp.co.isid.mos.bird.bizsupport.moschickenstorestateconfirm.dao.CodShokuhouzaiListDao;
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
public class ConditionLogicImpl implements ConditionLogic {
    /** ロジックID */
    public static final String LOGIC_ID = MosChickenStoreStateConfirmUtil.SCREEN_ID+"L01";
    /* パラメーターキー：システム日付 */
    public static final String PK_SYS_DATE = "sysDate";
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
    /* ロジック【指定管理番号条件項目情報の取得】*/
    private ChangeTitleLogic mosChickenStoreStateConfirmChangeTitleLogic;
    
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
        String sysDate = (String)params.get(PK_SYS_DATE);
        if(MosChickenStoreStateConfirmUtil.isNull(sysDate)){
            //MSG【E0202】(がありません。)
            throw new MissingDataException("システム日付");
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
        String sysDate = (String)params.get(PK_SYS_DATE);
        //３．Dao【管理対象期間】の検索を実行する。
        List listKanriKikan = 
            getMosChickenMstKanriKikanDao().selectKikanList(sysDate);
        if(listKanriKikan != null && listKanriKikan.size() > 0){
            String companyCd=(String)params.get(PK_COMPANY_CD);
            BirdUserInfo userInfo = (BirdUserInfo)params.get(PK_USERINFO);
            String userId = userInfo.getUserID();
            boolean limitFlg = userInfo.isLimit();
            //５．Dao【対象支部情報】の各検索を実行し、条件項目対象支部の情報を取得する。
            List listSibu = getMosChickenStoreStateConfirmCodSibuListDao().select(companyCd, userId, limitFlg);
            MstKanriKikan eitity = (MstKanriKikan)listKanriKikan.get(0);
            params.put(ChangeTitleLogicImpl.PK_CKANRI_NO, eitity.getCkanriNo());
            params.put(ChangeTitleLogicImpl.PK_LIST_KIKAN, listKanriKikan);
            list = getMosChickenStoreStateConfirmChangeTitleLogic().execute(params);
            list.add(listKanriKikan);
            list.add(listSibu);
        }
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
    /**
     * @return mosChickenStoreStateConfirmChangeTitleLogic を戻します。
     */
    public ChangeTitleLogic getMosChickenStoreStateConfirmChangeTitleLogic() {
        return mosChickenStoreStateConfirmChangeTitleLogic;
    }
    /**
     * @param mosChickenStoreStateConfirmChangeTitleLogic 設定する mosChickenStoreStateConfirmChangeTitleLogic。
     */
    public void setMosChickenStoreStateConfirmChangeTitleLogic(
            ChangeTitleLogic mosChickenStoreStateConfirmChangeTitleLogic) {
        this.mosChickenStoreStateConfirmChangeTitleLogic = mosChickenStoreStateConfirmChangeTitleLogic;
    }

}
