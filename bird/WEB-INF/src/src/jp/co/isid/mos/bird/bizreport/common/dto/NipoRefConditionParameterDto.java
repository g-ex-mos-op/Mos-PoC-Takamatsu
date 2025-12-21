package jp.co.isid.mos.bird.bizreport.common.dto;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.bizreport.common.code.Kibetu;
import jp.co.isid.mos.bird.bizreport.common.code.NipoZennenDataShubetu;
import jp.co.isid.mos.bird.bizreport.common.code.ShukeiKbn;
import jp.co.isid.mos.bird.bizreport.common.code.TaishoKikan;
import jp.co.isid.mos.bird.bizreport.common.code.TaishoTenpo;
import jp.co.isid.mos.bird.bizreport.common.code.TenpoShubetu;
import jp.co.isid.mos.bird.bizreport.common.common.BizReportConstants;
import jp.co.isid.mos.bird.bizreport.common.entity.CodCompany;
import jp.co.isid.mos.bird.bizreport.common.util.NipoRefUtil;
import jp.co.isid.mos.bird.common.code.TaishoJoken;
import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;

/**
 * 日報共通DTO【Request情報】
 *
 * 作成日:2013/01/10
 * @author xkinu
 *
 */
public class NipoRefConditionParameterDto implements CsvOutputDto {

    //begin add 2019/02/22 xou.zoubun モスダイニング支部時、値引表示対応
	/** モスダイニング RC */
    public static final String SIBU_CD_RC = "090";
	/** モスダイニング FC */
    public static final String SIBU_CD_FC = "091";
	/** 値引関連項目表示フラグ */
    private boolean nebikiFlg = false;
    //end add 2019/02/22 xou.zoubun モスダイニング支部時、値引表示対応

    /** 初期アクションフラグ */
    private boolean initActionFlg = false;
    /**
     * 再検索制御フラグ
     * ※検索の結果取得後は、再検索とする
     * true:『再検索』
     * false:『検索』
     */
    private boolean researchFlg = false;
    /** 換算フラグ 2013/02追加 */
    private String kansanFlg = "";
    /** 会社エンティティ 2013/02追加 */
    private CodCompany codCompany;
    /**
     * 再検索制御フラグを取得する
     * @return
     */
    public boolean isResearchFlg() {
        return researchFlg;
    }
    /**
     * 再検索制御フラグを設定する
     * @param researchBtnFlg
     */
    public void setResearchFlg(boolean researchFlg) {
        this.researchFlg = researchFlg;
    }

    /**
     * CTL【ログインユーザー情報】
     */
    private BirdUserInfo birdUserInfo;
    /**
     * CTL【e-mossles日付情報】
     */
    private BirdDateInfo birdDateInfo;
    /**
     * ログインユーザ情報取得する
     * @return BirdUserInfo ログインユーザ情報
     */
    public BirdUserInfo getBirdUserInfo() {
        return birdUserInfo;
    }

    /**
     * ログインユーザ情報を設定する
     * @param birdUserInfo ログインユーザ情報
     */
    public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
        this.birdUserInfo = birdUserInfo;
    }
	/**
	 * ウィインドウID
	 */
	private int windowId;
    /**
	 * サブタグ区分
	 */
	private String subTagKbn = BizReportConstants.SUB_TAG_0;

    /**
     * 管理会社企業情報
     */
    private String companyCd = CommonUtil.COMPANY_CD_MOS;
    /**
     * 管理会社企業情報
     */
    private String companyName;
    /**
     * 店舗種別情報
     */
    private String tenpoShubetuCd = TenpoShubetu.CODE_ALL;

    /**
     * 集計区分情報
     */
    private String shukeiKbnCd = ShukeiKbn.OUT_RC;

    /**
     * 対象店舗情報
     */
    private String taishoTenpoCd = TaishoTenpo.ALL;
    /**
     * 対象期間情報
     */
    private String taishoKikanCd = TaishoKikan.DAY1;

    /**
     * 期間指定:期日指定日報
     */
    private String kikanNipo;
    /**
     * 期間指定:月別月報
     */
    private String kikanYM;

    /**
     * 期間指定:期別年
     */
    private String kikanYear;

    /**
     * 期間指定:期別
     */
    private String kikanKibetu;

    /**
     * 期間指定:期間指定From
     */
    private String kikanFrom;

    /**
     * 期間指定:期間指定To
     */
    private String kikanTo;
    /**
     * 期間指定:当月月報
     */
    private String kikanCurrMonth;


    /**
     * 前年データ種別(前年対象店)
     */
    private String zenDataShubetu ;
    /**
     * 前年データ種別(前年対象店以外)
     */
    private String zenDataZennenOthCd;
    /**
     * 対象支部コード
     */
    private String sibuCd;

    /**
     * 対象支部名称
     */
    private String sibuName;

    /**
     * 行CSSクラス名
     */
    private String className;

    /**
     * SVコード
     */
    private String svCd;

    /**
     * SV名称
     */
    private String svName;

    private String kikanGepoYM;

    private List taishoSibuList = new ArrayList(0);

    private String taishoJoken = TaishoJoken.CODE_ALL;

    private String hyojiTaisho;
    /**
     * 初期アクションフラグを取得する
     * @return initActionFlg 初期アクションフラグ
     */
    public boolean isInitActionFlg() {
        return this.initActionFlg;
    }

    /**
     * 初期アクションフラグを設定する
     * @param initActionFlg 初期アクションフラグ
     */
    public void setInitActionFlg(boolean initActionFlg) {
        this.initActionFlg = initActionFlg;
    }
	/**
	 * ウィンドウIDを取得する
	 * @return ウィインドウID
	 */
	public int getWindowId() {
		return windowId;
	}

	/**
	 * ウィンドウIDを設定する
	 * @param windowId ウィンドウID
	 */
	public void setWindowId(int windowId) {
		this.windowId = windowId;
	}

	/**
	 * サブタグ区分を取得する
	 * @return サブタグ区分
	 */
	public String getSubTagKbn() {
		return subTagKbn;
	}

	/**
	 * サブタグ区分を設定する
	 * @param subTagKbn サブタグ区分
	 */
	public void setSubTagKbn(String subTagKbn) {
		this.subTagKbn = subTagKbn;
	}

    /**
	 * @return クラス変数kikanGepoYM を戻します。
	 */
	public String getKikanGepoYM() {
		return kikanGepoYM;
	}

	/**
	 * @param kikanGepoYM を クラス変数kikanGepoYMへ設定します。
	 */
	public void setKikanGepoYM(String kikanGepoYM) {
		this.kikanGepoYM = kikanGepoYM;
	}

	// 検索条件getter/setter
    /**
     * 会社コードを取得する
     * @return 会社コード
     */
    public String getCompanyCd() {
        return companyCd;
    }

    /**
     * 会社コードを設定する
     * @param companyCd 会社コード
     */
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }

    /**
     * 店舗種別を取得する
     * @return 店舗種別
     */
    public String getTenpoShubetuCd() {
        return tenpoShubetuCd;
    }

    /**
     * 店舗種別を設定する
     * @param tenpoShubetuCd 店舗種別
     */
    public void setTenpoShubetuCd(String tenpoShubetuCd) {
        this.tenpoShubetuCd = tenpoShubetuCd;
    }

    /**
     * 集計区分を取得する
     * @return 集計区分
     */
    public String getShukeiKbnCd() {
        return shukeiKbnCd;
    }

    /**
     * 集計区分を設定する
     * @param shukeiKbnCd 集計区分
     */
    public void setShukeiKbnCd(String shukeiKbnCd) {
        this.shukeiKbnCd = shukeiKbnCd;
    }

    /**
     * 対象店舗を取得する
     * @return 対象店舗
     */
    public String getTaishoTenpoCd() {
        return taishoTenpoCd;
    }

    /**
     * 対象店舗を設定する
     * @param taishoTenpoCd 対象店舗
     */
    public void setTaishoTenpoCd(String taishoTenpoCd) {
        this.taishoTenpoCd = taishoTenpoCd;
    }

    /**
     * 対象期間を取得する
     * @return 対象期間
     */
    public String getTaishoKikanCd() {
        return taishoKikanCd;
    }

    /**
     * 対象期間を設定する
     * @param taishoKikanCd 対象期間
     */
    public void setTaishoKikanCd(String taishoKikanCd) {
        this.taishoKikanCd = taishoKikanCd;
    }

    /**
     * 期間指定:期日期間日報を取得する
     * @return 期間指定:期日期間日報
     */
    public String getKikanNipo() {
        return kikanNipo;
    }

    /**
     * 期間指定:期日期間日報を設定する
     * @param kikanNipo 期間指定:期日期間日報
     */
    public void setKikanNipo(String kikanNipo) {
        this.kikanNipo = kikanNipo;
    }

    /**
     * 期間指定:月別月報を取得する
     * @return 期間指定:月別月報
     */
    public String getKikanYM() {
        return kikanYM;
    }

    /**
     * 期間指定:月別月報を設定する
     * @param kikanYM 期間指定:月別月報
     */
    public void setKikanYM(String kikanYM) {
        this.kikanYM = kikanYM;
    }

    /**
     * 期間指定:期別年を取得する
     * @return 期間指定:期別年
     */
    public String getKikanYear() {
        return kikanYear;
    }

    /**
     * 期間指定:期別年を設定する
     * @param kikanYear 期間指定:期別年
     */
    public void setKikanYear(String kikanYear) {
        this.kikanYear = kikanYear;
    }

    /**
     * 期間指定:期別期を取得する
     * @return 期間指定:期別期
     */
    public String getKikanKibetu() {
        return kikanKibetu;
    }

    /**
     * 期間指定:期別期を設定する
     * @param kikanKibetu 期間指定:期別期
     */
    public void setKikanKibetu(String kikanKibetu) {
        this.kikanKibetu = kikanKibetu;
    }

    /**
     * 期間指定:期間指定Fromを取得する
     * @return 期間指定:期間指定From
     */
    public String getKikanFrom() {
        return kikanFrom;
    }

    /**
     * 期間指定:期間指定Fromを設定する
     * @param kikanFrom 期間指定:期間指定From
     */
    public void setKikanFrom(String kikanFrom) {
        this.kikanFrom = kikanFrom;
    }

    /**
     * 期間指定:期間指定Toを取得する
     * @return 期間指定:期間指定To
     */
    public String getKikanTo() {
        return kikanTo;
    }

    /**
     * 期間指定:期間指定Toを設定する
     * @param kikanTo 期間指定:期間指定To
     */
    public void setKikanTo(String kikanTo) {
        this.kikanTo = kikanTo;
    }

    /**
     * 前年データ種別(前年対象店用)を取得する
     * @return 前年データ種別(前年対象店)
     */
    public String getZenDataZennenCd() {
        return zenDataShubetu;
    }

    /**
     * 前年データ種別(前年対象店用)を設定する
     * @param value 前年データ種別
     */
    public void setZenDataZennenCd(String value) {
        this.zenDataShubetu = value;
    }

    /**
     * 対象支部コードを取得する
     * @return 対象支部コード
     */
    public String getSibuCd() {
        return sibuCd;
    }

    /**
     * 対象支部コードを設定する
     * @param sibuCd 対象支部コード
     */
    public void setSibuCd(String sibuCd) {
        this.sibuCd = CommonUtil.isNull(sibuCd)?sibuCd:sibuCd.trim();
    }

    //begin add 2019/02/22 xou.zoubun モスダイニング支部時、値引表示対応
    /**
     * 値引表示可否フラグを取得する
     * @return 値引表示可否フラグ
     */
    public boolean getNebikiFlg() {
        return this.isAllowedDispNebiki();
    }

    /**
     * 値引表示可否フラグを設定する
     * @param flg 値引表示可否フラグ
     */
    public void setNebikiFlg(boolean flg) {
        this.nebikiFlg = flg;
    }

    /**
     * 値引表示可否フラグを取得する
     */
    public boolean isAllowedDispNebiki() {
    	this.nebikiFlg = (SIBU_CD_RC.equals(this.sibuCd) || SIBU_CD_FC.equals(this.sibuCd)) && !this.isSvFlg();
        return this.nebikiFlg;
    }
    //end add 2019/02/22 xou.zoubun モスダイニング支部時、値引表示対応

    /**
     * 対象支部名を取得する
     * @return 対象支部名
     */
    public String getSibuName() {
        return sibuName;
    }

    /**
     * 対象支部名を設定する
     * @param sibuName 対象支部名
     */
    public void setSibuName(String sibuName) {
        this.sibuName = sibuName;
    }

    /**
     * 行CSSクラス名を取得する
     * @return 行CSSクラス
     */
    public String getClassName() {
        return className;
    }

    /**
     * 行CSSクラスを設定する
     * @param className 行CSSクラス
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * SVコードを取得する。2008/12/09 追加
     * @return svCd
     */
    public String getSvCd() {
        return svCd;
    }
    /**
     * SVコードを設定する。2008/12/09 追加
     * @param svCd
     */
    public void setSvCd(String svCd) {
        this.svCd = svCd;
    }

    /**
     * SV名称を取得する。2008/12/09 追加
     * @return svName
     */
    public String getSvName() {
        return svName;
    }
    /**
     * SV名称を設定する。2008/12/09 追加
     * @param svName
     */
    public void setSvName(String svName) {
        this.svName = svName;
    }

	/**
	 * 前年データ種別(前年対象店以外)
	 * @return クラス変数zenDataZennenOthCd を戻します。
	 */
	public String getZenDataZennenOthCd() {
		return zenDataZennenOthCd;
	}

	/**
	 * 前年データ種別(前年対象店以外)
	 * @param zenDataZennenOthCd を クラス変数zenDataZennenOthCdへ設定します。
	 */
	public void setZenDataZennenOthCd(String zenDataZennenOthCd) {
		this.zenDataZennenOthCd = zenDataZennenOthCd;
	}
    /**
     * SV指定(担当店一覧)判断フラグ
     *
     * @return
     */
    public boolean isSvFlg() {
    	return ShukeiKbn.SV_CD.equals(getShukeiKbnCd());
    }
    /**
     * 対象期間が「営業月報抽出」か否か
     * @return
     */
    public boolean isGepoCsv() {
    	return BizReportConstants.GEPOCSV.equals(getTaishoKikanCd());
    }
    /**
     * 表示対象名称(SVコード + SV名称) 2008/12/09 追加
     * @return hyojiTaishoName を戻します。
     */
    public String getHyojiSvName() {
        String strSvCd   = getSvCd();
        String strSvName = getSvName();

        if (CommonUtil.isNull(strSvCd) || CommonUtil.isNull(strSvName)) {
            return "";
        }

        // SVコードの前ゼロ付加
        CodeFormatter formatter = new CodeFormatter(8);
        formatter.setFormatPattern("00000000");
        if (strSvCd != null && !strSvCd.trim().equals("")) {
            strSvCd = formatter.format(strSvCd, true);
        }

        return " " + strSvCd + " " + strSvName.trim();
    }

	/**
	 * @return クラス変数birdDateInfo を戻します。
	 */
	public BirdDateInfo getBirdDateInfo() {
		return birdDateInfo;
	}

	/**
	 * @param birdDateInfo を クラス変数birdDateInfoへ設定します。
	 */
	public void setBirdDateInfo(BirdDateInfo birdDateInfo) {
		this.birdDateInfo = birdDateInfo;
	}

	/**
	 * @return クラス変数companyName を戻します。
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName を クラス変数companyNameへ設定します。
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	/**
	 * @return クラス変数kikanCurrMonth を戻します。
	 */
	public String getKikanCurrMonth() {
		return kikanCurrMonth;
	}

	/**
	 * @param kikanCurrMonth を クラス変数kikanCurrMonthへ設定します。
	 */
	public void setKikanCurrMonth(String kikanCurrMonth) {
		this.kikanCurrMonth = kikanCurrMonth;
	}

	/**
	 * @return クラス変数taishoSibuList を戻します。
	 */
	public List getTaishoSibuList() {
		return taishoSibuList;
	}

	/**
	 * @param taishoSibuList を クラス変数taishoSibuListへ設定します。
	 */
	public void setTaishoSibuList(List taishoSibuList) {
		this.taishoSibuList = taishoSibuList;
	}
    /**
     * 対象支部リストをデータ有無判断処理
     * @return boolean true:データ有り
     */
    public boolean isExistTaishoSibuList() {
        return (null!=getTaishoSibuList() && getTaishoSibuList().size()>0?true:false);
    }

    public boolean isTaishoKikan1Day() {
    	return TaishoKikan.DAY1.equals(getTaishoKikanCd());
    }
    public boolean isSelectedZennenTaishoTen () {
    	return TenpoShubetu.CODE_ZENNEN.equals(getTenpoShubetuCd());
    }
    public boolean isMosCompany() {
    	return CommonUtil.COMPANY_CD_MOS.equals(getCompanyCd());
    }

	/**
     * ブロック表示判断値
     *
     * @return
     */
    public boolean isDispBlock() {
    	return (isMosCompany() && (isSvFlg() || ShukeiKbn.OUT_RC.equals(getShukeiKbnCd())));
    }
    /**
     * 平均値表示判断処理
     *
     * @return　true:平均値有り false:平均値無し
     */
    public boolean isAveDispFlg() {
    	return !(isSelectedZennenTaishoTen()
		&& NipoZennenDataShubetu.CODE_DOYO.equals(getZenDataZennenCd()));
    }
    /**
     * 店舗種別名称
     * @return
     */
    public String getLabelTenpoShubetu() {
    	return TenpoShubetu.getName(getTenpoShubetuCd());
    }
    /**
     * 集計区分名称
     * @return
     */
    public String getLabelShukeiKbn() {
    	return ShukeiKbn.getName(getShukeiKbnCd());
    }
    /**
     * 対象店舗名称
     * @return
     */
    public String getLabelTaishoTenpo() {
    	return TaishoTenpo.getName(getTaishoTenpoCd());
    }
    /**
     * 対象期間名称
     */
    public String getLabelTaishoKikan() {
    	String labelTaishoKikan = TaishoKikan.getName(getUserTypeCd(), getTaishoKikanCd());
    	if(CommonUtil.isNull(labelTaishoKikan)) {
    		if(BizReportConstants.CODE_GEPOCSV[0][0].equals(getTaishoKikanCd())) {
    			labelTaishoKikan = BizReportConstants.CODE_GEPOCSV[0][1];
    		}
    	}
    	return labelTaishoKikan;
    }
    /**
     * 期間指定値取得
     * @return
     */
    public String getLabelKikanSiteiFrom() {
		// 日付フォーマッタ
		DateFormatter df = new DateFormatter();

		// 期日指定日報
		if (TaishoKikan.DAY1.equals(getTaishoKikanCd())) {
			return (df.format(getKikanNipo(),
				DateFormatter.PATTERN_SLASH, DateFormatter.DATE_TYPE_YMD));
		// 当月月報
		} else if (TaishoKikan.MONTHAPP.equals(getTaishoKikanCd())) {
			// 処理無し
			return "";
		// 月別月報告
		} else if (TaishoKikan.MONTH.equals(getTaishoKikanCd())) {
			return (df.format(getKikanYM(),
				DateFormatter.PATTERN_MONTH_SLASH, DateFormatter.DATE_TYPE_YM));
		// 期別期報
		} else if (TaishoKikan.KIBETU.equals(getTaishoKikanCd())) {
			return (getKikanYear());
		// 期間指定
		} else if (TaishoKikan.DAYS.equals(getTaishoKikanCd())) {
			return (df.format(getKikanFrom(),
				DateFormatter.PATTERN_SLASH, DateFormatter.DATE_TYPE_YMD));
		}
		return "";
    }
    /**
     * 期間指定値取得
     * @return
     */
    public String getLabelKikanSiteiTo() {
		// 日付フォーマッタ
		DateFormatter df = new DateFormatter();

		// 期別期報
		if (TaishoKikan.KIBETU.equals(getTaishoKikanCd())) {
			return (Kibetu.getName(getKikanKibetu()));
		// 期間指定
		} else if (TaishoKikan.DAYS.equals(getTaishoKikanCd())) {
			return (df.format(getKikanTo(),
					DateFormatter.PATTERN_SLASH, DateFormatter.DATE_TYPE_YMD));
		}
		return "";
    }
    public boolean isExistKikanSiteiFrom() {
    	return !CommonUtil.isNull(getLabelKikanSiteiFrom());
    }
    public boolean isExistKikanSiteiTo() {
    	return !CommonUtil.isNull(getLabelKikanSiteiTo());
    }
    /**
     * 前年同月営業日補正判断処理
     * @return
     */
    public boolean isSelectHosei() {
    	return NipoZennenDataShubetu.CODE_HOSEI.equals(getZennenDataShubetu());
    }
    /**
     * 前年データ種別名称取得処理
     * @return
     */
    public String getZennenDataShubetuName() {
    	String userTypeCd = getUserTypeCd();
    	if(isSelectedZennenTaishoTen()) {
    		//前年対象店の場合
    		return NipoZennenDataShubetu.getName(
    				getZenDataZennenCd(), userTypeCd);
    	}
    	else {
    		//前年対象店以外の場合
    		return NipoZennenDataShubetu.getName(
    				getZenDataZennenOthCd(), userTypeCd);
    	}
    }
    public String getUserTypeCd() {
    	if(getBirdUserInfo()!=null) {
    		return getBirdUserInfo().getMstUser().getUserTypeCd();
    	}
    	return "";
    }
    public boolean isLimitFlg() {
    	if(UserType.isHonbu(getUserTypeCd())) {
    		return getBirdUserInfo().isLimit();
    	}
    	return false;
    }
    private String linkParams;

	/**
	 * @return クラス変数linkParams を戻します。
	 */
	public String getLinkParams() {
		return linkParams;
	}
	/**
	 * @param linkParams を クラス変数linkParamsへ設定します。
	 */
	public void setLinkParams(String linkParams) {
		this.linkParams = linkParams;
	}
	/**
	 * 換算済判断フラグ
	 * @return クラス変数kansanFlg を戻します。
	 */
	public String getKansanFlg() {
		return kansanFlg;
	}
	/**
	 * 換算済判断フラグ
	 * @param kansanFlg を クラス変数kansanFlgへ設定します。
	 */
	public void setKansanFlg(String kansanFlg) {
		this.kansanFlg = kansanFlg;
	}
	/**
	 * 換算済判断フラグ
	 * @return
	 */
	public boolean isKansan() {
		return BizReportConstants.KANSAN_ON.equals(getKansanFlg());
	}
	/**
	 * @return クラス変数entityCodCompany を戻します。
	 */
	public CodCompany getCodCompany() {
		return codCompany;
	}
	/**
	 * @param codCompany を クラス変数entityCodCompanyへ設定します。
	 */
	public void setCodCompany(CodCompany entityCodCompany) {
		this.codCompany = entityCodCompany;
	}
	public String getKikanSiteiFrom() {
		// 期日指定日報
		if (TaishoKikan.DAY1.equals(getTaishoKikanCd())) {
			return getKikanNipo();
		// 当月月報
		} else if (TaishoKikan.MONTHAPP.equals(getTaishoKikanCd())) {
			// 処理無し
			return getKikanCurrMonth();
		// 月別月報告
		} else if (TaishoKikan.MONTH.equals(getTaishoKikanCd())) {
			return getKikanYM();
		// 期別期報
		} else if (TaishoKikan.KIBETU.equals(getTaishoKikanCd())) {
			return getKikanYear();
		// 期間指定
		} else if (TaishoKikan.DAYS.equals(getTaishoKikanCd())) {
			return getKikanFrom();
		}
		return "";
    }
    /**
     * 期間指定値取得
     * @return
     */
    public String getKikanSiteiTo() {
		// 期別期報
		if (TaishoKikan.KIBETU.equals(getTaishoKikanCd())) {
			return getKikanKibetu();
		// 期間指定
		} else if (TaishoKikan.DAYS.equals(getTaishoKikanCd())) {
			return getKikanTo();
		}
		return "";
    }
    public String getZennenDataShubetu() {
    	if(isSelectedZennenTaishoTen()) {
    		return getZenDataZennenCd();
    	}
    	return getZenDataZennenOthCd();
    }
	/**
	 *
	 * @param parameterDto
	 * @return
	 */
    public boolean equalsParams(NipoRefConditionParameterDto parameterDto) {
    	if (!NipoRefUtil.equals(getCompanyCd(),parameterDto.getCompanyCd())) {
    		return false;
    	}
    	if (!NipoRefUtil.equals(getTenpoShubetuCd() ,parameterDto.getTenpoShubetuCd())) {
    		return false;
    	}
    	if (!NipoRefUtil.equals(getTaishoTenpoCd() ,parameterDto.getTaishoTenpoCd())) {
    		return false;
    	}
    	if (!NipoRefUtil.equals(getShukeiKbnCd() ,parameterDto.getShukeiKbnCd())) {
    		return false;
    	}
    	if (!NipoRefUtil.equals(getTaishoKikanCd() ,parameterDto.getTaishoKikanCd())) {
    		return false;
    	}
    	if (!NipoRefUtil.equals(getKikanSiteiFrom() ,parameterDto.getKikanSiteiFrom())) {
    		return false;
    	}
    	if (!NipoRefUtil.equals(getKikanSiteiTo() ,parameterDto.getKikanSiteiTo())) {
    		return false;
    	}
    	if (!NipoRefUtil.equals(getZennenDataShubetu() ,parameterDto.getZennenDataShubetu())) {
    		return false;
    	}
    	if (!NipoRefUtil.equals(getSibuCd() ,parameterDto.getSibuCd())) {
    		return false;
    	}
    	if (!NipoRefUtil.equals(getSvCd() ,parameterDto.getSvCd())) {
    		return false;
    	}
    	if (!NipoRefUtil.equals(getTaishoTenpoCd() ,parameterDto.getTaishoTenpoCd())) {
    		return false;
    	}
    	if (!NipoRefUtil.equals(getLinkParams() ,parameterDto.getLinkParams())) {
    		return false;
    	}
    	return true;
    }
	/**
	 * @return クラス変数taishoJoken を戻します。
	 */
	public String getTaishoJoken() {
		return taishoJoken;
	}
	/**
	 * @param taishoJoken を クラス変数taishoJokenへ設定します。
	 */
	public void setTaishoJoken(String taishoJoken) {
		this.taishoJoken = taishoJoken;
	}
	/**
	 * @return クラス変数hyojiTaisho を戻します。
	 */
	public String getHyojiTaisho() {
		return hyojiTaisho;
	}
	/**
	 * @param hyojiTaisho を クラス変数hyojiTaishoへ設定します。
	 */
	public void setHyojiTaisho(String hyojiTaisho) {
		this.hyojiTaisho = hyojiTaisho;
	}
}