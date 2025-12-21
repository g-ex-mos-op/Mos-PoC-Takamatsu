package jp.co.isid.mos.bird.bizreport.common.suiiref.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizreport.common.code.TenpoShubetu;
import jp.co.isid.mos.bird.bizreport.common.common.BizReportConstants;
import jp.co.isid.mos.bird.bizreport.common.entity.CodCompany;
import jp.co.isid.mos.bird.bizreport.common.entity.CodKikanSitei;
import jp.co.isid.mos.bird.bizreport.common.suiiref.code.ZennenDataShubetu;
import jp.co.isid.mos.bird.common.entity.CodBlock;
import jp.co.isid.mos.bird.common.entity.CodHyojiTaisho;
import jp.co.isid.mos.bird.common.entity.UILists;
import jp.co.isid.mos.bird.common.util.CommonUtil;

/**
 * 推移表共通DTO【条件部情報】クラス<br/>
 * <br/>
 * 作成日:2013/04/05<br/>
 * @author xkinu
 *
 */
public class SuiiRefConditionDto {
	/**
	 * MAXウィインドウID
	 */
	private int maxWindowId;
	
	/**
	 * MAXウィンドウIDを取得する
	 * @return MAXウィンドウID
	 */
	public int getMaxWindowId() {
		return maxWindowId;
	}

	/**
	 * MAXウィンドウIDを設定する
	 * @param maxWindowId MAXウィンドウID
	 */
	public void setMaxWindowId(int maxWindowId) {
		this.maxWindowId = maxWindowId;
	}


	/**
	 * MAXウィンドウIDを生成取得します。
	 */ 
	public int updateWindowId() {
		int newWindowId = getMaxWindowId() + 1;
		setMaxWindowId(newWindowId);
		return newWindowId;
	}
    /**
     * List[[会社]]
     */
    private List listCompany = new ArrayList(0);
    
    /**
     * List[UILists[店舗種別]]
     */
    private List listTenpoShubetu = new ArrayList(0);
    /**
     * List[UILists[対象期間]]
     */
    private List listTaishoKikan = new ArrayList(0);
    /**
     * Map[キー：会社コード, UILists[対象条件]]
     */
    private Map mapTaishoJoken = new HashMap();
    
    /**
     * List[[ブロック]]
     */
    private List listBlock = new ArrayList(0);
    /**
     *  DTO【検索条件情報】保持Map
     *  windowIdをキーに格納されています。
     */
    private Map mapSearchParameter = new HashMap();
    /**
     * DTO【検索結果条件情報】保持Map
     *  windowIdをキーに格納されています。
     */
    private Map mapResultParameter = new HashMap();
    
    /** MOSCARDメニューアクセス権限判断値 */
    private boolean isAccessMoscard = false;
    
    /** NETORDERメニューアクセス権限判断値 */
    private boolean isAccessNetorder = false;
    
    /**
     * List[[会社]]を取得する
     * @return List 会社リスト
     */
    public List getListCompany() {
        return listCompany;
    }

    /**
     * List[[会社]]を設定する
     * @param listCompany 会社リスト
     */
    public void setListCompany(List list) {
        this.listCompany = list;
    }

    /**
     * 会社名称を取得する
     * @param dto 宅配売上日報 条件部情報DTO
     * @return String 会社名称
     */
    public String getCompanyName(SuiiRefParameterDto dto) {
    	CodCompany eCompany = getEntityCodCompany(dto);
        return CommonUtil.isNull(eCompany.getCompanyName())?BizReportConstants.EMPTY:eCompany.getCompanyName().trim();
    }
    /**
     * CodCompany[会社]を取得する
     * @param dto 宅配売上日報 条件部情報DTO
     * @return String 会社名称
     */
    public CodCompany getEntityCodCompany(SuiiRefParameterDto dto) {
    	if(getListCompany() != null) {
	        for (Iterator it = getListCompany().iterator(); it.hasNext();) {
	        	CodCompany info = (CodCompany) it.next();
	            if (info.getCompanyCd().equals(dto.getCompanyCd())) {
	            	if (!CommonUtil.isNull(info.getCompanyName())) {
	            		return info;
	            	}
	            	break;
	            }
	        }
    	}
        return new CodCompany();
    }
	/**
	 * List[[会社別対象条件別表示対象]]
	 * 構成--> list[ UILists[会社別対象条件]] ] --> UILists[会社別対象条件]].List[ UILists[対象条件別表示対象] ]
	 * @return クラス変数listTaishoJokens を戻します。
	 */
	public List getListsTaishoJoken(String companyCd) {
		if(mapTaishoJoken.containsKey(companyCd)) {
			return (List)mapTaishoJoken.get(companyCd);
		}
		return null;
	}

	/**
	 * List[[会社別対象条件別表示対象]]
	 * 構成--> list[ UILists[会社別対象条件]] ] --> UILists[会社別対象条件]].List[ UILists[対象条件別表示対象] ]
	 * @param listTaishoJokens を クラス変数listTaishoJokensへ設定します。
	 */
	public void setListsTaishoJoken(String companyCd, List lists) {
		this.mapTaishoJoken.put(companyCd, lists);
	}

	/**
	 * DTO【検索条件情報】
	 * @return クラス変数resultParameterDto を戻します。
	 */
	public SuiiRefParameterDto getResultParameterDto(int windowId) {
		return (SuiiRefParameterDto) mapResultParameter.get(new Integer(windowId));
	}

	/**
	 * DTO【検索条件情報】設定処理
	 * @param resultParameterDto
	 */
	public void setResultParameterDto(	SuiiRefParameterDto resultParameterDto) {
		this.mapResultParameter.put(new Integer(resultParameterDto.getWindowId()), resultParameterDto);
	}

	/**
	 * DTO【検索結果条件情報】取得処理
	 * 
	 * @param windowId
	 * @return
	 */
	public SuiiRefParameterDto getSearchParameterDto(int windowId) {
		return (SuiiRefParameterDto) mapSearchParameter.get(new Integer(windowId));
	}

	/**
	 * DTO【検索結果条件情報】設定処理
	 * @param searchParameterDto
	 */
	public void setSearchParameterDto(SuiiRefParameterDto searchParameterDto) {
		this.mapSearchParameter.put(new Integer(searchParameterDto.getWindowId()), searchParameterDto);
	}
	/**
	 * 初期値設定処理<br/>
	 * <br/>
	 * プルダウンメニュー又は、個店ポータル画面から遷移された場合に呼び出されます。<br/>
	 * 
	 * ＜初期値＞<br/>
	 * 会社=ＳＥＱ先頭所属会社<br/>
	 * 店舗種別=全店<br/>
	 * 前年データ種別=前年同月<br/>
	 * 
	 * @param paramDto 推移表共通DTO【検索条件】
	 */
	public void copyDefaultParamerter(SuiiRefParameterDto paramDto) {
		//会社コード設定
		CodCompany eCompany = (CodCompany)getListCompany().get(0);
		paramDto.setCodCompany(eCompany);
		paramDto.setCompanyCd((String)eCompany.getCompanyCd());
		paramDto.setCompanyName((String)eCompany.getCompanyName());
		this.copyDefaultParamerter(paramDto.getCompanyCd(), paramDto);
		paramDto.setFocusTab("");
	}
	/**
	 * 対象会社初期値設定処理<br/>
	 * <br/>
	 * 主に会社プルダウン変更時に呼び出されます。<br/>
	 * <br/>
	 * ＜初期値＞<br/>
	 * 店舗種別=全店<br/>
	 * 前年データ種別=前年同月<br/>
	 * 
	 * @param companyCd 対象会社コード
	 * @param paramDto 推移表共通DTO【検索条件】
	 */
	public void copyDefaultParamerter(String companyCd, SuiiRefParameterDto paramDto) {
		//店舗種別
		paramDto.setTenpoShubetu(TenpoShubetu.CODE_ALL);
		
		//対象期間
		UILists uiTaishoKikan = (UILists)getListTaishoKikan().get(0);
		paramDto.setTaishoKikan((String)uiTaishoKikan.getKey());
		//期間指定
		String kikanSitei = (String)((CodKikanSitei)uiTaishoKikan.getListData().get(0)).getCode();
		paramDto.setKikanSitei(kikanSitei);

		//会社別対象条件を設定します。
		paramDto.setListTaishoJoken(getListsTaishoJoken(companyCd));
		UILists uiTaishoJoken = (UILists)paramDto.getListTaishoJoken().get(0);
		paramDto.setTaishoJoken(uiTaishoJoken.getKey());
		paramDto.setHyojiTaisho("");
		if(uiTaishoJoken.getListData() != null && !uiTaishoJoken.getListData().isEmpty()) {
			CodHyojiTaisho cod = (CodHyojiTaisho)uiTaishoJoken.getListData().get(0);
			paramDto.setHyojiTaisho(cod.getHyojitaishoCd());
		}
		paramDto.setBlockCd("");
		paramDto.setBlockName("");
		//前年データ種別
		paramDto.setZennenDataShubetu(ZennenDataShubetu.CODE_DOGETU);
	}

	/**
	 * @return クラス変数listBlock を戻します。
	 */
	public List getListBlock() {
		return listBlock;
	}

	/**
	 * @param listBlock を クラス変数listBlockへ設定します。
	 */
	public void setListBlock(List listBlock) {
		this.listBlock = listBlock;
	}

	/**
	 * @return クラス変数isAccessMoscard を戻します。
	 */
	public boolean isAccessMoscard() {
		return isAccessMoscard;
	}

	/**
	 * @param isAccessMoscard を クラス変数isAccessMoscardへ設定します。
	 */
	public void setAccessMoscard(boolean isAccessMoscard) {
		this.isAccessMoscard = isAccessMoscard;
	}
	
	/**
	 * @return クラス変数isAccessNetorder を戻します。
	 */
	public boolean isAccessNetorder() {
		return isAccessNetorder;
	}

	/**
	 * @param isAccessNetorder を クラス変数isAccessNetorderへ設定します。
	 */
	public void setAccessNetorder(boolean isAccessNetorder) {
		this.isAccessNetorder = isAccessNetorder;
	}

	/**
	 * List[UILists[店舗種別]]
	 * @return クラス変数List[UILists[店舗種別]] を戻します。
	 */
	public List getListTenpoShubetu() {
		return listTenpoShubetu;
	}

	/**
	 * List[UILists[店舗種別]]
	 * @param list を クラス変数List[UILists[店舗種別]]へ設定します。
	 */
	public void setListTenpoShubetu(List list) {
		this.listTenpoShubetu = list;
	}

	/**
	 * List[UILists[対象期間]]
	 * @return クラス変数List[UILists[対象期間]] を戻します。
	 */
	public List getListTaishoKikan() {
		return listTaishoKikan;
	}

	/**
	 * List[UILists[対象期間]]
	 * @param list を クラス変数List[UILists[対象期間]]へ設定します。
	 */
	public void setListTaishoKikan(List list) {
		this.listTaishoKikan = list;
	}
	/**
	 * ブロック名称取得処理
	 * @return
	 */
	public String getBlockName(String blockCd) {
		for (int b=0; b<listBlock.size(); b++) {
			CodBlock block = (CodBlock)listBlock.get(b);
			if(block.getBlockCd().equals(blockCd)) {
				return block.getBlockName().trim();
			}
		}
		return "";
	}

}