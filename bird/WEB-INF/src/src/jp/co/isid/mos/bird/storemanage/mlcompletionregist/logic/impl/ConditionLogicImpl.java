package jp.co.isid.mos.bird.storemanage.mlcompletionregist.logic.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.common.logic.GetSibuTorikomiLogic;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.storemanage.mlcompletionregist.action.MlCompletionregistAction;
import jp.co.isid.mos.bird.storemanage.mlcompletionregist.dao.CodCompanyDao;
import jp.co.isid.mos.bird.storemanage.mlcompletionregist.dao.UIKenshuInfoDao;
import jp.co.isid.mos.bird.storemanage.mlcompletionregist.dto.MlCompletionregistDto;
import jp.co.isid.mos.bird.storemanage.mlcompletionregist.entity.CodCompany;
import jp.co.isid.mos.bird.storemanage.mlcompletionregist.logic.MlCompletionregistLogic;

/**
 * マスタライセンス研修修了登録
 * 条件画面出力データ検索ロジック
 * 
 * @author xkinu
 */
public class ConditionLogicImpl implements MlCompletionregistLogic {
    
    /* ロジックID */    
    public static final String LOGIC_ID = "BSM006L02";
    
    /*【ロジック】条件画面会社プルダウン変更時出力支部データ検索ロジック */
    private SibuListLogicImpl sibuListLogic;
    /*【ロジック】支部取込コードによる支部情報取得ロジック */
    private GetSibuTorikomiLogic sibuTorikomiLogic;


    /*【DAO】会社コード*/
    private CodCompanyDao codCompanyDao;
    /*【DAO】研修プルダウンリストデータ*/
    private UIKenshuInfoDao uiKenshuInfoDao;
    /**
     * 事前条件処理
     * 
     * @param dto
     * @throws ApplicationException
     */
    public void validate(MlCompletionregistDto dto) throws ApplicationException {
        // 画面DTO
        if (dto == null) {
            throw new NotNullException("マスタライセンス研修修了登録 画面DTO");
        }
        // ユーザーID
        String userId = dto.getUserId();
        if (isNull(userId)) {
            throw new NotNullException("ユーザーID");
        }
        //システム日付
        String toDay = dto.getToday();
        if (isNull(toDay)) {
            throw new NotNullException("システム日付");
        }
        //ユーザータイプコード
        String usertypeCd = dto.getUsertypeCd();
        if (isNull(usertypeCd)) {
            throw new NotNullException("ユーザータイプコード");
        }
    }
    
    /**
     * 条件画面出力データ検索を行う
     * 
     * 条件画面に必要な全てのデータを戻します。
     * 
     * @param MlCompletionregistDto dto 画面データ保持クラス
     * @exception ApplicationException
     */
    public Map execute(MlCompletionregistDto dto) throws ApplicationException {      
        Map returnMap = new HashMap();
        
        String userId = dto.getUserId();

        // 研修選択タイトルデータ取得
        returnMap.put("mapEntryTitle", executeEntryTitle(dto));

        // 会社データ取得
        List listCompany = executeListCompany(userId);
        returnMap.put("listCompany", listCompany);
        dto.setCompanyList(listCompany);

        // 会社コード設定
        String companyCd = ((CodCompany)listCompany.get(0)).getCompanyCd();
        dto.setCompanyCd(companyCd);

        //--------------------
        // 支部データ取得
        //--------------------
        //Map logigMap = getSibuListLogic().execute(dto);
        //returnMap.put("listSibu", (List)logigMap.get("listSibu"));

        //boolean limit = dto.isLimit();  //制限区分

        //リスト取得
        List sibuTorikomiList = sibuTorikomiLogic.execute(companyCd, userId, dto.isLimit());
// 2007/02/05 delete start        
//        if (sibuTorikomiList == null || sibuTorikomiList.size() == 0) {
//            throw new NotExistException("支部情報");
//        }
// delete end        

        //返却マップに格納
        returnMap.put("listSibu", sibuTorikomiList);


        //検索データを戻す。
        return returnMap;
    }
    /**
     * 研修選択タイトルデータ取得処理
     * 
     * @param MlCompletionregistDto dto 画面データ保持クラス
     * @return List  
     * @exception ApplicationException
     */
    private Map executeEntryTitle(MlCompletionregistDto dto) throws ApplicationException {
        String toDay = dto.getToday();
        String usertypeCd = dto.getUsertypeCd();
       // リターンMap
        Map map = new HashMap();
    	//ベーシック研修プルダウンリストデータ取得
        map.put(MlCompletionregistAction.ENTRY_CD_BASIC
        		, getKenshuInfoDao().select(MlCompletionregistAction.ENTRY_CD_BASIC, toDay, usertypeCd));
    	//出張研修プルダウンリストデータ取得
        map.put(MlCompletionregistAction.ENTRY_CD_TRIP
        		, getKenshuInfoDao().select(MlCompletionregistAction.ENTRY_CD_TRIP, toDay, usertypeCd));
    	//更新研修プルダウンリストデータ取得
        map.put(MlCompletionregistAction.ENTRY_CD_RENEWAL
        		, getKenshuInfoDao().select(MlCompletionregistAction.ENTRY_CD_RENEWAL, toDay, usertypeCd));
    	return map;
    }    
    /**
     * 会社データ取得処理
     * 
     * @param String userId ユーザーID
     * @return List  
     * @exception ApplicationException
     */
    private List executeListCompany(String userId) throws ApplicationException {
        List listCompany = getCompanyDao().select(userId);
        if (listCompany == null || listCompany.size() == 0) {
            throw new NotExistException("会社情報");
        }
        return listCompany;
    }

    /**
     * 条件画面会社プルダウン変更時出力支部データ検索ロジック取得処理
     * @return companyJohoLogic を戻します。
     */
    public SibuListLogicImpl getSibuListLogic() {
        return sibuListLogic;
    }
    /**
     * 条件画面会社プルダウン変更時出力支部データ検索ロジック設定処理
     * @param conditionLogic を設定。
     */
    public void setSibuListLogic(SibuListLogicImpl sibuListLogic) {
        this.sibuListLogic = sibuListLogic;
    }
   /**
     * ベーシック研修プルダウンリストデータ取得
     * 
     * @return UIKenshuInfoDao を戻します。
     */
    public UIKenshuInfoDao getKenshuInfoDao() {
        return this.uiKenshuInfoDao;
    }
    /**
     * ベーシック研修プルダウンリストデータ設定
     * 
     * @param dao UIKenshuInfoDao を設定。
     */
    public void setKenshuInfoDao(UIKenshuInfoDao dao) {
        this.uiKenshuInfoDao = dao;
    }
   /**
     * @return codCompanyDao を戻します。
     */
    public CodCompanyDao getCompanyDao() {
        return codCompanyDao;
    }
    /**
     * @param codCompanyDao を設定。
     */
    public void setCompanyDao(CodCompanyDao codCompanyDao) {
        this.codCompanyDao = codCompanyDao;
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
     * @return sibuTorikomiLogic を戻します。
     */
    public GetSibuTorikomiLogic getSibuTorikomiLogic() {
        return sibuTorikomiLogic;
    }

    /**
     * @param sibuTorikomiLogic 設定する sibuTorikomiLogic。
     */
    public void setSibuTorikomiLogic(GetSibuTorikomiLogic sibuTorikomiLogic) {
        this.sibuTorikomiLogic = sibuTorikomiLogic;
    }
}
