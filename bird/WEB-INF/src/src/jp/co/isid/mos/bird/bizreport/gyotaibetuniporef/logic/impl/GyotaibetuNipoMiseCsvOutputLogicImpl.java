package jp.co.isid.mos.bird.bizreport.gyotaibetuniporef.logic.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizreport.common.code.NipoZennenDataShubetu;
import jp.co.isid.mos.bird.bizreport.common.code.ShukeiKbn;
import jp.co.isid.mos.bird.bizreport.common.code.TaishoKikan;
import jp.co.isid.mos.bird.bizreport.common.code.TaishoTenpo;
import jp.co.isid.mos.bird.bizreport.common.code.TenpoShubetu;
import jp.co.isid.mos.bird.bizreport.common.code.UserType;
import jp.co.isid.mos.bird.bizreport.common.dto.NipoRefConditionParameterDto;
import jp.co.isid.mos.bird.bizreport.common.util.NipoRefUtil;
import jp.co.isid.mos.bird.bizreport.gyotaibetuniporef.common.GyotaibetuNipoCommon;
import jp.co.isid.mos.bird.bizreport.gyotaibetuniporef.common.GyotaibetuNipoConstants;
import jp.co.isid.mos.bird.bizreport.gyotaibetuniporef.entity.UIMiseListInfo;
import jp.co.isid.mos.bird.common.code.TaishoJoken;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;
import jp.co.isid.mos.bird.framework.util.formatter.NumericFormatter;

public class GyotaibetuNipoMiseCsvOutputLogicImpl implements CsvOutputLogic {
    public static final String LOGIC_ID = "BBR005L04";
    
    private GyotaibetuNipoRefMiseLogicImpl gyotaibetuNipoRefMiseLogic;
    private GyotaibetuNipoRefOnerLogicImpl gyotaibetuNipoRefOnerLogic;
    private GyotaibetuNipoSearchLogicImpl gyotaibetuNipoSearchLogic;
	// 数値タイプの文字列フォーマッタ(定数)
	NumericFormatter uriageFormat = new NumericFormatter(false, 0, true);
    
    private final String FILE_NAME = "TENPO.csv";
    
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
    	
		// ヘッダー部情報リスト
		List headerList = new ArrayList();
		
		// 屋号別売上日報条件部DTO取得
		NipoRefConditionParameterDto dto = (NipoRefConditionParameterDto) csvOutputDto;

    	String userType = dto.getBirdUserInfo().getMstUser().getUserTypeCd();
    	String[] linkParams = GyotaibetuNipoCommon.getLinkParams(dto.getLinkParams());
    	if(CommonUtil.isNull(linkParams[2])) {
    		linkParams[2] = TaishoJoken.CODE_SIBU;
    	}
        // リンク区分
        String linkKbn = linkParams[2];

		// 前年データ種別
		String zenDataShu = null;
    	//集計区分の取得
    	String shukeiKbn = dto.getShukeiKbnCd();
		
        // ヘッダ部作成：オーナーユーザの場合
        if (UserType.ONER.equals(userType)) {
        	
            // 前年データ種別取得
            zenDataShu = dto.getZenDataZennenOthCd();
            
			// １行目：会社
			List head1stList = new ArrayList();
			head1stList.add(GyotaibetuNipoConstants.CSV_HD_COMPANY);			
			head1stList.add(dto.getCompanyName());
						
			// ２行目：前年データ種別
			List head2ndList = new ArrayList();
			head2ndList.add(GyotaibetuNipoConstants.CSV_HD_ZEN_DATA_SHU);
			head2ndList.add(NipoZennenDataShubetu.getName(zenDataShu,dto.getBirdUserInfo().getMstUser().getUserTypeCd()));
			
            // ３行目：対象期間
            List head3rdList = new ArrayList();
            head3rdList.add(GyotaibetuNipoConstants.CSV_HD_TAISHO_KIKAN);
            head3rdList.add(NipoRefUtil.getCsvTaishoKikan(dto));
            
			headerList.add(head1stList);
			headerList.add(head2ndList);
			headerList.add(head3rdList);
			
        }else{
        	
			// 前年データ種別取得
			zenDataShu = TenpoShubetu.CODE_ZENNEN.equals(dto.getTenpoShubetuCd())
				? dto.getZenDataZennenCd() : dto.getZenDataZennenOthCd();
				
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
	        
			// ３行目:集計区分、前年データ種別
			List head3rdList = new ArrayList();
			head3rdList.add(GyotaibetuNipoConstants.CSV_HD_SHUKEI_KBN);
            /* 2008/12/09追加 SV指定の場合 */
            if(ShukeiKbn.SV_CD.equals(shukeiKbn)){
                head3rdList.add(GyotaibetuNipoConstants.CSV_HD_SV);
            }else{
                head3rdList.add(ShukeiKbn.getName(dto.getShukeiKbnCd()));        
            }

			head3rdList.add(GyotaibetuNipoConstants.EMPTY);
			head3rdList.add(GyotaibetuNipoConstants.CSV_HD_ZEN_DATA_SHU);
			head3rdList.add(NipoZennenDataShubetu.getName
	            (zenDataShu, dto.getBirdUserInfo().getMstUser().getUserTypeCd()));
			head3rdList.add(GyotaibetuNipoConstants.EMPTY);
			
			StringBuffer linkInfo = new StringBuffer();
			
	        // リンク区分
	        String yagoName = linkParams[3];
            if (GyotaibetuNipoConstants.sibu.equals(linkKbn)) {
                linkInfo.append(GyotaibetuNipoConstants.HD_SIBU);
                linkInfo.append(GyotaibetuNipoCommon.setEmpty(dto.getSibuName()));
                linkInfo.append(GyotaibetuNipoConstants.SPACE);
                linkInfo.append(GyotaibetuNipoCommon.setEmpty(yagoName));
                
            } else if (GyotaibetuNipoConstants.area.equals(linkKbn)) {
                linkInfo.append(GyotaibetuNipoConstants.HD_SLAREA);
                linkInfo.append(GyotaibetuNipoCommon.setEmpty(dto.getSibuName()+GyotaibetuNipoConstants.MSG_TOTAL));
                linkInfo.append(GyotaibetuNipoConstants.SPACE);
                linkInfo.append(GyotaibetuNipoCommon.setEmpty(yagoName));
                
            } else if (GyotaibetuNipoConstants.jigyo.equals(linkKbn) ) {
                linkInfo.append(GyotaibetuNipoConstants.HD_JIGYOU);
                linkInfo.append(GyotaibetuNipoCommon.setEmpty(dto.getSibuName()+GyotaibetuNipoConstants.MSG_TOTAL));
                linkInfo.append(GyotaibetuNipoConstants.SPACE);
                linkInfo.append(GyotaibetuNipoCommon.setEmpty(yagoName));
                
            } else if (GyotaibetuNipoConstants.toukatu.equals(linkKbn) ) {
                linkInfo.append(GyotaibetuNipoConstants.HD_HONBU);
                linkInfo.append(GyotaibetuNipoCommon.setEmpty(dto.getSibuName()+GyotaibetuNipoConstants.MSG_TOTAL));
                linkInfo.append(GyotaibetuNipoConstants.SPACE);
                linkInfo.append(GyotaibetuNipoCommon.setEmpty(yagoName));
                
            } else if (GyotaibetuNipoConstants.total.equals(linkKbn)) {
                linkInfo.append(GyotaibetuNipoConstants.HD_ALL);
                linkInfo.append(GyotaibetuNipoConstants.SPACE);
                linkInfo.append(GyotaibetuNipoCommon.setEmpty(yagoName));
            }else{
                /* 2008/12/09追加
                 * SV指定の場合、SVコード、SV名称を表示 */
                if(ShukeiKbn.SV_CD.equals(shukeiKbn)){
                    linkInfo.append(GyotaibetuNipoConstants.HD_SV);
                    linkInfo.append(GyotaibetuNipoCommon.setEmpty(dto.getHyojiSvName()));                    
                }else{
                    linkInfo.append(GyotaibetuNipoConstants.HD_SIBU);
                    linkInfo.append(GyotaibetuNipoCommon.setEmpty(dto.getSibuName()));                  
                }

            }
            
            head3rdList.add(linkInfo.toString());
            
			headerList.add(head1stList);
			headerList.add(head2ndList);
			headerList.add(head3rdList);
        }
        		
		// ４行目
		List head4thList = new ArrayList();
               
        // ５行目
        List head5thList = new ArrayList();

        if (UserType.HONBU.equals(userType)) {
	        //支部一覧画面(支部リンク以外)のダウンロードの場合
	        if (!GyotaibetuNipoConstants.sibu.equals(linkKbn)) {
	        	//集計区分を含まない場合
	        	if (!ShukeiKbn.IN_RC.equals(shukeiKbn)) {
		        	head5thList.add("支部ｺｰﾄﾞ");
		            head5thList.add("支部名称");
	        	}else{
		            head5thList.add("集計区分");
	        	}
	        }
	        //集計区分を含まない場合
	        if (!ShukeiKbn.IN_RC.equals(shukeiKbn)) {
                /* 2008/12/09追加
                 * SV指定の場合、支部コード、支部名称を1列目と2列目に表示。*/
                if(ShukeiKbn.SV_CD.equals(shukeiKbn)){
                    head5thList.add("支部ｺｰﾄﾞ");
                    head5thList.add("支部名称");                          
                }
	        	head5thList.add("ﾌﾞﾛｯｸｺｰﾄﾞ");
		        head5thList.add("ﾌﾞﾛｯｸ名称");
	        }
        }
        head5thList.add("店ｺｰﾄﾞ");
        head5thList.add("店名称");    	
        head5thList.add("屋号");
        head5thList.add("店種");
        if ( dto.getTaishoKikanCd().equals( TaishoKikan.DAY1 )) {
        	head5thList.add("天候");
        }else{
        	head5thList.add("営業日数");
        }
        head5thList.add("売上");
        head5thList.add("予算");
        head5thList.add("達成率");
        
        if (UserType.HONBU.equals(userType)) {
        	
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
		        head5thList.add("前年客単価");
		        head5thList.add("前年比(客単価)");
	    	}
	        
        }else if (UserType.ONER.equals(userType)) {
	        head5thList.add("前年実績");
	        head5thList.add("前年比(売上)");
	        head5thList.add("客数");          
	        head5thList.add("前年客数");
	        head5thList.add("前年比(客数)");
	        head5thList.add("客単価");
	        head5thList.add("前年客単価");
	        head5thList.add("前年比(客単価)");
        }
        
        if ( dto.getTaishoKikanCd().equals( TaishoKikan.DAY1 )) {
        	head5thList.add("前年天候");
        }else{
        	head5thList.add("前年営業日数");
        }
        
        headerList.add( head4thList );
        headerList.add( head5thList );
        
        return headerList;
    }
    
    /**
     * csvoutputデータ作成
     */
    public List getOutputData(CsvOutputDto csvOutputDto) {

    	List miseList = new ArrayList();
    	List viewList = new ArrayList();
    	Map paramMap = new HashMap();
    	
    	NipoRefConditionParameterDto dto = (NipoRefConditionParameterDto)csvOutputDto;
        
		String userType = dto.getBirdUserInfo().getMstUser().getUserTypeCd();
		if (UserType.ONER.equals(userType)) {
			dto.setTenpoShubetuCd(TenpoShubetu.CODE_ALL);
		}
		
    	String shukeiKbn = dto.getShukeiKbnCd();
    	String[] linkParams = GyotaibetuNipoCommon.getLinkParams(dto.getLinkParams());
    	if(CommonUtil.isNull(linkParams[2])) {
    		linkParams[2] = TaishoJoken.CODE_SIBU;
    	}
    	//検索条件の取得
    	paramMap = getGyotaibetuNipoSearchLogic().getSearchData( dto );  	
    	paramMap.put( GyotaibetuNipoConstants.SIBU_CD,dto.getSibuCd() );
    	paramMap.put( GyotaibetuNipoConstants.YAGO_CD,linkParams[0]);
    	paramMap.put( GyotaibetuNipoConstants.YAGO_TYPE,linkParams[1]);
    	paramMap.put( GyotaibetuNipoConstants.KUBUN,linkParams[2]);
	            
        //前年データ種別の判別
		String zenDataShu = null;
		if (UserType.HONBU.equals(userType)) {
			if (TenpoShubetu.CODE_ZENNEN.equals(dto.getTenpoShubetuCd())) {
				zenDataShu = dto.getZenDataZennenCd();
			} else {
				zenDataShu = dto.getZenDataZennenOthCd();
			}
	    }else if (UserType.ONER.equals(userType)) {
			zenDataShu = dto.getZenDataZennenOthCd();
	    }
	    

		if (UserType.HONBU.equals(userType)) {
            //店舗別一覧の取得
            miseList = getGyotaibetuNipoRefMiseLogic().execute(paramMap);
            
    	    //取得データの画面表示用の編集
	   	    viewList = getGyotaibetuNipoRefMiseLogic().editViewDataList(miseList,dto);
		}else{
            //店舗別一覧の取得
            miseList = getGyotaibetuNipoRefOnerLogic().execute(paramMap);
            
			//オーナーの場合
    	    //取得データの画面表示用の編集
	   	    viewList = getGyotaibetuNipoRefOnerLogic().editViewDataList(miseList);
		}
    	

        List outputList = getHeaderData( csvOutputDto );
            
        String kikan = dto.getTaishoKikanCd();

        // リンク区分
        String linkKbn = linkParams[2];

        if (viewList.size() != viewList.size() ) {
            throw new FtlSystemException("");
        }
        
        for ( int i = 0; i < viewList.size(); i++ ) {
            List lineList = new ArrayList();
            UIMiseListInfo miseData = (UIMiseListInfo)viewList.get(i);

	        // 屋号別売上情報をcsvoutputリストへの追加 
	        outputList.add( setOutputList(miseData,lineList,zenDataShu,kikan,0,linkKbn,userType,shukeiKbn));
        }
		
        return outputList;
    }
    
    /**
     * csvoutputリストへの追加
     * 
     * @param miseData
     * @param lineList
     * @param dataShu
     * @param kikan
     * @param flg
     * @param linkKbn
     * @param userType
     * @param shukeiKbn
     * @return
     */
    public List setOutputList(UIMiseListInfo miseData,List lineList,String dataShu,String kikan,int flg,String linkKbn,String userType,String shukeiKbn) {
    	
        //支部一覧画面(支部リンク以外)のダウンロードの場合
        if (!GyotaibetuNipoConstants.sibu.equals(linkKbn)) {
    		//集計区分を含まない場合
    		if (!ShukeiKbn.IN_RC.equals(shukeiKbn)) {
	    		//支部一覧でリンクボタンを押した場合
	        	if ( miseData.getDispCode().equals(TaishoJoken.CODE_MISE) ){
	    	    	lineList.add( GyotaibetuNipoCommon.setEmpty(miseData.getSibuCd()) );
	    	    	lineList.add( GyotaibetuNipoCommon.setEmpty(miseData.getSibuName()) );
	    	    	lineList.add( GyotaibetuNipoCommon.setEmpty(miseData.getBlockCd()) );
	    	    	lineList.add( GyotaibetuNipoCommon.setEmpty(miseData.getBlockName()) );
	    	    	lineList.add( GyotaibetuNipoCommon.setEmpty(miseData.getMiseCd()) );
	    	    	lineList.add( GyotaibetuNipoCommon.setEmpty(miseData.getMiseNameKj()) );
	    	    	lineList.add( GyotaibetuNipoCommon.setEmpty(miseData.getYagoDtlName()) );
	        	}else if( miseData.getDispCode().equals(GyotaibetuNipoConstants.block) ){
	    	    	lineList.add( GyotaibetuNipoCommon.setEmpty(miseData.getSibuCd()) );
	    	    	lineList.add( GyotaibetuNipoCommon.setEmpty(miseData.getSibuName()) );
	    	    	lineList.add( GyotaibetuNipoCommon.setEmpty(miseData.getBlockCd()) );
	    	    	lineList.add( GyotaibetuNipoCommon.setEmpty(miseData.getBlockName()) );
	    	    	lineList.add( "" );
	    	    	lineList.add( "" );
	    	    	lineList.add( GyotaibetuNipoCommon.setEmpty(miseData.getYagoDtlName()) );
	    	    }else if(miseData.getDispCode().equals(TaishoJoken.CODE_ALL)){
	    	    	lineList.add( GyotaibetuNipoCommon.setEmpty(miseData.getSibuCd()) );
	    	    	lineList.add( GyotaibetuNipoCommon.setEmpty(miseData.getSibuName()) + GyotaibetuNipoConstants.MSG_TOTAL);
	    	    	lineList.add( "" );
	    	    	lineList.add( "" );
	    	    	lineList.add( "" );
	    	    	lineList.add( "" );
	    	    	lineList.add( GyotaibetuNipoCommon.setEmpty(miseData.getYagoDtlName()) );
	    	    }else{
	    	    	lineList.add( "" );
	    	    	lineList.add( "" );
	    	    	lineList.add( "" );
	    	    	lineList.add( "" );
	    	    	lineList.add( "" );
	    	    	lineList.add( "" );
	    	    	lineList.add( "" );
	    	    }
    		}else{
    			//集計区分を含む場合
    			if ( miseData.getDispCode().equals(TaishoJoken.CODE_MISE) ){
    				lineList.add( GyotaibetuNipoCommon.setEmpty(miseData.getSibuName()) );
	    	    	lineList.add( GyotaibetuNipoCommon.setEmpty(miseData.getMiseCd()) );
	    	    	lineList.add( GyotaibetuNipoCommon.setEmpty(miseData.getMiseNameKj()) );
	    	    	lineList.add( GyotaibetuNipoCommon.setEmpty(miseData.getYagoDtlName()) );
    			}else{
    				lineList.add( "" );
    			}
    		}
    	}else{
    		//店舗一覧でダウンロードボタンを押した場合
	    	if ( miseData.getDispCode().equals(TaishoJoken.CODE_MISE) ){
	    		if (UserType.HONBU.equals(userType)) {
	    			//集計区分を含まない場合
	    			if (!ShukeiKbn.IN_RC.equals(shukeiKbn)) {
                        
                        //SV指定の場合、支部コード、支部名称を1列目と2列目に表示。2008/12/09追加
                        if(ShukeiKbn.SV_CD.equals(shukeiKbn)){
                            lineList.add( GyotaibetuNipoCommon.setEmpty(miseData.getSibuCd()) );
                            lineList.add( GyotaibetuNipoCommon.setEmpty(miseData.getSibuName()) );                           
                        }
				    	lineList.add( GyotaibetuNipoCommon.setEmpty(miseData.getBlockCd()) );
				    	lineList.add( GyotaibetuNipoCommon.setEmpty(miseData.getBlockName()) );
	    			}
	    		}
	    		lineList.add( miseData.getMiseCd() );
		    	lineList.add( GyotaibetuNipoCommon.setEmpty(miseData.getMiseNameKj()) );
	    		lineList.add( GyotaibetuNipoCommon.setEmpty(miseData.getYagoDtlName()) );
	    	}else if( miseData.getDispCode().equals(GyotaibetuNipoConstants.block) ){
                
                //SV指定の場合、支部コード、支部名称を1列目と2列目に表示。2008/12/09追加
                if(ShukeiKbn.SV_CD.equals(shukeiKbn)){
                    lineList.add( GyotaibetuNipoCommon.setEmpty(miseData.getSibuCd()) );
                    lineList.add( GyotaibetuNipoCommon.setEmpty(miseData.getSibuName()) );                           
                }
                
	    		lineList.add( GyotaibetuNipoCommon.setEmpty(miseData.getBlockCd()) );
		    	lineList.add( GyotaibetuNipoCommon.setEmpty(miseData.getBlockName()));
		    	lineList.add( "" );
		    	lineList.add( "" );
		    	lineList.add( GyotaibetuNipoCommon.setEmpty(miseData.getYagoDtlName()) );
		    }else if(miseData.getDispCode().equals(TaishoJoken.CODE_ALL)){
		    	if (UserType.HONBU.equals(userType)) {
                    //SV指定の場合、支部コード、支部名称を1列目と2列目に表示。2008/12/09追加
                    if(ShukeiKbn.SV_CD.equals(shukeiKbn)){
                        lineList.add( GyotaibetuNipoCommon.setEmpty(miseData.getSibuCd()) );
                        lineList.add( GyotaibetuNipoCommon.setEmpty(miseData.getSibuName()) );                           
                    }
			    	lineList.add( "" );
			    	lineList.add( "支部合計" );
		    	  	lineList.add( "" );
			    	lineList.add( "" );
			    	lineList.add( GyotaibetuNipoCommon.setEmpty(miseData.getYagoDtlName()) );
		    	}else{
		    		lineList.add( "" );
		    		lineList.add( "総合計" );
		    		lineList.add( GyotaibetuNipoCommon.setEmpty(miseData.getYagoDtlName()) + GyotaibetuNipoConstants.MSG_TOTAL );		
		    	}
		    }else if(miseData.getDispCode().equals("SV_TOTAL")){
                lineList.add( "" );
                lineList.add( "" );
                lineList.add( "" );
                lineList.add( "総合計" );
                lineList.add( "" );
                lineList.add( "" );
                lineList.add( GyotaibetuNipoCommon.setEmpty(miseData.getYagoDtlName()) );
            }else {
		    	lineList.add( "" );
		    	lineList.add( "" );
		    	lineList.add( "" );
		    	lineList.add( "" );
		    	lineList.add( "" );
		    }
    	}
	    	
   		lineList.add( GyotaibetuNipoCommon.setEmpty(miseData.getTenpoShubetuName()) );
   		if ( kikan.equals( TaishoKikan.DAY1 )) {
   			lineList.add( GyotaibetuNipoCommon.setEmpty(miseData.getTenkoKbnName()) );
   		}else{
   			lineList.add( GyotaibetuNipoCommon.setEmpty(miseData.getEigyoDays()) );
   		}
        lineList.add( String.valueOf(uriageFormat.format(miseData.getUriage())) );
        lineList.add( String.valueOf(miseData.getYosan()) );
        lineList.add( miseData.getTaseiRitu() );
        
        if ( dataShu.equals( NipoZennenDataShubetu.CODE_HOSEI ) ){
        	
        	lineList.add( uriageFormat.format(miseData.getZennenHiTaisyoUriage()) ); 		//前年比対象売上
        	lineList.add( uriageFormat.format(miseData.getZennenHiTaisyoMaeUriage()) );		//前年比対象前年売上
        	lineList.add( miseData.getZennenHiUriage() );	 	 		//前年比(売上)
        	lineList.add( String.valueOf(miseData.getKyakusu()) );	 	//客数
        	lineList.add( miseData.getZennenHiTaisyoKyakusu() );		//前年比対象客数
        	lineList.add( miseData.getZennenHiTaisyoMaeKyakusu() );		//前年比対象前年客数
        	lineList.add( miseData.getZennenHiKyakusu() );	  			//前年比(客数)
        	lineList.add( uriageFormat.format(miseData.getKyakuTanka()) );				 	//客単価
        	lineList.add( uriageFormat.format(miseData.getZennenHiTaisyoKyakuTanka()) );		//前年比対象客単価
        	lineList.add( uriageFormat.format(miseData.getZennenHiTaisyoMaeKyakuTanka()) );	//前年比対象前年客単価
        	lineList.add( miseData.getZennenHiKyakuTanka() );	 		//前年比(客単価)

        }else{         
        	
        	lineList.add( String.valueOf(uriageFormat.format(miseData.getZennenUriage())) ); 	//前年実績
            lineList.add( String.valueOf(miseData.getZennenHiUriage()) );	//前年比(売上)
            lineList.add( String.valueOf(miseData.getKyakusu()) );	 		//客数
	        lineList.add( String.valueOf(miseData.getZennenKyakusu()) );	//前年客数
	        lineList.add( String.valueOf(miseData.getZennenHiKyakusu()) );	//前年比(客数)
	        lineList.add( uriageFormat.format(miseData.getKyakuTanka()) );				 		//客単価
	        lineList.add( uriageFormat.format(miseData.getZennenKyakuTanka()) );					//前年客単価
	        lineList.add( miseData.getZennenHiKyakuTanka() );				//前年比(客単価)
        }
        
        if ( kikan.equals( TaishoKikan.DAY1 )) {
        	lineList.add( GyotaibetuNipoCommon.setEmpty(miseData.getTenkoKbnZenName()) );		//前年天候
        }else{
        	lineList.add( GyotaibetuNipoCommon.setEmpty(miseData.getZennenEigyoDays()) );		//営業日数
        }
        
    	return lineList;
        
    }

    public void validate(CsvOutputDto csvOutputDto) {
        
    }
     
	public GyotaibetuNipoRefMiseLogicImpl getGyotaibetuNipoRefMiseLogic() {
		return gyotaibetuNipoRefMiseLogic;
	}

	public void setGyotaibetuNipoRefMiseLogic(
			GyotaibetuNipoRefMiseLogicImpl gyotaibetuNipoRefMiseLogic) {
		this.gyotaibetuNipoRefMiseLogic = gyotaibetuNipoRefMiseLogic;
	}

	public GyotaibetuNipoSearchLogicImpl getGyotaibetuNipoSearchLogic() {
		return gyotaibetuNipoSearchLogic;
	}

	public void setGyotaibetuNipoSearchLogic(
			GyotaibetuNipoSearchLogicImpl gyotaibetuNipoSearchLogic) {
		this.gyotaibetuNipoSearchLogic = gyotaibetuNipoSearchLogic;
	}

	public GyotaibetuNipoRefOnerLogicImpl getGyotaibetuNipoRefOnerLogic() {
		return gyotaibetuNipoRefOnerLogic;
	}

	public void setGyotaibetuNipoRefOnerLogic(
			GyotaibetuNipoRefOnerLogicImpl gyotaibetuNipoRefOnerLogic) {
		this.gyotaibetuNipoRefOnerLogic = gyotaibetuNipoRefOnerLogic;
	}
}
