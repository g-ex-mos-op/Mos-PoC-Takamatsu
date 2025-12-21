/**
 * 
 */
package jp.co.isid.mos.bird.analysis.posdata.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.analysis.common.dao.UIGroupTogoOwnerDao;
import jp.co.isid.mos.bird.analysis.common.entity.UIGroupTogoOwner;
import jp.co.isid.mos.bird.analysis.posdata.common.PosDataConstants;
import jp.co.isid.mos.bird.analysis.posdata.dao.UIZipOnerSibuDao;
import jp.co.isid.mos.bird.analysis.posdata.dto.PosDataFormDto;
import jp.co.isid.mos.bird.analysis.posdata.logic.ZipOnerCheckLogic;
import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.entity.UIUserOner;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.CannotAccessException;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;

/**
 * LOGIC【Zip対象オーナーチェック】
 * 作成日:2013/03/15
 * @author xkinu
 *
 */
public class ZipOnerCheckLogicImpl implements ZipOnerCheckLogic {
    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BDT001L04";
    //DAO【分割オーナー支部】
    private UIZipOnerSibuDao posDataUIZipOnerSibuDao;
	//DAO【オーナ情報】
	private UIGroupTogoOwnerDao uiGroupTogoOwnerDao;

	
    /**
     * 事前条件処理
     * @see jp.co.isid.mos.bird.framework.logic.DownloadLogic#validate(jp.co.isid.mos.bird.framework.dto.DownloadDto)
     * 
     * @param sessionDto DTO【POSデータ集信Sesshon情報】
     */
    public void validate(PosDataFormDto sessionDto) throws ApplicationException {
    	if(sessionDto == null) {
    		throw new MissingDataException("POSデータ集信DTO");
    	}
    	if(sessionDto.getBirdUserInfo() == null) {
    		throw new MissingDataException("ユーザー情報");
    	}
    	
    	String userTypeCd = sessionDto.getBirdUserInfo().getMstUser().getUserTypeCd();
        String onerCd = null;
        if ( UserType.isHonbu(userTypeCd)) {
        	// 本部ユーザの場合、検索内容のオーナーIDを設定する。
        	onerCd = sessionDto.getOnerId();										// オーナーID
        	if (CommonUtil.isNull(onerCd)) {
        		throw new NotNullException(PosDataConstants.MSG_ONER_ID);
        	}
    	    // オーナーコードのコード欄 半角数字
    	    CodeVerifier codeVerifier = new CodeVerifier();
    		if (onerCd.getBytes().length > 5 || !codeVerifier.validate(onerCd)) {
    			NotExistException ie = new NotExistException(PosDataConstants.MSG_ONER_ID);
    	    	ie.setHtmlElementName("onerCd");
    			throw ie;
    		}
    		// オーナコード存在チェック
    		UIGroupTogoOwner uiGroupTogoOwner = uiGroupTogoOwnerDao.select(onerCd);
    		if (uiGroupTogoOwner == null) {
    			NotExistException ie = new NotExistException(PosDataConstants.MSG_ONER_ID);
    	    	ie.setHtmlElementName("onerCd");
    			throw ie;
    		}
        }else if (UserType.isOner(userTypeCd)){
        	// オーナーの場合、ログインユーザのオーナーコードを取得する。	// ログインユーザID 
        	onerCd = getOnerId(sessionDto.getBirdUserInfo().getUserOner(), CommonUtil.COMPANY_CD_MOS);
        	sessionDto.setOnerId(onerCd);
        }
        else {
	    	// ユーザータイプが本部又はオーナーでない場合、権限エラー
	    	throw new CannotAccessException();
        }
    }
	/*
	 * 実行処理
	 * @see jp.co.isid.mos.bird.analysis.posdata.logic.ZipOnerCheckLogic#execute(jp.co.isid.mos.bird.analysis.posdata.dto.PosDataFormDto)
	 */
	public boolean execute(PosDataFormDto sessionDto) {
    	//事前条件処理
    	validate(sessionDto);
		//１．DAO【分割オーナー支部】.検索を実行し、戻り値List[[分割オーナー支部]]を取得します。
    	List listSibu = getPosDataUIZipOnerSibuDao().select(CommonUtil.COMPANY_CD_MOS, sessionDto.getOnerId());
    	//２．処理１のList[[分割オーナー支部]]の件数が０件の場合は、falseをリターンします。
		return !listSibu.isEmpty();
	}
    /**
     * オーナーコードの取得
     */
    private String getOnerId(List onerList,String companyCd){
        boolean onerExistFlg = false;

         UIUserOner oner = new UIUserOner();
         
         for ( int i = 0; i < onerList.size(); i++ ) {
             oner = (UIUserOner)onerList.get(i);
             if ( companyCd.equals( oner.getCompanyCd() ) ) {
                 onerExistFlg = true;
                 break;
             }
         }
         if ( !onerExistFlg ) {
             throw new NotExistException("オーナー");
         }
         
         return oner.getOnerCd();
    }

	/**
	 * @return クラス変数posDataUIZipOnerSibuDao を戻します。
	 */
	public UIZipOnerSibuDao getPosDataUIZipOnerSibuDao() {
		return posDataUIZipOnerSibuDao;
	}
	/**
	 * @param posDataUIZipOnerSibuDao を クラス変数posDataUIZipOnerSibuDaoへ設定します。
	 */
	public void setPosDataUIZipOnerSibuDao(UIZipOnerSibuDao posDataUIZipOnerSibuDao) {
		this.posDataUIZipOnerSibuDao = posDataUIZipOnerSibuDao;
	}
	/**
	 * @return クラス変数uiGroupTogoOwnerDao を戻します。
	 */
	public UIGroupTogoOwnerDao getUiGroupTogoOwnerDao() {
		return uiGroupTogoOwnerDao;
	}
	/**
	 * @param uiGroupTogoOwnerDao を クラス変数uiGroupTogoOwnerDaoへ設定します。
	 */
	public void setUiGroupTogoOwnerDao(UIGroupTogoOwnerDao uiGroupTogoOwnerDao) {
		this.uiGroupTogoOwnerDao = uiGroupTogoOwnerDao;
	}
}