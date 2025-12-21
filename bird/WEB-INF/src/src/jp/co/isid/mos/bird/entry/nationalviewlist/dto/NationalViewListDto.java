/*
 * 作成日: 2006/12/25
 * 
 */
package jp.co.isid.mos.bird.entry.nationalviewlist.dto;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.entry.common.code.ConditionTaishoJoken;
import jp.co.isid.mos.bird.entry.common.dto.CommonDto;
import jp.co.isid.mos.bird.entry.nationalviewlist.entity.UIAttendAbsentCntInfo;
import jp.co.isid.mos.bird.entry.nationalviewlist.entity.UIStatusInfo;

/**
 * 全国大会申込状況確認Dto
 * 
 * @author xkinu
 */
public class NationalViewListDto extends CommonDto {

    /**
     * 最大データ保持件数
     */
    private int maxSize;
    /**
     * 支部制限判断値
     */ 
    private boolean limit = false;
    /**
     * 指定オーナーコード
     */ 
    private String targetOnerCd;
    /**
     * 指定オーナーコード
     */ 
    private Map jokenOnerCd= new LinkedHashMap();
    /**
     * 指定支部コード
     */ 
    private Map jokenSibuCd= new LinkedHashMap();
    /**
     * 指定SVコード
     * 
     * 入力したSVコードの値
     */
    private Map jokenSvCd= new LinkedHashMap();
    /** 条件項目：対象条件 */
    private Map jokenTaishoJoken= new LinkedHashMap();
    /**
     * 指定オーナーコード
     */ 
    private Map csvOnerCd= new LinkedHashMap();
    /**
     * 指定支部コード
     */ 
    private Map csvSibuCd= new LinkedHashMap();
    /**
     * 指定SVコード
     * 
     * 入力したSVコードの値
     */
    private Map csvSvCd= new LinkedHashMap();
    /**
     * 条件項目：対象条件
     */
    private Map csvTaishoJoken= new LinkedHashMap();
    /**
     * 指定区分
     */
    private Map csvKbn= new LinkedHashMap();

    /**
     *  会社情報
     *  対象となる会社コードリスト
     */
    private List listCompanyCd = new ArrayList();
    /**
     *  支部プルダウンの内容
     */
    private List listSibuCd = new ArrayList();

    // 対象条件プルダウンの内容
    private List listTaishoJoken = new ArrayList();

    /**
     * 対象条件コース情報エンティティー
     */
    private Map entityStatusInfo = new LinkedHashMap();

    // 対象が事業方針、懇親会、共栄の場合の変数
    /**
     *  区分プルダウン用リスト
     */
    private List listKbn;


    /**
     * 照会用出欠席情報
     */ 
    private Map entityAttendAbsentInfo = new LinkedHashMap();

    /**
     * 検索結果
     */
    private Map listSearchData = new LinkedHashMap();

    /**
     * 現行ウィンドウID値クリア処理
     *
     */
    public void clear(int windowId){
        //条件項目クリア
        setJokenTaishoJoken(windowId, null);
        setJokenOnerCd(windowId, null);
        setJokenSibuCd(windowId, null);
        setJokenSvCd(windowId, null);
        //CSV用条件項目クリア
        if(isSearched(windowId)) {
            setCsvTaishoJoken(windowId, null);
            setCsvOnerCd(windowId, null);
            setCsvSibuCd(windowId, null);
            setCsvSvCd(windowId, null);
            setCsvKbn(windowId, null);
        }
        //１．出欠席データ検索結果クリア
        searchDataClear(windowId);
    }
    /**
     * 検索結果クリア処理
     *
     */
    public void searchDataClear(int windowId){
        //１．出欠席データ検索結果クリア
        if(isExistSearchData(windowId)) {
            setListSearchData(windowId, null);
            setEntityAttendAbsentInfo(windowId, null);
        }
    }
    /**
     * データコピー処理
     * 
     * @param reqDto    NationalViewListReqDto
     */
    public void copyData(NationalViewListReqDto reqDto) {
        copyJokenData(reqDto);
        copySearchData(reqDto);
    }
    /**
     * 条件データコピー処理
     * 
     * @param reqDto    NationalViewListReqDto
     */
    public void copyJokenData(NationalViewListReqDto reqDto) {
        int windowId = reqDto.getWindowId();
        reqDto.setEntityStatusInfo(getEntityStatusInfo(windowId));
        settingJokenParam(reqDto);
    }
    /**
     * CSVダウンロード条件データコピー処理
     * 
     * @param reqDto    NationalViewListReqDto
     */
    public void copyCsvJokenData(NationalViewListReqDto reqDto) {
        int windowId = reqDto.getWindowId();
        reqDto.setTargetCompanyCd(getTargetCompanyCd());
        reqDto.setUserId(getUserId());
        reqDto.setLimit(isLimit());
        reqDto.setSysDate(getSysDate());
        reqDto.setEntityStatusInfo(getEntityStatusInfo(windowId));
        settingCsvParam(reqDto);
    }
    /**
     * 初期データコピー処理
     * 
     * @param windowId
     * @param reqDto
     */
    public void copyInitData(int windowId, NationalViewListReqDto reqDto) {
        reqDto.setWindowId(windowId);
        copyJokenData(reqDto);
    }
    /**
     * 検索データコピー処理
     * 
     * @param windowId
     * @param reqDto
     */
    public void copySearchData(NationalViewListReqDto reqDto) {
        int windowId = reqDto.getWindowId();
        reqDto.setEntityAttendAbsentInfo(getEntityAttendAbsentInfo(windowId));
        reqDto.setListSearchData(getListSearchData(windowId));
    }
    /**
     * 検索データ存在判断処理
     * 
     * @param windowId
     * @return
     */
    public boolean isExistSearchData(int windowId) {
        return listSearchData.containsKey(new Integer(windowId));
    }
    /**
     * 検索済存在判断処理
     * 
     * @param windowId
     * @return
     */
    public boolean isSearched(int windowId) {
        return csvTaishoJoken.containsKey(new Integer(windowId));
    }
    /**
     *  検索結果取得処理
     * @return
     */
    public List getListSearchData(int windowId) {
        return (List)listSearchData.get(new Integer(windowId));
    }
    /**
     *  検索結果設定処理
     *  
     * @param data
     */
    public void setListSearchData(int windowId, List data) {
        // 現在ウインドウIDのデータを保持している場合
        if (this.listSearchData.containsKey(new Integer(windowId))) {
            // 順番を入れ替える為、前回データを削除する
            this.listSearchData.remove(new Integer(windowId));
        // 最大数を超えた場合
        } else if (this.listSearchData.size() > getMaxSize()) {
            // 最古データを削除
            this.listSearchData.remove(this.listSearchData.keySet().toArray()[0]);
        }
        // リスト設定
        this.listSearchData.put(new Integer(windowId), data);
    }

    /**
     * 支部プルダウン情報
     * @return
     */
    public List getListSibuCd() {
        return listSibuCd;
    }
    
    /**
     * 条件項目：対象条件リスト取得処理
     * @return
     */
    public List getListTaishoJoken() {
        return listTaishoJoken;
    }
    /**
     * @param listTaishoJoken 設定する listTaishoJoken。
     */
    public void setListTaishoJoken(List listTaishoJoken) {
        this.listTaishoJoken = listTaishoJoken;
    }

    /**
     * @return entityUIStatusInfo を戻します。
     */
    public UIStatusInfo getEntityStatusInfo(int windowId) {
        return (UIStatusInfo)entityStatusInfo.get(new Integer(windowId));
    }

    /**
     * @param entity 設定する。
     */
    public void setEntityStatusInfo(int windowId, UIStatusInfo entity) {
        this.entityStatusInfo.put(new Integer(windowId), entity);
    }
    /**
     * 対象エントリー状況情報存在判断処理
     * @return
     */
    public boolean isEmptyEntityStatusInfo(int windowId) {
        if(getEntityStatusInfo(windowId) == null) {
            return true;
        }
        return false;
    }
    /**
     * 条件項目：区分用リスト取得処理
     * 
     * @return listKbn を戻します。
     */
    public List getListKbn() {
        return listKbn;
    }
    /**
     * 条件項目：区分用リスト設定処理
     * 
     * @param listKbn 設定する listKbn。
     */
    public void setListKbn(List listKbn) {
        this.listKbn = listKbn;
    }
    /**
     * オーナー参加・不参加(出欠席）情報エンティティー取得処理
     * 
     * @return onerAttendInfo を戻します。
     */
    public  UIAttendAbsentCntInfo getEntityAttendAbsentInfo(int windowId) {
        return (UIAttendAbsentCntInfo)entityAttendAbsentInfo.get(new Integer(windowId));
    }
    /**
     * オーナー参加・不参加(出欠席）情報エンティティー設定処理
     * 
     * @param onerAttendInfo 設定する onerAttendInfo。
     */
    public void setEntityAttendAbsentInfo(int windowId, UIAttendAbsentCntInfo entity) {
        // 現在ウインドウIDのデータを保持している場合
        if (this.entityAttendAbsentInfo.containsKey(new Integer(windowId))) {
            // 順番を入れ替える為、前回データを削除する
            this.entityAttendAbsentInfo.remove(new Integer(windowId));
        // 最大数を超えた場合
        } else if (this.entityAttendAbsentInfo.size() > getMaxSize()) {
            // 最古データを削除
            this.entityAttendAbsentInfo.remove(this.entityAttendAbsentInfo.keySet().toArray()[0]);
        }
        this.entityAttendAbsentInfo.put(new Integer(windowId), entity);
    }
    /**
     * @param listSibuCd 設定する listSibuCd。
     */
    public void setListSibuCd(List listSibuCd) {
        this.listSibuCd = listSibuCd;
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
     * @return listCompanyCd を戻します。
     */
    public List getListCompanyCd() {
        return listCompanyCd;
    }
    /**
     * @param listCompanyCd 設定する listCompanyCd。
     */
    public void setListCompanyCd(List listCompanyCd) {
        this.listCompanyCd = listCompanyCd;
    }
    /**
     * @return jokenOnerCd を戻します。
     */
    public String getJokenOnerCd(int windowId) {
        return (String)jokenOnerCd.get(new Integer(windowId));
    }
    /**
     * @param jokenOnerCd 設定する jokenOnerCd。
     */
    public void setJokenOnerCd(int windowId, String jokenOnerCd) {
        this.jokenOnerCd.put(new Integer(windowId), jokenOnerCd);
    }
    /**
     * @return jokenSibuCd を戻します。
     */
    public String getJokenSibuCd(int windowId) {
        return (String)jokenSibuCd.get(new Integer(windowId));
    }
    /**
     * @param jokenSibuCd 設定する jokenSibuCd。
     */
    public void setJokenSibuCd(int windowId, String jokenSibuCd) {
        this.jokenSibuCd.put(new Integer(windowId), jokenSibuCd);
    }
    /**
     * @return jokenSvCd を戻します。
     */
    public String getJokenSvCd(int windowId) {
        return (String)jokenSvCd.get(new Integer(windowId));
    }
    /**
     * @param jokenSvCd 設定する jokenSvCd。
     */
    public void setJokenSvCd(int windowId, String jokenSvCd) {
        this.jokenSvCd.put(new Integer(windowId), jokenSvCd);
    }
    /**
     * @return jokenTaishoJoken を戻します。
     */
    public String getJokenTaishoJoken(int windowId) {
        return (String)jokenTaishoJoken.get(new Integer(windowId));
    }
    /**
     * @param jokenTaishoJoken 設定する jokenTaishoJoken。
     */
    public void setJokenTaishoJoken(int windowId, String jokenTaishoJoken) {
        this.jokenTaishoJoken.put(new Integer(windowId), jokenTaishoJoken);
    }
    /**
     * @return csvKbn を戻します。
     */
    public String getCsvKbn(int windowId) {
        return (String)csvKbn.get(new Integer(windowId));
    }
    /**
     * @param csvKbn 設定する csvKbn。
     */
    public void setCsvKbn(int windowId, String csvKbn) {
        this.csvKbn.put(new Integer(windowId), csvKbn);
    }
    /**
     * @return csvOnerCd を戻します。
     */
    public String getCsvOnerCd(int windowId) {
        return (String)csvOnerCd.get(new Integer(windowId));
    }
    /**
     * @param csvOnerCd 設定する csvOnerCd。
     */
    public void setCsvOnerCd(int windowId, String csvOnerCd) {
        this.csvOnerCd.put(new Integer(windowId), csvOnerCd);
    }
    /**
     * @return csvSibuCd を戻します。
     */
    public String getCsvSibuCd(int windowId) {
        return (String)csvSibuCd.get(new Integer(windowId));
    }
    /**
     * @param csvSibuCd 設定する csvSibuCd。
     */
    public void setCsvSibuCd(int windowId, String csvSibuCd) {
        this.csvSibuCd.put(new Integer(windowId), csvSibuCd);
    }
    /**
     * @return csvSvCd を戻します。
     */
    public String getCsvSvCd(int windowId) {
        return (String)csvSvCd.get(new Integer(windowId));
    }
    /**
     * @param csvSvCd 設定する csvSvCd。
     */
    public void setCsvSvCd(int windowId, String csvSvCd) {
        this.csvSvCd.put(new Integer(windowId), csvSvCd);
    }
    /**
     * @return csvTaishoJoken を戻します。
     */
    public String getCsvTaishoJoken(int windowId) {
        return (String)csvTaishoJoken.get(new Integer(windowId));
    }
    /**
     * @param csvTaishoJoken 設定する csvTaishoJoken。
     */
    public void setCsvTaishoJoken(int windowId, String csvTaishoJoken) {
        this.csvTaishoJoken.put(new Integer(windowId), csvTaishoJoken);
    }
    /**
     * 現ウィンドウID のCSV検索対象条件項目保管処理
     * 
     * 保管対象パラメーター
     * ・対象条件
     * ・支部コード
     * ・SVコード
     * 
     * @param reqDto リクエスト用DTO
     */
    public void holdCsvParam(NationalViewListReqDto reqDto){     
        int windowId = reqDto.getWindowId();
        setCsvTaishoJoken(windowId, reqDto.getTargetTaishoJoken());
        setCsvSibuCd(windowId, reqDto.getTargetSibuCd());
        setCsvSvCd(windowId, reqDto.getTargetSvCd());
    }
    /**
     * リクエストされたウィンドウID の保管データから
     * CSV検索対象条件項目への設定処理
     * 
     * 再設定対象パラメーター
     * ・SVコード
     * 
     * @param reqDto リクエスト用DTO
     */
    public void settingCsvParam(NationalViewListReqDto reqDto){
        int windowId = reqDto.getWindowId();
        reqDto.setCsvTaishoJoken(getCsvTaishoJoken(windowId));
        reqDto.setCsvSibuCd(getCsvSibuCd(windowId));
        reqDto.setCsvSvCd(getCsvSvCd(windowId));
        reqDto.setTargetKbn(getCsvKbn(windowId));
    }
    /**
     * リクエストされたウィンドウID の保管データから
     * 検索対象条件項目への設定処理
     * 
     * 再設定対象パラメーター
     * ・対象条件
     * ・対象
     * ・支部コード
     * ・SVコード
     * 
     * @param reqDto リクエスト用DTO
     */
    public void settingJokenParam(NationalViewListReqDto reqDto){
        int windowId = reqDto.getWindowId();
        reqDto.setTargetTaishoJoken(getJokenTaishoJoken(windowId));
        reqDto.setTargetSibuCd(getJokenSibuCd(windowId));
        reqDto.setTargetSvCd(getJokenSvCd(windowId));
        reqDto.setTargetKbn(getCsvKbn(windowId));
    }
    /**
     * リクエストされた検索対象条件項目保管処理
     * 
     * 保管対象パラメーター
     * ・対象条件
     * ・支部コード
     * ・SVコード
     * 
     * @param reqDto
     */
    public void holdJokenParam(NationalViewListReqDto reqDto){
        int windowId = reqDto.getWindowId();
        String taishoJoken = reqDto.getTargetTaishoJoken();
        setJokenTaishoJoken(windowId, taishoJoken);
        if(ConditionTaishoJoken.VALUE_ALLONSER.equals(taishoJoken)){
            setJokenSibuCd(windowId, "");
            setJokenSvCd(windowId, "");
        }
        else if(ConditionTaishoJoken.VALUE_SIBU.equals(taishoJoken)){
            setJokenSibuCd(windowId, reqDto.getTargetSibuCd());
            setJokenSvCd(windowId, "");
        }
        else if(ConditionTaishoJoken.VALUE_SV.equals(taishoJoken)){
            setJokenSibuCd(windowId, "");
            setJokenSvCd(windowId, reqDto.getTargetSvCd());
        }
        setCsvKbn(windowId, reqDto.getTargetKbn());
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
     * @return maxSize を戻します。
     */
    public int getMaxSize() {
        return maxSize;
    }
    /**
     * @param maxSize 設定する maxSize。
     */
    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

}