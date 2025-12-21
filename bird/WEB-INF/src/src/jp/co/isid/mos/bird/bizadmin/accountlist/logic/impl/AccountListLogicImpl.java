package jp.co.isid.mos.bird.bizadmin.accountlist.logic.impl;

import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.bizadmin.accountlist.code.AccountListConst;
import jp.co.isid.mos.bird.bizadmin.accountlist.dao.UIAccountListDao;
import jp.co.isid.mos.bird.bizadmin.accountlist.dao.UIOnerInfoDao;
import jp.co.isid.mos.bird.bizadmin.accountlist.dto.AccountListDto;
import jp.co.isid.mos.bird.bizadmin.accountlist.entity.UIAccountList;
import jp.co.isid.mos.bird.bizadmin.accountlist.entity.UIOnerInfo;
import jp.co.isid.mos.bird.bizadmin.accountlist.logic.AccountListLogic;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.dto.GamenRoleDto;
import jp.co.isid.mos.bird.framework.entity.UIUserOner;
import jp.co.isid.mos.bird.framework.exception.CannotExecuteException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.MissingConfigException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.logic.GetGamenRoleLogic;
import jp.co.isid.mos.bird.framework.util.DateManager;

/**
 * お申込内容一覧取得 ロジック
 * @author xnkusama
 *
 */
public class AccountListLogicImpl implements AccountListLogic {

    /** ロジックID */
    public static final String LOGIC_ID = "BBA005L01";
    /*DAO*/
    private UIAccountListDao accountlistUIAccountListDao;
    private UIOnerInfoDao accountlistUIOnerInfoDao;
    /*LOGIC*/
    private GetGamenRoleLogic gamenRoleLogic;
    
    public void execute(AccountListDto dto) {
        //事前条件
        validate(dto);

        /*ユーザー一覧を取得*/
        String sysDt = dto.getBirdDateInfo().getSysDate();
        String closeKizyunDt;
        try {
            closeKizyunDt = DateManager.getPrevYear(sysDt.substring(0, 4), 1) + sysDt.substring(4, 8);
        }
        catch (Exception ex) {
            throw new FtlSystemException("ユーザー一覧取得条件の日付", null, ex);
        }
        List listAccount = getAccountlistUIAccountListDao().selectAccountList(dto.getBirdUserInfo().getUserID(), sysDt, closeKizyunDt);
        
        /*該当データなし判別*/
        if (listAccount == null || listAccount.isEmpty()) {
            throw new NoResultException();
        }
        dto.setListAccount(listAccount);
        
        /*オーナー情報を取得*/
        //オーナーコード
        if (dto.getBirdUserInfo().getUserOner() == null || dto.getBirdUserInfo().getUserOner().isEmpty()) {
            throw new CannotExecuteException("ユーザー情報が取得");
        }
        UIUserOner uiUserOner = (UIUserOner) dto.getBirdUserInfo().getUserOner().get(0);
        //オーナー情報取得
        String kizyunDt = dto.getBirdDateInfo().getSysDate().substring(0, 6) + "01";
        UIOnerInfo uiOnerInfo = getAccountlistUIOnerInfoDao()
                                    .getOnerInfo(uiUserOner.getCompanyCd(), uiUserOner.getOnerCd(), kizyunDt, dto.getBirdUserInfo().getUserID());
        /*該当データなし判別*/
        if (uiOnerInfo == null || uiOnerInfo.getMiseCd() == null) {
            dto.setListAccount(null);
            throw new NoResultException();
        }
        
        /*検索結果ヘッダをセット*/
        //オーナー名
        dto.setHeaderOnerName(uiOnerInfo.getOnerNameKj());
        //代表店舗
        String representativeMise = "";
        if (!CommonUtil.isNull(uiOnerInfo.getMiseCd())) {
            representativeMise = uiOnerInfo.getMiseCd() + " " + uiOnerInfo.getMiseNameKj();
        }
        dto.setHeaderRepresentativeMise(representativeMise);
        //お申込日
        for (Iterator ite = listAccount.iterator(); ite.hasNext();) {
            UIAccountList entity = (UIAccountList) ite.next();
            if (entity.getUserId().equals(dto.getBirdUserInfo().getUserID())) {
                dto.setHeaderAppliedDt(entity.getAppliedDt());
                break;
            }
        }
        /*オーナー情報をセット*/
        dto.setUserKeiyakuType(uiOnerInfo.getKeiyakuType());
        
        /*アカウント変更 リンク表示判断*/
        GamenRoleDto gamenRoleDto = new GamenRoleDto();
        gamenRoleDto.setGamenId(AccountListConst.VIEW_ID);
        gamenRoleDto.setUserId(dto.getBirdUserInfo().getUserID());
        gamenRoleDto.setBunruiCd(AccountListConst.LINK_DISPLAY_BUNRUI);
        List listGamenRole = getGamenRoleLogic().getGamenRole(gamenRoleDto);
        if ((listGamenRole != null && !listGamenRole.isEmpty()) && "01".equals(dto.getUserKeiyakuType())) {
            dto.setEnableEditLink(true);
        }
    }
    
    private void validate(AccountListDto dto) {
        if (dto.getBirdUserInfo() == null 
                || CommonUtil.isNull(dto.getBirdUserInfo().getUserID())) 
        {
            throw new MissingConfigException("ログインユーザー情報");
        }
    }

    public UIAccountListDao getAccountlistUIAccountListDao() {
        return accountlistUIAccountListDao;
    }

    public void setAccountlistUIAccountListDao(
            UIAccountListDao accountlistUIAccountListDao) {
        this.accountlistUIAccountListDao = accountlistUIAccountListDao;
    }

    public UIOnerInfoDao getAccountlistUIOnerInfoDao() {
        return accountlistUIOnerInfoDao;
    }

    public void setAccountlistUIOnerInfoDao(UIOnerInfoDao accountlistUIOnerInfoDao) {
        this.accountlistUIOnerInfoDao = accountlistUIOnerInfoDao;
    }

    public GetGamenRoleLogic getGamenRoleLogic() {
        return gamenRoleLogic;
    }

    public void setGamenRoleLogic(GetGamenRoleLogic gamenRoleLogic) {
        this.gamenRoleLogic = gamenRoleLogic;
    }

}
