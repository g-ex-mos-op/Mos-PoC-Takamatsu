/*
 * 作成日: 2006/12/25
 * 
 */
package jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.code.ConditionTaishoJoken;
import jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.code.UserType;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.entity.MstUser;

/**
 * Session用Dto
 * 
 * @author xkinu
 */
public class BunsAutoAmtRegistDto {
    /**
     * ログインユーザー情報
     */
    private BirdUserInfo birdUserInfo;
    /**
     * BIRD日付情報
     */
    private BirdDateInfo birdDateInfo;
    
    /* 対象会社コード  */
    private String targetCompanyCd="00";
    /* セッションKey */
    private String nowSessionKey;
    /* セッションKey */
    private Map sessionKey = new HashMap();
    /**
     * 最大ウィンドウID
     */
    private int maxWindowId = 0;
    /**
     * 最大データ保持件数
     */
    private int maxSize;
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
    private Map jokenMiseCd= new LinkedHashMap();
    /**
     * 指定SVコード
     * 
     * 入力したSVコードの値
     */
    private Map jokenSvCd= new LinkedHashMap();
    /**
     * 対象条件で指定されたコードの名称
     */
    private Map JokenNameKj= new LinkedHashMap();
    /** 条件項目：対象条件 */
    private Map jokenTaishoJoken= new LinkedHashMap();
    /**
     * 指定オーナーコード
     */ 
    private Map csvOnerCd= new LinkedHashMap();
    /**
     * 指定支部コード
     */ 
    private Map csvMiseCd= new LinkedHashMap();
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
     *  会社情報
     *  対象となる会社コードリスト
     */
    private List listCompanyCd = new ArrayList();

    // 対象条件プルダウンの内容
    private List listTaishoJoken = new ArrayList();

    /**
     * 変更フラグ
     */
    private String flgRegist;
    /**
     * ダウンロードフラグ
     */
    private String flgDownload;

    /**
     * 検索結果
     */
    private Map listSearchData = new LinkedHashMap();
    /**
     * 検索結果の店舗リスト
     */
    private Map listSearchDataMiseList = new LinkedHashMap();
    /**
     * 編集用検索結果
     */
    private List listRegData;
    /**
     * 編集用検索結果の店舗リスト
     */
    private List listRegDataMiseList;
    /**
     * セッションKeyの設定
     * @return sessionKey を戻します。
     */
    public String getSessionKey(int windowId) {
        return (String)sessionKey.get(new Integer(windowId));
    }
    /**
     * セッションKeyの設定
     * @param sessionKey sessionKey を設定。
     */
    public void setSessionKey(int windowId, String sessionKey) {
        this.sessionKey.put(new Integer(windowId), sessionKey);
    }
    /**
     * @return nowSessionKey を戻します。
     */
    public String getNowSessionKey() {
        return nowSessionKey;
    }
    /**
     * @param nowSessionKey 設定する nowSessionKey。
     */
    public void setNowSessionKey(String nowSessionKey) {
        this.nowSessionKey = nowSessionKey;
    }
    /**
     * @return targetCompanyCd を戻します。
     */
    public String getTargetCompanyCd() {
        return targetCompanyCd;
    }
    /**
     * @param targetCompanyCd 設定する targetCompanyCd。
     */
    public void setTargetCompanyCd(String targetCompanyCd) {
        this.targetCompanyCd = targetCompanyCd;
    }

    /**
     * 最大ウィンドウIDを取得します。
     * @return 最大ウィンドウID
     */
    public int getMaxWindowId() {
        return maxWindowId;
    }
    
    /**
     * 最大ウィンドウIDを設定します。
     * @param maxWindowId　最大ウィンドウID
     */
    public void setMaxWindowId(int maxWindowId) {
        this.maxWindowId = maxWindowId;
    }
    
    /**
     * ウィンドウIDを生成します。
     */
    public int createWindowId() {
        int newWindowId = getMaxWindowId() + 1;
        setMaxWindowId(newWindowId);
        return newWindowId;
    }
    
    /**
     * 現行ウィンドウID値クリア処理
     *
     */
    public void clear(int windowId){
        //条件項目クリア
        setJokenTaishoJoken(windowId, null);
        setJokenOnerCd(windowId, null);
        setJokenMiseCd(windowId, null);
        setJokenSvCd(windowId, null);
        setJokenNameKj(windowId, null);
        //CSV用条件項目クリア
        if(isSearched(windowId)) {
            setCsvTaishoJoken(windowId, null);
            setCsvOnerCd(windowId, null);
            setCsvMiseCd(windowId, null);
            setCsvSvCd(windowId, null);
        }
        //１．データ検索結果クリア
        searchDataClear(windowId);
    }
    /**
     * 検索結果クリア処理
     *
     */
    public void searchDataClear(int windowId){
        if(isRegist()) {
            setListRegData(null);
            setListRegDataMiseList(null);
        }
        else {
            if(isExistSearchData(windowId)) {
                setListSearchData(windowId, null);
                setListSearchDataMiseList(windowId, null);
            }
        }
    }
    /**
     * データコピー処理
     * 
     * @param reqDto    BunsAutoAmtRegistReqDto
     */
    public void copyData(BunsAutoAmtRegistReqDto reqDto) {
        copyJokenData(reqDto);
        copySearchData(reqDto);
    }
    /**
     * 条件データコピー処理
     * 
     * @param reqDto    BunsAutoAmtRegistReqDto
     */
    public void copyJokenData(BunsAutoAmtRegistReqDto reqDto) {
        settingJokenParam(reqDto);
    }
    /**
     * CSVダウンロード条件データコピー処理
     * 
     * @param reqDto    BunsAutoAmtRegistReqDto
     */
    public void copyCsvJokenData(BunsAutoAmtRegistReqDto reqDto) {
        settingCsvParam(reqDto);
    }
    /**
     * 初期データコピー処理
     * 
     * @param windowId
     * @param reqDto
     */
    public void copyInitData(int windowId, BunsAutoAmtRegistReqDto reqDto) {
        reqDto.setWindowId(windowId);
        reqDto.setTargetTaishoJoken(ConditionTaishoJoken.VALUE_ONER);
    }
    /**
     * 検索データコピー処理
     * 
     * @param windowId
     * @param reqDto
     */
    public void copySearchData(BunsAutoAmtRegistReqDto reqDto) {
        int windowId = reqDto.getWindowId();
        if(isRegist()) {
            reqDto.setListSearchData(getListRegData());
            reqDto.setListSearchDataMiseList(getListRegDataMiseList());
        }
        else {
            reqDto.setListSearchData(getListSearchData(windowId));
            reqDto.setListSearchDataMiseList(getListSearchDataMiseList(windowId));
        }
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
     * 検索結果の店舗リスト取得処理
     * @return listSearchDataMiseList を戻します。
     */
    public List getListSearchDataMiseList(int windowId) {
        return (List)listSearchDataMiseList.get(new Integer(windowId));
    }
    /**
     * 検索結果の店舗リスト設定処理
     * 
     * 複数ウィンド対応機能の保持件数制限あり。
     * 
     * @param miseList 設定する listSearchDataMiseList。
     */
    public void setListSearchDataMiseList(int windowId, List miseList) {
        // 現在ウインドウIDのデータを保持している場合
        if (this.listSearchDataMiseList.containsKey(new Integer(windowId))) {
            // 順番を入れ替える為、前回データを削除する
            this.listSearchDataMiseList.remove(new Integer(windowId));
        // 最大数を超えた場合
        } else if (this.listSearchDataMiseList.size() > getMaxSize()) {
            // 最古データを削除
            this.listSearchDataMiseList.remove(this.listSearchDataMiseList.keySet().toArray()[0]);
        }
        // リスト設定
        this.listSearchDataMiseList.put(new Integer(windowId), miseList);
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
     * 検索結果存在チェック処理
     * @return
     */
    public boolean isEmptySearchData(int windowId) {
        if(isRegist()) {
            return (getListRegData() == null);
        }
        if(getListSearchData(windowId) == null) {
            return true;
        }
        return false;
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
     * @return jokenMiseCd を戻します。
     */
    public String getJokenMiseCd(int windowId) {
        return (String)jokenMiseCd.get(new Integer(windowId));
    }
    /**
     * @param jokenMiseCd 設定する jokenMiseCd。
     */
    public void setJokenMiseCd(int windowId, String jokenMiseCd) {
        this.jokenMiseCd.put(new Integer(windowId), jokenMiseCd);
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
     * @return csvMiseCd を戻します。
     */
    public String getCsvMiseCd(int windowId) {
        return (String)csvMiseCd.get(new Integer(windowId));
    }
    /**
     * @param csvMiseCd 設定する csvMiseCd。
     */
    public void setCsvMiseCd(int windowId, String csvMiseCd) {
        this.csvMiseCd.put(new Integer(windowId), csvMiseCd);
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
    }    /**
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
    public void holdCsvParam(BunsAutoAmtRegistReqDto reqDto){     
        int windowId = reqDto.getWindowId();
        setCsvTaishoJoken(windowId, reqDto.getTargetTaishoJoken());
        setCsvMiseCd(windowId, reqDto.getTargetMiseCd());
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
    public void settingCsvParam(BunsAutoAmtRegistReqDto reqDto){
        int windowId = reqDto.getWindowId();
        reqDto.setCsvTaishoJoken(getCsvTaishoJoken(windowId));
        reqDto.setCsvMiseCd(getCsvMiseCd(windowId));
        reqDto.setCsvSvCd(getCsvSvCd(windowId));
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
    public void settingJokenParam(BunsAutoAmtRegistReqDto reqDto){
        int windowId = reqDto.getWindowId();
        reqDto.setTargetTaishoJoken(getJokenTaishoJoken(windowId));
        reqDto.setTargetOnerCd(getJokenOnerCd(windowId));
        reqDto.setTargetMiseCd(getJokenMiseCd(windowId));
        reqDto.setTargetSvCd(getJokenSvCd(windowId));
        reqDto.setTargetNameKj(getJokenNameKj(windowId));
    }
    /**
     * リクエストされた検索対象条件項目保管処理
     * 
     * 保管対象パラメーター
     * ・対象条件
     * ・オーナーコード
     * ・店舗コード
     * ・SVコード
     * 
     * @param reqDto
     */
    public void holdJokenParam(BunsAutoAmtRegistReqDto reqDto){
        int windowId = reqDto.getWindowId();
        String taishoJoken = reqDto.getTargetTaishoJoken();
        setJokenTaishoJoken(windowId, taishoJoken);
        if(ConditionTaishoJoken.VALUE_ONER.equals(taishoJoken)){
            setJokenOnerCd(windowId, reqDto.getTargetOnerCd());
            setJokenMiseCd(windowId, "");
            setJokenSvCd(windowId, "");
        }
        else if(ConditionTaishoJoken.VALUE_MISE.equals(taishoJoken)){
            setJokenOnerCd(windowId, "");
            setJokenMiseCd(windowId, reqDto.getTargetMiseCd());
            setJokenSvCd(windowId, "");
        }
        else if(ConditionTaishoJoken.VALUE_SV.equals(taishoJoken)){
            setJokenOnerCd(windowId, "");
            setJokenMiseCd(windowId, "");
            setJokenSvCd(windowId, reqDto.getTargetSvCd());
        }
     }
    /**
     * @return limit を戻します。
     */
    public boolean isLimit() {
        return getBirdUserInfo().isLimit();
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
    /**
     * ログインユーザーのユーザータイプは本部か否か。
     * 
     * @return
     */
    public boolean isHonbuUser() {
        return UserType.HONBU.equals(getUserTypeCd());
    }
    /**
     * ログインユーザーのユーザータイプはオーナーか否か。
     * 
     * @return
     */
    public boolean isOnerUser() {
        return UserType.ONER.equals(getUserTypeCd());
    }
    /**
     * ログインユーザーのユーザータイプは店舗か否か。
     * 
     * @return
     */
    public boolean isTenpoUser() {
        return UserType.TENPO.equals(getUserTypeCd());
    }
    /**
     * ログインユーザーのユーザータイプは本部か否か。
     * 
     * @return
     */
    public String getUserTypeCd() {
        MstUser mstUser = (MstUser)getBirdUserInfo().getMstUser();
        return mstUser.getUserTypeCd();
    }
    /**
     * ユーザー別照会制御用判断処理
     * 
     * @return
     */
    public boolean isRegist() {
        return "1".equals(flgRegist);
    }
    /**
     * ユーザー別照会ダウンロード判断処理
     * 
     * @return
     */
    public boolean isDownload() {
        return "1".equals(flgDownload);
    }
    /**
     * @return birdDateInfo を戻します。
     */
    public BirdDateInfo getBirdDateInfo() {
        return birdDateInfo;
    }
    /**
     * @param birdDateInfo 設定する birdDateInfo。
     */
    public void setBirdDateInfo(BirdDateInfo birdDateInfo) {
        this.birdDateInfo = birdDateInfo;
    }
    /**
     * @return birdUserInfo を戻します。
     */
    public BirdUserInfo getBirdUserInfo() {
        return birdUserInfo;
    }
    /**
     * @param birdUserInfo 設定する birdUserInfo。
     */
    public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
        this.birdUserInfo = birdUserInfo;
    }
    /**
     * @return flgDownload を戻します。
     */
    public String getFlgDownload() {
        return flgDownload;
    }
    /**
     * @param flgDownload 設定する flgDownload。
     */
    public void setFlgDownload(String flgDownload) {
        this.flgDownload = flgDownload;
    }
    /**
     * @return flgRegist を戻します。
     */
    public String getFlgRegist() {
        return flgRegist;
    }
    /**
     * @param flgRegist 設定する flgRegist。
     */
    public void setFlgRegist(String flgRegist) {
        this.flgRegist = flgRegist;
    }
    /**
     * @return listRegData を戻します。
     */
    public List getListRegData() {
        return listRegData;
    }
    /**
     * @param listRegData 設定する listRegData。
     */
    public void setListRegData(List listRegData) {
        this.listRegData = listRegData;
    }
    /**
     * @return listRegDataMiseList を戻します。
     */
    public List getListRegDataMiseList() {
        return listRegDataMiseList;
    }
    /**
     * @param listRegDataMiseList 設定する listRegDataMiseList。
     */
    public void setListRegDataMiseList(List listRegDataMiseList) {
        this.listRegDataMiseList = listRegDataMiseList;
    }
    /**
     * @return jokenNameKj を戻します。
     */
    public String getJokenNameKj(int windowId) {
        return (String)JokenNameKj.get(new Integer(windowId));
    }
    /**
     * @param jokenNameKj 設定する jokenNameKj。
     */
    public void setJokenNameKj(int windowId, String name) {
        JokenNameKj.put(new Integer(windowId), name);
    }
}