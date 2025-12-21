package jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.logic.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.common.ProceedsCommon;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.common.ProceedsConstants;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.dao.UriageTaxMeisaiDao;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.dto.MeisaiRequestDto;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.dto.ProceedsManageGepoDto;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.dto.UriageTaxMeisaiRequestResultDto;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.entity.UriageTaxMeisaiInfo;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.entity.UserCompanyInfo;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.logic.MstMiseInfoLogic;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.logic.UriageTaxSearchMeisaiLogic;

/**
 * 売上金管理月報売上消費税明細検索ロジック
 * 2019/07/16
 * @author USI 張
 *
 */
public class UriageTaxSearchMeisaiLogicImpl implements UriageTaxSearchMeisaiLogic{

	/** 対象店舗情報取得ロジック */
    private MstMiseInfoLogic mstMiseInfoLogic;

    /** 対象店舗情報取得ロジック */
    private UriageTaxMeisaiDao uriageTaxMeisaiDao;

    /**売上消費税明細結果を保管*/
    private List<UriageTaxMeisaiInfo> uriageTaxMeisaiInfoList = new ArrayList<UriageTaxMeisaiInfo>();

    public void uriageTaxExecute(ProceedsManageGepoDto proceedsManageGepoDto
	  		  , MeisaiRequestDto meisaiRequestJokenDto
	  		  , UriageTaxMeisaiRequestResultDto uriageTaxMeisaiRequestResultDto) {
	    	// 売上金管理月報・結果部DTOを空にする
	        proceedsManageGepoDto.setCommonTransitionUriageTaxMeisaiResult(meisaiRequestJokenDto.getWindowId(),null);

	        // 0.事前条件処理
	    	validate(proceedsManageGepoDto,meisaiRequestJokenDto,uriageTaxMeisaiRequestResultDto);

	    	//1．売上消費税明細検索を実行、検索結果を取得
	        List uriageTaxResult=getUriageTaxMeisaiDao().select(meisaiRequestJokenDto.getCompanyCd()
	          	  ,meisaiRequestJokenDto.getTaishoTenpoCd(), meisaiRequestJokenDto.getTaishoYM());

	        // 2.結果チェック
	        if (uriageTaxResult == null || uriageTaxResult.isEmpty()){
	        	throw new NoResultException();
	        }
	        if (ProceedsCommon.isNull(((UriageTaxMeisaiInfo)uriageTaxResult.get(0)).getEigyoDt())){
	        	throw new NoResultException();
	        }

	        // 3明細条件を明細結果へ設定
	        uriageTaxMeisaiRequestResultDto.setWindowId(meisaiRequestJokenDto.getWindowId());
	        uriageTaxMeisaiRequestResultDto.setCompanyCd(meisaiRequestJokenDto.getCompanyCd());
	        uriageTaxMeisaiRequestResultDto.setTaishoTenpoCd(meisaiRequestJokenDto.getTaishoTenpoCd());
	        uriageTaxMeisaiRequestResultDto.setTaishoYM(meisaiRequestJokenDto.getTaishoYM());
	        uriageTaxMeisaiRequestResultDto.setSyukeiCd(meisaiRequestJokenDto.getSyukeiCd());
	        uriageTaxMeisaiRequestResultDto.setTabNo(meisaiRequestJokenDto.getTabNo());
	        String taishoTenpoName = "";
	        List companyList = proceedsManageGepoDto.getCompanyList();

	        for(Iterator it = companyList.iterator(); it.hasNext();){
	        	UserCompanyInfo company = (UserCompanyInfo)it.next();
	        	if (meisaiRequestJokenDto.getCompanyCd().equals(company.getCompanyCd())){
	        		uriageTaxMeisaiRequestResultDto.setCompanyNm(company.getCompanyName());
	        		break;
	        	}
	        }

	        //null値のデータを削除する
	        uriageTaxMeisaiInfoList.clear();
	        for(int i=0;i<uriageTaxResult.size();i++) {
	        	UriageTaxMeisaiInfo uriageTaxMeisaiInfo = (UriageTaxMeisaiInfo) uriageTaxResult.get(i);
	        	uriageTaxMeisaiInfoList.add(uriageTaxMeisaiInfo);
	        }

	        List<UriageTaxMeisaiInfo> remove_list=new ArrayList<>();
	        for(UriageTaxMeisaiInfo u:uriageTaxMeisaiInfoList) {
	        	if(u.getTax1()==null && u.getTax2()==null && u.getTax3()==null && u.getTax4()==null && u.getTax5()==null &&
	        	   u.getUriage1()==null && u.getUriage2()==null  && u.getUriage3()==null && u.getUriage4()==null  && u.getUriage5()==null){
	        		remove_list.add(u);
	        	}
	        }

	        uriageTaxMeisaiInfoList.removeAll(remove_list);

	        //処理したデータを設定する
	        uriageTaxMeisaiRequestResultDto.setUriageTaxMeisaiList(uriageTaxMeisaiInfoList);

	       // 全店検索の場合、全店固定
	        if (ProceedsConstants.ZENTEN_CD.equals(meisaiRequestJokenDto.getTaishoTenpoCd())) {
	            taishoTenpoName = ProceedsConstants.ZENTEN_NAME;
	        } else {
	            taishoTenpoName = getMstMiseInfoLogic().getMiseCdName(
	                    meisaiRequestJokenDto.getCompanyCd(), meisaiRequestJokenDto.getTaishoTenpoCd()
	            );
	        }
	        uriageTaxMeisaiRequestResultDto.setTaishoTenpo(taishoTenpoName);

	        // 売上金管理月報・結果部DTOに検索結果を保持する
	        proceedsManageGepoDto.setCommonTransitionUriageTaxMeisaiResult(uriageTaxMeisaiRequestResultDto.getWindowId(),uriageTaxMeisaiRequestResultDto);
	     }


    /**
	 * 0.事前条件処理
	 * @param proceedsManageGepoDto 売上金管理月報・条件部DTOクラス
	 * @param meisaiRequestJokenDto 売上金管理月報明細照会・条件部DTOクラス
	 * @param uriageTaxMeisaiRequestResultDto 売上金管理月報売上消費税明細照会・結果部DTOクラス
	 *
	 */
	private void validate(
			ProceedsManageGepoDto proceedsManageGepoDto
		  , MeisaiRequestDto meisaiRequestJokenDto
		  , UriageTaxMeisaiRequestResultDto uriageTaxMeisaiRequestResultDto){

	// セッション情報チェック
		if(ProceedsCommon.isNull(proceedsManageGepoDto.getUserId())){
			throw new NotNullException(ProceedsConstants.MSG_USER_ID);
		}
		if(ProceedsCommon.isNull(proceedsManageGepoDto.getUserType())){
			throw new NotNullException(ProceedsConstants.MSG_USER_TYPE);
		}
		if(proceedsManageGepoDto.getUserType().equals(ProceedsConstants.MISE)){
			if(ProceedsCommon.isNull(meisaiRequestJokenDto.getTaishoTenpo())){
				throw new NotNullException(ProceedsConstants.MSG_TAISHO_TENPO);
			}
		}

	// マスタ情報チェック

		// 入力値チェック
		if(ProceedsCommon.isNull(meisaiRequestJokenDto.getTaishoYM())){
			throw new NotNullException(ProceedsConstants.MSG_TAISHO_YM,"taishoYM",0);
		}

		String taisyoTenpoCd = meisaiRequestJokenDto.getTaishoTenpoCd();

		if(ProceedsCommon.isNull(taisyoTenpoCd)){
			if(proceedsManageGepoDto.getUserType().equals(ProceedsConstants.HONBU)){
                throw new NotNullException(ProceedsConstants.MSG_TAISHO_TENPO,"taishoTenpoCd",0);
			}else{
                taisyoTenpoCd = ProceedsConstants.ZENTEN_CD;
			}
		}

		if(!taisyoTenpoCd.equals(ProceedsConstants.ZENTEN_CD)){
			CodeVerifier codeVeri = new CodeVerifier(true);
            if(!codeVeri.validate(taisyoTenpoCd) || taisyoTenpoCd.length() > 5) {
                throw new InvalidInputException(ProceedsConstants.MSG_TAISHO_TENPO, "taishoTenpoCd", 0);
            }
            CodeFormatter cdf = new CodeFormatter(5, "00000");
            cdf.setFormatPattern("00000");
            taisyoTenpoCd = cdf.format(taisyoTenpoCd, true);
		}
        meisaiRequestJokenDto.setTaishoTenpoCd(taisyoTenpoCd);
	}

	/**
	 * 対象店舗情報取得ロジックを取得する
	 * @return mstMiseInfoLogic 対象店舗情報取得ロジック
	 */
	public MstMiseInfoLogic getMstMiseInfoLogic() {
		return mstMiseInfoLogic;
	}

	/**
	 * 対象店舗情報取得ロジックを設定する
	 * @param mstMiseInfoLogic 対象店舗情報取得ロジック
	 */
	public void setMstMiseInfoLogic(MstMiseInfoLogic mstMiseInfoLogic) {
		this.mstMiseInfoLogic = mstMiseInfoLogic;
	}

	/**
	 *  売上消費税明細情報取得ロジックを設定する
	 * @param uriageTaxMeisaiDao  売上消費税明細情報取得ロジック
	 */
	public void setUriageTaxMeisaiDao(UriageTaxMeisaiDao uriageTaxMeisaiDao) {
		this.uriageTaxMeisaiDao=uriageTaxMeisaiDao;
	}

	/**
	 *  売上消費税明細情報取得ロジックを取得する
	 * @return uriageTaxMeisaiDao  売上消費税明細情報取得ロジック
	 */
	public UriageTaxMeisaiDao getUriageTaxMeisaiDao() {
		return uriageTaxMeisaiDao;
	}
}
