/**
 * 
 */
package jp.co.isid.mos.bird.storeinfo.eiseiref.logic.impl;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.common.dao.CodHyojiTaishoDao;
import jp.co.isid.mos.bird.common.entity.CodHyojiTaisho;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.storeinfo.eiseiref.dto.RequestDto;
import jp.co.isid.mos.bird.storeinfo.eiseiref.dto.SessionDto;
import jp.co.isid.mos.bird.storeinfo.eiseiref.logic.ConditionLogic;
import jp.co.isid.mos.bird.storeinfo.eiseiref.util.EiseiRefUtil;

/**
 * LOGIC【条件項目の取得設定】
 * 
 * 作成日:2012/12/07
 * @author xkinu
 *
 */
public class ConditionLogicImpl implements ConditionLogic {
    /** ロジックID */
    public static final String LOGIC_ID = EiseiRefUtil.SCREEN_ID+"L01";
    /** DAO【表示対象】*/
    private CodHyojiTaishoDao codHyojiTaishoDao;

	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.storeinfo.eiseiref.logic.ConditionLogic#
	 */
	public void execute(BirdUserInfo birdUserInfo, BirdDateInfo birdDateInfo
			  , SessionDto sessionDto, RequestDto requestDto) {
		//１．事前条件処理
		validate(birdUserInfo, birdDateInfo, sessionDto, requestDto);
		//２．ログインユーザーが本部以外の場合、対象店舗情報を取得します。
		String userTypeCd = birdUserInfo.getMstUser().getUserTypeCd();
		if(!UserType.isHonbu(userTypeCd)) {
			//1.BIRD共通DAO【表示対象】.店検索を実行し、List[[店舗]]を取得します。
			List listMise = getCodHyojiTaishoDao().selectMise(
					requestDto.getCompanyCd()
					, birdUserInfo.getUserID(), userTypeCd, birdUserInfo.isLimit()
					, birdDateInfo.getAppDate());
			//2.処理1のList[[店舗]]が0件の場合、Exceptionを発生させます。
			if(listMise.isEmpty()) {
				throw new NotExistException("店舗情報");
			}
			//3.DTO【Session情報】.List[[店舗]]へ処理1のList[[店舗]]を設定します。
			sessionDto.setListMise(listMise);
			requestDto.setMiseCd(((CodHyojiTaisho)listMise.get(0)).getHyojitaishoCd());
		}
		//３．List[[実施年度]]を生成します。
		List listNendo = new ArrayList(0);
		//４．システム日付の年度を取得し、実施最終年度とします。
		String nendoTo = DateManager.getCurrentYear(birdDateInfo.getSysDate());
		//５．処理４の実施最終年度を含めて過去３年度分の年度をList[[実施年度]]へ格納します。
		try {
			for(int i=0; i<3; i++) {
				String nendo = DateManager.getPrevYear(nendoTo, i);
				if (nendo.compareTo("2013") < 0) {
					//ただし、2013年度より前の年度は格納しない。
					break;
				}
				SelectItem item = new SelectItem(nendo, nendo);
				listNendo.add(item);
			}
		}
		catch (Exception ex) {
			throw new FtlSystemException("店舗衛生結果画面", "実績年度生成処理", ex);
		}
        //６．DTO【Session情報】.List[[実施年度]]へ処理３のList[[実施年度]]を設定します。
		sessionDto.setListNendo(listNendo);
	}
    /**
     * 事前条件処理
     * 
     * @param birdUserInfo
     * @param birdDateInfo
     * @param sessionDto
     * @param requestDto
     */
    private void validate(BirdUserInfo birdUserInfo, BirdDateInfo birdDateInfo
			  , SessionDto sessionDto, RequestDto requestDto)
    {
        if (birdDateInfo == null) {
            throw new MissingDataException("日付情報");
        }
        if (birdUserInfo == null) {
            throw new MissingDataException("ログインユーザー情報");
        }
        if (sessionDto == null) {
            throw new MissingDataException("セッション情報");
        }
        if (requestDto == null) {
            throw new MissingDataException("リクエスト情報");
        }
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
}
