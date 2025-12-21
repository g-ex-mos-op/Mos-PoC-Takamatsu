/**
 * 
 */
package jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.logic.impl;

import java.io.File;
import java.util.List;

import jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.dto.SearchedDto;
import jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.entity.TrnPayDetaileStatement;
import jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.util.TenantPayPdfRegistUtil;
import jp.co.isid.mos.bird.framework.dto.DownloadDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.GenericErrorException;
import jp.co.isid.mos.bird.framework.logic.DownloadLogic;

/**
 * PDFファイルダウンロードロジック
 * 
 * 作成日:2009/06/24
 * @author xkinu
 *
 */
public class DownloadLogicImpl implements DownloadLogic {
    /** ロジックID */
    public static final String LOGIC_ID = TenantPayPdfRegistUtil.LOGIC_ID_DOWNLOAD;

    /**
     * ファイル名取得処理<p>
     * このメソッドの戻り値が、ファイル保存ダイアログに表示される<br>
     * ファイル名の<b>デフォルト</b>になります。
     * @param downloadDto
     * @return String ファイル名
	 * @see jp.co.isid.mos.bird.framework.logic.DownloadLogic#getFileName(jp.co.isid.mos.bird.framework.dto.DownloadDto)
	 */
	public String getFileName(DownloadDto downloadDto) {
		try {
			return getDownloadFileName(getDownloadEntity((SearchedDto)downloadDto));
		}
		catch (IndexOutOfBoundsException iobEx) {
			GenericErrorException geEx = new GenericErrorException("操作", "ただいまのファイルのダウンロードの操作は無効です。");
			geEx.setStackTrace(iobEx.getStackTrace());
			throw geEx;
		}
	}

    /**
     * ダウンロードファイルのフルパス取得処理<p>
     * このメソッドの戻り値が、ダウンロード対象になります。<br>
     * ファイル名まで含む完全なパスを取得できるようにして下さい。
     * @param downloadDto
     * @return
	 * @see jp.co.isid.mos.bird.framework.logic.DownloadLogic#getFileFullPath(jp.co.isid.mos.bird.framework.dto.DownloadDto)
	 */
	public String getFileFullPath(DownloadDto downloadDto) {
		try {
			TrnPayDetaileStatement entity = getDownloadEntity((SearchedDto)downloadDto);
			return entity.getSaveDir().trim() + File.separator + getDownloadFileName(entity);
		}
		catch (IndexOutOfBoundsException iobEx) {
			GenericErrorException geEx = new GenericErrorException("操作", "ただいまのファイルのダウンロードの操作は無効です。");
			geEx.setStackTrace(iobEx.getStackTrace());
			throw geEx;
		}
	}

    /**
     * 事前チェック処理<p>
     * ファイルの存在チェックや権限チェックを行って下さい。<br>
     * ※ 存在チェックは、共通ダウンロードアクション内でも行っています。
     * @param downloadDto
     * @throws ApplicationException
	 * @see jp.co.isid.mos.bird.framework.logic.DownloadLogic#validate(jp.co.isid.mos.bird.framework.dto.DownloadDto)
	 */
	public void validate(DownloadDto downloadDto) throws ApplicationException {
		// TODO 自動生成されたメソッド・スタブ

	}

	/**
     * コンテントタイプ取得処理
     * @param downloadDto
     * @return String
	 * @see jp.co.isid.mos.bird.framework.logic.DownloadLogic#getContentType(jp.co.isid.mos.bird.framework.dto.DownloadDto)
	 */
	public String getContentType(DownloadDto downloadDto) {
        return "text/plain";
	}
	/**
	 * ダウンロード対象エンティティ取得処理
	 * 
	 * @param searchedDto
	 * @return
	 */
	private TrnPayDetaileStatement getDownloadEntity(SearchedDto searchedDto) {
		int downloadIndex = searchedDto.getDownloadIndex();
		List listSearch = searchedDto.getSessionDto().getListSearchData();
		TrnPayDetaileStatement entity = (TrnPayDetaileStatement) listSearch.get(downloadIndex);
		return entity;
	}
	/**
	 * ダウンロード対象ファイル名称取得処理
	 * 
	 * ファイル名の頭に、「店コード+対象年月+回数+’_’」を付加する。
	 * 例）meisai.pdf ⇒ 0200120090601_meisai.pdf
	 * 
	 * @return ダウンロード対象ファイル名称
	 */
	private String getDownloadFileName(TrnPayDetaileStatement entity) {
		return entity.getUrikakeCd()
		+entity.getKeisanYm()
		+entity.getSeq()
		+"_"+entity.getFileName();
	}

}
