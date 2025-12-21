package jp.co.isid.mos.bird.bizsupport.moschickenstateconfirm.logic.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizsupport.common.dao.MstKanriKikanDao;
import jp.co.isid.mos.bird.bizsupport.common.entity.MstKanriKikan;
import jp.co.isid.mos.bird.bizsupport.moschickenstateconfirm.dao.CodMiseListDao;
import jp.co.isid.mos.bird.bizsupport.moschickenstateconfirm.entity.CodShokuhouzaiList;
import jp.co.isid.mos.bird.bizsupport.moschickenstateconfirm.logic.CheckOnerExistLogic;
import jp.co.isid.mos.bird.bizsupport.moschickenstateconfirm.logic.ConditionLogic;
import jp.co.isid.mos.bird.bizsupport.moschickenstateconfirm.util.MosChickenStateConfirmUtil;
import jp.co.isid.mos.bird.bizsupport.moschickenstorestateconfirm.dao.CodShokuhouzaiListDao;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;

/**
 * 予約・在庫状況確認(モスチキン用画面）
 * 条件項目情報の取得ロジック
 * 
 * @author xkinu
 *
 */
public class ConditionLogicImpl implements ConditionLogic {
    /** ロジックID */
    public static final String LOGIC_ID = MosChickenStateConfirmUtil.SCREEN_ID+"L01";
    /* パラメーターキー：システム日付 */
    public static final String PK_SYS_DATE = "sysDate";
    /* パラメーターキー：会社コード */
    public static final String PK_COMPANY_CD = "companyCd";
    /* パラメーターキー：オーナーコード */
    public static final String PK_ONER_CD = "onerCd";
    /* パラメーターキー：ユーザー情報 */
    public static final String PK_USERINFO = "userInfo";
    
    /* ロジック【対象オーナー存在チェック】*/
    private CheckOnerExistLogic mosChickenStateConfirmCheckOnerExistLogic;
    
    /* DAO【モスチキン管理対象期間】*/
    private MstKanriKikanDao mosChickenMstKanriKikanDao;
    /*DAO【店舗取得】*/
    private CodMiseListDao mosChickenStateConfirmCodMiseListDao;
//add 2019/08/12 USI張 begin
    /*DAO【食包材情報】*/
    private CodShokuhouzaiListDao mosChickenCodShokuhouzaiListDao;
//add 2019/08/12 USI張 end

    /**
     * 事前条件処理
     * @param map
     * @throws ApplicationException
     */
    private void validate(final Map params) {
        BirdUserInfo userInfo = (BirdUserInfo)params.get(PK_USERINFO);
        if(userInfo == null){
            //MSG【E0202】(がありません。)
            throw new MissingDataException("ユーザー情報");
        }
        String companyCd=(String)params.get(PK_COMPANY_CD);
        if(MosChickenStateConfirmUtil.isNull(companyCd)){
            //MSG【E0202】(がありません。)
            throw new MissingDataException("会社コード");
        }
        String sysDate = (String)params.get(PK_SYS_DATE);
        if(MosChickenStateConfirmUtil.isNull(sysDate)){
            //MSG【E0202】(がありません。)
            throw new MissingDataException("システム日付");
        }
        String userTypeCd = userInfo.getMstUser().getUserTypeCd();
        //２．ユーザータイプコードが本部の場合、オーナーコードの必須・妥当性チェックを行う。
        if(MosChickenStateConfirmUtil.USER_TYPE_CD_HONBU.equals(userTypeCd)){
            MosChickenStateConfirmUtil.checkOnerCd((String)params.get(PK_ONER_CD));
        }
        
    }
    /**
     * メイン実行処理
     * 
     */
    public List execute(Map params) {
        //１．事前条件判断処理
        validate(params);
        BirdUserInfo userInfo = (BirdUserInfo)params.get(PK_USERINFO);
        String userTypeCd = userInfo.getMstUser().getUserTypeCd();
        String userId = userInfo.getUserID();
        //２．ユーザータイプコードが本部の場合、ロジック【対象オーナー存在チェック】を実行する。
        if(MosChickenStateConfirmUtil.USER_TYPE_CD_HONBU.equals(userTypeCd)){
            getMosChickenStateConfirmCheckOnerExistLogic().execute(params);
        }
        //３．リターン値Listをインスタンス化する。
        List list = new ArrayList();
        String companyCd=(String)params.get(PK_COMPANY_CD);
        String sysDate = (String)params.get(PK_SYS_DATE);
        String onerCd = (String)params.get(PK_ONER_CD);
        //３．Dao【管理対象期間】の検索を実行する。
        List listKanriKikan = 
            getMosChickenMstKanriKikanDao().selectKikanList(sysDate);
        //４．Dao【対象店舗情報】の各検索を実行し、条件項目対象店舗の情報を取得する。
        List listMise = null;
        if(MosChickenStateConfirmUtil.USER_TYPE_CD_HONBU.equals(userTypeCd)){
            //a.本部ユーザーの場合Dao【対象店舗情報】.本部ユーザー店舗情報取得を実行する。
            listMise = getMosChickenStateConfirmCodMiseListDao().getHonbuUserMiseInfo(companyCd, onerCd, sysDate);
        }
        else if(MosChickenStateConfirmUtil.USER_TYPE_CD_ONER.equals(userTypeCd)){
            //b.オーナーユーザーの場合Dao【対象店舗情報】.オーナーユーザー店舗情報取得を実行する。
            listMise = getMosChickenStateConfirmCodMiseListDao().getOnerUserMiseInfo(companyCd, userId, sysDate);
        }
        else if(MosChickenStateConfirmUtil.USER_TYPE_CD_MISE.equals(userTypeCd)){
            //c.店舗ユーザーの場合Dao【対象店舗情報】.店舗ユーザー店舗情報取得を実行する。
            listMise = getMosChickenStateConfirmCodMiseListDao().getTenpoUserMiseInfo(companyCd, userId, sysDate);
        }
        if(listKanriKikan != null && listKanriKikan.size() > 0){
            MstKanriKikan entity = (MstKanriKikan)listKanriKikan.get(0);
            //５．ユーティル【Staticメソッドクラス】.指定管理番号条件項目情報の取得を実行する。
            list = MosChickenStateConfirmUtil.createListJokenData(entity.getCkanriNo(), listKanriKikan);
            //６．条件項目情報をリターン値Listへ設定します。
            list.add(0,listKanriKikan);
            list.add(1,listMise);
//add 2019/08/12 USI張 begin
            list.add(2,this.getShokuList(entity.getCkanriNo()));
//add 2019/08/12 USI張 end
        }
        //７．リターン値Listをリターンする。
        return list;
    }

//add 2019/08/12 USI張 begin
    public List getShokuList(String ckanriNo) {
        //食包材
        List listShokuhouzai = getMosChickenCodShokuhouzaiListDao().select(ckanriNo);
        //プルダウンは末尾に「全て」を追加する
        CodShokuhouzaiList allShokuhou = new CodShokuhouzaiList();
        allShokuhou.setShokuCd("all");
        allShokuhou.setShokuNameKna("全て");
        listShokuhouzai.add(allShokuhou);
    	return listShokuhouzai;
    }

//add 2019/08/12 USI張 end
    /**
     * @return mosChickenStateConfirmCodMiseListDao を戻します。
     */
    public CodMiseListDao getMosChickenStateConfirmCodMiseListDao() {
        return mosChickenStateConfirmCodMiseListDao;
    }
    /**
     * @param mosChickenStateConfirmCodMiseListDao 設定する mosChickenStateConfirmCodMiseListDao。
     */
    public void setMosChickenStateConfirmCodMiseListDao(
            CodMiseListDao mosChickenStateConfirmCodMiseListDao) {
        this.mosChickenStateConfirmCodMiseListDao = mosChickenStateConfirmCodMiseListDao;
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
     * @return mosChickenStateConfirmCheckOnerExistLogic を戻します。
     */
    public CheckOnerExistLogic getMosChickenStateConfirmCheckOnerExistLogic() {
        return mosChickenStateConfirmCheckOnerExistLogic;
    }
    /**
     * @param mosChickenStateConfirmCheckOnerExistLogic 設定する mosChickenStateConfirmCheckOnerExistLogic。
     */
    public void setMosChickenStateConfirmCheckOnerExistLogic(
            CheckOnerExistLogic mosChickenStateConfirmCheckOnerExistLogic) {
        this.mosChickenStateConfirmCheckOnerExistLogic = mosChickenStateConfirmCheckOnerExistLogic;
    }

//add 2019/08/12 USI張 begin
    /**
     * @return mosChickenCodShokuhouzaiListDao を戻します。
     */
	public CodShokuhouzaiListDao getMosChickenCodShokuhouzaiListDao() {
		return mosChickenCodShokuhouzaiListDao;
	}
    /**
     * @param mosChickenCodShokuhouzaiListDao 設定する mosChickenCodShokuhouzaiListDao。
     */
	public void setMosChickenCodShokuhouzaiListDao(CodShokuhouzaiListDao mosChickenCodShokuhouzaiListDao) {
		this.mosChickenCodShokuhouzaiListDao = mosChickenCodShokuhouzaiListDao;
	}
//add 2019/08/12 USI張 end
}
