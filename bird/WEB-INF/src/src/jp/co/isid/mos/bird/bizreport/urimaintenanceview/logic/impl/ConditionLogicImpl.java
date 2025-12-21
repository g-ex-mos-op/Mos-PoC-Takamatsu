/*
 * 作成日:2012/08/10
 */
package jp.co.isid.mos.bird.bizreport.urimaintenanceview.logic.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.bizreport.urimaintenanceview.common.UriMainteViewConstants;
import jp.co.isid.mos.bird.bizreport.urimaintenanceview.dto.UriMainteViewSesDto;
import jp.co.isid.mos.bird.bizreport.urimaintenanceview.logic.ConditionLogic;
import jp.co.isid.mos.bird.common.entity.MstUserCompany;
import jp.co.isid.mos.bird.common.kaikei.dao.CtlKaikeiKbnInfoDao;
import jp.co.isid.mos.bird.common.kaikei.dao.CtlSyukeiKbnDao;
import jp.co.isid.mos.bird.common.kaikei.entity.CtlSyukeiKbn;
import jp.co.isid.mos.bird.common.kaikei.util.KaikeiUtil;
import jp.co.isid.mos.bird.common.logic.GetUserCompanyLogic;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;

/**
 * 売上修正確認の条件部情報取得ロジック インターフェース
 *
 * @author xkawa
 */
public class ConditionLogicImpl implements ConditionLogic {

    /** ロジックID */
    public static final String LOGIC_ID = "BBR009L02";
    
    /* LOGIC */
    /** ユーザー所属会社取得ロジック */
    private GetUserCompanyLogic getUserCompanyLogic;

    /** 会計集計区分情報Dao */
    private CtlSyukeiKbnDao ctlSyukeiKbnDao;
    
    /** 会計区分情報取得ロジック */
    private CtlKaikeiKbnInfoDao ctlKaikeiKbnInfoDao;
    
    /**
     * 条件部情報を取得する
     * @param sessionDto 売上修正確認セッションDto
     * 更新日:2014/12/10 周春建　会計区分拡張対応
     */
    public void execute(UriMainteViewSesDto sessionDto) {

        //ロジック【ユーザー所属会社取得】実行。
        List companyList = getUserCompanyLogic.execute(sessionDto.getUserId(), null);
        if (companyList == null || companyList.isEmpty()) {
            // データが存在しない場合
            throw new NotExistException(UriMainteViewConstants.MSG_USER_COMPANY);
        }
        sessionDto.setCompanyList(companyList);            //会社リスト
        
        //修正日リスト作成
        List syuseiDateList = getSyuseiDateList(sessionDto.getSysDate());
        sessionDto.setSyuseDateList(syuseiDateList);       //修正日リスト

        // 集計区分情報取得(全情報)
        List tempSyukeiKbnList = getCtlSyukeiKbnDao().selectAll();

        // プルダウン用情報作成
        List syukeiKbnPullDownList = new ArrayList();
        for(int i = 2; i < 12; i++) {
            syukeiKbnPullDownList.add(getSyukeiKbn(i, tempSyukeiKbnList));
        }
        sessionDto.setSyukeiKbnList(syukeiKbnPullDownList);

        // 1.Dao【会計集計区分情報】．検索を実行し、戻り値List[[集計区分情報]]を取得する
        List syukeiKbnList = getCtlSyukeiKbnDao().select(UriMainteViewConstants.BIRD_DISP_FLG_ON);
        
        // 2.Dao【会計区分情報】．検索を実行し、戻り値List[[会計区分情報]]を取得する
        //List kaikeiKbnList = getCtlKaikeiKbnInfoDao().select(UriMainteViewConstants.BIRD_DISP_FLG_ON);
        //プルダウン先頭の会社情報を取得して設定
        MstUserCompany comp = (MstUserCompany)sessionDto.getCompanyList().get(0); 
        String companyCd   = comp.getCompanyCd();
        //会計区分マスタの抽出条件には会社コードも含める
        List kaikeiKbnList = getCtlKaikeiKbnInfoDao().select(UriMainteViewConstants.BIRD_DISP_FLG_ON,companyCd);
        
        // 3．変数．Map[集計区分別会計区分マスタ情報]を生成する
        Map kaikeiInfoMaster = KaikeiUtil.makeKaikeiSyukeiKbnInfo(syukeiKbnList, kaikeiKbnList);
        sessionDto.setKaikeiKbnMasterInfo(kaikeiInfoMaster);

    }


    /**
     * 日付リストを取得する
     * @param  date 日付
     * @return List 日付リスト
     */
    private List getSyuseiDateList(String date) {

        // 日付リスト
        List dayList = new ArrayList();
 
        //フォーマッター
        DateFormatter formatter = new DateFormatter();

        String appMonth = formatter.format
            (date, DateFormatter.PATTERN_MONTH, DateFormatter.DATE_TYPE_YMD);
 
        // 対象となる月を算出
        String fromMonth = "";
        try{
            fromMonth = DateManager.getPrevMonth(appMonth, 2);
        }catch(Exception ex) {
            throw new FtlSystemException("条件画面初期処理");
        }

        String day = "";
        int i = 0;      
        while( true ) {
            try {
                // 日付取得(YYYYMMDD)
                day = DateManager.getPrevDate(date,i);
            }catch ( Exception ex ) {
                throw new FtlSystemException("条件画面初期処理");
            }

            if ( day.substring(0,6).equals( fromMonth ) ) {
                break;
            }

            SelectItem item = item = new SelectItem(day, formatter.format
                (day, DateFormatter.PATTERN_SLASH,DateFormatter.DATE_TYPE_YMD));
            dayList.add( item );
 
            day = "";
            i++;
        }
        return dayList;
    }
    

    /**
     * 集計区分名称を設定する
     * @param num            番号
     * @param syukeiInfoList 集計区分名称リスト
     * @return 集計区分
     */
    private CtlSyukeiKbn getSyukeiKbn(int num, List syukeiInfoList) {
        CtlSyukeiKbn info = null;
        String srtNum = getStringNum(num, 2);
        if (syukeiInfoList != null && !syukeiInfoList.isEmpty()) {
            for (Iterator it = syukeiInfoList.iterator(); it.hasNext();) {
                info = (CtlSyukeiKbn)(it.next());
                if (srtNum.equals(info.getSyukeiCd())) {
                    info.setSyukeiName(info.getSyukeiName().trim());
                    return info;
                }
            }
        }
        
        // 存在しない場合は作成する
        info = new CtlSyukeiKbn();
        info.setSyukeiCd(srtNum);
        info.setSyukeiName("集計区分" + String.valueOf(num));
        return info;
    }

    /**
     * データ部・項目名称番号を文字列("00")に変換する
     * @param num  番号
     * @param size 桁数
     * @return String 項目名称番号
     */
    public static String getStringNum(int num, int size) {
        String strNum = String.valueOf(num);
        while (strNum.length() < size) {
            strNum = "0" + strNum;
        }
        return strNum;
    }

    public GetUserCompanyLogic getGetUserCompanyLogic() {
        return getUserCompanyLogic;
    }

    public void setGetUserCompanyLogic(GetUserCompanyLogic getUserCompanyLogic) {
        this.getUserCompanyLogic = getUserCompanyLogic;
    }

    /**
     * 会計集計区分情報Daoを取得する
     * @return 会計集計区分情報Dao
     */
    public CtlSyukeiKbnDao getCtlSyukeiKbnDao() {
        return ctlSyukeiKbnDao;
    }

    /**
     * 会計集計区分情報Daoを設定する
     * @param mstAccountKbnInfoDao 会計集計区分情報Dao
     */
    public void setCtlSyukeiKbnDao(CtlSyukeiKbnDao ctlSyukeiKbnDao) {
        this.ctlSyukeiKbnDao = ctlSyukeiKbnDao;
    }

    /**
     * 会計区分情報取得ロジックを戻します。
     * @return ctlKaikeiKbnInfoDao 会計区分情報取得ロジック
     */
    public CtlKaikeiKbnInfoDao getCtlKaikeiKbnInfoDao() {
        return ctlKaikeiKbnInfoDao;
    }

    /**
     * 会計区分情報取得ロジックを設定します。
     * @param ctlKaikeiKbnInfoDao 会計区分情報取得ロジック
     */
    public void setCtlKaikeiKbnInfoDao(CtlKaikeiKbnInfoDao ctlKaikeiKbnInfoDao) {
        this.ctlKaikeiKbnInfoDao = ctlKaikeiKbnInfoDao;
    }
}
