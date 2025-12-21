package jp.co.isid.mos.bird.bizreport.gyotaibetuniporef.logic.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizreport.common.code.NipoZennenDataShubetu;
import jp.co.isid.mos.bird.bizreport.common.code.ShukeiKbn;
import jp.co.isid.mos.bird.bizreport.common.code.TaishoKikan;
import jp.co.isid.mos.bird.bizreport.common.code.TenkoKbn;
import jp.co.isid.mos.bird.bizreport.common.code.TenpoShubetu;
import jp.co.isid.mos.bird.bizreport.common.code.UserType;
import jp.co.isid.mos.bird.bizreport.common.dto.NipoRefConditionParameterDto;
import jp.co.isid.mos.bird.bizreport.gyotaibetuniporef.common.GyotaibetuNipoCommon;
import jp.co.isid.mos.bird.bizreport.gyotaibetuniporef.common.GyotaibetuNipoConstants;
import jp.co.isid.mos.bird.bizreport.gyotaibetuniporef.dao.UIMiseListInfoDao;
import jp.co.isid.mos.bird.bizreport.gyotaibetuniporef.dao.UIYagoListInfoDao;
import jp.co.isid.mos.bird.bizreport.gyotaibetuniporef.entity.UIMiseListInfo;
import jp.co.isid.mos.bird.bizreport.gyotaibetuniporef.entity.UIYagoListInfo;
import jp.co.isid.mos.bird.bizreport.gyotaibetuniporef.logic.GyotaibetuNipoRefMiseLogic;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.util.Calculator;

/**
 * 
 * @author xnarita
 * @modifier xkinu  2007/06/11 予算登録2次 クローズ店予算表示対応
 */
public class GyotaibetuNipoRefMiseLogicImpl implements GyotaibetuNipoRefMiseLogic {
    public static final String LOGIC_ID = "BBR005L02";
    
    private UIMiseListInfoDao uIMiseListInfoDao;
    private UIYagoListInfoDao uIYagoListInfoDao;
            
    /**
     * 店情報の取得
     * @param paramMap
     * 管理会社企業コード:COMPANY_CD
     * ユーザーID：USER_ID
     * 店舗種別：TENSHU
     * 前年データ種別：DATASHU
     * 対象期間：TAISHO_KIKAN
     * 期間FROM：KIKAN_FROM
     * 期間TO：KIKAN_TO
     * 支部制限フラグ：LIMIT_FLG
     * エリア大：AREA_DAI_FLG
     * 
     * @modifier xkinu  2007/06/11 予算登録2次 クローズ店予算表示対応
     */
    public List execute(Map paramMap) {
        
        String companyCd = (String)paramMap.get( GyotaibetuNipoConstants.COMPANY_CD );
        String userId = (String)paramMap.get( GyotaibetuNipoConstants.USER_ID );
        String tenpoShu = (String)paramMap.get( GyotaibetuNipoConstants.TENSHU );
        String dataShu = (String)paramMap.get( GyotaibetuNipoConstants.DATASHU );
        String taishoKikan = (String)paramMap.get( GyotaibetuNipoConstants.TAISHO_KIKAN );
        String kikanFrom = (String)paramMap.get( GyotaibetuNipoConstants.KIKAN_FROM );
        String kikanTo = (String)paramMap.get( GyotaibetuNipoConstants.KIKAN_TO );
//        boolean limitFlg = ( (Boolean)paramMap.get( GyotaibetuNipoConstants.LIMIT_FLG ) ).booleanValue();
        String taishoTenpo =(String)paramMap.get( GyotaibetuNipoConstants.TAISHO_TENPO );
        String shukeiKbn = (String)paramMap.get( GyotaibetuNipoConstants.SHUKEI_KBN );
        
        String userTypeCd = (String)paramMap.get(GyotaibetuNipoConstants.USER_TYPE_CD);
        
        String sibuCd = (String)paramMap.get( GyotaibetuNipoConstants.SIBU_CD );
        String yagoCd = (String)paramMap.get( GyotaibetuNipoConstants.YAGO_CD );
        String yagoType = (String)paramMap.get( GyotaibetuNipoConstants.YAGO_TYPE );
        String kubun = (String)paramMap.get( GyotaibetuNipoConstants.KUBUN );
        //20081209追加 SVコード
        String svCd    = (String)paramMap.get( GyotaibetuNipoConstants.SV_CD );
             
        List yagoDtlCode = new ArrayList();
        List yagoCdList = new ArrayList();
        boolean yagoFlg = true;
        boolean limitFlg = false;
                    
        //屋号タイプがnull(別の日報からの遷移の場合)
        if(yagoType == null){
        	yagoType = GyotaibetuNipoConstants.OYA;
        }
        if(yagoCd == null){
        	yagoCd = GyotaibetuNipoConstants.ALL;
        }
        if(CommonUtil.isNull(kubun)){
        	kubun = GyotaibetuNipoConstants.sibu;
        }
           
        if(UserType.HONBU.equals(userTypeCd)){
            limitFlg = ( (Boolean)paramMap.get( GyotaibetuNipoConstants.LIMIT_FLG ) ).booleanValue();
            /* 2008/12/09追加 xayumi SV対応
             * 集計区分で『SV指定(担当店一覧)』が選択された場合 */
            if(shukeiKbn.equals(ShukeiKbn.SV_CD)){
                kubun = "";
            }   
        }
        
        //屋号タイプが親コードの場合の処理
        if(yagoType.equals(GyotaibetuNipoConstants.OYA) ){
        	
        	//屋号コードがALL以外の場合の処理
        	if(!yagoCd.equals(GyotaibetuNipoConstants.ALL)){

	        	//親屋号コードから詳細屋号コードを取得する。
	        	yagoDtlCode = getUIYagoListInfoDao().getDtlYagoCode(yagoCd);
	
	        	for(int i = 0; i < yagoDtlCode.size() ; i++){
	        		
	        		UIYagoListInfo yagoDtldata = (UIYagoListInfo) yagoDtlCode.get(i);

	        		//詳細屋号コードの設定
	        		yagoCdList.add(yagoDtldata.getYagoDetailCd());
	        	}
        	}else{
        		yagoFlg = false;
        	}
        	
        }else if(yagoType.equals(GyotaibetuNipoConstants.DTL) ){
        	//詳細屋号コードを設定
        	//屋号コードがALL以外の場合の処理
        	if(!yagoCd.equals(GyotaibetuNipoConstants.ALL)){
            	yagoCdList.add(yagoCd);
        	}else{
        		yagoFlg = false;
        	}
        }else{
        	//タイプが未設定の場合（他の日報からタブ遷移した場合）
        	yagoFlg = false;
        }

        List resultList = new ArrayList();
        
        if (TaishoKikan.DAY1.equals(taishoKikan) || TaishoKikan.DAYS.equals(taishoKikan)) {
        	
	        //支部別売上げデータを取得
// 2007/06/11 ADD start xkinu 予算登録2次 クローズ店表示対応
	        resultList = getUIMiseListInfoDao().selectNipo(
	        		userTypeCd, limitFlg, userId
	        		, companyCd, tenpoShu, shukeiKbn
	        		, taishoTenpo
	        		, taishoKikan, kikanFrom, kikanTo
	        		, dataShu, kubun, sibuCd
	        		, yagoFlg, yagoCdList, svCd);
// 2007/06/11 ADD end xkinu 予算登録2次 クローズ店表示対応
        }else{
// 2007/06/11 ADD start xkinu 予算登録2次 クローズ店表示対応
	        resultList = getUIMiseListInfoDao().selectGepo(
	        		userTypeCd, limitFlg, userId
	        		, companyCd, tenpoShu, shukeiKbn
	        		, taishoTenpo
	        		, taishoKikan, kikanFrom, kikanTo
	        		, dataShu, kubun, sibuCd
	        		, yagoFlg, yagoCdList, svCd);
// 2007/06/11 ADD end xkinu 予算登録2次 クローズ店表示対応
        }
        
        // データが０件の場合は結果無し
        if ( resultList == null || resultList.size() == 0 ) {
        	throw new NoResultException();
        }else{
        	UIMiseListInfo list = (UIMiseListInfo)resultList.get(0);
        	if(list.getCount() <= 0){
        		throw new NoResultException();
        	}
        }
        
        return resultList;
    }    
    
    /**
     * 画面表示用に整理する
     * @param sibuList
     * @param uriageList
     * @param yosanList
     * @return
     */
    public List editViewDataList(List miseList, NipoRefConditionParameterDto conditionDto) {
    	String companyCd = conditionDto.getCompanyCd();
    	String shuKbn = conditionDto.getShukeiKbnCd();
		// 前年データ種別
		String zenDataShubetu = TenpoShubetu.CODE_ZENNEN.equals(conditionDto.getTenpoShubetuCd()) ?
				conditionDto.getZenDataZennenCd() : conditionDto.getZenDataZennenOthCd();
    	//画面表示用のリスト
    	List viewList = new ArrayList();
    	
    	UIMiseListInfo miseData = new UIMiseListInfo();
    	boolean miseFlg = true;
    	boolean blockFlg = true;
    	boolean totalFlg = true;
        boolean totalSvFlg = true;
    	
    	boolean checkBlockFlg = false;
    	boolean checkTotalFlg = false;
    	
    	//最終1行は不要データのためカットする。（miseList.size() - 1）
        for(int i = 0 ; i < miseList.size() - 1 ; i++){
    	
        	//検索結果リストからi番目のレコードを取り出す。
        	miseData = (UIMiseListInfo) miseList.get(i);
        	//
        	checkBlockFlg = false;
        	checkTotalFlg = false;
            
            if(shuKbn.equals(ShukeiKbn.SV_CD) && miseData.getSibuCd() == null 
                    && miseData.getMiseCd() == null && miseData.getBlockCd() == null){               
                /* SV指定で検索された場合 *****************/
                //表示区分にTOTALをセット
                miseData.setDispCode(GyotaibetuNipoConstants.sv_total);
                //CSSのセット
                miseData.setCssClass(GyotaibetuNipoConstants.rClassSum5);
                //表示フラグのセット
                miseData.setViewFlg(totalSvFlg);
                
                totalSvFlg    = false;
                checkTotalFlg = true;
                                
            }else if(miseData.getMiseCd() == null && miseData.getBlockCd() == null){               
        		//表示区分にTOTALをセット
        		miseData.setDispCode(GyotaibetuNipoConstants.total);
                
                //CSSのセット
                if(shuKbn.equals(ShukeiKbn.SV_CD)){                   
                    //SV指定で検索された場合
                    miseData.setCssClass(GyotaibetuNipoConstants.rClassSum4);
                }else{
                    miseData.setCssClass(GyotaibetuNipoConstants.rClassSum5);                    
                }

            	//表示フラグのセット
            	miseData.setViewFlg(totalFlg);
            	
            	totalFlg = false;
            	checkTotalFlg = true;
            	            	
        	}else if(miseData.getMiseCd() == null){
        		//表示区分にBLOCKをセット
        		miseData.setDispCode(GyotaibetuNipoConstants.block);
        		//ブロック名の編集
        		miseData.setBlockName(GyotaibetuNipoCommon.setEmpty(miseData.getBlockName()) + GyotaibetuNipoConstants.MSG_TOTAL);
        		//CSSのセット
            	miseData.setCssClass(GyotaibetuNipoConstants.rClassSum2);
            	//表示フラグのセット
            	miseData.setViewFlg(blockFlg);
            	
                totalFlg = true;
            	blockFlg = false;
            	checkBlockFlg = true;
            	checkTotalFlg = true;
            	
        	}else{
        		//表示区分にMISEをセット
            	miseData.setDispCode(GyotaibetuNipoConstants.mise);
            	//店舗種別のセット
            	String tenSyuName = "";
            	String tenSyuCd = miseData.getTenpoShubetu();
            	if(!tenSyuCd.trim().equals("") && tenSyuCd != null){
            		tenSyuName = TenpoShubetu.getName( tenSyuCd ).substring(0,1);
            	}
            	miseData.setTenpoShubetuName( tenSyuName );	
            	//天候区分名のセット
            	miseData.setTenkoKbnName( TenkoKbn.getName( miseData.getTenkoKbn() ) );
            	//前年天候区分名のセット
            	miseData.setTenkoKbnZenName( TenkoKbn.getName( miseData.getTenkoKbnZen() ) );
            	//CSSのセット
            	miseData.setCssClass(GyotaibetuNipoConstants.rClassSum1);
            	//表示フラグのセット
            	miseData.setViewFlg(miseFlg);
            	
                totalFlg = true;
            	blockFlg = true;
        	}
        	
        	if(i == miseList.size()-1 ){
        		//表示区分にtotalをセット
        		miseData.setDispCode(GyotaibetuNipoConstants.total);
        		//CSSのセット
        		miseData.setCssClass(GyotaibetuNipoConstants.rClassSum5);
        	}
        	
        	//会社コードがモス　直営店含む　の場合は
        	if(shuKbn.equals(ShukeiKbn.IN_RC) && companyCd.equals(GyotaibetuNipoConstants.MOS)){
        		//ブロック合計と総合計を表示しない
        		if(!checkBlockFlg == true && !checkTotalFlg == true){
        			viewList = this.addMiseList(viewList,miseData,checkTotalFlg, zenDataShubetu);
        		}
    		}else{
    			//会社コードがモス以外の場合は
    			if(!companyCd.equals(GyotaibetuNipoConstants.MOS)){
    				//ブロック合計を表示しない
    				if(!checkBlockFlg == true){
            			viewList = this.addMiseList(viewList,miseData,checkTotalFlg, zenDataShubetu);
            		}
    			}else{
    				//会社コードがモス　直営店を含まない　の場合はブロック合計と総合計を表示する
    				viewList = this.addMiseList(viewList,miseData,checkTotalFlg, zenDataShubetu);
    			}
    		}
        	
        	//blockCd = miseData.getBlockCd();
        	//blockName = miseData.getBlockName();
    	}
        
		//表示区分にtotalをセット
		//miseData.setDispCode(total);
		//CSSのセット
		//miseData.setCssClass(rClassSum5);
		
    	return viewList;
    }
         
    /**
     * 店舗情報の表示用リストへの追加
     * @param sibuRedMos
     * @return viewList
     */
    private List addMiseList(List viewList,UIMiseListInfo miseData,boolean totalFlg, String zenDataShubetu) {
		
    	//屋号情報を編集後にリストへ追加
    	BigDecimal ritu = null;
    	BigDecimal kyakuTanka = null;
    	BigDecimal zennenhiKyakusu = null;
    	BigDecimal zennenhiUri = null;
    	BigDecimal zennenhiKyakuTanka = null;
    	BigDecimal zennenKakuTanka = null;

    	BigDecimal kyakuTankaNet = null;
    	BigDecimal zennenhiKyakusuNet = null;
    	BigDecimal zennenhiUriNet = null;
    	BigDecimal zennenhiKyakuTankaNet = null;
    	BigDecimal zennenKakuTankaNet = null;
		//前年比の計算
        try {
    		//　達成率　＝　売上げ／予算＊１００
        	ritu 		= Calculator.percentage(String.valueOf( miseData.getUriage()),String.valueOf(miseData.getYosan()),2);       
            //　前年比（売上げ）　＝　売上げ／前年実績＊１００
        	zennenhiUri 		= Calculator.percentage(String.valueOf( miseData.getUriage()),String.valueOf(miseData.getZennenUriage()),2);
        	//　前年比（客数）＝　客数／前年客数＊１００
        	zennenhiKyakusu 	= Calculator.percentage(String.valueOf( miseData.getKyakusu()),String.valueOf(miseData.getZennenKyakusu()),2);
        	//　客単価　＝　売上げ／客数
        	kyakuTanka 	= Calculator.divide(String.valueOf( miseData.getUriage()),String.valueOf(miseData.getKyakusu()));
            //　前年客単価　＝　前年実績／前年客数
        	zennenKakuTanka = Calculator.divide(String.valueOf( miseData.getZennenUriage()),String.valueOf(miseData.getZennenKyakusu()));
        	//　前年比客単価　＝　客単価／前年客単価＊１００
            zennenhiKyakuTanka = Calculator.percentage(String.valueOf(kyakuTanka),String.valueOf(zennenKakuTanka),2);
	    	//　NET客単価　＝　売上げ／客数
	    	kyakuTankaNet 	= Calculator.divide(String.valueOf( miseData.getZennenHiTaisyoUriage()),String.valueOf(miseData.getZennenHiTaisyoKyakusu()));
	        //　NET前年客単価　＝　前年実績／前年客数
	    	zennenKakuTankaNet = Calculator.divide(String.valueOf( miseData.getZennenHiTaisyoMaeUriage()),String.valueOf(miseData.getZennenHiTaisyoMaeKyakusu()));
	        //　NET前年比（売上げ）　＝　売上げ／前年実績＊１００
	    	zennenhiUriNet 		= Calculator.percentage(String.valueOf( miseData.getZennenHiTaisyoUriage()),String.valueOf(miseData.getZennenHiTaisyoMaeUriage()),2);
	    	//　NET前年比（客数）＝　客数／前年客数＊１００
	    	zennenhiKyakusuNet 	= Calculator.percentage(String.valueOf( miseData.getZennenHiTaisyoKyakusu()),String.valueOf(miseData.getZennenHiTaisyoMaeKyakusu()),2);
	    	//　NET前年比客(単価)　＝　客単価／前年客単価＊１００
	        zennenhiKyakuTankaNet = Calculator.percentage(String.valueOf(kyakuTankaNet),String.valueOf(zennenKakuTankaNet),2);
        }
        catch (Exception ex) {
            throw new FtlSystemException("前年比の計算", null, ex);
        }
        //前年同月営業日補正の場合下記の処理を行う。
        if(NipoZennenDataShubetu.CODE_HOSEI.equals(zenDataShubetu)) {
        	//1.前年比（売上）をNET前年比（売上）にする。
        	zennenhiUri = zennenhiUriNet;
        	//2.前年比（客数）をNET前年比（客数）にする。
        	zennenhiKyakusu = zennenhiKyakusuNet;
        	//3.前年比（単価）をNET前年比（単価）にする。
        	zennenhiKyakuTanka = zennenhiKyakuTankaNet;
        }
        
		if ( ritu.intValue() == 0 || ritu.intValue() < 0) 		{  	ritu = new BigDecimal("0.00");  		}
		if ( kyakuTanka.intValue() == 0 ) 	{  	kyakuTanka = new BigDecimal("0.00"); 	}
		if ( zennenhiKyakusu.intValue() == 0 ) 	{  	zennenhiKyakusu = new BigDecimal("0.00");  	}
		if ( zennenhiUri.intValue() == 0 ) 		{  	zennenhiUri = new BigDecimal("0.00");  		}
		if ( zennenhiKyakuTanka.intValue() == 0 ){  	zennenhiKyakuTanka = new BigDecimal("0.00");  		}
		        
		//率のセット 赤モス		
		miseData.setTaseiRitu(ritu);
		miseData.setKyakuTanka(kyakuTanka);
        miseData.setZennenHiKyakuTanka(zennenhiKyakuTanka);
        miseData.setZennenHiKyakusu(zennenhiKyakusu);
        miseData.setZennenHiUriage(zennenhiUri);
        miseData.setZennenKyakuTanka(zennenKakuTanka);
        //NET値のセット
        miseData.setZennenHiTaisyoKyakuTanka(kyakuTankaNet);
        miseData.setZennenHiTaisyoMaeKyakuTanka(zennenhiKyakuTankaNet);
		       
		//率が100以下の場合の表示
        if(ritu.intValue() < 100){
        	if(totalFlg){
        		miseData.setTaseiRituClass(GyotaibetuNipoConstants.bodyHirituClass);
        	}else{
        		miseData.setTaseiRituClass(GyotaibetuNipoConstants.bodyHirituClassM);
        	}
        }else{
        	if(totalFlg){
        		miseData.setTaseiRituClass(GyotaibetuNipoConstants.bodyNumClass);
        	}else{
        		miseData.setTaseiRituClass(GyotaibetuNipoConstants.bodyNumClassN);
        	}
        }
        
        if(zennenhiUri.intValue() < 100){
        	if(totalFlg){
	        	miseData.setZennenHiUriageClass(GyotaibetuNipoConstants.bodyHirituClass);
        	}else{
	        	miseData.setZennenHiUriageClass(GyotaibetuNipoConstants.bodyHirituClassM);
        	}
        }else{
        	if(totalFlg){
	        	miseData.setZennenHiUriageClass(GyotaibetuNipoConstants.bodyNumClass);
        	}else{
	        	miseData.setZennenHiUriageClass(GyotaibetuNipoConstants.bodyNumClassN);
        	}
        }
        
        if(zennenhiKyakusu.intValue() < 100){
        	if(totalFlg){
	        	miseData.setZennenHiKyakusuClass(GyotaibetuNipoConstants.bodyHirituClass);
        	}else{
	        	miseData.setZennenHiKyakusuClass(GyotaibetuNipoConstants.bodyHirituClassM);
        	}
	    }else{
	    	if(totalFlg){
		    	miseData.setZennenHiKyakusuClass(GyotaibetuNipoConstants.bodyNumClass);
	    	}else{
		    	miseData.setZennenHiKyakusuClass(GyotaibetuNipoConstants.bodyNumClassN);
	    	}
	    }
        
        if(zennenhiKyakuTanka.intValue() < 100){
        	if(totalFlg){
	        	miseData.setZennenHiKyakuTankaClass(GyotaibetuNipoConstants.bodyHirituClass);
        	}else{
	        	miseData.setZennenHiKyakuTankaClass(GyotaibetuNipoConstants.bodyHirituClassM);
        	}
	    }else{
	    	if(totalFlg){
		    	miseData.setZennenHiKyakuTankaClass(GyotaibetuNipoConstants.bodyNumClass);
	    	}else{
		    	miseData.setZennenHiKyakuTankaClass(GyotaibetuNipoConstants.bodyNumClassN);
	    	}
	    }

		//モス情報をリストへ追加する。
		viewList.add(miseData);
    	
		return viewList;
	}
    
    public UIMiseListInfoDao getUIMiseListInfoDao() {
		return uIMiseListInfoDao;
	}

	public void setUIMiseListInfoDao(UIMiseListInfoDao miseListInfoDao) {
		uIMiseListInfoDao = miseListInfoDao;
	}

	public UIYagoListInfoDao getUIYagoListInfoDao() {
		return uIYagoListInfoDao;
	}

	public void setUIYagoListInfoDao(UIYagoListInfoDao yagoListInfoDao) {
		uIYagoListInfoDao = yagoListInfoDao;
	}

}
