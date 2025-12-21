/*
 * 作成日:2012/08/09
 */
package jp.co.isid.mos.bird.bizreport.urimaintenanceview.dto;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;

/**
 * 売上修正確認明細・条件DTOクラス
 * 
 * @author xkawa
 */
public class UriMainteViewMeisaiJokenDto extends MeisaiRequestDto {
    
    /** 遷移時アプリエラー */
    private ApplicationException appEx;

    /**
     * 検索フラグ
     */
    private boolean searchFlg;
    
    ////////////セッター・ゲッター//////////////
    /**
     * 遷移時アプリエラーを戻します。
     * @return appEx 遷移時アプリエラー
     */
    public ApplicationException getAppEx() {
        return appEx;
    }
    /**
     * 遷移時アプリエラーを設定します。
     * @param appEx 遷移時アプリエラー
     */
    public void setAppEx(ApplicationException appEx) {
        this.appEx = appEx;
    }
    /**
     * 検索フラグを戻します。
     * @return searchFlg 検索フラグ
     */
    public boolean isSearchFlg() {
        return searchFlg;
    }
    /**
     * 検索フラグを設定します。
     * @param searchFlg 検索フラグ
     */
    public void setSearchFlg(boolean searchFlg) {
        this.searchFlg = searchFlg;
    }
}
