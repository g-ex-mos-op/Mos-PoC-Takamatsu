package jp.co.isid.mos.bird.bizreport.gyotaibetuniporef.logic.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizreport.common.code.NipoZennenDataShubetu;
import jp.co.isid.mos.bird.bizreport.common.code.ShukeiKbn;
import jp.co.isid.mos.bird.bizreport.common.code.TaishoTenpo;
import jp.co.isid.mos.bird.bizreport.common.code.TenpoShubetu;
import jp.co.isid.mos.bird.bizreport.common.dto.NipoRefConditionParameterDto;
import jp.co.isid.mos.bird.bizreport.common.util.NipoRefUtil;
import jp.co.isid.mos.bird.bizreport.gyotaibetuniporef.common.GyotaibetuNipoConstants;
import jp.co.isid.mos.bird.bizreport.gyotaibetuniporef.entity.UISibuViewList;
import jp.co.isid.mos.bird.bizreport.gyotaibetuniporef.logic.GyotaibetuNipoRefSibuLogic;
import jp.co.isid.mos.bird.bizreport.gyotaibetuniporef.logic.GyotaibetuNipoSearchLogic;
import jp.co.isid.mos.bird.common.code.TaishoJoken;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;
import jp.co.isid.mos.bird.framework.util.formatter.NumericFormatter;

public class GyotaibetuNipoSibuCsvOutputLogicImpl implements CsvOutputLogic {
    public static final String LOGIC_ID = "BBR005L03";
    
    private GyotaibetuNipoRefSibuLogic gyotaibetuNipoRefSibuLogic;
    private GyotaibetuNipoSearchLogic gyotaibetuNipoSearchLogic;
    
    private final String FILE_NAME = "YAGO.csv";
	// 数値タイプの文字列フォーマッタ(定数)
	NumericFormatter uriageFormat = new NumericFormatter(false, 0, true);
    
    /**
     * ファイル名称を取得する
     * @return FILE_NAME
     */
    public String getFileName(CsvOutputDto csvOutputDto) {   
        return FILE_NAME;
    }
    
    /**
     * ヘッダー情報作成
     * @param csvOutputDto
     * @return
     */
    public List getHeaderData(CsvOutputDto csvOutputDto) {
    	
		List headerList = new ArrayList();
		
		// 屋号別売上日報条件部DTO取得
    	NipoRefConditionParameterDto dto = (NipoRefConditionParameterDto) csvOutputDto;
		
		// 前年データ種別
		String zenDataShu =  TenpoShubetu.CODE_ZENNEN.equals(dto.getTenpoShubetuCd()) ?
		dto.getZenDataZennenCd() : dto.getZenDataZennenOthCd();
		
		// １行目:会社、対象店舗
		List head1stList = new ArrayList();
		head1stList.add(GyotaibetuNipoConstants.CSV_HD_COMPANY);
		head1stList.add(dto.getCompanyName());
		head1stList.add(GyotaibetuNipoConstants.EMPTY);
		head1stList.add(GyotaibetuNipoConstants.CSV_HD_TAISHO_TENPO);
		head1stList.add(TaishoTenpo.getName(dto.getTaishoTenpoCd()));
		
		// ２行目:店舗種別、対象期間
		List head2ndList = new ArrayList();       
		head2ndList.add(GyotaibetuNipoConstants.CSV_HD_TENPO_SHU);
		head2ndList.add(TenpoShubetu.getName(dto.getTenpoShubetuCd()));
		head2ndList.add(GyotaibetuNipoConstants.EMPTY);
		head2ndList.add(GyotaibetuNipoConstants.CSV_HD_TAISHO_KIKAN);
		head2ndList.add(NipoRefUtil.getCsvTaishoKikan(dto));
        
		// ３行目:集計区分、前年データ種別、タブ名
		List head3rdList = new ArrayList();
		head3rdList.add(GyotaibetuNipoConstants.CSV_HD_SHUKEI_KBN);
		head3rdList.add(ShukeiKbn.getName(dto.getShukeiKbnCd()));
		head3rdList.add(GyotaibetuNipoConstants.EMPTY);
		head3rdList.add(GyotaibetuNipoConstants.CSV_HD_ZEN_DATA_SHU);
		head3rdList.add(NipoZennenDataShubetu.getName
            (zenDataShu, dto.getBirdUserInfo().getMstUser().getUserTypeCd()));
		head3rdList.add(GyotaibetuNipoConstants.EMPTY);
		
		head3rdList.add(GyotaibetuNipoConstants.CSV_HD_TAB);
		if (dto.getSubTagKbn().equals(GyotaibetuNipoConstants.TAB_NO_URIAGE) || 
				dto.getSubTagKbn().equals(GyotaibetuNipoConstants.TAB_NO_KYAKUSU)) {
				head3rdList.add(GyotaibetuNipoConstants.TAB_TITLE);
		}else if(dto.getSubTagKbn().equals(GyotaibetuNipoConstants.TAB_NO_DTL_URIAGE) || 
				dto.getSubTagKbn().equals(GyotaibetuNipoConstants.TAB_NO_DTL_KYAKUSU)) {
				head3rdList.add(GyotaibetuNipoConstants.TAB_TITLE_DTL);
		}
		
		// ４行目
		List head4thList = new ArrayList();
        
        // ５行目
        List head5thList = new ArrayList();

        if (ShukeiKbn.IN_RC.equals(dto.getShukeiKbnCd())) {
        	head5thList.add("集計区分");
        }else{
	        head5thList.add("事業本部ｺｰﾄﾞ");
	        head5thList.add("事業本部名称");
	        head5thList.add("ｴﾘｱｺｰﾄﾞ");
	        head5thList.add("ｴﾘｱ名称");
	        head5thList.add("支部ｺｰﾄﾞ");
	        head5thList.add("支部名称");
    	}
        head5thList.add("屋号");
        head5thList.add("店舗数");  
        
        if ( zenDataShu.equals( NipoZennenDataShubetu.CODE_HOSEI ) ) {
        	head5thList.add("前年比対象店舗数");
        }
        
        head5thList.add("売上");
        head5thList.add("予算");
        head5thList.add("達成率");
        
        //前年同月営業日補正の場合の表示       
        if ( zenDataShu.equals( NipoZennenDataShubetu.CODE_HOSEI ) ) {
            head5thList.add("前年比対象売上");
            head5thList.add("前年比対象前年売上");
            head5thList.add("前年比(売上)");
            head5thList.add("客数");   
            head5thList.add("前年比対象客数");
            head5thList.add("前年比対象前年客数");
            head5thList.add("前年比(客数)");
            head5thList.add("客単価");
            head5thList.add("前年比対象客単価");
            head5thList.add("前年比対象前年客単価");
            head5thList.add("前年比(客単価)");
        } else{
        	//前年同月営業日補正以外の場合の表示
	        head5thList.add("前年実績");
	        head5thList.add("前年比(売上)");
	        head5thList.add("客数");          
	        head5thList.add("前年客数");
	        head5thList.add("前年比(客数)");
	        head5thList.add("客単価");
	        head5thList.add("前年比(客単価)");
    	}
        
        headerList.add( head1stList );
        headerList.add( head2ndList );
        headerList.add( head3rdList );
        headerList.add( head4thList );
        headerList.add( head5thList );
        
        return headerList;
    }
    
    /**
     * csvoutputデータ作成
     */
    public List getOutputData(CsvOutputDto csvOutputDto) {

    	List sibuList = new ArrayList();
    	List viewList = new ArrayList();
    	Map paramMap = new HashMap();
    	
    	NipoRefConditionParameterDto dto = (NipoRefConditionParameterDto)csvOutputDto;   	
        
    	//集計区分の取得
    	String shukeiKbn = dto.getShukeiKbnCd();

    	//検索条件の取得
    	paramMap = getGyotaibetuNipoSearchLogic().getSearchData(dto);
		
		String zenDataShu = null;
	    if (TenpoShubetu.CODE_ZENNEN.equals(dto.getTenpoShubetuCd())) {
	    	zenDataShu = dto.getZenDataZennenCd();
	    } else {
	    	zenDataShu = dto.getZenDataZennenOthCd();
	    }
	    //支部別一覧の取得
		sibuList = getGyotaibetuNipoRefSibuLogic().execute(paramMap);
		//取得データの画面表示用の編集
   	    viewList = getGyotaibetuNipoRefSibuLogic().editViewDataList(sibuList,dto);
   	    
        List outputList = getHeaderData( csvOutputDto );
        
        if (viewList.size() != viewList.size() ) {
            throw new FtlSystemException("");
        }
        
        for ( int i = 0; i < viewList.size(); i++ ) {
            List lineList = new ArrayList();
            UISibuViewList sibuData = (UISibuViewList)viewList.get(i);
            
            if( dto.getSubTagKbn().equals("0") || dto.getSubTagKbn().equals("1")){
            
	            if ( sibuData.getTabNo() != 2 ) {

	            	// 屋号別売上情報をcsvoutputリストへの追加 
	            	outputList.add( setOutputList(sibuData,lineList,zenDataShu,0,shukeiKbn));
	            }
            }else if( dto.getSubTagKbn().equals("2") || dto.getSubTagKbn().equals("3") ){
	            
	            if ( sibuData.getTabNo() != 1 ) {

	            	// 詳細売上情報をcsvoutputリストへの追加 
	            	outputList.add( setOutputList(sibuData,lineList,zenDataShu,1,shukeiKbn));
	            }            
            }
        }
        
        return outputList;
    }
    
    /**
     * csvoutputリストへの追加
     */
    public List setOutputList(UISibuViewList sibuData,List lineList,String dataShu,int flg,String shukeiKbn) {
    	
    	if (!ShukeiKbn.IN_RC.equals(shukeiKbn)) {
    		//集計区分を含まない場合
		    if ( sibuData.getDispCode().equals(TaishoJoken.CODE_SIBU) ){
		    	lineList.add( sibuData.getJigyouCd() );
		    	lineList.add( sibuData.getJigyouName());
		    	lineList.add( sibuData.getSlareaCd() );
		    	lineList.add( sibuData.getSlareaName());
		    	lineList.add( sibuData.getSibuCd() );
		    	lineList.add( sibuData.getSibuName() );
		    }else if(sibuData.getDispCode().equals(TaishoJoken.CODE_SLAREA)){
		    	lineList.add( sibuData.getJigyouCd() );
		    	lineList.add( sibuData.getJigyouName());
		    	lineList.add( sibuData.getSlareaCd() );
		    	lineList.add( sibuData.getSlareaName() + GyotaibetuNipoConstants.MSG_TOTAL);
		    	lineList.add( "" );
		    	lineList.add( "" );
		    }else if(sibuData.getDispCode().equals(TaishoJoken.CODE_JIGYOU)){
		    	lineList.add( sibuData.getJigyouCd() );
		    	lineList.add( sibuData.getJigyouName() + GyotaibetuNipoConstants.MSG_TOTAL);
		    	lineList.add( "" );
		    	lineList.add( "" );
		    	lineList.add( "" );
		    	lineList.add( "" );
		    }else if(sibuData.getDispCode().equals(TaishoJoken.CODE_SEGMENT)){
		    	lineList.add( sibuData.getHonbuCd() );
		    	lineList.add( sibuData.getHonbuName() + GyotaibetuNipoConstants.MSG_TOTAL);
		    	lineList.add( "" );
		    	lineList.add( "" );
		    	lineList.add( "" );
		    	lineList.add( "" );
		    }else if(sibuData.getDispCode().equals(TaishoJoken.CODE_ALL)){
		    	lineList.add( "" );
		    	lineList.add( "総合計" );
		    	lineList.add( "" );
		    	lineList.add( "" );
		    	lineList.add( "" );
		    	lineList.add( "" );
		    }else{
		    	lineList.add( "" );
		    	lineList.add( "" );
		    	lineList.add( "" );
		    	lineList.add( "" );
		    	lineList.add( "" );
		    	lineList.add( "" );
		    }
    	}else{
    		//集計区分を含む場合
    		if ( sibuData.getDispCode().equals(TaishoJoken.CODE_SIBU) ){
		    	lineList.add( sibuData.getSibuName() );
    		}else if(sibuData.getDispCode().equals(TaishoJoken.CODE_ALL)){
		    	lineList.add( "総合計" );
		    }else{
		    	lineList.add( "" );
		    }
    	}
	    	
    	if( flg == 0){
    		lineList.add( sibuData.getYagoName() );
    	}else{
    		lineList.add( sibuData.getYagoDtlName() );
    	}
        
    	if ( dataShu.equals( NipoZennenDataShubetu.CODE_HOSEI ) ) {
    		lineList.add( String.valueOf(sibuData.getCount()) );//店舗数（同月データ）
    		lineList.add( String.valueOf(sibuData.getHoseiCount()) );//前年比対象店舗数（補正データ）
        }else{
        	lineList.add( String.valueOf(sibuData.getCount()) );//店舗数
        }
        
        lineList.add( String.valueOf( uriageFormat.format(sibuData.getUriage())) );
        lineList.add( String.valueOf(sibuData.getYosan()) );
        lineList.add( sibuData.getTaseiRitu() );
        
        if ( dataShu.equals( NipoZennenDataShubetu.CODE_HOSEI ) ){
        	
        	lineList.add( uriageFormat.format(sibuData.getZennenHiTaisyoUriage()) ); 		//前年比対象売上
        	lineList.add( uriageFormat.format(sibuData.getZennenHiTaisyoMaeUriage()) );		//前年比対象前年売上
        	lineList.add( sibuData.getZennenHiUriage() );	 	 		//前年比(売上)
        	lineList.add( String.valueOf(sibuData.getKyakusu()) );	 	//客数
        	lineList.add( sibuData.getZennenHiTaisyoKyakusu() );		//前年比対象客数
        	lineList.add( sibuData.getZennenHiTaisyoMaeKyakusu() );		//前年比対象前年客数
        	lineList.add( sibuData.getZennenHiKyakusu() );	  			//前年比(客数)
        	lineList.add( uriageFormat.format(sibuData.getKyakuTanka()) );				 	//客単価
        	lineList.add( uriageFormat.format(sibuData.getZennenHiTaisyoKyakuTanka()) );		//前年比対象客単価
        	lineList.add( uriageFormat.format(sibuData.getZennenHiTaisyoMaeKyakuTanka()) );	//前年比対象前年客単価
        	lineList.add( sibuData.getZennenHiKyakuTanka() );	 		//前年比(客単価)

        }else{         
        	
        	lineList.add( String.valueOf(uriageFormat.format(sibuData.getZennenUriage())) ); //前年実績
            lineList.add( String.valueOf(sibuData.getZennenHiUriage()) );	//前年比(売上)
            lineList.add( String.valueOf(sibuData.getKyakusu()) );	 	//客数
	        lineList.add( String.valueOf(sibuData.getZennenKyakusu()) );//前年客数
	        lineList.add( String.valueOf(sibuData.getZennenHiKyakusu()) );//前年比(客数)
	        lineList.add( sibuData.getKyakuTanka() );				 	//客単価
	        lineList.add( sibuData.getZennenHiKyakuTanka() );			 	//前年比(客単価)
        }
        
    	return lineList;
        
    }

    public void validate(CsvOutputDto csvOutputDto) {
        
    }

	public GyotaibetuNipoRefSibuLogic getGyotaibetuNipoRefSibuLogic() {
		return gyotaibetuNipoRefSibuLogic;
	}

	public void setGyotaibetuNipoRefSibuLogic(
			GyotaibetuNipoRefSibuLogic gyotaibetuNipoRefSibuLogic) {
		this.gyotaibetuNipoRefSibuLogic = gyotaibetuNipoRefSibuLogic;
	}

	public GyotaibetuNipoSearchLogic getGyotaibetuNipoSearchLogic() {
		return gyotaibetuNipoSearchLogic;
	}

	public void setGyotaibetuNipoSearchLogic(
			GyotaibetuNipoSearchLogic gyotaibetuNipoSearchLogic) {
		this.gyotaibetuNipoSearchLogic = gyotaibetuNipoSearchLogic;
	}
}
