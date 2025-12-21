package jp.co.isid.mos.bird.bizsupport.pllumpextract.dto;

import java.util.List;

import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;

public class  PlLumpExtractConditionDto implements CsvOutputDto{

    /**
     * ユーザタイプ
     */
    private String userInfoKbn;
    
    /**
     * ユーザ制限区分
     */
    private String userLimitKbn;
    
    /**
     * 決算月List
     */
    private List closingMonthList;
    
    /**
     * 決算月Str
     */
    private String closingMonthStr;
    
    /**
     * 対象月List
     */
    private List targetMonthList;
    
    /**
     * 対象月List(RC用)
     */
    private List targetMonthRcList;
    
    /**
     * 対象月Str
     */
    private String targetMonthStr;
    
    /**
     * 対象月Str(RC)
     */
    private String targetMonthStrRc;
    
    /**
     * 対象期間FROMList
     */
    private List periodMonthFromList;
    
    /**
     * 対象期間FROMList(RC用)
     */
    private List periodMonthFromRcList;
    
    /**
     * 対象期間FROMStr
     */
    private String periodMonthFromStr;
    
    /**
     * 対象期間FROMStr（RC)
     */
    private String periodMonthFromStrRc;
    
    /**
     * 対象期間TOList
     */
    private List periodMonthToList;
    
    /**
     * 対象期間TOList(RC用)
     */
    private List periodMonthToRcList;
    
    /**
     * 対象期間TOｓｔｒ
     */
    private String periodMonthToStr;
    
    /**
     * 対象期間ToStr(RC)
     */
    private String periodMonthToStrRc;
    /**
     * selectListIndex
     */
    private int selectListIndex;
    
    /**
     * オーナーコード
     */
    private String ownerCd;
    
    /**
     * 直営リスト
     */
    private List rcList;
    
    /**
     * 直営Str
     */
    private String rcStr;
    
    /**
     * 管理会社企業コード
     */
    private String companyCd;
    
    /**
     * 検索月
     *
     * */
    private String monthTypeFc;
    
    /**
     * データタイプ
     */
    private String dataTypeFc;
    
    /**
     * 出力先
     */
    private String outputTypeFc;
    
    /**
     * plMode
     */
    private String plMode;
    
    /**
     * 検索月
     *
     * */
    private String monthTypeRc;
    
    /**
     * データタイプ
     */
    private String dataTypeRc;
    
    /**
     * 出力先
     */
    private String outputTypeRc;
        
    /**
     * localFlg
     */
    private boolean localFlg;
    
    /**
     * limitFlg
     */
    private boolean limitFlg;
    
    /**
     * userId
     */
    private String userId;
    
    /**
     * onerList
     */
    private List onerList;
    
    /**
     * PL年月のMAX年月
     */
    private String plMaxMonth;
    
    /**
     * ユーザタイプを取得します。
     * @return ユーザタイプ
     */
    public String getUserInfoKbn() {
        return userInfoKbn;
    }
    /**
     * ユーザタイプを設定します。
     * @param userInfoKbn ユーザタイプ
     */
    public void setUserInfoKbn(String userInfoKbn) {
        this.userInfoKbn = userInfoKbn;
    }
    
    /**
     * ユーザ制限区分を取得します。
     * @return ユーザ制限区分
     */
    public String getUserLimitKbn() {
        return userLimitKbn;
    }
    /**
     * ユーザ制限区分を設定します。
     * @param userLimitKbn ユーザ制限区分
     */
    public void setUserLimitKbn(String userLimitKbn) {
        this.userLimitKbn = userLimitKbn;
    }
    
    /**
     * 決算月Listを取得します。
     * @return 決算月List
     */
    public List getClosingMonthList() {
        return closingMonthList;
    }
    /**
     * 決算月Listを設定します。
     * @param closingMonthList 決算月List
     */
    public void setClosingMonthList(List closingMonthList) {
        this.closingMonthList = closingMonthList;
    }
    
    /**
     * 決算月Strを取得します。
     * @return 決算月Str
     */
    public String getClosingMonthStr() {
        return closingMonthStr;
    }
    /**
     * 決算月Strを設定します。
     * @param closingMonthStr 決算月Str
     */
    public void setClosingMonthStr(String closingMonthStr) {
        this.closingMonthStr = closingMonthStr;
    }
    
    /**
     * 対象月Listを取得します。
     * @return 対象月List
     */
    public List getTargetMonthList() {
        return targetMonthList;
    }
    /**
     * 対象月Listを設定します。
     * @param targetMonthList 対象月List
     */
    public void setTargetMonthList(List targetMonthList) {
        this.targetMonthList = targetMonthList;
    }
    
    /**
     * 対象月List(RC用)を取得します。
     * @return 対象月List(RC用)
     */
    public List getTargetMonthRcList() {
        return targetMonthRcList;
    }
    /**
     * 対象月List(RC用)を設定します。
     * @param targetMonthList 対象月List(RC用)
     */
    public void setTargetMonthRcList(List targetMonthRcList) {
        this.targetMonthRcList = targetMonthRcList;
    }
    
    /**
     * 対象月Strを取得します。
     * @return 対象月Str
     */
    public String getTargetMonthStr() {
        return targetMonthStr;
    }
    /**
     * 対象月Strを設定します。
     * @param targetMonthStr 対象月Str
     */
    public void setTargetMonthStr(String targetMonthStr) {
        this.targetMonthStr = targetMonthStr;
    }
    
    /**
     * 対象月Str(RC) を取得します
     * @return
     */
    public String getTargetMonthStrRc() {
    	return this.targetMonthStrRc;
    }
    
    /**
     * 対象月Str(RC)を設定します。
     * @param targetMonthStrRc
     */
    public void setTargetMonthStrRc( String targetMonthStrRc) {
    	this.targetMonthStrRc = targetMonthStrRc;
    }
    
    /**
     * 対象期間FROMListを取得します。
     * @return 対象期間FROMList
     */
    public List getPeriodMonthFromList() {
        return periodMonthFromList;
    }
    /**
     * 対象期間FROMListを設定します。
     * @param periodMonthFromList 対象期間FROMList
     */
    public void setPeriodMonthFromList(List periodMonthFromList) {
        this.periodMonthFromList = periodMonthFromList;
    }
    
    /**
     * 対象期間FROMList(RC用)を取得します。
     * @return 対象期間FROMList(RC用)
     */
    public List getPeriodMonthFromRcList() {
        return periodMonthFromRcList;
    }
    /**
     * 対象期間FROMList(RC用)を設定します。
     * @param periodMonthFromRcList 対象期間FROMList(RC用)
     */
    public void setPeriodMonthFromRcList(List periodMonthFromRcList) {
        this.periodMonthFromRcList = periodMonthFromRcList;
    }
    
    /**
     * 対象期間FROMStrを取得します。
     * @return 対象期間FROMStr
     */
    public String getPeriodMonthFromStr() {
        return periodMonthFromStr;
    }
    /**
     * 対象期間FROMStrを設定します。
     * @param periodMonthFromStr 対象期間FROMStr
     */
    public void setPeriodMonthFromStr(String periodMonthFromStr) {
        this.periodMonthFromStr = periodMonthFromStr;
    }
    
    /**
     * 対象期間FROMStr(RC)を取得します。
     * @return
     */
    public String getPeriodMonthFromStrRc() {
    	return this.periodMonthFromStrRc;
    }
    
    /**
     *　対象期間FROMStr(RC)を設定します。 
     * @param periodMonthFromStrRc
     */
    public void setPeriodMonthFromStrRc( String periodMonthFromStrRc) {
    	this.periodMonthFromStrRc = periodMonthFromStrRc;
    }
    
    /**
     * 対象期間TOListを取得します。
     * @return 対象期間TOList
     */
    public List getPeriodMonthToList() {
        return periodMonthToList;
    }
    /**
     * 対象期間TOListを設定します。
     * @param periodMonthToList 対象期間TOList
     */
    public void setPeriodMonthToList(List periodMonthToList) {
        this.periodMonthToList = periodMonthToList;
    }
    
    /**
     * 対象期間TOList(RC用)を取得します。
     * @return 対象期間TOList(RC用)
     */
    public List getPeriodMonthToRcList() {
        return periodMonthToRcList;
    }
    /**
     * 対象期間TOList(RC用)を設定します。
     * @param periodMonthToRcList 対象期間TOList(RC用)
     */
    public void setPeriodMonthToRcList(List periodMonthToRcList) {
        this.periodMonthToRcList = periodMonthToRcList;
    }
    
    /**
     * 対象期間TOｓｔｒを取得します。
     * @return 対象期間TOｓｔｒ
     */
    public String getPeriodMonthToStr() {
        return periodMonthToStr;
    }
    
    /**
     * 対象期間TOｓｔｒを設定します。
     * @param periodMonthToStr 対象期間TOｓｔｒ
     */
    public void setPeriodMonthToStr(String periodMonthToStr) {
        this.periodMonthToStr = periodMonthToStr;
    }

    /**
     * 対象期間TOStr(RC)を取得します。
     * @return
     */
    public String getPeriodMonthToStrRc() {
    	return this.periodMonthToStrRc;
    }
    
    /**
     * 対象期間ToStr('RC)を設定します
     * @param periodMonthToStrRC
     */
    public void setPeriodMonthToStrRc( String periodMonthToStrRC) {
    	this.periodMonthToStrRc = periodMonthToStrRC;
    }
    /**
     * PLモードを取得します。
     * @return PLモード
     */
    public String getPlMode() {
        return plMode;
    }
    /**
     * PLモードを設定します。
     * @param plMode PLモード
     */
    public void setPlMode(String plMode) {
        this.plMode = plMode;
    }
    
    /**
     * selectListを取得します。
     * @return selectList
     */
    public int getSelectListIndex() {
        return selectListIndex;
    }
    /**
     * selectListを設定します。
     * @param selectListIndex selectList
     */
    public void setSelectListIndex(int selectListIndex) {
        this.selectListIndex = selectListIndex;
    }

   /**
    * ownerCdを取得します
    * @return
    */
    public String getOwnerCd() {
    	return this.ownerCd;
    }
    /**
     * ownerCdの設定
     * @param ownerCd
     */
    public void setOwnerCd( String ownerCd ) {
    	this.ownerCd = ownerCd;
    }
    
    /**
     * rclistの設定
     * @return
     */
    public List getRcList() {
    	return this.rcList;
    }
    
    /**
     * rcListの設定
     * @param rcList
     */
    public void setRcList( List rcList) {
        this.rcList = rcList;     
    }
    
    /**
     *  rcStrの設定
     * @return
     */
    public String getRcStr() {
    	return this.rcStr;
    }
    /**
     *  rcStrの設定
     * @param rcStr
     */
    public void setRcStr( String rcStr ) {
        this.rcStr = rcStr;
    }
    
    /**
     * companyCdの設定
     * @return
     */
    public String getCompanyCd() {
    	return this.companyCd;
    }

   /**
     * companyCdの設定
     * @param companyCd
     */
    public void setCompanyCd( String companyCd) {
    	this.companyCd = companyCd;
    }
    
    /**
     * monthType設定
     * @return
     */
    public String getMonthTypeFc() {
    	return this.monthTypeFc;
    }
    /**
     * monthType設定
     * @param monthType
     */
    public void setMonthTypeFc( String monthTypeFc ) {
    	this.monthTypeFc  = monthTypeFc;
    }

    /**
     * dataType設定 
     * @return dataType
     */
    public String getDataTypeFc() {
    	return this.dataTypeFc;
    }
    /**
     * dataType設定 
     * @param dataType
     */
    public void setDataTypeFc( String dataTypeFc) {
    	this.dataTypeFc = dataTypeFc;
    }
    
    /**
     * outputType設定
     * @param outputType
     */
    public void setOutputTypeFc( String outputTypeFc ) {
    	this.outputTypeFc = outputTypeFc;
    }
    /**
     * outputTyep設定
     * @return
     */
    public String getOutputTypeFc() {
    	return this.outputTypeFc;
    }
    
    /**
     * monthType設定
     * @return
     */
    public String getMonthTypeRc() {
        return this.monthTypeRc;
    }
    /**
     * monthType設定
     * @param monthType
     */
    public void setMonthTypeRc( String monthTypeRc ) {
        this.monthTypeRc  = monthTypeRc;
    }

    /**
     * dataType設定 
     * @return dataType
     */
    public String getDataTypeRc() {
        return this.dataTypeRc;
    }
    /**
     * dataType設定 
     * @param dataType
     */
    public void setDataTypeRc( String dataTypeRc) {
        this.dataTypeRc = dataTypeRc;
    }
    
    /**
     * outputType設定
     * @param outputType
     */
    public void setOutputTypeRc( String outputTypeRc ) {
        this.outputTypeRc = outputTypeRc;
    }
    /**
     * outputTyep設定
     * @return
     */
    public String getOutputTypeRc() {
        return this.outputTypeRc;
    }
    
    /**
     * localFlg設定
     * @return
     */
    public boolean getLocalFlg() {
    	return this.localFlg;
    }
    /**
     * localFlg設定
     * @param localFlg
     */
    public void setLocalFlg( boolean localFlg ) {
        this.localFlg = localFlg;        
    }
    
    /**
     * limitFlg設定
     * @return
     */
    public boolean getLimitFlg() {
    	return this.limitFlg;
    }
    
    /**
     * limitFlg設定
     * @param limitFlg
     */
    public void setLimitFlg( boolean limitFlg ) {
    	this.limitFlg = limitFlg;
    }
    
    /**
     * userId設定
     * @return
     */
    public String getUserId() {
    	return this.userId;
    }
    
    /**
     * userId設定
     * @param userId
     */
    public void setUserId( String userId) {
    	this.userId = userId;
    }
    
    /**
     * onerList設定
     * @return
     */
    public List getOnerList() {
        return this.onerList;
    }
    
    /**
     * onerList設定
     * @param onerLIst
     */
    public void setOnerList( List onerLIst) {
    	this.onerList = onerLIst;
    }
    
    /**
     * PL年月のMAX年月の設定
     * @return
     */
    public String getPlMaxMonth() {
        return this.plMaxMonth;
    }
    /**
     * PL年月のMAX年月の設定
     * @param plMaxMonth
     */
    public void setPlMaxMonth(String plMaxMonth) {
        this.plMaxMonth = plMaxMonth;
    }
    
    /**
     * Dtoのクリア
     *
     */
    public void clear() {
    	setClosingMonthList( null );
        setClosingMonthStr( null );
        setTargetMonthList( null );
        setTargetMonthRcList( null );
        setTargetMonthStr( null );
        setPeriodMonthFromList( null );
        setPeriodMonthFromRcList( null );
        setPeriodMonthFromStr( null );
        setPeriodMonthToList( null );
        setPeriodMonthToRcList( null );
        setPeriodMonthToStr( null );
        setPlMode( null );
        setSelectListIndex( 0 );
        setOwnerCd( null );
        setCompanyCd( null );
        setMonthTypeFc( null );
        setDataTypeFc( null );
        setMonthTypeRc( null );
        setDataTypeRc( null );
        setLocalFlg( false ) ;
        setLimitFlg( false ) ;
        setUserId( null ) ;
        setOnerList(null );
        setTargetMonthStrRc(null);
        setPeriodMonthFromStrRc(null);
        setPeriodMonthToStrRc(null);
        setPlMaxMonth(null);
    }
}
