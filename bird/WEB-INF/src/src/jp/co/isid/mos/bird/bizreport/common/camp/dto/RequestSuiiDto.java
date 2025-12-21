/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.common.camp.dto;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.bizreport.common.camp.code.TaishoJoken;
import jp.co.isid.mos.bird.bizreport.common.camp.entity.CodCampMenu;
import jp.co.isid.mos.bird.bizreport.common.camp.entity.MstCampDate;
import jp.co.isid.mos.bird.bizreport.common.camp.entity.Suii;
import jp.co.isid.mos.bird.bizreport.common.camp.entity.UILists;
import jp.co.isid.mos.bird.bizreport.common.entity.CodBlock;
import jp.co.isid.mos.bird.bizreport.common.entity.CodCompany;
import jp.co.isid.mos.bird.bizreport.common.entity.CodKikanSitei;
import jp.co.isid.mos.bird.common.code.UserType;


/**
 * 日報系リクエスト用DTO
 * 
 * @author xkinu
 *
 */
public class RequestSuiiDto extends RequestDto {
    /**
     * メニュープルダウンリスト
     */ 
    private List listsMenu= new ArrayList();
    /**
     * メニューコード
     */ 
    private String menuCd;
    /**
     * ブロックコード
     */ 
    private String blockCd;
    
	/**
	 * メニューコード取得処理
	 * @return menuCd を戻します。
	 */
	public String getMenuCd() {
		return menuCd;
	}
	/**
	 * 店舗種別分の前年データ種別プルダウンリスト取得処理
	 * 
	 * @return listZennenDataShubetu を戻します。
	 */
	public List getListsMenu() {
		return listsMenu;
	}
	/**
	 * メニューコード設定処理
	 * 
	 * @param menuCd 設定する menuCd。
	 */
	public void setMenuCd(String menuCd) {
		this.menuCd = menuCd;
	}
	/**
	 * メニューコード取得処理
	 * 
	 * @return menuCd を戻します。
	 */
	public String getMenuNameKj() {
		List listCode = ((SessionSuiiDto)getSelfSessionDto()).getListMenu(getCampId(), getMenuTotaledKbn());
		for(int i=0; i<listCode.size(); i++) {
			CodCampMenu entity = (CodCampMenu)listCode.get(i);
			if(getMenuCd().equals(entity.getCode())) {
				return entity.getName();
			}
		}
		return "";
	}

	/**
	 * @param selfSessionDto 設定する selfSessionDto。
	 */
	public void setSelfSessionDto(SessionSuiiDto selfSessionDto) {
		super.setSelfSessionDto(selfSessionDto);
	}

	/**
	 * @return blockCd を戻します。
	 */
	public String getBlockCd() {
		return blockCd;
	}

	/**
	 * @param blockCd 設定する blockCd。
	 */
	public void setBlockCd(String blockCd) {
		this.blockCd = blockCd;
	}
	/**
	 * @return blockCd を戻します。
	 */
	public String getBlockName() {
		List listCode = ((SessionSuiiDto)getSelfSessionDto()).getListBlock();
        String blockCd = getBlockCd();
        if (blockCd == null) {
            blockCd = "";
        }
		for(int i=0; i<listCode.size(); i++) {
			CodBlock entity = (CodBlock)listCode.get(i);
			if(entity.getBlockCd().equals(blockCd)) {
				return entity.getBlockName();
			}
		}
		return "";
	}
	/**
	 * @param listsMenu 設定する listsMenu。
	 */
	public void setListsMenu(List listsMenu) {
		this.listsMenu = listsMenu;
	}
	/**
	 * 
	 * 条件初期値を設定します
	 */
	public void setInitialazeData(SessionSuiiDto selfSessionDto) {
        //デフォルト会社コードを設定します。
		CodCompany com = (CodCompany)selfSessionDto.getListCompany().get(0);
		setCompanyCd(com.getCompanyCd());
        //デフォルト年度を設定します。
		CodKikanSitei cod = (CodKikanSitei)selfSessionDto.getListNendo(getCompanyCd()).get(0);
		setNendo(cod.getCode());
		
		//条件項目の表示プルダウンリストを設定します
		setPullDownData(selfSessionDto);
//		if(getListsCamp() == null) {
//			return;
//		}
        //1．デフォルトキャンペーン識別番号
        if (getListsCamp() != null && !getListsCamp().isEmpty()) {
    		MstCampDate mstCamp = (MstCampDate)getListsCamp().get(0);
    		setCampId(mstCamp.getCampId());
        }
		
		//２．デフォルトメニュー集計区分を設定します。
		SelectItem defaultData = (SelectItem)getSelfSessionDto().getListMenuTotaled().get(0);
		setMenuTotaledKbn((String)defaultData.getValue());
		
		//３．デフォルト店舗種別を設定します。
		defaultData = (SelectItem)getSelfSessionDto().getListTenpoShubetu().get(0);
		setTenpoShubetu((String)defaultData.getValue());
		
		//４．デフォルトメニューを設定します。
		setMenuCd("");
		
		//５．デフォルト前年データ種別を設定します。
		UILists uiDefault = (UILists)getSelfSessionDto().getListsZennenDataShubetu().get(0);
		List listDefault = (List)uiDefault.getListData();
		defaultData = (SelectItem)listDefault.get(0);
		setZennenDataShubetu((String)defaultData.getValue());
		
		//６．デフォルト対象条件を設定します。
//---2008/08/05 update start 対象条件のデフォルトを「全社」に変更
//		setTaishoJoken(TaishoJoken.CODE_MISE);
        setTaishoJoken(TaishoJoken.CODE_ALL);
		
		//７．デフォルト表示対象を設定します。
//        if (UserType.ONER.equals(getSelfSessionDto().getBirdUserInfo().getMstUser().getUserTypeCd())) {
//            //オーナーユーザーの場合、一番上の店舗をデフォルトとする
//            String defHyojiTaisho = "";
//            if (getSelfSessionDto().getListHyojiTaisho(getCampId(), TaishoJoken.CODE_MISE) != null) {
//                CodHyojiTaisho entity = (CodHyojiTaisho) getSelfSessionDto().getListHyojiTaisho(getCampId(), TaishoJoken.CODE_MISE).get(1);
//                defHyojiTaisho = entity.getHyojitaishoCd();
//            }
//            setHyojiTaisho(defHyojiTaisho);
//        }
//        else {
//            setHyojiTaisho("");
//        }
        setHyojiTaisho("");
//---2008/08/05 update end        
	}
	/**
	 * 条件項目の表示データを設定します
	 * 
	 * 【事前条件】：(ウィンドウID・会社コード・年度)が設定されていること
	 */
	public void setPullDownData(SessionSuiiDto selfSessionDto) {
		super.setPullDownData(selfSessionDto);
		//3．メニュープルダウンリストを設定します。
		setListsMenu(selfSessionDto.getListsMenu(getCompanyCd(), getNendo()));
		//4．表示対象プルダウンリストを設定します。
		setListsHyojiTaisho(selfSessionDto.getListsHyojiTaisho(getCompanyCd(), getNendo()));
	}

    /**
     * 表示対象名称
     * @return hyojiTaishoName を戻します。
     */
    public String getHyojiTaishoName() {
        if(getSelfSessionDto() == null || (getListSearchData() == null || getListSearchData().isEmpty())) {
            return "";
        }
        //対象条件：店の場合
        if (TaishoJoken.CODE_MISE.equals(getTaishoJoken())) {
            return ((Suii) getListSearchData().get(0)).getMiseNameKj();
        }
        //対象条件：全社or全店の場合
        else if (TaishoJoken.CODE_ALL.equals(getTaishoJoken())) {
            if (UserType.HONBU.equals(getSelfSessionDto().getBirdUserInfo().getMstUser().getUserTypeCd())) {
                return "全社";
            }
            else {
                return "全店";
            }
        }
        return getSelfSessionDto().getHyojiTaishoName(getCampId(), getTaishoJoken(), getHyojiTaisho());
    }
}
