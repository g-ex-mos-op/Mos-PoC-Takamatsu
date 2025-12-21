/**
 * 
 */
package jp.co.isid.mos.bird.bizsupport.codbalancelist.action.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

import jp.co.isid.mos.bird.bizsupport.codbalancelist.action.ConfirmAction;
import jp.co.isid.mos.bird.bizsupport.codbalancelist.common.CodBalanceListUtil;
import jp.co.isid.mos.bird.bizsupport.codbalancelist.dto.RequestDto;
import jp.co.isid.mos.bird.bizsupport.codbalancelist.logic.ConditionLogic;
import jp.co.isid.mos.bird.framework.dto.CommonCodeDto;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;

/**
 * COD残高一覧アクションインターフェース
 * @author xkinu
 *
 */
public class ConfirmActionImpl implements ConfirmAction {
    /** アクションID：初期化処理 */
    public static final String initialize_ACTION_ID = CodBalanceListUtil.ACTION_ID_CONFIRM+"1";
    /** 共通DTO【プルダウンメニューDTO】*/
    private PullDownMenuDto pullDownMenuDto;
    /** DTO【自画面リクエスト用】*/
    private RequestDto codBalanceListReqDto;
	/** LOGIC【初期情報の取得】*/
    private ConditionLogic codBalanceListConditionLogic;

    /* 
     * 初期化処理アクション
     * 
     * (非 Javadoc)
	 * @see jp.co.isid.mos.bird.bizsupport.codbalancelist.action.ConfirmAction#initialize()
	 */
	public String initialize() {
        //１．DTO【プルダウンメニュー】.クリアフラグがtrueの場合下記の処理を行う。
        if(getPullDownMenuDto().isClearFlg()  || (getCommonCodeDto() != null && getCommonCodeDto().getUseCommonDto())) {            
            //１－０．DTO【プルダウンメニュー】.クリアフラグをfalseに設定する。
            getPullDownMenuDto().setClearFlg(false);

//  add start xkhata 20070912 COD残高一覧 オーナー絞込み対応
            Map pparam = null;
            
            if (  (getCommonCodeDto() != null && getCommonCodeDto().getUseCommonDto())) {
                pparam = new HashMap();
                pparam.put(ConditionLogic.ONER_CD, getCommonCodeDto().getOnerCd());
                pparam.put(ConditionLogic.COMPANY_CD, getCommonCodeDto().getCompanyCd());
            }
            //１．ロジック【初期情報の取得】を実行し、戻り値Mapを取得。
	        Map rparam = getCodBalanceListConditionLogic().execute(pparam);
	        
	        //２．戻り値Mapからダウンロード許可フラグを取得し、
	        //    DTO【Request用】ダウンロード許可フラグへ設定する。
	        boolean downloadFlg = ((Boolean)rparam.get(ConditionLogic.RK_DOWNLOAD_FLG)).booleanValue();
	        getCodBalanceListReqDto().setDownloadFlg(downloadFlg);
	        
	        //３．処理１の戻り値Map.[[COD残高一覧]]をDTO【Request用】.[[COD残高一覧]]へ代入する。
	        List listDatas = (List)rparam.get(ConditionLogic.RK_LIST_GETDATA);
	        getCodBalanceListReqDto().setListDatas(listDatas);
	        
	        //４．戻り値Mapからメッセージ文字列を取得し、
	        //    DTO【Request用】メッセージへ設定する。
	        String errMsg = (String)rparam.get(ConditionLogic.RK_MSG);
	        getCodBalanceListReqDto().setMsg(errMsg);
        }
        //２．DTO【自画面リクエスト用】.例外オブジェクトがnullでない場合下記の処理を行う。
	    if(getCodBalanceListReqDto().getExceptionObj() != null) {
        	//１．DTO【自画面リクエスト用】.例外オブジェクトを変数.例外オブジェクトへ代入する。
        	Exception ex = getCodBalanceListReqDto().getExceptionObj();
        	//２．DTO【自画面リクエスト用】.例外オブジェクトにnullを設定する。
        	getCodBalanceListReqDto().setExceptionObj(null);
        	//３．変数.例外オブジェクトがApplicationExceptionの場合下記の処理を行う。
			if (ex instanceof ApplicationException) {
				//3-1.変数.例外オブジェクトがNotExistExceptionの場合無処理。
				if ( ex instanceof NotExistException) {
					//何もしない
				}
				//3-2.変数.例外オブジェクトがNotExistExceptionで無い場合throwする。
				else {
					throw (ApplicationException)ex;
				}
			}
			//４．変数.例外オブジェクトがApplicationException以外の場合、
			//        FtlSystemExceptionを発生させる。
			else {
				throw new FtlSystemException("COD残高一覧CSVダウンロード", "", ex);
			}	        	
        }
        //３．nullをリターンする。
		return null;
	}

	/**
	 * @return codBalanceListConditionLogic を戻します。
	 */
	public ConditionLogic getCodBalanceListConditionLogic() {
		return codBalanceListConditionLogic;
	}

	/**
	 * @param codBalanceListConditionLogic 設定する codBalanceListConditionLogic。
	 */
	public void setCodBalanceListConditionLogic(
			ConditionLogic codBalanceListConditionLogic) {
		this.codBalanceListConditionLogic = codBalanceListConditionLogic;
	}

	/**
	 * @return codBalanceListReqDto を戻します。
	 */
	public RequestDto getCodBalanceListReqDto() {
		return codBalanceListReqDto;
	}

	/**
	 * @param codBalanceListReqDto 設定する codBalanceListReqDto。
	 */
	public void setCodBalanceListReqDto(RequestDto codBalanceListReqDto) {
		this.codBalanceListReqDto = codBalanceListReqDto;
	}

	/**
	 * @return pullDownMenuDto を戻します。
	 */
	public PullDownMenuDto getPullDownMenuDto() {
		return pullDownMenuDto;
	}

	/**
	 * @param pullDownMenuDto 設定する pullDownMenuDto。
	 */
	public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
		this.pullDownMenuDto = pullDownMenuDto;
	}

    /**
     * 共通コードDTO取得
     * @return 共通コードDTO
     */
    private CommonCodeDto getCommonCodeDto() {
        return (CommonCodeDto) SingletonS2ContainerFactory.getContainer().getComponent(CommonCodeDto.class);
    }

}
