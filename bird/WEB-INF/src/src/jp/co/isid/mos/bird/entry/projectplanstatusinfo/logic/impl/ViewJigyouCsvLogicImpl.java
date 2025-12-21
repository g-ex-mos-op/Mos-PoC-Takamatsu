/*
 * 作成日: 2006/12/04
 */
package jp.co.isid.mos.bird.entry.projectplanstatusinfo.logic.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.entry.projectplanstatusinfo.dao.UICsvSakuseiDao;
import jp.co.isid.mos.bird.entry.projectplanstatusinfo.dto.ProjectPlanStatusInfoDto;
import jp.co.isid.mos.bird.entry.projectplanstatusinfo.entity.UICsvSakusei;
import jp.co.isid.mos.bird.entry.projectplanstatusinfo.entity.UIStatusInfo;
import jp.co.isid.mos.bird.entry.projectplanstatusinfo.util.ProjectPlanStatusInfoUtil;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.GenericMessageException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;
import jp.co.isid.mos.bird.framework.util.Converter;

/**
 * 事業方針説明会 CSVロジック
 * @author xamaruyama
 */
public class ViewJigyouCsvLogicImpl implements CsvOutputLogic {

    /** ロジックID */
    public static final String LOGIC_ID = ProjectPlanStatusInfoUtil.SCREEN_ID+"L04";

    private UICsvSakuseiDao projectPlanStatusInfoUICsvSakuseiDao;
    /**
     * パラメーターKey：BIRDユーザー情報
     */
    public static final String PK_USERINFO = "userInfo";

   /**
     * ファイル名取得
     */
    public String getFileName(CsvOutputDto csvOutputDto) {

        // DTO
        ProjectPlanStatusInfoDto dto = (ProjectPlanStatusInfoDto) csvOutputDto;
        UIStatusInfo entity = dto.getEntityUIStatusInfo();
        String entryYear = entity.getEntryYear();
        String entryKai = entity.getEntryKai();
        String taishouJokenChoice = dto.getTaishouListChoice();
        String fileName = "";
        if (dto.isKekka() && taishouJokenChoice.equals(ProjectPlanStatusInfoUtil.TAISHO_DOWNLOAD)) {
            //          結果確認ダウンロード
            fileName = "KEKKA.csv";
        }else {
            String taishoListChoice = dto.getSpTaisho();
            if(taishoListChoice.equals(ProjectPlanStatusInfoUtil.TAISHO_PROJECTPLAN)){
                //          事業方針説明会
                fileName = "JIGYO" + entryYear + entryKai + ".csv";
            } else if (taishoListChoice.equals(ProjectPlanStatusInfoUtil.TAISHO_KONSIN)) {
                //          懇親会
                fileName = "KONSHIN" + entryYear + entryKai + ".csv";
            } else if (taishoListChoice.equals(ProjectPlanStatusInfoUtil.TAISHO_KYOEIKAI)) {
                //          共栄会定時総会
                fileName = "KYOEI" + entryYear + entryKai + ".csv";
            } else if (taishoListChoice.equals(ProjectPlanStatusInfoUtil.TAISHO_HONBU_SHUKUHAKU)) {
                //          本部手配宿泊
                fileName = "HONBU" + entryYear + entryKai + ".csv";
            }
        }

        return fileName;

    }

    /* (非 Javadoc)
     * @see jp.co.isid.mos.bird.framework.logic.CsvOutputLogic#getOutputData(jp.co.isid.mos.bird.framework.dto.CsvOutputDto)
     */
    public List getOutputData(CsvOutputDto csvOutputDto) {

        List listCSV = new ArrayList();

        //DTO
        ProjectPlanStatusInfoDto dto = (ProjectPlanStatusInfoDto) csvOutputDto;
        String sysDate = dto.getSysdate();
        boolean limit = dto.isLimit();
        String userId = dto.getUserId();
        UIStatusInfo entity = dto.getEntityUIStatusInfo();
        String entryCd = entity.getEntryCd();
        String entryYear = entity.getEntryYear();
        String entryKai = entity.getEntryKai();
        String companyCd = dto.getCompanyCd();
        String taishouJokenChoice = dto.getTaishouJokenChoice();
        String taishoListChoice = dto.getTaishouListChoice();
        String sibuListChoice = dto.getSibuListChoice();
        String svCd = dto.getSvCd();
        //区分
        String attendKbnChoice = dto.getAttendKbnChoice();
        //検索処理(SQL出力)
        List csvSakusei = null;
        //結果確認ダウンロード時の場合。
        if(dto.isKekka() && ProjectPlanStatusInfoUtil.TAISHO_DOWNLOAD.equals(taishoListChoice)){
            attendKbnChoice = "";
            //対象のウィンドウIDをキーに条件項目パラメーター値を保持
            dto.holdJokenParam();
            csvSakusei = getProjectPlanStatusInfoUICsvSakuseiDao().select(limit, userId,
                    entryCd, entryYear, entryKai, sysDate
                , companyCd, taishouJokenChoice, taishoListChoice, sibuListChoice, svCd, attendKbnChoice);
        }else{
            taishouJokenChoice = dto.getSpTaishoJoken();
            taishoListChoice = dto.getSpTaisho();
            sibuListChoice = dto.getSpSibuCd();
            svCd = dto.getSpSvCd();
            //対象のウィンドウIDをキーに区分の値を保持
            dto.setSpKbn(attendKbnChoice);

            //条件項目『区分』が｢未登録｣の場合。
            if(ProjectPlanStatusInfoUtil.KBN_MITOUROKU.equals(attendKbnChoice)){
                csvSakusei = getProjectPlanStatusInfoUICsvSakuseiDao().selectMitouroku(limit, userId,
                        entryCd, entryYear, entryKai, sysDate
                    , companyCd, taishouJokenChoice, taishoListChoice, sibuListChoice, svCd, attendKbnChoice);
            }
            else if(ProjectPlanStatusInfoUtil.KBN_ALL.equals(attendKbnChoice)){
                //条件項目『区分』が｢全て｣の場合。
                csvSakusei = getProjectPlanStatusInfoUICsvSakuseiDao().selectAll(limit, userId,
                        entryCd, entryYear, entryKai, sysDate
                    , companyCd, taishouJokenChoice, taishoListChoice, sibuListChoice, svCd);
            }else{
                //条件項目『区分』が｢出席｣、｢欠席｣、または対象条件プルダウンが｢本部手配宿泊｣の場合。
                csvSakusei = getProjectPlanStatusInfoUICsvSakuseiDao().select(limit, userId,
                        entryCd, entryYear, entryKai, sysDate
                    , companyCd, taishouJokenChoice, taishoListChoice, sibuListChoice, svCd, attendKbnChoice);
            }
        }
        //該当データが存在しない場合、下記の処理を行う。
        if (csvSakusei == null || csvSakusei.size() == 0) {
            //結果確認ダウンロード時の場合。
            if(dto.isKekka() && ProjectPlanStatusInfoUtil.TAISHO_DOWNLOAD.equals(taishoListChoice)){
                //Ⅰ．前回検索結果データをクリアするため、【DTO】検索結果クリア処理を実行する。
                dto.searchDataClear();
                //Ⅱ．区分プルダウンをデフォルトとして『全て』に設定する。
                dto.setAttendKbnChoice(ProjectPlanStatusInfoUtil.KBN_ALL);
                //Ⅲ．Exception　MSG【E0102】を発生させる。
                throw new NoResultException();

            }
            //各表示結果からのダウンロードの場合。
            else{
                throw new GenericMessageException("申込者が存在しません。");
            }
        }

        dto.setCsvSakusei(csvSakusei);

        // CSV出力用List

        try {
            // ヘッダ部
            makeHeader((ProjectPlanStatusInfoDto) csvOutputDto, listCSV);
            boolean isExistData = false;
            String lastSibuCd = "";
            String lastOnerCd = "";

            for (Iterator ite = dto.getCsvSakusei().iterator(); ite.hasNext();) {
                //Listからentityへキャストする
                UICsvSakusei uICsvSakusei = (UICsvSakusei) ite.next();
                //１行分のリスト作成
                List listData = new ArrayList();
                String sibuCd = uICsvSakusei.getSibuCd();
                String onerCd = uICsvSakusei.getOnerCd();
                String tabNo = uICsvSakusei.getTabNo();
                if(lastSibuCd.equals(sibuCd)
                        && lastOnerCd.equals(onerCd)
                        && ProjectPlanStatusInfoUtil.isNull(tabNo)
                        && isExistData){
                    lastSibuCd = sibuCd;
                    lastOnerCd = onerCd;
                    isExistData = false;
                    continue;
                }
                lastSibuCd = sibuCd;
                lastOnerCd = onerCd;
                if(!ProjectPlanStatusInfoUtil.isNull(tabNo)){
                    isExistData = true;
                }

                //支部コード
                if(sibuCd == null){
                    listData.add("");
                }else{
                    listData.add(sibuCd.trim());
                }

                //支部名称
                if(uICsvSakusei.getSibuName() == null){
                    listData.add("");
                }else{
                    listData.add(uICsvSakusei.getSibuName().trim());
                }

                //オーナーコード
                if(onerCd == null){
                    listData.add("");
                }else{
                    listData.add(onerCd.trim());
                }

                //オーナー名称
                if(uICsvSakusei.getOnerNameKj() == null){
                    listData.add("");
                }else{
                    listData.add(uICsvSakusei.getOnerNameKj().trim());
                }

                //SEQ_NO
                if(tabNo == null){
                    listData.add("");
                }else{
                    listData.add(tabNo.trim());
                }

                //店舗コード
                String miseCd = uICsvSakusei.getMiseCd();
                if(miseCd == null){
                    listData.add("");
                }else{
                    listData.add(miseCd.trim());
                }

                //店舗名称
                if(uICsvSakusei.getMiseNameKj() == null){
                    listData.add("");
                }else{
                    listData.add(uICsvSakusei.getMiseNameKj().trim());
                }

                //責任者氏名
                if(uICsvSakusei.getName() == null){
                    listData.add("");
                }else{
                    listData.add(uICsvSakusei.getName().trim());
                }

                //電話番号
                if(uICsvSakusei.getTel() == null){
                    listData.add("");
                }else{
                    listData.add(uICsvSakusei.getTel().trim());
                }

                //氏名漢字(姓)
                if(uICsvSakusei.getLNameKj() == null){
                    listData.add("");
                }else{
                    listData.add(uICsvSakusei.getLNameKj().trim());
                }

                //氏名漢字(名)

                if(uICsvSakusei.getFNameKj() == null){
                    listData.add("");
                }else{
                    listData.add(uICsvSakusei.getFNameKj().trim());
                }

                //氏名カナ(姓)
                if(uICsvSakusei.getLNameKna() == null){
                    listData.add("");
                }else{
                    listData.add(uICsvSakusei.getLNameKna().trim());
                }

                //氏名カナ(名)

                if(uICsvSakusei.getFNameKna() == null){
                    listData.add("");
                }else{
                    listData.add(uICsvSakusei.getFNameKna().trim());
                }

                //性別
                if(uICsvSakusei.getSex() == null){
                    listData.add("");
                }else{
                    listData.add(uICsvSakusei.getSex().trim());
                }

                //年齢
                if(uICsvSakusei.getAge() == null){
                    listData.add("");
                }else{
                    listData.add(uICsvSakusei.getAge().trim());
                }

                //事業方針説明会出席
                if (ProjectPlanStatusInfoUtil.TAISHO_PROJECTPLAN.equals(taishoListChoice)
                        || ProjectPlanStatusInfoUtil.TAISHO_DOWNLOAD.equals(taishoListChoice)) {
                    if(uICsvSakusei.getJigyoFlg() == null){
                        listData.add("");
                    }else{
                        listData.add(uICsvSakusei.getJigyoFlg().trim());
                    }
                }

                //懇親会出席
                if (ProjectPlanStatusInfoUtil.TAISHO_KONSIN.equals(taishoListChoice)
                        || ProjectPlanStatusInfoUtil.TAISHO_DOWNLOAD.equals(taishoListChoice)) {
                    if(uICsvSakusei.getKonshinFlg() == null){
                        listData.add("");
                    }else{
                        listData.add(uICsvSakusei.getKonshinFlg().trim());
                    }
                }

                //共栄会定時総会出席
                if (ProjectPlanStatusInfoUtil.TAISHO_KYOEIKAI.equals(taishoListChoice)
                        || ProjectPlanStatusInfoUtil.TAISHO_DOWNLOAD.equals(taishoListChoice)) {
                    if(uICsvSakusei.getKyoeiFlg() == null){
                        listData.add("");
                    }else{
                        listData.add(uICsvSakusei.getKyoeiFlg().trim());
                    }
                }

                //本部手配宿泊
                if (ProjectPlanStatusInfoUtil.TAISHO_HONBU_SHUKUHAKU.equals(taishoListChoice)
                        || ProjectPlanStatusInfoUtil.TAISHO_DOWNLOAD.equals(taishoListChoice)) {
                    if(uICsvSakusei.getShukuhakuFlg() == null){
                        listData.add("");
                    }else{
                        listData.add(uICsvSakusei.getShukuhakuFlg().trim());
                    }
                }

                //備考
                if(uICsvSakusei.getNote() == null){
                    listData.add("");
                }else{
                    listData.add(uICsvSakusei.getNote().trim());
                }

                //事業方針説明会欠席理由
                if(uICsvSakusei.getAbsenceReason() == null){
                    listData.add("");
                }else{
                    listData.add(uICsvSakusei.getAbsenceReason().trim());
                }

                //委任状
                if(uICsvSakusei.getIninMiseCd() == null){
                    listData.add("");
                }else{
                    listData.add(uICsvSakusei.getIninMiseCd().trim());
                }

                //更新者ID
                if(uICsvSakusei.getLastUser() == null){
                    listData.add("");
                }else{
                    listData.add(uICsvSakusei.getLastUser().trim());
                }

                //更新者名称
                if(uICsvSakusei.getUserNameKj() == null){
                    listData.add("");
                }else{
                    listData.add(uICsvSakusei.getUserNameKj().trim());
                }

                //オーナー契約終了日
                if(uICsvSakusei.getKeiyakuEnd() == null){
                    listData.add("");
                }else{
                    listData.add(Converter.dateToString("yyyy/MM/dd", Converter.stringToDate("yyyyMMdd", uICsvSakusei.getKeiyakuEnd().trim())));

                }

                listCSV.add(listData);

            }

        }
        catch (Exception ex) {
            throw new FtlSystemException("CSV作成");
        }
        return listCSV;
    }

    /* ヘッダ部 */
    private void makeHeader(ProjectPlanStatusInfoDto dto, List listCsv) {

        List header1 = new ArrayList();
        List header2 = new ArrayList();
        List header3 = new ArrayList();
        List header4 = new ArrayList();
        List header5 = new ArrayList();
        String taishouJokenChoice = dto.getTaishouJokenChoice();
        String taishoListChoice = dto.getTaishouListChoice();
        String attendKbn = dto.getAttendKbnChoice();

        try{
            //結果確認ダウンロード時の場合。
            if(dto.isKekka() && ProjectPlanStatusInfoUtil.TAISHO_DOWNLOAD.equals(taishoListChoice)){
                attendKbn = "";
            }else{
                taishouJokenChoice = dto.getSpTaishoJoken();
                taishoListChoice = dto.getSpTaisho();
            }
            // 一行目（開催日、本部申込開始日、オーナー申込開始日）
            header1.add("開催日：");
            header1.add(Converter.dateToString("yyyy/MM/dd", Converter.stringToDate("yyyyMMdd", dto.getEntityUIStatusInfo().getKaisaiDt()))
                    + "(" + Converter.dateToString("E", Converter.stringToDate("yyyyMMdd", dto.getEntityUIStatusInfo().getKaisaiDt())) + ")");
            header1.add("");
            //ダウンロードボタン押下時の場合。
            if(!ProjectPlanStatusInfoUtil.TAISHO_DOWNLOAD.equals(taishoListChoice)){
                taishouJokenChoice = dto.getSpTaishoJoken();
                taishoListChoice = dto.getSpTaisho();
                header1.add("条件：");
                header1.add(ProjectPlanStatusInfoUtil.getTaishoJokenName(taishouJokenChoice));
                header1.add("");
            }
            header1.add("本部申込開始日：");
            header1.add(Converter.dateToString("yyyy/MM/dd", Converter.stringToDate("yyyyMMdd", dto.getEntityUIStatusInfo().getHonbuFromDt())));
            header1.add("");
            header1.add("オーナー申込開始日：");
            header1.add(Converter.dateToString("yyyy/MM/dd", Converter.stringToDate("yyyyMMdd", dto.getEntityUIStatusInfo().getOnerFromDt())));


            // ニ行目（説明会名称、本部申込終了日、オーナー申込終了日）
            header2.add("説明会名称：");
            header2.add(dto.getEntityUIStatusInfo().getEntryTitle());
            header2.add("");
            //ダウンロードボタン押下時の場合。
            if(!ProjectPlanStatusInfoUtil.TAISHO_DOWNLOAD.equals(taishoListChoice)){
                header2.add("対象：");
                header2.add(ProjectPlanStatusInfoUtil.getTaishoName(taishoListChoice));
                header2.add("");
            }
            header2.add("本部申込終了日：");
            header2.add(Converter.dateToString("yyyy/MM/dd", Converter.stringToDate("yyyyMMdd", dto.getEntityUIStatusInfo().getHonbuToDt())));
            header2.add("");
            header2.add("オーナー申込終了日：");
            header2.add(Converter.dateToString("yyyy/MM/dd", Converter.stringToDate("yyyyMMdd", dto.getEntityUIStatusInfo().getOnerToDt())));


            //三行目（注意書き）
            header3.add("※支部コード、支部名称はオーナーマスタに登録されている支部が表示されます。");
            header3.add("");
            header3.add("");
            //ダウンロードボタン押下時の場合。
            if(!ProjectPlanStatusInfoUtil.TAISHO_DOWNLOAD.equals(taishoListChoice)
                    && !ProjectPlanStatusInfoUtil.TAISHO_HONBU_SHUKUHAKU.equals(taishoListChoice)){
                header3.add("区分：");
                header3.add(ProjectPlanStatusInfoUtil.getKbnName(attendKbn));
                header3.add("");
            }


            //四行目（空欄）
            header4.add("");


            //五行目（データのヘッダ）
            header5.add("支部コード");
            header5.add("支部名称");
            header5.add("オーナーコード");
            header5.add("オーナー名称");
            header5.add("SEQ_NO");
            header5.add("店舗コード");
            header5.add("店舗名称");
            header5.add("申込責任者");
            header5.add("申込者電話番号");
            header5.add("参加者氏(漢字)");
            header5.add("参加者名(漢字)");
            header5.add("参加者氏(フリガナ)");
            header5.add("参加者名(フリガナ)");
            header5.add("性別");
            header5.add("年齢");

            //結果確認ダウンロード時の場合。
            if(ProjectPlanStatusInfoUtil.TAISHO_DOWNLOAD.equals(taishoListChoice)){
                //条件項目『対象』の名称を設定する。
                header5.add(ProjectPlanStatusInfoUtil.LABEL_TAISHO_PROJECTPLAN);
                header5.add(ProjectPlanStatusInfoUtil.LABEL_TAISHO_KONSIN);
                header5.add(ProjectPlanStatusInfoUtil.LABEL_TAISHO_KYOEIKAI);
                header5.add(ProjectPlanStatusInfoUtil.LABEL_TAISHO_HONBU_SHUKUHAKU);

            }
            //各表示結果からのダウンロードの場合。
            else{
                //選択された条件項目『対象』のみの名称を設定する。
                header5.add(ProjectPlanStatusInfoUtil.getTaishoName(taishoListChoice));
            }

            header5.add("備考");
            header5.add("事業方針説明会欠席理由");
            header5.add("委任状");
            header5.add("更新者ID");
            header5.add("更新者名称");
            header5.add("オーナー契約終了日");

            listCsv.add(header1);
            listCsv.add(header2);
            listCsv.add(header3);
            listCsv.add(header4);
            listCsv.add(header5);

        }
        catch (Exception ex) {
            throw new FtlSystemException("CSV作成");
        }

    }

    /**
     * @return projectPlanStatusInfoUICsvSakuseiDao を戻します。
     */
    public UICsvSakuseiDao getProjectPlanStatusInfoUICsvSakuseiDao() {
        return projectPlanStatusInfoUICsvSakuseiDao;
    }

    /**
     * @param projectPlanStatusInfoUICsvSakuseiDao 設定する projectPlanStatusInfoUICsvSakuseiDao。
     */
    public void setProjectPlanStatusInfoUICsvSakuseiDao(
            UICsvSakuseiDao projectPlanStatusInfoUICsvSakuseiDao) {
        this.projectPlanStatusInfoUICsvSakuseiDao = projectPlanStatusInfoUICsvSakuseiDao;
    }

    public void validate(CsvOutputDto csvOutputDto) {
        // TODO 自動生成されたメソッド・スタブ

    }

    public List execute(ProjectPlanStatusInfoDto projectPlanStatusInfoDto) {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }
}
