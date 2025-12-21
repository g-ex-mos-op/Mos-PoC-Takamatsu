package jp.co.isid.mos.bird.bizreport.campkako.logic.impl;

import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.bizreport.campkako.dao.UISuiiDao;
import jp.co.isid.mos.bird.bizreport.campkako.logic.SuiiSearchLogic;
import jp.co.isid.mos.bird.bizreport.common.camp.code.TaishoJoken;
import jp.co.isid.mos.bird.bizreport.common.camp.dto.RequestSuiiDto;
import jp.co.isid.mos.bird.bizreport.common.camp.dto.SessionSuiiDto;
import jp.co.isid.mos.bird.bizreport.common.camp.entity.MstCampDate;
import jp.co.isid.mos.bird.bizreport.common.camp.util.CommonUtil;
import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.framework.entity.UIUserOner;
import jp.co.isid.mos.bird.framework.exception.NotNullException;

/**
 * 推移情報検索取得
 * @author xnkusama
 *
 */
public class SuiiSearchLogicImpl implements SuiiSearchLogic {
    /** ロジックID */    
    public static final String LOGIC_ID = "BBR014L14";

    /* DAO */
    UISuiiDao campsuiirefUISuiiDao;
    
    /**
     * 推移情報検索取得
     * @param requestDto
     * @param sessionDto
     */
    public List execute(SessionSuiiDto sessionDto, RequestSuiiDto requestDto) {
        // １．事前条件処理
        validate(sessionDto, requestDto);
        
        //MstCampDate mstCampDate = sessionDto.getMstCampDate(requestDto.getWindowId());
        MstCampDate mstCampDate = requestDto.getMstCampDate();
        String userType = sessionDto.getBirdUserInfo().getMstUser().getUserTypeCd();
        
        List listData;
        // ２.　Dao【キャンペーン売上推移表】.検索を実行する。戻り値List[[キャンペーン売上推移表]]を取得する。
        //本部ユーザー
        if (UserType.HONBU.equals(userType)) {
            listData = getCampsuiirefUISuiiDao()
                                .select(sessionDto.getBirdUserInfo().getUserID(),
                                        requestDto.getCampId(),
                                        requestDto.getMenuTotaledKbn(),
                                        requestDto.getMenuCd(),
                                        requestDto.getTaishoJoken(),
                                        requestDto.getHyojiTaisho(),
                                        requestDto.getBlockCd(),
                                        mstCampDate.getCampFrom(),
                                        mstCampDate.getCampTo(),
                                        mstCampDate.getCompanyCd(),
                                        sessionDto.getBirdUserInfo().isLimit());
        }
        //本部ユーザー以外
        else {
            //会社
            String onerCd = "";
            if (UserType.ONER.equals(userType)) {
                for (Iterator ite = sessionDto.getBirdUserInfo().getUserOner().iterator(); ite.hasNext();) {
                    UIUserOner userOner = (UIUserOner) ite.next();
                    if (requestDto.getCompanyCd().equals(userOner.getCompanyCd())) {
                        onerCd = userOner.getOnerCd();
                    }
                }
            }
            listData = getCampsuiirefUISuiiDao()
                            .selectOner(sessionDto.getBirdUserInfo().getUserID(),
                                        requestDto.getCampId(),
                                        requestDto.getMenuTotaledKbn(),
                                        requestDto.getMenuCd(),
                                        requestDto.getHyojiTaisho(),
                                        mstCampDate.getCampFrom(),
                                        mstCampDate.getCampTo(),
                                        mstCampDate.getCompanyCd(),
                                        onerCd);
                                        
        }

        return listData;
    }

    /**
     * 事前条件処理
     * @param requestDto
     */
    private void validate(SessionSuiiDto sessionDto, RequestSuiiDto requestDto) {
        //本部ユーザーの場合、店コードの入力チェック
        if (UserType.HONBU.equals(sessionDto.getBirdUserInfo().getMstUser().getUserTypeCd())) {
            if (TaishoJoken.CODE_MISE.equals(requestDto.getTaishoJoken())) {
                if (CommonUtil.isNull(requestDto.getHyojiTaisho())) {
                    int indexElement = 0;
                    List listCamp = sessionDto.getListCamp(requestDto.getCompanyCd(), requestDto.getNendo());
                    for (int i = 0; i < listCamp.size(); i++) {
                        MstCampDate entity = (MstCampDate) listCamp.get(i);
                        if (entity.getCampId().equals(requestDto.getCampId())) {
                            indexElement = i;
                        }
                    }
                    throw new NotNullException("店コード", "condTextHyojiTaisho", indexElement);
                }
            }
        }
    }

    public UISuiiDao getCampsuiirefUISuiiDao() {
        return campsuiirefUISuiiDao;
    }

    public void setCampsuiirefUISuiiDao(UISuiiDao campsuiirefUISuiiDao) {
        this.campsuiirefUISuiiDao = campsuiirefUISuiiDao;
    }

}
