/*
 * 作成日: 2006/08/
 *
 */
package jp.co.isid.mos.bird.bill.itemtotal.logic.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import jp.co.isid.mos.bird.bill.itemtotal.dao.UIShohinBetuInfoDao;
import jp.co.isid.mos.bird.bill.itemtotal.dto.ShobetuGoukeiDto;
import jp.co.isid.mos.bird.bill.itemtotal.dto.ShobetuGoukeiInfoDto;
import jp.co.isid.mos.bird.bill.itemtotal.entity.UIBunruiInfo;
import jp.co.isid.mos.bird.bill.itemtotal.entity.UIShohinBetuInfo;
import jp.co.isid.mos.bird.bill.itemtotal.logic.GetShohinBetuInfoLogic;
import jp.co.isid.mos.bird.framework.exception.InvalidDataException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.Calculator;
import jp.co.isid.mos.bird.framework.util.Converter;

/**
 * 商品別報取得ロジック
 *
 * @author xlee
 */
public class GetShohinBetuInfoLogicImpl implements GetShohinBetuInfoLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBS011L05";

    private static final String kazeiTitle = "課税商品合計";

    private static final String hikazeiTitle = "非課税商品合計";

    private static final String goukeiTitle = "合計";

    private static final String kazeiKbn = "1";

    private static final String goukeiKbn = "2";

    private static final String seBnrNmSougou = "総合計";


    /**
     *  商品別報取DAOを取得します。
     */
    private UIShohinBetuInfoDao uiShohinBetuInfoDao;

    /**
     *  商品別報取DAOを取得します。
     * @return  商品別報取DAO
     */
    public UIShohinBetuInfoDao getUIShohinBetuInfoDao() {
        return uiShohinBetuInfoDao;
    }

    /**
     *  商品別報取DAOを設定します。
     * @param uiShohinBetuInfoDao 商品別報取DAO
     */
    public void setUIShohinBetuInfoDao(UIShohinBetuInfoDao uiShohinBetuInfoDao) {
        this.uiShohinBetuInfoDao = uiShohinBetuInfoDao;
    }

    /**
     *  商品別報取を取得
     * @param　shobetuGoukeiDto 商品別合計DTO
     * @return 　商品別報取
     * @throws ParseException
     */
    public List execute(ShobetuGoukeiDto shobetuGoukeiDto){
    	//店コード
    	String miseCd = shobetuGoukeiDto.getCondTaishoTenpoCd();
    	//対象期間
    	String urikakeYm = shobetuGoukeiDto.getCondTaishoKikanCd();


    	//エラー処理：
    	if(isNull(miseCd)){
            throw new NotNullException("店コード"); //E0506 店コード
        }

    	//エラー処理：
    	if(isNull(urikakeYm)){
            throw new NotNullException("売掛年月"); //売掛年月
        }

		// 対象年月
		Date dt;
		Date dtAfter;
		String uriendYm = null;
		try {
			dt = Converter.stringToDate("yyyyMM", urikakeYm);
			Calendar rightNow = Calendar.getInstance();
			rightNow.setTime(dt);
			rightNow.add(Calendar.MONTH, +1);
			rightNow.add(Calendar.DAY_OF_YEAR, -1);
			dtAfter = rightNow.getTime();
			uriendYm = Converter.dateToString("yyyyMMdd", dtAfter);
		} catch (Exception e) {
			throw new InvalidDataException("対象年月");
		}

    	List tmpResult = getUIShohinBetuInfoDao().getShohinBetuInfo(miseCd, urikakeYm,uriendYm);

    	if (tmpResult == null || tmpResult.size() == 0) {
            throw new NoResultException(); //E0103 商品別情報
        }

        //各リストの情報
        List syouhinBetuInfoRetList = new ArrayList();

        //7項目毎にリストにセットされているタブを一つのリストに保持する
        List tmpBunruiList = new ArrayList();

        List bunruiInfoList = shobetuGoukeiDto.getBunruiInfoSubList();

        for(int i = 0; i < bunruiInfoList.size(); i++) {

        	List tmpBunruiSub = (ArrayList)bunruiInfoList.get(i);

        	for(int j = 0 ; j < tmpBunruiSub.size(); j++) {

        		UIBunruiInfo uiBunruiInfo = (UIBunruiInfo) tmpBunruiSub.get(j);
        		tmpBunruiList.add(uiBunruiInfo);
        	}
        }

        //総合計
        List syouhinBetuGouKeiList = new ArrayList();

        BigDecimal kazeiAllGoukei = new BigDecimal(0); //総合計用の課税金額合計

        BigDecimal hikazeiAllGoukei = new BigDecimal(0); //総合計用の非課税金額合計

        BigDecimal zenShohinAllGoukei = new BigDecimal(0); //総合計用の全商品の金額合計

        BigDecimal sogokei = new BigDecimal(0);
        BigDecimal uriageDaka = new BigDecimal(0);

        //分類の順番の通りに検索結果をソートし、リストに保持
        for(int i = 0 ; i < tmpBunruiList.size(); i++) {
        	UIBunruiInfo uiBunruiInfo = (UIBunruiInfo) tmpBunruiList.get(i);

        	String seikyuBunrui = uiBunruiInfo.getSeikyuBnrui();
        	String seBnrName = (String) uiBunruiInfo.getSeBnrName();
        	String seikyuBunruiKigou = (String) uiBunruiInfo.getSeikyuBunruiKigou();
        	String seikyuBunruiSeq = (String) uiBunruiInfo.getSeikyuBunruiSeq();

        	List syouhinBetuInfoList = new ArrayList();

	        //各分類別合計
            BigDecimal kazeiGoukei = new BigDecimal(0);
            BigDecimal hikazeiGoukei = new BigDecimal(0);
            BigDecimal zenShohinGoukei = new BigDecimal(0);

	    	for(int j = 0 ; j < tmpResult.size(); j++) {
		        //各分類のデータが入る
	        	UIShohinBetuInfo uiShohinBetu = (UIShohinBetuInfo)tmpResult.get(j);
	        	//請求書分類
	        	String seikyuBunruiRet = uiShohinBetu.getSeikyuBnrui();
	        	if(!seikyuBunrui.equals("") && seikyuBunrui.equals(seikyuBunruiRet)) { //seikyuBunruiが""の場合は"総合計だけ"
	        		BigDecimal kingaku = uiShohinBetu.getKingaku(); //金額
		        	sogokei = uiShohinBetu.getSogokei(); //総合計
		        	uriageDaka = shobetuGoukeiDto.getUriageDakaInfo(); //売上高

		        	BigDecimal kouseihi =  Calculator.percentage(kingaku, sogokei, 2); //構成比
		        	BigDecimal uriagehi =  Calculator.percentage(kingaku, uriageDaka, 2); //売上比

		        	String taxUri = uiShohinBetu.getTaxUri(); //売上消費税区分

		        	if(("0").equals(taxUri)) {
		        		kazeiGoukei = kazeiGoukei.add(kingaku); //課税商品金額
	            	} else if(("1").equals(taxUri) || ("3").equals(taxUri) || ("9").equals(taxUri)){ //非課税商品金額
	            		hikazeiGoukei = hikazeiGoukei.add(kingaku);
	            	}
		        	zenShohinGoukei = zenShohinGoukei.add(kingaku); //全商品金額

		        	//商品別情報を保持するDTOを生成
		        	ShobetuGoukeiInfoDto shobetuGoukeiInfoDto = new ShobetuGoukeiInfoDto();
		        	shobetuGoukeiInfoDto.setSeikyusyoBunrui(uiShohinBetu.getSeikyuBnrui());
		        	shobetuGoukeiInfoDto.setSeBnrName(seBnrName);
		        	shobetuGoukeiInfoDto.setSeikyuBunruiKigou("分類"+seikyuBunruiKigou);
		        	shobetuGoukeiInfoDto.setSeikyuBunruiSeq(seikyuBunruiSeq);
		        	shobetuGoukeiInfoDto.setShoCdJitu(uiShohinBetu.getShoCdJitu());
		        	shobetuGoukeiInfoDto.setShoNameKj(uiShohinBetu.getShoNameKj());
		        	shobetuGoukeiInfoDto.setShoAmount(uiShohinBetu.getShoAmount());
		        	shobetuGoukeiInfoDto.setNisuName(uiShohinBetu.getNisuName());
		        	shobetuGoukeiInfoDto.setNohinTanka(uiShohinBetu.getNohinTanka());
		        	shobetuGoukeiInfoDto.setKingaku(kingaku);
		        	shobetuGoukeiInfoDto.setKouseihi(kouseihi);
		        	shobetuGoukeiInfoDto.setUriagehi(uriagehi);
		        	syouhinBetuInfoList.add(shobetuGoukeiInfoDto);
	        	}
	    	}
	    	if(syouhinBetuInfoList.size() == 0) {
	        	ShobetuGoukeiInfoDto shobetuGoukeiInfoDto = new ShobetuGoukeiInfoDto();
	        	shobetuGoukeiInfoDto.setSeikyusyoBunrui(seikyuBunrui);
	        	shobetuGoukeiInfoDto.setSeBnrName(seBnrName);
	        	shobetuGoukeiInfoDto.setSeikyuBunruiKigou("分類"+seikyuBunruiKigou);
	        	shobetuGoukeiInfoDto.setSeikyuBunruiSeq(seikyuBunruiSeq);
	        	shobetuGoukeiInfoDto.setShoCdJitu("");
	        	shobetuGoukeiInfoDto.setShoNameKj("");
	        	shobetuGoukeiInfoDto.setShoAmount(new BigDecimal(0));
	        	shobetuGoukeiInfoDto.setNisuName("");
	        	shobetuGoukeiInfoDto.setNohinTanka(new BigDecimal(0));
	        	shobetuGoukeiInfoDto.setKingaku(hikazeiGoukei);
	        	shobetuGoukeiInfoDto.setKouseihi(new BigDecimal(0));
	        	shobetuGoukeiInfoDto.setUriagehi(new BigDecimal(0));

	        	syouhinBetuInfoList.add(shobetuGoukeiInfoDto);
	    	}
	    	if(!seikyuBunrui.equals("")) {
		    	//分類用合計
	    		ShobetuGoukeiInfoDto shobetuGoukeiInfoDto2 = new ShobetuGoukeiInfoDto();
	        	shobetuGoukeiInfoDto2.setSeikyusyoBunrui(kazeiKbn);
	        	shobetuGoukeiInfoDto2.setSeBnrName("");
	        	shobetuGoukeiInfoDto2.setShoCdJitu("");
	        	shobetuGoukeiInfoDto2.setShoNameKj(kazeiTitle);
	        	shobetuGoukeiInfoDto2.setShoAmount(new BigDecimal(0));
	        	shobetuGoukeiInfoDto2.setNisuName("");
	        	shobetuGoukeiInfoDto2.setNohinTanka(new BigDecimal(0));
	        	shobetuGoukeiInfoDto2.setKingaku(kazeiGoukei);
	        	//構成比
	        	BigDecimal kazeiGouSeiHi =  Calculator.percentage(kazeiGoukei, sogokei, 2); //構成比
	        	//売上比
	        	BigDecimal kazeiUriageHi =  Calculator.percentage(kazeiGoukei, uriageDaka, 2); //売上比

	        	shobetuGoukeiInfoDto2.setKouseihi(kazeiGouSeiHi);
	        	shobetuGoukeiInfoDto2.setUriagehi(kazeiUriageHi);
                shobetuGoukeiInfoDto2.setMeisaiFlg(false);  //---2006/10/23 add kusama
	        	syouhinBetuInfoList.add(shobetuGoukeiInfoDto2);

	        	shobetuGoukeiInfoDto2 = new ShobetuGoukeiInfoDto();
	        	shobetuGoukeiInfoDto2.setSeikyusyoBunrui(kazeiKbn);
	        	shobetuGoukeiInfoDto2.setSeBnrName("");
	        	shobetuGoukeiInfoDto2.setShoCdJitu("");
	        	shobetuGoukeiInfoDto2.setShoNameKj(hikazeiTitle);
	        	shobetuGoukeiInfoDto2.setShoAmount(new BigDecimal(0));
	        	shobetuGoukeiInfoDto2.setNisuName("");
	        	shobetuGoukeiInfoDto2.setNohinTanka(new BigDecimal(0));
	        	shobetuGoukeiInfoDto2.setKingaku(hikazeiGoukei);
	        	//構成比
	        	BigDecimal hikazeiGouSeiHi =  Calculator.percentage(hikazeiGoukei, sogokei, 2); //構成比
	        	//売上比
	        	BigDecimal hikazeiUriageHi =  Calculator.percentage(hikazeiGoukei, uriageDaka, 2); //売上比
	        	shobetuGoukeiInfoDto2.setKouseihi(hikazeiGouSeiHi);
	        	shobetuGoukeiInfoDto2.setUriagehi(hikazeiUriageHi);
                shobetuGoukeiInfoDto2.setMeisaiFlg(false);  //---2006/10/23 add kusama
	        	syouhinBetuInfoList.add(shobetuGoukeiInfoDto2);

	        	shobetuGoukeiInfoDto2 = new ShobetuGoukeiInfoDto();
	        	shobetuGoukeiInfoDto2.setSeikyusyoBunrui(goukeiKbn);
	        	shobetuGoukeiInfoDto2.setSeBnrName("");
	        	shobetuGoukeiInfoDto2.setShoCdJitu("");
	        	shobetuGoukeiInfoDto2.setShoNameKj(goukeiTitle);
	        	shobetuGoukeiInfoDto2.setShoAmount(new BigDecimal(0));
	        	shobetuGoukeiInfoDto2.setNisuName("");
	        	shobetuGoukeiInfoDto2.setNohinTanka(new BigDecimal(0));
	        	shobetuGoukeiInfoDto2.setKingaku(zenShohinGoukei);
	        	//構成比
	        	BigDecimal zenShohinGouSeiHi =  Calculator.percentage(zenShohinGoukei, sogokei, 2); //構成比
	        	//売上比
	        	BigDecimal zenShohinUriageHi =  Calculator.percentage(zenShohinGoukei, uriageDaka, 2); //売上比
	        	shobetuGoukeiInfoDto2.setKouseihi(zenShohinGouSeiHi);
	        	shobetuGoukeiInfoDto2.setUriagehi(zenShohinUriageHi);
                shobetuGoukeiInfoDto2.setMeisaiFlg(false);  //---2006/10/23 add kusama
	        	syouhinBetuInfoList.add(shobetuGoukeiInfoDto2);

	        	syouhinBetuInfoRetList.add(syouhinBetuInfoList);
	    	}
	    	//総合計用
	    	kazeiAllGoukei = kazeiAllGoukei.add(kazeiGoukei);
	    	hikazeiAllGoukei = hikazeiAllGoukei.add(hikazeiGoukei);
	    	zenShohinAllGoukei = zenShohinAllGoukei.add(zenShohinGoukei);
        }

        //総合計用
		ShobetuGoukeiInfoDto shobetuGoukeiInfoDto3 = new ShobetuGoukeiInfoDto();
		shobetuGoukeiInfoDto3.setSeikyusyoBunrui("");
		shobetuGoukeiInfoDto3.setSeBnrName(seBnrNmSougou);
    	shobetuGoukeiInfoDto3.setShoCdJitu(kazeiKbn);
    	shobetuGoukeiInfoDto3.setShoNameKj(kazeiTitle);
    	shobetuGoukeiInfoDto3.setShoAmount(new BigDecimal(0));
    	shobetuGoukeiInfoDto3.setNisuName("");
    	shobetuGoukeiInfoDto3.setNohinTanka(new BigDecimal(0));
    	shobetuGoukeiInfoDto3.setKingaku(kazeiAllGoukei);
    	//構成比
    	BigDecimal kazeiGouSeiHiAllGoukei =  Calculator.percentage(kazeiAllGoukei, sogokei, 2); //構成比
    	//売上比
    	BigDecimal kazeiGouUriageHiAllGoukei =  Calculator.percentage(kazeiAllGoukei, uriageDaka, 2); //売上比
    	shobetuGoukeiInfoDto3.setKouseihi(kazeiGouSeiHiAllGoukei);
    	shobetuGoukeiInfoDto3.setUriagehi(kazeiGouUriageHiAllGoukei);
        shobetuGoukeiInfoDto3.setMeisaiFlg(false);  //---2006/10/23 add kusama
    	syouhinBetuGouKeiList.add(shobetuGoukeiInfoDto3);

    	shobetuGoukeiInfoDto3 = new ShobetuGoukeiInfoDto();
    	shobetuGoukeiInfoDto3.setSeikyusyoBunrui("");
    	shobetuGoukeiInfoDto3.setSeBnrName(seBnrNmSougou);
    	shobetuGoukeiInfoDto3.setShoCdJitu(kazeiKbn);
    	shobetuGoukeiInfoDto3.setShoNameKj(hikazeiTitle);
    	shobetuGoukeiInfoDto3.setShoAmount(new BigDecimal(0));
    	shobetuGoukeiInfoDto3.setNisuName("");
    	shobetuGoukeiInfoDto3.setNohinTanka(new BigDecimal(0));
    	shobetuGoukeiInfoDto3.setKingaku(hikazeiAllGoukei);
    	//構成比
    	BigDecimal hikazeiGouSeiHiAllGoukei =  Calculator.percentage(hikazeiAllGoukei, sogokei, 2); //構成比
    	//売上比
    	BigDecimal hikazeiGouUriageHiAllGoukei =  Calculator.percentage(hikazeiAllGoukei, uriageDaka, 2); //売上比
    	shobetuGoukeiInfoDto3.setKouseihi(hikazeiGouSeiHiAllGoukei);
    	shobetuGoukeiInfoDto3.setUriagehi(hikazeiGouUriageHiAllGoukei);
        shobetuGoukeiInfoDto3.setMeisaiFlg(false);  //---2006/10/23 add kusama
    	syouhinBetuGouKeiList.add(shobetuGoukeiInfoDto3);

    	shobetuGoukeiInfoDto3 = new ShobetuGoukeiInfoDto();
    	shobetuGoukeiInfoDto3.setSeikyusyoBunrui("");
    	shobetuGoukeiInfoDto3.setSeBnrName(seBnrNmSougou);
    	shobetuGoukeiInfoDto3.setShoCdJitu(goukeiKbn);
    	shobetuGoukeiInfoDto3.setShoNameKj(goukeiTitle);
    	shobetuGoukeiInfoDto3.setShoAmount(new BigDecimal(0));
    	shobetuGoukeiInfoDto3.setNisuName("");
    	shobetuGoukeiInfoDto3.setNohinTanka(new BigDecimal(0));
    	shobetuGoukeiInfoDto3.setKingaku(zenShohinAllGoukei);
    	//構成比
    	BigDecimal zenShohinGouSeiHiAllGoukei =  Calculator.percentage(zenShohinAllGoukei, sogokei, 2); //構成比
    	//売上比
    	BigDecimal zenShohinGouUriageHiAllGoukei =  Calculator.percentage(zenShohinAllGoukei, uriageDaka, 2); //売上比
    	shobetuGoukeiInfoDto3.setKouseihi(zenShohinGouSeiHiAllGoukei);
    	shobetuGoukeiInfoDto3.setUriagehi(zenShohinGouUriageHiAllGoukei);
        shobetuGoukeiInfoDto3.setMeisaiFlg(false);  //---2006/10/23 add kusama
    	syouhinBetuGouKeiList.add(shobetuGoukeiInfoDto3);

    	//最後に総合計を保持する
    	syouhinBetuInfoRetList.add(syouhinBetuGouKeiList);

    	return syouhinBetuInfoRetList;
    }

    /**
     * Null判断処理
     * @param str
     * @return boolean true：空かNullの場合
     */
    private boolean isNull(String str) {
        return (str == null || "".equals(str.trim())) ? true : false;
    }
}
