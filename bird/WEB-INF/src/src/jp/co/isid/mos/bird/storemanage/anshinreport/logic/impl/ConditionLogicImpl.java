package jp.co.isid.mos.bird.storemanage.anshinreport.logic.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.common.dao.CodCompanyDao;
import jp.co.isid.mos.bird.common.entity.CodCompany;
import jp.co.isid.mos.bird.framework.control.BirdContext;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.storemanage.anshinreport.code.TaishoJoken;
import jp.co.isid.mos.bird.storemanage.anshinreport.dto.AnshinReportDto;
import jp.co.isid.mos.bird.storemanage.anshinreport.logic.ChangeCompanyLogic;
import jp.co.isid.mos.bird.storemanage.anshinreport.logic.ConditionLogic;

/**
 * あんしん点検結果報告
 * 条件画面出力データ検索ロジック
 * 
 * @author kawashima
 */
public class ConditionLogicImpl implements ConditionLogic {
    public static final String LOGIC_ID =    "BSM018L01";
    /*【DAO】会社コード*/
    private CodCompanyDao codCompanyDao;

    private ChangeCompanyLogic changeCompanyLogic;
    

    /**
     * 事前条件処理
     * 
     * @param dto
     * @throws Exception
     */
    private void validate(AnshinReportDto dto) throws Exception {
        // 画面DTO
        if (dto == null) {
            throw new NotNullException("あんしん点検結果報告 画面DTO");
        }
    }
    
    /**
     * 条件画面出力データ検索と検索結果のDTOへの設定を行う
     * 
     * 条件画面に必要な全てのデータを戻します。
     * 
     * @param AnshinReportDto dto 画面データ保持クラス
     * @param MstUser ユーザ情報
     * @param BirdDateInfo e-mossles日付情報
     * @exception Exception
     */
    public Map execute(AnshinReportDto dto, BirdUserInfo userInfo, BirdDateInfo dateInfo) throws Exception {
        //事前条件判断処理実行
        validate(dto);            
        /* ユーザ情報取得 */
        dto.setUserId(userInfo.getMstUser().getUser_id());
        dto.setUsertypeCd(userInfo.getMstUser().getUserTypeCd());

        //会社 情報格納処理
        settingCompany(dto);
        
        // 実施年度を取得
        settingTaishoNendo(dto);
        
        // 対象条件情報作成
        List taishoJokenList = TaishoJoken.getPullDownList();
        dto.setTaishoJokenList(taishoJokenList);
        // 対象条件設定
        if (UserType.isHonbu(dto.getUsertypeCd())) {
            dto.setTaishoJoken(TaishoJoken.CODE_MISE);
        } else if (UserType.isOner(dto.getUsertypeCd())) {
            dto.setTaishoJoken(TaishoJoken.CODE_ONER);
        } else if (UserType.isTenpo(dto.getUsertypeCd())) {
            dto.setTaishoJoken(TaishoJoken.CODE_MISE);
        }
        
        // 会社条件情報取得ロジック実行
        if (!UserType.isHonbu(dto.getUsertypeCd())) {
            getChangeCompanyLogic().execute(dto, userInfo,dateInfo);
        }
        
        
        //検索データを戻す。
        return null;
    }
    /**
     * 会社プルダウン情報DTO格納処理
     * 
     * Dao【会社情報】実行
     * パラメーター：下記の値を保持したMapを引数とする。
     *               「userId」ユーザータイプコード
     * @param dto
     * @throws Exception
     */
    private void settingCompany(AnshinReportDto dto) throws Exception {
        //ユーザーID
        String userId = dto.getUserId();
        // Dao【会社情報】実行
        List list = getCodCompanyDao().select(userId);
        
        if (list == null || list.size() == 0) {
            throw new NotExistException("会社情報");
        }
        String initCompanyCd = ((CodCompany)(list.get(0))).getCompanyCd();
        // 会社コードリスト設定
        dto.setCompanyList(list);
        // 会社コード設定
        dto.setCompanyCd(initCompanyCd);
        // 会社名称設定
        dto.setCompanyName(dto.getCompanyName(initCompanyCd));

    }
    
    /**
     * 実施年度設定処理
     * あんしん点検結果ディレクトリ下のディレクトリ名一覧を取得
     * @param dto
     * @throws Exception
     */
    private void settingTaishoNendo(AnshinReportDto dto) throws Exception {
        
        List taishoNendo = new ArrayList();
        List taishoNendoStr = new ArrayList();
        
        String filePth = BirdContext.getProperty("fileProperty", "filePathAnshin");
        File dir = new File(filePth);
        
        File[] files = dir.listFiles();
        
        if (files == null || files.length == 0) {
            throw new NotExistException("実施年度");
        }
        
        File file;
        for (int i=0; i < files.length;i++) {
            file = files[i];
            // ディレクトリのみ抽出
            if (file.isDirectory()) {
                taishoNendoStr.add(file.getName());
            }
        }
        
        if (taishoNendoStr == null || taishoNendoStr.size() == 0) {
            throw new NotExistException("実施年度");
        } else {
            
            // ソート処理(降順)
            Comparator sortComparator = new Comparator() {
                public boolean equals(Object obj) {
                    return (super.equals(obj));
                }
                public int compare(Object obj1, Object obj2) {
                    
                    return String.valueOf(obj2).compareTo(String.valueOf(obj1));
                }
            };
            Collections.sort(taishoNendoStr, sortComparator);
            
            // 実施年度を設定
            dto.setJissiNendo((String)taishoNendoStr.get(0));
            
            SelectItem taishoSelect;
            for (int j=0; j < taishoNendoStr.size(); j++) {
                taishoSelect = new SelectItem(taishoNendoStr.get(j));
                taishoNendo.add(taishoSelect);
            }
        }
        
        dto.setTaishoNendo(taishoNendo);
        
    }

    /**
     * 
     * @return codCompanyDao を戻します。
     */
    public CodCompanyDao getCodCompanyDao() {
        return codCompanyDao;
    }

    /**
     * 
     * @param codCompanyDao を設定します。
     */
    public void setCodCompanyDao(CodCompanyDao codCompanyDao) {
        this.codCompanyDao = codCompanyDao;
    }

    /**
     * 
     * @return changeCompanyLogic を戻します。
     */
    public ChangeCompanyLogic getChangeCompanyLogic() {
        return changeCompanyLogic;
    }

    /**
     * 
     * @param changeCompanyLogic を設定します。
     */
    public void setChangeCompanyLogic(ChangeCompanyLogic changeCompanyLogic) {
        this.changeCompanyLogic = changeCompanyLogic;
    }
}
