/**
 *
 */
package jp.co.isid.mos.bird.framework.logic.impl;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dao.TrnOutSystemLogDao;
import jp.co.isid.mos.bird.framework.entity.TrnOutSystemLog;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.logic.RegOutputLogLogic;
import jp.co.isid.mos.bird.framework.util.DateManager;

/**
 * 作成日:2009/01/13
 * @author xkinu
 *
 */
public class RegOutputLogLogicImpl implements RegOutputLogLogic {
	/**
	 * DAO【外部システムログ】
	 */
	TrnOutSystemLogDao trnOutSystemLogDao;
	/**
	 * 実行処理
	 *
	 * @param birdUserInfo
	 * @return
	 */
	public int execute(final HttpServletRequest request, final BirdUserInfo birdUserInfo, final String linkId) {
		//１．事前条件処理を行います。
		validate(request, birdUserInfo, linkId);

        //２．タイムスタンプを生成する。
        Timestamp currentTimestamp = DateManager.getCurrentTimestamp();
		TrnOutSystemLog regData = new TrnOutSystemLog();
		HttpServletRequest hr = (HttpServletRequest) request;
        String sUserRemoteIP = hr.getHeader( "x-forwarded-for" );
		regData.setLinkId(linkId);//リンクID
		regData.setUserId(birdUserInfo.getUserID());//ユーザID
		if (sUserRemoteIP == null || "".equals(sUserRemoteIP)) {
			regData.setRmtHost(request.getRemoteHost());//リモートホスト
		} else {
			regData.setRmtHost(sUserRemoteIP);//リモートホスト
		}
        String userAgent = request.getHeader("user-agent");
        if(userAgent != null && userAgent.getBytes().length >200) {
        	userAgent = userAgent.substring(0,200);
        }
		regData.setUserAgnt(userAgent);//ユーザエージェント
		regData.setFirstTmsp(currentTimestamp);//ユーザID

		try {
			//DAO【外部システムログ】新規登録を実行します。
			return getTrnOutSystemLogDao().insert(regData);
		}
		catch (Exception e) {
			throw new FtlSystemException("外部システムログ","新規登録",e);
		}
	}
    /**
     * 事前条件処理
     *
     * @param request
     * @param birdUserInfo
     * @param linkId
     */
    private void validate(final HttpServletRequest request, final BirdUserInfo birdUserInfo, final String linkId) {
        if(request == null){
            //MSG【E0202】(がありません。)
            throw new MissingDataException("リクエスト");
        }
        if(birdUserInfo== null){
            //MSG【E0202】(がありません。)
            throw new MissingDataException("ログインユーザ情報");
        }
        if(linkId== null){
            //MSG【E0202】(がありません。)
            throw new MissingDataException("リンクID");
        }
    }
	/**
	 * @return trnOutSystemLogDao を戻します。
	 */
	public TrnOutSystemLogDao getTrnOutSystemLogDao() {
		return trnOutSystemLogDao;
	}
	/**
	 * @param trnOutSystemLogDao を クラス変数trnOutSystemLogDaoへ設定します。
	 */
	public void setTrnOutSystemLogDao(TrnOutSystemLogDao trnOutSystemLogDao) {
		this.trnOutSystemLogDao = trnOutSystemLogDao;
	}

}
