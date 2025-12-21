package jp.co.isid.mos.bird.bizsupport.budgetregist.dto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.bizsupport.common.entity.CodCompany;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;

import org.apache.myfaces.custom.fileupload.UploadedFile;

/**
 * 予算CSV取込DTO
 * 
 * @author Aspac
 */
public class BudgetRegistDto implements CsvOutputDto {

    /**
     * 実行モード
     * ※ 0: テンプレートダウンロード
     * ※ 1: 予算登録
     * ※ 2: 下期予算クリア
     */
    private int executeMode;
    
    
    /**
     * Viewモード
     * ※ 0: テンプレートダウンロード・予算登録表示(下期予算クリア非表示)
     * ※ 1: 全表示
     */
    private int viewMode;
    
    
    /**
     * エラーフラグ
     * ※登録ファイルCSVのエラー有無を判断する
     */
    private boolean errorFlg = false;
    
    
    /**
     * 下期予算クリア許可フラグ
     * ※下期予算クリアが実行可能な月(９月)かを判断する。
     */
    private boolean clearAllowFlg = false;
    
    /**
     * 予算登録CSVファイル
     */
    private UploadedFile uploadedFile;
    
    
    
    /**
     * エラー情報
     * ※エラー情報を埋め込んだCSVデータを保持
     */
    private List listErrorInfo;
    
    
    /**
     * 予算登録情報
     * ※予算登録CSVファイルから登録データを取得・保持
     */
    private List listBudget;
    
    
    /**
     * ユーザID
     */
    private String userId;
    
    
    /************************/
    /*** 予算登録初期表示 ***/
    /************************/
    
    
    /**
     * 会社リスト
     */
    private List listCompany;
    
    /**
     * 会社コード
     */
    private String condCompanyCd;
    
    /**
     * 会社名称
     */
    private String companyName;
    
    /**
     * 対象年度リスト
     */
    private List listYear;
    
    /**
     * 対象年度
     */
    private String condYear;
    
    
    
    /*********************/
    /***** 日付各種　*****/
    /*********************/
    
    
    /**
     * システム日付(yyyyMMdd)
     */
    private String sysdate;
    
    /**
     * システム日付(yyyyMM)
     */
    private String sysDateYearMonth;
    
    /**
     * 本年度(yyyy)
     */
    private String sysNendo;
    
    /**
     * システム日時
     */
    private Timestamp currentTimestamp;
   
    /**
     * 過去月変更フラグ
     */
    private boolean changeKako;
    
    /**
     * 下期予算クリア開始月
     */
    private String simokiClearFromMonth;

    /**
     * ユーザー情報
     */
    private BirdUserInfo birdUserInfo;
    /**
     * 日付情報
     */
    private BirdDateInfo birdDateInfo;
    
    /**
     * システム年度を取得します。
     * @return
     */
    public String getSysNendo() {
        return sysNendo;
    }
    /**
     * システム年度を設定します。
     * @param sysNendo
     */
    public void setSysNendo(String sysNendo) {
        this.sysNendo = sysNendo;
    }

    
    /**
     * CSVファイルを取得します。
     * @return CSVファイル
     */
    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }
    /**
     * CSVファイルを設定します。
     * @param uploadedFile CSVファイル
     */
    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }


    /**
     * 実行モードを取得します。
     * @return
     */
    public int getExecuteMode() {
        return executeMode;
    }
    /**
     * 実行モードを設定します。
     */
    public void setExecuteMode(int executeMode) {
        this.executeMode = executeMode;
    }

    
    /**
     * エラーフラグを取得します。
     * @return
     */
    public boolean isErrorFlg() {
        return errorFlg;
    }
    /**
     * エラーフラグを設定します。
     * @param errorFlg
     */
    public void setErrorFlg(boolean errorFlg) {
        this.errorFlg = errorFlg;
    }

    
    /**
     * 企業コードを取得します。
     * @return
     */
    public String getCondCompanyCd() {
        return condCompanyCd;
    }
    /**
     * 企業コードを設定します。
     * @param condCompanyCd
     */
    public void setCondCompanyCd(String condCompanyCd) {
        this.condCompanyCd = condCompanyCd;
    }

    
    /**
     * 企業リストを取得します。
     * @return
     */
    public List getListCompany() {
        return listCompany;
    }
    /**
     * 企業リストを設定します。
     * @param listCompany
     */
    public void setListCompany(List listCompany) {
        this.listCompany = listCompany;
    }

    
    /**
     * 年度リストを取得します。
     * @return
     */
    public List getListYear() {
        return listYear;
    }
    /**
     * 年度リストを設定します。
     * @param listYear
     */
    public void setListYear(List listYear) {
        this.listYear = listYear;
    }
    

    /**
     * Viewモードを取得します。
     * @return
     */
    public int getViewMode() {
        return viewMode;
    }
    /**
     * Viewモードを設定します。
     * @param viewMode
     */
    public void setViewMode(int viewMode) {
        this.viewMode = viewMode;
    }

    
    /**
     * 年度を取得します。
     * @return
     */
    public String getCondYear() {
        return condYear;
    }
    /**
     * 年度を設定します。
     */
    public void setCondYear(String condYear) {
        this.condYear = condYear;
    }

    
    /**
     * 予算クリア許可フラグを取得します。
     * @return
     */
    public boolean isClearAllowFlg() {
        return clearAllowFlg;
    }
    /**
     * 予算クリア許可フラグを設定します。
     * @param clearAllowFlg
     */
    public void setClearAllowFlg(boolean clearAllowFlg) {
        this.clearAllowFlg = clearAllowFlg;
    }
    
    
    /**
     * システム日付(yyyyMM)を取得します。
     * @return
     */
    public String getSysDateYearMonth() {
        return sysDateYearMonth;
    }
    /**
     * システム日付(yyyyMM)を設定します。
     */
    public void setSysDateYearMonth(String sysDateYearMonth) {
        this.sysDateYearMonth = sysDateYearMonth;
    }

    
    /**
     * 予算更新リストを取得します。
     * @return
     */
    public List getListBudget() {
        return listBudget;
    }
    /**
     * 予算更新リストを設定します。
     * @return
     */
    public void setListBudget(List listBudget) {
        this.listBudget = listBudget;
    }

    
    /**
     * エラー情報を取得します。
     * @return
     */
    public List getListErrorInfo() {
        return listErrorInfo;
    }
    /**
     * エラー情報を設定します。
     * @return
     */
    public void setListErrorInfo(List listErrorInfo) {
        this.listErrorInfo = listErrorInfo;
    }

    
    /**
     * 企業名称を取得します。
     * @return
     */
    public String getCompanyName() {
        
        List lstComp = getListCompany();
        String compCd = getCondCompanyCd();
        for (Iterator ite = lstComp.iterator(); ite.hasNext();) {
            CodCompany compInfo = (CodCompany) ite.next();
            if(compInfo.getCompanyCd().equals(compCd)){
                setCompanyName(compInfo.getCompanyName());
            }
        }
        
        return companyName;
    }
    /**
     * 企業名称を設定します。
     * @return
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    
    /**
     * タイムスタンプを取得します。
     * @return
     */
    public Timestamp getCurrentTimestamp() {
        return currentTimestamp;
    }
    /**
     * タイムスタンプを設定します。
     * @return
     */
    public void setCurrentTimestamp(Timestamp currentTimestamp) {
        this.currentTimestamp = currentTimestamp;
    }

    
    /**
     * システム日付を取得します。
     * @return
     */
    public String getSysdate() {
        return sysdate;
    }
    /**
     * システム日付を設定します。
     * @return
     */
    public void setSysdate(String sysdate) {
        this.sysdate = sysdate;
    }

    
    /**
     * ユーザIDを取得します。
     * @return
     */
    public String getUserId() {
        return userId;
    }
    /**
     * ユーザIDを設定します。
     * @return
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    
    /**
     * エラーフラグ、エラー情報、予算登録情報をクリアします。
     */
    public void doClear(){
        setErrorFlg(false);
        setListErrorInfo(null);
        setListBudget(null);
    }
    public boolean isChangeKako() {
        return changeKako;
    }
    public void setChangeKako(boolean changeKako) {
        this.changeKako = changeKako;
    }
    public String getSimokiClearFromMonth() {
        return simokiClearFromMonth;
    }
    public void setSimokiClearFromMonth(String simokiClearFromMonth) {
        this.simokiClearFromMonth = simokiClearFromMonth;
    }
    /**
     * 下期予算クリア 期間Fromプルダウン用List作成
     * システム日付前月から年度末（3月）までのリストを作成する
     * @return
     */
    public List getSimokiClearMonthList() {
        List listMonth = new ArrayList();
        DateFormatter formatter = new DateFormatter(DateFormatter.DATE_TYPE_YM, DateFormatter.PATTERN_MONTH_KANJI_YM);
        String dateYM = "";
        for (int i = -1; i <= 6; i++) {
            try {
                dateYM = DateManager.getNextMonth(getSysdate().substring(0, 6), i);
                if (dateYM.compareTo(getSysNendoNextYear() + "04") >= 0) {
                    break;
                }
                listMonth.add(new SelectItem(dateYM, formatter.format(dateYM, true)));
            }
            catch (Exception ex) {
                throw new FtlSystemException("日付", "", ex);
            }
        }
        
        return listMonth;
    }
    /**
     * システム日付の翌年度
     * @return String
     */
    public String getSysNendoNextYear() {
        String nextYear = "";
        try {
            nextYear = DateManager.getNextYear(getSysNendo(), 1);
        }
        catch (Exception ex) {
            throw new FtlSystemException("システム日付の翌年度取得", "", ex);
        }
        return nextYear;
    }
    public BirdDateInfo getBirdDateInfo() {
        return birdDateInfo;
    }
    public void setBirdDateInfo(BirdDateInfo birdDateInfo) {
        this.birdDateInfo = birdDateInfo;
    }
    public BirdUserInfo getBirdUserInfo() {
        return birdUserInfo;
    }
    public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
        this.birdUserInfo = birdUserInfo;
    }
}
