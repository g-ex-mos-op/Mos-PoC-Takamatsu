package jp.co.isid.mos.bird.analysis.sibuaverage.logic.impl;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.analysis.sibuaverage.code.ShukeiKbn;
import jp.co.isid.mos.bird.analysis.sibuaverage.code.SibuAverageConst;
import jp.co.isid.mos.bird.analysis.sibuaverage.code.ZennenDataShubetu;
import jp.co.isid.mos.bird.analysis.sibuaverage.dto.SibuAverageDto;
import jp.co.isid.mos.bird.analysis.sibuaverage.dto.SibuAverageReqDto;
import jp.co.isid.mos.bird.analysis.sibuaverage.entity.UIOnerInfo;
import jp.co.isid.mos.bird.analysis.sibuaverage.logic.ChangeOnerLogic;
import jp.co.isid.mos.bird.analysis.sibuaverage.logic.ConditionInfoLogic;
import jp.co.isid.mos.bird.common.code.TaishoJoken;
import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.common.dao.CodHyojiTaishoDao;
import jp.co.isid.mos.bird.common.entity.UILists;
import jp.co.isid.mos.bird.framework.entity.UIUserOner;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;

/**
 * 条件項目の取得 ロジック
 * @author xnkusama
 * 
 * 更新日:2012/11/01 xkinu 集計区分(支部)プルダウンリスト生成処理の追加
 *
 */
public class ConditionInfoLogicImpl implements ConditionInfoLogic {
    /** ロジックID */
    public static final String LOGIC_ID = "BDT006L01";
    /**DAO*/
    private CodHyojiTaishoDao codHyojiTaishoDao;

    /**LOGIC【オーナー変更ロジック】**/
    private ChangeOnerLogic sibuaverageChangeOnerLogic;
    /*FORMATTER*/
    private DateFormatter dateFormatter = new DateFormatter(DateFormatter.DATE_TYPE_YM, DateFormatter.PATTERN_MONTH_SLASH);
    /**
     * 更新日:2012/11/01 集計区分(支部)プルダウンリスト生成処理の追加
     */
    public void execute(SibuAverageDto sessionDto, SibuAverageReqDto requestDto) {
        //１．初期処理
        validate(sessionDto, requestDto);
        
        //ユーザータイプ別処理
        if (UserType.ONER.equals(sessionDto.getUserTypeCd())) {
        	//対象オーナーコード設定
	        UIUserOner uiUserOner = (UIUserOner) sessionDto.getBirdUserInfo().getUserOner().get(0);
            requestDto.setTargetOnerCd(uiUserOner.getOnerCd());
            //２－１．オーナーユーザーの場合、保有店舗一覧を取得する
            List listHyojiTaisho = getCodHyojiTaishoDao().selectMise(requestDto.getCompanyCd(), 
                                                                     sessionDto.getUserId(), 
                                                                     sessionDto.getUserTypeCd(), 
                                                                     sessionDto.getBirdUserInfo().isLimit(), 
                                                                     sessionDto.getBirdDateInfo().getAppDate());
            sessionDto.setListHyojiTaisho(listHyojiTaisho);
        }
        
        //３．対象条件プルダウン用リスト作成
        sessionDto.setListTaishoJoken(makeTaishoJokenList(sessionDto, requestDto));
        requestDto.setTaishoJoken(TaishoJoken.CODE_MISE);//デフォルト：「個店」or「店舗」
        //４．期間指定プルダウン用リストを作成
        sessionDto.setListKikanSitei(makeKikanList(sessionDto));
        
        //５．DTO【Request情報】．前年データ種別へデフォルト値として「前年同月」を設定します。
        requestDto.setZenDataShu(ZennenDataShubetu.CODE_DOGETU);//デフォルト：「前年同月」
        
        //６．定数クラス【集計区分】.プルダウンリスト取得処理を実行し、List[[集計区分]]取得します。
        List listShukeiKbn = ShukeiKbn.getUIListPullDownList();
        //７．DTO【Request情報】．List[[集計区分]]へ処理６のList[[集計区分]]を設定します。
        requestDto.setListShukeiKbn(listShukeiKbn);
        //８．DTO【Request情報】．集計区分へデフォルト値として「直営・販社を含む」を設定します。
        requestDto.setShukeiKbn(ShukeiKbn.IN_RC);//デフォルト：「直営・販社を含む」
        
        //９．集計区分ごとの保有店支部一覧を取得します。
        if(UserType.isOner(sessionDto.getUserTypeCd())) {
            //９－１．LOGIC【オーナー変更処理】を実行します。
        	getSibuaverageChangeOnerLogic().execute(sessionDto, requestDto);
        	//９－２．DTO【Request情報】．対象オーナーコードをキーにDTO【Session情報】.List[[集計区分]]を取得します。
        	listShukeiKbn = sessionDto.getListShukeiKbn(requestDto.getTargetOnerCd());
        	//９－３．処理９－２で取得したList[[集計区分]]をDTO【Request情報】.List[[集計区分]]へ設定します。
	        requestDto.setListShukeiKbn(listShukeiKbn);
	        //９－４．List[[集計区分]]の件数分、下記の処理を行います。
	    	for(int i=0; i<listShukeiKbn.size(); i++) {
	    		//for-0．List[[集計区分]]から現行インデックスのUILists[集計区分]を取得します。
	    		UILists kbnData = (UILists)listShukeiKbn.get(i);
	    		//for-1.UILists[集計区分].List[[データ]]からインデックス０(ゼロ)番目のUIOnerInfo[オーナー情報]を取得します。
                UIOnerInfo uiOner = (UIOnerInfo) kbnData.getListData().get(0);
                //for-2.DTO【Request情報】．表示対象オーナー名称へUIOnerInfo[オーナー情報].オーナー名称を設定します。
                requestDto.setHyojiTaishoOnerName(uiOner.getOnerNameKj());
                //for-3.UILists[集計区分].List[[データ]]の件数が2件以上の(複数支部にまたがる)場合は、
                //【Request情報】．支部プルダウン表示モードへ"true"を設定します。
		        if ( kbnData.getListData().size() >= 2 ) {
	                requestDto.setSibuMode(true);
		        }
	    	}
        }
    }
    
    /**
     * 対象条件プルダウン作成
     * @return
     */
    private List makeTaishoJokenList(SibuAverageDto sessionDto, SibuAverageReqDto requestDto) {
        String[] codes = new String[]{};
        if (UserType.HONBU.equals(sessionDto.getUserTypeCd())) {
            codes = new String[]{TaishoJoken.CODE_ONER, TaishoJoken.CODE_MISE};
        }
        else if (UserType.ONER.equals(sessionDto.getUserTypeCd())) {
            codes = new String[]{TaishoJoken.CODE_ALL, TaishoJoken.CODE_MISE};
        }
        
        
        return TaishoJoken.getPullDownList(sessionDto.getUserTypeCd(), requestDto.getCompanyCd(), sessionDto.getBirdUserInfo().isLimit(), codes);
    }
    
    /**
     * 期間指定プルダウン作成
     * @return
     */
    private List makeKikanList(SibuAverageDto sessionDto) {
        List listKikan = new ArrayList();
        String basisMonth = sessionDto.getBirdDateInfo().getAppDate().substring(0, 6);
        for (int i = 0; i < SibuAverageConst.KIKAN_SITEI_PULLDOWN; i++) {
            try {
                String month = DateManager.getPrevMonth(basisMonth, i);
                SelectItem sItem = new SelectItem(month, dateFormatter.format(month, true));
                listKikan.add(sItem);
            }
            catch (Exception ex) {
                throw new FtlSystemException("期間指定プルダウン作成", null, ex);
            }
        }
        return listKikan;
    }
    
    /**
     * 初期処理
     * @param sessionDto
     */
    private void validate(SibuAverageDto sessionDto, SibuAverageReqDto requestDto) {
        if (sessionDto == null) {
            throw new NotNullException("画面情報");
        }
        if (requestDto.getCompanyCd() == null || requestDto.getCompanyCd().trim().equals("")) {
            throw new NotNullException("会社");
        }
    }
    
    public CodHyojiTaishoDao getCodHyojiTaishoDao() {
        return codHyojiTaishoDao;
    }

    public void setCodHyojiTaishoDao(CodHyojiTaishoDao codHyojiTaishoDao) {
        this.codHyojiTaishoDao = codHyojiTaishoDao;
    }

	/**
	 * @return クラス変数sibuaverageChangeOnerLogic を戻します。
	 */
	public ChangeOnerLogic getSibuaverageChangeOnerLogic() {
		return sibuaverageChangeOnerLogic;
	}

	/**
	 * @param sibuaverageChangeOnerLogic を クラス変数sibuaverageChangeOnerLogicへ設定します。
	 */
	public void setSibuaverageChangeOnerLogic(
			ChangeOnerLogic sibuaverageChangeOnerLogic) {
		this.sibuaverageChangeOnerLogic = sibuaverageChangeOnerLogic;
	}
}