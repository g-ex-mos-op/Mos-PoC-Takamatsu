package jp.co.isid.mos.bird.bizreport.gyotaibetuniporef.logic.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizreport.common.code.NipoZennenDataShubetu;
import jp.co.isid.mos.bird.bizreport.common.code.ShukeiKbn;
import jp.co.isid.mos.bird.bizreport.common.code.TaishoKikan;
import jp.co.isid.mos.bird.bizreport.common.code.TenpoShubetu;
import jp.co.isid.mos.bird.bizreport.common.dto.NipoRefConditionParameterDto;
import jp.co.isid.mos.bird.bizreport.gyotaibetuniporef.common.GyotaibetuNipoConstants;
import jp.co.isid.mos.bird.bizreport.gyotaibetuniporef.dao.UISibuListInfoDao;
import jp.co.isid.mos.bird.bizreport.gyotaibetuniporef.dao.UIYagoListInfoDao;
import jp.co.isid.mos.bird.bizreport.gyotaibetuniporef.entity.UISibuListInfo;
import jp.co.isid.mos.bird.bizreport.gyotaibetuniporef.entity.UISibuViewList;
import jp.co.isid.mos.bird.bizreport.gyotaibetuniporef.entity.UITotalData;
import jp.co.isid.mos.bird.bizreport.gyotaibetuniporef.entity.UIYagoListInfo;
import jp.co.isid.mos.bird.bizreport.gyotaibetuniporef.logic.GyotaibetuNipoRefSibuLogic;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.util.Calculator;

/**
 * 屋号別売上 支部一覧ロジック
 * 
 * @author 
 *
 */
public class GyotaibetuNipoRefSibuLogicImpl implements GyotaibetuNipoRefSibuLogic {
    public static final String LOGIC_ID = "BBR005L01";
            
    private UISibuListInfoDao uISibuListInfoDao;
    
    private UIYagoListInfoDao uIYagoListInfoDao;
        
    /**
     * 屋号マスタの取得
     * @param compcd
     * @return resultList
     */
    public List getYagoList() {
    	
    	List resultList = new ArrayList();
    	
    	resultList = getUIYagoListInfoDao().getMstYagoInfo();
    	
        // データが０件の場合は結果無し
        if ( resultList == null || resultList.size() == 0 ) {
            throw new NoResultException();
        }
        
        return resultList;
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
    
    /**
     * 対象店舗数の取得
     * @param paramMap
     * @return count
     */
    public int getTenpoCount(Map paramMap) {
    	
    	String companyCd = (String)paramMap.get( GyotaibetuNipoConstants.COMPANY_CD );
        String userId = (String)paramMap.get( GyotaibetuNipoConstants.USER_ID );
        String tenpoShu = (String)paramMap.get( GyotaibetuNipoConstants.TENSHU );
        String dataShu = (String)paramMap.get( GyotaibetuNipoConstants.DATASHU );
        String taishoKikan = (String)paramMap.get( GyotaibetuNipoConstants.TAISHO_KIKAN );
        String kikanFrom = (String)paramMap.get( GyotaibetuNipoConstants.KIKAN_FROM );
        String kikanTo = (String)paramMap.get( GyotaibetuNipoConstants.KIKAN_TO );
        boolean limitFlg = ( (Boolean)paramMap.get( GyotaibetuNipoConstants.LIMIT_FLG ) ).booleanValue();
        String areaDaiFlg = (String)paramMap.get( GyotaibetuNipoConstants.AREA_DAI_FLG );
        String taishoTenpo =(String)paramMap.get( GyotaibetuNipoConstants.TAISHO_TENPO );
        String shukeiKbn = (String)paramMap.get( GyotaibetuNipoConstants.SHUKEI_KBN );
        
        String dataShuKbn = new String();
        if ( tenpoShu.equals( TenpoShubetu.CODE_ZENNEN ) ) {
            dataShuKbn = "1";
        } else if ( tenpoShu.equals( TenpoShubetu.CODE_YOSAN ) ) {
            dataShuKbn = "2";
        } else if ( tenpoShu.equals( TenpoShubetu.CODE_SIN ) ) {
            dataShuKbn = "3";
        } else {
        }
                
        //List countList = new ArrayList();
        int count = 0;
        
        if (TaishoKikan.DAY1.equals(taishoKikan) || TaishoKikan.DAYS.equals(taishoKikan)) {
        	
	        //対象店舗数を取得
        	count = getUISibuListInfoDao().getNipoTenpoCount( companyCd
											                ,userId
											                ,tenpoShu
											                ,shukeiKbn
											                ,dataShu
											                ,taishoKikan
											                ,kikanFrom
											                ,kikanTo
											                ,limitFlg
											                ,areaDaiFlg
											                ,taishoTenpo
											                ,dataShuKbn); 
        }else{
        	
	        //対象店舗数を取得
        	count = getUISibuListInfoDao().getGepoTenpoCount( companyCd
											                ,userId
											                ,tenpoShu
											                ,shukeiKbn
											                ,dataShu
											                ,taishoKikan
											                ,kikanFrom
											                ,kikanTo
											                ,limitFlg
											                ,areaDaiFlg
											                ,taishoTenpo
											                ,dataShuKbn); 
        }
        
        // データが０件の場合は結果無し
        if ( count <= 0) {
            throw new NoResultException();
        }/*else{
        	for(int i = 0 ; i < countList.size() ; i++){
        		UISibuListInfo sibuData = (UISibuListInfo) countList.get(i);
        		count = count + (int)sibuData.getCount();
        	}
        }*/
        
        return count;
    }

    /**
     * 支部別情報の取得
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
        String userId = (String)paramMap.get( GyotaibetuNipoConstants.USER_ID );
        String tenpoShu = (String)paramMap.get( GyotaibetuNipoConstants.TENSHU );
        String dataShu = (String)paramMap.get( GyotaibetuNipoConstants.DATASHU );
        String taishoKikan = (String)paramMap.get( GyotaibetuNipoConstants.TAISHO_KIKAN );
        String kikanFrom = (String)paramMap.get( GyotaibetuNipoConstants.KIKAN_FROM );
        String kikanTo = (String)paramMap.get( GyotaibetuNipoConstants.KIKAN_TO );
        boolean limitFlg = ( (Boolean)paramMap.get( GyotaibetuNipoConstants.LIMIT_FLG ) ).booleanValue();
        String taishoTenpo =(String)paramMap.get( GyotaibetuNipoConstants.TAISHO_TENPO );
        String shukeiKbn = (String)paramMap.get( GyotaibetuNipoConstants.SHUKEI_KBN );
        String userTypeCd = (String)paramMap.get(GyotaibetuNipoConstants.USER_TYPE_CD);
        
        List resultList = new ArrayList();
        
        if (TaishoKikan.DAY1.equals(taishoKikan) || TaishoKikan.DAYS.equals(taishoKikan)) {
        
	        //支部別日報売上げデータを取得
// 2007/06/11 ADD start xkinu 予算登録2次 クローズ店表示対応
	        resultList = getUISibuListInfoDao().selectNipo(
	        		userTypeCd, limitFlg, userId
	        		, companyCd, tenpoShu, shukeiKbn
	        		, taishoTenpo
	        		, taishoKikan, kikanFrom, kikanTo
	        		, dataShu);
// 2007/06/11 ADD end xkinu 予算登録2次 クローズ店表示対応
        }else{
        	
	        //支部別月報売上げデータを取得
// 2007/06/11 ADD start xkinu 予算登録2次 クローズ店表示対応
	        resultList = getUISibuListInfoDao().selectGepo(
	        		userTypeCd, limitFlg, userId
	        		, companyCd, tenpoShu, shukeiKbn
	        		, taishoTenpo
	        		, taishoKikan, kikanFrom, kikanTo
	        		, dataShu);
// 2007/06/11 ADD end xkinu 予算登録2次 クローズ店表示対応
        }
        
        // データが０件の場合は結果無し
        if ( resultList == null || resultList.size() == 0 ) {
        	throw new NoResultException();
        }else{
        	UISibuListInfo list = (UISibuListInfo)resultList.get(0);
        	if(list.getCount() <= 0){
        		throw new NoResultException();
        	}
        }
        
        return resultList;
    }

    /**
     * 画面表示用に整理する
     * 
     */
    public List editViewDataList(List sibuList, NipoRefConditionParameterDto conditionDto) {
    	String companyCd = conditionDto.getCompanyCd();
    	String shuKbn = conditionDto.getShukeiKbnCd();
		// 前年データ種別
		String zenDataShubetu = TenpoShubetu.CODE_ZENNEN.equals(conditionDto.getTenpoShubetuCd()) ?
				conditionDto.getZenDataZennenCd() : conditionDto.getZenDataZennenOthCd();
    	
    	//一レコード目のデータを取得
    	UISibuListInfo firstData = (UISibuListInfo) sibuList.get(0);
    	
    	String sibuCode = firstData.getSibuCd();
    	String areaCode = firstData.getSlareaCd();
    	String jigyoCode = firstData.getJigyouCd();
    	String toukatuCode  = firstData.getHonbuCd();
    	
    	//画面表示用のリスト
    	List viewList = new ArrayList();
    	//屋号情報リスト
    	List yagoList = new ArrayList();
    	yagoList = getYagoList();
    	//詳細屋号リスト
    	List yagoDtlList = new ArrayList();
    	yagoDtlList = getYagoDtlList();
    	
    	//屋号情報の合計保持用
    	UITotalData totalSibu = new UITotalData();
    	UITotalData totalArea = new UITotalData();
    	UITotalData totalJigyo = new UITotalData();
    	UITotalData totalToukatu = new UITotalData();
    	UITotalData totalData = new UITotalData();
    	//詳細屋号情報の合計保持用
    	UITotalData totalDtlSibu = new UITotalData();
    	UITotalData totalDtlArea = new UITotalData();
    	UITotalData totalDtlJigyo = new UITotalData();
    	UITotalData totalDtlToukatu = new UITotalData();
    	UITotalData totalDtlData = new UITotalData();
    	//屋号情報の初期化 屋号
    	totalSibu.clear(firstData,GyotaibetuNipoConstants.sibu,GyotaibetuNipoConstants.rClassSum1,yagoList);
    	totalArea.clear(firstData,GyotaibetuNipoConstants.area,GyotaibetuNipoConstants.rClassSum2,yagoList);
    	totalJigyo.clear(firstData,GyotaibetuNipoConstants.jigyo,GyotaibetuNipoConstants.rClassSum3,yagoList);
    	totalToukatu.clear(firstData,GyotaibetuNipoConstants.toukatu,GyotaibetuNipoConstants.rClassSum4,yagoList);
    	totalData.clear(firstData,GyotaibetuNipoConstants.total,GyotaibetuNipoConstants.rClassSum5,yagoList);
    	//屋号情報の初期化 詳細屋号
    	totalDtlSibu.clear(firstData,GyotaibetuNipoConstants.sibu,GyotaibetuNipoConstants.rClassSum1,yagoDtlList);
    	totalDtlArea.clear(firstData,GyotaibetuNipoConstants.area,GyotaibetuNipoConstants.rClassSum2,yagoDtlList);
    	totalDtlJigyo.clear(firstData,GyotaibetuNipoConstants.jigyo,GyotaibetuNipoConstants.rClassSum3,yagoDtlList);
    	totalDtlToukatu.clear(firstData,GyotaibetuNipoConstants.toukatu,GyotaibetuNipoConstants.rClassSum4,yagoDtlList);
    	totalDtlData.clear(firstData,GyotaibetuNipoConstants.total,GyotaibetuNipoConstants.rClassSum5,yagoDtlList);
    	 
    	//屋号加算フラグ
    	boolean isTotalFlg = true;
    	
        for(int i = 0 ; i < sibuList.size() ; i++){
    	
        	//検索結果リストからi番目のレコードを取り出す。
        	UISibuListInfo sibuData = (UISibuListInfo) sibuList.get(i);
        	        	
        	//エリア合計の処理
        	if( !sibuData.getSibuCd().equals(sibuCode)){
        		
        		//支部コードが変わった場合は、屋号加算フラグを初期化する。
        		isTotalFlg = false;
        		
        		//支部情報追加
        		viewList = this.addYagoList(viewList,totalSibu,1,false, zenDataShubetu);
        		viewList = this.addYagoList(viewList,totalDtlSibu,2,false, zenDataShubetu);
        		if(shuKbn.equals(ShukeiKbn.OUT_RC)
       				 && companyCd.equals(GyotaibetuNipoConstants.MOS)) {    		
	        		//エリア合計
	        		totalArea = this.setTotalYago(totalArea,totalSibu);
	        		totalDtlArea = this.setTotalYago(totalDtlArea,totalDtlSibu);
            	}
        		else {
        			//総合計の処理
        			totalData = this.setTotalYago(totalData,totalSibu);
        			totalDtlData = this.setTotalYago(totalDtlData,totalDtlSibu);
        		}
        		//支部情報の初期化
        		totalSibu = new UITotalData();
        		totalDtlSibu = new UITotalData();
            	totalSibu.clear(sibuData,GyotaibetuNipoConstants.sibu,GyotaibetuNipoConstants.rClassSum1,yagoList);
            	totalDtlSibu.clear(sibuData,GyotaibetuNipoConstants.sibu,GyotaibetuNipoConstants.rClassSum1,yagoDtlList);
        	}
        	if(shuKbn.equals(ShukeiKbn.OUT_RC)
   				 && companyCd.equals(GyotaibetuNipoConstants.MOS)) {
	        	//事業所合計の処理
	        	if( !sibuData.getSlareaCd().equals(areaCode)){
	        		
	        		//エリア情報追加
	        		if(!shuKbn.equals(ShukeiKbn.IN_RC) && companyCd.equals(GyotaibetuNipoConstants.MOS) ){
	        			viewList = this.addYagoList(viewList,totalArea,1,true, zenDataShubetu);
	        			viewList = this.addYagoList(viewList,totalDtlArea,2,true, zenDataShubetu);
	        		}
	        		
	        		//事業所合計の処理
	        		totalJigyo = this.setTotalYago(totalJigyo,totalArea);
	        		totalDtlJigyo = this.setTotalYago(totalDtlJigyo,totalDtlArea);
	        		
	        		//エリア情報の初期化
	        		totalArea = new UITotalData();
	        		totalDtlArea = new UITotalData();
	        		totalArea.clear(sibuData,GyotaibetuNipoConstants.area,GyotaibetuNipoConstants.rClassSum2,yagoList);
	        		totalDtlArea.clear(sibuData,GyotaibetuNipoConstants.area,GyotaibetuNipoConstants.rClassSum2,yagoDtlList);
	        	}
	        	//統括本部合計の処理
	        	if( !sibuData.getJigyouCd().equals(jigyoCode)){
	        		
	        		//事情所情報追加
	        		if(!shuKbn.equals(ShukeiKbn.IN_RC) && companyCd.equals(GyotaibetuNipoConstants.MOS) ){
		        		viewList = this.addYagoList(viewList,totalJigyo,1,true, zenDataShubetu);
		        		viewList = this.addYagoList(viewList,totalDtlJigyo,2,true, zenDataShubetu);
	        		}
	        		//統括本部合計の処理
	        		totalToukatu = this.setTotalYago(totalToukatu,totalJigyo);
	        		totalDtlToukatu = this.setTotalYago(totalDtlToukatu,totalDtlJigyo);
	        		
	        		//事業所情報の初期化
	        		totalJigyo = new UITotalData();
	        		totalDtlJigyo = new UITotalData();
	        		totalJigyo.clear(sibuData,GyotaibetuNipoConstants.jigyo,GyotaibetuNipoConstants.rClassSum3,yagoList);
	        		totalDtlJigyo.clear(sibuData,GyotaibetuNipoConstants.jigyo,GyotaibetuNipoConstants.rClassSum3,yagoDtlList);
	        	}
	        	//総合計の処理
	        	if( !sibuData.getHonbuCd().equals(toukatuCode)){
	        		
	        		//統括本部情報追加
	        		if(!shuKbn.equals(ShukeiKbn.IN_RC) && companyCd.equals(GyotaibetuNipoConstants.MOS) ){
		        		viewList = this.addYagoList(viewList,totalToukatu,1,true, zenDataShubetu);
		        		viewList = this.addYagoList(viewList,totalDtlToukatu,2,true, zenDataShubetu);
	        		}
	        		//総合計の処理
	        		totalData = this.setTotalYago(totalData,totalToukatu);
	        		totalDtlData = this.setTotalYago(totalDtlData,totalDtlToukatu);
	        		
	        		//統括本部情報の初期化
	        		totalToukatu = new UITotalData();
	        		totalDtlToukatu = new UITotalData();
	        		totalToukatu.clear(sibuData,GyotaibetuNipoConstants.toukatu,GyotaibetuNipoConstants.rClassSum4,yagoList);
	        		totalDtlToukatu.clear(sibuData,GyotaibetuNipoConstants.toukatu,GyotaibetuNipoConstants.rClassSum4,yagoDtlList);
	        	}
        	}

        	//i行目の支部情報のキーコードを取得する。
        	sibuCode = sibuData.getSibuCd();
        	areaCode = sibuData.getSlareaCd();
        	jigyoCode = sibuData.getJigyouCd();
        	toukatuCode  = sibuData.getHonbuCd();

        	//モス区分が空白の場合は、表示対象外とする。。
        	if(!sibuData.getMosgKbn().trim().equals("") && sibuData.getMosgKbn() != null){
        	        	
	        	//支部－詳細屋号情報コピー処理
	        	for(int y = 0 ; y < totalDtlSibu.yagoTotalData.length ; y++){
	        		UISibuViewList eUISibuView = totalDtlSibu.yagoTotalData[y];
	        		if(sibuData.getMosgKbn().equals( eUISibuView.getYagoDtlCd() )){
	        			//支部情報のコピー
	        			totalDtlSibu.yagoTotalData[y] = this.copyMosInfo(eUISibuView,sibuData);
	        		}
	        	}
	        	//支部－屋号情報加算処理
	        	for(int y = 0 ; y < totalSibu.yagoTotalData.length ; y++){
	        		UISibuViewList eUISibuView = totalSibu.yagoTotalData[y];
	        		//詳細屋号コードから親屋号コードを取得するクラス
	        		String oyaYagoCode = getOyaYagoCode( sibuData.getMosgKbn() , yagoDtlList);
	        		
	        		//親屋号コードが同じ場合の処理
	        		if(oyaYagoCode.equals( eUISibuView.getYagoCd() )){
	        			
	        			if(isTotalFlg){
	        				//支部情報のコピー
	        				totalSibu.yagoTotalData[y] = this.copyMosInfo(eUISibuView,sibuData);
	        				isTotalFlg = false;
	        			}else{
	        				//支部情報の加算
	        				totalSibu.yagoTotalData[y].setCount( eUISibuView.getCount() + sibuData.getCount() );
	        				totalSibu.yagoTotalData[y].setUriage( eUISibuView.getUriage().add(sibuData.getUriage()) );
	        				totalSibu.yagoTotalData[y].setYosan( eUISibuView.getYosan().add(sibuData.getYosan()) );
	        				totalSibu.yagoTotalData[y].setZennenUriage( eUISibuView.getZennenUriage().add(sibuData.getZennenUriage()) );
	        				totalSibu.yagoTotalData[y].setKyakusu( eUISibuView.getKyakusu() + sibuData.getKyakusu() );
	        				totalSibu.yagoTotalData[y].setZennenKyakusu( eUISibuView.getZennenKyakusu() + sibuData.getZennenKyakusu() );
	        				
	        				// 補正データの売上げ情報を　前年比対象項目へ転記
	        				totalSibu.yagoTotalData[y].setHoseiCount( eUISibuView.getCount() + sibuData.getTenpoCountNet() );
	        				totalSibu.yagoTotalData[y].setZennenHiTaisyoUriage( eUISibuView.getZennenHiTaisyoUriage().add(sibuData.getUriageNet()) );
	        				totalSibu.yagoTotalData[y].setZennenHiTaisyoMaeUriage( eUISibuView.getZennenHiTaisyoMaeUriage().add(sibuData.getUriageZenNet()) );
	        				totalSibu.yagoTotalData[y].setZennenHiTaisyoKyakusu( eUISibuView.getZennenHiTaisyoKyakusu().add(sibuData.getKyakusuNet()) );
	        				totalSibu.yagoTotalData[y].setZennenHiTaisyoMaeKyakusu( eUISibuView.getZennenHiTaisyoMaeKyakusu().add(sibuData.getKyakusuZenNet()) );
	        			}
	        		}
	        	}
        	}
    	}
        
    	//支部情報の追加
		viewList = this.addYagoList(viewList,totalSibu,1,false, zenDataShubetu);
		viewList = this.addYagoList(viewList,totalDtlSibu,2,false, zenDataShubetu);
		if(shuKbn.equals(ShukeiKbn.OUT_RC)
				 && companyCd.equals(GyotaibetuNipoConstants.MOS)) {
	   		//エリア合計処理       
			totalArea = this.setTotalYago(totalArea,totalSibu);
			totalDtlArea = this.setTotalYago(totalDtlArea,totalDtlSibu);
			
			//エリア情報の追加
			viewList = this.addYagoList(viewList,totalArea,1,true, zenDataShubetu);
			viewList = this.addYagoList(viewList,totalDtlArea,2,true, zenDataShubetu);
			//事業所合計の処理
			totalJigyo = this.setTotalYago(totalJigyo,totalArea);
			totalDtlJigyo = this.setTotalYago(totalDtlJigyo,totalDtlArea);
			
			//事情所情報追加
			viewList = this.addYagoList(viewList,totalJigyo,1,true, zenDataShubetu);
			viewList = this.addYagoList(viewList,totalDtlJigyo,2,true, zenDataShubetu);
			//統括本部合計の処理
			totalToukatu = this.setTotalYago(totalToukatu,totalJigyo);
			totalDtlToukatu = this.setTotalYago(totalDtlToukatu,totalDtlJigyo);
			
			//統括本部情報追加
			viewList = this.addYagoList(viewList,totalToukatu,1,true, zenDataShubetu);
			viewList = this.addYagoList(viewList,totalDtlToukatu,2,true, zenDataShubetu);
			//総合計の処理
			totalData = this.setTotalYago(totalData,totalToukatu);
			totalDtlData = this.setTotalYago(totalDtlData,totalDtlToukatu);
		}
		else {
			//総合計の処理
			totalData = this.setTotalYago(totalData,totalSibu);
			totalDtlData = this.setTotalYago(totalDtlData,totalDtlSibu);
		}
		viewList = this.addYagoList(viewList,totalData,1,true, zenDataShubetu);
		viewList = this.addYagoList(viewList,totalDtlData,2,true, zenDataShubetu);
		
    	return viewList;
    }
    
    /**
     * モス情報の表示用リストへの追加
     * 
     * @param viewList
     * @param totalYagoData
     * @param tabNo
     * @param totalFlg
     * @param zenDataShubetu 前年データ種別
     * @return
     */
    private List addYagoList(List viewList,UITotalData totalYagoData,int tabNo,boolean totalFlg, String zenDataShubetu) {
		
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

    	boolean vierFlg = true;
    	    	
    	for(int i = 0 ; i < totalYagoData.yagoTotalData.length ; i++){
    		if( totalYagoData.yagoTotalData[i].getCount() > 0 ){
    			
    			if(vierFlg){
    				totalYagoData.yagoTotalData[i].setViewFlg(true);
    				vierFlg = false;
    			}else{
    				totalYagoData.yagoTotalData[i].setViewFlg(false);
    			}
    		
		    	//前年比の計算
                try {
        			//　達成率　＝　売上げ／予算＊１００
    		    	ritu 		= Calculator.percentage(String.valueOf( totalYagoData.yagoTotalData[i].getUriage()),String.valueOf(totalYagoData.yagoTotalData[i].getYosan()),2);       
    		        //　前年比（売上げ）　＝　売上げ／前年実績＊１００
    		    	zennenhiUri 		= Calculator.percentage(String.valueOf( totalYagoData.yagoTotalData[i].getUriage()),String.valueOf(totalYagoData.yagoTotalData[i].getZennenUriage()),2);
    		    	//　前年比（客数）＝　客数／前年客数＊１００
    		    	zennenhiKyakusu 	= Calculator.percentage(String.valueOf( totalYagoData.yagoTotalData[i].getKyakusu()),String.valueOf(totalYagoData.yagoTotalData[i].getZennenKyakusu()),2);
    		    	//　客単価　＝　売上げ／客数
    		    	kyakuTanka 	= Calculator.divide(String.valueOf( totalYagoData.yagoTotalData[i].getUriage()),String.valueOf(totalYagoData.yagoTotalData[i].getKyakusu()));
    		        //　前年客単価　＝　前年実績／前年客数
    		    	zennenKakuTanka = Calculator.divide(String.valueOf( totalYagoData.yagoTotalData[i].getZennenUriage()),String.valueOf(totalYagoData.yagoTotalData[i].getZennenKyakusu()));
    		    	//　前年比客単価　＝　客単価／前年客単価＊１００
    		        zennenhiKyakuTanka = Calculator.percentage(String.valueOf(kyakuTanka),String.valueOf(zennenKakuTanka),2);
    		    	//　NET客単価　＝　売上げ／客数
    		    	kyakuTankaNet 	= Calculator.divide(String.valueOf( totalYagoData.yagoTotalData[i].getZennenHiTaisyoUriage()),String.valueOf(totalYagoData.yagoTotalData[i].getZennenHiTaisyoKyakusu()));
    		        //　NET前年客単価　＝　前年実績／前年客数
    		    	zennenKakuTankaNet = Calculator.divide(String.valueOf( totalYagoData.yagoTotalData[i].getZennenHiTaisyoMaeUriage()),String.valueOf(totalYagoData.yagoTotalData[i].getZennenHiTaisyoMaeKyakusu()));
    		        //　NET前年比（売上げ）　＝　売上げ／前年実績＊１００
    		    	zennenhiUriNet 		= Calculator.percentage(String.valueOf( totalYagoData.yagoTotalData[i].getZennenHiTaisyoUriage()),String.valueOf(totalYagoData.yagoTotalData[i].getZennenHiTaisyoMaeUriage()),2);
    		    	//　NET前年比（客数）＝　客数／前年客数＊１００
    		    	zennenhiKyakusuNet 	= Calculator.percentage(String.valueOf( totalYagoData.yagoTotalData[i].getZennenHiTaisyoKyakusu()),String.valueOf(totalYagoData.yagoTotalData[i].getZennenHiTaisyoMaeKyakusu()),2);
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
		        
		        //前年比のセット
		        totalYagoData.yagoTotalData[i].setTaseiRitu(ritu);
		        totalYagoData.yagoTotalData[i].setKyakuTanka(kyakuTanka);
		        totalYagoData.yagoTotalData[i].setZennenHiKyakuTanka(zennenhiKyakuTanka);
		        totalYagoData.yagoTotalData[i].setZennenHiKyakusu(zennenhiKyakusu);
		        totalYagoData.yagoTotalData[i].setZennenHiUriage(zennenhiUri);
		        totalYagoData.yagoTotalData[i].setZennenKyakuTanka(zennenKakuTanka);
		        //NET値のセット
		        totalYagoData.yagoTotalData[i].setZennenHiTaisyoKyakuTanka(kyakuTankaNet);
		        totalYagoData.yagoTotalData[i].setZennenHiTaisyoMaeKyakuTanka(zennenhiKyakuTankaNet);
		       
		        //率が100以下の場合の表示
		        if(ritu.intValue() < 100){
		        	if(totalFlg){
		        		totalYagoData.yagoTotalData[i].setTaseiRituClass(GyotaibetuNipoConstants.bodyHirituClass);
		        	}else{
		        		totalYagoData.yagoTotalData[i].setTaseiRituClass(GyotaibetuNipoConstants.bodyHirituClassM);
		        	}
		        }else{
		        	if(totalFlg){
		        		totalYagoData.yagoTotalData[i].setTaseiRituClass(GyotaibetuNipoConstants.bodyNumClass);
		        	}else{
		        		totalYagoData.yagoTotalData[i].setTaseiRituClass(GyotaibetuNipoConstants.bodyNumClassN);
		        	}
		        }
		        
		        if(zennenhiUri.intValue() < 100){
		        	if(totalFlg){
		        		totalYagoData.yagoTotalData[i].setZennenHiUriageClass(GyotaibetuNipoConstants.bodyHirituClass);
		        	}else{	
		        		totalYagoData.yagoTotalData[i].setZennenHiUriageClass(GyotaibetuNipoConstants.bodyHirituClassM);
		        	}	
		        }else{
		        	if(totalFlg){
		        		totalYagoData.yagoTotalData[i].setZennenHiUriageClass(GyotaibetuNipoConstants.bodyNumClass);
		        	}else{
		        		totalYagoData.yagoTotalData[i].setZennenHiUriageClass(GyotaibetuNipoConstants.bodyNumClassN);
		        	}
		        }
		        
		        if(zennenhiKyakusu.intValue() < 100){
		        	if(totalFlg){
		        		totalYagoData.yagoTotalData[i].setZennenHiKyakusuClass(GyotaibetuNipoConstants.bodyHirituClass);
		        	}else{	
		        		totalYagoData.yagoTotalData[i].setZennenHiKyakusuClass(GyotaibetuNipoConstants.bodyHirituClassM);
		        	}	
		        }else{
		        	if(totalFlg){
		        		totalYagoData.yagoTotalData[i].setZennenHiKyakusuClass(GyotaibetuNipoConstants.bodyNumClass);
		        	}else{
		        		totalYagoData.yagoTotalData[i].setZennenHiKyakusuClass(GyotaibetuNipoConstants.bodyNumClassN);
		        	}
		        }
		        
		        if(zennenhiKyakuTanka.intValue() < 100){
		        	if(totalFlg){
		        		totalYagoData.yagoTotalData[i].setZennenHiKyakuTankaClass(GyotaibetuNipoConstants.bodyHirituClass);
		        	}else{
		        		totalYagoData.yagoTotalData[i].setZennenHiKyakuTankaClass(GyotaibetuNipoConstants.bodyHirituClassM);
		        	}
		        }else{
		        	if(totalFlg){
		        		totalYagoData.yagoTotalData[i].setZennenHiKyakuTankaClass(GyotaibetuNipoConstants.bodyNumClass);
		        	}else{
		        		totalYagoData.yagoTotalData[i].setZennenHiKyakuTankaClass(GyotaibetuNipoConstants.bodyNumClassN);
		        	}
		        }
		        
		        //表示するタブＮｏのセット
		        totalYagoData.yagoTotalData[i].setTabNo(tabNo);
		        
				//モス情報をリストへ追加する。
				viewList.add(totalYagoData.yagoTotalData[i]);
			
    		}

    	}
    	
		return viewList;
	}
    
    /**
     * モス情報の合計値の加算処理
     * @param totalMos
     * @param plsMos
     * @return totalMos
     */
    private UITotalData setTotalYago(UITotalData totalYago,UITotalData plsYago){
    	
    	for(int i = 0 ; i < totalYago.yagoTotalData.length ; i++){
    		
    		totalYago.yagoTotalData[i].setCount( totalYago.yagoTotalData[i].getCount() + plsYago.yagoTotalData[i].getCount() );
    		totalYago.yagoTotalData[i].setUriage( totalYago.yagoTotalData[i].getUriage().add(plsYago.yagoTotalData[i].getUriage()) );
	    	totalYago.yagoTotalData[i].setZennenUriage( totalYago.yagoTotalData[i].getZennenUriage().add(plsYago.yagoTotalData[i].getZennenUriage()) );
	    	totalYago.yagoTotalData[i].setYosan( totalYago.yagoTotalData[i].getYosan().add(plsYago.yagoTotalData[i].getYosan()) );
	    	totalYago.yagoTotalData[i].setKyakusu( totalYago.yagoTotalData[i].getKyakusu() + plsYago.yagoTotalData[i].getKyakusu() );
	    	totalYago.yagoTotalData[i].setZennenKyakusu( totalYago.yagoTotalData[i].getZennenKyakusu() + plsYago.yagoTotalData[i].getZennenKyakusu() );
	    	
	    	totalYago.yagoTotalData[i].setHoseiCount( totalYago.yagoTotalData[i].getHoseiCount()+plsYago.yagoTotalData[i].getHoseiCount() );
	    	totalYago.yagoTotalData[i].setZennenHiTaisyoUriage( totalYago.yagoTotalData[i].getZennenHiTaisyoUriage().add(plsYago.yagoTotalData[i].getZennenHiTaisyoUriage()) );
	    	totalYago.yagoTotalData[i].setZennenHiTaisyoMaeUriage( totalYago.yagoTotalData[i].getZennenHiTaisyoMaeUriage().add(plsYago.yagoTotalData[i].getZennenHiTaisyoMaeUriage()) );
	    	totalYago.yagoTotalData[i].setZennenHiTaisyoKyakusu( totalYago.yagoTotalData[i].getZennenHiTaisyoKyakusu().add(plsYago.yagoTotalData[i].getZennenHiTaisyoKyakusu()) );
	    	totalYago.yagoTotalData[i].setZennenHiTaisyoMaeKyakusu( totalYago.yagoTotalData[i].getZennenHiTaisyoMaeKyakusu().add(plsYago.yagoTotalData[i].getZennenHiTaisyoMaeKyakusu()) );
    	}
    	
    	return totalYago;
    }
    
    /**
     * モス情報のコピー
     * @param totalMos
     * @param plsMos
     * @return totalMos
     */
    private UISibuViewList copyMosInfo(UISibuViewList sibuMos,UISibuListInfo sibuData){
    	
    	sibuMos.setCount( sibuData.getCount() );
    	sibuMos.setSibuCd( sibuData.getSibuCd() );
    	sibuMos.setSibuName( sibuData.getSibuName() );
    	sibuMos.setHonbuCd( sibuData.getHonbuCd() );
    	sibuMos.setHonbuName( sibuData.getHonbuName() );
    	sibuMos.setJigyouCd( sibuData.getJigyouCd() );
    	sibuMos.setJigyouName( sibuData.getJigyouName() );
    	sibuMos.setSlareaCd( sibuData.getSlareaCd() );
    	sibuMos.setSlareaName( sibuData.getSlareaName() );
    	sibuMos.setMosgKbn( sibuData.getMosgKbn() );
    	sibuMos.setUriage( sibuData.getUriage() );
    	sibuMos.setYosan( sibuData.getYosan() );
    	sibuMos.setZennenUriage( sibuData.getZennenUriage() );
    	sibuMos.setKyakusu( sibuData.getKyakusu() );
    	sibuMos.setZennenKyakusu( sibuData.getZennenKyakusu() );
    	
		// 補正データの売上げ情報を　前年比対象項目へ転記
    	sibuMos.setHoseiCount( sibuData.getTenpoCountNet() );
    	sibuMos.setZennenHiTaisyoUriage( sibuData.getUriageNet() );
    	sibuMos.setZennenHiTaisyoMaeUriage( sibuData.getUriageZenNet() );
		// 補正データの客数情報を　　前年比対象項目へ転記
    	sibuMos.setZennenHiTaisyoKyakusu( sibuData.getKyakusuNet() );
    	sibuMos.setZennenHiTaisyoMaeKyakusu( sibuData.getKyakusuZenNet() );
    	
    	return sibuMos;
    }
    
    /**
     * 親屋号コードの取得
     * @param String mosgKbn
     * @param List yagoDtlList
     * @return String oyaYagoCode
     */
    private String getOyaYagoCode(String mosgKbn , List yagoDtlList){
    	
    	String oyaYagoCode = null;
    	UIYagoListInfo yagoData;
    	
    	for(int i = 0 ; i < yagoDtlList.size() ; i++){
        	
        	//検索結果リストからi番目のレコードを取り出す。
    		yagoData = (UIYagoListInfo) yagoDtlList.get(i);
    		
    		if(yagoData.getYagoDetailCd().equals(mosgKbn) ){
    			
    			//親屋号コードを返す
    			return yagoData.getYagoCd();
    		}
    	}
    	
    	return oyaYagoCode;
    }
    
    public UISibuListInfoDao getUISibuListInfoDao() {
		return uISibuListInfoDao;
	}

	public void setUISibuListInfoDao(UISibuListInfoDao sibuListInfoDao) {
		uISibuListInfoDao = sibuListInfoDao;
	}

	public UIYagoListInfoDao getUIYagoListInfoDao() {
		return uIYagoListInfoDao;
	}

	public void setUIYagoListInfoDao(UIYagoListInfoDao yagoListInfoDao) {
		uIYagoListInfoDao = yagoListInfoDao;
	}
}
