/**
 * 
 */
package jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.dao.UIUrikakesakiNameDao;
import jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.dto.SessionDto;
import jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.entity.TrnPayDetaileStatement;
import jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.entity.UIUrikakesakiName;
import jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.logic.CheckInputDataLogic;
import jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.util.TenantPayPdfRegistUtil;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;

/**
 * 入力データ確認ロジック
 * 
 * 作成日:2009/06/26
 * @author xkinu
 *
 */
public class CheckInputDataLogicImpl implements CheckInputDataLogic {
	/** DAO【売掛先名称】*/
	private UIUrikakesakiNameDao tenantPayPdfRegistUIUrikakesakiNameDao;
	
	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.logic.CheckInputDataLogic#execute(jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.dto.SessionDto)
	 */
	public void execute(SessionDto sessionDto) {
		//１．事前条件処理
		validate(sessionDto);
		//変数[登録情報]へパラメータ.DTO【自画面セッション】.[登録対象エンティティ]を設定します。
		TrnPayDetaileStatement entity = sessionDto.getRegistDataEntity();
		
		//２．変数[登録情報].店コードの必須チェックを行います。
		if(CommonUtil.isNull(sessionDto.getRegistDataEntity().getUrikakeCd())) {
			throw new NotNullException("店コード");
		}
		//３．変数[登録情報].ファイル名称の必須チェックを行います。
		if(CommonUtil.isNull(sessionDto.getRegistDataEntity().getFileName())) {
			throw new NotNullException("ファイル登録");
		}
		//４．パラメータ.DTO【自画面セッション】.登録タイプが”新規”の場合、下記の処理を行います。
		if(TenantPayPdfRegistUtil.REGIST_TYPE_INSERT == sessionDto.getRegistType()) {
			//パラメータ.DTO【自画面セッション】.[登録対象エンティティ].店コードのチェック。
			String miseCd =  entity.getUrikakeCd();
            //４－１．パラメータ.DTO【自画面セッション】.[登録対象エンティティ].店コードのフォーマットチェック
			boolean isAlphabet = true;
            CodeVerifier codeVeri = new CodeVerifier(isAlphabet);
            if(!codeVeri.validate(miseCd) || miseCd.length() > 5) {
                throw new InvalidInputException("店コード", "miseCd", 0);
            }
            //４－２．パラメータ.DTO【自画面セッション】.[登録対象エンティティ].店コードを前0付加処理を行います。
			CodeFormatter cdf = new CodeFormatter(5, "00000");
            cdf.setFormatPattern("00000");
            //４－３．処理４－２で前ゼロ付加した店コードを
            //        パラメータ.DTO【自画面セッション】.[登録対象エンティティ].店コードへ設定します。
            entity.setUrikakeCd(cdf.format(miseCd, true));
            //４－４．DAO【売掛先名称】検索を実行し、結果[売掛先名称]を取得します。
			List listName = 
				getTenantPayPdfRegistUIUrikakesakiNameDao().select(entity.getCompanyCd(),entity.getUrikakeCd());
			//４－５．処理４－４の結果[売掛先名称]がNullの場合は下記の処理を行います。
			if(listName.size()<1) {
				//NotExistException「店コードが存在しません。」のメッセージを出力します。
				throw new NotExistException("店コード");
			}
			//４－６．パラメータ.DTO【自画面セッション】.[登録対象エンティティ].売掛先名称へ
			//        処理４－４の結果[売掛先名称].オーナー名称を設定します。
			UIUrikakesakiName eName = (UIUrikakesakiName)listName.get(0);
			entity.setUrikakeName(eName.getOnerNameKj());
		}

	}
    /**
     * 事前条件処理
     * 
     * @param requestDto
     */
    private void validate(SessionDto sessionDto)
    {
        if (sessionDto == null) {
            throw new MissingDataException("セッション情報");
        }
		//１．登録対象エンティティ存在チェック
		TrnPayDetaileStatement entity = sessionDto.getRegistDataEntity();
		if(entity == null) {
			throw new MissingDataException("登録対象エンティティ");
		}
    }

	/**
	 * @return tenantPayPdfRegistUIUrikakesakiNameDao を戻します。
	 */
	public UIUrikakesakiNameDao getTenantPayPdfRegistUIUrikakesakiNameDao() {
		return tenantPayPdfRegistUIUrikakesakiNameDao;
	}

	/**
	 * @param tenantPayPdfRegistUIUrikakesakiNameDao を クラス変数tenantPayPdfRegistUIUrikakesakiNameDaoへ設定します。
	 */
	public void setTenantPayPdfRegistUIUrikakesakiNameDao(
			UIUrikakesakiNameDao tenantPayPdfRegistUIUrikakesakiNameDao) {
		this.tenantPayPdfRegistUIUrikakesakiNameDao = tenantPayPdfRegistUIUrikakesakiNameDao;
	}

}
