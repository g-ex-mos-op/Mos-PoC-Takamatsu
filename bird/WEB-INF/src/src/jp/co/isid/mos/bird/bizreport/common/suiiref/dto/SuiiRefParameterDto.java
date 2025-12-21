package jp.co.isid.mos.bird.bizreport.common.suiiref.dto;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.bizreport.common.code.TenpoShubetu;
import jp.co.isid.mos.bird.bizreport.common.common.BizReportConstants;
import jp.co.isid.mos.bird.bizreport.common.dto.NipoRefConditionParameterDto;
import jp.co.isid.mos.bird.bizreport.common.entity.CodCompany;
import jp.co.isid.mos.bird.bizreport.common.suiiref.code.TaishoJoken;
import jp.co.isid.mos.bird.bizreport.common.suiiref.code.TaishoKikan;
import jp.co.isid.mos.bird.bizreport.common.suiiref.code.ZennenDataShubetu;
import jp.co.isid.mos.bird.bizreport.common.suiiref.util.SuiiRefUtil;
import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;

/**
 * 推移表共通DTO【検索条件】
 * 
 * 作成日:2013/04/25
 * @author xkinu
 *
 */
public class SuiiRefParameterDto implements CsvOutputDto {
	/**
	 * ウィインドウID
	 */
	private int windowId;

	/** 初期アクションフラグ */
    private boolean initActionFlg = false;
    /**
     * 再検索制御フラグ
     * ※検索の結果取得後は、再検索とする
     * true:『再検索』
     * false:『検索』
     */
    private boolean researchFlg = false;
    
    /** 換算フラグ */
    private String kansanFlg = "";
    
    /** CodCompany[会社] */
    private CodCompany codCompany;

    /** 
     * List[[対象条件]]
     */
    private List listTaishoJoken = new ArrayList(0);
    /**
     * 再検索制御フラグを取得する
     * @return
     */
    public boolean isResearchFlg() {
        return researchFlg;
    }
    /**
     * 再検索制御フラグを設定する
     * @param researchFlg
     */
    public void setResearchFlg(boolean researchFlg) {
        this.researchFlg = researchFlg;
    }
    /**
	 * 月次・日次タブ区分
	 */
	private String tabKbn = BizReportConstants.SUB_TAG_0;

    /**
     * 会社コード
     */
    private String companyCd = CommonUtil.COMPANY_CD_MOS;
    /**
     * 会社名称
     */
    private String companyName = "";
    /**
     * 店舗種別コード
     */
    private String tenpoShubetu = TenpoShubetu.CODE_ALL;

    /**
     * 対象期間コード
     */
    private String taishoKikan = TaishoKikan.CODE_MONTH;

    /**
     * 期間指定(日付)
     */
    private String kikanSitei = "";
    /**
     * 対象条件
     */
    private String taishoJoken = TaishoJoken.CODE_ALL;
    /**
     * 対象条件名称
     */
    private String taishoJokenName = "";
    /**
     * 表示対象
     */
    private String hyojiTaisho = "";
    /**
     * 表示対象名称
     */
    private String hyojiTaishoName = "";
    /**
     * ブロックコード
     */
    private String blockCd = "";
    /**
     * ブロック名称
     */
    private String blockName= "";
    /**
     * 前年データ種別
     */
    private String zennenDataShubetu = "";
    
    /**
     * サブタブ区分
     */
    private String focusTab = "";
    
    /**
     *  海外用レイアウト　
     */
    private String layoutFlg ="0";

	/**
	 * 所属支部（個店検索時)
	 */
	private String syozokuSibu= "";
	/**
	 * ユーザタイプ
	 */
	private String userType= "";

//add 2019/07/18 #35 USI張 begin
  	/** モスダイニング RC */
      public static final String SIBU_CD_RC = NipoRefConditionParameterDto.SIBU_CD_RC;
  	/** モスダイニング FC */
      public static final String SIBU_CD_FC = NipoRefConditionParameterDto.SIBU_CD_FC;
      /** コード値：支部 */
      public static final String CODE_SIBU  = jp.co.isid.mos.bird.common.code.TaishoJoken.CODE_SIBU;
 	  /** コード値：個店 */
	  public static final String CODE_MISE = jp.co.isid.mos.bird.common.code.TaishoJoken.CODE_MISE;
      /** * 値引関連項目表示フラグ */
      private boolean nebikiFlg = false;
//add 2019/07/18 #35 USI張 end

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
	 * 月次・日次タブ区分を取得する
	 * @return サブタグ区分
	 */
	public String getTabKbn() {
		return tabKbn;
	}

	/**
	 * 月次・日次タブ区分を設定する
	 * @param subTagKbn サブタグ区分
	 */
	public void setTabKbn(String subTagKbn) {
		this.tabKbn = subTagKbn;
	}
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
    public String getTenpoShubetu() {
        return tenpoShubetu;
    }

    /**
     * 店舗種別を設定する
     * @param code 店舗種別
     */
    public void setTenpoShubetu(String code) {
        this.tenpoShubetu = code;
    }

	/**
	 * 所属支部コードを取得する
	 * @return 所属支部コード
	 */
	public String getSyozokuSibu() {
		return syozokuSibu;
	}

	/**
	 * 所属支部コードを設定する
	 * @param syozokuSibu 所属支部コード
	 */
	public void setSyozokuSibu(String syozokuSibu) {
		this.syozokuSibu = syozokuSibu;
	}

    /**
     * 対象期間を取得する
     * @return 対象期間
     */
    public String getTaishoKikan() {
        return taishoKikan;
    }

    /**
     * 対象期間を設定する
     * @param taishoKikan 対象期間
     */
    public void setTaishoKikan(String taishoKikan) {
        this.taishoKikan = taishoKikan;
    }
    /**
     * 前年データ種別を取得する
     * @return 前年データ種別
     */
    public String getZennenDataShubetu() {
        return zennenDataShubetu;
    }

    /**
     * 前年データ種別を設定する
     * @param value 前年データ種別
     */
    public void setZennenDataShubetu(String value) {
        this.zennenDataShubetu = value;
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
    public boolean isSelectedZennenTaishoTen () {
    	return TenpoShubetu.CODE_ZENNEN.equals(getTenpoShubetu());
    }
    public boolean isMosCompany() {
    	return CommonUtil.COMPANY_CD_MOS.equals(getCompanyCd());
    }
    /**
     * 店舗種別名称
     * @return
     */
    public String getTenpoShubetuName() {
    	return TenpoShubetu.getName(getTenpoShubetu());
    }
    /**
     * 対象期間名称
     * @return
     */
    public String getTaishoKikanName() {
    	return TaishoKikan.getName(getTaishoKikan());
    }
    /**
     * 対象条件名称
     * @return
     */
    public String getTaishoJokenName() {
    	return taishoJokenName;
    }
	/**
	 * @param taishoJokenName を クラス変数taishoJokenNameへ設定します。
	 */
	public void setTaishoJokenName(String taishoJokenName) {
		this.taishoJokenName = taishoJokenName;
	}
    /**
     * 前年同月営業日補正判断処理
     * @return
     */
    public boolean isSelectHosei() {
    	return ZennenDataShubetu.CODE_HOSEI.equals(getZennenDataShubetu());
    }
    /**
     * 前年データ種別名称取得処理
     * @return
     */
    public String getZennenDataShubetuName() {
    		return ZennenDataShubetu.getName(
    				getZennenDataShubetu());
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
	 * @param entityCodCompany を クラス変数entityCodCompanyへ設定します。
	 */
	public void setCodCompany(CodCompany entityCodCompany) {
		this.codCompany = entityCodCompany;
	}
	/**
	 * 
	 * @param parameterDto
	 * @return
	 */
    public boolean equalsParams(SuiiRefParameterDto parameterDto) {
    	if (!SuiiRefUtil.equals(getCompanyCd(),parameterDto.getCompanyCd())) {
    		return false;
    	}
    	if (!SuiiRefUtil.equals(getTenpoShubetu() ,parameterDto.getTenpoShubetu())) {
    		return false;
    	}
    	if (!SuiiRefUtil.equals(getTaishoKikan() ,parameterDto.getTaishoKikan())) {
    		return false;
    	}
    	if (!SuiiRefUtil.equals(getKikanSitei() ,parameterDto.getKikanSitei())) {
    		return false;
    	}
    	if (!SuiiRefUtil.equals(getTaishoJoken() ,parameterDto.getTaishoJoken())) {
    		return false;
    	}
    	if (!SuiiRefUtil.equals(getHyojiTaisho() ,parameterDto.getHyojiTaisho())) {
    		return false;
    	}
    	if (!SuiiRefUtil.equals(getBlockCd() ,parameterDto.getBlockCd())) {
    		return false;
    	}
    	if (!SuiiRefUtil.equals(getZennenDataShubetu() ,parameterDto.getZennenDataShubetu())) {
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
	/**
	 * @return クラス変数kikanSitei を戻します。
	 */
	public String getKikanSitei() {
		return kikanSitei;
	}
	/**
	 * @param kikanSitei を クラス変数kikanSiteiへ設定します。
	 */
	public void setKikanSitei(String kikanSitei) {
		this.kikanSitei = kikanSitei;
	}
	/**
	 * @return クラス変数subTabKbn を戻します。
	 */
	public String getFocusTab() {
		return focusTab;
	}
	/**
	 * @param subTabKbn を クラス変数subTabKbnへ設定します。
	 */
	public void setFocusTab(String subTabKbn) {
		this.focusTab = subTabKbn;
	}
	/**
	 * List[[対象条件]]
	 * @return クラス変数listTaishoJoken を戻します。
	 */
	public List getListTaishoJoken() {
		return listTaishoJoken;
	}
	/**
	 * List[[対象条件]]
	 * @param listTaishoJoken を クラス変数listTaishoJokenへ設定します。
	 */
	public void setListTaishoJoken(List listTaishoJoken) {
		this.listTaishoJoken = listTaishoJoken;
	}
	/**
	 * @return クラス変数blockCd を戻します。
	 */
	public String getBlockCd() {
		return blockCd;
	}
	/**
	 * @param blockCd を クラス変数blockCdへ設定します。
	 */
	public void setBlockCd(String blockCd) {
		this.blockCd = blockCd;
	}
	/**
	 * @return クラス変数blockName を戻します。
	 */
	public String getBlockName() {
		return blockName;
	}
	/**
	 * @param blockName を クラス変数blockNameへ設定します。
	 */
	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}
	/**
	 * @return クラス変数hyojiTaishoName を戻します。
	 */
	public String getHyojiTaishoName() {
		if(TaishoJoken.CODE_ALL.equals(getTaishoJoken())) {
			return getTaishoJokenName();
		}
		return hyojiTaishoName;
	}
	/**
	 * @param hyojiTaishoName を クラス変数hyojiTaishoNameへ設定します。
	 */
	public void setHyojiTaishoName(String hyojiTaishoName) {
		this.hyojiTaishoName = hyojiTaishoName;
	}
	/**
	 * 出力用 表示対象ラベル
	 * @return
	 */
	public String getHyojiTaishoLabel() {
		String label = getHyojiTaisho() + " "+getHyojiTaishoName();
		if(TaishoJoken.CODE_ALL.equals(getTaishoJoken())) {
			label = getHyojiTaishoName();
		}
		else if(TaishoJoken.CODE_SEGMENT.equals(getTaishoJoken())) {
			label = getHyojiTaishoName();
		}
		else if(TaishoJoken.CODE_SIBU.equals(getTaishoJoken())) {
			label += " "+ getBlockName();
		}
		return label;
	}
	/**
	 * 出力用 期間指定ラベル
	 * @return
	 */
	public String getHyojiKikanShiteLabel() {
		String label = getHyojiTaisho() + " "+getHyojiTaishoName();
		if(TaishoKikan.CODE_MONTH.equals(getTaishoKikan())) {
			label = getKikanSitei().substring(0,4) + "/" + getKikanSitei().substring(4,6);
		}
		else if(TaishoKikan.CODE_NENDO.equals(getTaishoKikan())) {
			label = getKikanSitei()+"年度";
		}else{
			label = getKikanSitei().substring(0,4) + "/" + getKikanSitei().substring(4,6);
		}
		return label;
	}
	/**
	 * 結果データ月次判断処理
	 * @return
	 */
	public boolean isSuiiTypeGepo() {
		return SuiiRefUtil.SUII_TYPE_GEPO.equals(getFocusTab());
	}
	/**
	 * 期間開始年月
	 * @return
	 */
	public String getKikanSiteiFrom() {
		return SuiiRefUtil.getKikanSiteiFrom(getTaishoKikan(), getKikanSitei());
	}
	/**
	 * 期間終了年月
	 * @return
	 */
	public String getKikanSiteiTo() {
		return SuiiRefUtil.getKikanSiteiTo(getTaishoKikan(), getKikanSitei());
	}
	/**
	 * 期間開始年月
	 * @return
	 */
	public String getKikanSiteiFromAddDay() {
		return SuiiRefUtil.getKikanSiteiFrom(getTaishoKikan(), getKikanSitei())+"01";
	}
	/**
	 * 期間終了年月
	 * @return
	 */
	public String getKikanSiteiToAddDay() {
		return SuiiRefUtil.getKikanSiteiTo(getTaishoKikan(), getKikanSitei())+"31";
	}
	/**
	 * フォーカスタブ開始年月日
	 * @return
	 */
	public String getFocusTabFromAddDay() {
		return getFocusTab()+"01";
	}
	/**
	 * フォーカスタブ終了年月日
	 * @return
	 */
	public String getFocusTabToAddDay() {
		return getFocusTab()+"31";
	}
	public boolean isExistsBlockCd() {
		return !CommonUtil.isNull(getBlockCd());
	}
    
    /**
     * String[[レイアウトフラグ]]を取得する
     * @return layoutFlg レイアウトフラグ
     */
    public String getLayoutFlg() {
        return layoutFlg;
    }

    /**
     * String[[レイアウトフラグ]]を設定する
     * @param layoutFlg レイアウトフラグ
     */
    public void setLayoutFlg(String layoutFlg) {
        this.layoutFlg = layoutFlg;
    }

//add 2019/07/18 #35 USI張 begin
    /**
     * 値引表示可否フラグを取得する
     * @return 値引表示可否フラグ
     */
    public boolean getNebikiFlg() {
    	//値引き項目表示する条件：
		//①対象条件＝「支部」＆＆（モスダイニングRCかモスダイニングFC）
		//②対象条件＝「個店」＆＆（店舗の所属はモスダイニングRCかモスダイニングFC）
        return (
				UserType.isHonbu(this.userType) &&
				(
        		CODE_SIBU.equals(this.taishoJoken)&& (SIBU_CD_FC.equals(this.hyojiTaisho) || SIBU_CD_RC.equals(this.hyojiTaisho))
				||
				CODE_MISE.equals(this.taishoJoken)&& (SIBU_CD_FC.equals(this.syozokuSibu) || SIBU_CD_RC.equals(this.syozokuSibu))
				)
		);
    }

    /**
     * 値引表示可否フラグを設定する
     * @param flg 値引表示可否フラグ
     */
    public void setNebikiFlg(boolean flg) {
        this.nebikiFlg = flg;
    }

	/**
	 * ユーザタイプを設定する
	 * @param userType ユーザタイプ
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}

	/**
	 * ユーザタイプを取得する
	 * @return ユーザタイプ
	 */
	public String getUserType() {
		return userType;
	}

//add 2019/07/18 #35 USI張 end
}