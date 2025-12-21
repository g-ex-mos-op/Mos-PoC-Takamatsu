package jp.co.isid.mos.bird.bizsupport.energyamount.dto;

import java.util.List;

/**
 * エネルギー使用量ダウンロード セッションDTO
 * @
 * @author xnkusama
 *
 */
public class EnergyAmountSessionDto {

    /*ユーザータイプコード */
    private String userTypeCd;
    
    /*対象条件プルダウン用リスト*/
    private List listTaishoJoken;
    
    /*対象期間プルダウン用リスト*/
    private List listTaishoKikan;
    
    /*未入力店舗用 対象期間プルダウン用リスト*/
    private List listNoInputTaishoKikan;
    
    /*対象年月プルダウン用リスト*/
    private List listTaishoNengetu;

    /*対象年度プルダウン用リスト*/
    private List listTaishoNendo;
    
    /*未入力店舗用 対象年月プルダウン用リスト*/
    private List listNoInputTaishoNengetu;
    
    /*メーター区分プルダウン用リスト*/
    private List listMeterKbn;
    
    /*未入力店舗用 メーター区分プルダウン用リスト*/
    private List listNoInputMeterKbn;
	private EnergyAmountRequestDto holdRequestDto = null;
    
    public String getUserTypeCd() {
        return userTypeCd;
    }

    public void setUserTypeCd(String userTypeCd) {
        this.userTypeCd = userTypeCd;
    }

    public List getListMeterKbn() {
        return listMeterKbn;
    }

    public void setListMeterKbn(List listMeterKbn) {
        this.listMeterKbn = listMeterKbn;
    }

    public List getListTaishoJoken() {
        return listTaishoJoken;
    }

    public void setListTaishoJoken(List listTaishoJoken) {
        this.listTaishoJoken = listTaishoJoken;
    }

    public List getListTaishoKikan() {
        return listTaishoKikan;
    }

    public void setListTaishoKikan(List listTaishoKikan) {
        this.listTaishoKikan = listTaishoKikan;
    }

    public List getListTaishoNendo() {
        return listTaishoNendo;
    }

    public void setListTaishoNendo(List listTaishoNendo) {
        this.listTaishoNendo = listTaishoNendo;
    }

    public List getListTaishoNengetu() {
        return listTaishoNengetu;
    }

    public void setListTaishoNengetu(List listTaishoNengetu) {
        this.listTaishoNengetu = listTaishoNengetu;
    }

    public List getListNoInputTaishoNengetu() {
        return listNoInputTaishoNengetu;
    }

    public void setListNoInputTaishoNengetu(List listNoInputTaishoNengetu) {
        this.listNoInputTaishoNengetu = listNoInputTaishoNengetu;
    }

    public List getListNoInputTaishoKikan() {
        return listNoInputTaishoKikan;
    }

    public void setListNoInputTaishoKikan(List listNoInputTaishoKikan) {
        this.listNoInputTaishoKikan = listNoInputTaishoKikan;
    }

    public List getListNoInputMeterKbn() {
        return listNoInputMeterKbn;
    }

    public void setListNoInputMeterKbn(List listNoInputMeterKbn) {
        this.listNoInputMeterKbn = listNoInputMeterKbn;
    }

	/**
	 * @return クラス変数holdRequestDto を戻します。
	 */
	public EnergyAmountRequestDto getHoldRequestDto() {
		return holdRequestDto;
	}

	/**
	 * @param holdRequestDto を クラス変数holdRequestDtoへ設定します。
	 */
	public void setHoldRequestDto(EnergyAmountRequestDto holdRequestDto) {
		this.holdRequestDto = holdRequestDto;
	}

}
