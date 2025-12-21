/**
 *
 */
package jp.co.isid.mos.bird.bizsupport.pointinfotakein.logic.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.beanutils.PropertyUtils;

import jp.co.isid.mos.bird.bizsupport.pointinfotakein.common.PointInfoTakeinCommon;
import jp.co.isid.mos.bird.bizsupport.pointinfotakein.common.PointInfoTakeinConstants;
import jp.co.isid.mos.bird.bizsupport.pointinfotakein.dao.UIOfficialPointHisDao;
import jp.co.isid.mos.bird.bizsupport.pointinfotakein.dao.UIOfficialPointReadHisDao;
import jp.co.isid.mos.bird.bizsupport.pointinfotakein.dao.UIRetirePointDao;
import jp.co.isid.mos.bird.bizsupport.pointinfotakein.dao.UIRetirePointReadDao;
import jp.co.isid.mos.bird.bizsupport.pointinfotakein.dao.UIStaffPointHisDao;
import jp.co.isid.mos.bird.bizsupport.pointinfotakein.dao.UIStaffPointReadHisDao;
import jp.co.isid.mos.bird.bizsupport.pointinfotakein.dto.PointInfoTakeinDto;
import jp.co.isid.mos.bird.bizsupport.pointinfotakein.entity.UIOfficialPointHisInfo;
import jp.co.isid.mos.bird.bizsupport.pointinfotakein.entity.UIOfficialPointReadHisInfo;
import jp.co.isid.mos.bird.bizsupport.pointinfotakein.entity.UIRetirePointInfo;
import jp.co.isid.mos.bird.bizsupport.pointinfotakein.entity.UIRetirePointReadInfo;
import jp.co.isid.mos.bird.bizsupport.pointinfotakein.entity.UIStaffPointHisInfo;
import jp.co.isid.mos.bird.bizsupport.pointinfotakein.entity.UIStaffPointReadHisInfo;
import jp.co.isid.mos.bird.bizsupport.pointinfotakein.logic.RegistPointInfoTakeinLogic;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.util.DateManager;

/**
 * ポイント情報登録ロジック
 *
 * @author yushuncheng
 *
 */
public class RegistPointInfoTakeinLogicImpl implements RegistPointInfoTakeinLogic {

    /* DTO */
    /**
     * 社員付与ポイント取込履歴登録Dao
     */
    private UIStaffPointReadHisDao staffPointReadHisDao;

    /**
     * 社員付与ポイント履歴登録Dao
     */
    private UIStaffPointHisDao staffPointHisDao;

    /**
     * 退職時精算取込履歴登録Dao
     */
    private UIRetirePointReadDao retirePointReadDao;

    /**
     * 退職時精算履歴登録Dao
     */
    private UIRetirePointDao retirePointDao;

    /**
     * 役員付与ポイント取込履歴Dao
     */
    private UIOfficialPointReadHisDao officialPointReadHisDao;

    /**
     * 役員付与ポイント履歴Dao
     */
    private UIOfficialPointHisDao officialPointHisDao;

    BigDecimal bigDecimal = new BigDecimal(0);

    /**
     * ポイント取込情報登録
     *
     * @param PointInfoTakeinDto
     *            画面DTO
     */
    public void execute(PointInfoTakeinDto dto) {
        // チェック処理
        validate(dto);

        List listData = dto.getListUploadData();
        Timestamp tmsp = DateManager.getCurrentTimestamp();

        if (dto.getProcessMode() == PointInfoTakeinConstants.PROCESS_MODE_HPRI) {

            /*
             * 社員付与ポイント取込履歴（BD62HPRI）データを追加
             */
            UIStaffPointReadHisInfo insertRead = null;
            List<UIStaffPointReadHisInfo> insertReadList = new ArrayList<UIStaffPointReadHisInfo>();
            for (int i = 0; i < listData.size(); i++) {
                UIStaffPointReadHisInfo entityRegist = (UIStaffPointReadHisInfo) listData.get(i);
                insertRead = insertBD62(entityRegist, tmsp, dto);
                insertReadList.add(insertRead);
            }
            if (insertReadList != null && insertReadList.size() > 0) {
                getStaffPointReadHisDao().insertStaffPointReadHisList(insertReadList);

                // 基本ポイント情報登録
                executeHpriData(dto, tmsp);
            }
        } else if (dto.getProcessMode() == PointInfoTakeinConstants.PROCESS_MODE_KAIGAI_HPRI) {

            /*
             * 社員付与ポイント取込履歴（BD62HPRI）データを追加
             */
            UIStaffPointReadHisInfo insertRead = null;
            List<UIStaffPointReadHisInfo> insertReadList = new ArrayList<UIStaffPointReadHisInfo>();
            for (int i = 0; i < listData.size(); i++) {
                UIStaffPointReadHisInfo entityRegist = (UIStaffPointReadHisInfo) listData.get(i);
                insertRead = insertBD62(entityRegist, tmsp, dto);
                insertReadList.add(insertRead);
            }

            if (insertReadList != null && insertReadList.size() > 0) {
                getStaffPointReadHisDao().insertStaffPointReadHisList(insertReadList);

                // 海外赴任精算情報登録
                executeKaigaiHpriData(dto, tmsp, insertReadList);
            }
        } else if (dto.getProcessMode() == PointInfoTakeinConstants.PROCESS_MODE_TSES) {

            /*
             * 退職時精算取込履歴（BD64TSES）データを追加
             */
            List<UIRetirePointReadInfo> insertReadList = insertBD64(listData, tmsp, dto);

            /*
             * 退職時精算取込履歴（BD64TSES）の「削除対象フラグ」が「0」の場合、社員付与ポイント取込履歴（BD62HPRI
             * ）データを追加
             */

            UIStaffPointReadHisInfo insertRead = null;
            List<UIStaffPointReadHisInfo> insertReadListByDB62 = new ArrayList<UIStaffPointReadHisInfo>();
            for (int i = 0; i < listData.size(); i++) {
                UIStaffPointReadHisInfo entity = new UIStaffPointReadHisInfo();
                UIRetirePointReadInfo entityRead = (UIRetirePointReadInfo) listData.get(i);
                try {
                    PropertyUtils.copyProperties(entity, entityRead);
                    insertRead = insertBD62(entity, tmsp, dto);
                    insertReadListByDB62.add(insertRead);
                } catch (Exception e) {
                    throw new FtlSystemException("");
                }
            }

            if (insertReadListByDB62.size() > 0) {
                getStaffPointReadHisDao().insertStaffPointReadHisList(insertReadListByDB62);
            }

            if (insertReadList != null && insertReadList.size() > 0) {
                getRetirePointReadDao().insertRetirePointReadList(insertReadList);

                // 退職時精算情報登録
                executeTsesData(dto, tmsp);
            }

        } else if (dto.getProcessMode() == PointInfoTakeinConstants.PROCESS_MODE_YPRI) {

            /*
             * 役員付与ポイント取込履歴（BD63YPRI）データを追加
             */
            List<UIOfficialPointReadHisInfo> insertReadList = insertBD63(listData, tmsp, dto);

            if (insertReadList != null && insertReadList.size() > 0) {
                getOfficialPointReadHisDao().insertOfficialPointReadHisList(insertReadList);

                // 役員付与ポイント情報登録
                executeYpriData(dto, tmsp);
            }
        }
    }

    /**
     * 基本ポイント情報登録
     *
     * @param dto
     *            画面DTO
     * @param tmsp
     *            取込ﾀｲﾑｽﾀﾝﾌﾟ
     */
    private void executeHpriData(PointInfoTakeinDto dto, Timestamp tmsp) {
        // 社員付与ポイント履歴を追加ために、データチェック

        /*
         * 社員付与ポイント取込履歴（BD62HPRI）の「削除対象フラグ」が「1」の場合、
         * 社員付与ポイント履歴（BD59HPRI）を検索し、存在しないのデータを取得
         */
        List<UIStaffPointReadHisInfo> listToriSakujo = getStaffPointReadHisDao().getToriSakujoList(tmsp,
                PointInfoTakeinConstants.POINT_SHU_1);

        // データをチェックした、エラーデータがない場合
        if (listToriSakujo == null || listToriSakujo.size() == 0) {

            /*
             * 社員付与ポイント取込履歴（BD62HPRI）の 「年度」、「会社コード」、「等級」より付与ポイント（BM77HUYP）を検索し、
             * 存在しないのデータを取得
             */
            List<UIStaffPointReadHisInfo> listPoint = getStaffPointReadHisDao().getPointList(tmsp);

            // データをチェックした、エラーデータがない場合
            if (listPoint == null || listPoint.size() == 0) {

                /*
                 * 社員付与ポイント取込履歴（BD62HPRI）の 「社員番号」より統合ユーザー（非履歴）（IR01USER）を検索し、
                 * 存在しないのデータを取得
                 */
                List<UIStaffPointReadHisInfo> listUser = getStaffPointReadHisDao().getUserList(tmsp);

                // データをチェックした、エラーデータがない場合
                if (listUser == null || listUser.size() == 0) {
                    // 社員付与ポイント取込履歴の「削除対象フラグ」が「0」の場合、処理へ行く

                    // 社員付与ポイント取込履歴の「削除対象フラグ」が「0」のデータリストを取得
                    List<UIStaffPointReadHisInfo> listReadHis = getStaffPointReadHisDao().getPointListByFlg(tmsp);

                    List<UIStaffPointHisInfo> insertList = new ArrayList<UIStaffPointHisInfo>();
                    List<UIStaffPointHisInfo> updateList = new ArrayList<UIStaffPointHisInfo>();

                    for (Iterator ite = listReadHis.iterator(); ite.hasNext();) {
                        UIStaffPointReadHisInfo entityRegist = (UIStaffPointReadHisInfo) ite.next();
                        if (entityRegist != null) {
                            if ("0".equals(entityRegist.getToriSakujoFlg())) {
                                // 社員付与ポイント取込履歴（BD62HPRI）の「削除対象フラグ」が「0」の場合,退職時精算（BD61TSES）を削除する
                                getRetirePointDao().deleteBD61(entityRegist.getUserId());
                                UIStaffPointHisInfo entity = new UIStaffPointHisInfo();
                                try {
                                    PropertyUtils.copyProperties(entity, entityRegist);
                                } catch (Exception e) {
                                    throw new FtlSystemException("");
                                }
                                insertBD59(entity, dto, insertList, updateList, PointInfoTakeinConstants.POINT_SHU_1);
                            }
                        }
                    }

                    if (updateList != null && updateList.size() > 0) {
                        getStaffPointHisDao().updateStaffPointHisList(updateList);
                    }
                    if (insertList != null && insertList.size() > 0) {
                        getStaffPointHisDao().insertStaffPointHisList(insertList);
                    }

                    getStaffPointReadHisDao().updateStaffPoint(listReadHis);
                    // 社員付与ポイント取込履歴の「削除対象フラグ」が「1」の場合、処理へ行く
                    // 社員付与ポイント取込履歴の「削除対象フラグ」が「1」により、社員付与ポイント履歴の削除リストを取得
                    List<UIStaffPointHisInfo> deleteList = getStaffPointHisDao().getDeleteList(tmsp,
                            PointInfoTakeinConstants.POINT_SHU_1);
                    getStaffPointHisDao().deleteStaffPointHisList(deleteList);
                } else {
                    // エラーデータがある場合
                    List listErrCsv = new ArrayList();
                    // ヘッダ
                    listErrCsv.add(PointInfoTakeinCommon.makeHeader(dto));

                    // 社員番号が社員マスタに存在しない
                    if (listUser.size() > 0) {
                        for (Iterator ite = listUser.iterator(); ite.hasNext();) {
                            UIStaffPointReadHisInfo info = (UIStaffPointReadHisInfo) ite.next();
                            List listCsv = makeDataHpri(info, "社員番号が存在しません。");
                            listErrCsv.add(listCsv);
                        }
                    }

                    dto.setUploadError(true);
                    dto.setListUploadErrorData(listErrCsv);
                }
            } else {
                // エラーデータがある場合
                List listErrCsv = new ArrayList();
                // ヘッダ
                listErrCsv.add(PointInfoTakeinCommon.makeHeader(dto));

                // 付与ポイントに存在しない
                if (listPoint.size() > 0) {
                    for (Iterator ite = listPoint.iterator(); ite.hasNext();) {
                        UIStaffPointReadHisInfo info = (UIStaffPointReadHisInfo) ite.next();
                        List listCsv = makeDataHpri(info, "「年度」、「会社コード」、「等級」に該当する基本ポイントが存在しません。");
                        listErrCsv.add(listCsv);
                    }
                }

                dto.setUploadError(true);
                dto.setListUploadErrorData(listErrCsv);
            }
        } else {
            // エラーデータがある場合
            List listErrCsv = new ArrayList();
            // ヘッダ
            listErrCsv.add(PointInfoTakeinCommon.makeHeader(dto));

            // 削除対象データが社員付与ポイント履歴に存在しない
            if (listToriSakujo.size() > 0) {
                for (Iterator ite = listToriSakujo.iterator(); ite.hasNext();) {
                    UIStaffPointReadHisInfo info = (UIStaffPointReadHisInfo) ite.next();
                    List listCsv = makeDataHpri(info, "削除対象データが存在しません。");
                    listErrCsv.add(listCsv);
                }
            }

            dto.setUploadError(true);
            dto.setListUploadErrorData(listErrCsv);
        }
    }

    /**
     * 海外赴任精算情報登録
     *
     * @param dto
     *            画面DTO
     * @param tmsp
     *            取込ﾀｲﾑｽﾀﾝﾌﾟ
     * @param listRead
     *            取込情報リスト
     */
    private void executeKaigaiHpriData(PointInfoTakeinDto dto, java.sql.Timestamp tmsp,
            List<UIStaffPointReadHisInfo> listRead) {

        // 社員付与ポイント履歴を追加ために、データチェック

        /*
         * 社員付与ポイント取込履歴（BD62HPRI）の「削除対象フラグ」が「1」の場合、
         * 社員付与ポイント履歴（BD59HPRI）を検索し、存在しないのデータを取得
         */
        List<UIStaffPointReadHisInfo> listToriSakujo = getStaffPointReadHisDao().getToriSakujoList(tmsp,
                PointInfoTakeinConstants.POINT_SHU_2);
        // データをチェックした、エラーデータがない場合
        if (listToriSakujo == null || listToriSakujo.size() == 0) {
            /*
             * 社員付与ポイント取込履歴（BD62HPRI）の 「社員番号」より統合ユーザー（非履歴）（IR01USER）を検索し、
             * 存在しないのデータを取得
             */
            List<UIStaffPointReadHisInfo> listUser = getStaffPointReadHisDao().getUserList(tmsp);
            // データをチェックした、エラーデータがない場合
            if (listUser == null || listUser.size() == 0) {
                List<UIStaffPointHisInfo> insertList = new ArrayList<UIStaffPointHisInfo>();
                List<UIStaffPointHisInfo> updateList = new ArrayList<UIStaffPointHisInfo>();
                // 社員付与ポイント取込履歴の「削除対象フラグ」が「0」の場合、処理へ行く
                // 海外赴任の社員付与ポイントリスト
                for (int i = 0; i < listRead.size(); i++) {
                    UIStaffPointReadHisInfo entityRead = listRead.get(i);
                    /*
                     * 社員付与ポイント取込履歴（BD62HPRIの「削除対象フラグ」が「0」の場合、
                     * 社員付与ポイント履歴（BD59HPRI）の付与ポイント合計値を計算
                     */
                    UIStaffPointHisInfo entityRegist = getStaffPointHisDao().getKaigaiPointInfo(entityRead.getNendo(),
                            entityRead.getUserId());

                    if (entityRegist != null) {
                        entityRead.setPoint(entityRegist.getPoint());
                        entityRead.setRankCd(entityRegist.getRankCd());
                        entityRead.setKbCompanyCd(entityRegist.getKbCompanyCd());
                        entityRegist.setBikou(entityRead.getBikou());
                        entityRegist.setNendo(entityRead.getNendo());
                        insertBD59(entityRegist, dto, insertList, updateList, PointInfoTakeinConstants.POINT_SHU_2);
                    }

                }

                if (updateList != null && updateList.size() > 0) {
                    getStaffPointHisDao().updateStaffPointHisList(updateList);
                }
                if (insertList != null && insertList.size() > 0) {
                    getStaffPointHisDao().insertStaffPointHisList(insertList);
                }

                getStaffPointReadHisDao().updateStaffPoint(listRead);

                // 社員付与ポイント取込履歴の「削除対象フラグ」が「1」により、社員付与ポイント履歴の削除リストを取得
                List<UIStaffPointHisInfo> deleteList = getStaffPointHisDao().getDeleteList(tmsp,
                        PointInfoTakeinConstants.POINT_SHU_2);

                // 社員付与ポイント取込履歴の「削除対象フラグ」が「1」の場合、処理へ行く
                getStaffPointHisDao().deleteStaffPointHisList(deleteList);

            } else {
                // エラーデータがある場合
                List listErrCsv = new ArrayList();
                // ヘッダ
                listErrCsv.add(PointInfoTakeinCommon.makeHeader(dto));

                // 社員番号が社員マスタに存在しない
                if (listUser.size() > 0) {
                    for (Iterator ite = listUser.iterator(); ite.hasNext();) {
                        UIStaffPointReadHisInfo info = (UIStaffPointReadHisInfo) ite.next();
                        List listCsv = makeDataKaigaiHpri(info, "社員番号が存在しません。");
                        listErrCsv.add(listCsv);
                    }
                }

                dto.setUploadError(true);
                dto.setListUploadErrorData(listErrCsv);
            }
        } else {
            // エラーデータがある場合
            List listErrCsv = new ArrayList();
            // ヘッダ
            listErrCsv.add(PointInfoTakeinCommon.makeHeader(dto));

            // 削除対象データが社員付与ポイント履歴に存在しない
            if (listToriSakujo.size() > 0) {
                for (Iterator ite = listToriSakujo.iterator(); ite.hasNext();) {
                    UIStaffPointReadHisInfo info = (UIStaffPointReadHisInfo) ite.next();
                    List listCsv = makeDataKaigaiHpri(info, "削除対象データが存在しません。");
                    listErrCsv.add(listCsv);
                }
            }

            dto.setUploadError(true);
            dto.setListUploadErrorData(listErrCsv);
        }
    }

    /**
     * 退職時精算情報登録
     *
     * @param dto
     *            画面DTO
     * @param tmsp
     *            取込ﾀｲﾑｽﾀﾝﾌﾟ
     */
    private void executeTsesData(PointInfoTakeinDto dto, java.sql.Timestamp tmsp) {

        // 退職時精算を追加ために、データチェック

        /*
         * 退職時精算取込履歴（BD64TSES）の「削除対象フラグ」が「1」の場合、
         * 退職時精算（BD61TSES）を検索し、存在しないのデータを取得
         */
        List<UIRetirePointReadInfo> listToriSakujo = getRetirePointReadDao().getToriSakujoList(tmsp);

        // データをチェックした、エラーデータがない場合
        if (listToriSakujo == null || listToriSakujo.size() == 0) {

            /*
             * 退職時精算取込履歴（BD64TSES）の「社員番号」が入力されている場合、 統合ユーザー（非履歴）（IR01USER）を検索し、
             * 存在しないのデータを取得
             */
            List<UIRetirePointReadInfo> listUser = getRetirePointReadDao().getUserList(tmsp);

            // データをチェックした、エラーデータがない場合
            if (listUser == null || listUser.size() == 0) {

                /*
                 * 退職時精算取込履歴（BD64TSES）の「退職事由」が入力されている場合、
                 * 退職事由（BM79RETI）を検索し、存在しないのデータを取得
                 */
                List<UIRetirePointReadInfo> listReti = getRetirePointReadDao().getRetiList(tmsp);

                // データをチェックした、エラーデータがない場合
                if (listReti == null || listReti.size() == 0) {

                    /*
                     * 退職時精算取込履歴（BD64TSES）の「退職事由」が入力されている場合、
                     * 退職事由支給率（BM80RESI）を検索し、存在しないのデータを取得
                     */
                    List<UIRetirePointReadInfo> listResi = getRetirePointReadDao().getResiList(tmsp);

                    // データをチェックした、エラーデータがない場合
                    if (listResi == null || listResi.size() == 0) {
                        // 退職時精算取込履歴の「削除対象フラグ」が「0」の場合、処理へ行く

                        // 退職時精算取込履歴の「削除対象フラグ」が「0」のデータリストを取得
                        List<UIRetirePointReadInfo> listReadHis = getRetirePointReadDao().getPointListByFlg(tmsp);

                        // 更新退職時精算（BD61TSES）用
                        List<UIRetirePointInfo> insertList = new ArrayList<UIRetirePointInfo>();
                        List<UIRetirePointInfo> updateList = new ArrayList<UIRetirePointInfo>();

                        // 更新社員付与ポイント履歴（BD59HPRI）用
                        List<UIStaffPointHisInfo> insertBD59List = new ArrayList<UIStaffPointHisInfo>();
                        List<UIStaffPointHisInfo> updateBD59List = new ArrayList<UIStaffPointHisInfo>();

                        for (Iterator ite = listReadHis.iterator(); ite.hasNext();) {
                            UIRetirePointReadInfo entityRegist = (UIRetirePointReadInfo) ite.next();
                            if (entityRegist != null) {

                                // 海外赴任中の場合、退職時 累計ポイントと退職精算ポイントを0設置する。
                                if (entityRegist.getPointRuikei() == null || "".equals(entityRegist.getPointRuikei())) {
                                    entityRegist.setPointRuikei(bigDecimal);
                                }
                                if (entityRegist.gettSeisanPoint() == null
                                        || "".equals(entityRegist.gettSeisanPoint())) {
                                    entityRegist.settSeisanPoint(bigDecimal);
                                }

                                // 年度、社員番号、入社日をキーにDBにレコードが存在するか
                                UIRetirePointInfo entityDb = getRetirePointDao().checkExist(entityRegist.getNendo(),
                                        entityRegist.getUserId(), entityRegist.getNyusyaDt());

                                // レコードが存在する場合は更新モード
                                if (entityDb != null) {
                                    entityDb.setPointRuikei(entityRegist.getPointRuikei());
                                    entityDb.setKbCompanyCd(entityRegist.getKbCompanyCd());
                                    entityDb.setTaishokuDt(entityRegist.getTaishokuDt());
                                    entityDb.setKinzokuYear(entityRegist.getKinzokuYear());
                                    entityDb.setRetireCd(entityRegist.getRetireCd());
                                    entityDb.setSikyuRate(entityRegist.getSikyuRate());
                                    entityDb.settSeisanPoint(entityRegist.gettSeisanPoint());
                                    entityDb.setBikou(entityRegist.getBikou());
                                    entityDb.setLastUser(dto.getBirdUserInfo().getUserID());
                                    entityDb.setLastPgm(PointInfoTakeinConstants.VIEW_ID);
                                    entityDb.setLastTmsp(DateManager.getCurrentTimestamp());
                                    updateList.add(entityDb);
                                }
                                // レコードが存在しない場合は新規モード
                                else {
                                    UIRetirePointInfo entity = new UIRetirePointInfo();
                                    try {
                                        PropertyUtils.copyProperties(entity, entityRegist);
                                    } catch (Exception e) {
                                        throw new FtlSystemException("");
                                    }
                                    insertList.add(entity);
                                }

                                getStaffPointHisDao().deleteStaffPointHisInfoList(entityRegist.getUserId(), PointInfoTakeinConstants.POINT_SHU_3);
                                /*
                                 * 退職時精算取込履歴（BD64TSES）の「削除対象フラグ」が「0」の場合
                                 * 社員付与ポイント履歴（BD59HPRI）の付与ポイント合計値を計算
                                 */
                                UIStaffPointHisInfo entityPointSum = getStaffPointHisDao()
                                        .getKaigaiPointInfo(entityRegist.getNendo(), entityRegist.getUserId());
                                if (entityPointSum != null) {
                                    entityPointSum.setBikou(entityRegist.getBikou());
                                    entityPointSum.setNendo(entityRegist.getNendo());
                                    // 社員付与ポイント取込履歴（BD62HPRI）の付与ポイント、会社コード、等級コードを更新
                                    UIStaffPointReadHisInfo staffPointReadHisInfo = new UIStaffPointReadHisInfo();
                                    try {
                                        PropertyUtils.copyProperties(staffPointReadHisInfo, entityPointSum);
                                    } catch (Exception e) {
                                        throw new FtlSystemException("");
                                    }
                                    staffPointReadHisInfo.setTmsp(tmsp);
                                    staffPointReadHisInfo.setPointShu(PointInfoTakeinConstants.POINT_SHU_3);
                                    getStaffPointReadHisDao().updateStaffPointInfo(staffPointReadHisInfo);

                                    insertBD59(entityPointSum, dto, insertBD59List, updateBD59List,
                                            PointInfoTakeinConstants.POINT_SHU_3);
                                }
                            }
                        }

                        // 退職時精算取込履歴（BD64TSES）の「削除対象フラグ」が「0」の場合、
                        // 下記項目以外を退職時精算取込履歴（BD64TSES）の同項目を退職時精算（BD61TSES）に追加または更新する。

                        if (updateList != null && updateList.size() > 0) {
                            getRetirePointDao().updateRetirePointList(updateList);
                        }
                        if (insertList != null && insertList.size() > 0) {
                            getRetirePointDao().insertRetirePointList(insertList);
                        }

                        // 退職時精算取込履歴（BD64TSES）の「削除対象フラグ」が「0」の場合
                        // 社員付与ポイント取込履歴（BD62HPRIの同項目を社員付与ポイント履歴（BD59HPRI）に追加または更新する。
                        if (updateBD59List != null && updateBD59List.size() > 0) {
                            getStaffPointHisDao().updateStaffPointHisList(updateBD59List);
                        }
                        if (insertBD59List != null && insertBD59List.size() > 0) {
                            getStaffPointHisDao().insertStaffPointHisList(insertBD59List);
                        }

                        // 退職時精算取込履歴(BD64TSES)の累計ポイント、退職精算ポイントを更新
                        getRetirePointReadDao().updateRetirePointReadList(listReadHis);
                        // 退職時精算取込履歴の「削除対象フラグ」が「1」の場合、処理へ行く
                        // 退職時精算取込履歴の「削除対象フラグ」が「1」により、退職時精算履歴の削除リストを取得
                        List<UIRetirePointInfo> deleteList = getRetirePointDao().getDeleteList(tmsp);

                        getRetirePointDao().deleteRetirePointList(deleteList);

                        // 退職時精算取込履歴（BD64TSES）の「削除対象フラグ」が「1」の場合、処理へ行く
                        // 社員付与ポイント履歴の削除リストを取得
                        List<UIStaffPointHisInfo> deleteListByBD64 = getStaffPointHisDao().getDeleteListByBD64(tmsp,
                                PointInfoTakeinConstants.POINT_SHU_3);
                        getStaffPointHisDao().deleteStaffPointHisList(deleteListByBD64);
                    } else {
                        // エラーデータがある場合
                        List listErrCsv = new ArrayList();
                        // ヘッダ
                        listErrCsv.add(PointInfoTakeinCommon.makeHeader(dto));

                        // 支給率がマスタに存在しない
                        if (listResi.size() > 0) {
                            for (Iterator ite = listResi.iterator(); ite.hasNext();) {
                                UIRetirePointReadInfo info = (UIRetirePointReadInfo) ite.next();
                                List listCsv = makeDataTses(info, "「退職事由」、「勤続年数」に該当する支給率が存在しません。");
                                listErrCsv.add(listCsv);
                            }
                        }

                        dto.setUploadError(true);
                        dto.setListUploadErrorData(listErrCsv);
                    }
                } else {
                    // エラーデータがある場合
                    List listErrCsv = new ArrayList();
                    // ヘッダ
                    listErrCsv.add(PointInfoTakeinCommon.makeHeader(dto));

                    // 退職事由がマスタに存在しない
                    if (listReti.size() > 0) {
                        for (Iterator ite = listReti.iterator(); ite.hasNext();) {
                            UIRetirePointReadInfo info = (UIRetirePointReadInfo) ite.next();
                            List listCsv = makeDataTses(info, "退職事由が存在しません。");
                            listErrCsv.add(listCsv);
                        }
                    }

                    dto.setUploadError(true);
                    dto.setListUploadErrorData(listErrCsv);
                }
            } else {
                // エラーデータがある場合
                List listErrCsv = new ArrayList();
                // ヘッダ
                listErrCsv.add(PointInfoTakeinCommon.makeHeader(dto));

                // 社員番号が社員マスタに存在しない
                if (listUser.size() > 0) {
                    for (Iterator ite = listUser.iterator(); ite.hasNext();) {
                        UIRetirePointReadInfo info = (UIRetirePointReadInfo) ite.next();
                        List listCsv = makeDataTses(info, "社員番号が存在しません。");
                        listErrCsv.add(listCsv);
                    }
                }

                dto.setUploadError(true);
                dto.setListUploadErrorData(listErrCsv);
            }
        } else {
            // エラーデータがある場合
            List listErrCsv = new ArrayList();
            // ヘッダ
            listErrCsv.add(PointInfoTakeinCommon.makeHeader(dto));

            // 削除対象データが社員付与ポイント履歴に存在しない
            if (listToriSakujo.size() > 0) {
                for (Iterator ite = listToriSakujo.iterator(); ite.hasNext();) {
                    UIRetirePointReadInfo info = (UIRetirePointReadInfo) ite.next();
                    List listCsv = makeDataTses(info, "削除対象データが存在しません。");
                    listErrCsv.add(listCsv);
                }
            }

            dto.setUploadError(true);
            dto.setListUploadErrorData(listErrCsv);
        }
    }

    /**
     * 役員付与ポイント情報登録
     *
     * @param dto
     *            画面DTO
     * @param tmsp
     *            取込ﾀｲﾑｽﾀﾝﾌﾟ
     */
    private void executeYpriData(PointInfoTakeinDto dto, java.sql.Timestamp tmsp) {

        // 役員付与ポイントを追加ために、データチェック

        /*
         * 役員付与ポイント取込履歴（BD63YPRI）の「削除対象フラグ」が「1」の場合、
         * 役員付与ポイント履歴（BD60YPRI）を検索し、存在しないのデータを取得
         */
        List<UIOfficialPointReadHisInfo> listToriSakujo = getOfficialPointReadHisDao().getToriSakujoList(tmsp);

        // データをチェックした、エラーデータがない場合
        if (listToriSakujo == null || listToriSakujo.size() == 0) {

            /*
             * 役員付与ポイント取込履歴（BD63YPRI）の「社員番号」が入力されている場合、
             * 統合ユーザー（非履歴）（IR01USER）を検索し、 存在しないのデータを取得
             */
            List<UIOfficialPointReadHisInfo> listUser = getOfficialPointReadHisDao().getUserList(tmsp);

            // データをチェックした、エラーデータがない場合
            if (listUser == null || listUser.size() == 0) {

                /*
                 * 役員付与ポイント取込履歴（BD63YPRI）の「会社コード」が入力されている場合、
                 * 会社ビュー（VBM78COMR）を検索し、存在しないのデータを取得
                 */
                List<UIOfficialPointReadHisInfo> listCompany = getOfficialPointReadHisDao().getCompanyList(tmsp);

                // データをチェックした、エラーデータがない場合
                if (listCompany == null || listCompany.size() == 0) {

                    // 役員付与ポイント取込履歴の「削除対象フラグ」が「0」の場合、処理へ行く

                    // 役員付与ポイント取込履歴の「削除対象フラグ」が「0」のデータリストを取得
                    List<UIOfficialPointReadHisInfo> listReadHis = getOfficialPointReadHisDao().getListByFlg(tmsp);

                    List<UIOfficialPointHisInfo> insertList = new ArrayList<UIOfficialPointHisInfo>();
                    List<UIOfficialPointHisInfo> updateList = new ArrayList<UIOfficialPointHisInfo>();

                    for (Iterator ite = listReadHis.iterator(); ite.hasNext();) {
                        UIOfficialPointReadHisInfo entityRegist = (UIOfficialPointReadHisInfo) ite.next();
                        if (entityRegist != null) {

                            // 年度、社員番号、適用日、付与ポイント種別名称をキーにDBにレコードが存在するか
                            UIOfficialPointHisInfo entityDb = getOfficialPointHisDao().checkExist(
                                    entityRegist.getNendo(), entityRegist.getUserId(), entityRegist.getPointShuName());

                            // レコードが存在する場合は更新モード
                            if (entityDb != null) {
                                entityDb.setPoint(entityRegist.getPoint());
                                entityDb.setKbCompanyCd(entityRegist.getKbCompanyCd());
                                entityDb.setBikou(entityRegist.getBikou());
                                entityDb.setLastUser(dto.getBirdUserInfo().getUserID());
                                entityDb.setLastPgm(PointInfoTakeinConstants.VIEW_ID);
                                entityDb.setLastTmsp(DateManager.getCurrentTimestamp());
                                updateList.add(entityDb);
                            }
                            // レコードが存在しない場合は新規モード
                            else {
                                UIOfficialPointHisInfo entity = new UIOfficialPointHisInfo();
                                try {
                                    PropertyUtils.copyProperties(entity, entityRegist);
                                } catch (Exception e) {
                                    throw new FtlSystemException("");
                                }
                                insertList.add(entity);
                            }
                        }
                    }

                    if (updateList != null && updateList.size() > 0) {
                        getOfficialPointHisDao().updateOfficialPointHisList(updateList);
                    }
                    if (insertList != null && insertList.size() > 0) {
                        getOfficialPointHisDao().insertOfficialPointHisList(insertList);
                    }

                    // 役員付与ポイント取込履歴の「削除対象フラグ」が「1」の場合、処理へ行く
                    // 役員付与ポイント取込履歴の「削除対象フラグ」が「1」により、役員付与ポイント履歴の削除リストを取得
                    List<UIOfficialPointHisInfo> deleteList = getOfficialPointHisDao().getDeleteList(tmsp);

                    getOfficialPointHisDao().deleteOfficialPointHisList(deleteList);
                } else {
                    // エラーデータがある場合
                    List listErrCsv = new ArrayList();
                    // ヘッダ
                    listErrCsv.add(PointInfoTakeinCommon.makeHeader(dto));

                    // 会社コードが会社等級に存在しない
                    if (listCompany.size() > 0) {
                        for (Iterator ite = listCompany.iterator(); ite.hasNext();) {
                            UIOfficialPointReadHisInfo info = (UIOfficialPointReadHisInfo) ite.next();
                            List listCsv = makeDataYpri(info, "会社コードが存在しません。");
                            listErrCsv.add(listCsv);
                        }
                    }

                    dto.setUploadError(true);
                    dto.setListUploadErrorData(listErrCsv);
                }
            } else {
                // エラーデータがある場合
                List listErrCsv = new ArrayList();
                // ヘッダ
                listErrCsv.add(PointInfoTakeinCommon.makeHeader(dto));

                // 社員番号が社員マスタに存在しない
                if (listUser.size() > 0) {
                    for (Iterator ite = listUser.iterator(); ite.hasNext();) {
                        UIOfficialPointReadHisInfo info = (UIOfficialPointReadHisInfo) ite.next();
                        List listCsv = makeDataYpri(info, "社員番号が存在しません。");
                        listErrCsv.add(listCsv);
                    }
                }

                dto.setUploadError(true);
                dto.setListUploadErrorData(listErrCsv);
            }
        } else {
            // エラーデータがある場合
            List listErrCsv = new ArrayList();
            // ヘッダ
            listErrCsv.add(PointInfoTakeinCommon.makeHeader(dto));

            // 削除対象データが社員付与ポイント履歴に存在しない
            if (listToriSakujo.size() > 0) {
                for (Iterator ite = listToriSakujo.iterator(); ite.hasNext();) {
                    UIOfficialPointReadHisInfo info = (UIOfficialPointReadHisInfo) ite.next();
                    List listCsv = makeDataYpri(info, "削除対象データが存在しません。");
                    listErrCsv.add(listCsv);
                }
            }

            dto.setUploadError(true);
            dto.setListUploadErrorData(listErrCsv);
        }
    }

    /**
     * チェック処理
     *
     * @param dto
     */
    private void validate(PointInfoTakeinDto dto) {
        // 登録データの存在チェック
        if (dto.getListUploadData() == null || dto.getListUploadData().isEmpty()) {
            throw new NotExistException("登録データ");
        }
    }

    /**
     * 基本ポイント付与のデータ行リスト作成
     *
     * @param info
     * @param errorMessage
     * @return
     */
    private List makeDataHpri(UIStaffPointReadHisInfo info, String errorMessage) {

        List<Object> listRowData = new ArrayList<Object>();
        listRowData.add(info.getToriSakujoFlg());
        listRowData.add(info.getNendo());
        listRowData.add(info.getUserId());
        listRowData.add(info.getKbCompanyCd());
        listRowData.add(info.getRankCd());
        listRowData.add(info.getKaigaiFlg());
        listRowData.add(info.getBikou());
        listRowData.add(errorMessage);

        return listRowData;
    }

    /**
     * 海外赴任精算のデータ行リスト作成
     *
     * @param info
     * @param errorMessage
     * @return
     */
    private List makeDataKaigaiHpri(UIStaffPointReadHisInfo info, String errorMessage) {

        List<Object> listRowData = new ArrayList<Object>();
        listRowData.add(info.getToriSakujoFlg());
        listRowData.add(info.getNendo());
        listRowData.add(info.getUserId());
        listRowData.add(info.getBikou());
        listRowData.add(errorMessage);

        return listRowData;
    }

    /**
     * 退職のデータ行リスト作成
     *
     * @param info
     * @param errorMessage
     * @return
     */
    private List makeDataTses(UIRetirePointReadInfo info, String errorMessage) {

        List<Object> listRowData = new ArrayList<Object>();
        listRowData.add(info.getToriSakujoFlg());
        listRowData.add(info.getNendo());
        listRowData.add(info.getUserId());
        listRowData.add(info.getKbCompanyCd());
        listRowData.add(info.getTaishokuDt());
        listRowData.add(info.getNyusyaDt());
        listRowData.add(info.getKinzokuYear());
        listRowData.add(info.getRetireCd());
        listRowData.add(info.getBikou());
        listRowData.add(errorMessage);

        return listRowData;
    }

    /**
     * 退職のデータ行リスト作成
     *
     * @param info
     * @param errorMessage
     * @return
     */
    private List makeDataYpri(UIOfficialPointReadHisInfo info, String errorMessage) {

        List<Object> listRowData = new ArrayList<Object>();
        listRowData.add(info.getToriSakujoFlg());
        listRowData.add(info.getNendo());
        listRowData.add(info.getUserId());
        listRowData.add(info.getKbCompanyCd());
        listRowData.add(info.getPointShuName());
        listRowData.add(info.getPoint());
        listRowData.add(info.getBikou());
        listRowData.add(errorMessage);

        return listRowData;
    }

    public UIStaffPointReadHisDao getStaffPointReadHisDao() {
        return staffPointReadHisDao;
    }

    public void setStaffPointReadHisDao(UIStaffPointReadHisDao staffPointReadHisDao) {
        this.staffPointReadHisDao = staffPointReadHisDao;
    }

    public UIStaffPointHisDao getStaffPointHisDao() {
        return staffPointHisDao;
    }

    public void setStaffPointHisDao(UIStaffPointHisDao staffPointHisDao) {
        this.staffPointHisDao = staffPointHisDao;
    }

    public UIRetirePointReadDao getRetirePointReadDao() {
        return retirePointReadDao;
    }

    public void setRetirePointReadDao(UIRetirePointReadDao retirePointReadDao) {
        this.retirePointReadDao = retirePointReadDao;
    }

    public UIRetirePointDao getRetirePointDao() {
        return retirePointDao;
    }

    public void setRetirePointDao(UIRetirePointDao retirePointDao) {
        this.retirePointDao = retirePointDao;
    }

    public UIOfficialPointReadHisDao getOfficialPointReadHisDao() {
        return officialPointReadHisDao;
    }

    public void setOfficialPointReadHisDao(UIOfficialPointReadHisDao officialPointReadHisDao) {
        this.officialPointReadHisDao = officialPointReadHisDao;
    }

    public UIOfficialPointHisDao getOfficialPointHisDao() {
        return officialPointHisDao;
    }

    public void setOfficialPointHisDao(UIOfficialPointHisDao officialPointHisDao) {
        this.officialPointHisDao = officialPointHisDao;
    }

    public static int getKinzokuYear(String taishokuDt, String nyusyaDt) {
        int nyusyaYear = Integer.parseInt(nyusyaDt.substring(0, 4));
        int nyusyaMonth = Integer.parseInt(nyusyaDt.substring(4, 6));
        int nyusyaDays = Integer.parseInt(nyusyaDt.substring(6, 8));
        int taishokuYear = Integer.parseInt(taishokuDt.substring(0, 4));
        int taishokuMonth = Integer.parseInt(taishokuDt.substring(4, 6));
        int taishokuDays = Integer.parseInt(taishokuDt.substring(6, 8));
        int kinzokuYear = 0;
        if (nyusyaYear < taishokuYear) {
            kinzokuYear = taishokuYear - nyusyaYear;
        }
        if (nyusyaMonth > taishokuMonth) {
            kinzokuYear = kinzokuYear - 1;
        } else if (nyusyaDays > taishokuDays) {
            kinzokuYear = kinzokuYear - 1;
        }
        return kinzokuYear;
    }

//add 2018/03/27 xou.zoubun 退職者ポイント付与対応 begin*/
    /**
     * 勤続年数の計算（年数.月数.日数）
     *
     * @param  taishokuDt  退職日
     * @param  nyusyaDt    入社日
     * @return  勤続年数と月数と日数
     *
     **/
    public static String getKinzokuYearMonthDay(String taishokuDt, String nyusyaDt) {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    	String ret = "00.00.00";  //戻り値初期化

		try {
			Date from = sdf.parse(nyusyaDt);   //入社日
	        Date to = sdf.parse(taishokuDt);   //退職日

	        Calendar calFrom = Calendar.getInstance();
	        calFrom.setTime(from);

	        //入社日が月末の日か判定
	        boolean isLastofMonth = calFrom.getActualMaximum(Calendar.DATE) == calFrom.get(Calendar.DATE);
	        int dayorg = calFrom.get(Calendar.DATE);

	        Calendar calTo = Calendar.getInstance();
	        calTo.setTime(to);

	        //退職日の翌日を取得しておく
	        Calendar calTomorrow = Calendar.getInstance();
	        calTomorrow.setTime(to);
            calTomorrow.add(Calendar.DAY_OF_MONTH, 1);

            long days = 0;
	        long months = 0;
	        //入社日から退職日まで、経過月数を計算する
	        while (true) {
	            calFrom.add(Calendar.MONTH, 1); //From + 1 月
	            if (isLastofMonth) {
	            	//入社日が月末の日の場合、From＋１月後の月末にする。例：1月31日＋１月＝2月28日
	            	calFrom.set(Calendar.DATE, calFrom.getActualMaximum(Calendar.DATE));
	            } else if (dayorg <= calFrom.getActualMaximum(Calendar.DATE)) {
	            	//入社日が月末の日ではない かつ 元の日が該当月の有効日付である場合、元の日にセットする。
	            	calFrom.set(Calendar.DATE, dayorg);
	            }
	            if (calFrom.before(calTo)) {  //FromはToより前の場合、月数を＋１して、ループ続き
	            	months++;
		          	continue;
	            } else if (calFrom.equals(calTomorrow) ) { //From＝退職日の翌日の場合、ぴったりX年X月経過、ループ終了
	            	months++;
	            	days = 0;
	            	break;
	            } else if (calFrom.equals(calTo) ) { //From＝退職日の場合、X年X月と1日を経過、月数を切上げして、ループ終了
	            	months++;
	            	days = 1;
	            	break;
	            } else { //From＞退職日の場合、Fromを１ヶ月前に戻って、退職日と比較し、日数を計算する、ループ終了
		            calFrom.add(Calendar.MONTH, -1);
		            if (isLastofMonth) {
		            	calFrom.set(Calendar.DATE, calFrom.getActualMaximum(Calendar.DATE));
		            }
	            	days = ( calTo.getTimeInMillis() - calFrom.getTimeInMillis() ) / (1000 * 60 * 60 * 24 ) + 1;
	            	break;
	            }
	        }

	        String y = String.valueOf(months / 12); //月数/12で年数を計算する
	        String m = String.format("%2s", String.valueOf(months % 12)).replace(" ", "0") ; //月数MOD12で、月数を計算する、フォーマットする
	        String d = String.format("%2s", String.valueOf(days)).replace(" ", "0") ; //日数をフォーマットする
	        ret  =y + "." + m + "." + d;  //年数.月数.日数の文字列を返す

		} catch (ParseException e) {
			//日付解析できない場合、00.00.00を返す
		}

        return ret;

    }
//add 2018/03/27 xou.zoubun 退職者ポイント付与対応 end*/

    public void insertBD59(UIStaffPointHisInfo entityRegist, PointInfoTakeinDto dto,
            List<UIStaffPointHisInfo> insertList, List<UIStaffPointHisInfo> updateList, String pointShu) {

        // 年度、社員番号、適用日、付与ポイント種別「1」をキーにDBにレコードが存在するか
        UIStaffPointHisInfo entityDb = getStaffPointHisDao().checkExist(entityRegist.getNendo(),
                entityRegist.getUserId(), pointShu);

        // レコードが存在する場合は更新モード
        if (entityDb != null) {

            if (dto.getProcessMode() == PointInfoTakeinConstants.PROCESS_MODE_HPRI) {
                entityDb.setKbCompanyCd(entityRegist.getKbCompanyCd());
                entityDb.setRankCd(entityRegist.getRankCd());
                entityDb.setKaigaiFlg(entityRegist.getKaigaiFlg());
            }
            entityDb.setPoint(entityRegist.getPoint());
            entityDb.setBikou(entityRegist.getBikou());
            entityDb.setLastUser(dto.getBirdUserInfo().getUserID());
            entityDb.setLastPgm(PointInfoTakeinConstants.VIEW_ID);
            entityDb.setLastTmsp(DateManager.getCurrentTimestamp());
            updateList.add(entityDb);
        }
        // レコードが存在しない場合は新規モード
        else {
            if (dto.getProcessMode() == PointInfoTakeinConstants.PROCESS_MODE_HPRI) {
                insertList.add(entityRegist);
            } else {
                // 海外赴任中の場合、固定値1を設置する。
                if (dto.getProcessMode() == PointInfoTakeinConstants.PROCESS_MODE_KAIGAI_HPRI) {
                    entityRegist.setKaigaiFlg("");
                } else if (dto.getProcessMode() == PointInfoTakeinConstants.PROCESS_MODE_TSES) {
                    entityRegist.setKaigaiFlg("");
                }
                entityRegist.setPointShu(pointShu);
                entityRegist.setFirstUser(dto.getBirdUserInfo().getUserID());
                entityRegist.setFirstPgm(PointInfoTakeinConstants.VIEW_ID);
                entityRegist.setFirstTmsp(DateManager.getCurrentTimestamp());
                entityRegist.setLastUser(dto.getBirdUserInfo().getUserID());
                entityRegist.setLastPgm(PointInfoTakeinConstants.VIEW_ID);
                entityRegist.setLastTmsp(DateManager.getCurrentTimestamp());
                insertList.add(entityRegist);
            }

        }

    }

    public UIStaffPointReadHisInfo insertBD62(UIStaffPointReadHisInfo entityRegist, Timestamp tmsp,
            PointInfoTakeinDto dto) {
        if (entityRegist != null) {
            entityRegist.setTmsp(tmsp);
            if (dto.getProcessMode() == PointInfoTakeinConstants.PROCESS_MODE_HPRI) {
                /*
                 * ■付与ポイント種別 固定値「1（基本ポイント）」を設定
                 */
                entityRegist.setPointShu(PointInfoTakeinConstants.POINT_SHU_1);
            } else if (dto.getProcessMode() == PointInfoTakeinConstants.PROCESS_MODE_KAIGAI_HPRI) {
                /*
                 * ■付与ポイント種別 固定値「2（海外赴任精算）」を設定
                 */
                entityRegist.setPointShu(PointInfoTakeinConstants.POINT_SHU_2);
                /*
                 * ■海外赴任中フラグ「ブランク」を設定
                 */
                entityRegist.setKaigaiFlg("");
                entityRegist.setKbCompanyCd("");
                entityRegist.setRankCd("");
            } else if (dto.getProcessMode() == PointInfoTakeinConstants.PROCESS_MODE_TSES) {
                /*
                 * ■付与ポイント種別 固定値「3（退職時精算）」を設定
                 */
                entityRegist.setPointShu(PointInfoTakeinConstants.POINT_SHU_3);
                entityRegist.setKaigaiFlg("");
                entityRegist.setRankCd("");
            }
            entityRegist.setPoint(bigDecimal);
            entityRegist.setFirstUser(dto.getBirdUserInfo().getUserID());
            entityRegist.setFirstPgm(PointInfoTakeinConstants.VIEW_ID);
            entityRegist.setFirstTmsp(DateManager.getCurrentTimestamp());
            entityRegist.setLastUser(dto.getBirdUserInfo().getUserID());
            entityRegist.setLastPgm(PointInfoTakeinConstants.VIEW_ID);
            entityRegist.setLastTmsp(DateManager.getCurrentTimestamp());
        }
        return entityRegist;
    }

    public List<UIRetirePointReadInfo> insertBD64(List listData, Timestamp tmsp, PointInfoTakeinDto dto) {
        List<UIRetirePointReadInfo> insertReadList = new ArrayList<UIRetirePointReadInfo>();
        for (Iterator ite = listData.iterator(); ite.hasNext();) {
            UIRetirePointReadInfo entityRegist = (UIRetirePointReadInfo) ite.next();
            if (entityRegist != null) {
                String nyusyaDt = entityRegist.getNyusyaDt();
                String taishokuDt = entityRegist.getTaishokuDt();
                // 勤続年数=退職年月日－入社年月日
//modify 2018/03/27 xou.zoubun 退職者ポイント付与対応 勤続年数計算変更 begin
                //int kinzokuYear = getKinzokuYear(taishokuDt, nyusyaDt);
                String ymd = getKinzokuYearMonthDay(taishokuDt, nyusyaDt);
                StringTokenizer sTokenizer = new StringTokenizer(ymd, ".");
                long years = Long.valueOf(sTokenizer.nextToken());   //年
                long months = Long.valueOf(sTokenizer.nextToken());  //月
                long days = Long.valueOf(sTokenizer.nextToken());    //日
                BigDecimal y = BigDecimal.valueOf(years);
                BigDecimal m = BigDecimal.valueOf(months).divide(BigDecimal.valueOf(100), 2, BigDecimal.ROUND_HALF_UP);
                BigDecimal d = BigDecimal.valueOf(days).divide(BigDecimal.valueOf(10000), 4, BigDecimal.ROUND_HALF_UP);
                BigDecimal kinzokuYear  = y.add(m).add(d);  //BIGDECIMAL型に変換して、DB更新項目年数.月数にセットする
//                BigDecimal kinzokuDay = BigDecimal.valueOf(days); //BIGDECIMAL型に変換して、日数にセットする
//modify 2018/03/27 xou.zoubun 退職者ポイント付与対応 勤続年数計算変更 end
                entityRegist.setTmsp(tmsp);
                entityRegist.setPointRuikei(bigDecimal);
                entityRegist.setSikyuRate(bigDecimal);
                entityRegist.settSeisanPoint(bigDecimal);
//modify 2018/03/27 xou.zoubun 退職者ポイント付与対応 勤続年数計算変更 begin
                entityRegist.setKinzokuYear(kinzokuYear);
//                entityRegist.setKinzokuDay(kinzokuDay);
//modify 2018/03/27 xou.zoubun 退職者ポイント付与対応 勤続年数計算変更 end
                entityRegist.setFirstUser(dto.getBirdUserInfo().getUserID());
                entityRegist.setFirstPgm(PointInfoTakeinConstants.VIEW_ID);
                entityRegist.setFirstTmsp(DateManager.getCurrentTimestamp());
                entityRegist.setLastUser(dto.getBirdUserInfo().getUserID());
                entityRegist.setLastPgm(PointInfoTakeinConstants.VIEW_ID);
                entityRegist.setLastTmsp(DateManager.getCurrentTimestamp());
                insertReadList.add(entityRegist);
            }
        }
        return insertReadList;
    }

    public List<UIOfficialPointReadHisInfo> insertBD63(List listData, Timestamp tmsp, PointInfoTakeinDto dto) {
        List<UIOfficialPointReadHisInfo> insertReadList = new ArrayList<UIOfficialPointReadHisInfo>();
        for (Iterator ite = listData.iterator(); ite.hasNext();) {
            UIOfficialPointReadHisInfo entityRegist = (UIOfficialPointReadHisInfo) ite.next();
            if (entityRegist != null) {
                entityRegist.setTmsp(tmsp);
                entityRegist.setPointShu(PointInfoTakeinConstants.POINT_SHU_1);
                entityRegist.setFirstUser(dto.getBirdUserInfo().getUserID());
                entityRegist.setFirstPgm(PointInfoTakeinConstants.VIEW_ID);
                entityRegist.setFirstTmsp(DateManager.getCurrentTimestamp());
                entityRegist.setLastUser(dto.getBirdUserInfo().getUserID());
                entityRegist.setLastPgm(PointInfoTakeinConstants.VIEW_ID);
                entityRegist.setLastTmsp(DateManager.getCurrentTimestamp());
                insertReadList.add(entityRegist);
            }
        }
        return insertReadList;
    }
}
