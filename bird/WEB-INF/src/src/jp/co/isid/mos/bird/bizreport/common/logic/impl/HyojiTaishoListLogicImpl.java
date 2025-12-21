package jp.co.isid.mos.bird.bizreport.common.logic.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizreport.common.dao.CodBlockDao;
import jp.co.isid.mos.bird.bizreport.common.dao.CodHyojiTaishoDao;
import jp.co.isid.mos.bird.bizreport.common.dto.BizReportGroupDto;
import jp.co.isid.mos.bird.bizreport.common.logic.HyojiTaishoMapLogic;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * 表示対象データ検索ロジック
 * 
 * パラメーター：下記の値を保持したMapを引数とする。
 *               「companyCd」会社コード
 *               「taishoJoken」対象条件
 * 2008/06/16 エリア大情報 対応
 * @author xkinu
 */
public class HyojiTaishoListLogicImpl implements HyojiTaishoMapLogic {
    /** ロジックID */
    public static final String LOGIC_ID = "BBR000L08";

    /** パラメーター名：会社コード */
    public static final String PM_COMPANY_CD = KEY_COMPANY_CD;
    /** パラメーターキー：対象条件 */
    public static final String PM_TAISHOJOKEN = KEY_TAISHOJOKEN;
    /** パラメーターキー：アプリ日付 */
    public static final String PM_APP_DATE = "PM_APP_DATE";
    
    /*【DAO】表示対象コード*/
    private CodHyojiTaishoDao codHyojiTaishoDao;
    /*【DAO】ブロックコード*/
    private CodBlockDao codBlockDao;
    
    /**
     * 事前条件処理
     * 
     * @param dto
     * @throws ApplicationException
     */
    private void validate(Map map) throws ApplicationException {
        //会社コード
        String companyCd = (String) map.get(PM_COMPANY_CD);
        if (isNull(companyCd)) {
            throw new NotNullException("会社コード");
        }
        //対象条件
        String taishoJoken = (String) map.get(PM_TAISHOJOKEN);
        if (isNull(taishoJoken)) {
            throw new NotNullException("対象条件");
        }
        
        //アプリ日付(オーナーでかつ個店の時のみ)
        String userTypeCd = getBirdUserInfo().getMstUser().getUserTypeCd();
        if(BizReportGroupDto.USERTYPE_ONER.equals(userTypeCd) && 
                TaishoJokenListLogicImpl.CODE_MISE.equals(taishoJoken)) {

            String appDate = (String)map.get(PM_APP_DATE);
            if(appDate == null || appDate.length() != 8){
                throw new NotNullException("アプリ日付");
            }
        }
        
    }
    
    /**
     * 条件画面出力データ検索を行う
     * 
     * 条件画面に必要な全てのデータを戻します。
     * 
     * @param BizReportRefDto dto 画面データ保持クラス
     * @exception ApplicationException
     */
    public Map execute(final Map map) throws ApplicationException {
        //事前条件処理実行
        validate(map);
        //対象条件
        String taishoJoken = (String) map.get(PM_TAISHOJOKEN);
        Map returnMap = new HashMap();
        // 表示対象データ取得
        returnMap.put("list", executeList(map));
        if (TaishoJokenListLogicImpl.CODE_SIBU.equals(taishoJoken)) {
            //支部の場合
            returnMap.put("listBlock", getCodBlockDao().select());
        }        
        //検索データを戻す。
        return returnMap;
    }
    /**
     * 表示対象の種類を取得
     * 
     * @param companyCd 会社コード
     * @param taishoJoken 対象条件
     * @return List 表示対象データ
     * @exception ApplicationException
     */
    private List executeList(final Map map) throws ApplicationException {
        List list = null;
        //ユーザーID
        String userId = getBirdUserInfo().getUserID();
        //ユーザータイプコード
        String userTypeCd = getBirdUserInfo().getMstUser().getUserTypeCd();
        //リミットフラグ
        boolean limitFlg = getBirdUserInfo().isLimit();
        //会社コード
        String companyCd = (String) map.get(PM_COMPANY_CD);
        //対象条件
        String taishoJoken = (String) map.get(PM_TAISHOJOKEN);
        //アプリ日付
        String appDate = (String)map.get(PM_APP_DATE);
        if(TaishoJokenListLogicImpl.CODE_JIGYOU.equals(taishoJoken)) {
            //事業本部の場合
            list = getCodHyojiTaishoDao().selectJigyoHonbu(companyCd, userId, userTypeCd, limitFlg);
        }
        else if (TaishoJokenListLogicImpl.CODE_SLAREA.equals(taishoJoken)) {
            //営業エリアの場合
            list = getCodHyojiTaishoDao().selectSlArea(companyCd, userId, userTypeCd, limitFlg);
        }
        else if (TaishoJokenListLogicImpl.CODE_SIBU.equals(taishoJoken)) {
            //支部の場合
            list = getCodHyojiTaishoDao().selectSibu(companyCd, userId, userTypeCd, limitFlg);
        }
        else if (TaishoJokenListLogicImpl.CODE_MISE.equals(taishoJoken)){
            //店舗の場合
            list = getCodHyojiTaishoDao().selectMise(companyCd, userId, userTypeCd, limitFlg, appDate);
        }
        else if (TaishoJokenListLogicImpl.CODE_AREADAI.equals(taishoJoken)) {
            //支部の場合
            list = getCodHyojiTaishoDao().selectAreaDai(companyCd, userId, userTypeCd, limitFlg);
        }
        return list;
    }
    /**
     * 
     * @return
     */
    private S2Container getS2Container() {
        return SingletonS2ContainerFactory.getContainer();
    }
    /**
     * BIRDユーザー情報取得処理
     * @return birdUserInfo を戻します。
     */
    private BirdUserInfo getBirdUserInfo() {
        return (BirdUserInfo) getS2Container().getComponent(BirdUserInfo.class);
    }
    
    /**
     * 表示対象情報Dao取得処理
     * @return　CodHyojiTaishoDao
     */
	public CodHyojiTaishoDao getCodHyojiTaishoDao() {
		return codHyojiTaishoDao;
	}
	/**
	 * 表示対象情報Dao設定処理
	 * 
	 * @param CodHyojiTaishoDao 表示対象情報 
	 */
	public void setCodHyojiTaishoDao(CodHyojiTaishoDao HyojiTaishoDao) {
		this.codHyojiTaishoDao = HyojiTaishoDao;
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
}
