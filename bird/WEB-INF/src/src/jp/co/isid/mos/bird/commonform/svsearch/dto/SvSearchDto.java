/*
 * 作成日: 2006/08/22
 */
package jp.co.isid.mos.bird.commonform.svsearch.dto;

import java.util.List;

/**
 * SV情報保持用Dto<br>
 * 
 * 使用注意：SV検索を呼び出す際には、以下３点の設定が必要<br>
 *   1. 初期化 clear();<br>
 * 　2. 遷移元ページを設定(検索画面から元画面に戻る際のアクションの戻り値の設定)<br>
 * 　3. 検索初期処理起動フラグをtrueに設定
 * @author itamoto
 */
public class SvSearchDto {

    /* 遷移元ページ */
    private String navigationCase;
    /* 企業コード */
    private String companyCd;
    /* 支部コード */
    private String sibuCd;
    /* SVコード */
    private String svCd;
    /* SV名称 */
    private String svNameKj;
    /* SV名称（かな）*/
    private String svNameKna;
    /* 検索初期処理起動フラグ */
    private boolean initFlag;
    /* アクションフラグ */
    private boolean actionFlag;
    
    /* 企業コードリスト(検索用 会社リスト指定用) */
    private List rCompanyCdList;
    /* 支部コードリスト(検索用 支部リスト指定用) */
    private List sibuCdList;

    /**
     * 遷移元ページの設定
     * @return urlMapping を戻します。
     */
    public String getNavigationCase() {
        return navigationCase;
    }
    /**
     * 遷移元ページの設定
     * @param urlMapping urlMapping を設定。
     */
    public void setNavigationCase(String navigationCase) {
        this.navigationCase = navigationCase;
    }

    /**
     * 企業コード設定
     * @return companyCd を戻します。
     */
    public String getCompanyCd() {
        return companyCd;
    }
    /**
     * 企業コード設定
     * @param companyCd companyCd を設定。
     */
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }
    /**
     * 支部コード設定
     * @return sibuCd を戻します。
     */
    public String getSibuCd() {
        return sibuCd;
    }
    /**
     * 支部コード設定
     * @param sibuCd を設定。
     */
    public void setSibuCd(String sibuCd) {
        this.sibuCd = sibuCd;
    }
    /**
     * SVコード設定
     * @return svCd を戻します。
     */
    public String getSvCd() {
        return svCd;
    }
    /**
     * SVコード設定
     * @param svCd svCd を設定。
     */
    public void setSvCd(String svCd) {
        this.svCd = svCd;
    }
    /**
     * SV名称設定
     * @return svNameKj を戻します。
     */
    public String getSvNameKj() {
        return svNameKj;
    }
    /**
     * SV名称設定
     * @param svNameKj svNameKj を設定。
     */
    public void setSvNameKj(String svNameKj) {
        this.svNameKj = svNameKj;
    }
    /**
     * SV名称（カナ）設定
     * @return svNameKna を戻します。
     */
    public String getSvNameKna() {
        return svNameKna;
    }
    /**
     * SV名称（カナ）設定
     * @param svNameKna svNameKna を設定。
     */
    public void setSvNameKna(String svNameKna) {
        this.svNameKna = svNameKna;
    }
    /**
     * 検索初期処理起動フラグの設定
     * @return initFlag を戻します。
     */
    public boolean isInitFlag() {
        return initFlag;
    }
    /**
     * 検索初期処理起動フラグの設定
     * @param initFlag initFlag を設定。
     */
    public void setInitFlag(boolean initFlag) {
        this.initFlag = initFlag;
    }

    /**
     * アクションフラグの設定
     * @return actionFlag を戻します。
     */
    public boolean isActionFlag() {
        return actionFlag;
    }
    /**
     * アクションフラグの設定
     * @param actionFlag actionFlag を設定。
     */
    public void setActionFlag(boolean actionFlag) {
        this.actionFlag = actionFlag;
    }

    /**
     * 企業コードリストを取得します。
     * @return 企業コードリスト
     */
    public List getRCompanyCdList() {
        return rCompanyCdList;
    }
    /**
     * 企業コードリストを設定します。
     * @param rCompanyCdList 企業コードリスト
     */
    public void setRCompanyCdList(List rCompanyCdList) {
        this.rCompanyCdList = rCompanyCdList;
    }
    /**
     * 支部コードリストを取得します。
     * @return 支部コードリスト
     */
    public List getSibuCdList() {
        return sibuCdList;
    }
    /**
     * 支部コードリストを設定します。
     * @param sibuCdList 支部コードリスト
     */
    public void setSibuCdList(List sibuCdList) {
        this.sibuCdList = sibuCdList;
    }

    /**
     * SV情報クリア処理<br>
     */
    public void clear() {
        setInitFlag(false);
        setSvCd(null);
        setSvNameKj(null);
        setSvNameKna(null);
        setNavigationCase(null);
        setCompanyCd(null);
        setSibuCd(null);
        setActionFlag(false);
    }
}
