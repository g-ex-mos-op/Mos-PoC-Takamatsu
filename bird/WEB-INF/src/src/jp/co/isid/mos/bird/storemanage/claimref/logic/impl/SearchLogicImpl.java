/**
 * 
 */
package jp.co.isid.mos.bird.storemanage.claimref.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.common.code.TaishoJoken;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.storemanage.claimref.dao.UIVoiceInfoDao;
import jp.co.isid.mos.bird.storemanage.claimref.dto.RequestDto;
import jp.co.isid.mos.bird.storemanage.claimref.dto.SessionDto;
import jp.co.isid.mos.bird.storemanage.claimref.entity.UIVoiceInfo;
import jp.co.isid.mos.bird.storemanage.claimref.logic.SearchLogic;
import jp.co.isid.mos.bird.storemanage.claimref.util.VoiceRefUtil;

/**
 * 検索結果取得ロジック
 * 
 * @author xkinu
 *
 */
public class SearchLogicImpl implements SearchLogic {
    /** ロジックID */
    public static final String LOGIC_ID = VoiceRefUtil.SCREEN_ID+"L03";
    /** DAO【お客様の声情報】*/
    private UIVoiceInfoDao voiceRefUIVoiceInfoDao;
	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.bizreport.campniporef.logic.SearchLogic#execute(jp.co.isid.mos.bird.bizreport.common.camp.dto.RequestNipoDto)
	 */
	public List execute(SessionDto sessionDto, RequestDto requestDto) {
		//１．事前条件処理
		validate(sessionDto, requestDto);
		
		String sysDate = requestDto.getSelfSessionDto().getBirdDateInfo().getSysDate();
		String userId = requestDto.getSelfSessionDto().getBirdUserInfo().getUserID();
		String userTypeCd = requestDto.getSelfSessionDto().getUserTypeCd();
		boolean limitFlg = requestDto.getSelfSessionDto().getBirdUserInfo().isLimit();
		String companyCd = requestDto.getCompanyCd();
		String hyojiNaiyo = requestDto.getHyojiNaiyo();
		String bnrM = requestDto.getBnrM();
		String taishoNengetu = requestDto.getTaishoNengetu();
		String taishoJoken = requestDto.getTaishoJoken();
		String hyojiTaisho = requestDto.getHyojiTaisho();
		String kanriNo = requestDto.getKanriNo();
		//２．DAO【お客様の声情報】を実行し、戻り値List[[検索結果]]を取得する。
		List listSearchData =  getVoiceRefUIVoiceInfoDao().select(sysDate
			    , userId, userTypeCd, limitFlg
				, companyCd
				, taishoNengetu+"%"
				, taishoJoken, hyojiTaisho
				, hyojiNaiyo, bnrM
				, kanriNo);
		//３－３．検索結果値の判断をする。
		if(listSearchData == null || listSearchData.size()<1) {
			throw new NoResultException();
		}
		int lastIndex = listSearchData.size()-1;
		String lastJusinDt = ((UIVoiceInfo)listSearchData.get(lastIndex)).getJusinDt();
		for (int i=lastIndex; i>=0; i--) {
			UIVoiceInfo entity = (UIVoiceInfo)listSearchData.get(i);
			String jusinDt = entity.getJusinDt();
			while(i==0 || !lastJusinDt.equals(jusinDt)) {
				UIVoiceInfo eNew = new UIVoiceInfo();
				eNew.setJusinDt(lastJusinDt);
				listSearchData.add((!lastJusinDt.equals(jusinDt)? (i+1):i), eNew);
				if(lastJusinDt.equals(jusinDt)) {
					break;
				}
				lastJusinDt = jusinDt;
			}			
		}
		int seqNo = 0;
		for(int i=0; i<listSearchData.size(); i++) {
			UIVoiceInfo entity = (UIVoiceInfo)listSearchData.get(i);
			if(CommonUtil.isNull(entity.getClaimNo())) {
				seqNo = 1;
				continue;
			}
			entity.setSeq(String.valueOf(seqNo));
			seqNo++;
		}
		//５．List[[検索結果]]をリターンする。
		return listSearchData;
	}
    /**
     * 事前条件処理
     * 
     * @param sessionDto
     * @param requestDto
     */
    private void validate(SessionDto sessionDto, RequestDto requestDto)
    {	
    	BirdDateInfo dateInfo = sessionDto.getBirdDateInfo();
        if (dateInfo == null) {
            throw new MissingDataException("BIRD日付情報");
        }
        if (sessionDto == null) {
            throw new MissingDataException("セッション情報");
        }
    	BirdUserInfo userInfo = sessionDto.getBirdUserInfo();
        if (userInfo == null) {
            throw new MissingDataException("BIRDユーザー情報");
        }
		String companyCd = requestDto.getCompanyCd();
		if(CommonUtil.isNull(companyCd)) {
			throw new NotNullException("会社コード");
		}
		String taishoDt = requestDto.getTaishoNengetu();
		if(CommonUtil.isNull(taishoDt)) {
			throw new NotNullException("対象年月");
		}
		String taishoJoken = requestDto.getTaishoJoken();
		if(CommonUtil.isNull(taishoJoken)) {
			throw new MissingDataException("対象条件");
		}
		if(!TaishoJoken.CODE_ALL.equals(taishoJoken)) {
			String hyojiTaisho = requestDto.getHyojiTaisho();
			if(CommonUtil.isNull(hyojiTaisho)) {
				if(TaishoJoken.CODE_ONER.equals(taishoJoken)) {
					throw new NotNullException("オーナーコード", "onerCd", 0);
				}
				throw new NotNullException("表示対象");
			}
		}
    }
	/**
	 * @return voiceRefUIVoiceInfoDao を戻します。
	 */
	public UIVoiceInfoDao getVoiceRefUIVoiceInfoDao() {
		return voiceRefUIVoiceInfoDao;
	}
	/**
	 * @param voiceRefUIVoiceInfoDao 設定する voiceRefUIVoiceInfoDao。
	 */
	public void setVoiceRefUIVoiceInfoDao(UIVoiceInfoDao voiceRefUIVoiceInfoDao) {
		this.voiceRefUIVoiceInfoDao = voiceRefUIVoiceInfoDao;
	}
}
