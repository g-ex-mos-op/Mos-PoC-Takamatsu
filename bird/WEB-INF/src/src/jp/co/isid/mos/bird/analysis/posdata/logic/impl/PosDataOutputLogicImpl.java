package jp.co.isid.mos.bird.analysis.posdata.logic.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.analysis.posdata.logic.PosDataFormLogic;
import jp.co.isid.mos.bird.analysis.posdata.dto.PosDataFormDto;
import jp.co.isid.mos.bird.analysis.posdata.common.PosDataConstants;
import jp.co.isid.mos.bird.analysis.posdata.logic.PosDataOutputLogic;
import jp.co.isid.mos.bird.commonform.ownersearch.dto.OwnerSearchDto;
import jp.co.isid.mos.bird.analysis.common.dao.UIGroupTogoOwnerDao;
import jp.co.isid.mos.bird.analysis.common.entity.UIGroupTogoOwner;

import jp.co.isid.mos.bird.bizreport.common.code.UserType;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;

public class PosDataOutputLogicImpl implements PosDataOutputLogic {
    
    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BDT001L02";
    
	private PosDataFormLogic posDataFormLogic;
	
	private UIGroupTogoOwnerDao uiGroupTogoOwnerDao;
    
    /**
     * csvoutputデータ作成
     */
    public List execute(PosDataFormDto dto,OwnerSearchDto onerDto) {

    	List dataList = new ArrayList();
    	List outputList = new ArrayList();
    	Map paramMap = new HashMap();
    	
        // ユーザー関連情報
        BirdUserInfo birdUserInfo = dto.getBirdUserInfo();
        
        // パラメタ取得
        String userType = birdUserInfo.getMstUser().getUserTypeCd();		// ユーザタイプ
        String onerId = null;
        if ( userType.equals( UserType.HONBU ) ) {
        	// 本部ユーザの場合、検索内容のオーナーIDを設定する。
        	onerId = dto.getOnerId();										// オーナーID
        }else{
        	// オーナーの場合、ログインユーザのオーナーコードを取得する。	// ログインユーザID 
        	onerId = getPosDataFormLogic().getOnerId(birdUserInfo.getUserOner(),PosDataConstants.DEFORT_COMPANY_CD);
        }
        String dataDt = dto.getPosDataDt();									// 集信データ日
    	
    	//try{
    		
		// ユーザタイプの必須チェック
    	if (isNull(userType)) {
	    	NotNullException ut = new NotNullException(PosDataConstants.MSG_USER_TYPE);
	    	ut.setHtmlElementName("userType");
			throw ut;
    	}		
		// オーナーコードの必須チェック
	    if (isNull(onerId)) {
	    	NotNullException ne = new NotNullException(PosDataConstants.MSG_ONER_ID);
	    	ne.setHtmlElementName("onerCd");
			throw ne;
	    }
		// オーナーコードの文字列長チェック
	    if (!isNull(onerId)	&& onerId.getBytes().length > 5) {
	    	NotExistException ie = new NotExistException(PosDataConstants.MSG_ONER_ID);
	    	ie.setHtmlElementName("onerCd");
			throw ie;
		}
	    // オーナーコードのコード欄 半角数字
	    CodeVerifier codeVerifier = new CodeVerifier();
		if (!isNull(onerId)	&& !codeVerifier.validate(onerId)) {
			NotExistException ie = new NotExistException(PosDataConstants.MSG_ONER_ID);
	    	ie.setHtmlElementName("onerCd");
			throw ie;
		}
		// オーナコード存在チェック
		UIGroupTogoOwner uiGroupTogoOwner = uiGroupTogoOwnerDao.select(onerId);
		if (uiGroupTogoOwner == null) {
			NotExistException ie = new NotExistException(PosDataConstants.MSG_ONER_ID);
	    	ie.setHtmlElementName("onerCd");
			throw ie;
		}
		// データ集信日の必須チェック
    	if (isNull(dataDt)) {
	    	NotNullException dt = new NotNullException(PosDataConstants.MSG_DATA_DT);
	    	dt.setHtmlElementName("dataDt");
			throw dt;
    	}	
	
    	//検索条件の設定
	   	paramMap.put( PosDataConstants.MAP_ONER_ID,onerId );
	   	paramMap.put( PosDataConstants.MAP_USER_TYPE,userType );
	   	paramMap.put( PosDataConstants.MAP_DATA_DT,dataDt );
	   	paramMap.put( PosDataConstants.MAP_COMPANY_CD,PosDataConstants.DEFORT_COMPANY_CD );
    	    	
		//ＰＯＳ集信データの取得
		dataList = getPosDataFormLogic().execute(paramMap);
	    //ダウンロードリストにDATA内容を追加する。     
		outputList = getPosDataFormLogic().addDawnloadList( dataList );
			
	    // オーナーユーザの保存を行う。
	    if ( userType.equals( UserType.HONBU ) ) {
	    	dto.setSearchOnerId(dto.getOnerId());
	    }
	    // 集信データ日の保存を行う。
	    dto.setSearchPosDataDt(dto.getPosDataDt());
		
	    /*} catch (NoResultException e){
	    	
	    	// オーナーユーザの復帰を行う。
	    	if ( userType.equals( UserType.HONBU ) ) {
	    		dto.setOnerId(dto.getSearchOnerId());
	    		onerDto.setOnerCd(dto.getOnerId());
	        }
	    	// 集信データ日の復帰を行う。
	    	dto.setPosDataDt(dto.getSearchPosDataDt());
	    	
	   	   	throw e;
	   	   	
	    } catch (InvalidInputException e){
	    	
	    	// オーナーユーザの復帰を行う。
	    	if ( userType.equals( UserType.HONBU ) ) {
	    		dto.setOnerId(dto.getSearchOnerId());
	    		onerDto.setOnerCd(dto.getOnerId());
	        }
	    	// 集信データ日の復帰を行う。
	    	dto.setPosDataDt(dto.getSearchPosDataDt());
	    	
	   	   	throw e;
	   	   	
	   	} catch (Exception e) {
	   		e.printStackTrace();
	   		throw new FtlSystemException("");
	   	}*/
		
        return outputList;
    }

	public PosDataFormLogic getPosDataFormLogic() {
		return posDataFormLogic;
	}

	public void setPosDataFormLogic(PosDataFormLogic posDataFormLogic) {
		this.posDataFormLogic = posDataFormLogic;
	}
	
    /**
	 * nullチェック
	 */
    private boolean isNull(String val) {
        if (val == null) {
            return true;
        }
        if ("".equals(val.trim())) {
            return true;
        }
        return false;
    }

	public UIGroupTogoOwnerDao getUiGroupTogoOwnerDao() {
		return uiGroupTogoOwnerDao;
	}

	public void setUiGroupTogoOwnerDao(UIGroupTogoOwnerDao uiGroupTogoOwnerDao) {
		this.uiGroupTogoOwnerDao = uiGroupTogoOwnerDao;
	}
}
