package jp.co.isid.mos.bird.bizreport.gyotaibetuniporef.logic.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;

import jp.co.isid.mos.bird.bizreport.gyotaibetuniporef.dao.UIMiseListInfoDao;
import jp.co.isid.mos.bird.bizreport.gyotaibetuniporef.dao.UIYagoListInfoDao;
import jp.co.isid.mos.bird.bizreport.gyotaibetuniporef.logic.GyotaibetuNipoRefOnerLogic;
import jp.co.isid.mos.bird.bizreport.gyotaibetuniporef.entity.UIMiseListInfo;

import jp.co.isid.mos.bird.bizreport.common.code.TaishoKikan;
import jp.co.isid.mos.bird.bizreport.common.code.TenpoShubetu;
import jp.co.isid.mos.bird.bizreport.common.code.TenkoKbn;
import jp.co.isid.mos.bird.bizreport.gyotaibetuniporef.common.GyotaibetuNipoConstants;

import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.util.Calculator;

public class GyotaibetuNipoRefOnerLogicImpl implements GyotaibetuNipoRefOnerLogic {
    public static final String LOGIC_ID = "BBR005L05";
    
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
     */
    public List execute(Map paramMap) {
        
        String companyCd = (String)paramMap.get( GyotaibetuNipoConstants.COMPANY_CD );
        String onerCd = (String)paramMap.get( GyotaibetuNipoConstants.ONER_CD );
        String dataShu = (String)paramMap.get( GyotaibetuNipoConstants.DATASHU );
        String taishoKikan = (String)paramMap.get( GyotaibetuNipoConstants.TAISHO_KIKAN );
        String kikanFrom = (String)paramMap.get( GyotaibetuNipoConstants.KIKAN_FROM );
        String kikanTo = (String)paramMap.get( GyotaibetuNipoConstants.KIKAN_TO );
                
        List resultList = new ArrayList();
        
        if (TaishoKikan.DAY1.equals(taishoKikan) || TaishoKikan.DAYS.equals(taishoKikan)) {
        
        	//支部別売上げデータを取得
        	resultList = getUIMiseListInfoDao().getNipoOnerInfo( companyCd
											                ,onerCd
											                ,dataShu
											                ,taishoKikan
											                ,kikanFrom
											                ,kikanTo);
        }else{
        	
        	//支部別売上げデータを取得
        	resultList = getUIMiseListInfoDao().getGepoOnerInfo( companyCd
											                ,onerCd
											                ,dataShu
											                ,taishoKikan
											                ,kikanFrom
											                ,kikanTo);
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
    public List editViewDataList(List miseList) {
    	
    	//画面表示用のリスト
    	List viewList = new ArrayList();
    	UIMiseListInfo miseData = new UIMiseListInfo();
    	boolean totalFlg = true;
    	boolean checkTotalFlg = false;
    	
    	//最終1行は不要データのためカットする。（miseList.size() - 1）
        for(int i = 0 ; i < miseList.size()-1 ; i++){
    	
        	//検索結果リストからi番目のレコードを取り出す。
        	miseData = (UIMiseListInfo) miseList.get(i);
        	        	
        	if(miseData.getMiseCd() == null){            	
        		//表示区分にtotalをセット
        		miseData.setDispCode(GyotaibetuNipoConstants.total);
        		//CSSのセット
        		miseData.setCssClass(GyotaibetuNipoConstants.rClassSum5);
            	//表示フラグのセット
            	miseData.setViewFlg(totalFlg);
            	
        		totalFlg = false;
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
        	}
        	
        	viewList = this.addMiseList(viewList,miseData,checkTotalFlg);
    	}
		
    	return viewList;
    }
         
    /**
     * 店舗情報の表示用リストへの追加
     * @param sibuRedMos
     * @return viewList
     */
    public List addMiseList(List viewList,UIMiseListInfo miseData,boolean totalFlg) {
		
    	//屋号情報を編集後にリストへ追加    		
    	BigDecimal ritu = null;
    	BigDecimal kyakuTanka = null;
    	BigDecimal zennenhiKyakusu = null;
    	BigDecimal zennenhiUri = null;
    	BigDecimal zennenhiKyakuTanka = null;
    	BigDecimal zennenKakuTanka = null;
	
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
        }
        catch (Exception ex) {
            throw new FtlSystemException("前年比計算", null, ex);
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
    
    /**
     * 店情報の合計値の加算処理
     * @param totalBlock
     * @param plsMise
     * @return totalBlock
     */
    public UIMiseListInfo setTotalMise(UIMiseListInfo totalBlock,UIMiseListInfo plsMise){
    		
    	totalBlock.setUriage( totalBlock.getUriage().add(plsMise.getUriage()) );
	    totalBlock.setZennenUriage( totalBlock.getZennenUriage().add(plsMise.getZennenUriage()) );
	    totalBlock.setYosan( totalBlock.getYosan().add(plsMise.getYosan()) );
	    totalBlock.setKyakusu( totalBlock.getKyakusu() + plsMise.getKyakusu() );
	    totalBlock.setZennenKyakusu( totalBlock.getZennenKyakusu() + plsMise.getZennenKyakusu() );
	    totalBlock.setEigyoDays( totalBlock.getEigyoDays() + plsMise.getEigyoDays() );
	    totalBlock.setZennenEigyoDays( totalBlock.getZennenEigyoDays() + plsMise.getZennenEigyoDays() );
	    
    	return totalBlock;
    }
       
    /**
     * 店舗情報の初期化
     * @param UIMiseListInfo firstData
     * @param String cssClass
     * @return String oyaYagoCode
     */
	public UIMiseListInfo clear(UIMiseListInfo miseData,UIMiseListInfo firstData,String dispCode,String cssClass){
		
		miseData.setMiseCd( firstData.getMiseCd() );
		miseData.setMiseNameKj( firstData.getMiseNameKj() );
		miseData.setBlockCd( firstData.getBlockCd() );
		miseData.setMosgKbn( firstData.getMosgKbn() );
		miseData.setUriage(new BigDecimal(0));
		miseData.setYosan(new BigDecimal(0));
		miseData.setZennenUriage(new BigDecimal(0));
		miseData.setZennenKyakusu(0);
		miseData.setTaseiRitu(new BigDecimal(0));
		miseData.setKyakuTanka(new BigDecimal(0));
		miseData.setZennenHiKyakuTanka(new BigDecimal(0));
		miseData.setZennenHiKyakusu(new BigDecimal(0));
		miseData.setZennenHiUriage(new BigDecimal(0));
		miseData.setUriage( new BigDecimal(0) );
		miseData.setZennenUriage( new BigDecimal(0) );
		miseData.setYosan( new BigDecimal(0) );
		miseData.setKyakusu(0);
		miseData.setZennenKyakusu( 0 );
    	//CSSクラス
		miseData.setCssClass(cssClass);
		//ディスプレイコードのセット
		miseData.setDispCode(dispCode);
		
		return miseData;
	}
	
    /**
     * 詳細屋号マスタの取得
     * @param compcd
     * @return resultList
     */
    public List getYagoDtlList() {
    	
    	List resultList = new ArrayList();
    	
    	resultList = getUIYagoListInfoDao().getDtlYagoInfo();
    	
        // データが０件の場合は結果無し
        if ( resultList == null || resultList.size() == 0 ) {
            throw new NoResultException();
        }
        
        return resultList;
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
