package jp.co.isid.mos.bird.analysis.posdata.logic.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.analysis.common.dao.UIGroupTogoOwnerDao;
import jp.co.isid.mos.bird.analysis.common.entity.UIGroupTogoOwner;
import jp.co.isid.mos.bird.analysis.posdata.common.PosDataConstants;
import jp.co.isid.mos.bird.analysis.posdata.dao.UIZipOnerSibuDao;
import jp.co.isid.mos.bird.analysis.posdata.dto.PosDataFormDto;
import jp.co.isid.mos.bird.analysis.posdata.entity.UIOnerSibu;
import jp.co.isid.mos.bird.analysis.posdata.entity.UIPosDataInfo;
import jp.co.isid.mos.bird.analysis.posdata.logic.PosDataFormLogic;
import jp.co.isid.mos.bird.analysis.posdata.logic.ZipOutputLogic;
import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.common.entity.UILists;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.DownloadDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.CannotAccessException;
import jp.co.isid.mos.bird.framework.exception.MissingConfigException;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;

/**
 * LOGIC【Zipファイル取得】
 * 
 * 作成日:2013/02/26
 * @author xkinu
 *
 */
public class ZipOutputLogicImpl implements ZipOutputLogic {
    
    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BDT001L05";
    //DAO【分割オーナー支部】
    private UIZipOnerSibuDao posDataUIOnerSibuDao;
    //LOGIC【POS集信データ取得加工】
	private PosDataFormLogic posDataFormLogic;
	//DAO【オーナ情報】
	private UIGroupTogoOwnerDao uiGroupTogoOwnerDao;
    
    /**
     * 事前条件処理
     * @see jp.co.isid.mos.bird.framework.logic.DownloadLogic#validate(jp.co.isid.mos.bird.framework.dto.DownloadDto)
     * 
     * @param sessionDto DTO【POSデータ集信Sesshon情報】
     */
    public void validate(DownloadDto downloadDto) throws ApplicationException {
    	PosDataFormDto sessionDto = (PosDataFormDto)downloadDto;
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
        	onerCd = getPosDataFormLogic().getOnerId(sessionDto.getBirdUserInfo().getUserOner()
        			,CommonUtil.COMPANY_CD_MOS);
        	sessionDto.setOnerId(onerCd);
        }
        else {
	    	// ユーザータイプが本部又はオーナーでない場合、権限エラー
	    	throw new CannotAccessException();
        }
        //集信データ日必須チェック
        if (CommonUtil.isNull(sessionDto.getPosDataDt())) {
    		throw new NotNullException("集信データ日");
        }
    }
    /**
     * 実行処理
     * 
     * @param sessionDto DTO【POSデータ集信Sesshon情報】
     * @return
     */
    public List execute (PosDataFormDto sessionDto) {
    	//事前条件処理
    	validate(sessionDto);
    	
    	//DAO【分割オーナー支部】.検索を実行し、List[[オーナー支部]]を取得します。
    	List listSibu = getPosDataUIOnerSibuDao().select(
    			CommonUtil.COMPANY_CD_MOS, sessionDto.getOnerId());
    	if(listSibu == null || listSibu.isEmpty()) {
    		throw new MissingConfigException("分割オーナー支部");
    	}
        // ユーザー関連情報
        BirdUserInfo birdUserInfo = sessionDto.getBirdUserInfo();
        // パラメタ取得
        String userType = birdUserInfo.getMstUser().getUserTypeCd();		
							
    	Map paramMap = new HashMap();
    	//検索条件の設定
	   	paramMap.put( PosDataConstants.MAP_ONER_ID, sessionDto.getOnerId() );//オーナーコード
	   	paramMap.put( PosDataConstants.MAP_USER_TYPE,userType );// ユーザタイプ
	   	paramMap.put( PosDataConstants.MAP_DATA_DT,sessionDto.getPosDataDt() );// 集信データ日
	   	paramMap.put( PosDataConstants.MAP_COMPANY_CD,CommonUtil.COMPANY_CD_MOS);
    	    	
		//LOGIC【POS集信データ取得加工】.ＰＯＳ集信データの取得を実行し、List[[検索結果]]を取得します。
	   	List listSearchData = getPosDataFormLogic().execute(paramMap);
    	//変数Map[支部別情報]を生成します。
    	Map mapSibuData = new HashMap();
    	//List[[オーナー支部]]の件数分、変数Map[支部別出力情報]へ支部コードをキーにUILists[支部情報]を格納します。
    	for (int i=0; i<listSibu.size(); i++) {
    		//List[[オーナー支部]]の現行行のUIOnerSibu[オーナー支部]を取得します。
    		UIOnerSibu eSibu = (UIOnerSibu)listSibu.get(i);
    		//変数UILists[支部]を生成します。
    		//パラメータ：UIOnerSibu[オーナー支部].支部コード、UIOnerSibu[オーナー支部].支部名称
    		UILists uiSibu = new UILists(eSibu.getSibuCd(), eSibu.getSibuName());
    		//変数UILists[支部].List[[データ]]へArrayListを生成します。
    		uiSibu.setListData(new ArrayList(0));
    		//UIOnerSibu[オーナー支部].キーをキーに、変数Map[支部別情報]へUIOnerSibu[オーナー支部]を格納します。
    		mapSibuData.put(uiSibu.getKey(), uiSibu);
    	}
    	//List[[検索結果]]を支部別に分割します。
    	for (int i=0; i<listSearchData.size(); i++) {
    		//List[[検索結果]]の現行行から、UIPosDataInfo[集信データ]を取得します。
    		UIPosDataInfo posData = (UIPosDataInfo)listSearchData.get(i);
    		//変数Map[支部別情報]からUIPosDataInfo[集信データ].支部コードをキーにUILists[支部]を取得します。
    		UILists uiSibu = (UILists)mapSibuData.get(posData.getSibuCd());
    		//UILists[支部].List[[データ]]へUIPosDataInfo[集信データ]を追加します。
    		uiSibu.getListData().add(posData);
    	}
     	//変数List[[支部別出力情報]]を生成します。
    	List listSibuOutputData = new ArrayList(0);
    	//各List[[]]List[[出力用データ]]へ変更後、変数List[[支部別出力情報]]へ格納します。
    	for (int i=0; i<listSibu.size(); i++) {
    		//List[[オーナー支部]]の現行行のUIOnerSibu[オーナー支部]を取得します。
    		UIOnerSibu eSibu = (UIOnerSibu)listSibu.get(i);
    		//UIOnerSibu[オーナー支部].キーをキーに、変数Map[支部別情報]からUILists[支部]を取得します。
    		UILists uiSibu = (UILists)mapSibuData.get(eSibu.getSibuCd());
    		//UILists[支部].List[[データ]]が１件以上ある場合、下記の処理を行います。
    		if (!uiSibu.getListData().isEmpty()) {
	    		//LOGIC【POS集信データ取得加工】.ダウンロードリストの作成を実行し、List[[出力用データ]]を取得します。
	    		List listString = getPosDataFormLogic().addDawnloadList(uiSibu.getListData());//出力用データ
	    		//UILists[支部].List[[データ]]へList[[出力用データ]]を設定します。
	    		uiSibu.setListData(listString);
	    		//UILists[支部].List[[データ]]件数が1件以上の場合、変数List[[支部別出力情報]]へ
	    		//UILists[支部]を追加します。
	    		listSibuOutputData.add(uiSibu);
    		}
    	}   	
			
	    // オーナーユーザの保存を行う。
	    if ( userType.equals( UserType.HONBU ) ) {
	    	sessionDto.setSearchOnerId(sessionDto.getOnerId());
	    }
	    // 集信データ日の保存を行う。
	    sessionDto.setSearchPosDataDt(sessionDto.getPosDataDt());
	    
		//Zipファイルをリターンします。
        return listSibuOutputData;
    }
    /** 
     * コンテントタイプ取得処理
     * @param downloadDto
     * @return String
     * @see 
     */
    public String getContentType(final DownloadDto downloadDto) {
    	return "application/zip";
    }
    /** 
     * ダウンロードファイル名称取得処理
     * @param downloadDto
     * @return String
     */
    public String getFileName(final DownloadDto downloadDto) {
    	return "P4_REC.zip";
    }
    /* (非 Javadoc)
     * @see jp.co.isid.mos.bird.framework.logic.DownloadLogic#getFileFullPath(jp.co.isid.mos.bird.framework.dto.DownloadDto)
     */
    public String getFileFullPath(DownloadDto downloadDto) {
    	// TODO 自動生成されたメソッド・スタブ
    	return getFileName(downloadDto);
    }
	/**
	 * DAO【分割オーナー支部】
	 * @return クラス変数posDataUIOnerSibuDao を戻します。
	 */
	public UIZipOnerSibuDao getPosDataUIOnerSibuDao() {
		return posDataUIOnerSibuDao;
	}

	/**
	 * DAO【分割オーナー支部】
	 * @param posDataUIOnerSibuDao を クラス変数posDataUIOnerSibuDaoへ設定します。
	 */
	public void setPosDataUIOnerSibuDao(UIZipOnerSibuDao posDataUIOnerSibuDao) {
		this.posDataUIOnerSibuDao = posDataUIOnerSibuDao;
	}
	/**
	 * @return クラス変数posDataFormLogic を戻します。
	 */
	public PosDataFormLogic getPosDataFormLogic() {
		return posDataFormLogic;
	}
	/**
	 * @param posDataFormLogic を クラス変数posDataFormLogicへ設定します。
	 */
	public void setPosDataFormLogic(PosDataFormLogic posDataFormLogic) {
		this.posDataFormLogic = posDataFormLogic;
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
