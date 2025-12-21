package jp.co.isid.mos.bird.bizsupport.energyinputitem.dto;

import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.bizsupport.energyinputitem.common.EnergyInputItemConst;
import jp.co.isid.mos.bird.common.entity.MstSibu;
import jp.co.isid.mos.bird.common.util.CommonUtil;

/**
 * 店舗メーター管理状況メンテナンスDTO
 * @author xnkusam
 *
 */
public class EnergyInputItemDto {

    //会社ｺｰﾄﾞ
    private String companyCd;
    //対象支部プルダウン用リスト
    private List listSibu;
    //メーター区分プルダウン用リスト
    private List listMeterKbn;
    
    //システム日付
    private String sysDate;

    /*ユーザー情報*/
    private String userId;
    //ユーザータイプコード
    private String userTypeCd;

    /*検索条件*/
    //対象支部
    private String taishoSibu;
    //メーター区分
    private String meterKbn;
    
    /*編集データ*/
    private List listEditData;
    
    /*登録データ有無フラグ*/
    private boolean existRegistData = false;
    
    /*セッションキー*/
    private String sessionKey;

    /**
     * 支部名称取得
     * @return
     */
    public String getSibuName() {
        String sibuName = "";
        if (!CommonUtil.isNull(getTaishoSibu())) {
            if (getListSibu() != null && !getListSibu().isEmpty()) {
                for (Iterator ite = getListSibu().iterator(); ite.hasNext();) {
                    MstSibu mstSibu = (MstSibu) ite.next();
                    if (mstSibu.getSibuCd().equals(getTaishoSibu())) {
                        sibuName = mstSibu.getSibuName();
                        break;
                    }
                }
            }
        }
        return sibuName;
    }
    
    /**
     * メーター区分名称取得
     */
    public String getMeterKbnName() {
        String meterKbnName = "";
        if (EnergyInputItemConst.METER_KBN_TENPO.equals(this.meterKbn)) {
            meterKbnName = EnergyInputItemConst.METER_KBN_TENPO_NAME;
        }
        else if (EnergyInputItemConst.METER_KBN_OFFICE.equals(this.meterKbn)) {
            meterKbnName = EnergyInputItemConst.METER_KBN_OFFICE_NAME;
        }
        return meterKbnName;
    }
    
    public String getCompanyCd() {
        return companyCd;
    }
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }
    public String getUserTypeCd() {
        return userTypeCd;
    }
    public void setUserTypeCd(String userTypeCd) {
        this.userTypeCd = userTypeCd;
    }
    public List getListSibu() {
        return listSibu;
    }
    public void setListSibu(List listSibu) {
        this.listSibu = listSibu;
    }
    public List getListMeterKbn() {
        return listMeterKbn;
    }
    public void setListMeterKbn(List listMeterKbn) {
        this.listMeterKbn = listMeterKbn;
    }
    public String getMeterKbn() {
        return meterKbn;
    }
    public void setMeterKbn(String meterKbn) {
        this.meterKbn = meterKbn;
    }
    public String getTaishoSibu() {
        return taishoSibu;
    }
    public void setTaishoSibu(String taishoSibu) {
        this.taishoSibu = taishoSibu;
    }
    public List getListEditData() {
        return listEditData;
    }
    public void setListEditData(List listEditaData) {
        this.listEditData = listEditaData;
    }

    public boolean isExistRegistData() {
        return existRegistData;
    }

    public void setExistRegistData(boolean existRegistData) {
        this.existRegistData = existRegistData;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSysDate() {
        return sysDate;
    }

    public void setSysDate(String sysDate) {
        this.sysDate = sysDate;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }
}
