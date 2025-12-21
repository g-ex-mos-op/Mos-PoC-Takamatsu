package jp.co.isid.mos.bird.bizreport.gyotaibetuniporef.entity;

import java.math.BigDecimal;
import java.util.List;

import jp.co.isid.mos.bird.bizreport.gyotaibetuniporef.common.GyotaibetuNipoCommon;
import jp.co.isid.mos.bird.bizreport.gyotaibetuniporef.entity.UISibuListInfo;
import jp.co.isid.mos.bird.bizreport.gyotaibetuniporef.entity.UIYagoListInfo;

public class UITotalData {
    
	public UISibuViewList yagoTotalData[];
	
	public void clear(UISibuListInfo firstData,String dispCode,String cssClass,List yagoList){
			
		this.setYagoListdata(yagoList);
		
		for(int i = 0 ; i < yagoTotalData.length ; i++){
			
		yagoTotalData[i].setSibuCd( firstData.getSibuCd() );
		yagoTotalData[i].setSibuName( GyotaibetuNipoCommon.setEmpty(firstData.getSibuName()) );
		yagoTotalData[i].setHonbuCd( firstData.getHonbuCd() );
		yagoTotalData[i].setHonbuName( GyotaibetuNipoCommon.setEmpty(firstData.getHonbuName()) );
		yagoTotalData[i].setJigyouCd( firstData.getJigyouCd() );
		yagoTotalData[i].setJigyouName( GyotaibetuNipoCommon.setEmpty(firstData.getJigyouName()) );
		yagoTotalData[i].setSlareaCd( firstData.getSlareaCd() );
		yagoTotalData[i].setSlareaName( GyotaibetuNipoCommon.setEmpty(firstData.getSlareaName()) );
		yagoTotalData[i].setMosgKbn( firstData.getMosgKbn() );

		yagoTotalData[i].setUriage(new BigDecimal(0));
		yagoTotalData[i].setYosan(new BigDecimal(0));
    	yagoTotalData[i].setKyakusu( 0 );
    	yagoTotalData[i].setTaseiRitu(new BigDecimal(0));
		yagoTotalData[i].setKyakuTanka(new BigDecimal(0));
		yagoTotalData[i].setZennenUriage(new BigDecimal(0));
		yagoTotalData[i].setZennenKyakusu(0);
		yagoTotalData[i].setZennenHiKyakuTanka(new BigDecimal(0));
		yagoTotalData[i].setZennenHiKyakusu(new BigDecimal(0));
		yagoTotalData[i].setZennenHiUriage(new BigDecimal(0));
		yagoTotalData[i].setCount( 0 );
		//NET値の初期化
		yagoTotalData[i].setZennenHiTaisyoUriage(new BigDecimal(0));
		yagoTotalData[i].setZennenHiTaisyoMaeUriage(new BigDecimal(0));
		yagoTotalData[i].setZennenHiTaisyoKyakusu(new BigDecimal(0));
		yagoTotalData[i].setZennenHiTaisyoMaeKyakusu(new BigDecimal(0));
		yagoTotalData[i].setZennenHiTaisyoKyakuTanka(new BigDecimal(0));
		yagoTotalData[i].setZennenHiTaisyoMaeKyakuTanka(new BigDecimal(0));
    	
    	//ディスプレイコードのセット
    	yagoTotalData[i].setDispCode(dispCode);
    	//CSSクラス
    	yagoTotalData[i].setCssClass(cssClass);   	
    	
		}
		
	}
	
	public void setYagoListdata(List yagoList){
		
		yagoTotalData = new UISibuViewList[yagoList.size()];
		
		for(int i = 0 ; i < yagoList.size() ; i++){
			
			UIYagoListInfo yagoData = (UIYagoListInfo) yagoList.get(i);
			
			yagoTotalData[i] = new UISibuViewList();
			 
			yagoTotalData[i].setYagoCd( yagoData.getYagoCd() );
			yagoTotalData[i].setYagoName( GyotaibetuNipoCommon.setEmpty(yagoData.getYagoName()) );
			yagoTotalData[i].setYagoDtlCd( yagoData.getYagoDetailCd() );
			yagoTotalData[i].setYagoDtlName( GyotaibetuNipoCommon.setEmpty(yagoData.getYagoDetailName()) );
			
		}
	}
}
