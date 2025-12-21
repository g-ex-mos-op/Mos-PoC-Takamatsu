/**
 * 
 */
package jp.co.isid.mos.bird.analysis.sibuaverage.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.analysis.sibuaverage.code.ShukeiKbn;
import jp.co.isid.mos.bird.analysis.sibuaverage.dao.UIOnerInfoDao;
import jp.co.isid.mos.bird.analysis.sibuaverage.dto.SibuAverageDto;
import jp.co.isid.mos.bird.analysis.sibuaverage.dto.SibuAverageReqDto;
import jp.co.isid.mos.bird.analysis.sibuaverage.logic.ChangeOnerLogic;
import jp.co.isid.mos.bird.common.entity.UILists;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;

/**
 * オーナー変更ロジック
 * 作成日:2012/11/02
 * @author xkinu
 *
 */
public class ChangeOnerLogicImpl implements ChangeOnerLogic {
    /** ロジックID */
    public static final String LOGIC_ID = "BDT006L04";
    /** DAO【オーナー情報】**/
    private UIOnerInfoDao sibuaverageOnerInfoDao;
    /**
     * 初期処理
     * @param sessionDto DTO【Session情報】
     * @param requestDto DTO【Request情報】
     */
    private void validate(SibuAverageDto sessionDto, SibuAverageReqDto requestDto) {
        //必須チェック
        if (sessionDto == null) {
            throw new NotNullException("画面情報");
        }
        if (requestDto == null) {
            throw new NotNullException("画面情報");
        }
        // 必須チェック
        if (CommonUtil.isNull(requestDto.getTargetOnerCd())) {
            throw new NotNullException("オーナーコード", "onerCd", 0);
        }
        
    }
	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.analysis.sibuaverage.logic.ChangeOnerLogic#execute(java.lang.String, jp.co.isid.mos.bird.analysis.sibuaverage.dto.SibuAverageDto, jp.co.isid.mos.bird.analysis.sibuaverage.dto.SibuAverageReqDto)
     * @param sessionDto DTO【Session情報】
     * @param requestDto DTO【Request情報】
	 */
	public void execute(SibuAverageDto sessionDto,
			SibuAverageReqDto requestDto) {
		//１．事前条件
		validate(sessionDto, requestDto);
		//２．定数クラス【集計区分】.プルダウンリスト取得処理を実行し、List[[集計区分]]取得します。
    	List listShukeiKbn = ShukeiKbn.getUIListPullDownList();
        //３．処理２のList[[集計区分]]の件数分、下記の処理を行います。
    	for(int i=0; i<listShukeiKbn.size(); i++) {
    		//for-0．List[[集計区分]]から現行インデックスのUILists[集計区分]を取得します。
    		UILists kbnData = (UILists)listShukeiKbn.get(i);
	        //for-1． DAO【オーナー情報】．支部一覧を実行しList[[支部]]を取得します。
	        List listSibu = getSibuaverageOnerInfoDao()
	                                .selectOnerSibuList(requestDto.getCompanyCd(),
	                                		requestDto.getTargetOnerCd(), kbnData.getKey()
	                                		, sessionDto.getBirdUserInfo().isLimit()
	                                		, sessionDto.getUserTypeCd()
	                                		, sessionDto.getUserId());
	        //for-2.取得したList[[支部]]の件数が０件の場合は、下記のExceptionを発生させます。
	        if (listSibu == null || listSibu.isEmpty()) {
	            throw new NoResultException();
	        }
	        //for-3.UILists[集計区分].List[[データ]]へ処理for-2で取得したList[[支部]]を設定します。
	        kbnData.setListData(listSibu);
    	}
    	//４．DTO【Session情報】.List[[集計区分]]へDTO【Request情報】．対象オーナーコードをキーに
    	//処理２のList[[集計区分]]を設定します。
        sessionDto.setListShukeiKbn(requestDto.getTargetOnerCd(), listShukeiKbn);

	}
	/**
	 * @return クラス変数sibuaverageOnerInfoDao を戻します。
	 */
	public UIOnerInfoDao getSibuaverageOnerInfoDao() {
		return sibuaverageOnerInfoDao;
	}
	/**
	 * @param sibuaverageOnerInfoDao を クラス変数sibuaverageOnerInfoDaoへ設定します。
	 */
	public void setSibuaverageOnerInfoDao(UIOnerInfoDao sibuaverageOnerInfoDao) {
		this.sibuaverageOnerInfoDao = sibuaverageOnerInfoDao;
	}
}
