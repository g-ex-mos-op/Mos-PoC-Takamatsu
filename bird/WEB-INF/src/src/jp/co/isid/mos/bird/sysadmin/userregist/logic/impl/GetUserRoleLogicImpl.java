/*
 * 作成日: 2007/02/01
 */
package jp.co.isid.mos.bird.sysadmin.userregist.logic.impl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.sysadmin.userregist.dao.CodRoleTypeDao;
import jp.co.isid.mos.bird.sysadmin.userregist.entity.CodRoleType;
import jp.co.isid.mos.bird.sysadmin.userregist.logic.GetUserRoleLogic;

/**
 * ロール取得ロジック
 * @author xamaruyama
 */
public class GetUserRoleLogicImpl implements GetUserRoleLogic {

    /** ロジックＩＤ*/ 
    public static final String LOGIC_ID = "BSA006L04";

    /** Dao【契約タイプロール変換取得】 */
    private CodRoleTypeDao userRegistCodRoleTypeDao;
    
    /** String 契約タイプが店舗の場合*/
    private static final String keiyaku_Tenpo = "03";
    
    /** 
     * パラメーターKey：企業コードリスト
     */
    public static final String PK_ARR_COMPANY_CD = "arrCompanyCd";
    
    /** 
     * パラメーターKey：契約タイプ 
     */
    public static final String PK_KEIYAKU_CHOICE = "keiyakuChoice";
    
    /** 
     * パラメーターKey：オプション選択 
     */
    public static final String PK_ARR_OPTION = "arrOption";
    
    /** 
     * パラメーターKey：システム日付 
     */
    public static final String PK_SYSDATE = "sysDate";
    
    /** 
     * パラメーターKey：店舗コード 
     */
    public static final String PK_MISE_CD = "miseCd";
    
	/**
     * ロール取得処理
     */
    public List execute(Map roleParam) {
     
        /** List ロール格納用リスト*/
        List roleList = new ArrayList();
        List listOpt = new ArrayList();
//        List miseKbnRole = new ArrayList();
        
        // Mapから値を取得して変数に代入する。
        List arrCompanyCd = (List)roleParam.get(PK_ARR_COMPANY_CD);
        String keiyakuChoice = (String)roleParam.get(PK_KEIYAKU_CHOICE);
        List arrOption = (List)roleParam.get(PK_ARR_OPTION);
        String sysDate = (String)roleParam.get(PK_SYSDATE);
        String miseCd = (String)roleParam.get(PK_MISE_CD);
        
        if (arrOption != null && arrOption.size() == 0) {
            arrOption = null;            
        }        
        
        // 数値妥当性チェック（arrOptionは選択しなくても良いため、validateしない）        
        validate(arrCompanyCd, keiyakuChoice, sysDate);
        
        // 契約タイプが店舗(03)の場合
        if (keiyaku_Tenpo.equals(keiyakuChoice)) {
            //---2009/03/10 update start 店舗ユーザーは、店区分からのみロールを設定
            roleList = getUserRegistCodRoleTypeDao().getMiseKbnRole(arrCompanyCd, sysDate, miseCd);
//            // 店区分のロール取得
//            miseKbnRole = getCodRoleTypeDao().getMiseKbnRole(arrCompanyCd, sysDate, miseCd);
//            
//            // 契約タイプのロールとオプション選択のロールが重複している場合、重複分を削除する。
//            for (int i = 0; i < miseKbnRole.size(); i++) {
//                CodRoleType entityOpt = (CodRoleType)miseKbnRole.get(i);
//                String roleCodeOpt = entityOpt.getRoleCd();
//                boolean isDuplicate = false;
//                for (int j = 0; j < roleList.size(); j++) {
//                    CodRoleType entity = (CodRoleType)roleList.get(j);
//                    String roleCode = entity.getRoleCd();
//                    if (roleCodeOpt.equals(roleCode)) {
//                        isDuplicate = true;
//                        break;
//                    }
//                }
//                if(!isDuplicate) {
//                    roleList.add(entityOpt);
//                }
//            }
            //---2009/03/10 update end
            // 契約タイプが店舗以外(01,02,04)の場合
        } else {
            // 契約タイプのロール取得
            roleList = getUserRegistCodRoleTypeDao().getKeiyakuRole(arrCompanyCd, keiyakuChoice);
        
            // arrOptionにデータが存在する場合、オプションタイプのロールを取得する。
            if (arrOption != null) {
                listOpt = getUserRegistCodRoleTypeDao().getOptionRole(arrCompanyCd, arrOption);
                
                // 契約タイプのロールとオプション選択のロールが重複している場合、重複分を削除する。
                for (int i = 0; i < listOpt.size(); i++) {
                    CodRoleType entityOpt = (CodRoleType)listOpt.get(i);
                    String roleCodeOpt = entityOpt.getRoleCd();
                    boolean isDuplicate = false;
                    for (int j = 0; j < roleList.size(); j++) {
                        CodRoleType entity = (CodRoleType)roleList.get(j);
                        String roleCode = entity.getRoleCd();
                        if (roleCodeOpt.equals(roleCode)) {
                            isDuplicate = true;
                            break;
                        }
                    }
                    if(!isDuplicate) {
                        roleList.add(entityOpt);
                    }
                }
            }
        }
        return roleList;
    }
    
    public void validate (List arrCompanyCd, String keiyakuChoice, String sysDate) {

        if (arrCompanyCd == null || arrCompanyCd.size() <= 0) {
            throw new NotExistException("企業コード");
        }
        if (keiyakuChoice == null || "".equals(keiyakuChoice)) {
            throw new NotExistException("契約タイプ");
        }
        //契約タイプが店舗（"03"）で、なおかつシステム日付がnullまたは空欄の場合に例外を発生させる。
        //（契約タイプが店舗の場合のみシステム日付が必要なため）
        if (keiyakuChoice.equals(keiyaku_Tenpo) && (sysDate == null || "".equals(sysDate))) {
            throw new MissingDataException("システム日付");
        }
    }

    public CodRoleTypeDao getUserRegistCodRoleTypeDao() {
        return userRegistCodRoleTypeDao;
    }

    public void setUserRegistCodRoleTypeDao(CodRoleTypeDao codRoleTypeDao) {
        this.userRegistCodRoleTypeDao = codRoleTypeDao;
    }
}