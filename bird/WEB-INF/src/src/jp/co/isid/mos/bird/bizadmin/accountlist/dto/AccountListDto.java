package jp.co.isid.mos.bird.bizadmin.accountlist.dto;

import java.util.List;

import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;

/**
 * お申込内容一覧DTO
 * @author xnkusama
 *
 */
public class AccountListDto {

    //ユーザー情報
    private BirdUserInfo birdUserInfo;
    //日付情報
    private BirdDateInfo birdDateInfo;
    
    //申込内容一覧
    private List listAccount;
    
    /*検索ヘッダ*/
    private String headerAppliedDt;
    private String headerOnerName;
    private String headerRepresentativeMise;
    
    /*ログインユーザー情報*/
    //契約タイプ
    private String userKeiyakuType;
    
    /*アカウント変更画面 遷移情報*/
    //編集ユーザーID
    private String editUserId;
    //変更リンク表示フラグ
    private boolean enableEditLink = false;
    
    public BirdUserInfo getBirdUserInfo() {
        return birdUserInfo;
    }
    public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
        this.birdUserInfo = birdUserInfo;
    }
    public String getHeaderAppliedDt() {
        return headerAppliedDt;
    }
    public void setHeaderAppliedDt(String headerAppliedDt) {
        this.headerAppliedDt = headerAppliedDt;
    }
    public String getHeaderOnerName() {
        return headerOnerName;
    }
    public void setHeaderOnerName(String headerOnerName) {
        this.headerOnerName = headerOnerName;
    }
    public List getListAccount() {
        return listAccount;
    }
    public void setListAccount(List listAccount) {
        this.listAccount = listAccount;
    }
    public String getHeaderRepresentativeMise() {
        return headerRepresentativeMise;
    }
    public void setHeaderRepresentativeMise(String representativeMise) {
        this.headerRepresentativeMise = representativeMise;
    }
    public BirdDateInfo getBirdDateInfo() {
        return birdDateInfo;
    }
    public void setBirdDateInfo(BirdDateInfo birdDateInfo) {
        this.birdDateInfo = birdDateInfo;
    }
    public String getUserKeiyakuType() {
        return userKeiyakuType;
    }
    public void setUserKeiyakuType(String userKeiyakuType) {
        this.userKeiyakuType = userKeiyakuType;
    }
    public String getEditUserId() {
        return editUserId;
    }
    public void setEditUserId(String editUserId) {
        this.editUserId = editUserId;
    }
    public boolean isEnableEditLink() {
        return enableEditLink;
    }
    public void setEnableEditLink(boolean enableEditLink) {
        this.enableEditLink = enableEditLink;
    }
    
}
