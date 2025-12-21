/**
 * 
 */
package jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.logic.impl;

import java.sql.Timestamp;

import jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.dao.TrnPayDetaileStatementDao;
import jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.dto.SessionDto;
import jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.entity.TrnPayDetaileStatement;
import jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.logic.RegistLogic;
import jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.util.TenantPayPdfRegistUtil;
import jp.co.isid.mos.bird.framework.exception.AlreadyExistException;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.exception.NoTargetException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;

import org.seasar.framework.exception.SQLRuntimeException;

/**
 * テナント入金明細PDF情報登録ロジック
 * 
 * 作成日:2009/06/24
 * @author xkinu
 *
 */
public class RegistLogicImpl implements RegistLogic {
    /** ロジックID */
    public static final String LOGIC_ID = TenantPayPdfRegistUtil.LOGIC_ID_REGIST;

	/**
	 * DAO【テナント入金明細PDF情報】
	 */
	private TrnPayDetaileStatementDao tenantPayPdfRegistTrnPayDetaileStatementDao;

	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.logic.RegistLogic#execute(jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.dto.SessionDto)
	 */
	public void execute(SessionDto sessionDto) {
		//１．事前条件処理
		validate(sessionDto);
		//２．変数[登録対象]へDTO【自画面セッション】.[登録対象]を設定します。
		TrnPayDetaileStatement entity = sessionDto.getRegistDataEntity();
		//３．変数.登録タイプへDTO【自画面セッション】.登録タイプを設定します。
		int regType = sessionDto.getRegistType();
		//４．変数.登録タイプ別に処理を行います。
		//4-1.変数.登録タイプ=='削除'の場合、下記の処理を行います。
		if (TenantPayPdfRegistUtil.REGIST_TYPE_DELETE == regType) {
			//DAO【テナント入金明細PDF情報】削除処理を行います。
			getTenantPayPdfRegistTrnPayDetaileStatementDao().delete(entity);
		}
		//4-2.変数.登録タイプ=='削除'以外の場合、下記の処理を行います。
		else {
			//1.変数[登録対象].発行日へシステム日付を設定します。
			entity.setHakkoDt(sessionDto.getBirdDateInfo().getSysDate());

			String userId = sessionDto.getBirdUserInfo().getUserID();
			//2.現在の時刻のタイムスタンプを生成します。
			Timestamp currentTimeStamp = DateManager.getCurrentTimestamp();
			String pgmId = LOGIC_ID.substring(0,7);
			//3.変数[登録対象].更新ユーザーへログインユーザIDを設定します。
            entity.setLastUser(userId);          //更新ユーザー
			//4.変数[登録対象].更新タイムスタンプへ処理2のタイムスタンプを設定します。
            entity.setLastTmsp(currentTimeStamp);//更新タイムスタンプ
			//5.変数[登録対象].更新プログラムへ画面IDに"L"を連結した文字列を設定します。
            entity.setLastPgm(pgmId);            //更新プログラム
            //6.変数.登録タイプ=='新規'以外の場合、下記の処理を行います。
			if(TenantPayPdfRegistUtil.REGIST_TYPE_INSERT == regType) {
				//6-1.変数[登録対象].登録ユーザーへ処理3の変数[登録対象].更新ユーザーを設定します。
	            entity.setFirstUser(userId);          //登録ユーザー
				//6-2.変数[登録対象].登録タイムスタンプへ処理4の変数[登録対象].更新タイムスタンプを設定します。
	            entity.setFirstTmsp(currentTimeStamp);//登録タイムスタンプ
				//6-3.変数[登録対象].登録プログラムへ処理5の変数[登録対象].更新プログラムを設定します。
	            entity.setFirstPgm(pgmId);            //登録プログラム
	            try {
	            	//6-4.DAO【テナント入金明細PDF情報】新規登録処理を実行します。
	            	getTenantPayPdfRegistTrnPayDetaileStatementDao().insert(entity);
	            }
	            catch (SQLRuntimeException sqlEx) {
	            	DateFormatter dateF = new DateFormatter(DateFormatter.DATE_TYPE_YM, DateFormatter.PATTERN_MONTH_KANJI_YM);
	            	String yyyyMMKj = dateF.format(entity.getKeisanYm(), true);
	            	throw new AlreadyExistException("対象店舗の"+yyyyMMKj+"第"+Integer.valueOf(entity.getSeq())+"回入金明細");
	            }
			}
            //7.変数.登録タイプ=='更新'以外の場合、下記の処理を行います。
			else if(TenantPayPdfRegistUtil.REGIST_TYPE_UPDATE == regType) {
				//7-1.DAO【テナント入金明細PDF情報】更新処理を実行します。
				getTenantPayPdfRegistTrnPayDetaileStatementDao().update(entity);
			}
		}
	}
    /**
     * 事前条件処理
     * 
     * @param sessionDto
     */
    private void validate(SessionDto sessionDto)
    {
    	//パラメータDTO【自画面セッション】必須チェック
        if (sessionDto == null) {
            throw new MissingDataException("セッション情報");
        }
        //パラメータDTO【自画面セッション】.[登録対象]必須チェック
        if (sessionDto.getRegistDataEntity() == null) {
        	throw new NoTargetException("登録対象");
        }
        //パラメータDTO【自画面セッション】.BIRD日付情報必須チェック
        if (sessionDto.getBirdDateInfo() == null) {
            throw new MissingDataException("BIRD日付情報");
        }
        //パラメータDTO【自画面セッション】.BIRDユーザー情報必須チェック
        if (sessionDto.getBirdUserInfo() == null) {
            throw new MissingDataException("BIRDユーザー情報");
        }
    }
	/**
	 * DAO【テナント入金明細PDF情報】設定処理
	 * 
	 * @return tenantPayPdfRegistTrnPayDetaileStatementDao を戻します。
	 */
	public TrnPayDetaileStatementDao getTenantPayPdfRegistTrnPayDetaileStatementDao() {
		return tenantPayPdfRegistTrnPayDetaileStatementDao;
	}


	/**
	 * DAO【テナント入金明細PDF情報】取得処理
	 * 
	 * @param dao を クラス変数tenantPayPdfRegistTrnPayDetaileStatementDaoへ設定します。
	 */
	public void setTenantPayPdfRegistTrnPayDetaileStatementDao(
			TrnPayDetaileStatementDao dao) {
		this.tenantPayPdfRegistTrnPayDetaileStatementDao = dao;
	}


}
