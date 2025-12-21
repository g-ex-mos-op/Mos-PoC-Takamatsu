/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.common.camp.dto;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.bizreport.campniporef.code.TaishoKikan;
import jp.co.isid.mos.bird.bizreport.common.camp.code.RankKind;
import jp.co.isid.mos.bird.bizreport.common.camp.code.ShukeiKbn;
import jp.co.isid.mos.bird.bizreport.common.camp.code.TaishoJoken;
import jp.co.isid.mos.bird.bizreport.common.camp.entity.MstCampDate;
import jp.co.isid.mos.bird.bizreport.common.camp.entity.UILists;
import jp.co.isid.mos.bird.bizreport.common.camp.util.CommonUtil;
import jp.co.isid.mos.bird.bizreport.common.entity.CodCompany;
import jp.co.isid.mos.bird.bizreport.common.entity.CodHyojiTaisho;
import jp.co.isid.mos.bird.bizreport.common.entity.CodKikanSitei;
import jp.co.isid.mos.bird.common.code.UserType;


/**
 * 日報系リクエスト用DTO
 * 
 * @author xkinu
 *
 */
public class RequestNipoDto extends RequestDto {
	
    /**
     * 集計区分
     */ 
    private String shukeiKbn;
    /**
     * 対象期間
     */ 
    private String taishoKikan;
    /**
     * 対象日
     */ 
    private String taishoDt;
	/** 対象終了日 */
	private String taishoDtTo;
    /**
     * 順位
     */ 
    private String rankKind;
    private List listsTaishoDt = new ArrayList(0);
	/**
	 * 集計区分取得処理
	 * @param windowId
	 * @return shukeiKbn を戻します。
	 */
	public String getShukeiKbn() {
		return shukeiKbn;
	}

	/**
	 * 集計区分設定処理
	 * @param windowId
	 * @param shukeiKbn 設定する shukeiKbn。
	 */
	public void setShukeiKbn(String shukeiKbn) {
		this.shukeiKbn = shukeiKbn;
	}
	/**
	 * 対象日プルダウンリスト
	 * @param campId
	 * @return
	 */
	public List getListsTaishoDt() {
		return listsTaishoDt;
	}
	/**
	 * @return taishoDt を戻します。
	 */
	public String getTaishoDt() {
		return taishoDt;
	}
	/**
	 * 
	 * @return
	 */
	public String getTaishoDtFmt() {
		return CommonUtil.formattYMDSlash(getTaishoDt());
	}
	/**
	 * @param taishoDt 設定する taishoDt。
	 */
	public void setTaishoDt(String taishoDt) {
		this.taishoDt = taishoDt;
	}

	/**
	 * 順位取得処理
	 * 
	 * @return rankKind を戻します。
	 */
	public String getRankKind() {
		return rankKind;
	}

	/**
	 * 順位設定処理
	 * 
	 * @param rankKbn 設定する rankKind。
	 */
	public void setRankKind(String rankKbn) {
		this.rankKind = rankKbn;
	}

	/**
	 * @return rankKbnName を戻します。
	 */
	public String getRankKindName() {
		return RankKind.getName(getRankKind());
	}

	/**
	 * @return shukeiKbnName を戻します。
	 */
	public String getShukeiKbnName() {
		return ShukeiKbn.getName(getShukeiKbn());
	}
	/**
	 * @param listsTaishoDt 設定する listsTaishoDt。
	 */
	public void setListsTaishoDt(List listsTaishoDt) {
		this.listsTaishoDt = listsTaishoDt;
	}
	/**
	 * 
	 * 条件初期値を設定します
	 */
	public void setInitialazeData(SessionNipoDto selfSessionDto) {
        //デフォルト会社コードを設定します。
		CodCompany com = (CodCompany)selfSessionDto.getListCompany().get(0);
		setCompanyCd(com.getCompanyCd());
        //デフォルト年度を設定します。
		CodKikanSitei cod = (CodKikanSitei)selfSessionDto.getListNendo(getCompanyCd()).get(0);
		setNendo(cod.getCode());
		
		//条件項目の表示プルダウンリストを設定します
		setPullDownData(selfSessionDto);
		if(getListsCamp() == null) {
			return;
		}
        //1．デフォルトキャンペーン識別番号
		MstCampDate mstCamp = (MstCampDate)getListsCamp().get(0);
		setCampId(mstCamp.getCampId());
		
		//２．デフォルトメニュー集計区分を設定します。
		SelectItem defaultData = (SelectItem)getSelfSessionDto().getListMenuTotaled().get(0);
		setMenuTotaledKbn((String)defaultData.getValue());
		
		//３．デフォルト店舗種別を設定します。
		defaultData = (SelectItem)getSelfSessionDto().getListTenpoShubetu().get(0);
		setTenpoShubetu((String)defaultData.getValue());
		
		//４．デフォルト集計区分を設定します。
		defaultData = (SelectItem)selfSessionDto.getListShukeiKbn().get(0);
		setShukeiKbn((String)defaultData.getValue());
		
		//５．デフォルト前年データ種別を設定します。
		UILists uiDefault = (UILists)getSelfSessionDto().getListsZennenDataShubetu().get(0);
		List listDefault = (List)uiDefault.getListData();
		defaultData = (SelectItem)listDefault.get(0);
		setZennenDataShubetu((String)defaultData.getValue());
		
		//６．デフォルト対象条件を設定します。
		String userTypeCd = getSelfSessionDto().getUserTypeCd();
		if(UserType.HONBU.equals(userTypeCd)) {
			setTaishoJoken(TaishoJoken.CODE_ALL);
			setHyojiTaisho(TaishoJoken.CODE_ALL);
		}
		else if(UserType.ONER.equals(userTypeCd)) {
			setTaishoJoken(TaishoJoken.CODE_ALL);
		}
		else if(UserType.TENPO.equals(userTypeCd)) {
			setTaishoJoken(TaishoJoken.CODE_MISE);
			UILists uiCampHyojiTaisho = (UILists)getListsHyojiTaisho().get(0);
			UILists uiTaishoJokenHyojiTaisho = (UILists)uiCampHyojiTaisho.getListData().get(0);
			CodHyojiTaisho eHyojiTaisho = (CodHyojiTaisho)uiTaishoJokenHyojiTaisho.getListData().get(0);
			setHyojiTaisho(eHyojiTaisho.getHyojitaishoCd());
		}
		
		//７．デフォルト対象日
		setTaishoDt("");
		//８．デフォルト順位
		setRankKind(RankKind.CODE_KINKOUSEIHI);
		//９．デフォルト対象期間==単日
		setTaishoKikan(TaishoKikan.DAY);
	}
    /**
     * 
     * 条件初期値を設定します
     */
    public void setInitialazeDataKako(SessionNipoDto selfSessionDto) {
        //デフォルト会社コードを設定します。
        CodCompany com = (CodCompany)selfSessionDto.getListCompany().get(0);
        setCompanyCd(com.getCompanyCd());
        //デフォルト年度を設定します。
        CodKikanSitei cod = (CodKikanSitei)selfSessionDto.getListNendo(getCompanyCd()).get(0);
        setNendo(cod.getCode());
        
        //条件項目の表示プルダウンリストを設定します
        setPullDownData(selfSessionDto);
        if(getListsCamp() != null && !getListsCamp().isEmpty()) {
            //1．デフォルトキャンペーン識別番号
            MstCampDate mstCamp = (MstCampDate)getListsCamp().get(0);
            setCampId(mstCamp.getCampId());
        }
        
        //２．デフォルトメニュー集計区分を設定します。
        SelectItem defaultData = (SelectItem)getSelfSessionDto().getListMenuTotaled().get(0);
        setMenuTotaledKbn((String)defaultData.getValue());
        
        //３．デフォルト店舗種別を設定します。
        defaultData = (SelectItem)getSelfSessionDto().getListTenpoShubetu().get(0);
        setTenpoShubetu((String)defaultData.getValue());
        
        //４．デフォルト集計区分を設定します。
        defaultData = (SelectItem)selfSessionDto.getListShukeiKbn().get(0);
        setShukeiKbn((String)defaultData.getValue());
        
        //５．デフォルト前年データ種別を設定します。
        UILists uiDefault = (UILists)getSelfSessionDto().getListsZennenDataShubetu().get(0);
        List listDefault = (List)uiDefault.getListData();
        defaultData = (SelectItem)listDefault.get(0);
        setZennenDataShubetu((String)defaultData.getValue());
        
        //６．デフォルト対象条件を設定します。
        String userTypeCd = getSelfSessionDto().getUserTypeCd();
        if(UserType.HONBU.equals(userTypeCd)) {
            setTaishoJoken(TaishoJoken.CODE_ALL);
            setHyojiTaisho(TaishoJoken.CODE_ALL);
        }
        else if(UserType.ONER.equals(userTypeCd)) {
            setTaishoJoken(TaishoJoken.CODE_ALL);
        }
        else if(UserType.TENPO.equals(userTypeCd)) {
            setTaishoJoken(TaishoJoken.CODE_MISE);
            UILists uiCampHyojiTaisho = null;
            UILists uiTaishoJokenHyojiTaisho = null;
            CodHyojiTaisho eHyojiTaisho = null;
            if (getListsHyojiTaisho() != null && !getListsHyojiTaisho().isEmpty()) {
                uiCampHyojiTaisho = (UILists)getListsHyojiTaisho().get(0);
            }
            if (uiCampHyojiTaisho != null && uiCampHyojiTaisho.getListData() != null && !uiCampHyojiTaisho.getListData().isEmpty()) {
                uiTaishoJokenHyojiTaisho = (UILists)uiCampHyojiTaisho.getListData().get(0);
            }
            if (uiTaishoJokenHyojiTaisho != null && uiTaishoJokenHyojiTaisho.getListData() != null && !uiTaishoJokenHyojiTaisho.getListData().isEmpty()) {
                eHyojiTaisho = (CodHyojiTaisho)uiTaishoJokenHyojiTaisho.getListData().get(0);
            }
            if (eHyojiTaisho != null) {
                setHyojiTaisho(eHyojiTaisho.getHyojitaishoCd());
            }
            else {
                setHyojiTaisho("");
            }
        }
        
        //７．デフォルト対象日
        setTaishoDt("");
        setTaishoDtTo("");
        //８．デフォルト順位
        setRankKind(RankKind.CODE_KINKOUSEIHI);
        //７．デフォルト対象期間
        setTaishoKikan("");
    }
	/**
	 * 
	 * 条件項目の表示プルダウンリストを設定します
	 * 
	 * 【事前条件】：(ウィンドウID・会社コード・年度)が設定されていること
	 */
	public void setPullDownData(SessionNipoDto selfSessionDto) {
		super.setPullDownData(selfSessionDto);
		//3．対象日プルダウンリストを設定します。
		setListsTaishoDt(
				selfSessionDto.getListsTaishoDt(getCompanyCd()));
		//4．表示対象(支部)プルダウンリストを設定します。
		setListsHyojiTaisho(
				selfSessionDto.getListsHyojiTaisho(getCompanyCd()));
	}

	/**
	 * @return taishoKikan を戻します。
	 */
	public String getTaishoKikan() {
		return taishoKikan;
	}

	/**
	 * @param taishoKikan 設定する taishoKikan。
	 */
	public void setTaishoKikan(String taishoKikan) {
		this.taishoKikan = taishoKikan;
	}

	/**
	 * @return taishoDtTo を戻します。
	 */
	public String getTaishoDtTo() {
		return taishoDtTo;
	}

	/**
	 * @param taishoDtTo を クラス変数taishoDtToへ設定します。
	 */
	public void setTaishoDtTo(String taishoDtTo) {
		this.taishoDtTo = taishoDtTo;
	}

}
