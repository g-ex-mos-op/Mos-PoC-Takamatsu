package jp.co.isid.mos.bird.bizsupport.energyamount.logic.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.bizsupport.energyamount.common.EnergyAmountConst;
import jp.co.isid.mos.bird.bizsupport.energyamount.logic.ConditionInfoLogic;
import jp.co.isid.mos.bird.common.code.TaishoJoken;
import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.common.logic.GetHyojiTaishoLogic;
import jp.co.isid.mos.bird.common.logic.GetSibuHoyuTenpoLogic;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;

/**
 * 
 * 更新日:2010/05/14 xkinu エネルギーデータ推移表追加対応
 *
 */
public class ConditionInfoLogicImpl implements ConditionInfoLogic {

    /** ロジックID */
    public static final String LOGIC_ID = "BBS032L01";
    
    /*プルダウン年月数*/
    private static final int ENERGY_NENGETU = 25;
    private static final int ENERGY_NENDO = 2;
    private static final int NOINPUT_NENGETU = 14;
    /** 対象条件配列：本部用 */
    public static final String[][] PLDWN_TAISHO_JOKEN = new String [][]{
        {TaishoJoken.CODE_SIBU, "支部"},
        {TaishoJoken.CODE_MISE, "個店"}};
    
    /* 年月フォーマッター(yyyy/MM) */
    private static final DateFormatter dateFormatter = new DateFormatter(DateFormatter.DATE_TYPE_YM, DateFormatter.PATTERN_MONTH_SLASH);
    
    private GetSibuHoyuTenpoLogic sibuHoyuTenpoLogic;
    /** LOGIC【表示対象データ検索ロジック】*/
    private GetHyojiTaishoLogic getHyojiTaishoLogic;
    
    public Map execute(String companyCd, String sysDate, BirdUserInfo birdUserInfo) {
    	String userTypeCd = birdUserInfo.getMstUser().getUserTypeCd();
        //戻り値Map
        Map retMap = new HashMap();
        //システム日付前月
        String sysDateZenMonth = getCalcMonth(sysDate, -1);
        
        //事前条件
        validate(companyCd, sysDate);
        if(UserType.isHonbu(userTypeCd)) {
	        //支部一覧取得
	        List listSibu = getSibuHoyuTenpoLogic().execute(companyCd, birdUserInfo.getUserID(), birdUserInfo.isLimit());
	        //1行目に「すべて」を追加
	        SelectItem selectItem = new SelectItem(EnergyAmountConst.TAISHO_JOKEN_ALL, "すべて");
	        listSibu.add(0, selectItem);
	        retMap.put(MAP_KEY_SIBU, listSibu);
        }
        //エネルギー使用量 年月指定用プルダウン作成
        //システム日付の前月から過去25ヶ月降順
        List listEnergyNengetu = createMonthList(sysDateZenMonth, ENERGY_NENGETU);
        
        //エネルギー使用量 年度累計用プルダウンを作成
        //システム日付の年度を含め２年度降順
        List listEnergyNendo = createNendoList(sysDate, ENERGY_NENDO);
        
        //未入力店舗一覧 年月指定用プルダウン作成
        //システム日付の当月を含め過去14ヶ月降順
        List listNoinputNengetu = createMonthList(sysDate.substring(0, 6), NOINPUT_NENGETU);
        
        //対象期間プルダウン作成
        List listTaishoKikan = makeListTaishoKikan();
        
        //未入力店舗一覧用対象期間プルダウン作成
        List listNoinputTaishoKikan = makeNoInputListTaishoKikan();
        
        //メーター区分プルダウン作成
        List listMeterKbn = makeListMeterKbn();
        
        //未入力店舗一覧用メーター区分プルダウン作成
        List listNoinputMeterKbn = makeNoInputListMeterKbn();
        
        retMap.put(MAP_KEY_ENERGY_NENGETU, listEnergyNengetu);
        retMap.put(MAP_KEY_ENERGY_NENDO, listEnergyNendo);
        retMap.put(MAP_KEY_NOINPUT_NENGETU, listNoinputNengetu);
        retMap.put(MAP_KEY_TAISHO_KIKAN, listTaishoKikan);
        retMap.put(MAP_KEY_NOINPUT_TAISHO_KIKAN, listNoinputTaishoKikan);
        retMap.put(MAP_KEY_METER_KBN, listMeterKbn);
        retMap.put(MAP_KEY_NOINPUT_METER_KBN, listNoinputMeterKbn);
        
        //エネルギーデータ推移表用 条件項目情報作成処理
        executeSuii(companyCd, sysDate, retMap, userTypeCd);

          return retMap;
    }
          
    /**
     * エネルギーデータ推移表用
     * 条件項目情報作成処理
     * @author xkinu
     * @add xmatsuo
     * @param retMap
     * 更新日：2010/06/30 仕様追加 システム日付の年度が2010年度の場合は、2008年度分は表示しない
     * 更新日：2014/11/17 仕様追加 1行目にログインユーザーが本部かつ対象条件が「支部」の場合、「すべて」を追加
     */

    private void executeSuii(String companyCd, String sysDate, Map retMap, String userTypeCd) {
        //システム日付基準で、当年度含め３年度降順（初期値：当年度）
    	List listNendo = createNendoList(sysDate, 3);
    	//ただし、システム日付の年度が2010年度の場合は、2008年度分は表示しない(<--2010/06/30仕様追加)
    	if("2010".equals(DateManager.getCurrentYear(sysDate))) {
    		listNendo.remove(2);//2008年度分を削除
    	}
    	retMap.put(MAP_KEY_SUII_NENDO, listNendo);
    	//ログインユーザーが本部ユーザーの場合は、対象条件プルダウン情報を取得します。
    	if(UserType.isHonbu(userTypeCd)) {
    		List pullDownList = new ArrayList();
    		
    		for(int i = 0; i < PLDWN_TAISHO_JOKEN.length; i++) {
    			SelectItem item = new SelectItem(PLDWN_TAISHO_JOKEN[i][0], PLDWN_TAISHO_JOKEN[i][1]);
    			pullDownList.add(item);
    		}
	    	retMap.put(MAP_KEY_SUII_TAISHO_JOKEN, pullDownList);//営業エリアプルダウン情報
	    	//対象条件プルダウン情報取得
           //delete 松尾
//	    	List listSibu =new ArrayList(0);
//	    	List motoListSibu = (List)retMap.get(MAP_KEY_SIBU);
//	    	for(int i=1; i<motoListSibu.size(); i++) {
//	    		listSibu.add(motoListSibu.get(i));
//	    	}
            List listSibu =(List)retMap.get(MAP_KEY_SIBU);

	    	retMap.put(MAP_KEY_SUII_SIBU, listSibu);//支部プルダウン情報
    	}
    }
    
    /**
     * 年月の計算
     * @param date
     * @param append
     * @return
     */
    private String getCalcMonth(String date, int append) {
        String month = date;
        String retMonth = "";
        if (date.length() > 6) {
            month = date.substring(0, 6);
        }
        try {
            retMonth =  DateManager.getNextMonth(month, append);
        }
        catch (Exception ex) {
            throw new FtlSystemException("日付", null, ex);
        }
        return retMonth;
    }
    
    /**
     * 指定月数分のプルダウン作成
     */
    private List createMonthList(String month, int monthLen) {
        List retList = new ArrayList();
        for (int i = 0; i < monthLen; i++) {
            String value = getCalcMonth(month, -i);
            String label = dateFormatter.format(value, true);
            retList.add(new SelectItem(value, label));
        }
        return retList;
    }
    
    /**
     * 指定年度分のプルダウン作成
     */
    private List createNendoList(String date, int nendoLen) {
        List retList = new ArrayList();
        try {
        	String startNendo = DateManager.getCurrentYear(date);
            for (int i = 0; i < nendoLen; i++) {
                String value = DateManager.getPrevYear(startNendo, i);
                String label = value + "年度";
                retList.add(new SelectItem(value, label));
            }
        }
        catch (Exception ex) {
            throw new FtlSystemException("日付", null, ex);
        }
        return retList;
    }
    
    /**
     * 対象期間プルダウン作成
     */
    private List makeListTaishoKikan() {
        List retList = new ArrayList();
        retList.add(new SelectItem(EnergyAmountConst.TAISHO_KIKAN_NENGETU, EnergyAmountConst.TAISHO_KIKAN_NENGETU_NAME));
        retList.add(new SelectItem(EnergyAmountConst.TAISHO_KIKAN_NENDO, EnergyAmountConst.TAISHO_KIKAN_NENDO_NAME));
        return retList;
    }

    /**
     * 未入力店舗一覧用 対象期間プルダウン作成
     */
    private List makeNoInputListTaishoKikan() {
        List retList = new ArrayList();
        retList.add(new SelectItem(EnergyAmountConst.TAISHO_KIKAN_NENGETU, EnergyAmountConst.TAISHO_KIKAN_NENGETU_NAME));
        return retList;
    }
    
    /**
     * メーター区分プルダウン作成
     */
    private List makeListMeterKbn() {
        List retList = new ArrayList();
        retList.add(new SelectItem(EnergyAmountConst.METER_KBN_TENPO, EnergyAmountConst.METER_KBN_TENPO_NAME));
        retList.add(new SelectItem(EnergyAmountConst.METER_KBN_OFFICE, EnergyAmountConst.METER_KBN_OFFICE_NAME));
        return retList;
    }

    /**
     * 未入力店舗一覧用 メーター区分プルダウン作成
     */
    private List makeNoInputListMeterKbn() {
        List retList = new ArrayList();
        retList.add(new SelectItem(EnergyAmountConst.METER_KBN_TENPO, EnergyAmountConst.METER_KBN_TENPO_NAME));
        return retList;
    }

    /**
     * 事前条件
     * @param companyCd
     * @param sysDate
     */
    private void validate(String companyCd, String sysDate) {
        if (CommonUtil.isNull(companyCd)) {
            throw new NotNullException("会社コード");
        }
        if (CommonUtil.isNull(sysDate)) {
            throw new NotNullException("システム日付");
        }
    }

    public GetSibuHoyuTenpoLogic getSibuHoyuTenpoLogic() {
        return sibuHoyuTenpoLogic;
    }

    public void setSibuHoyuTenpoLogic(GetSibuHoyuTenpoLogic sibuHoyuTenpoLogic) {
        this.sibuHoyuTenpoLogic = sibuHoyuTenpoLogic;
    }

	/**
	 * @return クラス変数getHyojiTaishoLogic を戻します。
	 */
	public GetHyojiTaishoLogic getGetHyojiTaishoLogic() {
		return getHyojiTaishoLogic;
	}

	/**
	 * @param getHyojiTaishoLogic を クラス変数getHyojiTaishoLogicへ設定します。
	 */
	public void setGetHyojiTaishoLogic(GetHyojiTaishoLogic getHyojiTaishoLogic) {
		this.getHyojiTaishoLogic = getHyojiTaishoLogic;
	}

}
