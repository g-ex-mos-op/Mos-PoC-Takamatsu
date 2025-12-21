package jp.co.isid.mos.bird.storemanage.claimref.entity;

import jp.co.isid.mos.bird.common.util.CommonUtil;

/**
 * お客様の声データエンティティ
 * 
 * @author xkinu
 *
 */
public class UIVoiceInfo {
    
    public static final String TABLE = "BD05VICE";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String claimNo_COLUMN = "CLAIM_NO";
    
    public static final String jusinDt_COLUMN = "JUSIN_DT";
    
    public static final String kanryoDt_COLUMN = "KANRYO_DT";
    
    public static final String sibuCd_COLUMN = "SIBU_CD";
    
    public static final String sibuName_COLUMN = "SIBU_NAME";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";
    
    public static final String bnrL_COLUMN = "BNR_L";
    
    public static final String bnrLName_COLUMN = "BNR_L_NAME";
    
    public static final String bnrM_COLUMN = "BNR_M";
    
    public static final String bnrMName_COLUMN = "BNR_M_NAME";
    
    public static final String bnrS_COLUMN = "BNR_S";
    
    public static final String bnrSName_COLUMN = "BNR_S_NAME";
    
    public static final String genin_COLUMN = "GENIN";
    
    public static final String naiyou_COLUMN = "NAIYOU";
    
    public static final String tenpoTaiou_COLUMN = "TENPO_TAIOU";
    
    public static final String svTaisaku_COLUMN = "SV_TAISAKU";
    
    public static final String uketukeNo_COLUMN = "UKETUKE_NO";
    
    public static final String svCheck_COLUMN = "SV_CHECK";
    
    public static final String kokaiFlg_COLUMN = "KOKAI_FLG";
    
    public static final String typeCd_COLUMN = "TYPE_CD";
    
    public static final String typeName_COLUMN = "TYPE_NAME";
    
    public static final String kaitou_COLUMN = "KAITOU";
    
    public static final String shousai_COLUMN = "SHOUSAI";
    
    public static final String closeFlg_COLUMN = "CLOSE_FLG";
    
    public static final String svNameKj_COLUMN = "SV_NAME_KJ";

    /**
     * SEQ_NO
     */
    private String seq;
    /**
     * 会社コード
     */
    private String companyCd;
    
    /**
     * 管理番号
     */
    private String claimNo;
    
    /**
     * 受信日
     */
    private String jusinDt;
    
    /**
     * 完了日
     */
    private String kanryoDt;
    
    /**
     * 支部コード
     */
    private String sibuCd;
    
    /**
     * 支部名称
     */
    private String sibuName;
    
    /**
     * 店コード
     */
    private String miseCd;
    
    /**
     * 店舗名称
     */
    private String miseNameKj;
    
    /**
     * 大分類
     */
    private String bnrL;
    
    /**
     * 大分類名称
     */
    private String bnrLName;
    
    /**
     * 中分類
     */
    private String bnrM;
    
    /**
     * 中分類名称
     */
    private String bnrMName;
    
    /**
     * 小分類
     */
    private String bnrS;
    
    /**
     * 小分類名称
     */
    private String bnrSName;
    
    /**
     * 原因
     */
    private String genin;
    
    /**
     * 内容
     */
    private String naiyou;
    
    /**
     * 店舗対応
     */
    private String tenpoTaiou;
    
    /**
     * SV対策
     */
    private String svTaisaku;
    
    /**
     * 受付番号
     */
    private String uketukeNo;
    
    /**
     * SV送信チェック
     */
    private String svCheck;
    
    /**
     * 公開フラグ
     */
    private String kokaiFlg;
    
    /**
     * タイプコード
     */
    private String typeCd;
    
    /**
     * タイプ名称
     */
    private String typeName;
    
    /**
     * 回答
     */
    private String kaitou;
    
    /**
     * 詳細
     */
    private String shousai;
    
    /**
     * 閉店フラグ
     */
    private String closeFlg;
    
    /**
     * SV担当者名称
     */
    private String svNameKj;
    /**
     * TRタグスタイルシートクラスを取得します。
     * @return クラス名称
     */
    public String getTrCssClass() {
    	if(!CommonUtil.isNull(getClaimNo())) {
    		return "default_bkgColor";
    	}
    	return "body_sum5";
    }
    /**
     * 会社コードを取得します。
     * @return 会社コード
     */
    public String getCompanyCd() {
        return companyCd;
    }
    /**
     * 会社コードを設定します。
     * @param companyCd 会社コード
     */
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }
    
    /**
     * 管理番号を取得します。
     * @return 管理番号
     */
    public String getClaimNo() {
        return claimNo;
    }
    /**
     * 管理番号を設定します。
     * @param claimNo 管理番号
     */
    public void setClaimNo(String claimNo) {
        this.claimNo = claimNo;
    }
    
    /**
     * 受信日を取得します。
     * @return 受信日
     */
    public String getJusinDt() {
        return jusinDt;
    }
    /**
     * 受信日を設定します。
     * @param jusinDt 受信日
     */
    public void setJusinDt(String jusinDt) {
        this.jusinDt = jusinDt;
    }
    
    /**
     * 完了日を取得します。
     * @return 完了日
     */
    public String getKanryoDt() {
        return kanryoDt;
    }
    /**
     * 完了日を設定します。
     * @param kanryoDt 完了日
     */
    public void setKanryoDt(String kanryoDt) {
        this.kanryoDt = kanryoDt;
    }
    
    /**
     * 店コードを取得します。
     * @return 店コード
     */
    public String getMiseCd() {
        return miseCd;
    }
    /**
     * 店コードを設定します。
     * @param miseCd 店コード
     */
    public void setMiseCd(String miseCd) {
        this.miseCd = miseCd;
    }
    
    /**
     * 店舗名称を取得します。
     * @return 店舗名称
     */
    public String getMiseNameKj() {
        return miseNameKj;
    }
    /**
     * 店舗名称を設定します。
     * @param miseNameKj 店舗名称
     */
    public void setMiseNameKj(String miseNameKj) {
        this.miseNameKj = miseNameKj;
    }
    
    /**
     * 大分類を取得します。
     * @return 大分類
     */
    public String getBnrL() {
        return bnrL;
    }
    /**
     * 大分類を設定します。
     * @param bnrL 大分類
     */
    public void setBnrL(String bnrL) {
        this.bnrL = bnrL;
    }
    
    /**
     * 大分類名称を取得します。
     * @return 大分類名称
     */
    public String getBnrLName() {
        return bnrLName;
    }
    /**
     * 大分類名称を設定します。
     * @param bnrLName 大分類名称
     */
    public void setBnrLName(String bnrLName) {
        this.bnrLName = bnrLName;
    }
    
    /**
     * 中分類を取得します。
     * @return 中分類
     */
    public String getBnrM() {
        return bnrM;
    }
    /**
     * 中分類を設定します。
     * @param bnrM 中分類
     */
    public void setBnrM(String bnrM) {
        this.bnrM = bnrM;
    }
    
    /**
     * 中分類名称を取得します。
     * @return 中分類名称
     */
    public String getBnrMName() {
        return bnrMName;
    }
    /**
     * 中分類名称を設定します。
     * @param bnrMName 中分類名称
     */
    public void setBnrMName(String bnrMName) {
        this.bnrMName = bnrMName;
    }
    
    /**
     * 小分類を取得します。
     * @return 小分類
     */
    public String getBnrS() {
        return bnrS;
    }
    /**
     * 小分類を設定します。
     * @param bnrS 小分類
     */
    public void setBnrS(String bnrS) {
        this.bnrS = bnrS;
    }
    
    /**
     * 小分類名称を取得します。
     * @return 小分類名称
     */
    public String getBnrSName() {
        return bnrSName;
    }
    /**
     * 小分類名称を設定します。
     * @param bnrSName 小分類名称
     */
    public void setBnrSName(String bnrSName) {
        this.bnrSName = bnrSName;
    }
    
    /**
     * 原因を取得します。
     * @return 原因
     */
    public String getGenin() {
        return genin;
    }
    /**
     * 原因を設定します。
     * @param genin 原因
     */
    public void setGenin(String genin) {
        this.genin = genin;
    }
    
    /**
     * 内容を取得します。
     * @return 内容
     */
    public String getNaiyou() {
        return naiyou;
    }
    /**
     * 内容を設定します。
     * @param naiyou 内容
     */
    public void setNaiyou(String naiyou) {
        this.naiyou = naiyou;
    }
    
    /**
     * 店舗対応を取得します。
     * @return 店舗対応
     */
    public String getTenpoTaiou() {
        return tenpoTaiou;
    }
    /**
     * 店舗対応を設定します。
     * @param tenpoTaiou 店舗対応
     */
    public void setTenpoTaiou(String tenpoTaisaku) {
        this.tenpoTaiou = tenpoTaisaku;
    }
    
    /**
     * SV対策を取得します。
     * @return SV対策
     */
    public String getSvTaisaku() {
        return svTaisaku;
    }
    /**
     * SV対策を設定します。
     * @param svTaisaku SV対策
     */
    public void setSvTaisaku(String svTaisaku) {
        this.svTaisaku = svTaisaku;
    }
    
    /**
     * 受付番号を取得します。
     * @return 受付番号
     */
    public String getUketukeNo() {
        return uketukeNo;
    }
    /**
     * 受付番号を設定します。
     * @param uketukeNo 受付番号
     */
    public void setUketukeNo(String uketukeNo) {
        this.uketukeNo = uketukeNo;
    }
    
    /**
     * SV送信チェックを取得します。
     * @return SV送信チェック
     */
    public String getSvCheck() {
        return svCheck;
    }
    /**
     * SV送信チェックを設定します。
     * @param svCheck SV送信チェック
     */
    public void setSvCheck(String svCheck) {
        this.svCheck = svCheck;
    }
    
    /**
     * 公開フラグを取得します。
     * @return 公開フラグ
     */
    public String getKokaiFlg() {
        return kokaiFlg;
    }
    /**
     * 公開フラグを設定します。
     * @param kokaiFlg 公開フラグ
     */
    public void setKokaiFlg(String kokaiFlg) {
        this.kokaiFlg = kokaiFlg;
    }
    
    /**
     * タイプコードを取得します。
     * @return タイプコード
     */
    public String getTypeCd() {
        return typeCd;
    }
    /**
     * タイプコードを設定します。
     * @param typeCd タイプコード
     */
    public void setTypeCd(String typeCd) {
        this.typeCd = typeCd;
    }
    
    /**
     * タイプ名称を取得します。
     * @return タイプ名称
     */
    public String getTypeName() {
        return typeName;
    }
    /**
     * タイプ名称を設定します。
     * @param typeName タイプ名称
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    
    /**
     * 回答を取得します。
     * @return 回答
     */
    public String getKaitou() {
        return kaitou;
    }
    /**
     * 回答を設定します。
     * @param kaitou 回答
     */
    public void setKaitou(String kaitou) {
        this.kaitou = kaitou;
    }
    
    /**
     * 詳細を取得します。
     * @return 詳細
     */
    public String getShousai() {
        return shousai;
    }
    /**
     * 詳細を設定します。
     * @param shousai 詳細
     */
    public void setShousai(String shousai) {
        this.shousai = shousai;
    }
    
    /**
     * 閉店フラグを取得します。
     * @return 閉店フラグ
     */
    public String getCloseFlg() {
        return closeFlg;
    }
    /**
     * 閉店フラグを設定します。
     * @param closeFlg 閉店フラグ
     */
    public void setCloseFlg(String closeFlg) {
        this.closeFlg = closeFlg;
    }
	/**
	 * SV担当者名称取得処理
	 * 
	 * @return svNameKj を戻します。
	 */
	public String getSvNameKj() {
		return svNameKj;
	}
	/**
	 * SV担当者名称設定処理
	 * 
	 * @param svNameKj 設定する svNameKj。
	 */
	public void setSvNameKj(String svNameKj) {
		this.svNameKj = svNameKj;
	}
	/**
	 * @return sibuCd を戻します。
	 */
	public String getSibuCd() {
		return sibuCd;
	}
	/**
	 * @param sibuCd 設定する sibuCd。
	 */
	public void setSibuCd(String sibuCd) {
		this.sibuCd = sibuCd;
	}
	/**
	 * @return sibuName を戻します。
	 */
	public String getSibuName() {
		return sibuName;
	}
	/**
	 * @param sibuName 設定する sibuName。
	 */
	public void setSibuName(String sibuName) {
		this.sibuName = sibuName;
	}
	/**
	 * 状況取得処理
	 * 
	 * 完了日が設定されている場合は”完了”の文字列を戻します。
	 * 上記以外は空を戻します。
	 * @return
	 */
	public String getJokyo() {
		if(!CommonUtil.isNull(getKanryoDt())) {
			return "完了";
		}
		return "";
	}
	/**
	 * @return seq を戻します。
	 */
	public String getSeq() {
		return seq;
	}
	/**
	 * @param seq 設定する seq。
	 */
	public void setSeq(String seq) {
		this.seq = seq;
	}
    
}
