/*
 * 作成日: 2006/12/25
 * 
 */
package jp.co.isid.mos.bird.entry.nationalviewlist.dto;

import java.util.List;

import jp.co.isid.mos.bird.entry.common.code.ConditionTaishoJoken;
import jp.co.isid.mos.bird.entry.common.dto.CommonDto;
import jp.co.isid.mos.bird.entry.nationalviewlist.entity.UIAttendAbsentCntInfo;
import jp.co.isid.mos.bird.entry.nationalviewlist.entity.UIStatusInfo;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.dto.DownloadDto;

/**
 * リクエスト用申込状況確認Dto
 * 
 * @author xkinu
 */
public class NationalViewListReqDto extends CommonDto implements DownloadDto, CsvOutputDto{

    /**
     * 支部制限判断値
     */ 
    private boolean limit = false;
    /**
     * 指定オーナーコード
     */ 
    private String targetOnerCd;
    /**
     * 指定支部コード
     */ 
    private String targetSibuCd;
    /**
     * 指定SVコード
     * 
     * 入力したSVコードの値
     */
    private String targetSvCd;
    /** 条件項目：対象条件 */
    private String targetTaishoJoken;
    /**
     * 指定区分
     */
    private String targetKbn;

    /**
     * 指定オーナーコード
     */ 
    private String csvOnerCd;
    /**
     * 指定支部コード
     */ 
    private String csvSibuCd;
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
     * 対象条件コース情報エンティティー
     */
    private UIStatusInfo entityStatusInfo;

    /**
     * 照会用出欠席情報
     */ 
    private UIAttendAbsentCntInfo entityAttendAbsentInfo ;

    /**
     * 検索結果
     */
    private List listSearchData ;
    
    /**
     * 現行ウィンドウID値クリア処理
     *
     */
    public void clear(){
        //条件項目クリア
        setTargetTaishoJoken(null);
        setTargetOnerCd(null);
        setTargetSibuCd(null);
        setTargetSvCd(null);
        //CSV用条件項目クリア
        setCsvTaishoJoken(null);
        setCsvOnerCd(null);
        setCsvSibuCd(null);
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
        setEntityAttendAbsentInfo(null);
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
    public String getTargetSibuCd() {
        return targetSibuCd;
    }

    public void setTargetSibuCd(String targetSibuCd) {
        this.targetSibuCd = targetSibuCd;
    }
    /**
     * @return entityUIStatusInfo を戻します。
     */
    public UIStatusInfo getEntityStatusInfo() {
        return entityStatusInfo;
    }

    /**
     * @param entity 設定する。
     */
    public void setEntityStatusInfo(UIStatusInfo entity) {
        this.entityStatusInfo = entity;
    }
    /**
     * 対象エントリー状況情報存在判断処理
     * @return
     */
    public boolean isEmptyEntityStatusInfo() {
        if(getEntityStatusInfo() == null) {
            return true;
        }
        return false;
    }
    /**
     * オーナー参加・不参加(出欠席）情報エンティティー取得処理
     * 
     * @return onerAttendInfo を戻します。
     */
    public  UIAttendAbsentCntInfo getEntityAttendAbsentInfo() {
        return entityAttendAbsentInfo;
    }
    /**
     * オーナー参加・不参加(出欠席）情報エンティティー設定処理
     * 
     * @param onerAttendInfo 設定する onerAttendInfo。
     */
    public void setEntityAttendAbsentInfo(UIAttendAbsentCntInfo entity) {
        this.entityAttendAbsentInfo = entity;
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
     * @return csvSibuCd を戻します。
     */
    public String getCsvSibuCd() {
        return (String)csvSibuCd;
    }
    /**
     * @param csvSibuCd 設定する csvSibuCd。
     */
    public void setCsvSibuCd(String csvSibuCd) {
        this.csvSibuCd = csvSibuCd;
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
     * 現行のウィンドウで選ばれている対象条件は『支部』か？
     * 
     * true:支部が選択されている。
     * false:支部以外が選択されている。
     * @return
     */
    public boolean isTaishoJokenSibu(){
        return ConditionTaishoJoken.VALUE_SIBU.equals(getTargetTaishoJoken());
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

}