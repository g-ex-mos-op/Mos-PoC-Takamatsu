package jp.co.isid.mos.bird.config.summenuregist.dto;

import java.util.List;

import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;

/**
 * 店舗メーター管理状況メンテナンスDTO
 * @author xnkusam
 *
 */
public class SumMenuRegistDto implements CsvOutputDto {

    //システム日付
    private String sysDate;
    /*ユーザー情報*/
    private String userId;
    //ユーザータイプコード
    private String userTypeCd;

    /**登録情報*/
    //集約メニューコード
    private String sumMenuCd;
    //集約メニュー名称
    private String sumMenuName;
    
    /**データ*/
    //集約メニュー一覧
    private List listSumMenu;
    //子メニュー一覧
    private List listKoMenu;
    //集約設定一覧CSV用
    private List listCsvData;
    
    //子メニュー追加 エラーメッセージ
    private String koMenuAddErrMsg;
    
    /**セッションキー*/
    private String sessionKey;

    public List getListSumMenu() {
        return listSumMenu;
    }

    public void setListSumMenu(List listSumMenu) {
        this.listSumMenu = listSumMenu;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getSysDate() {
        return sysDate;
    }

    public void setSysDate(String sysDate) {
        this.sysDate = sysDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserTypeCd() {
        return userTypeCd;
    }

    public void setUserTypeCd(String userTypeCd) {
        this.userTypeCd = userTypeCd;
    }

    public List getListKoMenu() {
        return listKoMenu;
    }

    public void setListKoMenu(List listKoMenu) {
        this.listKoMenu = listKoMenu;
    }

    public String getSumMenuCd() {
        return sumMenuCd;
    }

    public void setSumMenuCd(String sumMenuCd) {
        this.sumMenuCd = sumMenuCd;
    }

    public String getSumMenuName() {
        return sumMenuName;
    }

    public void setSumMenuName(String sumMenuName) {
        this.sumMenuName = sumMenuName;
    }

    public List getListCsvData() {
        return listCsvData;
    }

    public void setListCsvData(List listCsvData) {
        this.listCsvData = listCsvData;
    }

    public String getKoMenuAddErrMsg() {
        return koMenuAddErrMsg;
    }

    public void setKoMenuAddErrMsg(String koMenuAddErrMsg) {
        this.koMenuAddErrMsg = koMenuAddErrMsg;
    }

}
