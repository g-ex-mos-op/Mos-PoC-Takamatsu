/*
 * 作成日: 2006/08/04
 */
package jp.co.isid.mos.bird.bill.buyinglistview.dto;

import java.util.List;

import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.dto.DownloadDto;
import jp.co.isid.mos.bird.framework.util.DateManager;

/**
 * 請求書照会DTO
 *
 * @author xlee
 */
public class BuyingListViewDto implements DownloadDto,CsvOutputDto {

    private int downloadIndex=0;

	// ウィンドウID
	/**
     * ウィンドウID
     */
    private int windowId = 0;

    // 最大ウィンドウID
    /**
     * 最大ウィンドウID
     */
    private int maxWindowId = 0;

	/**
     * ユーザータイプ
     */
    private String userTypeCd;

    /**
     * オーナーコード
     */
    private String condOnerCd;

// delete 2023/02/14 USI範  begin
//    /**
//     * 対象期間リスト
//     */
//    private List condListTaishoKikan;
//
//
//    /**
//     * 対象期間
//     */
//    private String condTaishoKikanCd;
// delete 2023/02/14 USI範 end
    /**
     * ソート区分
     */
    private String condKbn;

    /**
     * オーナーコード
     */
    private String onerCd;

    /**
     *　会社コード
     */
    private String condCompanyCd;

    /**
     *　売掛コード
     */
    private String urikakeCd;

    /**
     *　請求ID
     */
    private String seikyushoId;

    /**
     *　年月
     */
    private String seikyuDt;
// add 2023/02/14 USI範   begin
    /**
     *　対象期間From
     */
    private String frDate;
    /**
     *　対象期間To
     */
    private String toDate;
    /**
     *　金額From
     */
    private String kinGaku;
    /**
     *　金額To
     */
    private String kinGakuTo;
 // add 2023/02/14 USI範  end
//add 2023/04/27 USI金 begin
    /**
     * システム日付
     */
    private String sysDate;
//add 2023/04/27 USI金 end
    /**
     * お買上詳細照会データリスト
     */
    private List buyingDataList;

    /**
     * オーナー店舗数
     */
    private int onerMiseCnt = 0;
    /**
     * 連続実行制限店舗数
     */
    private int limitCnt = 0;
//    /** 請求書一覧リスト */
//    private List seikyuList;
    /**
     * 初期化する
     */
	public void clear(){
		setUserTypeCd(null);
		setCondOnerCd(null);
// delete 2023/02/14 USI範  begin
//		setListCondTaishoKikan(null);
// delete 2023/02/14 USI範  end

// add 2023/02/14 USI範  begin
        setfrDate(null);
        settoDate(null);
        setKinGaku(null);
        setKinGakuTo(null);
// add 2023/02/14 USI範  end
// delete 2023/02/14 USI範 begin
//		setCondTaishoKikanCd(null);
// delete 2023/02/14 USI範  end
		setCondKbn(null);
		setOnerCd(null);
		setCondCompanyCd(null);
		setBuyingDataList(null);
	}
// delete 2023/02/14 USI範  begin
//    /**
//     * 対象期間リストを設定します。
//     * @param condSeikyuSakiList お買上明細
//     */
//    public void setListCondTaishoKikan(List condListTaishoKikan) {
//        this.condListTaishoKikan = condListTaishoKikan;
//    }
//    /**
//     * 条件画面 対象期間　対象条件プルダウン用List取得処理
//     * @return　対象条件プルダウン用Listリスト
//     */
//    public List getListCondTaishoKikan() {
//
//        return condListTaishoKikan;
//    }
//
//    /**
//     * 選択された対象期間を設定します。
//     * @param condTaishoKikanCd 対象期間
//     */
//    public void setCondTaishoKikanCd(String condTaishoKikanCd) {
//        this.condTaishoKikanCd = condTaishoKikanCd;
//    }
//
//    /**
//     * 選択された対象期間を取得します。
//     * @return　選択された対象期間
//     */
//    public String getCondTaishoKikanCd() {
//
//        return condTaishoKikanCd;
//    }
// delete 2023/02/14 USI範  end
// add 2023/02/14 USI範 begin
	//システム14カ月以上前のYYYYMM日付取得
    public String getSystemMonth() throws Exception {
//modify 2023/04/27 USI金 begin
//    	Date imajikan = new Date();
//    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM", Locale.JAPAN);
//    	String ret = "";
//    	synchronized (sdf) {
//    		sdf.setLenient(true);
//    		ret = DateManager.getPrevMonth((sdf.format(imajikan)),13);
//    	}
    	String ret = "";
    	ret = DateManager.getPrevMonth(getSysDate().substring(0,6),14);
//modify 2023/04/27 USI金 end
    	return ret;
    }
    //SQL検索用　対象年月From
    public String getfrDate() {
        return frDate;
    }
    public void setfrDate(String frDate) {
        this.frDate = frDate;
    }
    //SQL検索用　対象年月To
    public String gettoDate() {
        return toDate;
    }
    public void settoDate(String toDate) {
        this.toDate = toDate;
    }
    //SQL検索用　金額From
    public String getKinGaku() {
        return kinGaku;
    }
    public void setKinGaku(String KinGaku) {
        this.kinGaku = KinGaku;
    }
    //SQL検索用　金額To
    public String getKinGakuTo() {
        return kinGakuTo;
    }
    public void setKinGakuTo(String kinGakuTo) {
        this.kinGakuTo = kinGakuTo;
    }
// add 2023/02/14 USI範  end
//add 2023/04/27 USI金 begin
    /**
	 * @return sysDate を戻します。
	 */
	public String getSysDate() {
		return sysDate;
	}
	/**
	 * @param sysDate を クラス変数sysDateへ設定します。
	 */
	public void setSysDate(String sysDate) {
		this.sysDate = sysDate;
	}
//add 2023/04/27 USI金 end
    /**
     * お買上明細リストのサイズを設定します。
     * @param お買上明細のサイズ
     */
	public int getBuyingDataListSize(){
		if(buyingDataList == null){
			return 0;
		}else{
			return buyingDataList.size();
		}
	}

    /**
     * ユーザータイプを設定します。
     * @return ユーザータイプ
     */
    public String getUserTypeCd() {
        return userTypeCd;
    }

    /**
     * ユーザータイプをを設定します。
     * @param userTypeCd ユーザータイプ
     */
    public void setUserTypeCd(String userTypeCd) {
        this.userTypeCd = userTypeCd;
    }

    /**
     * オーナーコードを取得します
     * @return オーナーコード
     */
    public String getOnerCd() {
        return onerCd;
    }

    /**
     * オーナーコードをを設定します。
     * @param onerCd オーナーコード
     */
    public void setOnerCd(String onerCd) {
        this.onerCd = onerCd;
    }

    /**
     * 会社コードを取得します。
     * @return 会社コード
     */
    public String getCondCompanyCd() {
		//企業コード固定 (株式会社モスフードサービス)
    	condCompanyCd = "00";
		return condCompanyCd;
    }

    /**
     * 会社コードを設定します。
     * @param condCompanyCd 会社コード
     */
    public void setCondCompanyCd(String condCompanyCd) {
        this.condCompanyCd = condCompanyCd;
    }

    /**
     * ソート区分の初期値を設定します。
     * @return ソート区分の初期値
     */
    public String getCondKbn() {
        return condKbn;
    }

    /**
     * ソート区分の初期値を設定します。
     * @param condKbn ソート区分の初期値
     */
    public void setCondKbn(String condKbn) {
        this.condKbn = condKbn;
    }

    /**
     * お買上明細リストを取得します。
     * @return お買上明細リスト
     */
    public List getBuyingDataList() {
        return buyingDataList;
    }

    /**
     * お買上リストを設定します。
     * @param buyingDataList　お買上明細リスト
     */
    public void setBuyingDataList(List buyingDataList) {
        this.buyingDataList = buyingDataList;
    }

    /**
     * 検索条件：オーナーコードを取得します。
     * @return オーナーコード
     */
	public String getCondOnerCd() {
        return condOnerCd;
	}

	/**
     *  検索条件：オーナーコードを設定します。
     * @param 　condOnerCd オーナーコード
     */
	public void setCondOnerCd(String condOnerCd) {
        this.condOnerCd = condOnerCd;
	}

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

    // ウィンドウID生成
    /**
     * ウィンドウIDを生成します。
     */
    public int createWindowId() {
        int newWindowId = getMaxWindowId() + 1;
        setMaxWindowId(newWindowId);
        return newWindowId;
    }

    /**
     * ウィンドウIDを更新します。
     */
    public void updateWindowid() {
        setWindowId(createWindowId());
    }

    /**
     * @param downloadIndex downloadIndex を設定。
     */
    public void setDownloadIndex(int downloadIndex) {
        this.downloadIndex = downloadIndex;
    }

    /**
     * @return downloadIndex を戻します。
     */
    public int getDownloadIndex() {
        return downloadIndex;
    }


    /**
    * 請求書一覧リストを取得します。
    * @return 請求書一覧リスト
    */
    public String getSeikyushoId() {
    	return seikyushoId;
    }
	/**
	 * 請求書一覧リストを設定します。
	 * @param 請求書一覧リスト
	 */
    public void setSeikyushoId(String seikyushoId) {
    	this.seikyushoId = seikyushoId;
    }

    /**
     * 請求書一覧リストを取得します。
     * @return 請求書一覧リスト
     */
    public String getUrikakeCd() {
    	return urikakeCd;
    }
    /**
     * 請求書一覧リストを設定します。
     * @param 請求書一覧リスト
     */
    public void setUrikakeCd(String urikakeCd) {
    	this.urikakeCd = urikakeCd;
    }

    /**
     * 請求書一覧リストを取得します。
     * @return 請求書一覧リスト
     */
    public String getSeikyuDt() {
    	return seikyuDt;
    }
    /**
     * 請求書一覧リストを設定します。
     * @param 請求書一覧リスト
     */
    public void setSeikyuDt(String seikyuDt) {
    	this.seikyuDt = seikyuDt;
    }

    public int getOnerMiseCnt() {
        return onerMiseCnt;
    }

    public void setOnerMiseCnt(int onerMiseCnt) {
        this.onerMiseCnt = onerMiseCnt;
    }

    public int getLimitCnt() {
        return limitCnt;
    }

    public void setLimitCnt(int limitCnt) {
        this.limitCnt = limitCnt;
    }
}
