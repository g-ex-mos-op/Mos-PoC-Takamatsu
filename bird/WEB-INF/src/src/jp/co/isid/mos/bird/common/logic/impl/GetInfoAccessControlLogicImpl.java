package jp.co.isid.mos.bird.common.logic.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.common.dao.TrnControlGyotaiKobetuDao;
import jp.co.isid.mos.bird.common.dao.TrnControlGyotaiTenpoDao;
import jp.co.isid.mos.bird.common.entity.TrnControlGyotaiKobetu;
import jp.co.isid.mos.bird.common.entity.TrnControlGyotaiTenpo;
import jp.co.isid.mos.bird.common.entity.TrnInfoAccessControl;
import jp.co.isid.mos.bird.common.logic.GetInfoAccessControlLogic;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.entity.MstUser;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;

/**
 * @author xnkusama
 */
public class GetInfoAccessControlLogicImpl implements GetInfoAccessControlLogic {

    public static final String LOGIC_ID = "BCN000L08";

    // ユーザータイプ：０１ 本部
    private static final String USER_TYPE_HONBU = "01";

    // ユーザータイプ：０２ オーナー
    private static final String USER_TYPE_ONER = "02";

    // ユーザータイプ：０１ 店舗
    private static final String USER_TYPE_MISE = "03";

    private TrnControlGyotaiKobetuDao trnControlGyotaiKobetuDao;

    private TrnControlGyotaiTenpoDao trnControlGyotaiTenpoDao;

    // private class SortComparator implements Comparator {
    // public boolean equals(Object obj) {
    // return (super.equals(obj));
    // }
    // public int compare(Object obj1, Object obj2) {
    // String val1 = ((TrnInfoAccessControl) obj1).getRegDate()
    // + ((TrnInfoAccessControl) obj1).getSeq();
    //            
    // String val2 = ((TrnInfoAccessControl) obj2).getRegDate()
    // + ((TrnInfoAccessControl) obj2).getSeq();
    //            
    // return val1.compareTo(val2);
    // }
    // }

    /**
     * 公開対象の絞込み
     * 
     * @param String
     *            infoShu
     * @param String
     *            birdUserInfo BIRDユーザー情報
     * @param List
     *            listMainData BT12～BT14で制限した情報
     * @exception ApplicationException
     */
    public List execute(String infoShu, BirdUserInfo birdUserInfo,
            List listMainData) throws ApplicationException {
        List listRet = new ArrayList();
        String userId = birdUserInfo.getUserID();

        // メインデータチェック
        if (listMainData == null || listMainData.size() == 0) {
            return listRet;
        }

        // パラメータチェック
        validate(infoShu, birdUserInfo);

        MstUser mstUser = birdUserInfo.getMstUser();
        // マッチング用リスト
        List listKobetsu = null;
        ;
        List listKobetsuMise = null;
        // ユーザータイプ判別
        String userType = mstUser.getUserTypeCd();

        /* BT15IAID 情報アクセス制御業態個別設定 */
        if (USER_TYPE_HONBU.equals(userType)) {
            // 本部：ユーザー対応オーナーが存在する（＝販社）場合は、オーナーと同様の処理
            if (birdUserInfo.getUserOner() != null
                    && birdUserInfo.getUserOner().size() > 0) {
                // オーナー：
                listKobetsu = getTrnControlGyotaiKobetuDao()
                        .getControlGyotaiByOner(infoShu, userId);
            }
        } else if (USER_TYPE_ONER.equals(userType)) {
            // オーナー：
            listKobetsu = getTrnControlGyotaiKobetuDao()
                    .getControlGyotaiByOner(infoShu, userId);
        } else if (USER_TYPE_MISE.equals(userType)) {
            // 店舗：
            listKobetsu = getTrnControlGyotaiKobetuDao()
                    .getControlGyotaiByMise(infoShu, userId);
        } else {
            throw new FtlSystemException("公開対象制限");
        }

        /* BT16IAST 情報アクセス制御業態別店舗別設定 */
        // 本部：
         if (USER_TYPE_HONBU.equals(userType)) {
             // 本部：ユーザー対応オーナーが存在する（＝販社）場合は、オーナーと同様の処理
             if (birdUserInfo.getUserOner() != null
                     && birdUserInfo.getUserOner().size() > 0) {
                 // オーナー：
                 listKobetsuMise = getTrnControlGyotaiTenpoDao()
                         .getControlGyotaiTenpoInfoAccessByOner(infoShu, userId);
             }
         }
        // オーナー：
        if (USER_TYPE_ONER.equals(userType)) {
            listKobetsuMise = getTrnControlGyotaiTenpoDao()
                    .getControlGyotaiTenpoInfoAccessByOner(infoShu, userId);
        }
        // 店舗：
        else if (USER_TYPE_MISE.equals(userType)) {
            listKobetsuMise = getTrnControlGyotaiTenpoDao()
                    .getControlGyotaiTenpoInfoAccessByMise(infoShu, userId);
        }

        // // 業態個別設定とのマッチング
        // List listMatchingGyotai = matchingGyotaiKobetu(listMainData,
        // listKobetsu);
        // // 業態別店舗別設定とのマッチング
        // List listMatchingTenpo = matchingGyotaiMise(listMainData,
        // listKobetsuMise);

        for (int i = 0; i < listMainData.size(); i++) {
            TrnInfoAccessControl entity = (TrnInfoAccessControl) listMainData
                    .get(i);
            if ("1".equals(entity.getKobetsuFlg())
                    || "1".equals(entity.getMiseFlg())) {
                if ("1".equals(entity.getKobetsuFlg())) {                    
                    //ユーザタイプ＝本部、ユーザー対応オーナーが存在する（＝販社）でない場合は個別設定は無視
                    if (USER_TYPE_HONBU.equals(userType)
                            && ((birdUserInfo.getUserOner() != null && birdUserInfo
                                    .getUserOner().size() == 0))) {
                        listRet.add(entity);
                        continue;
                    }
                    if (matchingGyotaiKobetu(entity, listKobetsu)) {
                        listRet.add(entity);
                        continue;
                    }
                }
                if ("1".equals(entity.getMiseFlg())) {
//--- 2007/08/07 本部で販社ユーザーは個店別設定を無視しない
                    //ユーザタイプ＝本部は個別設定は無視
//                    if (USER_TYPE_HONBU.equals(userType)) {
                    if (USER_TYPE_HONBU.equals(userType)
                            && ((birdUserInfo.getUserOner() == null || birdUserInfo.getUserOner().size() == 0))) 
                    {
                        listRet.add(entity);
                        continue;
                    }
                    if (matchingGyotaiMise(entity, listKobetsuMise)) {
                        listRet.add(entity);
                        continue;
                    }
                }
            } else {
                listRet.add(entity);
            }
        }

        // 重複行を削除
        listRet = deleteDuplicate(listRet);
        ((ArrayList) listRet).trimToSize();
        // //マッチング
        // List listRet = matching(listMatchingGyotai, listKobetsu);
        // //ソート
        // Collections.sort(listRet, new SortComparator());
        return listRet;
    }

    /**
     * 事前条件チェック
     * 
     * @param String
     *            infoShu
     * @param String
     *            birdUserInfo BIRDユーザー情報
     * @throws ApplicationException
     */
    private void validate(String infoShu, BirdUserInfo birdUserInfo)
            throws ApplicationException {

        // 情報種別
        if (infoShu == null) {
            throw new NotNullException("情報種別");
        }

        // BIRDユーザー情報
        if (birdUserInfo == null) {
            throw new NotNullException("BIRDユーザー情報");
        }
    }

    /*
     * 業態個別設定情報とのマッチング
     */
    private boolean matchingGyotaiKobetu(TrnInfoAccessControl entityMain,
            List listGyotai) {
        boolean flgMatch = false;
        if (listGyotai == null || listGyotai.size() == 0) {
            return false;
        }

        for (int i = 0; i < listGyotai.size(); i++) {
            TrnControlGyotaiKobetu entityGyotai = (TrnControlGyotaiKobetu) listGyotai
                    .get(i);
            if (entityMain.getRegDate().equals(entityGyotai.getRegDate())
                    && entityMain.getSeq().equals(entityGyotai.getSeq())
                    && entityMain.getGyotaiKbn().equals(
                            entityGyotai.getGyotaiKbn())) {
                flgMatch = true;
                break;
            }
        }
        return flgMatch;
    }

    /*
     * 業態個別設定店舗情報とのマッチング
     */
    private boolean matchingGyotaiMise(TrnInfoAccessControl entityMain,
            List listGyotaiMise) {
        boolean flgMatch = false;
        if (listGyotaiMise == null || listGyotaiMise.size() == 0) {
            return false;
        }
        for (int j = 0; j < listGyotaiMise.size(); j++) {
            TrnControlGyotaiTenpo entityGyotai = (TrnControlGyotaiTenpo) listGyotaiMise
                    .get(j);
            if (entityMain.getRegDate().equals(entityGyotai.getRegDate())
                    && entityMain.getSeq().equals(entityGyotai.getSeq())
                    && entityMain.getGyotaiKbn().equals(
                            entityGyotai.getGyotaiKbn())) {
                flgMatch = true;
                break;
            }
        }
        return flgMatch;
    }

//    /*
//     * 業態個別設定と業態別店舗別設定のマッチング
//     */
//    private List matching(List listKobetu, List listMise) {
//        if ((listKobetu == null || listKobetu.size() == 0)
//                && (listMise == null || listMise.size() == 0)) {
//            return null;
//        }
//        if (listKobetu == null || listKobetu.size() == 0) {
//            return listMise;
//        }
//        if (listMise == null || listMise.size() == 0) {
//            return listKobetu;
//        }
//
//        List listRet = new ArrayList();
//        listRet.addAll(listKobetu);
//        int intSize = listMise.size();
//        boolean flgMatch = false;
//        for (int i = 0; i < intSize; i++) {
//            flgMatch = false;
//            TrnInfoAccessControl entityMise = (TrnInfoAccessControl) listMise
//                    .get(i);
//            for (int j = 0; j < listKobetu.size(); j++) {
//                TrnInfoAccessControl entityKobetu = (TrnInfoAccessControl) listKobetu
//                        .get(j);
//                if (entityKobetu.getRegDate().equals(entityMise.getRegDate())
//                        && entityKobetu.getSeq().equals(entityMise.getSeq())) {
//                    flgMatch = true;
//                    break;
//                }
//            }
//            if (!flgMatch) {
//                listRet.add(entityMise);
//                flgMatch = false;
//            }
//        }
//        return listRet;
//    }

    /*
     * 重複行削除処理
     */
    private List deleteDuplicate(List listData) {
        String oldKey = "";
        String newKey = "";
        for (Iterator ite = listData.iterator(); ite.hasNext();) {
            TrnInfoAccessControl entity = (TrnInfoAccessControl) ite.next();
            newKey = entity.getRegDate() + entity.getSeq();
            if (oldKey.equals(newKey)) {
                ite.remove();
            } else {
                oldKey = newKey;
            }
        }
        return listData;
    }

//    /*
//     * null、空文字判定
//     */
//    private boolean isNull(String str) {
//        return (str == null || "".equals(str.trim())) ? true : false;
//    }

    /**
     * @return trnControlGyotaiKobetuDao を戻します。
     */
    public TrnControlGyotaiKobetuDao getTrnControlGyotaiKobetuDao() {
        return trnControlGyotaiKobetuDao;
    }

    /**
     * @param trnControlGyotaiKobetuDao
     *            trnControlGyotaiKobetuDao を設定。
     */
    public void setTrnControlGyotaiKobetuDao(
            TrnControlGyotaiKobetuDao trnControlGyotaiKobetuDao) {
        this.trnControlGyotaiKobetuDao = trnControlGyotaiKobetuDao;
    }

    /**
     * @return trnControlGyotaiTenpoDao を戻します。
     */
    public TrnControlGyotaiTenpoDao getTrnControlGyotaiTenpoDao() {
        return trnControlGyotaiTenpoDao;
    }

    /**
     * @param trnControlGyotaiTenpoDao
     *            trnControlGyotaiTenpoDao を設定。
     */
    public void setTrnControlGyotaiTenpoDao(
            TrnControlGyotaiTenpoDao trnControlGyotaiTenpoDao) {
        this.trnControlGyotaiTenpoDao = trnControlGyotaiTenpoDao;
    }
}