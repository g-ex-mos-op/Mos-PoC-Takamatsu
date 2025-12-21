package jp.co.isid.mos.bird.bizsupport.common.dto;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.bizsupport.lumptakein.entity.PlDataErrorInfo;
import jp.co.isid.mos.bird.bizsupport.lumptakein.entity.TrnPLInfo;

//import jp.co.isid.mos.bird.bizsupport.common.entity.TrnPLInfo;
//import jp.co.isid.mos.bird.bizsupport.common.entity.PlDataErrorInfo;
import jp.co.isid.mos.bird.bizsupport.plregist.entity.MSTUserOnerInfo;
import jp.co.isid.mos.bird.bizsupport.plregist.entity.TrnPLAuthorInfo;
import jp.co.isid.mos.bird.bizsupport.plregist.entity.TrnPosZenUriage;
import jp.co.isid.mos.bird.bizsupport.plregist.entity.TrnZenPLInfo;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;



/**
 * P/Lデータ入力用Dto<br>
 * @author itamoto
 */
public class PlRegistDto {
    
    /* VIEW_ID */
    public final String VIEW_ID            = "BBS001";
    public final String select_VIEW_ID     = "BBS001V01";
    public final String edit_VIEW_ID       = "BBS001V02";
    public final String confirm_VIEW_ID    = "BBS001V03";
    public final String onerSearch_VIEW_ID = "BCO006V01";
    public final String stateConfirm_VIEW_ID = "BBS014V01";

    // VIEW_ID(操作エラー)
    public final String operationErr_VIEW_ID = "operation.Err";
    
    
    // 対象年月表示範囲設定
    private static int NENGETU_DISPLAY_MONTH = 25;
    
    // 編集中確認メッセージ
    private static final String EDIT_CONFIRM_MSG = "編集中のデータはクリアされます。よろしいですか？";
    
    /* セッションKey */
    private String sessionKey;

    /* PLデータCSV一括取込画面より遷移判定フラグ */
    private boolean plCsvFlg;
    /* P/Lデータ情報 */
    private TrnPLInfo trnPLInfo;
    /* P/L作成者情報 決算月 */
    private String kessanDt;
    
    /* 編集画面初期処理判定フラグ */
    private boolean initFlg;
    
    /* エラーチェック or ワーニングチェック フラグ( true:エラーチェック false:ワーニング ) */
    private boolean errSwitchFlg;

    
    /* PLデータCSV一括取込画面→P/Lデータ登録後の処理フラグ */
    private boolean plCsvAfterCommitExeFlg;
    
    /* 対象年月リスト */
    private List targetYMList;

    /* 条件画面表示モード デフォルト:0、オーナ選択:1 */
    private int selectViewMode;
    /* ユーザタイプコード */
    private String userTypeCd;
    
    /* オーナコード */
    private String onerCd;
    /* オーナコードTEMP */
    private String onerCdTmp;
    
    /* 対象年月 */
    private String targetYM;
    /* 対象年月(セレクト用) */
    private String targetYMSelectBox;
    /* 店舗P/L */
    private String storePlMiseCd;
    /* P/L選択値 (店舗P/L：0、本社P/L：1) */
    private int plFlg;
    /* 店舗P/Lリスト */
    private List storePlList;
    /* 本社P/L 店コード */
    private String headOfficePlOnerCd;
    /* 本社P/L 店名称 */
    private String headOfficePlOnerName;
    /* 店舗クローズ日 */
    private String miseCloseDate;

    /* ユーザ対応オーナー情報 */
    private MSTUserOnerInfo msTUserOnerInfo;
    /* ユーザ対応店舗情報 */
    private List msTUserMiseInfoList;
    /* P/L作成者情報 */
    private TrnPLAuthorInfo trnPLAuthorInfo;

    /* 前月PLデータ情報 */
    private TrnZenPLInfo trnZenPLInfo;
    /* POS前月売上 */
    private TrnPosZenUriage TrnPosZenUriage;
    
    /* 売上高比率 */
    private BigDecimal uriagedakaRatio;
    /* 売上原価比率 */
    private BigDecimal uriagegenkaRatio;
    /* 売上総利益比率 */
    private BigDecimal uriageSoRiekiRatio;
    
    /* 売上総利益（参考）*/
    private BigDecimal uriageSoRiekiReference;
    /* 経費小計（参考）*/
    private BigDecimal keihiShokeiReference;
    /* 償却前利益（参考）*/
    private BigDecimal shokyakuRiekiReference;
    /* 当月利益（参考）*/
    private BigDecimal riekiReference;
    
    
    /* 内訳 売上高計 */
    private BigDecimal uriageDakaReference;
    /* 内訳 水道光熱費計 */
    private BigDecimal suidokonetsuhiReference;
    
    /* 内訳 売上原価計 前月在庫(43a) */
//    private BigDecimal uriageGenkaZenZaikoReference;
    /* 内訳 売上原価計 当月仕入(43b) */
    private BigDecimal uriageGenkaTogetsuShiireReference;
    /* 内訳 売上原価計 当月在庫(43c) */
    private BigDecimal uriageGenkaTogetsuZaikoReference;
    /* 内訳 売上原価計 差引売上原価(43d) */
//    private BigDecimal uriageGenkaSashihikiUriageReference;
    
    /* 内訳 原材料 差引売上原価(39d) */
    private BigDecimal sashihikiGenkaGenzairyoReference;
    /* 内訳 野菜 差引売上原価(40d) */
    private BigDecimal sashihikiGenkaYasaiReference;
    /* 内訳 包材 差引売上原価(41d) */
    private BigDecimal sashihikiGenkaHouzaiReference;
    /* 内訳 物販 差引売上原価(42d) */
    private BigDecimal sashihikiGenkaBuppanReference;
    /* 内訳 差引売上原価計(43d) */
    private BigDecimal sashihikiGenkaKeiReference;
    
    
    /* 内訳 給料手当計(47a) */
    private BigDecimal kyuryoTeateKyuryoReference;
    /* 内訳 給料手当計(47b) */
    private BigDecimal kyuryoTeateShoyoReference;
    /* 内訳 給料手当計(47c) */
    private BigDecimal kyuryoTeateTaishokuReference;

    
    /* 内訳 役員報酬計(44d) */
    private BigDecimal kyuryoTeateKeiYakuinReference;
    /* 内訳 給料手当計(45d) */
    private BigDecimal kyuryoTeateKeiKyuryoReference;
    /* 内訳 雑給計(46d) */
    private BigDecimal kyuryoTeateKeiZatsuKyuReference;
    /* 内訳 計(47d) */
    private BigDecimal kyuryoTeateKeiKubunReference;
    
    
    /* 借入金 借入金計(48d) */
    private BigDecimal kariireZandakaReference;
    /* 借入金 割賦未払い金計(49d) */
    private BigDecimal wappuZandakaReference;
    /* 借入金 リース未払い金計(50d) */
    private BigDecimal leaseZandakaReference;

    /* 借入金 当月増加計(50b) */
    private BigDecimal tougetsuZoukaReference;
    /* 借入金 当月減少計(51c) */
    private BigDecimal tougetsuGensyoReference;
    
    /* 借入金 計 */
    private BigDecimal kariireReference;
    

    /* ログイン情報 */
    private BirdUserInfo birdUserInfo;
    /* システム日付情報 */
    private BirdDateInfo birdDateInfo;

    /* 決算月（セレクトボックス表示用）*/
    private List kessanDtList;

    /* 編集画面表示時のタブ指定 デフォルト：0、月次損益計算書：1、内訳：2、借入金：3 */
    private int selectedTab;

    /* クローズ店を含むフラグ false:含まない　true:含む */
    private boolean closeMiseFlg;
    
    /* PDFダウンロードボタン効無効フラグ */
    private boolean pdfDLDisabledFlg;
    
    /**
     * クローズ店を含むフラグの設定
     * @return closeMiseFlg を戻します。
     */
	public boolean isCloseMiseFlg() {
        return closeMiseFlg;
    }
    /**
     * クローズ店を含むフラグの設定 
     * @param closeMiseFlg　closeMiseFlgの設定
     */
    public void setCloseMiseFlg(boolean closeMiseFlg) {
        this.closeMiseFlg = closeMiseFlg;
    }
    
    /**
     * エラー or ワーニングチェックフラグの設定
     * @return errSwitchFlg を戻します。
     */
    public boolean isErrSwitchFlg() {
        return errSwitchFlg;
    }
    /**
     * エラー or ワーニングチェックフラグの設定 
     * @param errSwitchFlg　errSwitchFlgの設定
     */
    public void setErrSwitchFlg(boolean errSwitchFlg) {
        this.errSwitchFlg = errSwitchFlg;
    }
    
    /**
     * PDFダウンロードボタン効無効フラグの設定
     * @return pdfDLDisabledFlg を戻します。
     */    
    public boolean isPdfDLDisabledFlg() {
        return pdfDLDisabledFlg;
    }
    /**
     * PDFダウンロードボタン効無効フラグの設定 
     * @param pdfDLDisabledFlg　pdfDLDisabledFlgの設定
     */
    public void setPdfDLDisabledFlg(boolean pdfDLDisabledFlg) {
        this.pdfDLDisabledFlg = pdfDLDisabledFlg;
    }
    
    /**
	 * セッションKeyの設定
	 * @return sessionKey を戻します。
	 */
	public String getSessionKey() {
		return sessionKey;
	}
	/**
	 * セッションKeyの設定
	 * @param sessionKey sessionKey を設定。
	 */
	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}

	/**
	 * PLデータCSV一括取込画面より遷移判定フラグの設定<br>
	 * PLデータCSV一括取込画面より遷移の場合：trueを設定する
	 * @return plCsvFlg を戻します。
	 */
	public boolean isPlCsvFlg() {
		return plCsvFlg;
	}
	/**
	 * PLデータCSV一括取込画面より遷移判定フラグの設定<br>
	 * PLデータCSV一括取込画面より遷移の場合：trueを設定する
	 * @param plCsvFlg plCsvFlg を設定。
	 */
	public void setPlCsvFlg(boolean plCsvFlg) {
		this.plCsvFlg = plCsvFlg;
	}

	/**
	 * PLデータCSV一括取込画面→P/Lデータ登録後の処理フラグの設定
	 * @return plCsvAfterCommitExeFlg を戻します。
	 */
	public boolean isPlCsvAfterCommitExeFlg() {
		return plCsvAfterCommitExeFlg;
	}
	/**
	 * PLデータCSV一括取込画面→P/Lデータ登録後の処理フラグの設定
	 * @param plCsvAfterCommitExeFlg plCsvAfterCommitExeFlg を設定。
	 */
	public void setPlCsvAfterCommitExeFlg(boolean plCsvAfterCommitExeFlg) {
		this.plCsvAfterCommitExeFlg = plCsvAfterCommitExeFlg;
	}

	/**
	 * P/L作成者情報 決算月の設定
	 * @return kessanDt を戻します。
	 */
	public String getKessanDt() {
		return kessanDt;
	}
	/**
	 * P/L作成者情報 決算月の設定
	 * @param kessanDt kessanDt を設定。
	 */
	public void setKessanDt(String kessanDt) {
		this.kessanDt = kessanDt;
	}

	/**
	 * 編集画面初期処理判定フラグの設定
	 * @return initFlg を戻します。
	 */
	public boolean isInitFlg() {
		return initFlg;
	}
	/**
	 * 編集画面初期処理判定フラグの設定
	 * @param initFlg initFlg を設定。
	 */
	public void setInitFlg(boolean initFlg) {
		this.initFlg = initFlg;
	}

	/**
	 * 対象年月リストの設定
	 * @return targetYMList を戻します。
	 */
	public List getTargetYMList() {
		return targetYMList;
	}
	/**
	 * 対象年月リストの設定
	 * @param targetYMList targetYMList を設定。
	 */
	public void setTargetYMList(List targetYMList) {
		this.targetYMList = targetYMList;
	}
	
	/**
	 * 条件画面表示モードの設定
	 * @return selectViewMode を戻します。
	 */
	public int getSelectViewMode() {
		return selectViewMode;
	}
	/**
	 * 条件画面表示モードの設定
	 * @param selectViewMode selectViewMode を設定。
	 */
	public void setSelectViewMode(int selectViewMode) {
		this.selectViewMode = selectViewMode;
	}

	/**
	 * ユーザタイプコードの設定
	 * @return userTypeCd を戻します。
	 */
	public String getUserTypeCd() {
		return userTypeCd;
	}
	/**
	 * ユーザタイプコードの設定
	 * @param userTypeCd userTypeCd を設定。
	 */
	public void setUserTypeCd(String userTypeCd) {
		this.userTypeCd = userTypeCd;
	}

	/**
	 * オーナコードの設定
	 * @return onerCd を戻します。
	 */
	public String getOnerCd() {
		return onerCd;
	}
	/**
	 * オーナコードの設定
	 * @param onerCd onerCd を設定。
	 */
	public void setOnerCd(String onerCd) {
		this.onerCd = onerCd;
	}
    
    /**
     * オーナコードの設定
     * @return onerCdTmp を戻します。
     */
    public String getOnerCdTmp() {
        return onerCdTmp;
    }
    /**
     * オーナコードの設定
     * @param onerCdTmp onerCdTmp を設定。
     */
    public void setOnerCdTmp(String onerCdTmp) {
        this.onerCdTmp = onerCdTmp;
    }
    
    
	/**
	 * 対象年月の設定
	 * @return targetYM を戻します。
	 */
	public String getTargetYM() {
		return targetYM;
	}
	/**
	 * 対象年月の設定
	 * @param targetYM targetYM を設定。
	 */
	public void setTargetYM(String targetYM) {
		this.targetYM = targetYM;
	}
	
	/**
	 * P/L選択値の設定
	 * @return plFlg を戻します。
	 */
	public int getPlFlg() {
		return plFlg;
	}
	
	/**
	 * 対象年月(セレクトボックス用)の設定
	 * @return targetYMSelectBox を戻します。
	 */
	public String getTargetYMSelectBox() {
		return targetYMSelectBox;
	}
	/**
	 * 対象年月(セレクトボックス用)の設定
	 * @param targetYMSelectBox targetYMSelectBox を設定。
	 */
	public void setTargetYMSelectBox(String targetYMSelectBox) {
		this.targetYMSelectBox = targetYMSelectBox;
	}

	/**
	 * P/L選択値の設定
	 * @param plFlg plFlg を設定。
	 */
	public void setPlFlg(int plFlg) {
		this.plFlg = plFlg;
	}
	/**
	 * 本社P/L 店コードの設定
	 * @return headOfficePlOnerCd を戻します。
	 */
	public String getHeadOfficePlOnerCd() {
		return headOfficePlOnerCd;
	}
	/**
	 * 本社P/L 店コードの設定
	 * @param headOfficePlOnerCd headOfficePlOnerCd を設定。
	 */
	public void setHeadOfficePlOnerCd(String headOfficePlOnerCd) {
		this.headOfficePlOnerCd = headOfficePlOnerCd;
	}
	/**
	 * 本社P/L 店名称の設定
	 * @return headOfficePlOnerName を戻します。
	 */
	public String getHeadOfficePlOnerName() {
		return headOfficePlOnerName;
	}
	/**
	 * 本社P/L 店名称の設定
	 * @param headOfficePlOnerName headOfficePlOnerName を設定。
	 */
	public void setHeadOfficePlOnerName(String headOfficePlOnerName) {
		this.headOfficePlOnerName = headOfficePlOnerName;
	}
	/**
	 * 店舗P/Lリストの設定
	 * @return storePlList を戻します。
	 */
	public List getStorePlList() {
		return storePlList;
	}
	/**
	 * 店舗P/Lリストの設定
	 * @param storePlList storePlList を設定。
	 */
	public void setStorePlList(List storePlList) {
		this.storePlList = storePlList;
	}
	/**
	 * 店舗P/L店コードの設定
	 * @return storePlMiseCd を戻します。
	 */
	public String getStorePlMiseCd() {
		return storePlMiseCd;
	}
	/**
	 * 店舗P/L店コードの設定
	 * @param storePlMiseCd storePlMiseCd を設定。
	 */
	public void setStorePlMiseCd(String storePlMiseCd) {
		this.storePlMiseCd = storePlMiseCd;
	}
    
    /**
     * 店クローズ日の設定
     * @return 店クローズ日を戻します。
     */
    public String getMiseCloseDate() {
        return miseCloseDate;
    }
    /**
     * 店クローズ日の設定
     * @return 店クローズ日を設定。
     */
    public void setMiseCloseDate(String miseCloseDate) {
        this.miseCloseDate = miseCloseDate;
    }
	
	/**
	 * ユーザ対応オーナー情報の設定
	 * @return msTUserOnerInfo を戻します。
	 */
	public MSTUserOnerInfo getMsTUserOnerInfo() {
		return msTUserOnerInfo;
	}
	/**
	 * ユーザ対応オーナー情報の設定
	 * @param msTUserOnerInfo msTUserOnerInfo を設定。
	 */
	public void setMsTUserOnerInfo(MSTUserOnerInfo msTUserOnerInfo) {
		this.msTUserOnerInfo = msTUserOnerInfo;
	}

	/**
	 * ユーザ対応店舗情報リストの設定
	 * @return msTUserMiseInfoList を戻します。
	 */
	public List getMsTUserMiseInfoList() {
		return msTUserMiseInfoList;
	}
	/**
	 * ユーザ対応店舗情報リストの設定
	 * @param msTUserMiseInfoList msTUserMiseInfoList を設定。
	 */
	public void setMsTUserMiseInfoList(List msTUserMiseInfoList) {
		this.msTUserMiseInfoList = msTUserMiseInfoList;
	}

	/**
	 * P/L作成者情報の設定
	 * @return trnPLAuthorInfo を戻します。
	 */
	public TrnPLAuthorInfo getTrnPLAuthorInfo() {
		return trnPLAuthorInfo;
	}
	/**
	 * P/L作成者情報の設定
	 * @param trnPLAuthorInfo trnPLAuthorInfo を設定。
	 */
	public void setTrnPLAuthorInfo(TrnPLAuthorInfo trnPLAuthorInfo) {
		this.trnPLAuthorInfo = trnPLAuthorInfo;
	}

	/**
	 * P/Lデータ情報の設定
	 * @return trnPLInfo を戻します。
	 */
	public TrnPLInfo getTrnPLInfo() {
		return trnPLInfo;
	}
	/**
	 * P/Lデータ情報の設定
	 * @param trnPLInfo trnPLInfo を設定。
	 */
	public void setTrnPLInfo(TrnPLInfo trnPLInfo) {
		this.trnPLInfo = trnPLInfo;
	}

	/**
	 * 前月PLデータ情報の設定
	 * @return trnZenPLInfo を戻します。
	 */
	public TrnZenPLInfo getTrnZenPLInfo() {
		return trnZenPLInfo;
	}
	/**
	 * 前月PLデータ情報の設定
	 * @param trnZenPLInfo trnZenPLInfo を設定。
	 */
	public void setTrnZenPLInfo(TrnZenPLInfo trnZenPLInfo) {
		this.trnZenPLInfo = trnZenPLInfo;
	}

	/**
	 * POS前月売上の設定
	 * @return trnPosZenUriage を戻します。
	 */
	public TrnPosZenUriage getTrnPosZenUriage() {
		return TrnPosZenUriage;
	}
	/**
	 * POS前月売上の設定
	 * @param trnPosZenUriage trnPosZenUriage を設定。
	 */
	public void setTrnPosZenUriage(TrnPosZenUriage trnPosZenUriage) {
		TrnPosZenUriage = trnPosZenUriage;
	}

	/**
	 * 売上高比率の設定
	 * @return uriagedakaRatio を戻します。
	 */
	public BigDecimal getUriagedakaRatio() {
		return uriagedakaRatio;
	}
	/**
	 * 売上高比率の設定
	 * @param uriagedakaRatio uriagedakaRatio を設定。
	 */
	public void setUriagedakaRatio(BigDecimal uriagedakaRatio) {
		this.uriagedakaRatio = uriagedakaRatio;
	}
	/**
	 * 売上原価比率の設定
	 * @return uriagegenkaRatio を戻します。
	 */
	public BigDecimal getUriagegenkaRatio() {
		return uriagegenkaRatio;
	}
	/**
	 * 売上原価比率の設定
	 * @param uriagegenkaRatio uriagegenkaRatio を設定。
	 */
	public void setUriagegenkaRatio(BigDecimal uriagegenkaRatio) {
		this.uriagegenkaRatio = uriagegenkaRatio;
	}
	/**
	 * 売上総利益比率の設定
	 * @return uriageSoRiekiRatio を戻します。
	 */
	public BigDecimal getUriageSoRiekiRatio() {
		return uriageSoRiekiRatio;
	}
	/**
	 * 売上総利益比率の設定
	 * @param uriageSoRiekiRatio uriageSoRiekiRatio を設定。
	 */
	public void setUriageSoRiekiRatio(BigDecimal uriageSoRiekiRatio) {
		this.uriageSoRiekiRatio = uriageSoRiekiRatio;
	}

	/**
	 * 経費小計（参考）の設定
	 * @return keihiShokeiReference を戻します。
	 */
	public BigDecimal getKeihiShokeiReference() {
		return keihiShokeiReference;
	}
	/**
	 * 経費小計（参考）の設定
	 * @param keihiShokeiReference keihiShokeiReference を設定。
	 */
	public void setKeihiShokeiReference(BigDecimal keihiShokeiReference) {
		this.keihiShokeiReference = keihiShokeiReference;
	}
	/**
	 *  当月利益（参考）の設定
	 * @return riekiReference を戻します。
	 */
	public BigDecimal getRiekiReference() {
		return riekiReference;
	}
	/**
	 *  当月利益（参考）の設定
	 * @param riekiReference riekiReference を設定。
	 */
	public void setRiekiReference(BigDecimal riekiReference) {
		this.riekiReference = riekiReference;
	}
	/**
	 * 償却前利益（参考）の設定
	 * @return shokyakuRiekiReference を戻します。
	 */
	public BigDecimal getShokyakuRiekiReference() {
		return shokyakuRiekiReference;
	}
	/**
	 * 償却前利益（参考）の設定
	 * @param shokyakuRiekiReference shokyakuRiekiReference を設定。
	 */
	public void setShokyakuRiekiReference(BigDecimal shokyakuRiekiReference) {
		this.shokyakuRiekiReference = shokyakuRiekiReference;
	}
	/**
	 * 売上総利益（参考）の設定
	 * @return uriageSoRiekiReference を戻します。
	 */
	public BigDecimal getUriageSoRiekiReference() {
		return uriageSoRiekiReference;
	}
	/**
	 * 売上総利益（参考）の設定
	 * @param uriageSoRiekiReference uriageSoRiekiReference を設定。
	 */
	public void setUriageSoRiekiReference(BigDecimal uriageSoRiekiReference) {
		this.uriageSoRiekiReference = uriageSoRiekiReference;
	}
	/**
	 * 日付情報の設定
	 * @return birdDateInfo を戻します。
	 */
	public BirdDateInfo getBirdDateInfo() {
		return birdDateInfo;
	}
	/**
	 * 日付情報の設定
	 * @param birdDateInfo birdDateInfo を設定。
	 */
	public void setBirdDateInfo(BirdDateInfo birdDateInfo) {
		this.birdDateInfo = birdDateInfo;
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
	 * 編集画面表示時のタブ指定の設定
	 * @return selectedTab を戻します。
	 */
	public int getSelectedTab() {
		return selectedTab;
	}
	/**
	 * 編集画面表示時のタブ指定の設定
	 * @param selectedTab selectedTab を設定。
	 */
	public void setSelectedTab(int selectedTab) {
		this.selectedTab = selectedTab;
	}
	
	/**
	 * 決算月（セレクトボックス表示用）の設定
	 * @return kessanDtList を戻します。
	 */
	public List getKessanDtList() {
		return kessanDtList;
	}
	/**
	 * 決算月（セレクトボックス表示用）の設定
	 * @param kessanDtList kessanDtList を設定。
	 */
	public void setKessanDtList(List kessanDtList) {
		this.kessanDtList = kessanDtList;
	}
    
    /**
     * 借入金計（参考）の設定
     * @param kariireReference を設定。
     */
    public BigDecimal getKariireReference() {
        return kariireReference;
    }
    /**
     * 借入金計（参考）の設定
     * @return kariireReference を戻します。
     */
    public void setKariireReference(BigDecimal kariireReference) {
        this.kariireReference = kariireReference;
    }
    /**
     * 水道光熱費計（参考）の設定
     * @param suidokonetsuhiReference を設定。
     */
    public BigDecimal getSuidokonetsuhiReference() {
        return suidokonetsuhiReference;
    }
    /**
     * 水道光熱費計（参考）の設定
     * @return suidokonetsuhiReference を戻します。
     */
    public void setSuidokonetsuhiReference(BigDecimal suidokonetsuhiReference) {
        this.suidokonetsuhiReference = suidokonetsuhiReference;
    }
    /**
     * 売上高計（参考）の設定
     * @param uriageDakaReference uriageDakaReference を設定。
     */
    public BigDecimal getUriageDakaReference() {
        return uriageDakaReference;
    }
    /**
     * 売上高計（参考）の設定
     * @return uriageDakaReference を戻します。
     */
    public void setUriageDakaReference(BigDecimal uriageDakaReference) {
        this.uriageDakaReference = uriageDakaReference;
    }
//    /**
//     * 売上原価計 前月在庫（参考）の設定
//     * @param uriageGenkaZenZaikoReference uriageGenkaZenZaikoReference を設定。
//     */
//    public BigDecimal getUriageGenkaZenZaikoReference() {
//        return uriageGenkaZenZaikoReference;
//    }
//    /**
//     * 売上原価計 前月在庫（参考）の設定
//     * @return uriageGenkaZenZaikoReference を戻します。
//     */
//    public void setUriageGenkaZenZaikoReference(BigDecimal uriageGenkaZenZaikoReference) {
//        this.uriageGenkaZenZaikoReference = uriageGenkaZenZaikoReference;
//    }
//    /**
//     * 売上原価計 差引売上（参考）の設定
//     * @param uriageGenkaSashihikiUriageReference uriageGenkaSashihikiUriageReference を設定。
//     */
//    public BigDecimal getUriageGenkaSashihikiUriageReference() {
//        return uriageGenkaSashihikiUriageReference;
//    }
//    /**
//     * 売上原価計 差引売上（参考）の設定
//     * @return uriageGenkaSashihikiUriageReference を戻します。
//     */
//    public void setUriageGenkaSashihikiUriageReference(
//            BigDecimal uriageGenkaSashihikiUriageReference) {
//        this.uriageGenkaSashihikiUriageReference = uriageGenkaSashihikiUriageReference;
//    }
    /**
     * 売上原価計 当月仕入（参考）の設定
     * @param uriageGenkaTogetsuShiireReference を設定。
     */
    public BigDecimal getUriageGenkaTogetsuShiireReference() {
        return uriageGenkaTogetsuShiireReference;
    }
    /**
     * 売上原価計 当月仕入（参考）の設定
     * @return uriageGenkaTogetsuShiireReference を戻します。
     */
    public void setUriageGenkaTogetsuShiireReference(
            BigDecimal uriageGenkaTogetsuShiireReference) {
        this.uriageGenkaTogetsuShiireReference = uriageGenkaTogetsuShiireReference;
    }
    /**
     * 売上原価計 当月在庫（参考）の設定
     * @param uriageGenkaTogetsuZaikoReference を設定。
     */
    public BigDecimal getUriageGenkaTogetsuZaikoReference() {
        return uriageGenkaTogetsuZaikoReference;
    }
    /**
     * 売上原価計 当月在庫（参考）の設定
     * @return uriageGenkaTogetsuZaikoReference を戻します。
     */
    public void setUriageGenkaTogetsuZaikoReference(
            BigDecimal uriageGenkaTogetsuZaikoReference) {
        this.uriageGenkaTogetsuZaikoReference = uriageGenkaTogetsuZaikoReference;
    }
    
    /**
     * 売上原価計 物販 の設定
     * @param sashihikiGenkaBuppanReference を戻します。
     */
    public BigDecimal getSashihikiGenkaBuppanReference() {
        return sashihikiGenkaBuppanReference;
    }
    /**
     * 売上原価計 物販 の設定
     * @return sashihikiGenkaBuppanReference を設定。
     */
    public void setSashihikiGenkaBuppanReference(
            BigDecimal sashihikiGenkaBuppanReference) {
        this.sashihikiGenkaBuppanReference = sashihikiGenkaBuppanReference;
    }
    
    /**
     * 売上原価計 原材料 の設定
     * @param sashihikiGenkaGenzairyoReference を戻します。
     */
    public BigDecimal getSashihikiGenkaGenzairyoReference() {
        return sashihikiGenkaGenzairyoReference;
    }
    /**
     * 売上原価計 原材料 の設定
     * @return sashihikiGenkaGenzairyoReference を設定。
     */
    public void setSashihikiGenkaGenzairyoReference(
            BigDecimal sashihikiGenkaGenzairyoReference) {
        this.sashihikiGenkaGenzairyoReference = sashihikiGenkaGenzairyoReference;
    }
    /**
     * 売上原価計 包材 の設定
     * @param sashihikiGenkaHouzaiReference を戻します。
     */
    public BigDecimal getSashihikiGenkaHouzaiReference() {
        return sashihikiGenkaHouzaiReference;
    }
    /**
     * 売上原価計 包材 の設定
     * @return sashihikiGenkaHouzaiReference を設定。
     */
    public void setSashihikiGenkaHouzaiReference(
            BigDecimal sashihikiGenkaHouzaiReference) {
        this.sashihikiGenkaHouzaiReference = sashihikiGenkaHouzaiReference;
    }
    /**
     * 売上原価計 野菜 の設定
     * @param sashihikiGenkaYasaiReference を戻します。
     */
    public BigDecimal getSashihikiGenkaYasaiReference() {
        return sashihikiGenkaYasaiReference;
    }
    /**
     * 売上原価計 野菜 の設定
     * @param sashihikiGenkaYasaiReference を設定。
     */
    public void setSashihikiGenkaYasaiReference(
            BigDecimal sashihikiGenkaYasaiReference) {
        this.sashihikiGenkaYasaiReference = sashihikiGenkaYasaiReference;
    }
    /**
     * 差引売上原価計 の設定
     * @param sashihikiGenkaKeiReference を戻します。
     */
    public BigDecimal getSashihikiGenkaKeiReference() {
        return sashihikiGenkaKeiReference;
    }
    /**
     * 差引売上原価計 の設定
     * @param sashihikiGenkaKeiReference を設定。
     */
    public void setSashihikiGenkaKeiReference(BigDecimal sashihikiGenkaKeiReference) {
        this.sashihikiGenkaKeiReference = sashihikiGenkaKeiReference;
    }
    /**
     * 給料手当(賞与計) の設定
     * @param sashihikiGenkaKeiReference を戻します。
     */
    public BigDecimal getKyuryoTeateKyuryoReference() {
        return kyuryoTeateKyuryoReference;
    }
    /**
     * 給料手当(賞与計) の設定
     * @param kyuryoTeateKyuryoReference を設定。
     */
    public void setKyuryoTeateKyuryoReference(BigDecimal kyuryoTeateKyuryoReference) {
        this.kyuryoTeateKyuryoReference = kyuryoTeateKyuryoReference;
    }
    /**
     * 給料手当(退職金) の設定
     * @param kyuryoTeateShoyoReference を戻します。
     */
    public BigDecimal getKyuryoTeateShoyoReference() {
        return kyuryoTeateShoyoReference;
    }
    /**
     * 給料手当(退職金) の設定
     * @param kyuryoTeateShoyoReference を設定。
     */
    public void setKyuryoTeateShoyoReference(BigDecimal kyuryoTeateShoyoReference) {
        this.kyuryoTeateShoyoReference = kyuryoTeateShoyoReference;
    }
    /**
     * 給料手当(計) の設定
     * @param kyuryoTeateTaishokuReference を戻します。
     */
    public BigDecimal getKyuryoTeateTaishokuReference() {
        return kyuryoTeateTaishokuReference;
    }
    /**
     * 給料手当(計) の設定
     * @param kyuryoTeateTaishokuReference を設定。
     */
    public void setKyuryoTeateTaishokuReference(
            BigDecimal kyuryoTeateTaishokuReference) {
        this.kyuryoTeateTaishokuReference = kyuryoTeateTaishokuReference;
    }
    
    /**
     * 給料手当計 の設定
     * @param kyuryoTeateKeiKubunReference を戻します。
     */
    public BigDecimal getKyuryoTeateKeiKubunReference() {
        return kyuryoTeateKeiKubunReference;
    }
    /**
     * 給料手当計 の設定
     * @param kyuryoTeateKeiKubunReference を設定。
     */    
    public void setKyuryoTeateKeiKubunReference(
            BigDecimal kyuryoTeateKeiKubunReference) {
        this.kyuryoTeateKeiKubunReference = kyuryoTeateKeiKubunReference;
    }
    /**
     * 給料手当(給料)計 の設定
     * @param kyuryoTeateKeiKyuryoReference を戻します。
     */
    public BigDecimal getKyuryoTeateKeiKyuryoReference() {
        return kyuryoTeateKeiKyuryoReference;
    }
    /**
     * 給料手当(給料)計 の設定
     * @param kyuryoTeateKeiKyuryoReference を設定。
     */
    public void setKyuryoTeateKeiKyuryoReference(
            BigDecimal kyuryoTeateKeiKyuryoReference) {
        this.kyuryoTeateKeiKyuryoReference = kyuryoTeateKeiKyuryoReference;
    }
    /**
     * 給料手当(役員報酬)計 の設定
     * @param kyuryoTeateKeiYakuinReference を戻します。
     */
    public BigDecimal getKyuryoTeateKeiYakuinReference() {
        return kyuryoTeateKeiYakuinReference;
    }
    /**
     * 給料手当(役員報酬)計 の設定
     * @param kyuryoTeateKeiYakuinReference を設定。
     */
    public void setKyuryoTeateKeiYakuinReference(
            BigDecimal kyuryoTeateKeiYakuinReference) {
        this.kyuryoTeateKeiYakuinReference = kyuryoTeateKeiYakuinReference;
    }
    /**
     * 給料手当(雑給)計 の設定
     * @param kyuryoTeateKeiZatsuKyuReference を戻します。
     */
    public BigDecimal getKyuryoTeateKeiZatsuKyuReference() {
        return kyuryoTeateKeiZatsuKyuReference;
    }
    /**
     * 給料手当(雑給)計 の設定
     * @param kyuryoTeateKeiZatsuKyuReference を設定。
     */
    public void setKyuryoTeateKeiZatsuKyuReference(
            BigDecimal kyuryoTeateKeiZatsuKyuReference) {
        this.kyuryoTeateKeiZatsuKyuReference = kyuryoTeateKeiZatsuKyuReference;
    }
    
    /**
     * 借入金(借入金)計 の設定
     * @param kariireZandakaReference を設定。
     */
    public BigDecimal getKariireZandakaReference() {
        return kariireZandakaReference;
    }
    /**
     * 借入金(借入金)計 の設定
     * @param kariireZandakaReference を戻します。
     */
    public void setKariireZandakaReference(BigDecimal kariireZandakaReference) {
        this.kariireZandakaReference = kariireZandakaReference;
    }
    /**
     * 借入金(割賦未払い金)計 の設定
     * @param leaseZandakaReference を設定。
     */
    public BigDecimal getLeaseZandakaReference() {
        return leaseZandakaReference;
    }
    /**
     * 借入金(割賦未払い金)計 の設定
     * @param leaseZandakaReference を戻します。
     */
    public void setLeaseZandakaReference(BigDecimal leaseZandakaReference) {
        this.leaseZandakaReference = leaseZandakaReference;
    }
    /**
     * 借入金(リース未払い金)計 の設定
     * @param tougetsuGensyoReference を設定。
     */
    public BigDecimal getTougetsuGensyoReference() {
        return tougetsuGensyoReference;
    }
    /**
     * 借入金(リース未払い金)計 の設定
     * @param tougetsuGensyoReference を戻します。
     */
    public void setTougetsuGensyoReference(BigDecimal tougetsuGensyoReference) {
        this.tougetsuGensyoReference = tougetsuGensyoReference;
    }
    /**
     * 借入金(当月増加)計 の設定
     * @param tougetsuZoukaReference を設定。
     */
    public BigDecimal getTougetsuZoukaReference() {
        return tougetsuZoukaReference;
    }
    /**
     * 借入金(当月増加)計 の設定
     * @param tougetsuZoukaReference を戻します。
     */
    public void setTougetsuZoukaReference(BigDecimal tougetsuZoukaReference) {
        this.tougetsuZoukaReference = tougetsuZoukaReference;
    }
    /**
     * 借入金(当月減少)計 の設定
     * @param wappuZandakaReference を設定。
     */
    public BigDecimal getWappuZandakaReference() {
        return wappuZandakaReference;
    }
    /**
     * 借入金(当月減少)計 の設定
     * @param wappuZandakaReference を戻します。
     */
    public void setWappuZandakaReference(BigDecimal wappuZandakaReference) {
        this.wappuZandakaReference = wappuZandakaReference;
    }
    
    
    

	/**
	 * 保持情報のクリア
	 */
	public void clear() {
		setTrnPLInfo(null);
		setTrnZenPLInfo(null);
		setSelectedTab(1);
		setUriagedakaRatio(null);
		setUriagegenkaRatio(null);
		setUriageSoRiekiRatio(null);
		setPlCsvAfterCommitExeFlg(false);
	}

	/**
	 * 対象年月リスト作成
	 * @param birdDateInfo
	 * @return 対象年月リスト
	 */
	public List createTargetYMList(BirdDateInfo birdDateInfo) {
        DateFormatter formatter = new DateFormatter();
        String sysMonth = formatter.format(birdDateInfo.getSysDate(),
				DateFormatter.PATTERN_MONTH, DateFormatter.DATE_TYPE_YMD);
        List listMonth = new ArrayList();

		// 前月から「NENGETU_DISPLAY_MONTH」ヶ月を表示
        try {
			sysMonth = DateManager.getPrevMonth(sysMonth, 1);
		} catch (Exception e) {
            throw new FtlSystemException("条件画面初期処理");
		}

        for (int i = 0; i < NENGETU_DISPLAY_MONTH; i++) {
            String month = "";
            try {
                month = DateManager.getPrevMonth(sysMonth, i);
            }
            catch (Exception ex) {
                throw new FtlSystemException("条件画面初期処理");
            }
            SelectItem item = new SelectItem(month, formatter.format(month,
					DateFormatter.PATTERN_MONTH_SLASH,
					DateFormatter.DATE_TYPE_YM)); 
            listMonth.add(item);
        }
        // 自由入力
//        listMonth.add(new SelectItem("999999", "任意月指定"));

		return listMonth;
	}

	/**
	 * 決算月表示リスト作成
	 * @return 決算月表示リスト
	 */
	public List creatKessanDtList() {
		// 決算月表示リスト作成
        List listKessanDt = new ArrayList();
        listKessanDt.add(new SelectItem("01", "1月"));
        listKessanDt.add(new SelectItem("02", "2月"));
        listKessanDt.add(new SelectItem("03", "3月"));
        listKessanDt.add(new SelectItem("04", "4月"));
        listKessanDt.add(new SelectItem("05", "5月"));
        listKessanDt.add(new SelectItem("06", "6月"));
        listKessanDt.add(new SelectItem("07", "7月"));
        listKessanDt.add(new SelectItem("08", "8月"));
        listKessanDt.add(new SelectItem("09", "9月"));
        listKessanDt.add(new SelectItem("10", "10月"));
        listKessanDt.add(new SelectItem("11", "11月"));
        listKessanDt.add(new SelectItem("12", "12月"));
		return listKessanDt;
	}

	/**
	 * WARNINGメッセージセット
	 */
	public void setWarningMsg(TrnPLInfo trnPLInfo) {
		// POS売上チェック
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage mes;
		if (trnPLInfo.getPlDataErrorInfo().isExistErrorSales()) {
			mes = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"（確認）売上高とPOS前月売上の差が大きすぎます。", "");
			context.addMessage(null, mes);
		}
		// 上下限チェック
		String warinigMsg = createErrorNumLimitMsg(trnPLInfo
				.getPlDataErrorInfo());
		if (warinigMsg != null) {
			mes = new FacesMessage(FacesMessage.SEVERITY_ERROR, warinigMsg,
					"");
			context.addMessage(null, mes);
		}

		// メモ・通信欄エラー時
		if (trnPLInfo.getPlDataErrorInfo().isErrorItem(TrnPLInfo.memo_COLUMN,
				PlDataErrorInfo.ERRORCODE_INVALID)) {
			mes = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"メモ・通信欄は50文字以内で入力して下さい。", "");
			context.addMessage(null, mes);
		}
	}

	/**
	 * 上下限ワーニングメッセージ生成
	 * （ワーニングが無い場合はNULLを返す）
	 * @param plDataErrorInfo
	 * @return ワーニングメッセージ
	 */
	public String createErrorNumLimitMsg(PlDataErrorInfo plDataErrorInfo) {
		boolean existFlg = false;
		String warningMsg = "（確認）金額をご確認下さい。";

		// 月次損益計算書
        if (plDataErrorInfo.isErrorItem(
				creatItemCodeList(1),
				PlDataErrorInfo.ERRORCODE_NUM_LIMIT)) {
        	existFlg = true;
        	warningMsg = warningMsg + "[月次損益]";
		}
        else if (plDataErrorInfo.isErrorItem(
                creatItemCodeList(1),
                PlDataErrorInfo.ERRORCODE_NUM_ADJUST)) {
            existFlg = true;
            warningMsg = warningMsg + "[月次損益]";
        }
        
        // 内訳
        if (plDataErrorInfo.isErrorItem(
				creatItemCodeList(2),
				PlDataErrorInfo.ERRORCODE_NUM_LIMIT)) {
        	existFlg = true;
        	warningMsg = warningMsg + "[内訳]";
		}
        else if (plDataErrorInfo.isErrorItem(
                creatItemCodeList(2),
                PlDataErrorInfo.ERRORCODE_NUM_ADJUST)) {
            existFlg = true;
            warningMsg = warningMsg + "[内訳]";
        }
        
        // 借入金
        if (plDataErrorInfo.isErrorItem(
				creatItemCodeList(3),
				PlDataErrorInfo.ERRORCODE_NUM_LIMIT)) {
        	existFlg = true;
        	warningMsg = warningMsg + "[借入金]";
		}
        else if (plDataErrorInfo.isErrorItem(
                creatItemCodeList(3),
                PlDataErrorInfo.ERRORCODE_NUM_ADJUST_LOAN)) {
            existFlg = true;
            warningMsg = warningMsg + "[借入金]";
        }
		return (existFlg == true) ? warningMsg : null;
	}

	/**
	 * itemCodeListの作成
	 * mode 1:月次損益計算書
	 *      2:内訳
	 *      3:貸入金
	 * @param mode
	 * @return itemCodeList 
	 */
	private List creatItemCodeList(int mode) {
        List itemCodeList = new ArrayList();
        // 月次損益計算書
        if (mode == 1) {
			itemCodeList.add(TrnPLInfo.uriagedaka_COLUMN);
			itemCodeList.add(TrnPLInfo.uriagegenka_COLUMN);
			itemCodeList.add(TrnPLInfo.uriageSoRieki_COLUMN);
			itemCodeList.add(TrnPLInfo.salary_COLUMN);
			itemCodeList.add(TrnPLInfo.yachin_COLUMN);
			itemCodeList.add(TrnPLInfo.suikouHi_COLUMN);
			itemCodeList.add(TrnPLInfo.royalty_COLUMN);
			itemCodeList.add(TrnPLInfo.tesuryo_COLUMN);
			itemCodeList.add(TrnPLInfo.koukoku_COLUMN);
			itemCodeList.add(TrnPLInfo.shoumou_COLUMN);
			itemCodeList.add(TrnPLInfo.houteiFukuri_COLUMN);
			itemCodeList.add(TrnPLInfo.fukuriKousei_COLUMN);
			itemCodeList.add(TrnPLInfo.kousai_COLUMN);
			itemCodeList.add(TrnPLInfo.ryohi_COLUMN);
			itemCodeList.add(TrnPLInfo.tusin_COLUMN);
			itemCodeList.add(TrnPLInfo.lease_COLUMN);
			itemCodeList.add(TrnPLInfo.sharyo_COLUMN);
			itemCodeList.add(TrnPLInfo.sozei_COLUMN);
			itemCodeList.add(TrnPLInfo.hoken_COLUMN);
			itemCodeList.add(TrnPLInfo.unchin_COLUMN);
			itemCodeList.add(TrnPLInfo.shuzen_COLUMN);
			itemCodeList.add(TrnPLInfo.yobi_COLUMN);
			itemCodeList.add(TrnPLInfo.zappi_COLUMN);
			itemCodeList.add(TrnPLInfo.keihiShokei_COLUMN);
			itemCodeList.add(TrnPLInfo.shokyakuRieki_COLUMN);
			itemCodeList.add(TrnPLInfo.genkaShokyaku_COLUMN);
			itemCodeList.add(TrnPLInfo.eigaiShueki_COLUMN);
			itemCodeList.add(TrnPLInfo.eigaiHiyo_COLUMN);
			itemCodeList.add(TrnPLInfo.honshahiHai_COLUMN);
			itemCodeList.add(TrnPLInfo.rieki_COLUMN);
		}
        // 内訳
        else if (mode == 2) {
			itemCodeList.add(TrnPLInfo.uriage_COLUMN);
			itemCodeList.add(TrnPLInfo.buppan_COLUMN);
			itemCodeList.add(TrnPLInfo.uriUchiwake_COLUMN);
			itemCodeList.add(TrnPLInfo.elec_COLUMN);
			itemCodeList.add(TrnPLInfo.gas_COLUMN);
			itemCodeList.add(TrnPLInfo.water_COLUMN);
			itemCodeList.add(TrnPLInfo.sonota_COLUMN);
			itemCodeList.add(TrnPLInfo.suikouUchiwake_COLUMN);
			itemCodeList.add(TrnPLInfo.genzairyoShire_COLUMN);
			itemCodeList.add(TrnPLInfo.genzairyoZaiko_COLUMN);
			itemCodeList.add(TrnPLInfo.genzairyoKei_COLUMN);
			itemCodeList.add(TrnPLInfo.yasaiShire_COLUMN);
			itemCodeList.add(TrnPLInfo.yasaiZaiko_COLUMN);
			itemCodeList.add(TrnPLInfo.yasaiKei_COLUMN);
			itemCodeList.add(TrnPLInfo.houzaiShire_COLUMN);
			itemCodeList.add(TrnPLInfo.houzaiZaiko_COLUMN);
			itemCodeList.add(TrnPLInfo.houzaiKei_COLUMN);
			itemCodeList.add(TrnPLInfo.buppanShire_COLUMN);
			itemCodeList.add(TrnPLInfo.buppanZaiko_COLUMN);
			itemCodeList.add(TrnPLInfo.buppanKei_COLUMN);
			itemCodeList.add(TrnPLInfo.touSiireKei_COLUMN);
			itemCodeList.add(TrnPLInfo.touZaikoKei_COLUMN);
			itemCodeList.add(TrnPLInfo.sashihikiKei_COLUMN);
			itemCodeList.add(TrnPLInfo.yakuinSalary_COLUMN);
			itemCodeList.add(TrnPLInfo.yakuinBonus_COLUMN);
			itemCodeList.add(TrnPLInfo.yakuinRetire_COLUMN);
			itemCodeList.add(TrnPLInfo.yakuinKei_COLUMN);
			itemCodeList.add(TrnPLInfo.salarySalary_COLUMN);
			itemCodeList.add(TrnPLInfo.salaryBonus_COLUMN);
			itemCodeList.add(TrnPLInfo.salaryRetire_COLUMN);
			itemCodeList.add(TrnPLInfo.salaryKei_COLUMN);
			itemCodeList.add(TrnPLInfo.zakkyuSalary_COLUMN);
			itemCodeList.add(TrnPLInfo.zakkyuBonus_COLUMN);
			itemCodeList.add(TrnPLInfo.zakkyuRetire_COLUMN);
			itemCodeList.add(TrnPLInfo.zakkyuKei_COLUMN);
			itemCodeList.add(TrnPLInfo.kyuryoKei_COLUMN);
			itemCodeList.add(TrnPLInfo.bonusKei_COLUMN);
			itemCodeList.add(TrnPLInfo.retireKei_COLUMN);
			itemCodeList.add(TrnPLInfo.salaryUtiKei_COLUMN);
        }
        // 貸入金
        else if (mode == 3) {
			itemCodeList.add(TrnPLInfo.kashiireUp_COLUMN);
			itemCodeList.add(TrnPLInfo.kashiireDown_COLUMN);
			itemCodeList.add(TrnPLInfo.kashiireZandaka_COLUMN);
			itemCodeList.add(TrnPLInfo.kappuUp_COLUMN);
			itemCodeList.add(TrnPLInfo.kappuDown_COLUMN);
			itemCodeList.add(TrnPLInfo.kappuZandaka_COLUMN);
			itemCodeList.add(TrnPLInfo.leaseUp_COLUMN);
			itemCodeList.add(TrnPLInfo.leaseDown_COLUMN);
			itemCodeList.add(TrnPLInfo.leaseZandaka_COLUMN);
			itemCodeList.add(TrnPLInfo.touZoukaKei_COLUMN);
			itemCodeList.add(TrnPLInfo.touGenshoKei_COLUMN);
			itemCodeList.add(TrnPLInfo.touZandakaKei_COLUMN);
        }
        return itemCodeList;
	}
	
    
    /**
     * 集計項目にNULLを設定する
     */
    public void setReferenceNull(){
        
        // 月次損益計算書集計 参考値の初期化
        setUriageSoRiekiReference(null);
        setKeihiShokeiReference(null);
        setShokyakuRiekiReference(null);
        setRiekiReference(null);
        
        // 内訳集計 参考値の初期化
        setUriageDakaReference(null);
        setSuidokonetsuhiReference(null);
        setUriageGenkaTogetsuShiireReference(null);
        setUriageGenkaTogetsuZaikoReference(null);
        setSashihikiGenkaGenzairyoReference(null);
        setSashihikiGenkaYasaiReference(null);
        setSashihikiGenkaHouzaiReference(null);
        setSashihikiGenkaBuppanReference(null);
        setSashihikiGenkaKeiReference(null);
        setKyuryoTeateKyuryoReference(null);
        setKyuryoTeateShoyoReference(null);
        setKyuryoTeateTaishokuReference(null);
        setKyuryoTeateKeiYakuinReference(null);
        setKyuryoTeateKeiKyuryoReference(null);
        setKyuryoTeateKeiZatsuKyuReference(null);
        setKyuryoTeateKeiKubunReference(null);
        
        // 借入金集計 参考値の初期化
        setKariireZandakaReference(null);
        setWappuZandakaReference(null);
        setLeaseZandakaReference(null);
        setTougetsuZoukaReference(null);
        setTougetsuGensyoReference(null);
        setKariireReference(null);
    }
    

	// セッションキー作成 /////////////////////////////////////////////
	public static final String SHA1PRNG = "SHA1PRNG";
    public static final String MD5 = "MD5";

    /**
     *  セッションキー作成処理 
     */
    public String _makeSessionKey() {
        byte[] bytes = new byte[256];
        byte[] key;
        try {
            SecureRandom random = SecureRandom.getInstance(SHA1PRNG);
            MessageDigest md = MessageDigest.getInstance(MD5);
            random.nextBytes(bytes);
            md.update(bytes);
            key = md.digest();
        }
        catch (Exception ex) {
            throw new FtlSystemException("セッションキー作成失敗");
        }
        return asHex(key);
    }

    /* 16進数変換 */
    private static String asHex(byte bytes[]) {
        StringBuffer strbuf = new StringBuffer(bytes.length * 2);
        for (int index = 0; index < bytes.length; index++) {
            int bt = bytes[index] & 0xff;
            if (bt < 0x10) {
                strbuf.append("0");
            }
            strbuf.append(Long.toString(bt, 16));
        }
        return strbuf.toString().toUpperCase();
    }
    
    /**
     * sessionKey有効チェック
     * @param param 画面保持セッションキー
     * @param sessionKey セッションキー
     * @return
     */
    public boolean isValidSessionKey(String param, String sessionKey) {
    	if (param != null && sessionKey != null && param.equals(sessionKey)) {
			return true;
		} else {
			return false;
		}
    }
    
    /**
     * 編集中確認メッセージを戻す
     * @return string
     */
    public String getEditConfirmMsg() {
        return EDIT_CONFIRM_MSG;
    }
    public void setEditConfirmMsg(String edit_confirm_msg) {
    }


    

}
