package jp.co.isid.mos.bird.config.scheduleregist.dto;

import java.util.List;

import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;

/**
 * スケジュール登録 セッションDTO
 * @author xnkusama
 *
 */
public class ScheduleRegistSessionDto {

    /*共通情報*/
    //ログインユーザー情報
    private BirdUserInfo birdUserInfo;
    //日付情報
    private BirdDateInfo birdDateInfo;
    
    /*検索条件画面 情報*/
    //会社プルダウンList
    private List listCondCompany;
    //対象週プルダウンList
    private List listCondTargetWeek;
    
    /*検索条件*/
    //会社
    private String condCompany;
    //会社名称
    private String condCompanyName;
    //対象週
    private String condTargetWeek;
    
    /*編集画面情報*/
    //ステータスプルダウンList
    private List listStatus;
    //日付タブList
    private List listDateTable;
    
    /*スケジュール情報*/
    private List listSchedule;
    
    /*画面制御情報*/
    //セッションキー
    private String sessionKey;
    //アクティブタブ日付
    private String activeTabDate;

    
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
    public List getListCondCompany() {
        return listCondCompany;
    }
    public void setListCondCompany(List listCondCompany) {
        this.listCondCompany = listCondCompany;
    }
    public List getListCondTargetWeek() {
        return listCondTargetWeek;
    }
    public void setListCondTargetWeek(List listCondTargetWeek) {
        this.listCondTargetWeek = listCondTargetWeek;
    }
    public List getListStatus() {
        return listStatus;
    }
    public void setListStatus(List listStatus) {
        this.listStatus = listStatus;
    }
    public List getListDateTable() {
        return listDateTable;
    }
    public void setListDateTable(List listDateTabl) {
        this.listDateTable = listDateTabl;
    }
    public String getCondCompany() {
        return condCompany;
    }
    public void setCondCompany(String condCompany) {
        this.condCompany = condCompany;
    }
    public String getCondTargetWeek() {
        return condTargetWeek;
    }
    public void setCondTargetWeek(String condTargetWeek) {
        this.condTargetWeek = condTargetWeek;
    }
    public List getListSchedule() {
        return listSchedule;
    }
    public void setListSchedule(List listSchedule) {
        this.listSchedule = listSchedule;
    }
    public String getSessionKey() {
        return sessionKey;
    }
    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }
    public String getCondCompanyName() {
        return condCompanyName;
    }
    public void setCondCompanyName(String condCompanyName) {
        this.condCompanyName = condCompanyName;
    }
    public String getActiveTabDate() {
        return activeTabDate;
    }
    public void setActiveTabDate(String activeTabDate) {
        this.activeTabDate = activeTabDate;
    }
}
