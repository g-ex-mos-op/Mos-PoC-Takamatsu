package jp.co.isid.mos.bird.bizsupport.moschickenstateconfirm.dto;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.common.dto.MosChickenDto;

/**
 * 予約・在庫状況確認 DTO
 * 
 * モスチキン用の画面のDTOです。
 * 
 * @author xkinu
 *
 */
public class MosChickenStateConfirmDto extends MosChickenDto {
    /* 対象オーナコード  */
    private String targetOnerCd;
    /* 対象オーナ名称  */
    private String targetOnerName;
    /* 対象管理番号 */
    private String targetCkanriNo;
    /* 対象タイトル */
    private String targetTitle;
    /* 対象店舗コード */
    private String targetMiseCd;
    /* 対象店舗名称 */
    private String targetMiseNameKj;
    /* 対象日付 */
    private String targetDate;
//add 2019/08/12 USI張 begin
    /* 対象食包材コード  */
    private String targetShokuCd;
    /* 対象食包材名称カナ(半角)  */
    private String targetShokuNameKna;
//add 2019/08/12 USI張 end
    /* 計算対象管理番号 */
    private String regTargetCkanriNo;
    /* 計算対象店舗コード */
    private String regTargetMiseCd;
    /* 計算対象日付 */
    private String regTargetDate;
//add 2019/08/12 USI張 begin
    /* 計算対象食包材 */
    private String regTargetshokuCd;
//add 2019/08/12 USI張 end
    /* 条件項目：[[タイトル]] */
    private List listTitle;
    /* 条件項目：[[対象店舗]] */
    private List listOnerMise;
    /* 条件項目：[[対象日付]] */
    private List listDate;
//add 2019/08/12 USI張 begin
    /* 条件項目：[[食包材]] */
    private List listShokuhou;
//add 2019/08/12 USI張 end
    /* 条件項目：[[予約・在庫状況情報]] */
    private List listSearchData;
    /* オナー選択済みフラグ */
    private boolean onerSelectFlg = false;
    /* 条件項目：[[タイトル]] */
    private boolean title1CntFlg = false;
    /* 条件項目：[[対象店舗]] */
    private boolean onerMise1CntFlg = false;
    /* 条件項目：[[対象日付]] */
    private boolean date1CntFlg = false;
//add 2019/08/12 USI張 begin
    /* 条件項目：[[食包材]] */
    private boolean shokuhouCntFlg = false;
//add 2019/08/12 USI張 end
    /* 判断フラグ：JSF実行ボタン無効フラグ */
    private boolean execDisabledFlg = false;
    
    /**
     * @return date1CntFlg を戻します。
     */
    public boolean isDate1CntFlg() {
        return date1CntFlg;
    }
    /**
     * @param date1CntFlg 設定する date1CntFlg。
     */
    public void setDate1CntFlg(boolean date1CntFlg) {
        this.date1CntFlg = date1CntFlg;
    }
    /**
     * @return onerMise1CntFlg を戻します。
     */
    public boolean isOnerMise1CntFlg() {
        return onerMise1CntFlg;
    }
    /**
     * @param onerMise1CntFlg 設定する onerMise1CntFlg。
     */
    public void setOnerMise1CntFlg(boolean onerMise1CntFlg) {
        this.onerMise1CntFlg = onerMise1CntFlg;
    }
    /**
     * @return title1CntFlg を戻します。
     */
    public boolean isTitle1CntFlg() {
        return title1CntFlg;
    }
    /**
     * @param title1CntFlg 設定する title1CntFlg。
     */
    public void setTitle1CntFlg(boolean title1CntFlg) {
        this.title1CntFlg = title1CntFlg;
    }
    /**
     * 対象オーナーコード取得処理
     * 
     * @return
     */
    public String getTargetOnerCd() {
        return targetOnerCd;
    }
    /**
     * 対象オーナーコード設定処理
     * 
     * @param targetOnerCd
     */
    public void setTargetOnerCd(String targetOnerCd) {
        this.targetOnerCd = targetOnerCd;
    }
    /**
     * 対象オーナー名称取得処理
     * 
     * @return
     */
    public String getTargetOnerName() {
        return targetOnerName;
    }
    /**
     * 対象オーナー名称設定処理
     * 
     * @param targetOnerName
     */
    public void setTargetOnerName(String targetOnerName) {
        this.targetOnerName = targetOnerName;
    }
    /**
     * 対象管理番号取得処理
     * 
     * @return String
     */
    public String getTargetCkanriNo() {
        return targetCkanriNo;
    }
    /**
     * 対象管理番号設定処理
     * 
     * @param targetCkanriNo
     */
    public void setTargetCkanriNo(String targetCkanriNo) {
        this.targetCkanriNo = targetCkanriNo;
    }
    /**
     * 対象タイトル取得処理
     * 
     * @return
     */
    public String getTargetTitle() {
        return targetTitle;
    }
    /**
     * 対象タイトル設定処理
     * 
     * @param targetTitle
     */
    public void setTargetTitle(String targetTitle) {
        this.targetTitle = targetTitle;
    }
    /**
     * 対象店舗コード取得処理
     * 
     * @return
     */
    public String getTargetMiseCd() {
        return targetMiseCd;
    }
    /**
     * 対象店舗コード設定処理
     * 
     * @param targetMiseCd
     */
    public void setTargetMiseCd(String targetMiseCd) {
        this.targetMiseCd = targetMiseCd;
    }
    /**
     * 対象店舗名称取得処理
     * 
     * @return
     */
    public String getTargetMiseNameKj() {
        return targetMiseNameKj;
    }
    /**
     * 対象店舗名称設定処理
     * 
     * @param targetMiseNameKj
     */
    public void setTargetMiseNameKj(String targetMiseNameKj) {
        this.targetMiseNameKj = targetMiseNameKj;
    }
    /**
     * 対象日付取得処理
     * 
     * @return
     */
    public String getTargetDate() {
        return targetDate;
    }
    /**
     * 対象日付設定処理
     * 
     * @param targetDate
     */
    public void setTargetDate(String targetDate) {
        this.targetDate = targetDate;
    }
    /**
     * クリア処理
     *
     */
    public void clear(){
        //条件項目値クリア
        clearConditionData();
        setTargetOnerCd(null);
        setTargetOnerName(null);
        setListOnerMise(null);
        setListDate(null);
//add 2019/08/12 USI張 begin
        setListShokuhou(null);
//add 2019/08/12 USI張 end
        setListSearchData(null);
        setExecDisabledFlg(false);
   }
    /**
     * 条件項目値クリア処理
     *
     */
    public void clearConditionData(){
        setTargetCkanriNo(null);
        setTargetTitle(null);
        setTargetMiseCd(null);
        setTargetMiseNameKj(null);
        setTargetDate(null);
//add 2019/08/12 USI張 begin
        setTargetShokuCd(null);
        setTargetShokuNameKna(null);
//add 2019/08/12 USI張 end
    }
    /**
     * @return listDate を戻します。
     */
    public List getListDate() {
        return listDate;
    }
    /**
     * @param listDate 設定する listDate。
     */
    public void setListDate(List listDate) {
        this.listDate = listDate;
        setDate1CntFlg((listDate != null && listDate.size() == 1));
    }
    /**
     * @return listOnerMise を戻します。
     */
    public List getListOnerMise() {
        return listOnerMise;
    }
    /**
     * @param listOnerMise 設定する listOnerMise。
     */
    public void setListOnerMise(List listOnerMise) {
        this.listOnerMise = listOnerMise;
        setOnerMise1CntFlg((listOnerMise != null && listOnerMise.size() == 1));
    }
    /**
     * @return listSearchData を戻します。
     */
    public List getListSearchData() {
        return listSearchData;
    }
    /**
     * @param listSearchData 設定する listSearchData。
     */
    public void setListSearchData(List listSearchData) {
        this.listSearchData = listSearchData;
    }
    /**
     * @return listTitle を戻します。
     */
    public List getListTitle() {
        return listTitle;
    }
    /**
     * @param listTitle 設定する listTitle。
     */
    public void setListTitle(List listTitle) {
        this.listTitle = listTitle;
        setTitle1CntFlg((listTitle != null && listTitle.size() == 1));
    }
    /**
     * @return onerSelectFlg を戻します。
     */
    public boolean isOnerSelectFlg() {
        return onerSelectFlg;
    }
    /**
     * @param onerSelectFlg 設定する onerSelectFlg。
     */
    public void setOnerSelectFlg(boolean onerSelectFlg) {
        this.onerSelectFlg = onerSelectFlg;
    }
    /**
     * @return execDisabledFlg を戻します。
     */
    public boolean isExecDisabledFlg() {
        return execDisabledFlg;
    }
    /**
     * @param execDisabledFlg 設定する execDisabledFlg。
     */
    public void setExecDisabledFlg(boolean execDisabledFlg) {
        this.execDisabledFlg = execDisabledFlg;
    }
    /**
     * @return regTargetDate を戻します。
     */
    public String getRegTargetDate() {
        return regTargetDate;
    }
    /**
     * @param regTargetDate 設定する regTargetDate。
     */
    public void setRegTargetDate(String regTargetDate) {
        this.regTargetDate = regTargetDate;
    }
    /**
     * @return regTargetCkanriNo を戻します。
     */
    public String getRegTargetCkanriNo() {
        return regTargetCkanriNo;
    }
    /**
     * @param regTargetCkanriNo 設定する regTargetCkanriNo。
     */
    public void setRegTargetCkanriNo(String regTargetCkanriNo) {
        this.regTargetCkanriNo = regTargetCkanriNo;
    }
    /**
     * @return regTargetMiseCd を戻します。
     */
    public String getRegTargetMiseCd() {
        return regTargetMiseCd;
    }
    /**
     * @param regTargetMiseCd 設定する regTargetMiseCd。
     */
    public void setRegTargetMiseCd(String regTargetMiseCd) {
        this.regTargetMiseCd = regTargetMiseCd;
    }
//modify 2019/08/12 USI張 begin
    /**
     * @return targetShokuCd を戻します。
     */
    public String getTargetShokuCd() {
        return targetShokuCd;
    }
    /**
     * @param targetShokuCd 設定する targetShokuCd。
     */
    public void setTargetShokuCd(String targetShokuCd) {
        this.targetShokuCd = targetShokuCd;
    }
    /**
     * @return targetShokuNameKna を戻します。
     */
    public String getTargetShokuNameKna() {
        return targetShokuNameKna;
    }
    /**
     * @param targetShokuNameKna 設定する targetShokuNameKna。
     */
    public void setTargetShokuNameKna(String targetShokuNameKna) {
        this.targetShokuNameKna = targetShokuNameKna;
    }

	/**
	 * shokuhouCntFlgを取得します。
	 * @return shokuhouCntFlg
	 */
	public boolean isShokuhouCntFlg() {
		return shokuhouCntFlg;
	}
	/**
	 * shokuhouCntFlgを設定します。
	 * @param shokuhouCntFlg
	 */
	public void setShokuhouCntFlg(boolean shokuhouCntFlg) {
		this.shokuhouCntFlg = shokuhouCntFlg;
	}

	/**
	 * listShokuhouを取得します。
	 * @return listShokuhou
	 */
	public List getListShokuhou() {
		return listShokuhou;
	}
	/**
	 * listShokuhouを設定します。
	 * @param listShokuhou
	 */
	public void setListShokuhou(List listShokuhou) {
		this.listShokuhou = listShokuhou;
		setShokuhouCntFlg((listShokuhou != null && listShokuhou.size() == 1));
	}
	/**
	 * regTargetshokuCdを取得します。
	 * @return regTargetshokuCd
	 */
	public String getRegTargetshokuCd() {
		return regTargetshokuCd;
	}
	/**
	 * regTargetshokuCdを設定します。
	 * @param regTargetshokuCd
	 */
	public void setRegTargetshokuCd(String regTargetshokuCd) {
		this.regTargetshokuCd = regTargetshokuCd;
	}

//modify 2019/08/12 USI張 end


}
