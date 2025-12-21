package jp.co.isid.mos.bird.bizreport.common.camp.logic.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizreport.common.camp.code.TaishoJoken;
import jp.co.isid.mos.bird.bizreport.common.camp.dao.CodHyojiTaishoDao;
import jp.co.isid.mos.bird.bizreport.common.camp.logic.GetHyojiTaishoLogic;
import jp.co.isid.mos.bird.bizreport.common.dao.CodBlockDao;
import jp.co.isid.mos.bird.bizreport.common.logic.impl.TaishoJokenListLogicImpl;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;

/**
 * 表示対象データ検索ロジック
 * 
 * パラメーター：下記の値を保持したMapを引数とする。
 *               「companyCd」会社コード
 *               「taishoJoken」対象条件
 * @author xkinu
 */
public class GetHyojiTaishoLogicImpl implements GetHyojiTaishoLogic {
    /** ロジックID */
    public static final String LOGIC_ID = "BBRCMPL02";
    
    /*【DAO】表示対象コード*/
    private CodHyojiTaishoDao commonCampaignCodHyojiTaishoDao;
    /*【DAO】ブロックコード*/
    private CodBlockDao codBlockDao;
    
    /**
     * 事前条件処理
     * 
     * @param dto
     * @throws ApplicationException
     */
    private void validate(BirdDateInfo dataInfo, BirdUserInfo userInfo
    		, String companyCd, String campId, String taishoJoken)
    {
        if (dataInfo == null) {
            throw new MissingDataException("BIRD日付情報");
        }
        if (userInfo == null) {
            throw new MissingDataException("BIRDユーザー情報");
        }
        if (isNull(companyCd)) {
            throw new NotNullException("会社コード");
        }
        if (isNull(campId)) {
            throw new NotNullException("キャンペーン識別番号");
        }
        //対象条件
        if (isNull(taishoJoken)) {
            throw new NotNullException("対象条件");
        }        
    }
    
    /**
     * 表示対象検索結果取得処理実行
     * 
     * @param dataInfo
     * @param userInfo
     * @param companyCd
     * @param campId
     * @param taishoJoken
     * @return
     */
     public Map execute(BirdDateInfo dataInfo, BirdUserInfo userInfo
    		 , String companyCd, String campId, String taishoJoken)
     {
        //事前条件処理実行
        validate(dataInfo, userInfo, companyCd, campId, taishoJoken);
        //対象条件
        Map returnMap = new HashMap();
        // 表示対象データ取得
        returnMap.put(RK_LIST, executeList(dataInfo, userInfo, companyCd, campId, taishoJoken));
        if (TaishoJokenListLogicImpl.CODE_SIBU.equals(taishoJoken)) {
            //支部の場合
            returnMap.put(RK_LIST_BLOCK, getCodBlockDao().select());
        }        
        //検索データを戻す。
        return returnMap;
    }
    /**
     * 表示対象の種類を取得
     * 
     * @param dataInfo
     * @param userInfo
     * @param companyCd
     * @param campId
     * @param taishoJoken
     * @return
     */
    private List executeList(BirdDateInfo dataInfo, BirdUserInfo userInfo
   		 , String companyCd, String campId, String taishoJoken) 
    {
        List list = null;
        //ユーザーID
        String userId = userInfo.getUserID();
        //ユーザータイプコード
        String userTypeCd = userInfo.getMstUser().getUserTypeCd();
        //リミットフラグ
        boolean limitFlg = userInfo.isLimit();
        if(TaishoJoken.CODE_JIGYOU.equals(taishoJoken)) {
            //事業本部の場合
            list = getCommonCampaignCodHyojiTaishoDao().selectJigyoHonbu(userId, userTypeCd, limitFlg, companyCd, campId);
        }
        else if (TaishoJoken.CODE_SLAREA.equals(taishoJoken)) {
            //営業エリアの場合
            list = getCommonCampaignCodHyojiTaishoDao().selectSlArea(userId, userTypeCd, limitFlg, companyCd, campId);
        }
        else if (TaishoJoken.CODE_SIBU.equals(taishoJoken)) {
            //支部の場合
            list = getCommonCampaignCodHyojiTaishoDao().selectSibu(userId, userTypeCd, limitFlg, companyCd, campId);
        }
        else if (TaishoJoken.CODE_AREADAI.equals(taishoJoken)) {
            //エリア大の場合
            list = getCommonCampaignCodHyojiTaishoDao().selectAreaDai(userId, userTypeCd, limitFlg, companyCd, campId);
        }
        else if (TaishoJoken.CODE_MISE.equals(taishoJoken)){
            //店舗の場合
            list = getCommonCampaignCodHyojiTaishoDao().selectMise(dataInfo.getSysDate(), userId, userTypeCd, limitFlg, companyCd, campId);
        }
        return list;
    }
    /**
     * ブロック情報Dao取得処理
     * @return　codBlockDao
     */
    public CodBlockDao getCodBlockDao() {
        return codBlockDao;
    }
    /**
     * ブロック情報Dao設定処理
     * 
     * @param CodBlockDao 表示対象情報 
     */
    public void setCodBlockDao(CodBlockDao dao) {
        this.codBlockDao = dao;
    }
    /**
     * Null判断処理
     * @param str
     * @return boolean true：空かNullの場合
     */
    private boolean isNull(String str) {
        return (str == null || "".equals(str.trim())) ? true : false;
    }

	/**
	 * 表示対象情報Dao設定処理
	 * 
	 * @param CodHyojiTaishoDao 表示対象情報 
	 */
	public void setCommonCampaignCodHyojiTaishoDao(CodHyojiTaishoDao HyojiTaishoDao) {
		this.commonCampaignCodHyojiTaishoDao = HyojiTaishoDao;
	}
	/**
	 * @return codHyojiTaishoDao を戻します。
	 */
	public CodHyojiTaishoDao getCommonCampaignCodHyojiTaishoDao() {
		return commonCampaignCodHyojiTaishoDao;
	}
}
