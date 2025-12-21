package jp.co.isid.mos.bird.bizsupport.moschickenrefconflist2.dto;

import java.util.List;

import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PdfOutputDto;

/**
 * 販売予約管理数DTO
 * 
 * @author inazawa
 *
 */
public class MosChickenRefConfListDto implements PdfOutputDto {
    //-----
    //複数Window対応
    //-----
//    /**
//     * ウィンドウID
//     */
//    private int windowId = 0;
//    /**
//     * 最大ウィンドウID
//     */
//    private int maxWindowId = 0;
    /**
     * 管理会社
     */
    private String companyCd;
    /**
     * お渡し日
     */
    private String reserveDate;
    /**
     * お渡し時間
     */
    private String reserveHourFrom;
    /**
     * お渡し時間
     */
    private String reserveHourTo;
    /**
     * タイトル
     */
    private String title;
    /**
     * 備考
     */
    private String bikou;
    private String miseCdFrom;
    private String miseCdTo;
    
    /* PDFファイル名 */
    private String fileName;
    /**
     * メモ
     */
    private String memo;
    /**
     * 代金済みフラグ
     * 0:未払い
     * 1:代済み
     */
    private String paymentFlg;
    /**
     * 店コード
     */
    private String miseCd;
    /**
     * 店名称
     */
    private String miseNmKj;
    /**
     * キャンセルフラグ
     * 0:未1:済2:新規
     */
    private String cancelFlg;
    /**
     * シーケンスNO
     */
    private int seqNo;
    /**
     * 登録マスタ商品数
     */
    private int mstMenuCont;
    /**
     * PDFタイプ
     */
    private String pdfType;
    /* 検索条件：管理番号 */
    private String ckanriNo;
    /* 検索結果：[[モスチキン予約情報]] */
    private List listMosChiCkenInfo;
    /* 検索結果：[[モスチキン予約明細情報]] */
    private List listMosChiCkenDetailInfo;
    /* ログイン情報 */
    private BirdUserInfo birdUserInfo;
    private String premiumFlg= "";

    /**
     * @return listMosChiCkenInfo を戻します。
     */
    public List getListMosChiCkenInfo() {
        return listMosChiCkenInfo;
    }

    /**
     * @param listMosChiCkenInfo 設定する listMosChiCkenInfo。
     */
    public void setListMosChiCkenInfo(List listMosChiCkenInfo) {
        this.listMosChiCkenInfo=listMosChiCkenInfo;
    }

    /**
     * @return listMosChiCkenDetailInfo を戻します。
     */
    public List getListMosChiCkenDetailInfo() {
        return listMosChiCkenDetailInfo;
    }

    /**
     * @param listMosChiCkenDetailInfo 設定する listMosChiCkenDetailInfo。
     */
    public void setListMosChiCkenDetailInfo(List listMosChiCkenDetailInfo) {
        this.listMosChiCkenDetailInfo=listMosChiCkenDetailInfo;
    }

    /**
     * @return ckanriNo を戻します。
     */
    public String getCkanriNo() {
        return ckanriNo;
    }

    /**
     * @param ckanriNo 設定する ckanriNo。
     */
    public void setCkanriNo(String ckanriNo) {
        this.ckanriNo = ckanriNo;
    }
    /**
     * クリア処理
     *
     */
    public void clear() {
        setCkanriNo(null);
        setListMosChiCkenDetailInfo(null);
        setListMosChiCkenInfo(null);
        setMiseCd(null);
        setRCompanyCd(null);
        setReserveDate(null);
        setPremiumFlg("");
        setPaymentFlg("");
       
    }

    /**
     * @return miseCd を戻します。
     */
    public String getMiseCd() {
        return miseCd;
    }

    /**
     * @param menuCd 設定する menuCd。
     */
    public void setMiseCd(String miseCd) {
        this.miseCd = miseCd;
    }



    /**
     * companyCdを戻す
     * @return
     */
    public String getCompanyCd() {
        return companyCd;
    }
    /**
     * companyCdを設定
     * @param companyCd
     */
    public void setRCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }


    /**
     * reserveDateを渡す
     * @return reserveDate
     */
    public String getReserveDate() {
        return reserveDate;
    }
    /**
     * reserveDateを設定
     * @param reserveDate
     */
    public void setReserveDate(String reserveDate) {
        this.reserveDate = reserveDate;
    }

    /**
     * titleを戻す
     * @return title
     */
    public String getTitle() {
        return title;
    }
    /**
     * titleを設定
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * miseNmKjを戻す
     * @return miseNmKj
     */
    public String getMiseNmKj() {
        return miseNmKj;
    }
    /**
     * miseNmKを設定
     * @param miseNmKj
     */
    public void setMiseNmKj(String miseNmKj) {
        this.miseNmKj = miseNmKj;
    }
    /**
     * reserveHourFromを戻す
     * @return reserveHourFrom
     */
    public String getReserveHourFrom() {
        return reserveHourFrom;
    }
    /**
     * reserveHourFromを設定
     * @param reserveHourFrom
     */
    public void setReserveHourFrom(String reserveHourFrom) {
        this.reserveHourFrom = reserveHourFrom;
    }
    /**
     * reserveHourToを戻す
     * @return reserveHourTo
     */
    public String getReserveHourTo() {
        return reserveHourTo;
    }
    /**
     * reserveHourToを設定
     * @param reserveHourTo
     */
    public void setReserveHourTo(String reserveHourTo) {
        this.reserveHourTo = reserveHourTo;
    }
    /**
     * bikouを戻す
     * @return bikou
     */
    public String getBikou() {
        return bikou;
    }
    /**
     * bikouを返す
     * @param bikou
     */
    public void setBikou(String bikou) {
        this.bikou=bikou;
    }
    /**
     * memoを戻す
     * @return memo
     */
    public String getMemo() {
        return memo;
    }
    /**
     * memoを返す
     * @param memo
     */
    public void setMemo(String memo) {
        this.memo=memo;
    }
    /**
     * paymentFlgを戻す
     * @return paymentFlg
     */
    public String getPaymentFlg() {
        return paymentFlg;
    }
    /**
     * paymentFlgを返す
     * @param paymentFlg
     */
    public void setPaymentFlg(String paymentFlg) {
        this.paymentFlg=paymentFlg;
    }
    /**
     * cancelFlgを戻す
     * @return cancelFlg
     */
    public String getCancelFlg() {
        return cancelFlg;
    }
    /**
     * cancelFlgを設定
     * @param cancelFlg
     */
    public void setCancelFlg(String cancelFlg) {
        this.cancelFlg=cancelFlg;
    }

    /**
     * BIRDログイン情報の設定
     * @return birdUserInfo を戻します。
     */
    public BirdUserInfo getBirdUserInfo() {
        return birdUserInfo;
    }
    /**
     * BIRDログイン情報の設定
     * @param birdUserInfo birdUserInfo を設定。
     */
    public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
        this.birdUserInfo = birdUserInfo;
    }
    /**
     * seqNoを返す
     * @return seqNo
     */
    public int getSeqNo() {
        return seqNo;
    }
    /**
     * seqNoを設定
     * @param seqNo
     */
    public void setSeqNo(int seqNo) {
        this.seqNo = seqNo;
    }
    /**
     * getFileNameを取得
     * @return fileName
     */
    public String getFileName() {
        return fileName;
    }
    /**
     * getFileNameを設定
     * @param fileName
     */
    public void setFileName(String fileName) {
        this.fileName=fileName;
    }
    /**
     * pdfTypeを取得
     * @return pdfType
     */
    public String getPdfType() {
        return pdfType;
    }
    /**
     * pdfTypeを設定
     * @param pdfType
     */
    public void setPdfType(String pdfType) {
        this.pdfType=pdfType;
    }
    /**
     * mstMenuContを取得
     * @return mstMenuCont
     */
    public int getMstMenuCont() {
        return mstMenuCont;
    }
    /**
     * mstMenuContを設定
     * @param mstMenuCont
     */
    public void setMstMenuCont(int mstMenuCont) {
        this.mstMenuCont = mstMenuCont;
    }

    /**
     * @return premiumFlg を戻します。
     */
    public String getPremiumFlg() {
        return premiumFlg;
    }

    /**
     * @param premiumFlg 設定する premiumFlg。
     */
    public void setPremiumFlg(String premiumFlg) {
        this.premiumFlg = premiumFlg;
    }

    public String getMiseCdFrom() {
        return miseCdFrom;
    }

    public void setMiseCdFrom(String miseCdFrom) {
        this.miseCdFrom = miseCdFrom;
    }

    public String getMiseCdTo() {
        return miseCdTo;
    }

    public void setMiseCdTo(String miseCdTo) {
        this.miseCdTo = miseCdTo;
    }

//    //-----
//    //複数Window対応
//    //-----
//    /**
//     * ウィンドウIDを取得します。
//     * @return　ウィンドウID
//     */
//    public int getWindowId() {
//        return windowId;
//    }
//    
//    /**
//     * ウィンドウIDを設定します。
//     * @param windowId　ウィンドウID
//     */
//    public void setWindowId(int windowId) {
//        this.windowId = windowId;
//    }

//    /**
//     * 最大ウィンドウIDを取得します。
//     * @return 最大ウィンドウID
//     */
//    public int getMaxWindowId() {
//        return maxWindowId;
//    }
//    
//    /**
//     * 最大ウィンドウIDを設定します。
//     * @param maxWindowId　最大ウィンドウID
//     */
//    public void setMaxWindowId(int maxWindowId) {
//        this.maxWindowId = maxWindowId;
//    }
//    
//    // ウィンドウID生成
//    /**
//     * ウィンドウIDを生成します。
//     */
//    public int createWindowId() {
//        int newWindowId = getMaxWindowId() + 1;
//        setMaxWindowId(newWindowId);
//        return newWindowId;
//    }
//    
//    /**
//     * ウィンドウIDを更新します。
//     */
//    public void updateWindowid() {
//        setWindowId(createWindowId());
//    }


}
