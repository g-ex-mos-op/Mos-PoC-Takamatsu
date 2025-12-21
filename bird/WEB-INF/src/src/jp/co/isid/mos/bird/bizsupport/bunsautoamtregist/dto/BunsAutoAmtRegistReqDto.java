/*
 * 作成日: 2006/12/25
 * 
 */
package jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.dto;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.code.ConditionTaishoJoken;
import jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.util.BunsAutoAmtRegistUtil;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.dto.DownloadDto;

/**
 * リクエスト用Dto
 * 
 * @author xkinu
 */
public class BunsAutoAmtRegistReqDto implements DownloadDto, CsvOutputDto{
    /** DTO【自画面セッション用】*/
    private BunsAutoAmtRegistDto bunsAutoAmtRegistDto;
    /**
     * ウィンドウID
     */
    private int windowId = 0;
    /**
     * 支部制限判断値
     */ 
    private boolean limit = false;
    /** 条件項目：対象条件 */
    private String targetTaishoJoken = ConditionTaishoJoken.VALUE_ONER;
    /**
     * 指定オーナーコード
     */ 
    private String targetOnerCd;
    /**
     * 指定支部コード
     */ 
    private String targetMiseCd;
    /**
     * 指定SVコード
     * 
     * 入力したSVコードの値
     */
    private String targetSvCd;
    /**
     * 対象条件で指定されたコードの名称
     */
    private String targetNameKj;
    
    /**
     * ダウンロード指定区分
     * 
     * 区分コードの値
     */
    private String targetKbn;
    /**
     * 遷移元画面VIEW_ID
     */
    private String backViewId = BunsAutoAmtRegistUtil.VIEW_ID_CONDITION;
    /**
     * 指定オーナーコード
     */ 
    private String csvOnerCd;
    /**
     * 指定支部コード
     */ 
    private String csvMiseCd;
    /**
     * 指定SVコード
     * 
     * 入力したSVコードの値
     */
    private String csvSvCd;
    /**
     * 条件項目：対象条件
     */
    private String csvTaishoJoken;

    /**
     * 検索結果
     */
    private List listSearchData ;
    /**
     * 検索結果の店舗リスト
     */
    private List listSearchDataMiseList;
    /**
     * 登録完了フラグ
     */
    private String registedFlg = "";
    
    /**
     * ウィンドウIDを取得します。
     * @return　ウィンドウID
     */
    public int getWindowId() {
        return windowId;
    }
    
    /**
     * ウィンドウIDを設定します。
     * @param windowId　ウィンドウID
     */
    public void setWindowId(int windowId) {
        this.windowId = windowId;
    }

    /**
     * 現行ウィンドウID値クリア処理
     *
     */
    public void clear(){
        //条件項目クリア
        setTargetTaishoJoken(null);
        setTargetOnerCd(null);
        setTargetMiseCd(null);
        setTargetSvCd(null);
        //CSV用条件項目クリア
        setCsvTaishoJoken(null);
        setCsvOnerCd(null);
        setCsvMiseCd(null);
        setCsvSvCd(null);
        //１．出欠席データ検索結果クリア
        searchDataClear();
    }
    /**
     * 検索結果クリア処理
     *
     */
    public void searchDataClear(){
        //１．出欠席データ検索結果クリア
        setListSearchData(null);
    }

    /**
     *  検索結果取得処理
     * @return
     */
    public List getListSearchData() {
        return listSearchData;
    }
    /**
     *  検索結果設定処理
     *  
     * @param data
     */
    public void setListSearchData(List data) {
        this.listSearchData = data;
    }
    // 支部コードのsetterとgetter
    public String getTargetMiseCd() {
        return targetMiseCd;
    }

    public void setTargetMiseCd(String targetMiseCd) {
        this.targetMiseCd = targetMiseCd;
    }
    /**
     * @return targetSvCd を戻します。
     */
    public String getTargetSvCd() {
        return targetSvCd;
    }
    /**
     * @param targetSvCd 設定する targetSvCd。
     */
    public void setTargetSvCd(String targetSvCd) {
        this.targetSvCd = targetSvCd;
    }
    /**
     * @return targetTaishoJoken を戻します。
     */
    public String getTargetTaishoJoken() {
        return targetTaishoJoken;
    }
    /**
     * @param targetTaishoJoken 設定する targetTaishoJoken。
     */
    public void setTargetTaishoJoken(String targetTaishoJoken) {
        this.targetTaishoJoken = targetTaishoJoken;
    }
    /**
     * @return targetOnerCd を戻します。
     */
    public String getTargetOnerCd() {
        return targetOnerCd;
    }
    /**
     * @param targetOnerCd 設定する targetOnerCd。
     */
    public void setTargetOnerCd(String targetOnerCd) {
        this.targetOnerCd = targetOnerCd;
    }
    /**
     * @return csvOnerCd を戻します。
     */
    public String getCsvOnerCd() {
        return (String)csvOnerCd;
    }
    /**
     * @param csvOnerCd 設定する csvOnerCd。
     */
    public void setCsvOnerCd(String csvOnerCd) {
        this.csvOnerCd = csvOnerCd;
    }
    /**
     * @return csvMiseCd を戻します。
     */
    public String getCsvMiseCd() {
        return (String)csvMiseCd;
    }
    /**
     * @param csvMiseCd 設定する csvMiseCd。
     */
    public void setCsvMiseCd(String csvMiseCd) {
        this.csvMiseCd = csvMiseCd;
    }
    /**
     * @return csvSvCd を戻します。
     */
    public String getCsvSvCd() {
        return (String)csvSvCd;
    }
    /**
     * @param csvSvCd 設定する csvSvCd。
     */
    public void setCsvSvCd(String csvSvCd) {
        this.csvSvCd = csvSvCd;
    }
    /**
     * @return csvTaishoJoken を戻します。
     */
    public String getCsvTaishoJoken() {
        return csvTaishoJoken;
    }
    /**
     * @param csvTaishoJoken 設定する csvTaishoJoken。
     */
    public void setCsvTaishoJoken(String csvTaishoJoken) {
        this.csvTaishoJoken = csvTaishoJoken;
    }
    /**
     * 現行のウィンドウで選ばれている対象条件は『オーナー』か？
     * 
     * true:オーナーが選択されている。
     * false:オーナー以外が選択されている。
     * @return
     */
    public boolean isTaishoJokenOner(){
        return ConditionTaishoJoken.VALUE_ONER.equals(getTargetTaishoJoken());
    }
    /**
     * 現行のウィンドウで選ばれている対象条件は『店舗』か？
     * 
     * true:店舗が選択されている。
     * false:店舗以外が選択されている。
     * @return
     */
    public boolean isTaishoJokenMise(){
        return ConditionTaishoJoken.VALUE_MISE.equals(getTargetTaishoJoken());
    }
    /**
     * 現行のウィンドウで選ばれている対象条件は『SVコード』か？
     * 
     * true:SVコードが選択されている。
     * false:SVコード以外が選択されている。
     * @return
     */
    public boolean isTaishoJokenSv(){
        return ConditionTaishoJoken.VALUE_SV.equals(getTargetTaishoJoken());
    }
    /**
     * @return limit を戻します。
     */
    public boolean isLimit() {
        return limit;
    }
    /**
     * @param limit 設定する limit。
     */
    public void setLimit(boolean limit) {
        this.limit = limit;
    }
    /**
     * @return targetKbn を戻します。
     */
    public String getTargetKbn() {
        return targetKbn;
    }
    /**
     * @param targetKbn 設定する targetKbn。
     */
    public void setTargetKbn(String targetKbn) {
        this.targetKbn = targetKbn;
    }

    /**
     * @return listSearchDataMiseList を戻します。
     */
    public List getListSearchDataMiseList() {
        return listSearchDataMiseList;
    }

    /**
     * @param listSearchDataMiseList 設定する listSearchDataMiseList。
     */
    public void setListSearchDataMiseList(List listSearchDataMiseList) {
        this.listSearchDataMiseList = listSearchDataMiseList;
    }

    /**
     * 遷移元が初期画面判断処理
     * 
     * @return
     */
    public boolean isCondition() {
        return BunsAutoAmtRegistUtil.VIEW_ID_CONDITION.equals(getBackViewId());
    }
    /**
     * 遷移元が編集画面判断処理
     * 
     * @return
     */
    public boolean isEdit() {
        return BunsAutoAmtRegistUtil.VIEW_ID_EDIT.equals(getBackViewId());
    }
    /**
     * 遷移元が照会・確認画面判断処理
     * 
     * @return
     */
    public boolean isConfirm() {
        return BunsAutoAmtRegistUtil.VIEW_ID_CONFIRM.equals(getBackViewId());
    }
    /**
     * JSFタイトル取得処理
     * 
     * @return
     */
    public String getLayoutTitle() {
        if (getBunsAutoAmtRegistDto().isHonbuUser() && isCondition()) {
            return BunsAutoAmtRegistUtil.SCREEN_NAME;
        }
        else if (isEdit()) {
            return BunsAutoAmtRegistUtil.SCREEN_NAME + "(編集画面)";
        }
        else if (isConfirm()) {
            return BunsAutoAmtRegistUtil.SCREEN_NAME + "(確認画面)";
        }
        return BunsAutoAmtRegistUtil.SCREEN_NAME;
    }

    /**
     * @return bunsAutoAmtRegistDto を戻します。
     */
    public BunsAutoAmtRegistDto getBunsAutoAmtRegistDto() {
        return bunsAutoAmtRegistDto;
    }

    /**
     * @param bunsAutoAmtRegistDto 設定する bunsAutoAmtRegistDto。
     */
    public void setBunsAutoAmtRegistDto(BunsAutoAmtRegistDto bunsAutoAmtRegistDto) {
        this.bunsAutoAmtRegistDto = bunsAutoAmtRegistDto;
    }

    /**
     * @return registedFlg を戻します。
     */
    public String getRegistedFlg() {
        return registedFlg;
    }

    /**
     * @param registedFlg 設定する registedFlg。
     */
    public void setRegistedFlg(String registedFlg) {
        this.registedFlg = registedFlg;
    }

    /**
     * 遷移元画面VIEW_ID取得処理
     * 
     * @return backViewId を戻します。
     */
    public String getBackViewId() {
        return backViewId;
    }

    /**
     * 遷移元画面VIEW_ID設定処理
     * 
     * @param backViewId 設定する backViewId。
     */
    public void setBackViewId(String backViewId) {
        this.backViewId = backViewId;
    }

    /**
     * @return targetNameKj を戻します。
     */
    public String getTargetNameKj() {
        return targetNameKj;
    }

    /**
     * @param targetNameKj 設定する targetNameKj。
     */
    public void setTargetNameKj(String targetNameKj) {
        this.targetNameKj = targetNameKj;
    }
}