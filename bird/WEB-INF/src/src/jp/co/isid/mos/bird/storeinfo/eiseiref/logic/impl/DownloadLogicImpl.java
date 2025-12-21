package jp.co.isid.mos.bird.storeinfo.eiseiref.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.common.dao.CodHyojiTaishoDao;
import jp.co.isid.mos.bird.framework.control.BirdContext;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.DownloadDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.logic.DownloadLogic;
import jp.co.isid.mos.bird.storeinfo.eiseiref.dto.FileDownloadDto;
import jp.co.isid.mos.bird.storeinfo.eiseiref.util.EiseiRefUtil;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * LOGIC【ダウンロード】
 * 
 * 作成日:2012/12/05
 * @author xkinu
 *
 */
public class DownloadLogicImpl implements DownloadLogic {

    /*LogicID*/
    public static final String LOGIC_ID =    EiseiRefUtil.SCREEN_ID+"L03";

    // ファイルセパレーター
    private String FILE_SEPARATOR = System.getProperties().getProperty("file.separator");

    /** BIRD共通DAO【表示対象】*/
    private CodHyojiTaishoDao codHyojiTaishoDao;
    
    /**
     * ファイル名取得処理<p>
     * このメソッドの戻り値が、ファイル保存ダイアログに表示される<br>
     * ファイル名の<b>デフォルト</b>になります。
     * 【総合衛生】_実施年度_実施状況コード_実施状況名称_店番_店名.pdf
     * 【店舗衛生】_実施年度_店番_店名.pdf
     * 【訪店報告】_実施年度_実施状況コード_実施状況名称_店番_店名.pdf
     * @param downloadDto
     * @return String ファイル名
     */
    public String getFileName(final DownloadDto downloadDto) {
    	//０．パラメータ．DTO【ダウンロード情報】をDTO【ダウンロード対象情報】へキャストする。
    	FileDownloadDto dto = (FileDownloadDto) downloadDto;
    	//１．BIRD共通DAO【表示対象】.店検索を実行し、List[[店舗]]を取得します。
		List listMise = getCodHyojiTaishoDao().selectMise(
				dto.getCompanyCd()
				, getBirdUserInfo().getUserID(), getBirdUserInfo().getMstUser().getUserTypeCd()
				, getBirdUserInfo().isLimit()
				, getBirdDateInfo().getAppDate());
		//２．処理１のList[[店舗]]が0件の場合、Exceptionを発生させます。
		if(listMise.isEmpty()) {
			throw new NotExistException("店舗情報");
		}
		//３．DTO【ダウンロード対象情報】.ダウンロード対象ファイル名称をリターンします。
        return dto.getDownloadFileName();
    }
    
    /**
     * ダウンロードファイルのフルパス取得処理<p>
     * このメソッドの戻り値が、ダウンロード対象になります。<br>
     * ファイル名まで含む完全なパスを取得できるようにして下さい。
     * @param downloadDto
     * @return
     */
    public String getFileFullPath(final DownloadDto downloadDto) {
    	//０．パラメータ．DTO【ダウンロード情報】をDTO【ダウンロード対象情報】へキャストする。
        FileDownloadDto dto = (FileDownloadDto) downloadDto;
        StringBuffer path = new StringBuffer();
        //１．BIRD共通CTR【コンテキスト取得】のファイルプロパティーから"filePathEisei"をキーにファイルパスを取得します。
        path.append(BirdContext.getProperty("fileProperty", "filePathEisei"));
        //２．処理１のファイルパスへ下記のパスを連結します。
        path.append(FILE_SEPARATOR);
        path.append("bd3"+dto.getTabKey()+"tb");
        path.append(FILE_SEPARATOR);
        path.append(getFileName(dto));
        
        return path.toString();
    }
    
    
    /**
     * 事前チェック処理<p>
     * ファイルの存在チェックや権限チェックを行って下さい。<br>
     * ※ 存在チェックは、共通ダウンロードアクション内でも行っています。
     * @param downloadDto
     * @throws ApplicationException
     */
    public void validate(final DownloadDto downloadDto) throws ApplicationException {
        
    }
    
    /** 
     * コンテントタイプ取得処理
     * @param downloadDto
     * @return String
     */
    public String getContentType(final DownloadDto downloadDto) {
        return CONTENT_TYPE_PDF;
    }

	/**
	 * @return クラス変数codHyojiTaishoDao を戻します。
	 */
	public CodHyojiTaishoDao getCodHyojiTaishoDao() {
		return codHyojiTaishoDao;
	}

	/**
	 * @param codHyojiTaishoDao を クラス変数codHyojiTaishoDaoへ設定します。
	 */
	public void setCodHyojiTaishoDao(CodHyojiTaishoDao codHyojiTaishoDao) {
		this.codHyojiTaishoDao = codHyojiTaishoDao;
	}
    /**
     * 
     * @return
     */
    private S2Container getS2Container() {
        return SingletonS2ContainerFactory.getContainer();
    }

    /**
     * BIRD日付情報取得処理
     * @return birdDateInfo を戻します。
     */
    private BirdDateInfo getBirdDateInfo() {
    	return (BirdDateInfo) getS2Container().getComponent(BirdDateInfo.class);
    }

    /**
     * BIRDユーザー情報取得処理
     * @return birdUserInfo を戻します。
     */
    private BirdUserInfo getBirdUserInfo() {
        return (BirdUserInfo) getS2Container().getComponent(BirdUserInfo.class);
    }
}
